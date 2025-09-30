package com.sana.rules.entity.vo.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "规则分页查询实体")
public class RulesItemPageQuery extends SanaPageParams {

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String name;

}
