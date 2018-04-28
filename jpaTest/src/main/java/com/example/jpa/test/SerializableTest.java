package com.example.jpa.test;

import java.io.*;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class SerializableTest implements Externalizable{

    /**
    *name
    */
    private String name;

    /**
     * in
     */
    private Integer id;

    public SerializableTest() {
    }

    public SerializableTest(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    /** 重写此方法，根据需要保存需要的内容，序列化
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject( this.name );
        out.writeObject( id );
    }

    /**
     *重写此方法，根据需要读取内容，反序列化
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String) in.readObject();
        this.id = (Integer) in.readObject();
    }
}
