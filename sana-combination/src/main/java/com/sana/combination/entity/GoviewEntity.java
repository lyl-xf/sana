package com.sana.combination.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goview_project")
public class GoviewEntity extends BaseEntity {

    private String projectName;

    private Integer state;

    private String indexImage;

    private String remarks;

}
