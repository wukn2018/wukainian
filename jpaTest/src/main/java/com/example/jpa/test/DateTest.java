package com.example.jpa.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date( );
        SimpleDateFormat s = new SimpleDateFormat( "yyyy-MM-dd,HH-mm-ss" );
        String s1= s.format( date );
        System.out.println(s1);
    }
}
