package com.cat.grabclass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.entity.Courses;

import java.util.Map;

/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 * @date 2023-03-28 12:29:39
 */
public interface CoursesService extends IService<Courses> {

    PageUtils queryPage(Map<String, Object> params);
}

