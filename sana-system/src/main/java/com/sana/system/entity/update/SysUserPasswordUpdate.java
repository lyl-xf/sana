package com.sana.system.entity.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "用户修改密码")
public class SysUserPasswordUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "原始密码")
    @NotBlank(message = "原始密码不能为空")
    private String password;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
