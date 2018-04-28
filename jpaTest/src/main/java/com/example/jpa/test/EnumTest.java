package com.example.jpa.test;

import com.example.jpa.wknEnum.MyEnum;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class EnumTest {
    public static void main(String[] args) {
        test01();
    }


    public static void test01() {
        MyEnum myEnum = MyEnum.WKL;
        System.out.println(myEnum);
    }
}
