package com.example.jpa.test;

import com.example.jpa.pojo.Persion;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 *
 * 文件操作类
 */
public class FileTest {

    public static void main(String[] args) {

        boolean  b = test05();
        System.out.println(b);
    }


    /**
     * 创建文件
     */
    public void add() {
        File file = new File( "d:\\test.txt" );
        try {
            file.createNewFile();
            /**
             *       /
             */
            System.out.println(File.pathSeparator);
            /**
             *       ;
             */
            System.out.println(File.separator);
        } catch (IOException e) {
            e.printStackTrace( );
        }
    }


    /**
     * 删除指定文件
     */
    public void delete() {
        String name = "d:"+File.separator+"test.txt";
        File file = new File(name);
        //判断文件是否存在
        if(file.exists()){
            file.delete();
        }
    }


    /**
     * 判断是否存在文件，
     */
    public void shoed() {
        String fileName = "d:" + File.separator + "wkn.txt";
        File file = new File( fileName );
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace( );
            }
        }else {
            file.delete();
        }
    }


    /**
     * 创建文件夹
     */
    public static void df() {
        String str = "d:" + File.separator + "wkn";
        File file = new File( str );
        file.mkdir();
    }


    /**
     * 列出指定目录的全部文件名称
     */
    @Test
    public void dh() {
        String s1 = "d:" + File.separator;
        File file = new File( s1 );
        String[] strings = file.list();
        for(int i = 0;i<strings.length;++i) {
            System.out.println(strings[i]);
        }
    }




    /**
     * 列出指定目录的全部文件路径
     */
    @Test
    public void dh1() {
        String s1 = "d:" + File.separator;
        File file = new File( s1 );
        File[] strings = file.listFiles();
        for(int i = 0;i<strings.length;++i) {
            System.out.println(strings[i]);
        }
    }


    /**
     * 判断一个路径是否是目录
     *
     *
     */
    @Test
    public void dfg() {
        String s2 = "d:" + File.separator;
        File file = new File( s2 );
        if(file.isDirectory()) {
            System.out.println(file.getPath()+"是一个目录");
        }

    }


    /**
     *
     */
    public void fgt() {
        String s3 = "d:" + File.separator;
        File file = new File( s3 );
        if(file != null) {
            if(file.isDirectory()) {
                File[] file1 = file.listFiles();
                if(file1 != null) {
                    for(int i = 0; i<file1.length;++i) {
                        System.out.println(file1[i]);
                    }
                }
            }
        }else {
            System.out.println(file);

        }

    }





    public static void test01() {
        Integer i = null;
        Persion persion = new Persion(  );
        persion.setId( 12 );
        persion.setName( String.valueOf( i ) );
        System.out.println(persion);

        //String转Integer
        String str = "dd";
        if(null != str && str.length()>0 ){
            Integer.parseInt( str );
        }




    }













    public String show(String string){
        System.out.println("-----"+string);


        return string;
    }


    public static void test02(){
        Date date = new Date( );
        SimpleDateFormat s = new SimpleDateFormat( "yyyy-MM-dd,HH-mm-ss" );
        String s1= s.format( date );
        System.out.println(s1);
    }




    public static void test03(){
        String s = UUID.randomUUID().toString();
        System.out.println(s);
    }
    public static void test04(){
       String s = "d:" + File.separator + "testFile";
       File file = new File( s );
       file.delete();

    }



    public static boolean test05() {
        boolean b = 1<2;
        String s = "offsssSale";
            if(b && s.equals( "offSale" )) {
                System.out.println(b+"2222");
                return true;
        }
        return false;
    }





    private void tese06() {
        Map<String,String> m = new HashMap(  );
        m.put( "1","a");
        m.put( "2","b");
        m.put( "3","c");
        //Map.Entry是一个Entry对象，一个Etry对象保存的是key  value类型的数据
        //m.entrySet是将一个map集合中的所有的Entry对象保存在set集合中
        for(Map.Entry mm : m.entrySet()) {
        }
    }


}
