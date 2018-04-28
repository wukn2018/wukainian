package com.example.es.repository;

import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class QuertIndex {

    public static final Logger logger = LoggerFactory.getLogger( TransportClientRepository.class );

    private TransportClient client;


    /**
     * 多获取API允许基于它们获取文档列表index，type并且id
     */
    public void querLi() {

        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                //获取知道的索引文档，有唯一的index  type  id
                .add("twitter", "tweet", "1")
                //获取具有相同索引名称，类型但是id不一样的文档
                .add("twitter", "tweet", "2", "3", "4")
                //从其他索引类型获取需要的索引文档
                .add("another", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            //判断文档是否存在
            if (response.isExists()) {
                //得到所有文档内容
                String json = response.getSourceAsString();
            }
        }

    }






    /**
     * 量API允许在单个请求中索引和删除多个文档
     */
    public void vf() throws Exception{

        BulkRequestBuilder bulkRequest = client.prepareBulk();

        // 使用客户端＃准备，或使用请求＃直接构建索引/删除请求
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            //遍历每个批量响应项来处理失败
        }

    }


    /**
     * 使用批量处理器
     *
     * BulkProcessor类基于数或请求的大小批量操作，或在给定的时间
     */
    public void bulkProcessorTes() {

        BulkProcessor bulkProcessor = BulkProcessor.builder(
                //启动客户端
                client,
                new BulkProcessor.Listener() {
                    //在执行bulk之前被调用
                    @Override
                    public void beforeBulk(long executionId,BulkRequest request) {


                    }

                    //执行后调用此方法。你可以例如检查是否有一些失败的请求response.hasFailures()
                    @Override
                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {


                    }

                    //这个方法在批量失败并被提出时调用 Throwable
                    @Override
                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {


                    }
                })
                //我们希望每10万次请求执行一次批量操作
                .setBulkActions(10000)
                //我们希望每5mb刷新一次散装
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                //无论请求的数量是多少，我们都希望每5秒刷新一次
                .setFlushInterval( TimeValue.timeValueSeconds(5))
                //设置并发请求的数量。值为0意味着只有一个请求将被允许执行。值为1表示在累积新的批量请求时允许执行1个并发请求
                .setConcurrentRequests(1)
                //设置自定义退避策略，最初将等待100ms，成倍增加，并重试三次。每当有一个或多个批量项目请求
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();


        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        //处理请求
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1").source(json, XContentType.JSON));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));

        //关闭
        bulkProcessor.close();

    }






    /**
     * 如果您正在使用elasticsearch运行测试并且正在使用BulkProcessor填充数据集，
     * 则应该更好地设置并发请求的数量，0以便以同步方式执行批量刷新操作：
     */
    public void g() {
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {

            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {

            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {

            } /* Listener methods */ })
                .setBulkActions(10000)
                .setConcurrentRequests(0)
                .build();

         //添加你的请求
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1").source("", XContentType.JSON));

        //刷新任何剩余的请求
        bulkProcessor.flush();

        //或者关闭bulkProcessor，如果你不再需要的话
        bulkProcessor.close();

        //刷新你的索引
        client.admin().indices().prepareRefresh().get();

        //现在你可以开始搜索！
        client.prepareSearch().get();

    }











































}
