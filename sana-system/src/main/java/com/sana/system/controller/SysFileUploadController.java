package com.sana.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.sana.base.file.service.FileService;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.result.SysFileUploadResult;
import com.sana.system.service.SysFileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author LON
 * @create 2025/7/26
 */
@RestController
@RequestMapping("/sys/file")
@Tag(name = "文件上传")
public class SysFileUploadController {


    @Resource
    private SysFileUploadService sysFileUploadService;


    @PostMapping("/upload")
    @Operation(summary = "上传")
    public SanaResult<SysFileUploadResult> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return SanaResult.error("请选择需要上传的文件");
        }
        return SanaResult.ok(sysFileUploadService.upload(file));
    }
}
