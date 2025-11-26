package com.sana.combination.entity.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author LON
 * @create 2025/11/26 10:28
 */
@Data
@Schema(description = "项目详情")
public class GoviewProjectResult {

    @Schema(description = "主键")
    private String id;

    @Schema(description = "项目名称")
    private String projectName;

    @Schema(description = "项目状态[-1未发布,1发布]")
    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "创建人id")
    private String createUserId;

    @Schema(description = "删除状态[1删除,-1未删除]")
    private Integer isDelete;

    @Schema(description = "首页图片")
    private String indexImage;

    @Schema(description = "项目介绍")
    private String remarks;

    private String content;

}
