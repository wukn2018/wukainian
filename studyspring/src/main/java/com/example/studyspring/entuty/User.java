package com.example.studyspring.entuty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    // 构造函数
    // get/set方法。。。
}
