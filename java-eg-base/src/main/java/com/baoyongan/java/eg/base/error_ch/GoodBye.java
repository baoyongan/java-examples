package com.baoyongan.java.eg.base.error_ch;

public class GoodBye {

    public static void main(String[] args) {
        try {
            System.out.println("Hello world");
            System.exit(0);
        }finally {
            System.out.println("GoodBye world");
        }

    }
}
