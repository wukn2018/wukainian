package com.example.es.pojo;

import com.alibaba.fastjson.JSON;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class Article {

    private static final long serialVersionUID= 1L;

    private String id;
    //分类编号
    private Category category;

    // 标题
    @ESearchTypeColumn
    private String title;
    // 外部链接
    private String link;

    // 标题颜色red：红色；green：绿色；blue：蓝色；yellow：黄色；orange：橙色
    private String color;
    // 文章图片
    private String image;

    //关键字
    private String keywords;

    //描述、摘要
    @ESearchTypeColumn
    private String description;

    public Article() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}
