package com.baoyongan.java.eg.base;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {

        Random s1=new Random(1L);
        Random s2=new Random(1L);

        for (int i = 0; i < 10; i++) {
            System.out.printf("Rdm_1:[ %d ] Rdm_2:[ %d ] \r\n",s1.nextInt(),s2.nextInt());

        }
    }
}
