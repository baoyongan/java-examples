package com.baoyongan.java.eg.base.error_ch;

import java.util.Random;

public class Swich
{
    /**
     * 注意switch 的 语法。没有break 会一直按顺序往下执行
     * @param args
     */
    public static void main(String[] args) {
        Random r= new Random();
        int cide=r.nextInt(10);
        System.out.println("cide:"+cide);
        switch (cide){
            case 1:
                System.out.println(1);
            case 9:
                System.out.println(9);
            case 8:
                System.out.println(8);
            case 7:
                System.out.println(7);
                break;
            case 6:
                System.out.println(6);
            case 5:
                System.out.println(5);
            case 4:
                System.out.println(4);
            case 3:
                System.out.println(3);
                break;
            case 2:
                System.out.println(2);
                default:
                    System.out.println("defalut");
        }

    }
}
