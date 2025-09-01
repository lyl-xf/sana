package com.sana.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/8/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_type")
public class SysDictTypeEntity extends BaseEntity {
    /**
     * 字典类型
     */
    private String code;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
}
