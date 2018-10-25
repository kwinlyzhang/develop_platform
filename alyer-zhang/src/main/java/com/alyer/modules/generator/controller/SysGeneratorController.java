package com.alyer.modules.generator.controller;

import com.alibaba.fastjson.JSON;
import com.alyer.modules.generator.service.SysGeneratorService;
import com.alyer.modules.generator.utils.PageUtils;
import com.alyer.modules.generator.utils.Query;
import com.alyer.modules.generator.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

	private static final Logger logger = LoggerFactory.getLogger(SysGeneratorController.class);

	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
		int total = sysGeneratorService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public R code(String tables, HttpServletResponse response){
		final byte[] data = sysGeneratorService.generatorCode(tables.split(","));

		final File tmpFile = new File("D:/code/alyer.zip");
		final File tmpPath = tmpFile.getParentFile();
		if (tmpPath.exists() == false) {
			tmpPath.mkdirs();
		}
		try (OutputStream outputStream = new FileOutputStream(tmpFile);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(data));
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);) {
			IOUtils.copy(bufferedInputStream, bufferedOutputStream);
		} catch (final IOException ex) {
			logger.error("生成代码发生错误！", ex);
		}
		return R.ok().put("path", tmpFile.getPath());
	}
}
