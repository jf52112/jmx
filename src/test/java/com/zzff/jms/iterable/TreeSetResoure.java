package com.zzff.jms.iterable;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetResoure {
    public static void main(String[] args) {
        TreeSet<String> ss=new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().length()-o2.toString().length();
            }
        });

        ss.add("a");
        ss.add("zzff");
        ss.add("bbcc");
        ss.add("cb");
        System.out.println(ss);
    }
}
