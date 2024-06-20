package com.baoyongan.java.eg.base.numberAndString_ch;

import java.math.BigDecimal;

public class BasicMathDemo {
    public static void main(String[] args) {
        Double dealBalance=14423.08;

        Double appendInterval = 0.01;
        Double appendpurMinBalance = 0.01;
        appendInterval = (null == appendInterval) ? 0.0 : appendInterval;
        // 入参金额 小于等于 最小金额  || （入参金额-最小金额）不是 级差的整数倍
        if (dealBalance - appendpurMinBalance < 0
                || (appendInterval > 0 && ((dealBalance - appendpurMinBalance) % appendInterval) != 0)) {
            System.out.println("不满足");
        }
        BigDecimal dealBalanceBig = new BigDecimal("14423.08");
        BigDecimal appendpurMinBalanceBig = new BigDecimal("0.01");
        BigDecimal appendIntervalBig = new BigDecimal("0.01");
        if (dealBalanceBig.compareTo(appendpurMinBalanceBig) < 0
                || (appendIntervalBig.compareTo(BigDecimal.ZERO) > 0 && ((dealBalanceBig.subtract(appendpurMinBalanceBig)).remainder(appendIntervalBig).compareTo(BigDecimal.ZERO))!=0)) {
            System.out.println("不满足");


        } else {
            System.out.println("满足");
        }

        double a = -191.635;
        double b = 43.74;
        int c = 16, d = 45;

        System.out.printf("The absolute value " + "of %.3f is %.3f%n",
                a, Math.abs(a));

        System.out.printf("The ceiling of " + "%.2f is %.0f%n",
                b, Math.ceil(b));

        System.out.printf("The floor of " + "%.2f is %.0f%n",
                b, Math.floor(b));

        System.out.printf("The rint of %.2f " + "is %.0f%n",
                b, Math.rint(b));

        System.out.printf("The max of %d and " + "%d is %d%n",
                c, d, Math.max(c, d));

        System.out.printf("The min of of %d " + "and %d is %d%n",
                c, d, Math.min(c, d));

        int dealut=5;
        System.out.println(Double.parseDouble("50"));
        int page= (int) Math.ceil(11/(Double.parseDouble("5")));
        System.out.println(page);
    }
}
