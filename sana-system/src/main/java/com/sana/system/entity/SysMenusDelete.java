package com.sana.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @auther LON
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "菜单删除ids")
public class SysMenusDelete {

    private List<Long> ids;

}
