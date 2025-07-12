package com.sana.base.framework.token;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12 16:01
 */
@Component
public class DataScope implements StpInterface {

    //todo 完善返回一个账号所拥有的权限码集合
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return List.of();
    }


    //todo 完善返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return List.of();
    }
}
