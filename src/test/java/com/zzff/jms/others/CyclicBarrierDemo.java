package com.zzff.jms.others;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
//循环栅栏,只有线程到达指定的数量才能够往下执行
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cb=new CyclicBarrier(7,()->{System.out.println("人到齐，可以开始开会");});
        for (int i = 1; i <=7 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 会议室，第"+tempInt+"人到");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }

}
