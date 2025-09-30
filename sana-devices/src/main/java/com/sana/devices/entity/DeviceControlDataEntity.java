package com.sana.devices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_control_data")
public class DeviceControlDataEntity extends BaseEntity {
     /**
     * 设备id
     */
     private Long deviceItemId;
     /**
     * 功能类型
     */
     private Integer controlType;
     /**
     * 控制类型名称
     */
     private String controlTypeLabel;
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
     * 属性类型
     */
     private String controlDataTypeLabel;
     /**
     * 属性值
     */
     private String controlDefultValue;

}
