package com.example.eeeeessssss.controller;
import com.example.eeeeessssss.contans.EsContan;
import com.example.eeeeessssss.response.ResultClient;
import com.example.eeeeessssss.service.EsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "query")
public class EsQueryController {


    @Autowired
    private EsQueryService queryService;


    /**
     *
     * 搜索测试
     * @return
     */
    @GetMapping(value = "all")
    @ResponseBody
    public ResultClient queryAll(String str) {
        List<Object> list = queryService.quertTest( EsContan.INDEX_NAME03,EsContan.INDEX_TYPE03,str);
        System.out.println(list+"-------------->2");
        return new ResultClient( list );
    }





























}
