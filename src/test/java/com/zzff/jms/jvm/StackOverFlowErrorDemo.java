package com.zzff.jms.jvm;

public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        stackOverFlowError();
    }
    public static void stackOverFlowError(){
        //深度的方法调用导致出不来
        stackOverFlowError();//Exception in thread "main" java.lang.StackOverflowError
    }
}
