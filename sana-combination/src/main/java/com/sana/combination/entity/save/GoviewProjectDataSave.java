package com.sana.combination.entity.save;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 用户
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoviewProjectDataSave implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long projectId;

    private String content;

}
