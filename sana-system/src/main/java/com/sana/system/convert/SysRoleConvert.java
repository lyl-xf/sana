package com.sana.system.convert;

import com.sana.system.entity.SysRoleEntity;
import com.sana.system.entity.result.SysRoleResult;
import com.sana.system.entity.save.SysRoleSave;
import com.sana.system.entity.update.SysRoleUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleResult convert(SysRoleEntity entity);

    SysRoleEntity convert(SysRoleResult vo);

    SysRoleEntity convert(SysRoleSave saveVO);

    List<SysRoleResult> convertList(List<SysRoleEntity> entityList);

    SysRoleEntity convert(SysRoleUpdate updateVO);
}
