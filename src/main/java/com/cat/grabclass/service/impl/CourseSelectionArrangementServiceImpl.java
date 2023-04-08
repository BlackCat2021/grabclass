package com.cat.grabclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cat.grabclass.common.utils.Constant;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.Query;
import com.cat.grabclass.common.utils.RedisUtils;
import com.cat.grabclass.dao.CourseSelectionArrangementDao;
import com.cat.grabclass.entity.CourseSelectionArrangement;
import com.cat.grabclass.service.CourseSelectionArrangementService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;


/**
 * @author Mr.xin
 */
@Service("courseSelectionArrangementService")
public class CourseSelectionArrangementServiceImpl extends ServiceImpl<CourseSelectionArrangementDao, CourseSelectionArrangement> implements CourseSelectionArrangementService {


    @Resource
    private CourseSelectionArrangementDao arrangementDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseSelectionArrangement> page = this.page(
                new Query<CourseSelectionArrangement>().getPage(params),
                new QueryWrapper<CourseSelectionArrangement>()
        );

        return new PageUtils(page);
    }

    /**
     * 开启选课安排、将选课加入redis中
     * @param id
     */
    @Override
    public void StartScheduling(Long id) {
        CourseSelectionArrangement arrangement = arrangementDao.selectById(id);
        arrangement.setState(1);
        UpdateWrapper<CourseSelectionArrangement> updateWrapper = new UpdateWrapper<CourseSelectionArrangement>().eq("id", id);
        arrangementDao.update(arrangement,updateWrapper);
        // 将抢课安排放入缓存中提高性能
        RedisUtils.set(Constant.Redis.Course_SELE_ARRAN + arrangement.getCoursesId(),arrangement, 7, TimeUnit.DAYS);
        RedisUtils.set(Constant.Redis.COURSE_STOCK + arrangement.getCoursesId(),arrangement.getStockCount(), 7, TimeUnit.DAYS);
    }

}