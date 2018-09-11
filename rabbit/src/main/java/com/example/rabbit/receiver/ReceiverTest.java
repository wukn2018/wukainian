//package com.example.rabbit.receiver;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @Author : Wukn
// * @Date : 2018/5/4
// *
// */
//@Component
//@RabbitListener(queues = "test")//注明需要消费的队列名称，用于监听这个队列
//public class ReceiverTest {
//
//
//    /**
//     * 消息消费
//     * @param hello
//     *
//     *
//     * @RabbitListener 和 @RabbitHandler可以结合使用，
//     * 不同类型的消息使用不同的方法来处理。
//     */
//    @RabbitHandler//用于处理消息
//    public void process(String hello) {
//        System.out.println("消费消息  : " + hello);
//    }
//}
