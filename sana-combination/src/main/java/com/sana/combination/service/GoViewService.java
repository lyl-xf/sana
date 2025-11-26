package com.sana.combination.service;

import com.sana.base.syshandle.conbination.ConbinationResult;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.combination.entity.GoviewEntity;
import com.sana.combination.entity.GoviewProjectDataEntity;
import com.sana.combination.entity.query.GoviewProjectQuery;
import com.sana.combination.entity.query.GoviewQuery;
import com.sana.combination.entity.result.GoviewProjectResult;
import com.sana.combination.entity.save.GoviewProjectDataSave;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author LON
 * @create 2025/11/25 13:08
 */
public interface GoViewService {


    ConbinationResult<List<GoviewEntity>> getPage(@Valid GoviewQuery query);

    SanaResult<GoviewEntity>  goviewEntity(GoviewEntity goviewEntity);

    SanaResult publish(GoviewEntity goviewEntity);

    SanaResult upload(MultipartFile file);

    SanaResult editProject(GoviewEntity goviewEntity);

    SanaResult saveData(Long projectId,String content);

    SanaResult<GoviewProjectResult> getData(Long projectId);

    SanaResult deleteProject(Long ids);

    SanaPage<GoviewEntity> goviewPage(@Valid GoviewProjectQuery query);
}
