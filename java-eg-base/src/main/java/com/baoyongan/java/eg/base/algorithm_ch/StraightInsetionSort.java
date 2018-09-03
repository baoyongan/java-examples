package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 直接插入排序 将一个记录插入到已经排好序的有序表中，从而得到一个新的的有序表   O(n^2)
 */
public class StraightInsetionSort {
    public static void main(String[] args) {

        int[] in={14,5,2,4,6,1,8,8,9};
        System.out.println(Arrays.toString(in));
        in =sort1(in,true); // 初级版本

        System.out.println(Arrays.toString(in));
    }

    private static int[] sort1(int[] in, boolean b) {
        for (int i = 1; i <in.length ; i++) {
            if(b){
                // 递增排序
                if(in[i]<in[i-1]){ // i 为分界线  i坐标前面的是已经排好序的，后面的是为排序的
                    //  不满足进行排序
                    int temp=in[i];
                    int j;
                    for (j = i-1; j>=0&&in[j] >temp ; j--) {
                        in[j+1]=in[j]; // 后移
                    }
                    in[j+1]=temp; // 填充空出来的位置
                }
            }else{
                // 递减
                if(in[i]>in[i-1]){
                    int temp=in[i];
                    int j;
                    for (j = i-1; j >=0&&in[j]<temp ; j--) {
                        in[j+1]=in[j];
                    }
                    in[j+1]=temp;
                }
            }
        }
        return in;
    }
}
