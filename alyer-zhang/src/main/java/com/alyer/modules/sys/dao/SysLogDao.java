package com.alyer.modules.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2017-03-08 10:40:56
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
