package com.alyer.modules.oss.service;

import com.baomidou.mybatisplus.service.IService;
import com.alyer.common.utils.PageUtils;
import com.alyer.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
