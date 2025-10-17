package com.sana.base.syshandle.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.util.SaResult;
import com.sana.base.syshandle.result.SanaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@RestControllerAdvice
public class SanaExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(SanaException.class)
    public SanaResult<String> handleException(SanaException ex) {
        return SanaResult.error(ex.getCode(), ex.getMsg());
    }

    /**
     * 处理所有不可知的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public SanaResult<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return SanaResult.error(SanaExceptionCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * 处理所有接口权限数据验证异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handlerNotLoginException(NotPermissionException nle)
            throws Exception {
        // 打印堆栈，以供调试
        nle.printStackTrace();
        // 返回给前端
        return SaResult.error(nle.getMessage());
    }


    /**
     * 处理所有接口登录数据验证异常
     */
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerNotLoginException(NotLoginException nle)
            throws Exception {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未能读取到有效 token";
        }
        else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token 无效";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token 已过期";
        }
        else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token 已被顶下线";
        }
        else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token 已被踢下线";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_FREEZE)) {
            message = "token 已被冻结";
        }
        else if(nle.getType().equals(NotLoginException.NO_PREFIX)) {
            message = "未按照指定前缀提交 token";
        }
        else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return SaResult.error(message);
    }
}
