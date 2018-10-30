package com.alyer.modules.consume_type.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alyer.modules.consume_type.entity.ConsumeTypeEntity;
import com.alyer.modules.consume_type.service.ConsumeTypeService;
import com.alyer.common.utils.PageUtils;
import com.alyer.common.utils.R;



/**
 * 消费类型
 *
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-30 09:33:57
 */
@RestController
@RequestMapping("consume_type/consumetype")
public class ConsumeTypeController {
    @Autowired
    private ConsumeTypeService consumeTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("consume_type:consumetype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = consumeTypeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有类型
     * @return
     */
    @RequestMapping("/list/all")
    public R consumeTypeList() {
        List<ConsumeTypeEntity> list = consumeTypeService.selectList(new EntityWrapper<>());
        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("consume_type:consumetype:info")
    public R info(@PathVariable("id") Long id){
			ConsumeTypeEntity consumeType = consumeTypeService.selectById(id);

        return R.ok().put("consumeType", consumeType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("consume_type:consumetype:save")
    public R save(@RequestBody ConsumeTypeEntity consumeType){
			consumeTypeService.insert(consumeType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("consume_type:consumetype:update")
    public R update(@RequestBody ConsumeTypeEntity consumeType){
			consumeTypeService.updateById(consumeType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("consume_type:consumetype:delete")
    public R delete(@RequestBody Long[] ids){
			consumeTypeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
