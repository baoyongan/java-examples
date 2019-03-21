package com.baoyongan.java.eg.base.error_ch;

public class ReturnValue {

    public static void main(String[] args) {

        System.out.println(decision());
    }

    private static boolean decision() {
        try {
            return true;
        }finally {
            return false;
        }

       /* boolean flag=false;
        try {
            flag=true;
            System.out.println("before return");
            return flag;
        }finally {
            System.out.println("execute finally");
            flag=false;
        }*/

    }
}
