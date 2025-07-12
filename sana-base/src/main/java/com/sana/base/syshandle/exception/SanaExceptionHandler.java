package com.sana.base.syshandle.exception;

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

    @ExceptionHandler(Exception.class)
    public SanaResult<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return SanaResult.error(SanaExceptionCode.INTERNAL_SERVER_ERROR);
    }
}
