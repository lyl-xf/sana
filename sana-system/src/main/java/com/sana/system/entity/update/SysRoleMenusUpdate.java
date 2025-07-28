package com.sana.system.entity.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther LON
 */
@Data
@Schema(description = "角色授权接受实体")
public class SysRoleMenusUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色编码")
    private List<String> menus;

    @Schema(description = "控制台")
    private List<String> grids;

    @Schema(description = "数据类型")
    private Integer grid;

    @Schema(description = "角色id")
    private Long roleId;

}
