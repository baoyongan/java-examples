package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 冒泡排序   时间复杂度O(n^2) ,最坏情况是 n+(n-1)+(n-2)+....+3+2+1=n(n-1)/2
 */
public class DubbleSort {

    public static void main(String[] args) {
        int[] in={14,5,2,4,6,1,7,8,9};
        System.out.println(Arrays.toString(in));
//        in =sort1(in,true); // 初级版本
//        in =sort2(in,true); // 正宗冒泡
        in =sort3(in,true); // 正宗冒泡改进

        System.out.println(Arrays.toString(in));

    }

    /**
     * 如果数据交换几次之后顺序已经是有序的了，后面的比较就没有意义了，所以在内部循环没有发生交换的时候退出
     * @param in
     * @param b
     * @return
     */
    private static int[] sort3(int[] in, boolean b) {
        boolean goon=true;
        for (int i = 0; i <in.length && goon; i++) {
            goon=false;
            for (int j = in.length-1; j > i ; j--) {
                if(b){
                    // 递增
                    if(in[j]<in[j-1]){
                        swap(in,j,j-1);
                        goon=true;
                    }

                }else{
                    // 递减
                    if(in[j]>in[j-1]){
                        swap(in,j,j-1);
                        goon=true;
                    }
                }
            }
        }
        return in;
    }

    /**
     * 冒泡  每次内循环比较 两两比较上升一个数值，两两比较过程中也会交换顺序。
     * @param in
     * @param b
     * @return
     */
    private static int[] sort2(int[] in, boolean b) {
        for (int i = 0; i <in.length ; i++) {
            for (int j = in.length-1; j > i; j--) {
                if(b){
                    // 递增
                    if(in[j]<in[j-1])
                        swap(in,j,j-1);
                }else{
                    // 递减
                    if(in[j]>in[j-1])
                        swap(in,j,j-1);
                }
            }
        }
        return in;
    }

    /**
     *  初级版，交换排序的思想，两两比较，每一次进行内部循环都要完成该位置上最大或者最小值的变化。
     * @param in
     * @param b true：递增   false:递减
     */
    private static int[] sort1(int[] in, boolean b) {

        for (int i = 0; i <in.length ; i++) {

            for (int j = i+1; j < in.length; j++) {
                if(b){
                    if (in[i]>in[j])
                        swap(in,i,j);
                }else{
                    if (in[i]<in[j])
                        swap(in,i,j);
                }

            }
        }

        return in;
    }

    public static void swap(int[] in, int i, int j) {
        int temp=in[i];
        in[i]=in[j];
        in[j]=temp;
    }
}
