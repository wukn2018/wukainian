package com.example.jpa.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 * 操作文件内容
 *
 */
public class RandomAccessFileTest {


    public static void main(String[] args) throws Exception {
        String s = "d:" + File.separator + "text.txt";
        File file = new File( s );
        RandomAccessFile randomAccessFile = null;
        randomAccessFile = new RandomAccessFile( file,"ww" );
        String name = null;
        int age = 0;
        name = "qq";
        age = 1;
        randomAccessFile.writeBytes( name );
        randomAccessFile.close();
        //准备空间
        byte[] bytes = new byte[10];
        randomAccessFile.skipBytes( 12 );
        for(int i = 0;i<bytes.length;++i) {
            //循环读出数组的内容
            System.out.println(bytes[i] = randomAccessFile.readByte());
        }
    }
}
