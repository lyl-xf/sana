package com.sana.system.entity.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "认证返回数据")
public class SysUserAuthDataResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "token")
    @JsonProperty(value = "token")
    private String token;

    @Schema(description = "refresh_token")
    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @Schema(description = "access_token 过期时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date accessTokenExpire;

    @Schema(description = "refresh_token 过期时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date refreshTokenExpire;

    @Schema(description = "userInfo")
    private UserInfoResult userInfo;
}
