package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "用户信息")
public class UserInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String dashboard;
    private String userName;
    private List<String> role;
    private String realName;
    private Integer gender;
    private String signature;
    private String avatar;
    private String mobile;
    private String email;

}
