package com.alyer.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author zhangshengbo 669434283@qq.com
 * @since 1.2.0 2016-11-28
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
