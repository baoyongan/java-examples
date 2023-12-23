package com.baoyongan.java.eg.base.generic_ch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型方法示例
 * 泛型方法是引入自己类型参数的方法，这类似与声明泛型类型，但类型参数的范围仅限于声明他的方法。
 */
public class GenericMethod {


    /**
     * 静态泛型方法的语法 (类型参数必须出现在返回值之前)
     *
     * @param t1
     * @param t2
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean compare(Pair<K, V> t1, Pair<K, V> t2) {
        return t1.getKey().equals(t2.getKey()) && t1.getValue().equals(t2.getValue());
    }

    /**
     * 泛型方法的语法
     *
     * @param t
     * @param s
     * @param <T>
     * @param <S>
     * @return
     */
    public <T, S> String cw(T t, S s) {
        return "";
    }

    /**
     * 泛型方法的语法
     *
     * @param <T>
     * @param <S>
     * @param <R>
     * @return
     */
    public <T, S, R> String cq() {
        return "";
    }

    public static void printList(List<Object> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    /**
     * 有界类型参数在 算法中的典型应用
     * @param array
     * @param elem
     * @return
     * @param <T>
     */
    public static <T extends Comparable<T>> int countGreanerThan(T[] array, T elem) {
        int count=0;
        for (T e : array) {
            // 大于给定的元素，计数器+1
            if (e.compareTo(elem)>0)
                count++;
        }
        return count;
    }

    public static void printAllList(List<?> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    /**
     * 通配符的应用
     * @param list
     */
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    public static double sumOfList (List<? extends Number> list) {
        double d=0.0;
        for (Number n : list) {
            d+=n.doubleValue();
        }
        return d;
    }

    public static void main(String[] args) {
        OrderedPair<String, Integer> p1 = new OrderedPair<>("p1", 100);
        OrderedPair<String, Integer> p2 = new OrderedPair<>("p2", 200);
        // GenericMethod.<String,Integer>compare(p1,p2);
        Boolean result=GenericMethod.compare(p1, p2);
        System.out.println(result);
        List<String> adds = new ArrayList<>();
        adds.add("1");
        GenericMethod.printAllList(adds);

        List<Number> s = new ArrayList<>();
        addNumbers(s);
        List<Integer> s1 = new ArrayList<>();
        addNumbers(s1);

        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println("sum=" + sumOfList(list));

    }
}
