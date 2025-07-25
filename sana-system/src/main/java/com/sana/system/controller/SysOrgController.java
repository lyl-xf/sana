package com.sana.system.controller;

import com.sana.system.service.SysOrgService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther LON
 * @date 2025/7/12
 */
@RestController
@RequestMapping("/sys/org")
@Tag(name = "机构管理")
public class SysOrgController {
    @Resource
    private SysOrgService sysOrgService;

}
