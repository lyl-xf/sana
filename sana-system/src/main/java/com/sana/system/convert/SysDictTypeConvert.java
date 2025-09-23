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

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    SysDictTypeResult convert(SysDictTypeEntity entity);

    SysDictTypeEntity convert(SysDictTypeResult vo);

    SysDictTypeEntity convert(SysDictTypeSave vo);

    SysDictTypeEntity convert(SysDictTypeUpdate vo);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    List<SysDictTypeResult> convertList(List<SysDictTypeEntity> list);


}
