package com.alyer.modules.consume_type.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.Query;

import com.alyer.modules.consume_type.dao.ConsumeTypeDao;
import com.alyer.modules.consume_type.entity.ConsumeTypeEntity;
import com.alyer.modules.consume_type.service.ConsumeTypeService;


@Service("consumeTypeService")
public class ConsumeTypeServiceImpl extends ServiceImpl<ConsumeTypeDao, ConsumeTypeEntity> implements ConsumeTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConsumeTypeEntity> page = this.selectPage(
                new Query<ConsumeTypeEntity>(params).getPage(),
                new EntityWrapper<ConsumeTypeEntity>()
        );

        return new PageUtils(page);
    }

}
