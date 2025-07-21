package com.sana.system.service;

import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.result.SysCaptchaResult;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 16:56
 */
public interface SysCaptchaService {

    /**
     * 生成验证码
     */
    SysCaptchaResult generate();

    /**
     * 是否开启验证码
     * @return
     */
    SanaResult<Boolean> captchaEnabled();
}
