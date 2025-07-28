package com.sana.system.entity;

import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单api权限管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenusApiEntity extends BaseEntity {
    private Long menuId;
    private String code;
    private String url;
}