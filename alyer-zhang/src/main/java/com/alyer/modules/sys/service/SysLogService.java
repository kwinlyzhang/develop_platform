package com.alyer.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
