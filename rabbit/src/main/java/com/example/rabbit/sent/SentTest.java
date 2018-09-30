package com.example.rabbit.sent;

import com.alibaba.fastjson.JSON;
import com.example.rabbit.pojo.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 * 消息生产者
 */
@Controller
public class SentTest {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 队列名称
     */
    @Value( "${QUEUE_NAME}" )
    private String QUEUE_NAME;

    /**
     * 队列名称
     */
    @Value( "${QUEUE_NAME1}" )
    private String QUEUE_NAME1;

    public void send() throws Exception{
        String context = "开始生产消息 " + new Date();
        this.rabbitTemplate.convertAndSend(context);
    }


    public void send(int i) {
        //生产的消息
        User user = new User( 156,"sd" );
        String json = JSON.toJSONString( user );
        String context = "生产者----mesage---->"+i;
        System.out.println("！！！ " + context);
        this.rabbitTemplate.convertAndSend(QUEUE_NAME, json);
        this.rabbitTemplate.convertAndSend(QUEUE_NAME1, json);
    }
}
