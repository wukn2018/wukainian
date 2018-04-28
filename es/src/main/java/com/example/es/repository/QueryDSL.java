package com.example.es.repository;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class QueryDSL {

    public static final Logger lo = LoggerFactory.getLogger( QueryDSL.class );

    private TransportClient client;




    /**
     * 集群健康
     */
    public  void DSLtEST01() {
        //获取所有指数的信息
        ClusterHealthResponse healthResponse = client.admin().cluster().prepareHealth(  ).get();
        //集群名称
        String clusterName = healthResponse.getClusterName();
        //数据节点的总数
        int numberOfDataNodes = healthResponse.getNumberOfDataNodes();
        //获取节点的总数
        int numberOfNodes = healthResponse.getNumberOfNodes();
        //遍历所有索引
        for(ClusterIndexHealth health:healthResponse.getIndices().values()){
            //索引名称
            String index = health.getIndex();
            //碎片数量
            int numberOfShards = health.getNumberOfShards();
            //复制品数量
            int numberOfReplicas = health.getNumberOfReplicas();
            //索引状态
            ClusterHealthStatus status = health.getStatus();
        }
    }





    /**
     * 您可以使用群集运行状况API等待整个群集或给定索引的特定状态
     */
    public void healthtest() {
        //准备健康请求
        client.admin().cluster().prepareHealth()
                //等待群集变成黄色
                .setWaitForYellowStatus()
                .get();

        //准备索引的健康请求 company
        client.admin().cluster().prepareHealth("company")
                //等待索引为绿色
                .setWaitForGreenStatus()
                .get();

        //准备索引的健康请求 employee
        client.admin().cluster().prepareHealth("employee")
                //等待索引为绿色
                .setWaitForGreenStatus()
                //最多等2秒钟
                .setTimeout( TimeValue.timeValueSeconds(2))
                .get();
    }






    /**
     * 如果索引不具备预期状态，并且您希望在此情况下失败，则需要明确解释结果：
     */
    public void healthdetw() {
        ClusterHealthResponse response = client.admin().cluster().prepareHealth("company")
                //等待索引为绿色
                .setWaitForGreenStatus()
                .get();

        ClusterHealthStatus status = response.getIndices().get("company").getStatus();
        if (!status.equals(ClusterHealthStatus.GREEN)) {
            //
            //如果不是，则抛出异常 GREEN
            throw new RuntimeException("Index is in " + status + " state");
        }

    }













}































