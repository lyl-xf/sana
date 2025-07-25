package com.sana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.convert.SysUserConvert;
import com.sana.system.dao.SysUserDao;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysUserCopyResult;
import com.sana.system.entity.query.SysUserQuery;
import com.sana.system.entity.result.SysUserResult;
import com.sana.system.entity.save.SysUserSave;
import com.sana.system.entity.update.SysUserPasswordUpdate;
import com.sana.system.entity.update.SysUserUpdate;
import com.sana.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Override
    public SanaPage<SysUserResult> page(SysUserQuery query) {
        IPage<SysUserCopyResult> page = baseMapper.getLists(getPage(query),query,true);

        List<SysUserResult> resultList = page.getRecords().stream()
                .map(dto -> {
                    SysUserResult result = new SysUserResult();
                    // 复制基本属性...
                    result.setId(dto.getId());
                    result.setUsername(dto.getUsername());
                    result.setRealName(dto.getRealName());
                    result.setAvatar(dto.getAvatar());
                    result.setEmail(dto.getEmail());
                    result.setMobile(dto.getMobile());
                    result.setGender(dto.getGender());
                    result.setStatus(dto.getStatus());
                    result.setSuperAdmin(dto.getSuperAdmin());
                    result.setCreateTime(dto.getCreateTime());
                    result.setPostIdList(StringUtils.isNotBlank(dto.getPostIdList())
                            ? Arrays.stream(dto.getPostIdList().split(","))
                            .map(Long::valueOf)
                            .collect(Collectors.toList())
                            : Collections.emptyList());
                    result.setRoleIdList(StringUtils.isNotBlank(dto.getRoleIdList())
                            ? Arrays.stream(dto.getRoleIdList().split(","))
                            .map(Long::valueOf)
                            .collect(Collectors.toList())
                            : Collections.emptyList());

                    return result;
                })
                .collect(Collectors.toList());
        return new SanaPage<>(resultList, page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public SysUserResult updateMySelf(SysUserUpdate sysUserUpdate) {
        return null;
    }

    @Override
    public String updateMySelfPassword(SysUserPasswordUpdate sysUserPasswordUpdate) {
        return "";
    }

    @Override
    public SysUserEntity getUserName(String username) {
        return baseMapper.getUserName(username);
    }

    @Override
    public void registerUser(SysUserSave vo) {
        //实体转换
        SysUserEntity user = SysUserConvert.INSTANCE.convert(vo);
        //保存用户
        baseMapper.insert(user);
    }
}
