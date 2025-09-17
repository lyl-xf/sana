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
        try {
            OptLogEntity log = new OptLogEntity();

            // 设置请求相关信息
            setRequestInfo(log);

            // 设置用户相关信息
            setUserInfo(log, optLog);

            // 设置模块和操作名称
            setModuleAndName(joinPoint, log, optLog);

            // 设置请求参数
            setRequestParams(joinPoint, log);

            // 保存操作日志到Redis
            String key = CacheKeyBuilder.logKey();
            redisUtils.leftPush(key, log);
        } catch (Exception e) {
            log.error("保存操作日志失败", e);
        }
    }



    /**
     * 设置请求相关信息
     * @param log 日志实体
     */
    private void setRequestInfo(OptLogEntity log) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (request != null) {
            log.setIp(request.getRemoteAddr());
            log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            log.setReqUri(request.getRequestURI());
            log.setReqMethod(request.getMethod());
            log.setDuration((int) LocalDateTimeUtil.between(LocalDateTime.now(), LocalDateTime.now()).toMillis());
        }
    }

    /**
     * 设置用户相关信息
     * @param log 日志实体
     * @param optLog 操作日志注解
     */
    private void setUserInfo(OptLogEntity log, OptLog optLog) {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        if (user != null) {
            log.setUserId(user.getId());
            log.setRealName(user.getRealName());
            if (optLog.type().length > 0) {
                log.setOperateType(optLog.type()[0].getValue());
            }
            log.setModule(optLog.module());
            log.setName(optLog.name());
        }
    }

    /**
     * 设置模块和操作名称
     * @param joinPoint 切点
     * @param log 日志实体
     * @param optLog 操作日志注解
     */
    private void setModuleAndName(ProceedingJoinPoint joinPoint, OptLogEntity log, OptLog optLog) {
        // 如果注解中没有设置模块名，则从类上的@Tag注解获取
        if (StrUtil.isBlank(log.getModule())) {
            Tag tag = joinPoint.getTarget().getClass().getAnnotation(Tag.class);
            if (tag != null) {
                log.setModule(tag.name());
            }
        }

        // 如果注解中没有设置操作名，则从方法上的@Operation注解获取
        if (StrUtil.isBlank(log.getName())) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Operation operation = signature.getMethod().getAnnotation(Operation.class);
            if (operation != null) {
                log.setName(operation.summary());
            }
        }
    }

    /**
     * 设置请求参数
     * @param joinPoint 切点
     * @param log 日志实体
     */
    private void setRequestParams(ProceedingJoinPoint joinPoint, OptLogEntity log) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            if (args[0] instanceof MultipartFile) {
                log.setReqParams("file");
            } else {
                log.setReqParams(JsonUtils.toJsonString(args));
            }
        }
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
