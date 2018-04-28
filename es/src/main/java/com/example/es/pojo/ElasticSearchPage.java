package com.example.es.pojo;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class ElasticSearchPage<T> {
    private String scrollId;

    private long total;

    private int pageSize;

    private int pageNum;

    private T param;

    private List<T> retList;

    private List<String> scrollIds;

    public ElasticSearchPage() {
    }

    public ElasticSearchPage(String scrollId, long total, int pageSize, int pageNum, T param, List<T> retList, List<String> scrollIds) {
        this.scrollId = scrollId;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.param = param;
        this.retList = retList;
        this.scrollIds = scrollIds;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public List<T> getRetList() {
        return retList;
    }

    public void setRetList(List<T> retList) {
        this.retList = retList;
    }

    public List<String> getScrollIds() {
        return scrollIds;
    }

    public void setScrollIds(List<String> scrollIds) {
        this.scrollIds = scrollIds;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}
