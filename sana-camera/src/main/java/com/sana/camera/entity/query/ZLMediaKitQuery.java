package com.sana.camera.entity.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/11/5 11:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "服务器配置查询实体")
public class ZLMediaKitQuery {

    private String serverId;

    private String host;


}
