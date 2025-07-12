package com.sana.base.framework.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sana.base.framework.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPageParams;


import java.util.Map;

/**
 * @author LON
 * @create 2025/7/12
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
    /**
     * 获取分页对象，方便Ipml层使用
     *
     * @param query 分页参数
     */
    protected IPage<T> getPage(SanaPageParams query) {
        Page<T> page = new Page<>(query.getPage(), query.getPageSize());
        return page;
    }


    /**
     * 获取分页对象，方便Ipml层使用
     *
     * @param query 分页参数
     */
    protected IPage<Map<String, Object>> getPageMap(SanaPageParams query) {
        Page<Map<String, Object>> page = new Page<>(query.getPage(), query.getPageSize());
        return page;
    }

}
