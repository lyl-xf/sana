package com.sana.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/8/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_org")
public class SysUserOrgEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long orgId;
}
