package com.yuqiyu.redis.service;
import com.yuqiyu.redis.entity.UserEntity;
import com.yuqiyu.redis.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Service
public class UserService {

    @Autowired
    private UserJpa userJpa;


    /**
     *
     * @return
     */
    @Cacheable(value = "wkn",key = "#id")
    public List<UserEntity> findAll(String id) {
       return userJpa.findAll();
    }




}
