//package com.example.rabbit.receiver;
//
///**
// *
// * @Author : Wukn
// * @Date : 2018/2/5
// */
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = "test")
//public class NeoReceiver2 {
//
//
//    @RabbitHandler
//     public void process(String neo) {
//                 System.out.println("消费者------->2: " + neo);
//             }
//}
