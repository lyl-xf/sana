package com.sana.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.result.SysCaptchaResult;
import com.sana.system.service.SysCaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 16:54
 */
@Slf4j
@RestController
@RequestMapping("/sys/captcha")
@ApiSupport(author = "LON")
@Tag(name = "验证码")
public class SysCaptchaController {
    @Resource
    private SysCaptchaService sysCaptchaService;

    /**
     * 获取验证码
     * 本接口无需参数，调用后返回一个验证码结果对象。
     * @return Result<SysCaptchaResult> 返回验证码结果，其中包含验证码图片的Base64编码。
     */
    @GetMapping("/getCaptcha")
    public SanaResult<SysCaptchaResult> captcha() {

        // 生成并返回验证码
        return SanaResult.ok(sysCaptchaService.generate());
    }


    /**
     * 检查验证码功能是否开启。
     * 在配置文件中设置captchaEnabled属性即可
     *
     * @return {@link SanaResult <Boolean>} 结果对象，其中的 Boolean 值表示验证码功能是否开启。
     */
    @GetMapping("/enabled")
    @Operation(summary = "是否开启验证码")
    public SanaResult<Boolean> captchaEnabled() {
        // 调用服务层方法，获取验证码功能是否开启的状态
        return sysCaptchaService.captchaEnabled();
    }

}
