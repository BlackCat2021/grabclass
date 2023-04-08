package com.cat.grabclass.service.provider;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Mr.xin
 */
@DependsOn("springContextUtils")
@Component("commonProducer")
public class CommonProducer {
    private DefaultMQProducer producer;

    @PostConstruct
    private void init() throws MQClientException {
        this.producer = new DefaultMQProducer("cpg");
        // FIXME: 2023/1/14 namesrv 应该写在配置文件中
        producer.setNamesrvAddr("192.168.62.102:9876");
        producer.setRetryTimesWhenSendFailed(3);
        start();
    }

    private void start() throws MQClientException {
        this.producer.start();
    }

    public SendResult sendMessage(Message message, Object args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        return producer.send(message);
    }

    public void sendMessage4Delay(Message message, int  delayLevel)  {
        message.setDelayTimeLevel(delayLevel);
        try {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {

                }
                @Override
                public void onException(Throwable throwable) {
                    // todo 将消息存入mysql中，然后使用定时任务处理
                }
            });
        } catch (MQClientException  | RemotingException  | InterruptedException e){
            // todo 将消息存入mysql中，然后使用定时任务处理
            e.printStackTrace();
        }
    }


}