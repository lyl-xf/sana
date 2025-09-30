package com.sana.devices.entity.vo.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备查询实体")
public class DeviceItemQuery extends SanaPageParams {
    /**
     * 机构名称
     */
    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "所属部门，即product_type_id")
    private Integer groupId;
}
