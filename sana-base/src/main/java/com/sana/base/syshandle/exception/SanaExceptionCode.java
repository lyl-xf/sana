package com.sana.base.syshandle.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LON
 * @create 2025/7/12
 */
@Getter
@AllArgsConstructor
public enum SanaExceptionCode {

    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    INVALID_PARAMETER(300, "无效参数");


    private final int code;
    private final String msg;
}
