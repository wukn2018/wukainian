package com.example.jpa.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */


/**
 * 如果要完成动态代理，首先定义一个InvocationHandler的接口的子类，以完成代理的具体操作
 */
public class MyProxy implements InvocationHandler {

    /**
     * 真实主题
     */
    private Object obj;


    /**
     *绑定真实操作主题
     * @param object
     * @return
     */
    public Object bind(Object object) {

        //得到代理对象
        return Proxy.newProxyInstance( object.getClass().getClassLoader(),object.getClass().getInterfaces(),this );

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //调用方法
        Object temp = method.invoke( this.obj,args );
        return temp;
    }
}

/**
 * 定义接口
 */
interface testd {
    public String talk(String name, int id);


}



class reProxy implements testd {

    @Override
    public String talk(String name, int id) {
        return "名称"+name+"-----"+"id"+id;
    }
}


/**
 * 测试
 */
class TestProxy {
    public static void main(String[] args) {
        //实例化代理类
        MyProxy m = new MyProxy();
        //绑定操作主题实现类
        testd ote = (testd) m.bind( new reProxy() );
        //调用主题方法
       String s =  ote.talk( "www",12 );
       System.out.println(s);

    }

}








