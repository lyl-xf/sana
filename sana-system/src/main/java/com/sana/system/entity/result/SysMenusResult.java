package com.sana.system.entity.result;

import com.sana.base.syshandle.tree.HandleTree;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单信息")
public class SysMenusResult extends HandleTree<SysMenusResult> {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "pid")
    private Long pid;
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单URL")
    private String path;
    @Schema(description = "内容")
    private String component;
    @Schema(description = "重定向地址")
    private String redirect;
    @Schema(description = "meta元数据")
    private SysMetaResult meta;
    @Schema(description = "apiList接口api权限")
    private List<SysApiListResult> apiList;
}
