package com.cat.grabclass.service.consumer;

import com.cat.grabclass.common.utils.Constant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Mr.xin
 */
@Component
@DependsOn("springContextUtils")
public class RecordConsumer {

    private DefaultMQPushConsumer consumer;


    @PostConstruct
    private void init() throws MQClientException {
        this.consumer = new DefaultMQPushConsumer("rcg");
        consumer.setNamesrvAddr("192.168.62.102:9876");
        consumer.setConsumeThreadMax(30);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(Constant.MQ.TX_RECORD_TOPIC, "*");
        consumer.setMessageListener(new MessageListenerImpl());
        start();
    }
    private void start() throws MQClientException {
        this.consumer.start();
    }
}
