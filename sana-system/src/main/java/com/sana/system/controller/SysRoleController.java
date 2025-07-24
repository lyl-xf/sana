package com.sana.system.controller;

import com.sana.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LON
 * @create 2025/7/12
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;


}
