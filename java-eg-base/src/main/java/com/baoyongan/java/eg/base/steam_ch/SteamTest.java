package com.baoyongan.java.eg.base.steam_ch;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SteamTest {

    public static void main(String[] args) {



        // forEach Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。
//        forEach();

        // map map 方法用于映射每个元素到对应的结果
//        map();

        // filter filter 方法用于通过设置的条件过滤出元素
//        filter();

        // limit 方法用于获取指定数量的流。
//        limit();

//        sorted 方法用于对流进行排序。
        sorted();
    }

    private static void sorted() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    private static void limit() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    private static void filter() {
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }

    private static void map() {

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        List<Integer> result=numbers.stream().map(i->i*i).distinct().collect(Collectors.toList());

        System.out.println(result);
    }

    private static void forEach() {
        Random random=new Random();

        random.ints().limit(10).forEach(a->{
            System.out.println(a);
        });

        System.out.println("========================");

        random.ints().limit(10).forEach(System.out::println);

    }
}
