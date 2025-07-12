package com.sana.base.syshandle.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LON
 * @create 2025/7/12
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    /**
     * 停用
     */
    DISABLE(0),
    /**
     * 正常
     */
    ENABLED(1);

    private final int value;

}
