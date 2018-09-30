package com.example.rabbit.config;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Configuration
public class RabbitConfig {

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


    /**
     * 队列
     * @return
     */
    @Bean
    public Queue Queue() {
        return new Queue(QUEUE_NAME);
    }











}
