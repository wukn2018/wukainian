package com.example.jpa.pojo;

import java.io.Serializable;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class Persion implements Serializable{

    /**
    *name
    */
    private String name;

    /**
     * id
     */
    private Integer id;

    public Persion() {
    }

    public Persion(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
