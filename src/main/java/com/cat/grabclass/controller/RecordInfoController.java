package com.cat.grabclass.controller;

import java.util.Arrays;
import java.util.Map;

import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.R;
import com.cat.grabclass.entity.RecordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cat.grabclass.service.RecordInfoService;



/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 * @date 2023-03-28 12:29:39
 */

@CrossOrigin("*")
@RestController
@RequestMapping("grabclass/recordinfo")
public class RecordInfoController {
    @Autowired
    private RecordInfoService recordInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recordInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		RecordInfo recordInfo = recordInfoService.getById(id);

        return R.ok().put("recordInfo", recordInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RecordInfo recordInfo){
		recordInfoService.save(recordInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RecordInfo recordInfo){
		recordInfoService.updateById(recordInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		recordInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
