package com.sana.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.usercache.UserContextUtil;
import com.sana.system.dao.SysLogOperateDao;
import com.sana.system.entity.SysLogOperateEntity;
import com.sana.system.entity.query.SysLogOperateQuery;
import com.sana.system.entity.query.SysLogSysOperateQuery;
import com.sana.system.entity.result.SysLogOperateResult;
import com.sana.system.service.SysLogOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @auther LON
 * @date 2024/3/30 17:14
 */
@Slf4j
@Service
public class SysLogOperateServiceImpl extends BaseServiceImpl<SysLogOperateDao, SysLogOperateEntity> implements SysLogOperateService {


    @Override
    public SanaPage<SysLogOperateResult> page(SysLogOperateQuery query) {
        MyUserDetails user = UserContextUtil.getCurrentUserInfo();
        query.setUserId(user.getId());
        IPage<SysLogOperateResult> page = baseMapper.getListsTd(getPage(query),query);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public SanaPage<SysLogOperateResult> sysPage(SysLogSysOperateQuery query) {
        IPage<SysLogOperateResult> page = null;
        //系统日志
        if(query.getOperateType()== OperateTypeEnum.SYSTEM.getValue()){
             page = baseMapper.getSysLogListsTd(getPage(query),query);
        //设备日志
        } else if (query.getOperateType()== OperateTypeEnum.DEVICE.getValue()) {
             page = baseMapper.getdeviceLogListsTd(getPage(query),query);
        //通讯日志
        }else if (query.getOperateType()== OperateTypeEnum.COMMUNICATION.getValue()){
             page = baseMapper.getCommunLogListsTd(getPage(query),query);
        }else {
            page = baseMapper.getSysListsTd(getPage(query),query);
        }

        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

}
