package com.cat.grabclass.service.impl;

import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.Query;
import com.cat.grabclass.entity.Courses;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cat.grabclass.dao.CoursesDao;
import com.cat.grabclass.service.CoursesService;


@Service("coursesService")
public class CoursesServiceImpl extends ServiceImpl<CoursesDao, Courses> implements CoursesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Courses> page = this.page(
                new Query<Courses>().getPage(params),
                new QueryWrapper<Courses>()
        );

        return new PageUtils(page);
    }

}