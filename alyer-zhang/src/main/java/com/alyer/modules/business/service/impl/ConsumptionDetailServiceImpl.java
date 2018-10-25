package com.alyer.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.Query;

import com.alyer.modules.business.dao.ConsumptionDetailDao;
import com.alyer.modules.business.entity.ConsumptionDetailEntity;
import com.alyer.modules.business.service.ConsumptionDetailService;


@Service("consumptionDetailService")
public class ConsumptionDetailServiceImpl extends ServiceImpl<ConsumptionDetailDao, ConsumptionDetailEntity> implements ConsumptionDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConsumptionDetailEntity> page = this.selectPage(
                new Query<ConsumptionDetailEntity>(params).getPage(),
                new EntityWrapper<ConsumptionDetailEntity>()
        );

        return new PageUtils(page);
    }

}
