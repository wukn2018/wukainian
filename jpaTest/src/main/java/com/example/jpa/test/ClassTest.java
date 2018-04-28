package com.example.jpa.test;

import com.example.jpa.pojo.Persion;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 * 反射
 */
public class ClassTest {

    public static void main(String[] args)throws Exception {
        test07();

    }


    /**
     * 无参
     */
    public static void test02(){
        Class<?> c = null;
        try {
            c = Class.forName( "com.example.jpa.pojo.Persion" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        }

        Persion persion = null;
        try {
            //实例化Persion
            persion = (Persion) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace( );
        } catch (IllegalAccessException e) {
            e.printStackTrace( );
        }
        persion.setId( 12 );
        persion.setName( "ww" );
        System.out.println(persion);
    }


    /**
     * 调用有参的构造方法实例化对象
     */
    public static void test03(){
        Class<?> c = null;
        try {
            c = Class.forName( "com.example.jpa.pojo.Persion" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        }
        Persion persion = null;
        //申明一个构造方法数组
        Constructor<?>[] cons = null;
        //得到所有的构造方法
        cons = c.getConstructors();
        try {
            //向构造方法中传递参数，并实例化对象
            persion = (Persion) cons[0].newInstance( 12,"ss" );
        } catch (Exception e) {
            e.printStackTrace( );
        }
        System.out.println(persion);
    }


    /**
     * 取得类中的全部接口
     */
    public static void test04(){
        Class<?> c = null;
        try {
            //实例化Class
            c = Class.forName( "com.example.jpa.test.Wknk" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        }
        //取得实现的全部接口
       Class<?>[] cc =  c.getInterfaces();
        for(int i = 0;i<cc.length;++i) {
            System.out.println(cc[i].getName());
        }
    }

    /**
     * 得到类中的全部方法
     */
    public static void test05() {
        Class<?> c = null;
        try {
            //得到Class的实例对象
            c = Class.forName( "com.example.jpa.test.IOTest" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        }
        //得到类的全部方法
        Method[] methods = c.getMethods();
        for(int i = 0;i<methods.length;++i) {
            //得到方法的返回值类型
           Class<?> c1 =  methods[i].getReturnType();

           //得到全部的参数类型
           Class<?>[] c2 = methods[i].getParameterTypes();

           //得到方法的修饰符
           int c3 = methods[i].getModifiers();

           //还原修饰符
           System.out.println( Modifier.toString( c3 ));

           System.out.println("-----------------------");

           //得到方法名称
           System.out.println(c1.getName());

           System.out.println(methods[i].getName());

        }

    }


    /**
     * 通过反射调用类中的方法
     */
    public static void test06() {
        Class<?> c = null;
        try {
            //实例化Class类。指定需要加载的类的路径
            c = Class.forName( "com.example.jpa.test.FileTest" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        }
        try {
            //调取某个类中的方法指定调取方法的名称
            Method method = c.getMethod( "add" );
            //调取这个类的方法是需要实例化这个类
            method.invoke( c.newInstance() );
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }


    /**
     * 通过反射调取某个类的方法，并向这个方法传递参数
     */
    public static void test07() {
        Class<?> c = null;
        try {
            c = Class.forName( "com.example.jpa.test.FileTest" );
        } catch (Exception e) {
            e.printStackTrace( );
        }
        try {
            //得到指定的方法,并向该方法传递参数类型
            Method mm= c.getMethod( "show" ,String.class);
            //调用该方法前先实例化该类，然后传入改方法的入参
            String s1 = (String) mm.invoke( c.newInstance(),"sss" );
            System.out.println(s1);
        } catch (Exception e) {
            e.printStackTrace( );
        }
        String str = null;

    }


    /**
     * 调用get set 方法
     */
    public static void test08() {
        Class<?> c = null;
        try {
            c = Class.forName( "com.example.jpa.pojo.Persion" );
        } catch (Exception e) {
            e.printStackTrace( );
        }
        Object obj = null;
        try {
            obj =  c.newInstance();
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }


















}