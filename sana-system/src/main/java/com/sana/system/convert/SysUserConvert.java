package com.sana.system.convert;

import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysUserResult;
import com.sana.system.entity.save.SysUserSave;
import com.sana.system.entity.update.SysUserUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    SysUserEntity convert(SysUserSave save);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    SysUserEntity convert(SysUserUpdate update);

}