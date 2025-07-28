package com.sana.system.service;

import com.sana.system.entity.result.SysFileUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LON
 * @create 2025/7/28 11:49
 */
public interface SysFileUploadService {

    SysFileUploadResult upload(MultipartFile file);

}
