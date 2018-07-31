package com.baoyongan.java.eg.base.process_ch;

public class BreakCh {

    public static void main(String[] args) {
        int i=0;
        while (true){
            System.out.println(i);
            if (i++==10){
                break;
            }
        }
        System.out.println("over");
    }

}
