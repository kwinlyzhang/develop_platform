package com.alyer.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
