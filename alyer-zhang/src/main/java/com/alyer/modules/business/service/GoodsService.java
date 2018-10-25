package com.alyer.modules.business.service;

import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.business.entity.GoodsEntity;

import java.util.Map;

/**
 * 商品管理
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-16 15:52:35
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

