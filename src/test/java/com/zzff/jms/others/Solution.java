package com.zzff.jms.others;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static String countAndSay(int n) {

        List arr=new ArrayList<>();
        arr.add(1);
        String str="";
        for(int i=2;i<=n;i++){
            arr=same(arr);
        }
        for(int i=0;i<arr.size();i++){
            str=str+arr.get(i);
        }
        return str;

    }
    public static List same(List m){
        int num=1;int n=0;
        boolean boo=false;
        List as=new ArrayList();
        for(int i=0;i<m.size();i++){
            if(i+1<m.size()&&m.get(i)==m.get(i+1)){
                num=num+1;
                boo=false;
            }else{
                boo=true;
            }
            if(boo){
                as.add(num);
                as.add(m.get(i));
                num=1;
            }

        }
        return as;
    }

    public static void main(String[] args) {
        String res=countAndSay(10);
        System.out.println(res);
    }
}
