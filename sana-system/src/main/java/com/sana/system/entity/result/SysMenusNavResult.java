package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "兼容前端需要的数据格式")
public class SysMenusNavResult {

    private static final long serialVersionUID = 1L;
    @Schema(description = "仪表盘")
    private List<String> dashboardGrid;
    @Schema(description = "菜单")
    private List<SysMenusResult> menu;
    @Schema(description = "权限")
    private List<String> permissions;
}
