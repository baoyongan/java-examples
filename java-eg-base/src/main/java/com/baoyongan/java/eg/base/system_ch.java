package com.baoyongan.java.eg.base;

public class system_ch {

    public static void main(String[] args) {
        System.out.println("availableProcessors count:"+Runtime.getRuntime().availableProcessors());

        long max=Long.MAX_VALUE;
        System.out.println(max);



        String aa="hesss-cba";
        System.out.println(aa.replace("hesss-",""));
        String b = aa.replace("hesss-", "");
        System.out.println(b);
        System.out.println(aa);
    }
}
