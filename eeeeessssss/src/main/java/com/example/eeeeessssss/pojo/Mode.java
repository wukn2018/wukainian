package com.example.eeeeessssss.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class Mode<T> {

    List<T> list = new ArrayList<>(  );

    public Mode(List<T> list) {
        this.list = list;
    }

    public Mode() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
