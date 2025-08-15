package com.sana.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.usercache.UserContextUtil;
import com.sana.system.convert.SysUserConvert;
import com.sana.system.dao.SysUserDao;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysUserCopyResult;
import com.sana.system.entity.query.SysUserQuery;
import com.sana.system.entity.result.SysUserPasswordResult;
import com.sana.system.entity.result.SysUserResult;
import com.sana.system.entity.save.SysUserSave;
import com.sana.system.entity.update.SysUserPasswordUpdate;
import com.sana.system.entity.update.SysUserUpdate;
import com.sana.system.service.SysUserOrgService;
import com.sana.system.service.SysUserRoleService;
import com.sana.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserOrgService sysUserOrgService;

    @Value("${sana.resetPassword}")
    private String resetPassword;

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
    public void updateMySelf(SysUserUpdate sysUserUpdate) {
        SysUserEntity sysUserEntity = baseMapper.getUserName(sysUserUpdate.getUsername());
        sysUserEntity.setSignature(sysUserUpdate.getSignature());
        sysUserEntity.setRealName(sysUserUpdate.getRealName());
        sysUserEntity.setGender(sysUserUpdate.getGender());
        baseMapper.updateById(sysUserEntity);
    }

    @Override
    public String updateMySelfPassword(SysUserPasswordUpdate sysUserPasswordUpdate) {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        SysUserEntity userEntity =  baseMapper.getUserName(user.getUsername());

        if (!BCrypt.checkpw( sysUserPasswordUpdate.getPassword(),user.getPassword())) {
            throw new SanaException("账号密码不正确");
        }
        userEntity.setPassword(BCrypt.hashpw(sysUserPasswordUpdate.getNewPassword()));
        baseMapper.updateById(userEntity);

        return null;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserOrgRole(SysUserSave vo) {
        try {
            //初始加密密码
            vo.setPassword(vo.getPassword());
            //实体转换
            SysUserEntity user = SysUserConvert.INSTANCE.convert(vo);
            user.setPassword(BCrypt.hashpw(vo.getPassword()));
            //保存用户
            baseMapper.insert(user);
            //保存角色
            List<Long> userRole = vo.getGroup();
            sysUserRoleService.saveUserRole(user.getId(),userRole);
            //保存组织
            List<Long> userOrg = vo.getDept();
            sysUserOrgService.saveUserOrg(user.getId(),userOrg);

        }catch (Exception e){
            log.info("保存用户、维护用户组织、角色出现异常，请排查",e);
            throw new SanaException("保存用户、维护用户组织、角色出现异常，请排查");
        }
    }

    @Override
    public void resetPassword(SysUserPasswordResult vo) {
        SysUserEntity sysUserEntity = baseMapper.selectById(vo.getId());
        sysUserEntity.setPassword(BCrypt.hashpw(resetPassword));
        // 修改密码
        baseMapper.updateById(sysUserEntity);
    }

    @Override
    public void updateByUserId(SysUserUpdate vo) {
        try {
            //实体转换
            SysUserEntity user = SysUserConvert.INSTANCE.convert(vo);
            //修改用户
            baseMapper.updateById(user);
            //修改角色
            List<Long> userRole = vo.getGroup();
            sysUserRoleService.deleteByUserIdList(user.getId());
            sysUserRoleService.saveUserRole(user.getId(),userRole);
            //修改组织
            List<Long> userOrg = vo.getDept();
            sysUserOrgService.deleteByUserIdList(user.getId());
            sysUserOrgService.saveUserOrg(user.getId(),userOrg);

        }catch (Exception e){
            log.info("保存用户、维护用户组织、角色出现异常，请排查",e);
            throw new SanaException("保存用户、维护用户组织、角色出现异常，请排查");
        }
    }

    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        baseMapper.deleteBatchIds(idList);

        // 删除用户角色关系
        sysUserRoleService.deleteByUserIdList(idList);

        // 删除用户组织关系
        sysUserOrgService.deleteByUserIdList(idList);
    }
}
