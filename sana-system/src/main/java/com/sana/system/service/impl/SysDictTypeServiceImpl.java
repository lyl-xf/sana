package com.sana.system.service.impl;

import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.system.convert.SysDictTypeConvert;
import com.sana.system.dao.SysDictDataDao;
import com.sana.system.dao.SysDictTypeDao;
import com.sana.system.entity.SysDictTypeEntity;
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
    @Override
    public void save(SysDictTypeSave saveVO) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(entity);
    }
    @Override
    public void delete(List<Long> idList) {

        removeByIds(idList);
    }

    @Override
    public List<SysDictTypeEntity> getList() {

        return baseMapper.getList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeDictType(Long id) {
        //先清空字典子项数据
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("dic", id);
        sysDictDataDao.deleteByMap(columnMap);
        //在清空字典类型
        baseMapper.deleteById(id);
    }


}
