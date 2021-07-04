package com.zzff.jms.others;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendMsg() throws Exception{
        System.out.println(Thread.currentThread().getName()+" 进入 invoked sendMsg()方法");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+" 进入 KKKinvoked sendEmail()方法");
    }

    @Override
    public void run() {
       get();
    }

    Lock lock=new ReentrantLock();
    public void get(){
        try{
            lock.lock();
            //lock.lock();
            System.out.println(Thread.currentThread().getName()+" 进入 KKKinvoked get()方法");
            set();
            sendMsg();
        }catch(Exception io){
        } finally{
            lock.unlock();
            //lock.unlock();
        }
    }
    public void set(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 进入 KKKinvoked set()方法");
        }finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo {

    public static void main(String[] args) {

        Phone phone=new Phone();
        new Thread(()->{
            try {
                phone.sendMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        Thread thread=new Thread(phone,"t3");
        Thread threads=new Thread(phone,"t4");
        thread.start();
        threads.start();
    }

}
