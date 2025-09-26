package com.sana.abutment.servelistener;

import com.sana.abutment.dao.DeviceStatusDao;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.spring.server.event.MqttClientOfflineEvent;
import org.dromara.mica.mqtt.spring.server.event.MqttClientOnlineEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author LON
 * @create 2025/9/21
 */
@Slf4j
@Component
public class StatusListener {

    @Resource
    private DeviceStatusDao deviceStatusDao;

    @EventListener
    public void online(MqttClientOnlineEvent event) {

        if( event.getClientId() != null && event.getClientId().startsWith(GeneralPrefixEnum.TABLE_PREFIX.getValue())){
            log.info("设备上线：{}",event.getClientId());
            deviceStatusDao.updataStatus(Long.valueOf(event.getClientId().substring(2)),1);
        }
    }

    @EventListener
    public void offline(MqttClientOfflineEvent event) {
        if( event.getClientId() != null && event.getClientId().startsWith(GeneralPrefixEnum.TABLE_PREFIX.getValue())){
            log.info("设备下线：{}",event.getClientId());
            deviceStatusDao.updataStatus(Long.valueOf(event.getClientId().substring(2)),0);
        }
    }

}
