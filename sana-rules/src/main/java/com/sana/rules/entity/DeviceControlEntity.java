package com.sana.rules.entity;

import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
public class DeviceControlEntity{
     private Long id;

     private String deviceItemName;
     /**
     * 设备id
     */
     private Long deviceItemId;

     /**
     * 功能名称
     */
     private String controlName;
     /**
     * 属性标志
     */
     private String controlMode;

     /**
     * 属性值
     */
     private String controlDefultValue;

}
