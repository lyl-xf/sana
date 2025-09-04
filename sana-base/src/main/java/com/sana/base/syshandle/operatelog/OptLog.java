package com.sana.base.syshandle.operatelog;

import com.sana.base.syshandle.enums.OperateTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LON
 * @create 2025/8/30
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLog {

    /**
     * 模块名
     */
    String module() default "";

    /**
     * 操作名
     */
    String name() default "";


    /**
     * 操作类型
     */
    OperateTypeEnum[] type() default OperateTypeEnum.QUERY;

}
