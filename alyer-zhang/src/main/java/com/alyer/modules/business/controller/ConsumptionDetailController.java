package com.alyer.modules.business.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alyer.modules.business.entity.ConsumptionDetailEntity;
import com.alyer.modules.business.service.ConsumptionDetailService;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.R;



/**
 * 消费明细
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-16 16:54:14
 */
@RestController
@RequestMapping("business/consumptiondetail")
public class ConsumptionDetailController {
    @Autowired
    private ConsumptionDetailService consumptionDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:consumptiondetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = consumptionDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{detailId}")
    @RequiresPermissions("business:consumptiondetail:info")
    public R info(@PathVariable("detailId") Long detailId){
			ConsumptionDetailEntity consumptionDetail = consumptionDetailService.selectById(detailId);

        return R.ok().put("consumptionDetail", consumptionDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:consumptiondetail:save")
    public R save(@RequestBody ConsumptionDetailEntity consumptionDetail){
			consumptionDetailService.insert(consumptionDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:consumptiondetail:update")
    public R update(@RequestBody ConsumptionDetailEntity consumptionDetail){
			consumptionDetailService.updateById(consumptionDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:consumptiondetail:delete")
    public R delete(@RequestBody Long[] detailIds){
			consumptionDetailService.deleteBatchIds(Arrays.asList(detailIds));

        return R.ok();
    }

}
