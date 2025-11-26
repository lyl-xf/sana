package com.sana.combination.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.conbination.ConbinationResult;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.combination.entity.GoviewEntity;
import com.sana.combination.entity.GoviewProjectDataEntity;
import com.sana.combination.entity.query.GoviewProjectQuery;
import com.sana.combination.entity.query.GoviewQuery;
import com.sana.combination.entity.result.GoviewProjectResult;
import com.sana.combination.entity.save.GoviewProjectDataSave;
import com.sana.combination.service.GoViewService;
import com.sana.system.entity.query.SysRoleQuery;
import com.sana.system.entity.result.SysRoleResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@RestController
@RequestMapping("/api/goview")
@ApiSupport(author = "LON")
@Tag(name = "goview大屏")
public class GoViewController {

    @Resource
    private GoViewService goViewService;


    @GetMapping("/sys/getOssInfo")
    @Operation(summary = "获取oss地址")
    public SanaResult getOssInfo() {


        return SanaResult.ok();
    }

    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<GoviewEntity>> page(@ParameterObject @Valid GoviewProjectQuery query) {
        SanaPage<GoviewEntity> rolePage = goViewService.goviewPage(query);
        return SanaResult.ok(rolePage);
    }



    @GetMapping("/project/list")
    @Operation(summary = "大屏设计器列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public ConbinationResult<List<GoviewEntity>> getPage(@ParameterObject @Valid GoviewQuery query) {
        return goViewService.getPage(query);
    }


    @PostMapping("/project/create")
    @Operation(summary = "添加新的大屏项目")
    @OptLog(type = OperateTypeEnum.INSERT)
    @SaCheckPermission("combination:settings:create")
    public SanaResult<GoviewEntity> createProject(@RequestBody GoviewEntity goviewEntity) {
        return goViewService.goviewEntity(goviewEntity);
    }

    //发布,取消发布
    @PutMapping("/project/publish")
    @Operation(summary = "发布新的大屏项目")
    @OptLog(type = OperateTypeEnum.INSERT)
    @SaCheckPermission("combination:settings:publish")
    public SanaResult publish(@RequestBody GoviewEntity goviewEntity) {
        return goViewService.publish(goviewEntity);
    }

    //上传文件
    @PostMapping("/project/upload")
    @Operation(summary = "上传文件")
    @SaCheckPermission("combination:settings:upload")
    //@OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult upload(@RequestParam("object") MultipartFile object) {

        return goViewService.upload(object);
    }


    @PostMapping("/project/save/data")
    @Operation(summary = "保存项目数据")
    @SaCheckPermission("combination:settings:savedata")
    //@OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveData(@RequestParam("projectId") Long projectId,@RequestParam("content") String content) {

        return goViewService.saveData(projectId,content);
    }

    //编辑（project/edit）
    @PostMapping("/project/edit")
    @Operation(summary = "修改保存大屏项目")
    @OptLog(type = OperateTypeEnum.INSERT)
    @SaCheckPermission("combination:settings:edit")
    public SanaResult editProject(@RequestBody GoviewEntity goviewEntity) {
        return goViewService.editProject(goviewEntity);
    }

    //预览,获取数据（/project/getData）

    @GetMapping("/project/getData")
    @Operation(summary = "获取项目存储数据")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<GoviewProjectResult> getData(@RequestParam Long projectId) {
        return goViewService.getData(projectId);
    }


    //删除项目
    @DeleteMapping("/project/delete")
    @Operation(summary = "删除大屏项目")
    @OptLog(type = OperateTypeEnum.DELETE)
    @SaCheckPermission("combination:settings:delete")
    public SanaResult deleteProject(@RequestParam Long ids) {
        return goViewService.deleteProject(ids);
    }
}
