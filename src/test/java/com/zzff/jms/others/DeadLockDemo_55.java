package com.zzff.jms.others;
/**
 * 死锁是两个或者两个以上线程争抢资源而产生的问题
 * 导致死锁的原因：线程相互争抢被其他线程锁定的资源，系统资源不足 进行运行推进的顺序不当 资源分配不当
 *
 * 问题定位：首先使用jdk自带的jps.exe命令 ，然后使用jstack.exe中的命令
 * 输入jps -l查出运行进程，获取运行进行id
 *输入jstack id  定位问题
 */
class HoldLocalThread implements  Runnable
{
    private String lockA;
    private String lockB;

    public HoldLocalThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA)
        {
            System.out.println(Thread.currentThread().getName()+"线程持有锁"+lockA+"\t 尝试获取锁"+lockB);
            synchronized (lockB)
            {
                System.out.println(Thread.currentThread().getName()+"线程持有锁"+lockB+"\t 尝试获取锁"+lockA);
            }
        }
    }
}

public class DeadLockDemo_55 {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoldLocalThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLocalThread(lockB,lockA),"ThreadBBB").start();
    }
}
