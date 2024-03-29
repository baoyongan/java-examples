package com.baoyongan.java.eg.base.numberAndString_ch;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Created by bqct_bya on 2017/10/18.
 */
public class NumberTest {

    public static void main(String[] args) {

        Short i = new Short((short) -1);
        System.out.println(Short.toUnsignedInt(i));

        // int 值溢出，不会报错，因为符号位变成1，所以变成了负值
        int max = Integer.MAX_VALUE;
        int sm = max + 1;
        System.out.println("测试溢出情况" + sm);
        // 典型的测试
        int lowest = -2147483648;
        int low = Integer.MIN_VALUE;
        int abs = Math.abs(lowest);
        System.out.printf("int 的最小值：%d, %d,使用math.abs 取绝对值返回：%d,溢出情况 \n", lowest, low, abs);
        // 负数的取余数运算注意项
        // (a/b)*b+a%b=a
        System.out.printf("-14/3= %d \n", -14 / 3);
        System.out.printf("14/-3= %d \n", 14 / -3);
        System.out.printf("-14%%3= %d \n", -14 % 3);
        System.out.printf("14%%-3= %d \n", 14 % -3);

        BigInteger bigInteger = new BigInteger("1212132132132212");

//        String s = Integer.toHexString(1);
//        System.out.println(s);

//        System.out.println(Integer.parseInt());
//        int a=0x0f;
        int a = 0x7FFFFFFF;
        System.out.println(a);
        System.out.println(Integer.parseInt("F", 16));
        System.out.println(Integer.toHexString(15));
        String s = Integer.toBinaryString(a);
        System.out.println(s.length());
//        System.out.println(Integer.toBinaryString(a));

        System.out.println("算法4_1.1.1 习题");
        int A = (0 + 15) / 2;
        double B = 2.0e-6 * 100000000.1; // 2.0e-6 表示2.0*10^-6;
        double B_1 = 2.0 * (Math.pow(10, -6)) * 100000000.1;
        boolean C = true && false || true && true;
        System.out.println(A);
        System.out.println(B);
        System.out.println(B_1);
        System.out.println(C);
        System.out.println(new BigDecimal("2.0e-6").toPlainString());

        double v = (1 + 2.236) / 2;
        System.out.println(v);
        double v1 = 1 + 2 + 3 + 4.0;
        System.out.println(v1);
        boolean b = 4.1 >= 4;
        System.out.println(b);
        String s1 = 1 + 2 + "3";
        System.out.println(s1);


        System.out.println("0%2=" + 0 % 2);
        System.out.println("1%2=" + 1 % 2);
        System.out.println("2%2=" + 2 % 2);


        System.out.println("=====================================================================");

        System.out.println(fmtMicrometer("111111111111111.21"));
        System.out.println(fmtMicrometer("11"));
        System.out.println(fmtMicrometer("111111"));
        System.out.println(fmtMicrometer("1111111.1"));
    }


    /**
     * 格式化数字为千分位显示；
     *
     * @param text 要格式化的数字
     * @return
     */
    public static String fmtMicrometer(String text) {
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }
}
