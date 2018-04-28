package com.example.es.repository;
import com.example.es.pojo.ESearchTypeColumn;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.Index;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Component
public class TransportClientRepository {

    public static final Logger logger = LoggerFactory.getLogger( TransportClientRepository.class );


    private TransportClient client;


    /**
     * 创建搜索引擎文档 1
     * @param index 索引名称
     * @param type 索引类型
     * @param id 索引id
     * @param doc
     * @return
     */
    public void gh(String index, String type, String id, Object doc) {
        try {
            IndexResponse response = client
                    //"twitter", "tweet", "1"
                    .prepareIndex("twitter", "tweet", "1")
                    //设置索引文档
                    .setSource( getFile(doc) )
                    .get();
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }



    /**
     *创建搜索引擎文档 2
     * 您也可以使用startArray(String)和 endArray()方法添加数组。
     * 顺便说一下，该field方法接受许多对象类型。您可以直接传递数字
     * ，日期和其他XContentBuilder对象。
     * @return
     */
    public void getjSOdN(String index, String type, String id) {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(json, XContentType.JSON)
                .get();


        //索引名称
        String _index = response.getIndex();
        //类型类型
        String _type = response.getType();
        //文档ID（生成与否）
        String _id = response.getId();
        //版本（如果这是您第一次索引此文档，您将得到：1
        long _version = response.getVersion();
        //状态已经存储了当前的实例语句。
        RestStatus status = response.status();

        //获取生成的JSON内容，则可以使用该 string()方法。
        // String str = builder.string();
    }





    /**
     * 更新文档1
     *
     * @param index
     * @param type
     * @param id
     * @param
     * @return
     */
    public String updateDoc(String index, String type, String id, Object doc) {

        UpdateResponse response = client
                .prepareUpdate(index, type, id)
                .setDoc(getFile( doc ))
                .get();
        return response.getId();
    }





    /**
     * 更新文档2
     *
     * @param index
     * @param type
     * @param id
     * @param
     * @return
     */
    public String updatejosn(String index, String type, String id) throws Exception {

        UpdateRequest updateRequest = new UpdateRequest(index,type,id);
//        updateRequest.index(index);
//        updateRequest.type(type);
//        updateRequest.id(id);
        try {
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("gender", "male")
                    .endObject());
        } catch (IOException e) {
            e.printStackTrace( );
        }
        client.update(updateRequest).get();
        return updateRequest.id();
    }







    /**
     * 更新文档3
     *支持upsert。如果文档不存在，upsert 元素的内容将用于索引新鲜文档
     * @throws Exception
     */
    public void updatesert(String index, String type, String id) throws Exception{
        //所以文档
        IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                .source(jsonBuilder()
                        .startObject()
                        .field("name", "Joe Smith")
                        .field("gender", "male")
                        .endObject());


        //修改索引文档
        UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
                .doc(jsonBuilder()
                        .startObject()
                        .field("gender", "male")
                        .endObject())
                .upsert(indexRequest);
        //执行修改
        client.update(updateRequest).get();
    }




    /**
     *删除索引
     * @param index  索引名称
     * @param type  索引类型
     * @param id  唯一索引id
     */
    public String deleteIndex(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).get();
        return response.getId();

    }






    /**
     *
     * 获取索引对应的存储内容
     *获取基于其id的类型化JSON文档。index，type，ID为id：
     * @param index
     * @param type
     * @param id
     * @return
     */
    public String getIdx(String index, String type, String id) {
        GetResponse response = client
                .prepareGet(index, type, id)
                .get();
        if (response.isExists()) {
            return response.getSourceAsString();
        }
        return null;

        /**
         * 默认情况下，operationThreaded设置为true表示在不同的线程上执行操作。这是一个例子，它将其设置为 false：
         */
      //  client.prepareGet(index, type, id).setOperationThreaded( false ).get();


    }







    /**
     * 生成JSON内容1
     *
     *Elasticsearch提供了内置的助手来生成JSON内容
     * @param obj
     * @return
     */
    public XContentBuilder getFile(Object obj) {
        try {

            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();

            List<Field> fieldList = new ArrayList<Field>();
            //得到Class实例化
            Class tempClass = obj.getClass();
            // 当父类为null的时候说明到达了最上层的父类(Object类).
            while (tempClass != null) {
                //tempClass.getDeclaredFields() 通过反射获得对象的字段值
                fieldList.addAll( Arrays.asList(tempClass.getDeclaredFields()));
                // 得到父类,然后赋给自己
                tempClass = tempClass.getSuperclass();
            }

            for(Field field:fieldList) {
                //判断字段中是否包含ESearchTypeColumn注解
                if(field.isAnnotationPresent( ESearchTypeColumn.class )) {
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), obj.getClass());
                    Object value =descriptor.getReadMethod().invoke(obj);
                    if (value != null) {

                        //添加文档
                        builder.field(field.getName(),value.toString()); }
                }
            }

            builder.endObject();
            return builder;
        } catch (IOException e) {
            e.printStackTrace( );
        } catch (IllegalAccessException e) {
            e.printStackTrace( );
        } catch (IntrospectionException e) {
            e.printStackTrace( );
        } catch (InvocationTargetException e) {
            e.printStackTrace( );
        }
        return null;
    }


    /**
     * 生成JSON内容2
     *
     *
     *您也可以使用startArray(String)和 endArray()方法添加数组。
     * 顺便说一下，该field方法接受许多对象类型。您可以直接传递数字
     * ，日期和其他XContentBuilder对象。
     * @return
     */
    public XContentBuilder getFilejSON() {

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();

                builder.startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject();
                return builder;

                //生成的JSON内容，则可以使用该 string()方法。
               // String str = builder.string();
        } catch (IOException e) {
            e.printStackTrace( );
        }
        return null;
    }




































}
