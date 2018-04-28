package com.example.es.pojo;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class User {

    private Integer id;
    private String name;
    private String address;
    private Integer sex;
    private Date publishDate;

    public User() {
    }

    public User(Integer id, String name, String address, Integer sex) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}
