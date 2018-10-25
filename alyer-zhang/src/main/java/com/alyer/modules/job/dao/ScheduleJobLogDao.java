package com.alyer.modules.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.alyer.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author zhangshengbo 669434283@qq.com
 * @since 1.2.0 2016-11-28
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
	
}
