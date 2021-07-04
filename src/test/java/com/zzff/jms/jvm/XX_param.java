package com.zzff.jms.jvm;

import java.lang.ref.*;
import java.util.HashMap;
import java.util.WeakHashMap;

public class XX_param {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        //runTimeMethod();


        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void runTimeMethod() {
        int computer_he = Runtime.getRuntime().availableProcessors();
        System.out.println(computer_he);//获取核数

        long computer_xms = Runtime.getRuntime().totalMemory();
        System.out.println(computer_xms);//获取JVM初始化自定义的堆内存大小1/64
        long computer_xmx = Runtime.getRuntime().maxMemory();
        System.out.println(computer_xmx);//获取JVM初始化自定义的堆内存最大值1/4
    }
}
