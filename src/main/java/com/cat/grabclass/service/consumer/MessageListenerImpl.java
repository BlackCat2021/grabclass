package com.cat.grabclass.service.consumer;


import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cat.grabclass.common.utils.Constant;
import com.cat.grabclass.common.utils.JsonUtils;
import com.cat.grabclass.common.utils.RedisUtils;
import com.cat.grabclass.common.utils.SpringContextUtils;
import com.cat.grabclass.dao.CoursesDao;
import com.cat.grabclass.dao.RecordInfoDao;
import com.cat.grabclass.entity.Courses;
import com.cat.grabclass.entity.RecordInfo;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.tomcat.util.bcel.Const;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MessageListenerImpl implements MessageListenerConcurrently {

    private final CoursesDao coursesDao = SpringContextUtils.getBean("coursesDao", CoursesDao.class);
    private final RecordInfoDao recordInfoDao = SpringContextUtils.getBean("recordInfoDao", RecordInfoDao.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

            MessageExt messageExt = list.get(0);
        try {
            String body = new String(messageExt.getBody());
            Map<String, RecordInfo> msg = JsonUtils.jsonToObject(body, new TypeReference<Map<String, RecordInfo>>(){});
            RecordInfo record = (RecordInfo) msg.get("record");
            Long courseId = record.getCoursesId();

            Boolean b = RedisUtils.hasKey(Constant.Redis.MESSAGE + messageExt.getMsgId());
            if(b){
                // 之前消费过
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            Courses course = coursesDao.selectById(courseId);
            Integer coursesStock = course.getCoursesStock();
            UpdateWrapper<Courses> updateWrapper = new UpdateWrapper<Courses>().eq("id", courseId).eq("courses_stock",coursesStock);
            course.setCoursesStock(coursesStock - 1);
            // 乐观锁方式更新，这可能导致redis mysql 数据不一致问题
            int update = coursesDao.update(course, updateWrapper);
            if(update > 0){
                // 修改订单状态
                record.setStatus(1);
                recordInfoDao.updateById(record);
            }else{
                // 扣减库存失败
                record.setStatus(-1);
                // 更新日志状态
                recordInfoDao.updateById(record);
            }
            // 防止消息重复消费
            RedisUtils.set(Constant.Redis.MESSAGE + messageExt.getMsgId(), " ", 15, TimeUnit.MINUTES);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            if (RedisUtils.get(Constant.Redis.FAIL_MESSAGE+messageExt.getMsgId())!=null){
                Long incr = RedisUtils.incr(Constant.Redis.FAIL_MESSAGE+messageExt.getMsgId(), 1);
                if(incr > 3){
                    // 存入数据库
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            }else{
                RedisUtils.set(Constant.Redis.FAIL_MESSAGE+messageExt.getMsgId(),1);
            }
            // 这里可以统计失败次数，超过 n 次错误以后将消息记录在数据库中，然后使用定时任务处理或者通知人工处理
            e.printStackTrace();
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
