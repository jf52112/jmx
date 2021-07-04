package com.zzff.jms.others;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * BlockQueue是Collection下的接口，与List同级别
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        try {
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));//false等2s，如果不能获取返回退出
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void theThirdGroup() {
        //阻塞
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        try {
            blockingQueue.put("a");
            blockingQueue.put("a");
            blockingQueue.put("a");
            //blockingQueue.put("a");//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void theSecondGroup() {
        //特殊值
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//false

        System.out.println(blockingQueue.peek());//a

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null
    }

    private static void theFirstGroup() {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        /**
         * 第一组
         * add()添加元素 remove()移除
         */
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //blockingQueue.add("d");//Exception in thread "main" java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//Exception in thread "main" java.util.NoSuchElementException
    }
}
