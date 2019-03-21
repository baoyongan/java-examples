package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 简单选择排序  性能略优于冒泡排序  O(n^2)
 */
public class SimpleSelectSort {

    public static void main(String[] args) {
        int[] in={14,5,2,4,6,1,7,8,9};
        System.out.println(Arrays.toString(in));
        in =sort1(in,true);
        System.out.println(Arrays.toString(in));
    }

    private static int[] sort1(int[] in, boolean b) {

        for (int i = 0; i <in.length ; i++) {
            int temp;
            temp=i;

            for (int j = i+1; j <in.length ; j++) {
                if(b){
                    // 递增
                    if(in[temp]>in[j]){
                        temp=j;
                    }
                }else {
                    if (in[temp]<in[j]){
                        temp=j;
                    }
                }
            }

            if(temp!=i){
                // 找到最小值
                DubbleSort.swap(in,i,temp);
            }
        }
        return in;
    }
}
