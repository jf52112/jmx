package com.zzff.jms.others;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列，不用对队列进行wait、notity等操作
 */

class MyResource{
    private volatile  boolean FLAG=true;
    private AtomicInteger num=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws  Exception{
        String data=null;
        boolean off=false;
        while (FLAG){
            data = num.incrementAndGet()+"";
            off=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(off){
                System.out.println(Thread.currentThread().getName()+"\t 数据插入成功"+data);
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 数据插入失败"+data);
            }
            //TimeUnit.SECONDS.sleep(1);
        }
    }
    public void myConsumer() throws  Exception{
        String poll=null;

        while (FLAG){
            poll = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(poll==null||poll.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t 数据消费失败");
                return ;
            }
            System.out.println(Thread.currentThread().getName()+"\t 数据消费成功"+poll);
            System.out.println();
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
        }
    }
    public void getStatus(){
        System.out.println(Thread.currentThread().getName()+"\t 主线程叫停");
        FLAG=false;
    }
}


public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws  Exception{
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(10);
        //BlockingQueue synchronousQueue=new SynchronousQueue();
        MyResource resource = new MyResource(blockingQueue);

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产者线程启动");
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费者线程启动");
            try {
                resource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        TimeUnit.SECONDS.sleep(5);
        resource.getStatus();
    }
}

