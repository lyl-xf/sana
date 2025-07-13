package com.sana.base.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sana.base.syshandle.enums.FieldConsEnum;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 18:01
 */
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        // 创建者
        strictInsertFill(metaObject, FieldConsEnum.CREATOR.getFieldName(), Long.class, 1L);
        // 创建者账号名称
        strictInsertFill(metaObject, FieldConsEnum.CREATOR_NAME.getFieldName(), String.class, "[admin:+1]");
        // 创建时间
        strictInsertFill(metaObject, FieldConsEnum.CREATE_TIME.getFieldName(), Date.class, date);
        // 删除标识
        strictInsertFill(metaObject, FieldConsEnum.DELETED.getFieldName(), Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        // 更新者
        strictUpdateFill(metaObject, FieldConsEnum.UPDATER.getFieldName(), Long.class, 1L);
        // 更新者账号名称
        strictUpdateFill(metaObject, FieldConsEnum.UPDATER_NAME.getFieldName(), String.class, "[admin:+1]");
        // 更新时间
        strictUpdateFill(metaObject, FieldConsEnum.UPDATE_TIME.getFieldName(), Date.class, date);

    }
}