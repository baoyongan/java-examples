package com.baoyongan.java.eg.base.generic_ch;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型推断和泛型方法示例
 */
public class BoxDemo {

    public static <U> void addBox(U u, List<BoxGeneric<U>> boxes) {
        boxes.add(new BoxGeneric<U>(u));
    }

    public static <U> void outputBoxes(List<BoxGeneric<U>> boxes) {
        int count = 0;
        for (BoxGeneric<U> box :
                boxes) {
            U u = box.get();
            System.out.println("盒子 #"+ count +"包含["+u.toString()+"]");
            count++;
        }
    }

    public static void main(String[] args) {
        List<BoxGeneric<Integer>> boxes =new ArrayList<>();
        BoxDemo.<Integer>addBox(10, boxes);
        BoxDemo.addBox(20, boxes);
        BoxDemo.addBox(30, boxes);
        BoxDemo.outputBoxes(boxes);
    }

}
