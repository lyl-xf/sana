package com.sana.system.entity.save;

import com.sana.system.entity.SysMenusApiEntity;
import com.sana.system.entity.save.SysMetaSave;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther LON
 */
@Data
@Schema(description = "菜单")
public class SysMenusSave implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;
    @Schema(description = "上级id")
    private Long pid;
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单路径")
    private String path;
    @Schema(description = "菜单内容")
    private String component;
    @Schema(description = "重定向地址")
    private String redirect;
    @Schema(description = "菜单元数据")
    private SysMetaSave meta;
    @Schema(description = "菜单api权限")
    private List<SysMenusApiEntity> apiList;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "code")
    private String code;
    @Schema(description = "url")
    private String url;

}
