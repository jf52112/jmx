package com.zzff.jms.others;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

/**
 * 第四种获取线程的方式：线程池
 * 底层为实现为ThreadPoolExecutor类
 * 线程池做的工作主要是控制运行线程的数量，处理过程种将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量的线程排队等候，等其它线程执行完毕，再从队列中取出任务来执行。
 * 他的主要特点为：线程复用：控制最大并发数：线程管理。
 * 第一：降低资源消耗，通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度，当任务到达时，任务可以不需要的等到线程创建就能立即执行
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和控制
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());//获取电脑核数

        //生产上的调用
        ExecutorService threadPool=new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                /*new ThreadPoolExecutor.AbortPolicy()*/
                /*new ThreadPoolExecutor.CallerRunsPolicy()*/
                /*new ThreadPoolExecutor.DiscardOldestPolicy()*/
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 1; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 执行业务");
                });
                //try {TimeUnit.MICROSECONDS.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    //学习上的调用
    private static void executorsTest() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一个池5个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一个池1个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一个池N个线程
        //模拟10个用户来办理业务，每个用户就是一个自外部的请求线程

        try {
            for (int i = 1; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 执行业务");
                });
                try {
                    TimeUnit.MICROSECONDS.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
