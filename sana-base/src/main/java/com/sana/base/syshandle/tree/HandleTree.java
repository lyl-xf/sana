package com.sana.base.syshandle.tree;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
public class HandleTree<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Schema(description = "id")
    private Long id;
    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private Long pid;
    /**
     * 子节点列表
     */
    private List<T> children = new ArrayList<>();
}
