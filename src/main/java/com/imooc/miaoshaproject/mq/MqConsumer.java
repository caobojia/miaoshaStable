package com.imooc.miaoshaproject.mq;

import com.alibaba.fastjson.JSON;
import com.imooc.miaoshaproject.dao.ItemDOMapper;
import com.imooc.miaoshaproject.dao.ItemStockDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by fengliguantou on 2019/2/23.
 */
@Component
@Slf4j
public class MqConsumer {

    private DefaultMQPushConsumer consumer;
    @Value("${mq.nameserver.addr}")
    private String nameAddr;

    @Value("${mq.topicname}")
    private String topicName;
    public static int i = 1;

    @Resource
    private ItemStockDOMapper itemStockDOMapper;

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer("stock_consumer_group");
        consumer.setNamesrvAddr(nameAddr);
        consumer.subscribe(topicName,"*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //实现库存真正到数据库内扣减的逻辑
                Message msg = msgs.get(0);
                String jsonString  = new String(msg.getBody());
                Map<String,Object>map = JSON.parseObject(jsonString, Map.class);
                Integer itemId = (Integer) map.get("itemId");
                Integer amount = (Integer) map.get("amount");
                itemStockDOMapper.decreaseStock(itemId,amount);
                System.out.println(i++);
                int a = 10 / 0;
                log.info("商品Id为{}库存减{}",itemId,amount);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }
}
