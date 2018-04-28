package com.example.jpa.test;

import java.io.*;
import java.util.ArrayList;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
//对象序列化
public class tranientTest implements Serializable{

    /**
     *  //使用tranient表示这个属性不会被序列化
     */
    public transient String name;
    /**
     * id
     */
    private Integer id;

    public tranientTest(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "tranientTest{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

class Df{
    public static void main(String[] args) throws Exception {

    }

    /**
     * 系列化
     * @throws Exception
     */
    public void test01() throws Exception {
        String str = "d:" + File.separator + "test.txt";
        File file = new File( str );
        //文件输出流
        OutputStream ou = new FileOutputStream( file );
        //对象输出流
        ObjectOutputStream oo = new ObjectOutputStream( ou );
       // oo.write(new tranientTest("we",12));
        oo.close();
    }

    /**
     * 反序列化
     * @throws Exception
     */
    public void test02() throws Exception {
        String str = "d:" + File.separator + "test.txt";
        File file = new File( str );
        //文件输出流
        InputStream ou = new FileInputStream( file );
        //对象输出流
        ObjectInputStream oo = new ObjectInputStream( ou );
        Object o = oo.readObject();
        oo.close();
        System.out.println(o);
        System.out.println("你是");
    }

}