package com.example.jpa.listTest;

import java.util.Stack;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class StackTest {


    public static void main(String[] args) {
        test01();

    }

    public static void test01() {
        Stack<String> st = new Stack<>();
        st.push( "1" );
        st.push( "12" );
        st.push( "123" );
        System.out.println(st.pop());

    }
 }
