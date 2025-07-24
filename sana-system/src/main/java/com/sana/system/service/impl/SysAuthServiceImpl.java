package com.sana.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.enums.UserStatusEnum;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.syshandle.usercache.UserCacheService;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.query.SysAccountLoginQuery;
import com.sana.system.entity.result.SysUserAuthDataResult;
import com.sana.system.entity.result.UserInfoResult;
import com.sana.system.service.SysAuthService;
import com.sana.system.service.SysRoleService;
import com.sana.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Value("${spring.captchaEnabled}")
    private Boolean captchaEnabled;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private StpInterfaceImpl stpInterface;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private UserCacheService userCacheService;


    @Override
    public SysUserAuthDataResult loginByAccount(SysAccountLoginQuery login) {

            if(captchaEnabled){
                boolean flag = validate(login.getKey(), login.getCaptcha());
                if (!flag) {
                    throw new SanaException("验证码错误");
                }
            }
            SysUserEntity user =  sysUserService.getUserName(login.getUsername());
/*        // 注册时加密密码
        String hashedPassword = BCrypt.hashpw(login.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        sysUserService.updateById(user);*/
            if (Objects.isNull(user)) {
                throw new SanaException("登录用户：" + login.getUsername() + " 不存在");
            }

            if (!BCrypt.checkpw( login.getPassword(),user.getPassword())) {
                throw new SanaException("账号密码不正确");
            }

            if (user.getStatus() == UserStatusEnum.DISABLE.getValue()){
                throw new SanaException("用户:"+ login.getUsername() + " 已停用，请联系管理员");
            }
            StpUtil.login(user.getId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

            if(tokenInfo != null){
                //SysUserAuthDataResult sysUserAuthDataResult = getUserInfo(tokenInfo, user);
                return getUserInfo(tokenInfo, user);
            }else {
                throw new SanaException("登录失败");
            }
    }


    /**
     * 获取用户信息
     *
     * @param tokenInfo
     * @param user
     * @return
     */
    public SysUserAuthDataResult getUserInfo(SaTokenInfo tokenInfo, SysUserEntity user){
        // 返回数据
        SysUserAuthDataResult sysUserAuthDataResult = new SysUserAuthDataResult();
        sysUserAuthDataResult.setToken(tokenInfo.tokenValue);

        //查询用户菜单按钮权限
        List<String> authoritySet = stpInterface.getPermissionList(user.getId(), "user");

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
        userInfoResult.setRole(authoritySet);
        sysUserAuthDataResult.setUserInfo(userInfoResult);

        //缓存用户数据，sa-token中没有类似于security那样使用UserDetails，所以这里手动存储用户信息，用于后面的数据处理

        //检索数据权限，加入用户对象中
        List<Long> dataScopeList = sysRoleService.getDataScope(user);
        MyUserDetails myUserDetails = new MyUserDetails();
        BeanUtils.copyProperties(user, myUserDetails);
        myUserDetails.setDataScopeList(dataScopeList);
        //myUserDetails.setAuthoritySet(authoritySet);
        MyUserDetails myUserDetail = userCacheService.getUserInfo(user.getId());
        if(myUserDetail!=null){
            userCacheService.updateUserInfo(user.getId(),myUserDetails);
        }else {
            userCacheService.cacheUserInfo(user.getId(), myUserDetails);
        }


        return sysUserAuthDataResult;
    }

    /**
     * 效验验证码
     *
     * @param key
     * @param code
     * @return
     */
    public boolean validate(String key, String code) {

        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }
        key = CacheKeyBuilder.captchaKey(key);
        String captcha = (String) redisUtils.get(key);
        // 删除验证码
        if (captcha != null) {
            redisUtils.delete(key);
        }
        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }



}
