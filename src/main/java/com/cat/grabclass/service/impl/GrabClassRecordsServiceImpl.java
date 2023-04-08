package com.cat.grabclass.service.impl;

import com.cat.grabclass.common.contants.ResultStatus;
import com.cat.grabclass.common.utils.*;
import com.cat.grabclass.dao.*;
import com.cat.grabclass.entity.*;
import com.cat.grabclass.excption.BusinessException;
import com.cat.grabclass.service.*;
import com.cat.grabclass.service.provider.TransactionProducer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Service("grabClassRecordsService")
@Slf4j
public class GrabClassRecordsServiceImpl extends ServiceImpl<GrabClassRecordsDao, GrabClassRecords> implements GrabClassRecordsService {


    @Resource
    private RecordInfoDao recordInfoDao;

    @PostConstruct
    private void init() {
        cache = CacheBuilder.newBuilder()
                // 设置初始容量为50
                .initialCapacity(1000)
                // 50byte * 10000 = 500,000 -> 50KB
                .maximumSize(10000)
                .expireAfterAccess(30L, TimeUnit.MINUTES)
                .build();
    }


    @Resource
    private CoursesDao coursesDao;


    @Resource
    private CourseSelectionArrangementDao arrangementDao;
    private static String STOCK_PREFIX = "stock_";
    private static Cache<String, Boolean> cache;

    @Resource
    private TransactionProducer producer;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrabClassRecords> page = this.page(
                new Query<GrabClassRecords>().getPage(params),
                new QueryWrapper<GrabClassRecords>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long createRecord(Long userId, Long courseId) throws BusinessException, ExecutionException {


        Courses course = null;
        course = RedisUtils.get(Constant.Redis.COURSE + courseId);
        if (course == null) {
            course = coursesDao.selectById(courseId);
            if(course != null){
                RedisUtils.set(Constant.Redis.COURSE + courseId, course, 1, TimeUnit.HOURS);
            }
        }

        if (course == null) {
            throw new BusinessException(ResultStatus.COURSE_NOT_EXIST);
        }

        //校验是否在选课时间段内
        Date now = new Date(System.currentTimeMillis());
        CourseSelectionArrangement arrangement = RedisUtils.get(Constant.Redis.Course_SELE_ARRAN + courseId);
        if (arrangement == null) {
            throw new BusinessException(ResultStatus.ARRANGEMENT_NOT_EXIST);
        }
        if(now.before(arrangement.getStartDate()) || now.after(arrangement.getEndDate())){
            throw new BusinessException(ResultStatus.ARRANGEMENT_NOT_EXIST);
        }


        // 先查本地缓存是否有剩余
        // 穿透型缓存
        if (!cache.get(STOCK_PREFIX + courseId, () -> {
            Integer count = RedisUtils.get(Constant.Redis.COURSE_STOCK + courseId);
            System.out.println(count);
            if (count == null) {
                return false;
            }
            return count > 0;
        })) {
            throw new BusinessException(ResultStatus.STOCK_NOT_ENOUGH);
        }
        // 预扣减
        Long stock = RedisUtils.decr(Constant.Redis.COURSE_STOCK + courseId, 1);
        if (stock < 0) {
            // 反向补偿
            RedisUtils.incr(Constant.Redis.COURSE_STOCK + courseId, 1);
            Integer count = RedisUtils.get(Constant.Redis.COURSE_STOCK + courseId);
            // 判断库存是否为0,加入本地缓存
            if (count == 0) {
                cache.put(STOCK_PREFIX + courseId, false);
            }
            throw new BusinessException(ResultStatus.STOCK_NOT_ENOUGH);
        }

        // 创建记录
        RecordInfo recordInfo = new RecordInfo();

        recordInfo.setId(IDUtils.generateId());
        recordInfo.setCoursesId(courseId);
        recordInfo.setCreateDate(now);
        recordInfo.setStatus(0);
        recordInfo.setStudentId(userId);
        recordInfo.setCoursesName(coursesDao.selectById(courseId).getCoursesName());

        // 发送消息
        try {
            Map<String, RecordInfo> msg = new HashMap<>();
            msg.put("record", recordInfo);
            Message message = new Message();
            message.setTopic(Constant.MQ.TX_RECORD_TOPIC);
            message.setTags(Constant.MQ.TX_RECORD_TAG);
            message.setBody(JsonUtils.objectToJson(msg).getBytes(StandardCharsets.UTF_8));

            HashMap<String, RecordInfo> args = new HashMap<>();
            args.put("record", recordInfo);
            producer.sendMessage(message, args);
        } catch (MQClientException e) {
            log.error("errorMessage:{}    method:{}", e.getErrorMessage(), Thread.currentThread().getStackTrace()[1].getMethodName());
            throw new BusinessException(ResultStatus.CREATE_ORDER_FAIL);
        }
        return recordInfo.getId();
    }

    @Override
    public int getRecordStatus(Long recordId) {

        RecordInfo recordInfo = recordInfoDao.selectById(recordId);

        return recordInfo == null ? -1 : recordInfo.getStatus();
    }

}