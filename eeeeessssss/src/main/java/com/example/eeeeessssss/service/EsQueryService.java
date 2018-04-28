package com.example.eeeeessssss.service;
import com.alibaba.fastjson.JSON;
import com.example.eeeeessssss.repository.QueryRepository;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Service
public class EsQueryService {


    Logger logger = LoggerFactory.getLogger( EsQueryService.class );

    @Autowired
    private TransportClient client;

    @Autowired
    private QueryRepository queryModel;



    /**
     *
     * 搜索
     * @return
     */
    public List<Object> quertTest(String index,String type,String s){
        //集群健康
        clusterHealth();
        Map<String,Object> map = new HashMap<>(  );
        map.put( "createByName","xg_13858091726" );
        //搜索类型
        QueryBuilder queryBuilders = queryModel.queryStringQueryByTitle(s);
        //搜索结果
        List<Object> list = querySearch(index ,type,queryBuilders);

        return list;
    }






    /**
     * 高亮设置
     * @return
     */
    public HighlightBuilder highlightBuilder() {
        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.preTags("<h1>");
        hBuilder.postTags("</h1>");
        // 设置高亮显示的字段
        hBuilder.field("name");
        return hBuilder;
    }


    /**
     * 建立搜索模板
     * @param index  索引名称
     * @param type  索引类型
     * @param queryBuilders  搜索类型
     * @return
     */
    public SearchResponse searchBuild(String index , String type ,QueryBuilder queryBuilders ) {
        //设置高亮
        HighlightBuilder highlightBuilder = highlightBuilder();
        //创建搜索模板
        SearchResponse searchResponse = client
                //索引名称，   也可以.setIndices(index);
                .prepareSearch(index)

                //索引类型  当参数为0个或者不调用此方法时，表示查询所有的type；
                .setTypes(type)

                //搜索方式
                .setQuery( queryBuilders)

                //参数可为一个或多个字符串，表示要进行检索的type，当参数为0个或者不调用此方法时，表示查询所有的type；
                //.setSearchType( SearchType.DFS_QUERY_THEN_FETCH)

                //设置超时时间
                //.setTimeout()

                //置Score的最小数量
                //.setMinScore( 1 )

                //从哪一个Score开始查
                //.setFrom( 2 )

                //每页显示条数
                //.setSize(  )

                //过滤
                //.setPostFilter(QueryBuilders.rangeQuery("id").from(1).to(2) )

                //需要查询出多少条结果
                //.setSize( 5 )

                //设置高亮
                //.highlighter(hBuilder)

                //排序
                //.addSort( "age", SortOrder.DESC )

                .get();

        return searchResponse;
    }


    /**
     * 迭代搜索内容
     * @return
     */
    public List<Object> querySearch(String index , String type ,QueryBuilder queryBuilders) {
        //搜索模板
        SearchResponse searchResponse = searchBuild(index , type , queryBuilders);
        //获取搜索内容
        SearchHits hits = searchResponse.getHits();
        //搜索数据数量
        long totalHits = hits.getTotalHits();
        System.out.println("搜索数据数量------>"+totalHits);
        //获取所有所有数据
        SearchHit[] hits2 = hits.getHits();
        List<Object> list = new ArrayList(  );
        //遍历
        for(SearchHit h : hits2) {
            System.out.println(h.getSourceAsString()+"--------->1");
            list.add( JSON.parse( h.getSourceAsString()));
        }
        return list;
    }









    /**
     * 匹配查询
     * @return
     */
    public QueryBuilder quertMathQuery(){
        QueryBuilder qb = matchQuery("name","吴开念");
        return qb;
    }





    /**
     * 集群健康
     */
    public  void clusterHealth() {
        //获取所有指数的信息
        ClusterHealthResponse healthResponse = client.admin().cluster().prepareHealth(  ).get();

        //集群名称
        String clusterName = healthResponse.getClusterName();
        logger.info( "集群名称{}(clusterName-->)"+healthResponse.getClusterName() );

        //数据节点的总数
        int numberOfDataNodes = healthResponse.getNumberOfDataNodes();
        logger.info( "数据节点的总数{}(numberOfDataNodes-->)"+healthResponse.getNumberOfDataNodes() );

        //获取节点的总数
        int numberOfNodes = healthResponse.getNumberOfNodes();
        logger.info( "节点总数{}(numberOfNodes)"+healthResponse.getNumberOfNodes() );

        //遍历所有索引
        for(ClusterIndexHealth health:healthResponse.getIndices().values()){
            //索引名称
            String index = health.getIndex();
            logger.info( "索引名称{}index"+health.getIndex() );

            //碎片数量
            int numberOfShards = health.getNumberOfShards();
            logger.info( "碎片数量{}(numberOfShards-->)"+health.getNumberOfShards() );

            //复制品数量
            int numberOfReplicas = health.getNumberOfReplicas();
            logger.info( "复制品数量{}(numberOfReplicas-->)"+health.getNumberOfReplicas() );

            //索引状态
            ClusterHealthStatus status = health.getStatus();
            logger.info( "索引状态{}(status-->)"+health.getStatus() );

        }
    }





























}
