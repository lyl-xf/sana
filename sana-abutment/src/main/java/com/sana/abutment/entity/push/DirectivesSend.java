package com.sana.abutment.entity.push;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @create 2025/9/21
 */
@Data
@Schema(description = "发送消息实体")
public class DirectivesSend {

    @Schema(description = "发送主题")
    private String topic;

    @Schema(description = "发送数据")
    private String pushData;

}