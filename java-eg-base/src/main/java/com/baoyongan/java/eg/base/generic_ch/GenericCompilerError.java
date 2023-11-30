package com.baoyongan.java.eg.base.generic_ch;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型在使用中容易出现的问题
 */
public class GenericCompilerError {

    public static void main(String[] args) {
        List<String> strs= new ArrayList<>();
        strs.add("dsf");
//        testRawType(strs);
        testInstanceof(strs);

    }

    private static void testRawType(List<String> strs) {
        unsafeAdd(strs, new Integer(43));
        String s = strs.get(1);
        System.out.println(s);
    }

    private static void testInstanceof(Object strs) {
        if (strs instanceof List) {
            List<String> d= (List<String>) strs;
            String s = d.get(0);
            System.out.println(s);
        }
    }

    /**
     * 使用原生态　List 作为入参，会避开泛型的类型校验，但很容易发现类型转换问题。
     * @param strs
     * @param o
     */
    private static void unsafeAdd(List strs, Object o) {
        strs.add(o);
    }
}
