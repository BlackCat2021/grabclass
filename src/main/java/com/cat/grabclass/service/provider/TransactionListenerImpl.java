package com.cat.grabclass.service.provider;

import com.cat.grabclass.dao.RecordInfoDao;
import com.cat.grabclass.entity.RecordInfo;
import com.cat.grabclass.common.utils.JsonUtils;
import com.cat.grabclass.common.utils.SpringContextUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;

/**
 * @author Mr.xin
 */
public class TransactionListenerImpl implements TransactionListener {


    private final RecordInfoDao recordInfoDao = SpringContextUtils.getBean("recordInfoDao", RecordInfoDao.class);

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String body = new String(messageExt.getBody());
        Map<String, Object> msg = JsonUtils.jsonToObject(body, Map.class);
        RecordInfo record = (RecordInfo)msg.get("record");
        RecordInfo recordInfo  = recordInfoDao.selectById(record.getId());
        if(recordInfo == null){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    // 执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        Long courseId = null;
        int insertCount = 0;
        try {
            Map<String, Object> args = (Map) o;
            RecordInfo recordInfo = (RecordInfo) args.get("record");
            courseId = (Long) args.get("courseId");
            // 记录入库
            insertCount = recordInfoDao.insert(recordInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        // 插入成功
        if (insertCount > 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            // 订单创建失败
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
