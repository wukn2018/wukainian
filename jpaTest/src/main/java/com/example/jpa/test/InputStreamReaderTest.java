package com.example.jpa.test;

import java.io.*;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 * 包装流
 */
public class InputStreamReaderTest {


    public static void main(String[] args) throws Exception {
        test02();
    }


    /**
     * 将字符流变为字节流
     */
    public static void test01() throws Exception {

        String str = "d:" + File.separator + "test.txt";
        File file = new File( str );
        //将字符流转化为字节流
        Writer writer = new OutputStreamWriter( new FileOutputStream( file ) );
        //使用字符流输出
        writer.write( "wwwwwwwwwww" );
        writer.close();
    }

    /**
     * 将字节流转化为字符流
     * @throws Exception
     */
    public static void test02() throws Exception {

        String str = "d:" + File.separator + "test.txt";
        File file = new File( str );
        Reader reader = new InputStreamReader( new FileInputStream( file ) );
        char[] b = new char[1024];
        int read = reader.read( b );
        reader.close();
        System.out.println(new String( b,0,read ));
    }



    /**
     * 内存操作流
     * @throws Exception
     */
    public static void test03() throws Exception {

        String str = "wertyoiuytrew";
        //向内存中输入内容
        ByteArrayInputStream b = new ByteArrayInputStream( str.getBytes() );
        //读取内存中的内容
        ByteArrayOutputStream bb = new ByteArrayOutputStream(  );
        int i = 0;
        while((i = b.read()) != -1) {
            //将存入的长度变为char
            char j = (char) i;
            //转化的char变为大写
            bb.write( Character.toLowerCase( j ) );
        }
        String s = bb.toString();

        try {
            bb.close();
            b.close();
        } catch (IOException e) {
            e.printStackTrace( );
        }
        System.out.println(s);
    }











}
