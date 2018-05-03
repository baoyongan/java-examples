package com.baoyongan.java.eg.base.generic_ch;

import java.util.ArrayList;
import java.util.List;

public class Util {


    /**
     *  静态泛型方法的语法
     * @param t1
     * @param t2
     * @param <K>
     * @param <V>
     * @return
     */
   public static <K,V>boolean compare(OrderedPair<K,V> t1,OrderedPair<K,V> t2){
        return t1.getKey().equals(t2.getKey()) &&t1.getValue().equals(t2.getValue());
   }

    /**
     * 泛型方法的语法
     * @param t
     * @param s
     * @param <T>
     * @param <S>
     * @return
     */
   public <T,S>String cw(T t,S s ){
        return "";
   }

    /**
     * 泛型方法的语法
     * @param <T>
     * @param <S>
     * @param <R>
     * @return
     */
   public <T,S,R>String cq(){
       return  "";
   }

    public static void printList(List<Object> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    public static void printAllList(List<?> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        OrderedPair<String,Integer> p1=new OrderedPair<>("p1",100);
        OrderedPair<String,Integer> p2=new OrderedPair<>("p2",200);
        // Util.<String,Integer>compare(p1,p2);
        Util.compare(p1,p2);
        List<String> adds=new ArrayList<>();
        adds.add("1");
        Util.printAllList(adds);

        List<Number> s=new ArrayList<>();
        addNumbers(s);
        List<Integer> s1=new ArrayList<>();
        addNumbers(s1);
   }
}
