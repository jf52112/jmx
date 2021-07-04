package com.zzff.jms.others;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestCAS {

    public static void main(String[] args) {
        AtomicInteger ai=new AtomicInteger(3);
        int res = ai.getAndIncrement();
        ai.compareAndSet(3,5);
        AtomicStampedReference<Integer> a=new AtomicStampedReference<>(1,1);
        a.compareAndSet(1,1,1,1);
    }
    
}
