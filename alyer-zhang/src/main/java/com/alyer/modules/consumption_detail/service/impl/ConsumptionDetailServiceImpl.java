package com.alyer.modules.consumption_detail.service.impl;

import com.alyer.common.utils.Constant;
import com.alyer.common.utils.DateUtils;
import com.alyer.modules.consume_type.entity.ConsumeTypeEntity;
import com.alyer.modules.consume_type.service.ConsumeTypeService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.Query;

import com.alyer.modules.consumption_detail.dao.ConsumptionDetailDao;
import com.alyer.modules.consumption_detail.entity.ConsumptionDetailEntity;
import com.alyer.modules.consumption_detail.service.ConsumptionDetailService;


@Service("consumptionDetailService")
public class ConsumptionDetailServiceImpl extends ServiceImpl<ConsumptionDetailDao, ConsumptionDetailEntity> implements ConsumptionDetailService {

    @Autowired
    private ConsumeTypeService consumeTypeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long createUserId = (Long)params.get("createUserId");
        Page<ConsumptionDetailEntity> page = this.selectPage(
                new Query<ConsumptionDetailEntity>(params).getPage(),
                new EntityWrapper<ConsumptionDetailEntity>()
                        .eq(createUserId != null,"create_user_id", createUserId)
        );

        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> trend(Long userId) {
        // 获取消费类型
        final List<ConsumeTypeEntity> consumeTypes = consumeTypeService.selectList(new EntityWrapper<>());
        // 获取消费明细，如果不是超级管理员，获取自己创建的数据
        final Map<String, Object> params = new HashMap<>();
        if (userId != Constant.SUPER_ADMIN) {
            params.put("createUserId", userId);
        }
        final List<ConsumptionDetailEntity> consumptionDetails = this.baseMapper.queryList(params);
        final Map<String, BigDecimal> tempMap = new HashMap<>();
        for (ConsumptionDetailEntity consumptionDetail: consumptionDetails) {
            if (Objects.isNull(tempMap.get(consumptionDetail.getType()))) {
                tempMap.put(consumptionDetail.getType(), consumptionDetail.getTotal());
            } else {
                tempMap.put(consumptionDetail.getType(), tempMap.get(consumptionDetail.getType()).add(consumptionDetail.getTotal()));
            }

            final String currentDate = DateUtils.format(consumptionDetail.getConsumeDate(), "yyyy/MM/dd");
            final String mapKey = currentDate + ":" + consumptionDetail.getType();
            if (Objects.isNull(tempMap.get(mapKey))) {
                tempMap.put(mapKey, consumptionDetail.getTotal());
            } else {
                tempMap.put(mapKey, tempMap.get(mapKey).add(consumptionDetail.getTotal()));
            }
        }
        // 当前日期
        Date currentDate = new Date();
        // 当前日期之前的是个日期
        final List<String> tenDates = new ArrayList<>();
        for (int day = -6; day <= 0; day++) {
            tenDates.add(DateUtils.format(DateUtils.addDateDays(currentDate, day), "yyyy/MM/dd"));
        }
        // 类型名称
        final List<String> consumeTypeNames = new ArrayList<>();
        // 折线图数据
        final List<Map<String, Object>> details = new ArrayList<>();
        // 饼状图数据
        final List<Map<String, Object>> datum = new ArrayList<>();
        for (ConsumeTypeEntity consumeType: consumeTypes) {
            consumeTypeNames.add(consumeType.getName());
            // 收集折线图数据
            final List<BigDecimal> amounts = new ArrayList<>();
            for (String consumeDate: tenDates) {
                final String mapKey = consumeDate + ":" + consumeType.getType();
                BigDecimal total = tempMap.get(mapKey);
                if (Objects.isNull(total)) {
                    total = new BigDecimal(0);
                }
                amounts.add(total);
            }
            final Map<String, Object> detail = new HashMap<>();
            detail.put("name", consumeType.getName());
            detail.put("data", amounts);
            detail.put("type", "line");
            detail.put("stack", "总量");
            details.add(detail);

            // 收集饼状图数据
            final Map<String, Object> data = new HashMap<>();
            data.put("value", Objects.isNull(tempMap.get(consumeType.getType())) ? 0 : tempMap.get(consumeType.getType()));
            data.put("name", consumeType.getName());
            datum.add(data);
        }
        final Map<String, Object> result = new HashMap<>();
        result.put("consumeTypeNames", consumeTypeNames);
        result.put("dates", tenDates);
        result.put("details", details);
        result.put("datum", datum);
        return result;
    }
}
