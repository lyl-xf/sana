package com.sana.rules.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.QuartzEnum;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.rules.entity.JobInfoEntity;
import com.sana.rules.entity.vo.result.JobInfoResult;
import com.sana.rules.service.SchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LON
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/rules/quartz")
@Tag(name = "任务处理")
@ApiSupport(author = "LON")
public class SchedulerController {


    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/createJob")
    @Operation(summary = "新增定时任务")
    public SanaResult createJob(@RequestBody JobInfoEntity jobInfo) {
        return SanaResult.ok( schedulerService.addCronJob(jobInfo.getJobName(), jobInfo.getCron(),jobInfo.getJobGroup(),jobInfo.getTriggerGroup(),  QuartzEnum.RULES_CLASS_NAME.getValue()));
    }

    @PostMapping("/deleteJob")
    @Operation(summary = "删除定时任务")
    public SanaResult deleteJob(@RequestBody JobInfoEntity jobInfo) {
        schedulerService.deleteCronJob(jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfo.getTriggerName(), jobInfo.getTriggerGroup());
        return SanaResult.ok();
    }

    @PostMapping("/executeImmediately")
    @Operation(summary = "立即执行定时任务")
    public SanaResult executeImmediately(@RequestBody JobInfoEntity jobInfo) {
        schedulerService.executeImmediately(jobInfo.getJobName(),jobInfo.getJobGroup(),jobInfo.getTriggerGroup(), QuartzEnum.RULES_CLASS_NAME.getValue());
        return SanaResult.ok();
    }


    @PostMapping("/updateLogJob")
    @Operation(summary = "新增日志存储定时任务")
    public SanaResult createLogJob(@RequestBody JobInfoEntity jobInfo) {
        return SanaResult.ok( schedulerService.createLogJob(jobInfo.getJobName(), jobInfo.getCron(),jobInfo.getJobGroup(),jobInfo.getTriggerGroup(),  QuartzEnum.LOGS_CLASS_NAME.getValue()));
    }

    @PostMapping("/updateDeviceJob")
    @Operation(summary = "新增设备数据存储定时任务")
    public SanaResult createDeviceJob(@RequestBody JobInfoEntity jobInfo) {
        return SanaResult.ok( schedulerService.createDeviceJob(jobInfo.getJobName(), jobInfo.getCron(),jobInfo.getJobGroup(),jobInfo.getTriggerGroup(),  QuartzEnum.DEVICE_CLASS_NAME.getValue()));
    }


    @GetMapping("/getJobData")
    @Operation(summary = "设备数据存储定时任务")
    public SanaResult<List<JobInfoResult>> getJobData() {
        return SanaResult.ok( schedulerService.getJobData());
    }

}
