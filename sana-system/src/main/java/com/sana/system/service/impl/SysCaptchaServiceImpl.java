package com.sana.system.service.impl;

import cn.hutool.core.lang.UUID;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.result.SysCaptchaResult;
import com.sana.system.service.SysCaptchaService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 16:58
 */
@Slf4j
@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {


    @Resource
    private RedisUtils redisUtils;
    @Value("${spring.captchaEnabled}")
    private Boolean captchaEnabled;


    @Override
    public SysCaptchaResult generate() {
        // 如果开启了则captchaEnabled字段为ture，否则为false，尽量一个请求解决
        SysCaptchaResult captchaVO = new SysCaptchaResult();
        if (!captchaEnabled) {
            captchaVO.setCaptchaEnabled(false);
            return captchaVO;
        }

        // 生成验证码key
        String key = UUID.randomUUID().toString() +  Instant.now().getNano();

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.FONT_9);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = CacheKeyBuilder.captchaKey(key);
        redisUtils.set(redisKey, captcha.text(), 300);
        captchaVO.setKey(key);
        captchaVO.setImage(image);
        captchaVO.setCaptchaEnabled(true);
        return captchaVO;
    }



    @Override
    public SanaResult<Boolean> captchaEnabled() {
        return SanaResult.ok(captchaEnabled);
    }


}
