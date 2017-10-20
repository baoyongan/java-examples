package com.baoyongan.java.eg.base.class_ch;

/**
 * Created by bqct_bya on 2017/10/18.
 */
public class ObjectTest implements Cloneable{
    private String str;
    private Bicycle bicycle;

    public ObjectTest(String str,Bicycle bicycle) {
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


    }
}
