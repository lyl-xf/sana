package com.sana.rules.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.rules.entity.vo.query.RulesItemPageQuery;
import com.sana.rules.entity.vo.result.RulesItemResult;
import com.sana.rules.entity.vo.save.RulesItemSave;
import com.sana.rules.entity.vo.update.RulesItemUpdate;
import com.sana.rules.service.RulesItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author LON
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/rules/rulesItem")
@Tag(name = "规则实例")
@ApiSupport(author = "LON")
public class RulesItemController {
    @Resource
    private RulesItemService rulesItemService;
    @GetMapping("/page")
    @Operation(summary = "规则实例分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<RulesItemResult>> getPage(@ParameterObject @Valid RulesItemPageQuery query) {
        SanaPage<RulesItemResult> sanaPage = rulesItemService.getRulesPage(query);
        return SanaResult.ok(sanaPage);
    }


    @PostMapping("/save")
    @Operation(summary = "规则实例新增")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveRulesItem(@RequestBody @Valid RulesItemSave saveVO) {
        rulesItemService.saveRulesItem(saveVO);
        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "规则实例修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult updateRulesItem(@RequestBody @Valid RulesItemUpdate updateVO) {
        rulesItemService.updateRulesItem(updateVO);
        return SanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "规则实例删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult deleteRulesItem(@RequestParam("id") Long id) {
        rulesItemService.deleteRulesItem(id);
        return SanaResult.ok();
    }

    /**
     * 该方法暂时弃用
     * @param
     * @return
     */
/*  @PostMapping("/updateAndSave")
    @Operation(summary = "新增/修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@SaCheckPermission("rules:rulesItem:save")
    public SanaResult updateAndSaveRulesItem(@RequestBody @Valid RulesItemUpdate updateVO) {
        rulesItemService.updateAndSaveRulesItem(updateVO);
        return SanaResult.ok();
    }*/




/*    @PostMapping("/getRules")
    @Operation(summary = "获取规则信息")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("rules:rulesItem:save")
    public SanaResult<RulesItemResult> getRules(@RequestBody @Valid RulesItemQuery queryVO) {
        return SanaResult.ok(rulesItemService.getRules(queryVO));
    }*/




/*    @PostMapping("/roleSeting")
    @Operation(summary = "规则信息设置")
    @OptLog(type = OperateTypeEnum.INSERT)
    //@SaCheckPermission("rules:rulesItem:save")
    public SanaResult saveRulesItemNodeSave(@RequestBody @Valid RulesItemNodeSave saveRulesItemNodeSaveVO) {
        rulesItemService.saveRulesItemNodeSave(saveRulesItemNodeSaveVO);
        return SanaResult.ok();
    }
    @PostMapping("/getRulesItem")
    @Operation(summary = "获取规则详情")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("rules:rulesItem:save")
    public SanaResult<RulesItemNodeQuert> getRulesItemDetail(@RequestBody @Valid RulesItemNodeQuery queryVO) {
        RulesItemNodeQuert rulesItemNodeQuert = rulesItemService.getRulesItemDetail(queryVO);
        if(rulesItemNodeQuert!=null){
            return SanaResult.ok(rulesItemNodeQuert);
        }else {
            return SanaResult.ok();
        }
    }
    */
}
