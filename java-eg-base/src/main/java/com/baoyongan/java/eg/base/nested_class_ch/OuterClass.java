package com.baoyongan.java.eg.base.nested_class_ch;

/**
 * 嵌套类-外部类
 * Created by bqct_bya on 2017/10/12.
 */
public class OuterClass {

    private int a=1;

    private static int b=0;

    /**
     * 嵌套类一 非静态嵌套类（内部类）
     */
    private class innerClass{
        // private static  int s=2; error 不能在内部类中定义静态成员
        public void print(){
            System.out.println(a);
        }
    }

    /**
     * 嵌套类二 静态嵌套类
     */
    private static class StaticNestedClass{
        public void print(){
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        OuterClass.StaticNestedClass sn=new OuterClass.StaticNestedClass();
        sn.print();

        OuterClass outer=new OuterClass();
        OuterClass.innerClass inner=outer.new innerClass();
        inner.print();

    }
}
