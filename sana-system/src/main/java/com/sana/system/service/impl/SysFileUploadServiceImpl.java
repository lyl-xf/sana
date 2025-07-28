package com.sana.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.sana.base.file.service.FileService;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.system.entity.result.SysFileUploadResult;
import com.sana.system.service.SysFileUploadService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author LON
 * @create 2025/7/28 11:51
 */
@Slf4j
@Service
public class SysFileUploadServiceImpl implements SysFileUploadService {

    @Resource
    private FileService fileService;


    @Override
    public SysFileUploadResult upload(MultipartFile file) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(DateUtil.format(new Date(), "yyyyMMdd"));
            stringBuffer.append("/");
            stringBuffer.append(FileNameUtil.getPrefix(file.getOriginalFilename()));
            stringBuffer.append("_");
            stringBuffer.append(DateUtil.timeToSecond(DateUtil.formatTime(new Date())));
            stringBuffer.append(".");
            stringBuffer.append(FileNameUtil.getSuffix(file.getOriginalFilename()));
            // 上传文件
            String url = fileService.upload(file.getBytes(), stringBuffer.toString());
            SysFileUploadResult vo = new SysFileUploadResult();
            vo.setSrc(url);
            vo.setSize(file.getSize());
            vo.setFileName(file.getOriginalFilename());
            vo.setMsg("minio");
            return vo;
        }catch (Exception e){
            log.error("上传文件失败", e);
            throw new SanaException("上传文件失败");
        }
    }
}
