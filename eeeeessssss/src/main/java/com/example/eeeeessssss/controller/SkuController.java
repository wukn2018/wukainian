package com.example.eeeeessssss.controller;

import com.example.eeeeessssss.entity.Sku;
import com.example.eeeeessssss.response.ResultClient;
import com.example.eeeeessssss.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@RestController
@RequestMapping(value = "sku")
public class SkuController {


    @Autowired
    private SkuService skuService;


    /**
     * 查询所有
     * @return
     */
    @PostMapping(value = "/find/all")
    @ResponseBody
    public ResultClient findAll() {
        List<Sku> list = skuService.findAll();
        return new ResultClient( list );
    }











}
