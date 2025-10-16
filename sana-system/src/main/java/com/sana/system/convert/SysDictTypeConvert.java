package com.sana.system.convert;

import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.result.SysDictTypeResult;
import com.sana.system.entity.save.SysDictTypeSave;
import com.sana.system.entity.update.SysDictTypeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author LON
*@create 2025/8/30
*/
@Mapper
public interface SysDictTypeConvert {



    SysDictTypeConvert INSTANCE = Mappers.getMapper(SysDictTypeConvert.class);


    SysDictTypeResult convert(SysDictTypeEntity entity);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    SysDictTypeEntity convert(SysDictTypeResult vo);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    SysDictTypeEntity convert(SysDictTypeSave vo);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    SysDictTypeEntity convert(SysDictTypeUpdate vo);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    List<SysDictTypeResult> convertList(List<SysDictTypeEntity> list);


}
