package com.zzff.jms.others;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static final int CONSTANT_NUM=6;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count=new CountDownLatch(CONSTANT_NUM);
        for (int i = 1; i <7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                count.countDown();
            }, com.zzff.jms.others.CountryEnum.forEach_CountryEnum(i).getMess()).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t ********秦帝国，统一华夏");
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch count=new CountDownLatch(CONSTANT_NUM);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                count.countDown();
            },String.valueOf(i)).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t ********班长最后关门走人");
    }
}
