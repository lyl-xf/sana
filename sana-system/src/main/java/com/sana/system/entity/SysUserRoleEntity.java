package com.sana.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_user_role")
public class SysUserRoleEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 用户ID
     */
    private Long userId;
}
