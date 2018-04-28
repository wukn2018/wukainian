package com.example.eeeeessssss.controller;
import com.example.eeeeessssss.contans.ResultCode;
import com.example.eeeeessssss.pojo.User;
import com.example.eeeeessssss.response.ResultClient;
import com.example.eeeeessssss.service.EsIndexService;
import com.example.eeeeessssss.service.SpuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "/es")
public class EsIndexController {


    public static final Logger log = LoggerFactory.getLogger( EsIndexController.class );



    @Autowired
    private EsIndexService esIndexService;

    @Autowired
    private SpuService spuService;


    /**
     * 添加索引文档
     * @param index
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/save")
    @ResponseBody
    private ResultClient esSave(String index, String type) throws Exception {
        boolean b = esIndexService.addStrMesg( index,type );
        return new ResultClient( b );
    }


    /**
     * 创建索引
     * @param index
     * @return
     */
    @GetMapping(value = "/build")
    @ResponseBody
    public String buildIndex(String index) throws Exception{
        boolean b1 = esIndexService.buildIndex( index );
        return ""+b1;
    }


    /**
     * 删除索引
     * @param index
     * @return
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    public ResultClient deleteIndex(String index) throws Exception{
        boolean b1 = esIndexService.deleteIndex( index );
        return new ResultClient( b1 );
    }


    /**
     * 查询数据
     * @param index
     * @return
     */
    @GetMapping(value = "/query")
    @ResponseBody
    public Map<String ,Object> queryIndex(String index, String type, String id) throws Exception{
        return esIndexService.dqueryndex( index ,type,id);
    }

    /**
     * 修改文档内容
     * @param index
     * @return
     */
    @GetMapping(value = "/update")
    @ResponseBody
    public String updateIndex(String index, String type, String id, User user) throws Exception{
        return esIndexService.updateDoc( index ,type,id,user);
    }












}
