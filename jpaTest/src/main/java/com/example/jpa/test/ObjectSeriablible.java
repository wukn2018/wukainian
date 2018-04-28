package com.example.jpa.test;

import java.io.*;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class ObjectSeriablible {


    public static void main(String[] args) {

    }

    /**
     * 对象输出流
     *
     *
     * 一个对象如果要进行输出，则必须使用ObjectOutputStream类
     * ObjectOutputStream是OutputStream的子类
     */
    public void test01() throws Exception{
        String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        //文件输出流
        OutputStream out = new FileOutputStream( file );
        //为对象输出流实例化
        ObjectOutputStream oo = new ObjectOutputStream( out );
        //保存对到文件
        oo.writeObject( new SerializableTest("D",1));
        oo.close();
    }






    /**
     * 对象输入流
     *
     *
     * 一个对象如果要进行输入，则必须使用ObjectintputStream类
     * ObjectinputStream是inputStream的子类
     */
    public void test02() throws Exception{
        String str = "d:" + File.separator + "ttest.txt";
        File file = new File( str );
        //文件输入流
        InputStream in = new FileInputStream( file );
        //为对象输入流实例化
        ObjectInputStream oo = new ObjectInputStream( in );
        Object o =  oo.readObject();
        oo.close();
        //读取对象
        System.out.println(o);


    }













































}
