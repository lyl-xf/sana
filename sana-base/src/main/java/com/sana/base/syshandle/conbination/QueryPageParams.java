package com.sana.base.syshandle.conbination;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@NoArgsConstructor
@Tag(name = "大屏查询参数")
public class QueryPageParams {

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码")
    @Schema(description = "当前页码", required = true)
    Integer page;

    @NotNull(message = "每页条数不能为空")
    @Range(min = 1, max = 99999, message = "每页条数")
    @Schema(description = "每页条数", required = true)
    Integer limit;

    @Schema(description = "排序字段")
    String orderByColumn;

    @Schema(description = "排序字符 asc desc")
    String isAsc;

    @Schema(description = "列表table里面的搜索")
    String searchText;



}
