package com.zzff.jms.others;
/**
 * 有N阶台阶，一次只能走1步或2步，共有多少种走法
 */
public class DieDai {
    public static void main(String[] args) {
        Long start=System.currentTimeMillis();
        System.out.println(getBu(100));
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static int getBu(int n){
        int sum=0;
        if(n<1){
            throw new IllegalArgumentException("值不能小于1");
        }if(n==1||n==2){
            return n;
        }
        int one=2;//初始化为走到第二级台阶的走法
        int two=1;//初始化为走到第一级台阶的走法
        for(int i=3;i<=n;i++){
            sum=(one+two)%1000000007;
            two=one%1000000007;
            one=sum%1000000007;

        }
        return sum;
    }
}
