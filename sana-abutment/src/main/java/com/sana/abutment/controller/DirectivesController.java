package com.sana.abutment.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.abutment.entity.push.DirectivesSend;
import com.sana.abutment.servepublish.MessagePublish;
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

    @PostMapping("/pushData")
    @Operation(summary = "指令发送")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public SanaResult<DirectivesSend> pushData(@RequestBody DirectivesSend directivesSend) {
        messagePublish.publishTopic(directivesSend.getTopic(), directivesSend.getPushData());
        return SanaResult.ok(directivesSend);
    }
}
