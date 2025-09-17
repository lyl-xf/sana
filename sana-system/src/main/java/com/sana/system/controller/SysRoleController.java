package com.sana.system.controller;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.query.SysRoleQuery;
import com.sana.system.entity.result.SysRoleResult;
import com.sana.system.entity.save.SysRoleSave;
import com.sana.system.entity.update.SysRoleDataScopeUpdate;
import com.sana.system.entity.update.SysRoleMenusUpdate;
import com.sana.system.entity.update.SysRoleUpdate;
import com.sana.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;


    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<SysRoleResult>> page(@ParameterObject @Valid SysRoleQuery query) {
        SanaPage<SysRoleResult> rolePage = sysRoleService.pageRole(query);
        return SanaResult.ok(rolePage);
    }

    @GetMapping("/list")
    @Operation(summary = "列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<SysRoleResult>> list() {
        List<SysRoleResult> list = sysRoleService.getListRole(new SysRoleQuery());
        return SanaResult.ok(list);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult<String> save(@RequestBody @Valid SysRoleSave saveVO) {
        sysRoleService.saveRole(saveVO);
        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> update(@RequestBody @Valid SysRoleUpdate updateVo) {
        sysRoleService.updateRole(updateVo);
        return SanaResult.ok();
    }



    @PostMapping("/roleLinkMenus")
    @Operation(summary = "角色绑定菜单、数据权限、首页模块权限")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> roleLinkMenus(@RequestBody @Valid SysRoleMenusUpdate sysRoleMenusUpdate) {
        sysRoleService.roleLinkMenus(sysRoleMenusUpdate);
        return SanaResult.ok();
    }


    @PutMapping("/data-scope")
    @Operation(summary = "数据权限")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> dataScope(@RequestBody @Valid SysRoleDataScopeUpdate updateVO) {
        sysRoleService.dataScope(updateVO);
        return SanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<String> delete(@RequestParam("id") List<Long> idList) {
        sysRoleService.deleteRole(idList);

        return SanaResult.ok();
    }

}
