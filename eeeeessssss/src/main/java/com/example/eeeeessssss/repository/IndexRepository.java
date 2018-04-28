package com.example.eeeeessssss.repository;
import com.alibaba.fastjson.JSONObject;
import com.example.eeeeessssss.pojo.User;
import com.example.eeeeessssss.response.ClientException;
import com.example.eeeeessssss.response.EnumRespCode;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/4/24
 *
 * 索引操作基础类
 *
 */
@Repository
public class IndexRepository {

    public static final Logger logger = LoggerFactory.getLogger( IndexRepository.class );


    @Autowired
    private TransportClient client;




    /**
     * 创建索引
     *
     * @param index  索引名称
     * @return
     */
    public boolean buildIndex(String index) throws Exception{
        logger.info( "index{}---->"+index );
        //判断索引是否存在
        if (!isIndexExist(index)) {
            logger.info("索引不存在!");
            CreateIndexResponse buildIndexresponse = client.admin().indices().prepareCreate(index).execute().actionGet();
            logger.info( "创建成功返回true{}'"+buildIndexresponse.isAcknowledged() );
            boolean b = buildIndexresponse.isAcknowledged();
            return b;
        }
        return false;
    }


    /**
     * 删除索引
     * @return
     */
    public boolean deleteIndex(String index) throws Exception{
        logger.info( "index{}---->"+index );
        if(!isIndexExist(index)) {
            logger.info( "索引不存在集群，无法删除" );
            throw new ClientException( EnumRespCode.BUSY);
        }
        DeleteIndexResponse diResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (diResponse.isAcknowledged()) {
            logger.info("删除索引成功index！！！" + index);
            return diResponse.isAcknowledged();
        }
        logger.info("删除索引失败index！！！" + index);
        return diResponse.isAcknowledged();
    }


    /**
     * 添加索引文档1
     * @param index
     * @param type
     */
    public boolean addMesg(String index,String type,User user) {
        logger.info( "index type{}---->"+index+"--->"+type );
        IndexResponse response_insert = client.prepareIndex(index,type)
                .setSource(getXContentBuilderKeyValue(user))
                .get();
        logger.info( "response_insert.forcedRefresh()--->"+response_insert.forcedRefresh() );
        logger.info( "response_insert.getResult()--->" +response_insert.getResult());
        int i = response_insert.status().getStatus();
        if(i>0) {
            return true;
        }
        return false;
    }

    /**
     * 添加索引文档2
     * @param index
     * @param type
     */
    public boolean addStrMesg(String index,String type,String json) {
        logger.info( "index + type{}----->josn"+index+"-->"+type+"--->"+json);
//        String json = "{" +
//                "\"user\":\"wkn\"," +
//                "\"postDate\":\"2018-4-21\"," +
//                "\"message\":\"dddddd\"" +
//                "}";
        IndexResponse response_insert = client.prepareIndex(index,type)
                .setSource(json, XContentType.JSON)
                .get();

        logger.info( "response_insert.forcedRefresh()>>>>"+response_insert.forcedRefresh() );
        logger.info( "response_insert.getResult()>>>>>" +response_insert.getResult());
        int i = response_insert.status().getStatus();
        if(i>0) {
            return true;
        }
        return false;
    }


    /**
     * 查询数据
     * @param index 索引
     * @param type  类型
     * @param id    数据ID
     * @return
     */
    public Map<String, Object> findIndexAndType(String index, String type, String id) {
        if(index == null || type == null || id == null) {
            logger.info(" 无法查询，缺少查询条件 ");
            return null;
        }
        //获取查询数据信息
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        //这里也有指定的时间获取返回值的信息，如有特殊需求可以
        return getResponse.getSource();
    }


    /**
     * 更新数据
     *
     * @param data  添加的数据类型 json格式的
     * @param index 索引<----->关系型数据库
     * @param type  类型<----->关系型数据表
     * @param id    数据ID<----->id
     * @return
     */
    public boolean updateIndex(JSONObject data, String index, String type, String id) {
        if(index == null || type == null || id == null) {
            logger.info(" 无法更新数据，缺少查询条件!!!!!!! ");
            return false;
        }
        //更新步骤
        UpdateRequest up = new UpdateRequest();
        up.index(index).type(type).id(id).doc(data);

        //获取响应信息
        //.actionGet(timeoutMillis)，也可以用这个方法，当过了一定的时间还没得到返回值的时候，就自动返回。
        UpdateResponse response = client.update(up).actionGet();
       int i =  response.status().getStatus();
        logger.info("更新数据状态信息，status{}", response.status().getStatus());
        if(i>0) {
            return true;
        }
        return false;
    }


    /**
     * 更新文档
     *
     * @param index
     * @param type
     * @param id
     * @param
     * @return
     */
    public String updateDoc(String index, String type, String id,User user) throws Exception{

        UpdateResponse response = client.prepareUpdate(index, type, id)
                .setDoc(getXContentBuilderKeyValue(user))
                .get();
        //更新文档的id
        String lid = response.getId();
        return lid;
    }


    /**
     * 生成文档json
     * @return
     */
    public XContentBuilder getXContentBuilderKeyValue(User user) {
        try {
            user = new User( "吴开念","中国",18,new Date(  ),"cat" );
            //用elasticsearch内置方法生成josn
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("name", user.getName())
                    .field("address", user.getAddress())
                    .field("age", user.getAge())
                    .field( "dateTime",user.getDateTime() )
                    .field( "title" ,user.getTitle())
                    .endObject();
            return builder;
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
        return null;
    }


    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public boolean isIndexExist(String index) throws Exception{
        logger.info( "index{}"+index );
        IndicesExistsResponse response = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        //索引存在集群就返回true
        if (response.isExists()) {
            logger.info("此索引 [" + index + "] 已经在ES集群里存在");
            return true;
        }
        logger.info(" ES集群没有此索引 [" + index + "] ");
        return false;
    }











}
