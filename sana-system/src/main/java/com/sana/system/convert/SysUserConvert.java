package com.sana.system.convert;


import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysUserResult;
import com.sana.system.entity.save.SysUserSave;
import com.sana.system.entity.update.SysUserUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserEntity convert(SysUserSave save);


}
