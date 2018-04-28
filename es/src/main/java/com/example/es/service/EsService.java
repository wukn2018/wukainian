package com.example.es.service;

import com.example.es.repository.TransportClientRepository;
import org.springframework.stereotype.Service;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Service
public class EsService {





    /**
     * 创建搜索引擎文档 1
     * @param index 索引名称
     * @param type 索引类型
     * @param id 索引id
     * @param doc
     * @return
     */
    public void gh(String index, String type, String id, Object doc) {
        TransportClientRepository repository = new TransportClientRepository();
        repository.gh( index,type,id,doc );
    }










}
