package com.example.es.pojo;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public @interface ESearchTypeColumn {

    /**
     * 字段类型
     * @return
     */
    String type() default "string";

    /**
     * 是否分词
     * @return
     */
    boolean analyze() default false;

}
