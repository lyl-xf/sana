package com.sana.rules.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.base.syshandle.entity.DeviceControl;
import com.sana.rules.entity.*;
import com.sana.rules.entity.vo.query.RulesItemPageQuery;
import com.sana.rules.entity.vo.result.RulesItemResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RulesItemDao extends BaseDao<RulesItemEntity> {
    //RulesItemResult getRules(@Param("queryVO") RulesItemQuery queryVO);


    IPage<RulesItemResult> getRulesPage(@Param("page") IPage<RulesItemEntity> page,@Param("model")  RulesItemPageQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    Integer getRulesItem(@Param("ruleId") Long id);


    void deleteRulesItemNode(@Param("ruleId") Long id);

    List<RulesListenDeviceIdEntity> getRulesListenDeviceIdList();

    List<RulesJobDeviceIdEntity> getRulesJobDeviceId();

    List<RulesJobPushDeviceEntity> getRulesJobPushDevice();

    List<Long> getDeviceItemIds();

    ArrayList<DeviceControl> getDeviceControlList(Long deviceItemId);

    List<Long> getDeviceModes();

    ArrayList<String> getDeviceModeList(Long deviceId);

    List<RulesActionInstancEntity> getActionMapData();

    List<RulesActionInstancEntity> getUnconditionalCache();

    void updateRulesItemById(@Param("id") Long id,@Param("asPath") String asPath);
}
