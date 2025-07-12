package com.sana.system.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "账号登录")
public class SysAccountLoginQuery {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "唯一key")
    private String key;

    @Schema(description = "验证码")
    private String captcha;
}
