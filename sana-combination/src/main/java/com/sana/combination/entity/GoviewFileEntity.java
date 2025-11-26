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
@TableName("goview_file")
public class GoviewFileEntity {

    private String id;

    private String fileName;

    private String fileurl;

    private Long fileSize;

    private String fileSuffix;

    /**
     * 虚拟路径
     */
    private String virtualKey;

    /**
     * 相对路径
     */
    private String relativePath;

    /**
     * 绝对路径
     */
    private String absolutePath;

    private String createTime;
}
