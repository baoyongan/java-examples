package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 *
 */
public class FindSingleOne {


    /**
     * 两两相等，只有一个不成对，找到它
     * @param A : an integer array
     *          return : a integer
     */
    public static int singleNumber(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        int n = A[0];
        for (int i = 1; i < A.length; i++) {
            n = n ^ A[i];
        }

        return n;
    }

    /**
     * 两两相等，只有两个不成对，找到他们
     * @param num
     * @return
     */
    public static int[] findTwoSingleNum(int[] num) {
        int[] twoNums = new int[2];
        int result = 0;
        for (int i = 0; i < num.length; i++) { //计算那两个单独的数异或的结果，存在变量result中。
            result ^= num[i];
        }
        int count = 1;
        while (true) { //计算result二进制中第一个为1的位置，存在变量count中。
            if ((result&1) == 1) {
                break;
            }
            count++;
            result >>= 1;
        }
        int num1 = 0, num2 = 0;
        int tmp = 1;
        for (int i = 1; i < count; i++) { //构造辅助变量
            tmp <<= 1;
        }
        for (int i = 0; i < num.length; i++) { // 按照tmp中1的位置，把数组中的数分成两组，num1和num2分别异或每组的数。
            if ((num[i] & tmp) == tmp) {
                num1 ^= num[i];
            } else {
                num2 ^= num[i];
            }
        }
        twoNums[0] = num1;
        twoNums[1] = num2;
        return twoNums;
    }



    public static void main(String[] args) {
        int[] c={0,0,2,2,3,4,4,5,5,6,6,7,7,8,8};

        System.out.println(singleNumber(c));

        int[] b={0,0,2,2,3,3,23,9,4,4,5,5,6,6,7,7,8,8};

        System.out.println(Arrays.toString(findTwoSingleNum(b)));
    }

}
