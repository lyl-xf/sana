package com.sana.system.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther LON
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "组织角色")
public class SysOrgQuery {

    @Schema(description = "组织角色")
    private String name;

}
