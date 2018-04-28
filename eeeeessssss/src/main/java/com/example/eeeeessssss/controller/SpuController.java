package com.example.eeeeessssss.controller;

import com.example.eeeeessssss.entity.Spu;
import com.example.eeeeessssss.response.ResultClient;
import com.example.eeeeessssss.service.SpuService;
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
@RequestMapping(value = "spu")
public class SpuController {


    @Autowired
    private SpuService spuService;


    /**
     * 查询所有spu
     * @return
     */
    @PostMapping(value = "/selsct/all")
    @ResponseBody
    public ResultClient findAllSpu() {
        List<Spu> list = spuService.findAllsPU();
        return new ResultClient( list );
    }















}
