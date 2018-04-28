package com.example.jpa.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class PageResult implements Serializable{


    /**
     * 当前第几页
     */
    private Integer pageNo;


    /**
     * 每页显示多少条
     */
    private Integer pageSize;


    /**
     * 总条数
     */
    private long totalSize;


    public PageResult() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this);
    }
}
