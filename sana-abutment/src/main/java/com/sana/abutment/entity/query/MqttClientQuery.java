package com.sana.abutment.entity.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/9/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "客户端列表查询")
public class MqttClientQuery extends SanaPageParams {

    @Schema(description = "客户端类型")
    private int operateType;

}
