package com.zzff.jms.jvm;

/**
 * sun.misc.Launcher  jdk下rt.jar下的jvm的入口应用启动类
 *
 * getClass();获得类类型，及反射
 * ================================||======================================
 * 类装载器ClassLoader负责加载class文件，class文件在文件开头有特定的文件标示，
 * 将class文件字节码内容加载到内存中，并将这些内容转换成方法区中的运行时数据结构并且ClassLoader只负责class文件的加载
 * 至于它是否可以运行，则有Execution Engine决定
 * ================================||======================================
 * 类加载器总共是3+1种
 * jvm自带三种类加载器：
 * 1、启动类加载器（Bootstrap） c++所写，java获得就会显示null
 * 2、扩展类加载器（Extension) Java ExtClassLoader
 * 3、应用程序加载器（AppClassLoader)也叫系统类加载器，加载当前应用的classpath的所有类
 *
 * 4、用户自定义加载器
 * Java.lang.ClassLoader的子类，用户可以定制类的加载器
 *
 *
 *   Bootstrap($JAVAHOME/jre/lib/rt.jar)
 *      ↑
 *   Extension($JAVAHOME/jre/lib/ext/*.jar)可参考javax包
 *      ↑
 *  AppClassLoader($CLASSPATH)
 *      ↑
 * ----------------
 *      ↑
 * User-Defined ClassLoader
 * ================================||======================================
 *
 * 类装载器将.class 装载并初始化变成大的Class，表示开始按照模板装载实例
 *================================||======================================
 * 双亲委派机制加载
 * 当一个类收到了类加载请求，它首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成，每一个层次类加载器都是如此，
 * 因此所有的加载请求都应该传到启动类加载器中，只有父类加载器反馈自己无法完成这个请求的时候（在它的加载路径下没有找到所需要的加载class）
 * 子加载器才会尝试自己去加载
 * 采用双亲委派的一个好处是比如加载位于rt.jar包中的类java.lang.Object,不管是哪个加载器加载这个类，最终都是委托给顶层的启动类加载器
 * 进行加载，这样就保证了使用不同的类加载器最终得到的都是同一个Object对象
 *
 * 沙箱安全:防止外部代码污染java源代码
 *eg:
 * package java.lang;
 *
 * public class String {
 *     //错误: 在类 java.lang.String 中找不到 main 方法
 *     public static void main(String[] args) {
 *         System.out.println("hello 1205");
 *     }
 * }
 *================================||======================================
 * 5、方法区
 *  5.1 它存储了每一个类的结构信息
 *  5.2 方法区是规范，在不同虚拟机里头实现是不一样的，最典型的就是永久代（java 7 PermGen Space) 和元空间（java 8  Metaspace)。
 *  eg:
 *  空调  x=new  格力（）；
 *  空调  x=new  海尔（）；
 *  供各线程共享时内存区域。它存储了每一个类的结构信息。例如运行时常量池（Runtime Constant Pool)
 *、字段和方法数据、构造函数和普通方法的字节码内容。上面讲的是规范，在不同虚拟机里头实现是不一样的。
 * 实例变量存在堆内存中，和方法区无关
 * ================================||======================================
 *6、栈
 * 6.1 栈管运行，堆管存储
 * 6.2 栈保存哪些东西？
 *      8种基本类型变量+对象的引用变量+实例方法都是在函数的栈内存中分配
 *      Exception in thread "main" java.lang.StackOverflowError  栈内存溢出 是一个错误
 *=====================================||======================================
 * 7、heap
 * 8、heap----->对象的生命周期------>OOM
 *
 * 9、GC是什么：分代收集算法（1、次数上频繁收集Young区2、次数上较少收集Old区3、基本不动元空间）
 *      9.1  引用计数法（对于循环引用问题不能解决，记数法消耗时间）-》进阶可达性分析
 *      9.2、复制算法（Copying  不产生内存碎片，但是费空间 用于young区）
 *      9.3、标志清除（Mark-Sweep 用于Old区，产生内存碎片，扫描标志，费时间、但是不需要额外空间)
 *      9.4、标志压缩（Mark-Compact)
 */

public class MyObject {
    public static void main(String[] args) {
        Object object=new Object();
        System.out.println(object.getClass().getClassLoader());//null
        //System.out.println(object.getClass().getClassLoader().getParent());
        //System.out.println(object.getClass().getClassLoader().getParent());

        System.out.println();
        System.out.println();

        MyObject myObject=new MyObject();
        System.out.println(myObject.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@b4aac2
        System.out.println(myObject.getClass().getClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@ca494b
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());//null
    }
}
