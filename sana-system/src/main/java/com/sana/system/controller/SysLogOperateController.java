package com.sana.system.controller;

import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.query.SysLogOperateQuery;
import com.sana.system.entity.query.SysLogSysOperateQuery;
import com.sana.system.entity.result.SysLogOperateResult;
import com.sana.system.service.SysLogOperateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LON
 * @create 2025/8/10
 */
@RestController
@RequestMapping("/sys/log/operate")
@Tag(name = "操作日志")
public class SysLogOperateController {


    @Resource
    private SysLogOperateService sysLogOperateService;


    @GetMapping("/page")
    @Operation(summary = "个人操作日志分页")
    public SanaResult<SanaPage<SysLogOperateResult>> page(@ParameterObject @Valid SysLogOperateQuery query) {
        SanaPage<SysLogOperateResult> lanaPage = sysLogOperateService.page(query);
        return SanaResult.ok(lanaPage);
    }



    @GetMapping("/sysPage")
    @Operation(summary = "系统操作日志分页")
    public SanaResult<SanaPage<SysLogOperateResult>> sysPage(@ParameterObject @Valid SysLogSysOperateQuery query) {
        if(query.getOperateType()!=null){
            return SanaResult.ok(sysLogOperateService.sysPage(query));
        }else {
            query.setOperateType(11);
            return SanaResult.ok(sysLogOperateService.sysPage(query));
        }


    }

    @GetMapping("/sysRulesPage")
    @Operation(summary = "情景模式日志分页查询")
    public SanaResult<SanaPage<SysLogOperateResult>> sysRulesPage(@ParameterObject @Valid SysLogSysOperateQuery query) {
        if(query.getOperateType()!=null){
            return SanaResult.ok(sysLogOperateService.sysPage(query));
        }else {
            query.setOperateType(9);
            return SanaResult.ok(sysLogOperateService.sysPage(query));
        }


    }


    @GetMapping("/sysRulesWarnings")
    @Operation(summary = "预警消息日志分页查询")
    public SanaResult<SanaPage<SysLogOperateResult>> sysRulesWarnings(@ParameterObject @Valid SysLogSysOperateQuery query) {
        if(query.getOperateType()!=null){
            return SanaResult.ok(sysLogOperateService.sysPage(query));
        }else {
            query.setOperateType(5);
            return SanaResult.ok(sysLogOperateService.sysPage(query));
        }
    }

}
