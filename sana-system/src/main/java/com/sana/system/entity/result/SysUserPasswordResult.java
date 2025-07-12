package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "用户修改密码")
public class SysUserPasswordResult implements Serializable {

    @Schema(description = "用户id", required = true)
    private Long id;

}
