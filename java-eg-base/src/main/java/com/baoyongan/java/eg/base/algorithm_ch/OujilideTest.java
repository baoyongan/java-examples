package com.baoyongan.java.eg.base.algorithm_ch;

/**
 * 欧几里得算法（找两个数的最大公约数）
 */
public class OujilideTest {

    public static void main(String[] args) {

        System.out.println(gcd(3,6));
    }

    private static int gcd(int p, int q) {
        if(q==0)
            return p;
        int r=p%q;
        return gcd(q,r);
    }
}
