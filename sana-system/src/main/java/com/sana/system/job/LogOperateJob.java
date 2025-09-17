package com.sana.system.job;

import cn.hutool.core.date.DateUtil;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.syshandle.operatelog.OptLogEntity;
import com.sana.system.dao.SysLogOperateDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LON
 * @create 2025/8/30
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class LogOperateJob implements Job {


    @Value("${sana.logging-enabled:false}")
    private boolean loggingEnabled;

    @Value("${sana.logging-save-type:tdengine}")
    private String logSaveType;


    @Value("${sana.log-save-speed:20}")
    private int dataNum;

    @Resource
    private RedisUtils redisCacheOps;

    @Resource
    private SysLogOperateDao sysLogOperateDao;

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
                "操作日志定时触发存储。"
        );*/

        if (loggingEnabled) {
            if(logSaveType.equals("tdengine")){
                try {
                    String key = CacheKeyBuilder.logKey();
                    OptLogEntity logEntity = (OptLogEntity) redisCacheOps.rightPop(key);
                    if(logEntity != null){
                        StringBuffer sql = new StringBuffer();
                        sql.append("INSERT INTO");
                        // 每次插入10条
                        for (int i = 0; i < dataNum -1; i++) {
                            OptLogEntity logs = null;
                            if(logEntity!=null){
                                logs = logEntity;
                                logEntity = null;
                            }else {
                                logs = (OptLogEntity) redisCacheOps.rightPop(key);
                            }
                            if (logs != null) {
                                sql.append(" user");
                                sql.append(logs.getUserId());
                                sql.append(" USING sys_log_operate TAGS(");
                                sql.append(logs.getUserId());
                                sql.append(") values(");
                                sql.append("'");
                                sql.append(LocalDateTime.now());
                                sql.append("','");
                                sql.append(logs.getModule());
                                sql.append("','");
                                sql.append(logs.getName());
                                sql.append("','");
                                sql.append(logs.getReqUri());
                                sql.append("','");
                                sql.append(logs.getReqMethod());
                                sql.append("','");
                                sql.append(logs.getReqParams());
                                sql.append("','");
                                sql.append(logs.getIp());
                                sql.append("','");
                                sql.append(logs.getUserAgent());
                                sql.append("',");
                                sql.append(logs.getOperateType());
                                sql.append(",");
                                sql.append("1");
                                sql.append(",'");
                                sql.append(logs.getRealName());
                                sql.append("')");
                            }
                        }
                        if(sql.length()>20){
                            sysLogOperateDao.insertTd(sql.toString());
                        }
                    }else {
                        return;
                    }
                } catch (Exception e) {
                    log.error("日志存储失败", e);
                }
            }else {
                String key = CacheKeyBuilder.logKey();
                //检查是否有这个key的数据，如果有，则循环取出，如果没有，则停止本次的循环
                OptLogEntity logEntitys = (OptLogEntity) redisCacheOps.rightPop(key);
                if(logEntitys!=null){
                    List<OptLogEntity> optLogEntityList = new ArrayList<>();
                    optLogEntityList.add(logEntitys);
                    for (int i = 0; i < dataNum -1; i++) {
                        OptLogEntity forLogEntitys = (OptLogEntity) redisCacheOps.rightPop(key);
                        if(forLogEntitys!=null){
                            optLogEntityList.add(forLogEntitys);
                        }else {
                            sysLogOperateDao.saveBatch(optLogEntityList);
                            return;
                        }
                    }
                }
            }
        }
    }

}
