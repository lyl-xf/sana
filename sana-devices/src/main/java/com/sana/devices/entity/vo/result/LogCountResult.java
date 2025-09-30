package com.sana.devices.entity.vo.result;

import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
public class LogCountResult {

    //系统操作
    private long systemLog;
    //设备操作
    private long deviceActionLog;
    //设备告警
    private long deviceAlarmsLog;
    //解除告警
    private long deviceAlarm;
    //情景模式
    private long scenarioModeLog;

}
