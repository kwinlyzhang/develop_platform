package com.alyer.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author zhangshengbo 669434283@qq.com
 * @since 3.1.0 2018-02-10
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
