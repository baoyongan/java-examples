package com.baoyongan.java.eg.base.error_ch;

import java.math.BigDecimal;

public class DoubleMinus {

    public static void main(String[] args) {
        float a = 2.0f -1.1f;
        System.out.println(a);
        double b = 2.0d -1.1d;
        System.out.println(b);
        double c = sub(2.0d,1.1d);
        System.out.println(c);
        float unit = Float.valueOf(1000);//取出换算单位1000.0
        float svt = ((20000000f+1))/unit;//加0.001
        System.out.println(String.valueOf(svt));//精度丢失
        double unit1 = Float.valueOf(1000);//取出换算单位1000.0
        double svt1 = ((20000000d+1))/unit1;//加0.001
        System.out.println(String.valueOf(svt1));//ok
        System.out.println(Long.toBinaryString( Double.doubleToLongBits(20000001d)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(20000001)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(16777215)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(16777216)));
        System.out.println(Integer.toBinaryString(20000001));
        float f = 20000001;
        System.out.println(f);
        double d =20000001;
        System.out.println(d);
    }
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
}
