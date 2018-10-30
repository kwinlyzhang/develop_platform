package com.alyer.modules.consumption_detail.controller;

import java.math.BigDecimal;
import java.util.*;

import com.alyer.common.utils.Constant;
import com.alyer.modules.consume_type.entity.ConsumeTypeEntity;
import com.alyer.modules.consume_type.service.ConsumeTypeService;
import com.alyer.modules.sys.controller.AbstractController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alyer.modules.consumption_detail.entity.ConsumptionDetailEntity;
import com.alyer.modules.consumption_detail.service.ConsumptionDetailService;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.R;


/**
 * 消费明细
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-30 09:41:51
 */
@RestController
@RequestMapping("consumption_detail/consumptiondetail")
public class ConsumptionDetailController extends AbstractController {
    @Autowired
    private ConsumptionDetailService consumptionDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("consumption_detail:consumptiondetail:list")
    public R list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        if(getUserId() != Constant.SUPER_ADMIN){
            params.put("createUserId", getUserId());
        }
        PageUtils page = consumptionDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("consumption_detail:consumptiondetail:info")
    public R info(@PathVariable("id") Long id) {
        ConsumptionDetailEntity consumptionDetail = consumptionDetailService.selectById(id);

        return R.ok().put("consumptionDetail", consumptionDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("consumption_detail:consumptiondetail:save")
    public R save(@RequestBody ConsumptionDetailEntity consumptionDetail) {
        consumptionDetail.setTotal(consumptionDetail.getAmount().multiply(new BigDecimal(consumptionDetail.getNumber())));
        consumptionDetail.setCreateUserId(getUserId());
        consumptionDetail.setCreateDate(new Date());
        consumptionDetail.setUpdatedDate(new Date());
        consumptionDetailService.insert(consumptionDetail);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("consumption_detail:consumptiondetail:update")
    public R update(@RequestBody ConsumptionDetailEntity consumptionDetail) {
        consumptionDetail.setTotal(consumptionDetail.getAmount().multiply(new BigDecimal(consumptionDetail.getNumber())));
        consumptionDetail.setUpdatedDate(new Date());
        consumptionDetailService.updateById(consumptionDetail);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("consumption_detail:consumptiondetail:delete")
    public R delete(@RequestBody Long[] ids) {
        consumptionDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/trend")
    public R consumeTrend() {
        final Long userId = getUserId();
        final Map<String, Object> result = consumptionDetailService.trend(userId);

        return R.ok().put("result", result);
    }
}