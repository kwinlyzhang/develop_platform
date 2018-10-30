package com.alyer.modules.consume_type.service;

import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.consume_type.entity.ConsumeTypeEntity;

import java.util.Map;

/**
 * 消费类型
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-30 09:33:57
 */
public interface ConsumeTypeService extends IService<ConsumeTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

