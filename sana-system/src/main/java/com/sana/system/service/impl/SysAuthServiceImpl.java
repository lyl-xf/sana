package com.sana.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.query.SysAccountLoginQuery;
import com.sana.system.entity.result.SysUserAuthDataResult;
import com.sana.system.entity.result.UserInfoResult;
import com.sana.system.service.SysAuthService;
import com.sana.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@Service
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public SysUserAuthDataResult loginByAccount(SysAccountLoginQuery login) {
/*        if(captchaEnabled){
            boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
            if (!flag) {
                throw new SanaException("验证码错误");
            }
        }*/
        SysUserEntity user =  sysUserService.getUserName(login.getUsername());

        if (Objects.isNull(user)) {
            throw new SanaException("登录用户：" + login.getUsername() + " 不存在.");
        }
        if (!BCrypt.checkpw(user.getPassword(), login.getPassword())) {
            throw new SanaException("账号密码不正确");
        }
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 返回数据
        SysUserAuthDataResult sysUserAuthDataResult = new SysUserAuthDataResult();
        sysUserAuthDataResult.setToken(tokenInfo.tokenValue);
        //sysUserAuthDataResult.setRefreshToken(userTokenResult.getRefreshToken());
        //sysUserAuthDataResult.setAccessTokenExpire(tokenInfo.getTokenTimeout());
        //sysUserAuthDataResult.setRefreshTokenExpire(userTokenResult.getRefreshTokenExpire());

        UserInfoResult userInfoResult = new UserInfoResult();
        userInfoResult.setUserId(user.getId());
        userInfoResult.setUserName(user.getUsername());
        userInfoResult.setDashboard("0");
        userInfoResult.setGender(user.getGender());
        userInfoResult.setRealName(user.getRealName());
        userInfoResult.setSignature(user.getSignature());
        userInfoResult.setAvatar(user.getAvatar());
        userInfoResult.setEmail(user.getEmail());
        userInfoResult.setMobile(user.getMobile());
        //todo 检索角色信息
        //userInfoResult.setRole(user.getAuthoritySet());


        sysUserAuthDataResult.setUserInfo(userInfoResult);

        return sysUserAuthDataResult;

    }
}
