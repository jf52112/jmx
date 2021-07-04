package com.zzff.jms.others;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 线程类的几种方式
 * 1、extends Thread
 * 2、impleements Runnable
 * 3、implements Callable<Integer>
 *     总结出Runnable和Callable的区别
 */
class MyThread2 implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("coming in call "+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

/**
 * 编程思想，传参传接口，
 * Runnable->FutureTask(Callable)
 * Thread(Runnable,Name)
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"主线程运行于此"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        MyThread thread=new MyThread();
        FutureTask task=new FutureTask(thread);
        Thread t=new Thread(task,"AA");
        //Thread t2=new Thread(task,"BB");t2.start();无效，不可再次运行同样的结果，因重复利用t的结果，如需再次进入，创建FutureTask
        t.start();
        //对于get值，一般我们留出尽可能多的时间给线程进行运算，避免因为线程运算get()等待线程完成出现的阻塞
        // int  result = (Integer)task.get();
        int num=100;
        System.out.println(Thread.currentThread().getName()+"主线程运行于此"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        int  result = (Integer)task.get();
        System.out.println(Thread.currentThread().getName()+"\t 计算的总结果为"+(num+result));
    }
}
