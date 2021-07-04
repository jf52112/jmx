package com.zzff.jms.others;
public class k2 extends k1 {
    private int i=test();
    private  static  int j=method();
    static {System.out.print("(6)");
    }
    k2(){//super();//写或不写都存在，在子类的构造器中一定会调用父类的构造器
        System.out.print("(7)");
    }
    {System.out.print("(8)");
    }
    public int test(){
        System.out.print("(9)");
        return 1;
    }
    public static int method(){
        System.out.print("(10)");
        return 1;
    }//结果(5)(1)(10)(6)(9)(3)(2)(9)(8)(7)  (9)(3)(2)(9)(8)(7)
    public static void main(String[] args) {
        k1 S1=new k2();
        System.out.println();
        k1 S2=new k2();
    }
}
