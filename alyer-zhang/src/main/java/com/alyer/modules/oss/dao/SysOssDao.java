package com.alyer.modules.oss.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.oss.entity.SysOssEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2017-03-25 12:13:26
 */
@Mapper
public interface SysOssDao extends BaseMapper<SysOssEntity> {
	
}
