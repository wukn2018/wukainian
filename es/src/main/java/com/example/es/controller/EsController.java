package com.example.es.controller;

import com.example.es.pojo.User;
import com.example.es.utils.StringUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "/es")
public class EsController {


    /**
     * 创建客户端
     * @return
     * @throws Exception
     */
    public TransportClient client() throws Exception {
        InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName("localhost"),9200);

        Settings settings = Settings.builder()
                .put( "cluster.name","my-application",true )
                .build();

        TransportClient client = new PreBuiltTransportClient( settings);
        client.addTransportAddress(node);
        return client;
    }



    /**
     * 创建索引
     * @param id
     * @return
     */
    @GetMapping("/buidIndex")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id") String id) throws Exception {
        TransportClient client = client();
        if (StringUtils.isEmpty(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = client.prepareGet("book", "novel", id)
                .get();
        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }




    /**
     * 创建索引文档
     * @param user
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity add(User user) throws Exception {
        TransportClient client = client();
        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("title", user.getId())
                    .field("author", user.getAddress())
                    .field("word_count", user.getName())
                    .field("publish_date", user.getPublishDate().getTime())
                    .endObject();
            IndexResponse result = client.prepareIndex("book", "novel")
                    .setSource(content)
                    .get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除索引文档
     * @param id
     * @return
     */
    @DeleteMapping("delateIndex")
    @ResponseBody
    public ResponseEntity delete(@RequestParam(name = "id") String id) throws Exception {
        TransportClient client = client();
        DeleteResponse result = client.prepareDelete("book", "novel", id)
                .get();
        return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
    }


    /**
     * genxg
     * @param id
     * @param title
     * @param author
     * @return
     */
    @PutMapping("/updateIndex")
    @ResponseBody
    public ResponseEntity update(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author) throws Exception {
        TransportClient client = client();
        UpdateRequest update = new UpdateRequest("book", "novel", id);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject();
            if (!StringUtils.isEmpty(title)) {
                builder.field("title", title);
            }
            if (!StringUtils.isEmpty(author)) {
                builder.field("author", author);
            }
            builder.endObject();
            update.doc(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            UpdateResponse result = client.update(update).get();
            return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/query/book/novel")
    @ResponseBody
    public ResponseEntity query(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "gt_word_count", defaultValue = "0") int gtWordCount,
            @RequestParam(name = "lt_word_count", required = false) Integer ltWordCount) throws Exception {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        TransportClient client = client();
        if (!StringUtils.isEmpty(author)) {
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }
        if (!StringUtils.isEmpty(title)) {
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count")
                .from(gtWordCount);
        if (ltWordCount != null && ltWordCount > 0) {
            rangeQuery.to(ltWordCount);
        }

        boolQuery.filter(rangeQuery);

        SearchRequestBuilder builder = client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType( SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);

        SearchResponse response = builder.get();
        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            result.add(hit.getSource());
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }}

