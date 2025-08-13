package com.sana.base.syshandle.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;


/**
 * 用户
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MyUserDetails {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别   0：男   1：女   2：未知
     */
    private Integer gender;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 机构ID
     */
    private String signature;

    /**
     * 超级管理员   0：否   1：是
     */
    private Integer superAdmin;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 数据权限范围
     * null：表示全部数据权限
     */
    private List<Long> dataScopeList;
    /**
     * 拥有权限集合
     */
    private List<String> authoritySet;


}
