package com.sana.combination.entity.query;

import com.sana.base.syshandle.conbination.QueryPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "数据列表查询")
public class GoviewQuery extends QueryPageParams {

}
