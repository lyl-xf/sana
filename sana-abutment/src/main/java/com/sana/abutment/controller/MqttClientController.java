package com.sana.abutment.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.abutment.entity.query.MqttClientQuery;
import com.sana.abutment.mqtt.servecontrol.MqttControlServe;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.dromara.mica.mqtt.core.server.model.ClientInfo;
import org.dromara.mica.mqtt.core.server.model.Subscribe;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21
 */
@RestController
@RequestMapping("/abutment/mqttClient")
@Tag(name = "指令推送")
@ApiSupport(author = "LON")
public class MqttClientController {

    @Resource
    private MqttControlServe mqttControlServe;

    @GetMapping("/close")
    @Operation(summary = "关闭客户端连接")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public SanaResult pushData(@RequestParam String clientId) {
        mqttControlServe.close(clientId);
        return SanaResult.ok();
    }

    @GetMapping("/getClients")
    @Operation(summary = "查询客户端信息")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public SanaResult<SanaPage<ClientInfo>> pushData(@ParameterObject @Valid MqttClientQuery query) {
        SanaPage<ClientInfo> ClientInfoPage = mqttControlServe.getClients(query.getPage(),  query.getPageSize());
        return SanaResult.ok(ClientInfoPage);
    }

    @GetMapping("/getSubscriptions")
    @Operation(summary = "获取客户端的订阅信息")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public SanaResult<List<Subscribe>> getSubscriptions(@RequestParam String clientId) {
        List<Subscribe> subscribeList= mqttControlServe.getSubscriptions(clientId);
        return SanaResult.ok(subscribeList);
    }
}
