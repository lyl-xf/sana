package com.sana.system.service;

import com.sana.system.entity.query.SysAccountLoginQuery;
import com.sana.system.entity.result.SysUserAuthDataResult;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysAuthService {

    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysUserAuthDataResult loginByAccount(SysAccountLoginQuery login);


}
