package com.sana.system.entity.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询")
public class SysUserQuery extends SanaPageParams {
    @Schema(description = "用户名")
    private String name;

    @Schema(description = "所属部门，即org_id")
    private Integer groupId;


}
