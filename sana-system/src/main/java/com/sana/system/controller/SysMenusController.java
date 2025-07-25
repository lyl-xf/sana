package com.sana.system.controller;

import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.base.syshandle.usercache.UserContextUtil;
import com.sana.system.entity.result.SysMenusNavResult;
import com.sana.system.service.SysMenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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


}
