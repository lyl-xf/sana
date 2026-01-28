package com.sana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LON
 * @create 2025/7/12
 */
@Getter
@AllArgsConstructor
public enum GeneralPrefixEnum {


    /**
     * 暂不使用
     */
    //TABLE_PREFIX("sb"),
    /**
     * 暂不使用
     */
    //DEVICE_TOPIC_PREFIX("/SB/"),
    /**
     * 规则前缀
     */
    RULE_PREFIX("rule"),

    /**
     * 边缘计算前缀
     */
    EDGE_PREFIX("BY"),
    /**
     * 边缘计算主题前缀
     */
    EDGE_TOPIC_PREFIX("/BY/"),

    /**
     * 脚本后缀
     */
    AVIATORSCRIPT_SUFFIX(".av"),

    /**
     * 脚本Cache名称
     */
    AVIATORSCRIPT_PREFIX("hello");


    private final String value;
}
