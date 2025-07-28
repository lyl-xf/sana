package com.sana.system.entity.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther LON
 */
@Data
@Schema(description = "菜单")
public class SysMetaUpdate implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Boolean hidden;
    private Boolean affix;
    private String icon;
    private String type;
    private Boolean hiddenBreadcrumb;
    private Boolean active;
    private String color;
    private Boolean fullpage;
    private String role;
}
