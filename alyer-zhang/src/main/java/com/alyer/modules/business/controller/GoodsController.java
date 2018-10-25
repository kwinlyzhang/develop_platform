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

import com.alyer.modules.business.entity.GoodsEntity;
import com.alyer.modules.business.service.GoodsService;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.R;



/**
 * 商品管理
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-16 15:52:35
 */
@RestController
@RequestMapping("generator/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:goods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{goodsId}")
    @RequiresPermissions("generator:goods:info")
    public R info(@PathVariable("goodsId") Long goodsId){
			GoodsEntity goods = goodsService.selectById(goodsId);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:goods:save")
    public R save(@RequestBody GoodsEntity goods){
			goodsService.insert(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:goods:update")
    public R update(@RequestBody GoodsEntity goods){
			goodsService.updateById(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:goods:delete")
    public R delete(@RequestBody Long[] goodsIds){
			goodsService.deleteBatchIds(Arrays.asList(goodsIds));

        return R.ok();
    }

}
