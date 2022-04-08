package com.baoyongan.java.eg.base.class_ch;

import java.util.Objects;

/**
 * Created by bqct_bya on 2017/10/18.
 */
public class ObjectTest implements Cloneable{
    private String str;
    private Bicycle bicycle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectTest that = (ObjectTest) o;
        return Objects.equals(str, that.str) && Objects.equals(bicycle, that.bicycle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, bicycle);
    }

    public ObjectTest(String str, Bicycle bicycle) {
        this.str = str;
        this.bicycle=bicycle;
    }

    public static void main(String[] args) {
        ObjectTest ts=new ObjectTest("99",new Bicycle());
        ObjectTest ts1=null;
        try {
            ts1 = (ObjectTest) ts.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("string()==ts>"+ts.toString());
        System.out.println("string()==ts1>"+ts1.toString());
        System.out.println("hashCode()==ts>"+ts.hashCode());
        System.out.println("hashCode()==ts1>"+ts1.hashCode());
        System.out.println("str()==ts>"+ts.str);
        System.out.println("str()==ts1>"+ts1.str);
        System.out.println("bicycle()==ts>"+ts.bicycle);
        System.out.println("bicycle()==ts1>"+ts1.bicycle);
        System.out.println("==================================================");
        System.out.println(ts.toString());
        System.out.println(System.identityHashCode(ts));
        // System.identityHashCode 不论是否重写都是返回 Object 默认的实现。
        System.out.println(Integer.toHexString(System.identityHashCode(ts)));
        System.out.println(Integer.toHexString(Objects.hash(ts)));
    }
}
