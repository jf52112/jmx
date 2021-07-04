package com.zzff.jms.others;
/**
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器（或<init>）它表示是正在创建的对象，根据new xx()，
 * 所以它的父类test()执行的是子类重写的代码（面向对象多态）
 */
public class k1 {
    private int i=test();
    private  static  int j=method();
    static {
        System.out.print("(1)");
    }
    k1(){System.out.print("(2)");
    }
    {System.out.print("(3)");
    }
    public int test(){
        System.out.print("(4)");
        return 1;
    }
    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}
