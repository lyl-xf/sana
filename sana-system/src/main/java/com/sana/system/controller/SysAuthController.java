package com.sana.system.controller;

import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.query.SysAccountLoginQuery;
import com.sana.system.entity.result.SysUserAuthDataResult;
import com.sana.system.service.SysAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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

    /**
     * 账号密码登录接口。
     * 使用账号和密码进行登录验证，并返回用户令牌。
     * @param login 包含账号和密码信息的登录请求对象。
     * @return 返回登录结果，其中包含用户令牌信息。
     */
    @PostMapping("/login")
    @Operation(summary = "账号密码登录")
    //@SaCheckPermission("")
    public SanaResult<SysUserAuthDataResult> login(@RequestBody SysAccountLoginQuery login) {
        // 通过账号服务进行账号密码登录，返回用户令牌
        SysUserAuthDataResult token = sysAuthService.loginByAccount(login);
        // 返回登录成功的结果，包含用户令牌
        return SanaResult.ok(token);
    }


}
