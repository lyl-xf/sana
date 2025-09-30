package com.sana.rules.service.impl;

import com.sana.base.scheduler.SchedulerUtils;
import com.sana.base.syshandle.enums.QuartzEnum;
import com.sana.rules.dao.SchedulerDao;
import com.sana.rules.entity.vo.result.JobInfoResult;
import com.sana.rules.service.SchedulerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LON
 * @data 2025/9/27
 */
@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Resource
    private SchedulerUtils schedulerUtils;

    @Resource
    private SchedulerDao schedulerDao;


    @Override
    public boolean addCronJob(String jobName, String cron, String jobGroup,String triggerGroup, String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobGroup,triggerGroup, jobClassName, QuartzEnum.TRIGGER_PRE.getValue());
    }

    @Override
    public void deleteCronJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        schedulerUtils.deleteCronJob(jobName,jobGroup,triggerName,triggerGroup);
    }


    @Override
    public void executeImmediately(String jobName,String jobGroup, String triggerGroup, String jobClassName) {
        schedulerUtils.executeImmediately(jobName,jobGroup,triggerGroup,jobClassName);
    }

    //新增日志定时任务
    @Override
    public boolean createLogJob(String jobName, String cron, String jobGroup,String triggerGroup,String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobGroup,triggerGroup, jobClassName, QuartzEnum.LOG_TRIGGER_PRE.getValue());
    }

    //新增日志定时任务
    @Override
    public boolean createDeviceJob(String jobName, String cron, String jobGroup,String triggerGroup,String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobGroup,triggerGroup, jobClassName, QuartzEnum.DEVICE_TRIGGER_PRE.getValue());
    }

    @Override
    public List<JobInfoResult> getJobData() {
        JobInfoResult LogOperateJob= schedulerDao.getJobData(QuartzEnum.LOGS_CLASS_NAME.getValue());
        LogOperateJob.setJobType(1);
        JobInfoResult DeviceDataJob = schedulerDao.getJobData(QuartzEnum.DEVICE_CLASS_NAME.getValue());
        DeviceDataJob.setJobType(2);
        return List.of(LogOperateJob,DeviceDataJob);
    }


}
