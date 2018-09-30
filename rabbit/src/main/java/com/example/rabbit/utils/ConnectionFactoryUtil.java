package com.example.rabbit.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * ***GOOD LUCK****
 *
 * @Author : Wukn
 * @Date : 2018/9/
 *
 *
 * 获取mq连接
 */
public class ConnectionFactoryUtil {


    public static Connection getConnection() throws Exception{
        //获取连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost( "127.0.0.1" );

        //设置端口
        factory.setPort( 5672 );

        //指定仓库
        factory.setVirtualHost( "/rabbit_wkn" );

        //用户名
        factory.setUsername( "wkn" );

        //密码
        factory.setPassword( "password" );
        return factory.newConnection();
    }
















}
