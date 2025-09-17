package com.sana.system.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.query.SysAccountLoginQuery;
import com.sana.system.entity.result.AccessTokenResult;
import com.sana.system.entity.result.SysUserAuthDataResult;
import com.sana.system.entity.save.SysUserSave;
import com.sana.system.service.SysAuthService;
import com.sana.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@RestController
@RequestMapping("/sys/auth")
@ApiSupport(author = "LON")
@Tag(name = "认证管理")
public class SysAuthController {
    @Resource
    private SysAuthService sysAuthService;

    @Resource
    private SysUserService sysUserService;

    /**
     * 账号密码登录接口。
     * 使用账号和密码进行登录验证，并返回用户令牌。
     * @param login 包含账号和密码信息的登录请求对象。
     * @return 返回登录结果，其中包含用户令牌信息。
     */
    @PostMapping("/login")
    @Operation(summary = "账号密码登录")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("")
    public SanaResult<SysUserAuthDataResult> login(@RequestBody SysAccountLoginQuery login) {
        // 通过账号服务进行账号密码登录，返回用户令牌
        SysUserAuthDataResult token = sysAuthService.loginByAccount(login);
        // 返回登录成功的结果，包含用户令牌
        return SanaResult.ok(token);
    }

    /**
     * 注册用户接口。
     * 使用用户信息进行用户注册，并返回注册结果。
     * @param vo 用户信息对象，包含用户名、密码、手机号码等字段。
     * @return 返回注册结果，如果注册成功则返回成功信息，否则返回错误信息。
     */
    @PostMapping("/registerUser")
    @Operation(summary = "获取最新版本号")
    public SanaResult<String> registerUser(@RequestBody @Valid SysUserSave vo) {
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())) {
            return SanaResult.error("密码不能为空");
        }
        String hashedPassword = BCrypt.hashpw(vo.getPassword());
        // 密码加密
        vo.setPassword(hashedPassword);
        // 保存
        sysUserService.registerUser(vo);

        return SanaResult.ok();
    }
    @PostMapping("/logout")
    @Operation(summary = "退出登陆")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<AccessTokenResult> logout() {
        // 调用服务层方法，处理用户登出逻辑
        sysAuthService.logout();
        // 返回成功响应
        return SanaResult.ok();
    }

    @GetMapping("/ver")
    @Operation(summary = "获取最新版本号")
    public SanaResult<String> ver() {
        // 返回登录成功的结果，包含用户令牌
        return SanaResult.ok(sysAuthService.getVersion());
    }
}
