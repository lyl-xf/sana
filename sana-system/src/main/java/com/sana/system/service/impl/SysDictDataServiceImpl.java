package com.sana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.convert.SysDictDataConvert;
import com.sana.system.dao.SysDictDataDao;
import com.sana.system.entity.SysDictDataEntity;
import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.query.SysDictDataQuery;
import com.sana.system.entity.result.SysDictDataResult;
import com.sana.system.entity.save.SysDictDataSave;
import com.sana.system.entity.update.SysDictDataUpdate;
import com.sana.system.service.SysDictDataService;
import com.sana.system.service.SysDictTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LON
 * @create 2025/9/21 14:45
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private SysDictTypeService sysDictTypeService;

    @Override
    public SanaPage<SysDictDataResult> page(SysDictDataQuery query) {
        IPage<SysDictDataResult> page = baseMapper.getLists(getPage(query),query);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public void saveDicData(SysDictDataSave saveVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(saveVO);

        SysDictTypeEntity sysDictTypeEntity = sysDictTypeService.getById(entity.getDic());
        String redisKey = CacheKeyBuilder.dictKey(sysDictTypeEntity.getCode());

        Map<String, String>  dictData= (HashMap)redisUtils.get(redisKey);
        if(dictData!=null){
            dictData.put(entity.getKey(),entity.getName());
            redisUtils.del(redisKey);
            redisUtils.set(redisKey, dictData);
        }else {
            Map<String, String> dicData = new HashMap<>();
            //查询出来数据，然后添加字段
            dicData.put(entity.getKey(),entity.getName());
            //存入
            redisUtils.set(redisKey, dicData);
        }
        baseMapper.insert(entity);
    }

    @Override
    public void updateDicData(SysDictDataUpdate updateVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(updateVO);

        updateById(entity);

        SysDictTypeEntity sysDictTypeEntity = sysDictTypeService.getById(entity.getDic());
        String redisKey = CacheKeyBuilder.dictKey(sysDictTypeEntity.getCode());
        Map<String, String>  dictData= (HashMap)redisUtils.get(redisKey);
        if(dictData!=null){
            if(dictData.containsKey(updateVO.getKey())){
                dictData.remove(updateVO.getKey());
                dictData.put(entity.getKey(),entity.getName());
                redisUtils.del(redisKey);
                redisUtils.set(redisKey, dictData);
            }else {
                dictData.put(entity.getKey(),entity.getName());
                redisUtils.del(redisKey);
                redisUtils.set(redisKey, dictData);
            }
        }else {
            Map<String, String> dicData = new HashMap<>();
            //查询出来数据，然后添加字段
            dicData.put(entity.getKey(),entity.getName());
            //存入
            redisUtils.set(redisKey, dicData);
        }

    }

    @Override
    public void deleteDicData(Long id) {

        SysDictDataEntity sysDictDataEntity = baseMapper.selectById(id);
        SysDictTypeEntity sysDictTypeEntity = sysDictTypeService.getById(id);

        String redisKey = CacheKeyBuilder.dictKey(sysDictTypeEntity.getCode());
        Map<String, String>  dictData= (HashMap)redisUtils.get(redisKey);
        if(dictData!=null){
            if(dictData.containsKey(sysDictDataEntity.getKey())){
                dictData.remove(sysDictDataEntity.getKey());
                redisUtils.del(redisKey);
                redisUtils.set(redisKey, dictData);
            }
        }
        baseMapper.deleteById(id);
    }
}