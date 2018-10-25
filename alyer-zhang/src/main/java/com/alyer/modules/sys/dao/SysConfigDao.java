package com.alyer.modules.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2016年12月4日 下午6:46:16
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);
	
}
