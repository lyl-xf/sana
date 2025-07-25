package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LON
 * @create 2025/7/25 9:54
 */
@Data
@Schema(description = "接口api权限")
public class SysApiListResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "apiList接口code")
    private String code;
    @Schema(description = "apiList接口url")
    private String url;
}
