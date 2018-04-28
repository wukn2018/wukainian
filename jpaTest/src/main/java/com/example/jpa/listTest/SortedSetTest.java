package com.example.jpa.listTest;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 *集合的内容输出
 */
public class SortedSetTest {


    public static void main(String[] args) {
        test10();
    }




    public static void test01() {
        SortedSet sort = new TreeSet(  );
        sort.add( "1" );
        sort.add( "2" );
        sort.add( "3" );
        sort.add( "4" );
        sort.add( "1" );
        System.out.println(sort.first());
        System.out.println(sort.last());
    }


    /**
     * 使用Iterator输出数据
     */
    public  static void test02() {
        List<String> list = new ArrayList<>(  );
        list.add( "1" );
        list.add( "2" );
        list.add( "3" );
        //通过Conllection中的iterator（）方法实例化一个Iterator对象
        Iterator<String> iterator = list.iterator();
        //判断是否存在数据，
        while (iterator.hasNext()) {
            //存在就输出
            System.out.println( iterator.next( ) );
        }
    }


    /**
     * 使用Iterator删除数据
     */
    public static void test03() {
        List<String> list = new ArrayList(  );
        list.add( "1" );
        list.add( "2" );
        list.add( "3" );
        list.add( "4" );
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if("2".equals( s )) {
                iterator.remove();
                //如果用list。remove（）来删除，虽然内容能删除，但是输出或报错
            }else {
                System.out.println(s);
            }

        }

    }


    /**
     * ListIterator  双向输出操作
     *
     * ListIterator接口只能通过List接口实例化
     */
    public static void test04() {
        List<String> list = new ArrayList(  );
        list.add( "1" );
        list.add( "2" );
        list.add( "3" );
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            //从前到后输出
            String s = iterator.next();
            System.out.println(s);
        }

    }


    /**
     *foreach输出
     */
    public static void test05() {
        List<String> list = new ArrayList(  );
        list.add( "1" );
        list.add( "2" );
        list.add( "3" );
        for(String ss : list) {
            System.out.println(ss);
        }
    }


    /**
     *判断map中的key或者value是否存在
     */
    public static void test06() {
        Map<String,String> m = new HashMap(  );
        m.put( "1","a");
        m.put( "2","b");
        m.put( "3","c");
        //根据key获取value
        System.out.println(m.get( "1" ));

        if(m.containsKey( "1" )) {
            System.out.println("存在对应的key");
        }else {
            System.out.println("不存在对应的key");
        }

        if(m.containsValue( "a" )) {
            System.out.println("存在对应的value");

        }else {
            System.out.println("不存在对应的value");
        }
    }


    /**
     * 输出全部的key
     *Map中的keySet（）方法可以将map中的key变为一个set集合
     *
     *
     */
    public static void test07() {
        Map<String,String> m = new HashMap(  );
        m.put( "1","a");
        m.put( "2","b");
        m.put( "3","c");

        //将map中的key存在set集合中
        Set<String> set = m.keySet();
        //迭代这个set集合
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String skey = iterator.next();
            System.out.println(m.get( skey ));
        }
    }



    /**
     * 输出全部的value
     *values（）方法可以将map中的value变为一个Collection类型集合
     *
     *
     */
    public static void test08() {
        Map<String,String> m = new HashMap(  );
        m.put( "1","a");
        m.put( "2","b");
        m.put( "3","c");

        //得到所有的value
        Collection<String> c = m.values();
        //迭代所有的value
        Iterator<String> iterator = c.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }
    }




    /**
     *
     *map遍历
     *
     */
    public static void test09() {
        Map<String,String> m = new HashMap(  );
        m.put( "1","a");
        m.put( "2","b");
        m.put( "3","c");
        //Map.Entry是一个Entry对象，一个Etry对象保存的是key  value类型的数据
        //m.entrySet是将一个map集合中的所有的Entry对象保存在set集合中
        for(Map.Entry mm : m.entrySet()) {
            System.out.println(mm.getKey()+"-----"+mm.getValue());
        }
    }


    /**
     * TreeMap的功能是将key排序
     */
    public static void test10() {
        Map<String,String> m = new TreeMap<>(  );
        m.put( "1","a");
        m.put( "2","b");
        m.put( "3","c");
        //得到所有的key
        Set<String> set = m.keySet();
        //迭代set
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key+"---"+m.get( key ));
        }
    }





















}
