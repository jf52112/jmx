package com.zzff.jms.others;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟增1和减一交替操作，
 *
 * 1    线程      操作（方法）      资源类
 * 2    判断      干活              通知
 * 3    防止虚假唤醒机制
 */
class ShareData{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void increment() throws Exception{
        lock.lock();
        System.out.println("increment加锁"+lock);
        try{
            while(number!=0){
                System.out.println("while_in循环"+lock);
                condition.await();
            }
            number=number+1;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
            System.out.println("increment解锁"+lock);
        }
    }
    public void decrement() throws  Exception{
        lock.lock();
        System.out.println("decrement加锁"+lock);
        try{
            while(number!=1){
                System.out.println("while_de循环"+lock);
                condition.await();
            }
            number=number-1;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
            System.out.println("decrement解锁"+lock);
        }
    }

}
class ShareData_sync{
    private int number=1;
    public  synchronized void increment() throws Exception{
        try{
            while(number!=1){
                this.wait();
            }
            number=number+1;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            this.notifyAll();
        }catch (Exception e){

        }
    }
    public synchronized void decrement() throws  Exception{
        try{
            while(number!=2){
                this.wait();
            }
            number=number-1;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            this.notifyAll();
        }catch (Exception e){

        }
    }

}

public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData=new ShareData();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"BB").start();
    }
}
