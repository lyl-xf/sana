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
@TableName("device_mode")
public class DeviceModeEntity extends BaseEntity {
    /**
     * 属性名称
     */
    private String modeName;
    /**
     * 属性标志
     */
    private String modeSigns;

    /**
     * 数据类型
     */
    private String modeDataType;

    /**
     * 数据来源
     */
    private Integer modeDataSource;

    /**
     * 控制属性  0：否   1：是
     */
    private Integer modeControlAtt;
    /**
     * 补充说明
     */
    private String modeRemark;
    /**
     * 设备id
     */
    private Long deviceItemId;
    /**
     * 默认值
     */
    private Long defultValue;
}
