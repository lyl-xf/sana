package com.sana.rules.dao;


import com.sana.rules.entity.vo.result.JobInfoResult;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author LON
 * @data 2025/9/27
 */
@Mapper
public interface SchedulerDao {

    JobInfoResult getJobData(String logsClassName);
}
