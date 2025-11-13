package com.sana.rules.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.rules.entity.vo.query.RulesItemNodeQuery;
import com.sana.rules.entity.vo.result.RulesItemNodeResult;
import com.sana.rules.entity.vo.save.RulesItemNodeSave;
import com.sana.rules.service.RulesItemNodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LON
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/rules/rulesItemNode")
@Tag(name = "规则节点")
@ApiSupport(author = "LON")
public class RulesItemNodeController {

    @Resource
    private RulesItemNodeService rulesItemNodeService;


    @PostMapping("/saveAndUpdate")
    @Operation(summary = "规则信息设置")
    // todo 默认去除修改规则的日志，如果有需要则放开，不过需要注意数据的长度
    /*@OptLog(type = OperateTypeEnum.INSERT)*/
    @SaCheckPermission("rules:rules:updateAndSave")
    public SanaResult saveRulesItemNodeSave(@RequestBody @Valid RulesItemNodeSave saveRulesItemNodeSaveVO) {
        return rulesItemNodeService.SaveAndUpdate(saveRulesItemNodeSaveVO);
    }
    @PostMapping("/getRulesItem")
    @Operation(summary = "获取规则详情")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<RulesItemNodeResult> getRulesItemDetail(@RequestBody @Valid RulesItemNodeQuery queryVO) {
        RulesItemNodeResult rulesItemNodeResult = rulesItemNodeService.GetRulesItemDetail(queryVO);
        return SanaResult.ok(rulesItemNodeResult);
    }


}
