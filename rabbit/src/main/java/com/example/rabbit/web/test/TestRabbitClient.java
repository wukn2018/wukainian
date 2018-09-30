package com.example.rabbit.web.test;

import com.example.rabbit.utils.ConnectionFactoryUtil;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * ***GOOD LUCK****
 *
 * @Author : Wukn
 * @Date : 2018/6/
 */
public class TestRabbitClient {


    public final String QUEUE_NAME =  "test_simple_queue";


    /**
     * 发送消息
     */
    @Test
        public void test01() {
        try {
            //获取连接
            Connection connection = ConnectionFactoryUtil.getConnection( );

            //获取通道
            Channel channel =  connection.createChannel();

            //创建队列申明
            channel.queueDeclare(QUEUE_NAME,false,false,false,null  );

            //每个消费者发送确认消息之前只发一个，不发送下一个
           // channel.basicQos( 1 );

            for (int i = 0;i<30;++i) {
                String message = "消息---"+i;
                channel.basicPublish( "",QUEUE_NAME,null,message.getBytes() );
                System.out.println("发送消息。。。"+message);
            }

            channel.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace( );
        }
    }


    /**
     * 消费消息1
     */
    public void test02() {
        Channel channel = null;
        QueueingConsumer consumer = null;
        try {
            //获取连接
            Connection connection = ConnectionFactoryUtil.getConnection( );

            //获取通道
             channel =  connection.createChannel();

            //通过channel指定队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null  );

            // .创建一个消费者队列consumer,并指定channel
             consumer = new QueueingConsumer(channel);

            //7.为channel指定消费者
             channel.basicConsume(QUEUE_NAME, true, consumer);
             while (true) {
            // 从consumer中获取队列中的消息,nextDelivery是一个阻塞方法,如果队列中无内容,则等待
             QueueingConsumer.Delivery delivery = consumer.nextDelivery();
             String message = new String(delivery.getBody());
             System.out.println("【消费者1接收到了\" + QUEUE_NAME + \"中的消息:】---" + message);
             }


        } catch (Exception e) {
            e.printStackTrace( );
        }

        try {
            //自动确认模式
            boolean autoACK = true;
            channel.basicConsume(QUEUE_NAME,autoACK, consumer );
        } catch (IOException e) {
            e.printStackTrace( );
        }

    }






    /**
     * 消费消息2
     */
    public void test03() {

        try {
            //获取连接
            Connection connection = ConnectionFactoryUtil.getConnection( );

            //获取通道
            Channel channel =  connection.createChannel();

            //通过channel指定队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null  );

            // .创建一个消费者队列consumer,并指定channel
            QueueingConsumer consumer = new QueueingConsumer(channel);

            //7.为channel指定消费者
            channel.basicConsume(QUEUE_NAME, true, consumer);
            while (true) {
                // 从consumer中获取队列中的消息,nextDelivery是一个阻塞方法,如果队列中无内容,则等待
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("【消费者2接收到了\" + QUEUE_NAME + \"中的消息:】---" + message);
            }
        } catch (Exception e) {
            e.printStackTrace( );
        }

    }




    @Test
    public void test04() {
        test03();
    }














}
