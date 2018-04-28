package com.example.eeeeessssss.configration;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import java.net.InetAddress;
/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Configuration
public class EsConfigClient {


    private static final Logger logger = LoggerFactory.getLogger(EsConfigClient.class);



    /**
     * es集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;

    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;


    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 集群
     */
    private String clusterNodes;
    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean
    public TransportClient transportClient() throws Exception{

        TransportClient client = null;
            Settings esSetting = Settings.builder()
                    // 增加嗅探机制，找到ES集群
                    .put("cluster.name", "my-application")
                    //.put("client.transport.sniff", true)
                    // 增加线程池个数，暂时设为5
                    //.put("thread_pool.search.size", 5)
                    .build();
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress( InetAddress.getByName(hostName),Integer.parseInt( port )));
            return client;
    }

    /**
     * 获取地址和端口号
     */
    public void getHost() {
        if(null != clusterNodes && !StringUtils.isBlank( clusterNodes )) {
            for(String str:clusterNodes.split( "," )) {
                String[] s = str.split( ":" );
                //地址
                String address = s[0];
                //端口号
                String port = s[1];
            }
        }
    }






    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(transportClient());
    }



























}

