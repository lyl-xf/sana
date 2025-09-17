package com.sana.system.controller;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.convert.SysDictTypeConvert;
import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.result.SysDictTypeResult;
import com.sana.system.entity.save.SysDictTypeSave;
import com.sana.system.entity.update.SysDictTypeUpdate;
import com.sana.system.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LON
 * @create 2025/8/30
 */
@RestController
@RequestMapping("/sys/dict")
@Tag(name = "字典类型")
public class SysDictTypeController {


    @Resource
    private SysDictTypeService sysDictTypeService;


    @GetMapping("/list")
    @Operation(summary = "字典列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<SysDictTypeResult>> getList() {
        // 通过ID从服务中获取字典类型实体
        List<SysDictTypeEntity> entity = sysDictTypeService.getList();
        // 将实体转换为SysDictTypeResult对象，然后封装到Result对象中返回
        return SanaResult.ok(SysDictTypeConvert.INSTANCE.convertList(entity));
    }


    @PostMapping("/save")
    @Operation(summary = "新增")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult<String> save(@RequestBody @Valid SysDictTypeSave saveVO) {
        sysDictTypeService.save(saveVO);
        return SanaResult.ok();
    }


    /**
     * @param uodateVO
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> update(@RequestBody @Valid SysDictTypeUpdate uodateVO) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(uodateVO);
        sysDictTypeService.updateById(entity);
        return SanaResult.ok();
    }


    @GetMapping("/deletDicType")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<String> delete(@RequestParam("id") Long id) {
        // 调用服务层方法，执行删除操作
        sysDictTypeService.removeDictType(id);
        return SanaResult.ok();
    }


}
