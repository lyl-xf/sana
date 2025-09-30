package com.sana.devices.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sana.base.syshandle.tree.HandleTree;
import com.sana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "产品类型")
@EqualsAndHashCode(callSuper=false)
public class DeviceProductTypeResult extends HandleTree<DeviceProductTypeResult> {
    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private Long pid;
    /**
     * 机构名称
     */
    @Schema(description = "产品名称")
    private String label;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 接入方式
     */
    @Schema(description = "接入方式")
    private Integer deviceAbutmentType;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

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
