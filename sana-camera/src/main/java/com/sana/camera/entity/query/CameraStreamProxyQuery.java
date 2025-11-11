package com.sana.camera.entity.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/11/11 15:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备查询实体")
public class CameraStreamProxyQuery extends SanaPageParams {

    /**
     * 机构名称
     */
    @Schema(description = "设备名称")
    private String name;

}
