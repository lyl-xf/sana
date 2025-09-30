package com.sana.devices.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "设备实例")
public class DeviceItemResult{

    @Schema(description = "id")
    private Long id;
    /**
     * 设备图片
     */
    @Schema(description = "设备图片")
    private String imgs;
    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    private String name;
    /**
     * 设备编码
     */
    @Schema(description = "设备编码")
    private String deviceNumber;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 产品类型id
     */
    @Schema(description = "产品类型id")
    private Long productTypeId;

    /**
     * 产品编辑回显内容
     */
    @Schema(description = "产品编辑回显内容")
    private List<Long> productTypeShow;

    /**
     * 设备主题
     */
    @Schema(description = "设备主题")
    private String topicName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 创建时间
     */
    @Schema(description = "修改时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String creatorName;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String updaterName;
}
