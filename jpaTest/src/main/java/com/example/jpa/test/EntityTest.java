package com.example.jpa.test;

import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */

//具体任务类
public class EntityTest extends TimerTask{


    @Override
    public void run() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");;
        System.out.println("当前时间----"+simpleDateFormat.format(new Date()));
    }
}
//对定时任务进行调度
class df {

    public static void main(String[] args) {
        Timer timer = new Timer();
        EntityTest entityTest = new EntityTest();
        timer.schedule(entityTest,1000,200);
    }

}







