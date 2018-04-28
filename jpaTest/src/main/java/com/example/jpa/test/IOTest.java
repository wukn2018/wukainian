package com.example.jpa.test;

import java.lang.String;
import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class IOTest {


    public static void main(String[] args) throws Exception {
        FileWriter2();
        //FileWriter();
        //FileinPutStreamTest();
       // FileOutputStreamTestadd();
        //isSpecialChardelete();
//        System.out.println("ssss");
//        System.out.println(isSpecialChar("aa"));


    }


    /**
     * 向文件中写入数据
     *
     *
     * FileOutputStreamTest是一个输出操作类，是utputStream的子类
     * 使用FileOutputStream时需要传入文件的路径
     */
    public static void FileOutputStreamTest() throws Exception {
        String s = "d:" + File.separator + "test.txt";
        //首先通过File找到文件
        File file = new File( s );
        //通过子类FileOutPutStream实例化OutPutStream
        OutputStream o = new FileOutputStream( file );
        //准备一个字符串
        String  ss = "HelloWord";
        byte[] bytes = ss.getBytes();
        o.write( bytes );
        //关闭流
        o.close();
    }


    /**
     * 追加新内容
     * 在FileOutPutStream中的boolean改为true
     *
     *
     */
    public static void FileOutputStreamTestadd() throws Exception {
        String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        OutputStream o = new FileOutputStream( file,true );
        String st = "dfgt";
        String s1 = "\t\n sddddd";
        byte[] bytes = st.getBytes();
        o.write( bytes );
        o.close();
    }


    /**
     * 文件输入流，
     *
     * FileInPutStream是将文件数据读入到程序
     *
     */
    public static void FileinPutStreamTest() throws Exception{
        java.lang.String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        InputStream in = new FileInputStream( file );
        //装备一个数组存储文件的数据
        byte[] bytes = new byte[1024];
        //将数据存到数组，返回存入数据的长度
        int ii = in.read( bytes );
        in.close();
        System.out.println(new String( bytes,0,ii ));
    }

    /**
     * 文件输入流，
     *
     * File有一个方法length可以获取这个文件的大小
     *
     */
    public static void FileinPutStreamTest2() throws Exception{
        String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        in.read( bytes );
        in.close();
        System.out.println(new String( bytes ));

    }

    /**
     * 文件输入流，
     *
     * 通过判断
     *
     */
    public static void FileinPutStreamTest3() throws Exception{
        String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        int tem = 0;
        while ((tem = in.read( bytes )) != -1) {
            bytes[len] = (byte) tem;
            len++;
        }
        in.close();
        System.out.println(new String( bytes,0,len ));


    }


    /**
     * 字符输出流
     *
     */
    public static void FileWriter() throws Exception{

        String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        Writer writer = new FileWriter( file );
        String s1 = "ssssssssss";
        writer.write( s1 );
        writer.close();
    }




    /**
     * 字节输入流
     *
     */
    public static void FileWriter2() throws Exception{
        String s = "d:" + File.separator + "test.txt";
        File file = new File( s );
        OutputStream out = new FileOutputStream( file );
        //写操作
        String str = "dfghjkloiuy";
        byte[] b = str.getBytes();
        //输出操作
        out.write( b );
        //out.close();
    }




    /**
     * 强制性清理缓冲区
     *
     */
    public static void FileWriter3() throws Exception{
      String str = "d:" + File.separator + "test.txt";
      File file = new File( str );
      Writer w = new FileWriter( file );
      String s = "ffeg";
      w.write(s);
      //刷新缓冲区
      w.flush();
      //w.close()
    }















    /**
     * 特殊字符校验
     * @param str
     * @return
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 去掉字符串中的特殊字符
     */
    public static String  isSpecialChardelete() {
        String str = "我的名字测试";
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.replaceAll("").trim());
        return m.replaceAll("").trim();
    }


}
