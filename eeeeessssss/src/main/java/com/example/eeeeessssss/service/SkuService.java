package com.example.eeeeessssss.service;

import com.example.eeeeessssss.entity.Sku;
import com.example.eeeeessssss.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Service
public class SkuService {


    @Autowired
    private SkuRepository repository;


    /**
     * 查询所有
     * @return
     */
    public List<Sku> findAll() {
        List<Sku> list = repository.findAll();
        return list;
    }
















}
