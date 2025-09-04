package com.sana.base.syshandle.operatelog;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.usercache.UserContextUtil;
import com.sana.base.utils.HttpContextUtils;
import com.sana.base.utils.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author LON
 * @create 2025/8/30
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class OperateLogAspect {

    private final RedisUtils redisUtils;

    private void save(ProceedingJoinPoint joinPoint, OptLog optLog, LocalDateTime startTime) {


        OptLogEntity log = new OptLogEntity();

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (request != null) {
            log.setIp(request.getRemoteAddr());
            log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            log.setReqUri(request.getRequestURI());
            log.setReqMethod(request.getMethod());
            log.setDuration((int) LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis());
        }
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        if(user!=null){
            log.setUserId(user.getId());
            log.setRealName(user.getRealName());
            log.setOperateType(optLog.type()[0].getValue());
            log.setModule(optLog.module());
            log.setName(optLog.name());
        }


        if (StrUtil.isBlank(log.getModule())) {
            Tag tag = ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(Tag.class);
            if (tag != null) {
                log.setModule(tag.name());
            }
        }
        if (StrUtil.isBlank(log.getName())) {
            Operation operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Operation.class);
            if (operation != null) {
                log.setName(operation.summary());
            }
        }

        if (joinPoint.getArgs().length > 0) {
            if (joinPoint.getArgs()[0] instanceof MultipartFile) {
                log.setReqParams("file");
            } else {
                log.setReqParams(joinPoint.getArgs().length > 0 ? JsonUtils.toJsonString(joinPoint.getArgs()) : null);
            }
        }
        // 保存操作日志
        String key = CacheKeyBuilder.logKey();
        redisUtils.leftPush(key, log);
    }

    @Around("@annotation(optLog)")
    public Object around(ProceedingJoinPoint joinPoint, OptLog optLog) throws Throwable {

        try {
            Object result = joinPoint.proceed();
            //保存日志
            save(joinPoint, optLog, LocalDateTime.now());
            return result;
        } catch (Exception e) {
            log.info("执行异常：" + e);
            return null;
        }
    }
}
