package com.example.eeeeessssss.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.eeeeessssss.entity.Sku;
import com.example.eeeeessssss.pojo.User;
import com.example.eeeeessssss.repository.IndexRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Service
public class EsIndexService {

    public static final Logger log = LoggerFactory.getLogger( EsIndexService.class );


    @Autowired
    private IndexRepository repository;

    @Autowired
    private SpuService spuService;


    @Autowired
    private SkuService skuService;


    /**
     * 创建索引
     *
     * @param index  索引名称
     * @return
     */
    public boolean buildIndex(String index) throws Exception{
        boolean b = repository.buildIndex( index );
        return b;

    }


    /**
     * 删除索引
     * @return
     */
    public boolean deleteIndex(String index) throws Exception{
       boolean b = repository.deleteIndex( index );
        return b;
    }


    /**
     * 添加索引文档1
     * @param index
     * @param type
     */
    public boolean addMesg(String index,String type,User user) {
       boolean b = repository.addMesg(index,type,user);
       return b;
    }


    /**
     * 添加索引文档2
     * @param index
     * @param type
     */
    public boolean addStrMesg(String index,String type) {
        boolean b = false;
      List<Sku> list = skuService.findAll();
      for(Sku sku:list) {
          String json = JSON.toJSONString( sku );
          b= repository.addStrMesg( index,type,json );
      }
        return b;
    }


    /**
     * 查询数据
     * @param index 索引
     * @param type  类型
     * @param id    数据ID
     * @return
     */
    public Map<String, Object> dqueryndex(String index, String type, String id) {
       Map<String,Object> map = repository.findIndexAndType(index,type,id);
       return map;
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
        boolean b = repository.updateIndex(data,index,type,id);
        return b;
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
        String idd = repository.updateDoc(index,type,id,user);
        return idd;
    }



}
