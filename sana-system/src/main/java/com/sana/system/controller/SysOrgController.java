package com.sana.system.controller;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.query.SysOrgQuery;
import com.sana.system.entity.result.SysOrgResult;
import com.sana.system.entity.save.SysOrgSave;
import com.sana.system.entity.update.SysOrgUpdate;
import com.sana.system.service.SysOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther LON
 * @date 2025/7/12
 */
@RestController
@RequestMapping("/sys/org")
@Tag(name = "机构管理")
public class SysOrgController {
    @Resource
    private SysOrgService sysOrgService;




    @GetMapping("/list")
    @Operation(summary = "列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<SysOrgResult>> list(@ParameterObject @Valid SysOrgQuery query) {
        List<SysOrgResult> list = sysOrgService.getList(query);

        return SanaResult.ok(list);
    }


    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult<String> save(@RequestBody @Valid SysOrgSave saveVO) {
        sysOrgService.saveOrg(saveVO);

        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> update(@RequestBody @Valid SysOrgUpdate updateVO) {
        sysOrgService.updateOrg(updateVO);

        return SanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<String> delete(@RequestParam("id") Long id) {
        sysOrgService.deleteOrg(id);

        return SanaResult.ok();
    }

}
