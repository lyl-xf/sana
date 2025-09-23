package com.sana.system.service.impl;

import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.system.convert.SysDictTypeConvert;
import com.sana.system.dao.SysDictDataDao;
import com.sana.system.dao.SysDictTypeDao;
import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.result.SysDictTypeResult;
import com.sana.system.entity.save.SysDictTypeSave;
import com.sana.system.service.SysDictTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LON
 * @create 2025/9/1 15:30
 */
@Slf4j
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
    @Resource
    private SysDictDataDao sysDictDataDao;
    @Resource
    private RedisUtils redisUtils;


    @Override
    public void save(SysDictTypeSave saveVO) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(saveVO);
        String redisKey = CacheKeyBuilder.dictKey(entity.getCode());
        redisUtils.set(redisKey,null);
        baseMapper.insert(entity);
    }
    @Override
    public void delete(List<Long> idList) {

        List<SysDictTypeEntity> sysDictTypeEntities = baseMapper.selectBatchIds(idList);
        for (SysDictTypeEntity sysDictTypeEntity : sysDictTypeEntities){
            String redisKey = CacheKeyBuilder.dictKey(sysDictTypeEntity.getCode());
            redisUtils.del(redisKey);
        }
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public List<SysDictTypeResult> getList() {

        List<SysDictTypeEntity> entity = baseMapper.getList();
        List<SysDictTypeResult> sysDictTypeResults = SysDictTypeConvert.INSTANCE.convertList(entity);
        return sysDictTypeResults;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeDictType(Long id) {
        //先清空字典子项数据
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("dic", id);
        sysDictDataDao.deleteByMap(columnMap);

        //删除字典类型缓存
        String redisKey = CacheKeyBuilder.dictKey(baseMapper.selectById(id).getCode());
        Object o = redisUtils.get(redisKey);
        // 删除验证码
        if (o != null) {
            redisUtils.delete(redisKey);
        }
        //在清空字典类型
        baseMapper.deleteById(id);

    }


}
