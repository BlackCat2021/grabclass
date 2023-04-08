package com.cat.grabclass.service.consumer;


import com.cat.grabclass.common.utils.Constant;
import com.cat.grabclass.common.utils.JsonUtils;
import com.cat.grabclass.common.utils.RedisUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @description 延迟删除缓存保证最终一致性
 * @author Mr.xin
 */
@Component
@DependsOn("springContextUtils")
public class CacheConsistencyConsumer {
    private DefaultMQPushConsumer consumer;


    @PostConstruct
    private void init() throws MQClientException {
        this.consumer = new DefaultMQPushConsumer("cccg");
        consumer.setNamesrvAddr("192.168.62.102:9876");
        consumer.setConsumeThreadMax(30);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(Constant.MQ.TOPIC_CACHE_DELETE, "*");
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt messageExt = list.get(0);
                byte[] body = messageExt.getBody();
                try {
                    Map map = JsonUtils.jsonToObject(new String(body), Map.class);
                    String cacheKey = (String) map.get("key");
                    RedisUtils.del(cacheKey);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        start();
    }
    private void start() throws MQClientException {
        this.consumer.start();
    }
}
