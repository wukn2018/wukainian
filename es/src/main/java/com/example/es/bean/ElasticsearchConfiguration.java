package com.example.es.bean;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class ElasticsearchConfiguration implements FactoryBean<TransportClient>,InitializingBean, DisposableBean {

    Logger logger = LoggerFactory.getLogger( ElasticsearchConfiguration.class );

    @Value( "{spring.data.elasticsearch.cluster-nodes}" )
    private String clusterNodes;


    @Value( "{spring.data.elasticsearch.cluster-name}" )
    private String clusterName;



    private TransportClient client;


    /**
     * 关闭客户端
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        logger.info( "关闭客户端------" );
        try {
            if(null != client) {
                client.close();
            }
        } catch (Exception e) {
            logger.error( "{error}" );
        }
    }

    /**
     * 输出客户端
     * @return
     * @throws Exception
     */
    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    /**
     *
     * @return
     */
    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }


    @Override
    public boolean isSingleton() {
        return false;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }


    /**
     * 创建客户端
     */
    public TransportClient buildClient() {
        //开启客户端
        try {
            PreBuiltTransportClient preBuiltTransportClient  = new PreBuiltTransportClient(settings());
            if(!"".equals( clusterNodes )) {
                for(String node:clusterNodes.split( "," )) {
                    String InetSocket [] = node.split(":");
                    //地址
                    String  Address = InetSocket[0];

                    Integer  port = Integer.valueOf(InetSocket[1]);
                    preBuiltTransportClient.addTransportAddress(new
                            InetSocketTransportAddress( InetAddress.getByName(Address),port ));
                }
                client = preBuiltTransportClient;
                return client;
            }
        } catch (UnknownHostException e1) {
                logger.error( "error{}"+e1 );
        }
        return null;
    }






    /**
     * 初始化默认的client
     *
     * 创建客户端，如果您使用与“elasticsearch”不同的名称，则必须设置群集名称：
     */
    public Settings settings(){
        //设置集群名称
        Settings settings = Settings.builder()
                //集群名称
                .put("cluster.name",clusterName)
                //开启集群刷新
                .put("client.transport.sniff",true)
                .build();

        //启动客户端
        client = new PreBuiltTransportClient(settings);
        return settings;
    }




}
