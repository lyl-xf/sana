package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "菜单元信息")
public class SysMetaResult implements Serializable {
    private static final long serialVersionUID = 1L;
    //private Long menu_id;
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
    private Integer sort;
}
