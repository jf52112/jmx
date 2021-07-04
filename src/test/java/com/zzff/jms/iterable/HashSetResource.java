package com.zzff.jms.iterable;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class HashSetResource {

    public static void main(String[] args) {
        Set<String> hashSet=new HashSet<>();
        hashSet.add("java");
        hashSet.add("html");

        LinkedHashSet linkedHashSet=new LinkedHashSet(1);
        LinkedHashSet linkedHashSets=new LinkedHashSet();

        linkedHashSet.add("zzff");



    }

}
