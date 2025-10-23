package com.sana.abutment.mqtt.servecontrol;

import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.syshandle.page.SanaPage;
import jakarta.annotation.Resource;
import org.dromara.mica.mqtt.core.server.model.ClientInfo;
import org.dromara.mica.mqtt.core.server.model.Subscribe;
import org.dromara.mica.mqtt.spring.server.MqttServerTemplate;
import org.springframework.stereotype.Component;
import org.tio.utils.page.Page;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21
 */
@Component
public class MqttControlServe {
    @Resource
    private MqttServerTemplate server;


    /**
     * 关闭客户端连接
     * @param clientId
     * @return
     */
    public void close(String clientId) {
        try {
            server.close(clientId);
        }catch (Exception e){
            e.printStackTrace();
            new SanaException("关闭客户端连接失败");
        }


    }
    /**
     * 获取客户端的订阅信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public SanaPage<ClientInfo> getClients(Integer pageIndex, Integer pageSize) {
        Page<ClientInfo> page = server.getClients(pageIndex, pageSize);
        //将Page<ClientInfo> 转为SanaPage<ClientInfo>
        SanaPage<ClientInfo> sanaPage = new SanaPage<>(page.getList(), page.getTotalRow(), page.getPageNumber(), page.getTotalRow());
        return sanaPage;
        //return
    }
    /**
     * 获取客户端的信息
     * @param clientId
     * @return
     */
    public ClientInfo getClientInfo(String clientId) {
        return server.getClientInfo(clientId);
    }

    /**
     * 获取客户端的订阅信息
     * @param clientId
     * @return
     */
    public List<Subscribe> getSubscriptions(String clientId) {
        return server.getSubscriptions(clientId);
    }
}

