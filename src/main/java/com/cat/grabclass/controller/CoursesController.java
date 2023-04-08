package com.cat.grabclass.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.cat.grabclass.common.annotations.Access;
import com.cat.grabclass.common.utils.Constant;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.R;
import com.cat.grabclass.common.utils.RedisUtils;
import com.cat.grabclass.entity.Courses;
import com.cat.grabclass.service.provider.CommonProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.hibernate.annotations.Source;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.grabclass.service.CoursesService;

import javax.annotation.Resource;


/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 */
@RestController
@RequestMapping("grabclass/courses")
public class CoursesController {
    private static final String COURSE_PREFIX = "course_";
    private static final String DELETE_CACHE_TOPIC = "topic_delete_cache";

    @Resource
    private CoursesService coursesService;
    @Resource
    private CommonProducer commonProducer;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @Access
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = coursesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @Access
    public R info(@PathVariable("id") Long id){
        Courses courses = RedisUtils.get(COURSE_PREFIX + id);
        if(courses != null){
            return R.ok().put("course",courses);
        }else{
            courses = coursesService.getById(id);
            RedisUtils.set(COURSE_PREFIX + id, courses, 1, TimeUnit.HOURS);
        }
        return R.ok().put("courses", courses);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Courses courses){
		coursesService.save(courses);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Courses courses){
		coursesService.updateById(courses);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("key",COURSE_PREFIX + courses.getId());
        Message message = new Message(Constant.MQ.TOPIC_CACHE_DELETE, Constant.MQ.TAG_CACHE_DELETE, JSON.toJSONString(map).getBytes());
        commonProducer.sendMessage4Delay(message, 1);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		coursesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
