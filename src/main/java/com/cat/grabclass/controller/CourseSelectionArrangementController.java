package com.cat.grabclass.controller;

import java.util.Arrays;
import java.util.Map;

import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.R;
import com.cat.grabclass.entity.CourseSelectionArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cat.grabclass.service.CourseSelectionArrangementService;



/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 */
@CrossOrigin("*")
@RestController
@RequestMapping("grabclass/courseselectionarrangement")
public class CourseSelectionArrangementController {
    @Autowired
    private CourseSelectionArrangementService courseSelectionArrangementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseSelectionArrangementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CourseSelectionArrangement courseSelectionArrangement = courseSelectionArrangementService.getById(id);

        return R.ok().put("courseSelectionArrangement", courseSelectionArrangement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CourseSelectionArrangement courseSelectionArrangement){
		courseSelectionArrangementService.save(courseSelectionArrangement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CourseSelectionArrangement courseSelectionArrangement){
		courseSelectionArrangementService.updateById(courseSelectionArrangement);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		courseSelectionArrangementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/startScheduling")
    public R startScheduling(@RequestParam Long id){
        courseSelectionArrangementService.StartScheduling(id);
        return R.ok();
    }


}
