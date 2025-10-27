package com.sana.abutment.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.abutment.entity.push.DirectivesSend;
import com.sana.abutment.mqtt.servepublish.ClientMessagePublish;
import com.sana.abutment.mqtt.servepublish.MessagePublish;
import com.sana.abutment.service.ProtocolsMqttService;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LON
 * @create 2025/9/21
 */
@RestController
@RequestMapping("/abutment/directives")
@Tag(name = "指令推送")
@ApiSupport(author = "LON")
public class DirectivesController {

    @Resource
    private MessagePublish messagePublish;

    @Resource
    private ClientMessagePublish clientMessagePublish;

    @Resource
    private ProtocolsMqttService protocolsMqttService;

    @PostMapping("/pushData")
    @Operation(summary = "指令发送")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public SanaResult<DirectivesSend> pushData(@RequestBody DirectivesSend directivesSend) {
        //检查设备是代理连接的，还是连接本地mqtt-broker，
        //boolean isProxy = false;
        // todo 从数据库中查询设备信息是否是代理连接的
        Integer mttpType = protocolsMqttService.getMttpType(directivesSend.getDeviceId());
        //1为本地mqtt-broker
        if(mttpType==1){
            messagePublish.publishTopic(directivesSend.getTopic(), directivesSend.getPushData());
        }else {
            clientMessagePublish.publish(directivesSend.getTopic(), directivesSend.getPushData());
        }

        return SanaResult.ok(directivesSend);
    }
}
