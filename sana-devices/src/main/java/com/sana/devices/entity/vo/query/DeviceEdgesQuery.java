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
@Schema(description = "边缘实例查询实体")
public class DeviceEdgesQuery extends SanaPageParams {
    /**
     * 机构名称
     */
    @Schema(description = "实例名称")
    private String name;
}
