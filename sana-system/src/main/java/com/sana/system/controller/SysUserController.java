package com.sana.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.base.syshandle.usercache.UserContextUtil;
import com.sana.system.entity.query.SysUserQuery;
import com.sana.system.entity.result.SysUserPasswordResult;
import com.sana.system.entity.result.SysUserResult;
import com.sana.system.entity.save.SysUserSave;
import com.sana.system.entity.update.SysUserPasswordUpdate;
import com.sana.system.entity.update.SysUserUpdate;
import com.sana.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
@ApiSupport(author = "LON")
@Tag(name = "用户管理")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 用户数据分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("sys:user:page")
    public SanaResult<SanaPage<SysUserResult>> page(@ParameterObject @Valid SysUserQuery query) {
        SanaPage<SysUserResult> sanaPage = sysUserService.page(query);
        return SanaResult.ok(sanaPage);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    public SanaResult<String> save(@RequestBody @Valid SysUserSave vo) {
        sysUserService.saveUserOrgRole(vo);
        return SanaResult.ok();
    }

    @PostMapping("/resetPassword")
    @Operation(summary = "重置为默认密码")
    public SanaResult resetPassword(@RequestBody @Valid SysUserPasswordResult vo) {
        sysUserService.resetPassword(vo);
        return SanaResult.ok();
    }




    @PostMapping("/update")
    @Operation(summary = "修改")
    public SanaResult<String> update(@RequestBody @Valid SysUserUpdate vo) {
        sysUserService.updateByUserId(vo);
        return SanaResult.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除")
    public SanaResult<String> delete(@RequestBody List<Long> idList) {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        if (idList.contains(user.getId())) {
            return SanaResult.error("不能删除当前登录用户");
        }
        sysUserService.delete(idList);
        return SanaResult.ok();
    }

    @PostMapping("/updateMySelf")
    @Operation(summary = "修改个人用户信息")
    public SanaResult updateMySelf(@RequestBody @Valid SysUserUpdate sysUserUpdate) {
        sysUserService.updateMySelf(sysUserUpdate);
        return SanaResult.ok();
    }

    @PostMapping("/updateMySelfPassword")
    @Operation(summary = "修改个人密码")
    public SanaResult<String> password(@RequestBody @Valid SysUserPasswordUpdate sysUserPasswordUpdate) {
        // 修改密码
        String result = sysUserService.updateMySelfPassword(sysUserPasswordUpdate);
        return SanaResult.ok(result);
    }

}
