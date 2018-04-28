package com.example.es.repository;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class SearchApi {

    public static final Logger lo = LoggerFactory.getLogger( SearchApi.class );

    private TransportClient client;




    /**
     * 搜索API允许执行搜索查询并取回匹配查询的搜索匹配。
     * 它可以跨越一个或多个索引并跨越一种或多种类型执行。
     * 该查询可以使用查询Java API提供。
     * 搜索请求的主体是使用SearchSourceBuilder。
     */
    public void SearchSourceBuilderDemo01() {

        SearchResponse response = client.prepareSearch("index1", "index2")
                .setTypes("type1", "type2")
                .setSearchType( SearchType.DFS_QUERY_THEN_FETCH)
                // Query
                .setQuery( QueryBuilders.termQuery("multi", "test"))
                // Filter
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        //使用所有默认选项对整个集群进行匹配
       // SearchResponse response1 = client.prepareSearch(  ).get();
    }





    /**
     *
     */
    public void SearchSourceBuilderDemo02() {
        SearchRequestBuilder srb1 = client
                .prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
        SearchRequestBuilder srb2 = client
                .prepareSearch().setQuery(QueryBuilders.matchQuery("name", "kimchy")).setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .get();

// You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
        }

    }



























































}
