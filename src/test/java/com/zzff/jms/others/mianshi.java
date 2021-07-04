package com.zzff.jms.others;

import org.junit.Test;

public class mianshi {
    @Test
    public void test1(){
        int i=1;
        i=i++;//i=1
        int j=i++;
        int k=i+ ++i*i++;//或者int k=i+ i++*i++; k=8
        System.out.println("i= "+i);
        System.out.println("j= "+j);
        System.out.println("k= "+k);
        //i= 4        j= 1        k= 11
    }
    @Test//写一个singleton示例
    public void test2(){
        //

    }
    /*饿汉式
    private static final mianshi ms=new mianshi();
    private mianshi (){
    }*/
    /*饿汉式枚举
    public enum Signleton{
    INSTANCE
    }*/

    private static volatile mianshi ms;
    private mianshi(){
    }
    public mianshi getms(){
        if(ms==null){
            synchronized (ms){
                if(ms==null){
                    ms= new mianshi();
                }
            }
        }
        return ms;
    }
}
