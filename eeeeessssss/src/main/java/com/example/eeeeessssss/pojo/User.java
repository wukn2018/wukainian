package com.example.eeeeessssss.pojo;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class User {
    private String name;
    private String address;
    private Integer age;
    private Date dateTime;
    private String title;

    public User() {
    }

    public User(String name, String address, Integer age, Date dateTime, String title) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.dateTime = dateTime;
        this.title = title;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}
