package com.example.jpa.test;

import com.example.jpa.pojo.Persion;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 * 动态代理
 */
public class ProxyTest {

    public static void main(String[] args) {
        test01();

    }





    /**
     * 取得类加载器
     */
    public static void test01() {
        Persion persion = new Persion(  );
        System.out.println("类加载器");
        System.out.println(persion.getClass().getClassLoader().getClass().getName());
    }


















}
