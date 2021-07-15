package com.baoyongan.java.eg.base.variable_ch;

/**
 * Created by bqct_bya on 2017/9/30.
 */
public class PrimitiveDataType {
    public static void main(String[] args) {

        long s=999_99____9999L;
        System.out.println(s);

        int a=12_232_232;
        System.out.println(a);

        float f=3.12_2121_21f;
        System.out.println(f);

        double d=32323.32_323_23d;
        System.out.println(d);

        Boolean sss=null;
        Boolean ddd=false;
        if(ddd==sss){
            System.out.println("ddd==sss");
        }
        System.out.println(sss);

        TestBool st=new TestBool();
        System.out.println(st);

    }

    static class TestBool{
        private Boolean fff;

        public Boolean getFff() {
            return fff;
        }

        public void setFff(Boolean fff) {
            this.fff = fff;
        }

        @Override
        public String toString() {
            return "TestBool{" +
                    "fff=" + fff +
                    '}';
        }
    }
}
