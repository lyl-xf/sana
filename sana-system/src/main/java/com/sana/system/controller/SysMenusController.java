package com.sana.system.controller;

import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.base.syshandle.usercache.UserContextUtil;
import com.sana.system.entity.SysMenusDelete;
import com.sana.system.entity.SysMenusEntity;
import com.sana.system.entity.result.SysMenusNavResult;
import com.sana.system.entity.result.SysMenusResult;
import com.sana.system.entity.save.SysMenusSave;
import com.sana.system.entity.update.SysMenusUpdate;
import com.sana.system.service.SysMenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*@author LON
*@create 2025/7/12
*/

@RestController
@RequestMapping("/sys/menus")
@Tag(name = "菜单管理")
public class SysMenusController {

    @Resource
    private SysMenusService sysMenusService;

    @GetMapping("/nav")
    @Operation(summary = "菜单导航")
    public SanaResult<SysMenusNavResult> nav() {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        //获取菜单导航
        SysMenusNavResult navResult = sysMenusService.getNavMenuList(user);
        return SanaResult.ok(navResult);
    }

    @GetMapping("/list")
    @Operation(summary = "菜单列表")
    @Parameter(name = "type")
    public SanaResult<List<SysMenusResult>> list(Integer type) {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        List<SysMenusResult> menu = sysMenusService.getMenuList(user);
        return SanaResult.ok(menu);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    public SanaResult save(@RequestBody @Valid SysMenusSave saveVO) {
        SysMenusEntity sysMenusEntity = sysMenusService.saveMenu(saveVO);
        return SanaResult.ok(sysMenusEntity.getId());
    }


    @PostMapping("/update")
    @Operation(summary = "修改")
    public SanaResult<String> update(@RequestBody @Valid SysMenusUpdate uodateVO) {
        sysMenusService.updateByData(uodateVO);
        return SanaResult.ok();
    }


    @PostMapping("/delete")
    @Operation(summary = "删除")
    public SanaResult<String> delete(@RequestBody SysMenusDelete sysMenusDelete) {
        sysMenusService.delete(sysMenusDelete.getIds());
        return SanaResult.ok();
    }

    @GetMapping("/roleList")
    @Operation(summary = "角色菜单分配列表")
    public SanaResult<List<String>> roleMenusList(@RequestParam("roleId") Long roleId) {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        List<String> menu = sysMenusService.getRoleMenusList(user,roleId);
        return SanaResult.ok(menu);
    }

}
