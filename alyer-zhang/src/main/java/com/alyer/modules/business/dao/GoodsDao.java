package com.alyer.modules.business.dao;

import com.alyer.modules.business.entity.GoodsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品管理
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-16 15:52:35
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsEntity> {
	
}
