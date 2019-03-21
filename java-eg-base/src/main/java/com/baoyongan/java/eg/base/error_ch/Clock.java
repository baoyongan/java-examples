package com.baoyongan.java.eg.base.error_ch;

public class Clock {

    public static void main(String[] args) {
        int mins=0;
        for (int i = 0; i <60*60*1000 ; i++) {
            if(i%(60*1000)==0){
                mins++;
            }
        }

        System.out.println(mins);
    }
}
