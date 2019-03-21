package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 是 插入排序的改进版本  时间复杂度依赖于 增量值的选择  复杂度要好于 插入排序
 * 希尔排序的精华所在，它将关键字较小的记录，不是一步一步地往前挪动，而是跳跃式地往前移，从而使得每次完成一轮循环后，整个序列就朝着有序坚实地迈进一步
 *
 * 按照增量不断对队列进行 增量跨度的值比较，和移位交换。加快了移位。
 */
public class ShellSort {


    public static void main(String[] args) {


        int[] in={14,5,2,4,6,1,8,8,9};
        System.out.println(Arrays.toString(in));
        in =sort1(in,true); // 初级版本

        System.out.println(Arrays.toString(in));

    }

    private static int[] sort1(int[] in, boolean b) {

        int i,j,increment=in.length;

        do {
            // 增量选择
            increment=increment/3+1;

            for (i = increment;  i<in.length ; i++) {
                if(b){
                    // 递增
                    if (in[i]<in[i-increment]){
                        int temp=in[i];

                        // 按增量跨度 交换
                        for (j = i-increment; j>=0&& in[j]>temp ; j-=increment) {
                            in[j+increment]=in[j];
                        }

                        // 空位插入i
                        in[j+increment]=temp;
                    }
                }else{
                    // 递减
                    if (in[i]>in[i-increment]){
                        int temp=in[i];

                        // 按增量跨度 交换
                        for (j = i-increment; j>=0&& in[j]<temp ; j-=increment) {
                            in[j+increment]=in[j];
                        }

                        // 空位插入i
                        in[j+increment]=temp;
                    }
                }
            }
        }while (increment>1);
        return in;
    }
}
