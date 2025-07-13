package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 16:56
 */
@Data
@Schema(description = "图片验证码")
public class SysCaptchaResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "key")
    private String key;

    @Schema(description = "image base64")
    private String image;

    @Schema(description = "是否开启验证码")
    private Boolean captchaEnabled;
}

