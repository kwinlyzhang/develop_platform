package com.alyer.modules.consumption_detail.dao;

import com.alyer.modules.consumption_detail.entity.ConsumptionDetailEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消费明细
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-30 09:41:51
 */
@Mapper
public interface ConsumptionDetailDao extends BaseMapper<ConsumptionDetailEntity> {
    List<ConsumptionDetailEntity> queryList(@Param("params") Map<String, Object> params);
}
