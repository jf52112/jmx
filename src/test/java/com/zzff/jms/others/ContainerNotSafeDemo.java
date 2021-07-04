package com.zzff.jms.others;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap();
//        Map<String, String> map=Collections.synchronizedMap(new HashMap());
        Map<String, String> map=new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();

        }
    }
    public void testSet(){
//        Set<String> set =new HashSet<>();
//        Set<String> set =Collections.synchronizedSet(new HashSet<>());
        Set<String> set =new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));

                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    public  void testArray(){
        //List<String> list= new ArrayList<>();//java.util.ConcurrentModificationException
        //List<String> list=new Vector<>();
        //List<String> list= Collections.synchronizedList(new ArrayList<>());
        List<String> list=new  CopyOnWriteArrayList();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
