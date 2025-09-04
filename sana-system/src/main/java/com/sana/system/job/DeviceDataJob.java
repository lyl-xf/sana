package com.sana.system.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LON
 * @create 2025/9/4 17:56
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class DeviceDataJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

/*        log.info("\n\t----------------------------------------------------------------------------\n\t" +
                        "=========================定时任务调度，执行业务逻辑====================:\n\t" +
                        "jobName:{}\n\t" +
                        "jobGroup:{}\n\t" +
                        "triggerName:{}\n\t" +
                        "triggerGroup:{}\n\t" +
                        "上次触发时间:{}\n\t" +
                        "本次触发时间:{}\n\t" +
                        "下次触发时间:{}\n\t" +
                        "调度时间:{}\n\t" +
                        "本次出发内容:{}\n\t" +
                        "----------------------------------------------------------------------------",
                context.getJobDetail().getKey().getName(),
                context.getJobDetail().getKey().getGroup(),
                context.getTrigger().getKey().getName(),
                context.getTrigger().getKey().getGroup(),
                DateUtil.formatDateTime(context.getPreviousFireTime()),
                DateUtil.formatDateTime(context.getFireTime()),
                DateUtil.formatDateTime(context.getNextFireTime()),
                DateUtil.formatDateTime(context.getScheduledFireTime()),
                "设备数据定时触发存储。"
        );*/

    }
}
