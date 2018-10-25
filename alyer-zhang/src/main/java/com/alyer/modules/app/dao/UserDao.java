package com.alyer.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2017-03-23 15:22:06
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
