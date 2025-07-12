package com.sana.base.syshandle.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @author LON
 * @create 2025/7/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SanaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public SanaException(String msg) {
        super(msg);
        this.code = SanaExceptionCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }
    public SanaException(String msg, Throwable e) {
        super(msg, e);
        this.code = SanaExceptionCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }
}
