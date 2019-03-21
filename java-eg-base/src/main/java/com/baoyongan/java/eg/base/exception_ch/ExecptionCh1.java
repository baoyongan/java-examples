package com.baoyongan.java.eg.base.exception_ch;

public class ExecptionCh1 {


    public static void main(String[] args) {
        System.out.println(doSome());
    }

    public static int doSome(){
        int x=0;
        try {
            x=1;
            x=x/0;
            return x;
        }catch (Exception e){
            x=2;
            return x;
        }finally {
            x=3;
//            return x;
        }
    }
}
