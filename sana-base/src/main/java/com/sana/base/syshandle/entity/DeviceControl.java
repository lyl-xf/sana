package com.sana.base.syshandle.entity;

import lombok.Data;

/**
 * 设备控制
 * @author LON
 * @create 2025/7/12
 */

@Data
public class DeviceControl {

    /**
     * id
     */
    private Long id;
    /**
     * 设备id
     */
    private Long deviceItemId;
    /**
     * 功能类型
     */
    private Integer controlType;
    /**
     * 功能名称
     */
    private String controlName;
    /**
     * 属性标志
     */
    private String controlMode;
    /**
     * 数据类型
     */
    private String controlDataType;
    /**
     * 发送数据属性值
     */
    private String controlDefultValue;
}
