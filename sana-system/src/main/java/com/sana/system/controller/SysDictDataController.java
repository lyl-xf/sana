package com.sana.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.convert.SysDictDataConvert;
import com.sana.system.entity.SysDictDataEntity;
import com.sana.system.entity.query.SysDictDataQuery;
import com.sana.system.entity.result.SysDictDataResult;
import com.sana.system.entity.save.SysDictDataSave;
import com.sana.system.entity.update.SysDictDataUpdate;
import com.sana.system.service.SysDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author LON
 * @create 2025/8/30
 */
@RestController
@RequestMapping("/sys/dic")
@Tag(name = "字典数据")
public class SysDictDataController {
    @Resource
    private SysDictDataService sysDictDataService;


    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)

    public SanaResult<SanaPage<SysDictDataResult>> page(@ParameterObject SysDictDataQuery query) {
        // 调用服务进行分页查询
        SanaPage<SysDictDataResult> sanaPage = sysDictDataService.page(query);
        // 返回查询结果
        return SanaResult.ok(sanaPage);
    }


    @PostMapping("/saveDicData")
    @Operation(summary = "新增")
    @OptLog(type = OperateTypeEnum.INSERT)
    @SaCheckPermission("sys:dic:save")
    public SanaResult<String> save(@RequestBody SysDictDataSave saveVO) {
        sysDictDataService.saveDicData(saveVO);
        return SanaResult.ok();
    }
    /**
     * 修改字典数据
     */
    /**
     * @param uodateVO
     * @return
     */
    @PostMapping("/updateDicData")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @SaCheckPermission("sys:dic:update")
    public SanaResult<String> update(@RequestBody @Valid SysDictDataUpdate uodateVO) {
        sysDictDataService.updateDicData(uodateVO);
        return SanaResult.ok();
    }

    @GetMapping("/deletDicData")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @SaCheckPermission("sys:dic:delete")
    public SanaResult<String> delete(@RequestParam("id") Long id) {
        // 调用服务层方法，执行删除操作
        sysDictDataService.deleteDicData(id);
        return SanaResult.ok();
    }
}
