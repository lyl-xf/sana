package com.sana.system.convert;

import com.sana.system.entity.SysDictDataEntity;
import com.sana.system.entity.result.SysDictDataResult;
import com.sana.system.entity.save.SysDictDataSave;
import com.sana.system.entity.update.SysDictDataUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConvert {

    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    SysDictDataResult convert(SysDictDataEntity entity);

    SysDictDataEntity convert(SysDictDataResult vo);

    SysDictDataEntity convert(SysDictDataSave vo);

    SysDictDataEntity convert(SysDictDataUpdate vo);


    List<SysDictDataResult> convertList(List<SysDictDataEntity> list);

}