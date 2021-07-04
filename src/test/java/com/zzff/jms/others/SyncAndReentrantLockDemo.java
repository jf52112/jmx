package com.zzff.jms.others;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * //AA 5  BB 10 CC 15
 * 来10轮
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource=new ShareResource();

            new Thread(()->{
                for (int i = 0; i <10 ; i++) {
                    shareResource.printAA();
                }
            },"AA").start();

            new Thread(()->{
                for (int i = 0; i <10 ; i++) {
                    shareResource.printBB();
                }

            },"BB").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.printCC();
            }

        },"CC").start();

    }

}
class ShareResource{
    private int number =1;
    Lock lock=new ReentrantLock();
    Condition conditionA=lock.newCondition();
    Condition conditionB=lock.newCondition();
    Condition conditionC=lock.newCondition();
    public void printAA(){
        lock.lock();
        try{
            while(number!=1){
                try {
                    conditionA.await();
                }catch (Exception e){

                }
            }
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t   打印AA"+i);
            }
            number=2;
            conditionB.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
    public void printBB(){
        lock.lock();
        try{
            while(number!=2){
                try {
                    conditionB.await();
                }catch (Exception e){

                }
            }
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t   打印BB"+i);
            }
            number=3;
            conditionC.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
    public void printCC(){
        lock.lock();
        try{
            while(number!=3){
                try {
                    conditionC.await();
                }catch (Exception e){

                }
            }
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t   打印CC"+i);
            }
            number=1;
            conditionA.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}