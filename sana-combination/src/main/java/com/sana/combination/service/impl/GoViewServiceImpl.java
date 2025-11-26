package com.sana.combination.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.conbination.ConbinationResult;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.combination.dao.GoViewServiceDao;
import com.sana.combination.entity.GoviewEntity;

import com.sana.combination.entity.GoviewFileEntity;
import com.sana.combination.entity.GoviewProjectDataEntity;
import com.sana.combination.entity.query.GoviewProjectQuery;
import com.sana.combination.entity.query.GoviewQuery;
import com.sana.combination.entity.result.GoviewProjectResult;
import com.sana.combination.entity.save.GoviewProjectDataSave;
import com.sana.combination.service.GoViewService;
import com.sana.combination.service.GoviewProjectDataService;
import com.sana.system.entity.result.SysFileUploadResult;
import com.sana.system.entity.result.SysRoleResult;
import com.sana.system.service.SysFileUploadService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LON
 * @create 2025/11/25 13:13
 */
@Slf4j
@Service
public class GoViewServiceImpl extends BaseServiceImpl<GoViewServiceDao,GoviewEntity>  implements GoViewService {

    @Resource
    private GoViewServiceDao goViewServiceDao;

    @Resource
    private SysFileUploadService sysFileUploadService;

    @Resource
    private GoviewProjectDataService goviewProjectDataService;



    @Override
    public SanaPage<GoviewEntity> goviewPage(GoviewProjectQuery query) {

        IPage<GoviewEntity> page = baseMapper.goviewPage(getPage(query), query,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }



    @Override
    public ConbinationResult<List<GoviewEntity>> getPage(GoviewQuery query) {
        int count = goViewServiceDao.countData();
        int pageCount = 0;
        if(count>0){
            pageCount = (int) Math.ceil((double) count / query.getLimit());
        }
        List<GoviewEntity> list =goViewServiceDao.getPage(query.getPage()-1,query.getLimit());
        // todo 如果需要权限，则使用下面这个：
        //List<GoviewEntity> list =goViewServiceDao.getPage(query, true);
        return ConbinationResult.ok(pageCount, list);
    }

    @Override
    public SanaResult<GoviewEntity>  goviewEntity(GoviewEntity goviewEntity) {
        goviewEntity.setState(-1);
        baseMapper.insert(goviewEntity);
        return SanaResult.ok(goviewEntity);
    }

    @Override
    public SanaResult publish(GoviewEntity goviewEntity) {
        GoviewEntity goviewEntity1 = baseMapper.selectById(goviewEntity.getId());
        goviewEntity1.setState(goviewEntity.getState());
        baseMapper.updateById(goviewEntity1);
        return SanaResult.ok();
    }

    @Override
    public SanaResult upload(MultipartFile file) {
        try {
            SysFileUploadResult sysFileUploadResult =  sysFileUploadService.upload(file);

            GoviewFileEntity goviewFileEntity = new GoviewFileEntity();
            goviewFileEntity.setFileSize(sysFileUploadResult.getSize());
            goviewFileEntity.setFileName(sysFileUploadResult.getFileName());
            goviewFileEntity.setFileurl(sysFileUploadResult.getSrc());
            //goviewFileEntity.setFileSuffix();
            goviewFileEntity.setRelativePath(sysFileUploadResult.getSrc());
            goviewFileEntity.setVirtualKey("oss");
            goviewFileEntity.setCreateTime(DateUtil.formatLocalDateTime(LocalDateTime.now()));
            String timestamp = System.currentTimeMillis() + "";
            goviewFileEntity.setId(timestamp);
            goViewServiceDao.uploadFile(goviewFileEntity);
            return SanaResult.ok(goviewFileEntity);
        }catch (Exception e){
            log.error("上传失败",e);
            return SanaResult.error();
        }
    }

    @Override
    public SanaResult editProject(GoviewEntity goviewEntity) {
        baseMapper.updateById(goviewEntity);
        return SanaResult.ok();
    }

    @Override
    public SanaResult saveData(Long projectId,String content) {
        //检索项目信息
        GoviewEntity goviewEntity = baseMapper.selectById(projectId);
        if(goviewEntity==null) {
            return SanaResult.error(500,"没有该项目ID");
        }
        //更新项目数据
        GoviewProjectDataEntity goviewProjectData= goviewProjectDataService.getGoviewProjectDataEntity(goviewEntity.getId());
        if(goviewProjectData!=null){
            GoviewProjectDataEntity goviewProjectDataEntity = new GoviewProjectDataEntity();
            goviewProjectDataEntity.setId(goviewProjectData.getId());
            goviewProjectDataEntity.setContent(content);
            goviewProjectDataService.updateByIdEntity(goviewProjectDataEntity);
            return SanaResult.ok();
        }else {
            GoviewProjectDataEntity goviewProjectDataEntity = new GoviewProjectDataEntity();
            goviewProjectDataEntity.setContent(content);
            goviewProjectDataEntity.setProjectId(projectId);
            goviewProjectDataService.saveEntity(goviewProjectDataEntity);
            return SanaResult.ok();
        }
    }

    @Override
    public SanaResult<GoviewProjectResult> getData(Long projectId) {

        GoviewEntity goviewEntity = baseMapper.selectById(projectId);
        GoviewProjectDataEntity goviewProjectData= goviewProjectDataService.getGoviewProjectDataEntity(projectId);
        if(goviewEntity!=null && goviewProjectData!=null){
            GoviewProjectResult goviewProjectResult = new GoviewProjectResult();
            goviewProjectResult.setProjectName(goviewEntity.getProjectName());
            goviewProjectResult.setState(goviewEntity.getState());
            goviewProjectResult.setCreateTime(goviewEntity.getCreateTime());
            goviewProjectResult.setCreateUserId(goviewEntity.getCreator().toString());
            goviewProjectResult.setIsDelete(goviewEntity.getDeleted());
            goviewProjectResult.setIndexImage(goviewEntity.getIndexImage());
            goviewProjectResult.setRemarks(goviewEntity.getRemarks());
            goviewProjectResult.setContent(goviewProjectData.getContent());
            return SanaResult.ok(goviewProjectResult);
        }else {
            return SanaResult.ok(null);
        }
    }

    @Override
    public SanaResult deleteProject(Long ids) {
        baseMapper.deleteById(ids);
        goviewProjectDataService.deleteByProjectId(ids);
        return SanaResult.ok();
    }



}
