package com.alyer.modules.business.service;

import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.business.entity.ConsumptionDetailEntity;

import java.util.Map;

/**
 * 消费明细
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-16 16:54:14
 */
public interface ConsumptionDetailService extends IService<ConsumptionDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

