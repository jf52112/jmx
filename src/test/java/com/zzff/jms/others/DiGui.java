package com.zzff.jms.others;

/**
 * 有N阶台阶，一次只能走1步或2步，共有多少种走法
 */
public class DiGui {
    public static void main(String[] args) {
        Long start=System.currentTimeMillis();
        System.out.println(getBu(40));
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static int getBu(int n){
        int sum=0;
        if(n<1){
            throw new IllegalArgumentException("值不能小于1");
        }if(n==1||n==2){
            return n;
        }else{
            sum=getBu(n-1)+getBu(n-2);
        }
        return sum;
    }
}
