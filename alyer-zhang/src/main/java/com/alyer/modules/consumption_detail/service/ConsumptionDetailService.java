package com.alyer.modules.consumption_detail.service;

import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.consumption_detail.entity.ConsumptionDetailEntity;

import java.util.Map;

/**
 * 消费明细
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-30 09:41:51
 */
public interface ConsumptionDetailService extends IService<ConsumptionDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Map<String, Object> trend(Long userId);
}

