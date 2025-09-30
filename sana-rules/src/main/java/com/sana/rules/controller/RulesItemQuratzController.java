package com.sana.rules.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.rules.service.RulesItemQuratzService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LON
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/rules/rulesItemQuratz")
@Tag(name = "规则实例")
@ApiSupport(author = "LON")
public class RulesItemQuratzController {
    @Resource
    private RulesItemQuratzService rulesItemQuratzService;


}
