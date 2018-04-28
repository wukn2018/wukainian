package com.example.es.pojo;

import com.alibaba.fastjson.JSON;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class Category {
    private Integer id;
    private String name;
    private String clor;

    public Category() {
    }

    public Category(Integer id, String name, String clor) {
        this.id = id;
        this.name = name;
        this.clor = clor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClor() {
        return clor;
    }

    public void setClor(String clor) {
        this.clor = clor;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}
