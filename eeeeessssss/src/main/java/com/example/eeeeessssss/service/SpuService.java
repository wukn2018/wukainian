package com.example.eeeeessssss.service;

import com.example.eeeeessssss.entity.Spu;
import com.example.eeeeessssss.repository.SpuRepository;
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
public class SpuService {

    @Autowired
    private SpuRepository repository;


    /**
     *查询所有spu
     * @return
     */
    public List<Spu> findAllsPU() {
        List<Spu> list =  repository.findAllSpu( true );
        return list;
    }

    /**
     *查询所有spu
     * @return
     */
    public List<Spu> findAll() {
        List<Spu> list =  repository.findAll();
        return list;
    }




}
