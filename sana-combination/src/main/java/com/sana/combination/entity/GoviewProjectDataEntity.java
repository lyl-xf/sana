package com.sana.combination.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goview_project_data")
public class GoviewProjectDataEntity extends BaseEntity {

    private Long projectId;

    private String content;

}
