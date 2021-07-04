package com.zzff.jms.jvm;

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class OOMDemo {
    public static void main(String[] args) {
        //outOfMemoryError();
        //outOfMemoryError_GC();
        //directBufferMemory();
        //unableCreateNewThread();
        metaspaceOOM();
    }
    private static void metaspaceOOM() {
        int i=0;


    }
    private static void unableCreateNewThread() {

        int i=0;
        for ( i = 0; ; i++) {
            System.out.println(i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i));
        }
    }
    private static void directBufferMemory() {
        // -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:MaxDirectMemorySize=10m
        //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        long max = VM.maxDirectMemory() / 1024 / 1024;//JVM可用的堆内存
        System.out.println(max+"MB");
        ByteBuffer.allocateDirect(11*1024*1024);//直接在本地内存分配空间
        //ByteBuffer.allocate(); 区别于allocateDirect
    }
    private static void outOfMemoryError_GC() {

        List<String> list=new ArrayList<>();
        int i=0;
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("========="+i);
            e.printStackTrace();
            throw e;
        }
    }
    private static void outOfMemoryError() {
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        //-Xmx10m  太多的对象导致堆内存溢出
        byte[] bytes=new byte[20*1024*1024];
    }
}
