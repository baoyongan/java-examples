package com.baoyongan.java.eg.base.class_ch.init;

/**
 * 被动引用的例子
 */
public class NotInitialization {

    public static void main(String[] args) {
//        type1();
//        type2();
        type3();
    }

    private static void type3() {
        // 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
        System.out.println(ConstClass.HELLOWORLD);
    }

    private static void type2() {
        // 数组定义没有触发该类型的SuperClass 的初始化，但是虚拟机会初始化一个“[Lsuperclass”的类的初始化。来自newarray 指令。
        SuperClass[] cax=new SuperClass[10];
    }

    private static void type1() {
        // 通过子类引用父类的静态字段，不会导致子类的初始化。
        // -XX:+TraceClassLoading 可以观察加载。
        System.out.println(SubClass.value);
    }

}







