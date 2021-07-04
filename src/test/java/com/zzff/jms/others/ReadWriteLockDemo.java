package com.zzff.jms.others;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile  Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();

    public void set(String key,Object value){

        rwLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);
        try {TimeUnit.MICROSECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        rwLock.writeLock().unlock();
    }
    public void get(String key){
        rwLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 正在读取"+key);
        try {TimeUnit.MICROSECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result);
        rwLock.readLock().unlock();
    }
}

/**
 * //结果显示写操作的时候必须保证原子性+独占，整个过程必须是一个完整的统一体，中间不能被分割，被打断
 * //为了保证并发量，读取资源共享应该同时进行，所以对于ReentrantLock和synchronized不满足
 * //读-读能共存
 * //读-写不能共存
 * //写-写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for (int i = 0; i <5 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.set(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <5 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            }, UUID.randomUUID().toString()).start();
        }
        System.out.println(Thread.currentThread().getName()+"主线程运行完成");
    }
}
