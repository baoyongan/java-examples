package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuikSort {

    public static void main(String[] args) {
//        int[] in={14,5,2,4,6,1,8,8,9};
        int[] in={14,9,8,7,6,5,4,3,2};
        System.out.println(Arrays.toString(in));

        /**
         * 最优的情况下，快速排序算法的时间复杂度为O(nlogn)。 最坏的情况下其时间复杂度为O(n2)。
         * 空间复杂度来说，主要是递归造成的栈空间的使用，最好情况，递归树的深度为log2n，其空间复杂度也就为O(logn)，最坏情况，需要进行n-1递归调用，其空间复杂度为O(n)，平均情况，空间复杂度也为O(logn)。
         *
         * 通过一趟排序将待排记录分割成独立的两部分，其中一部分记录的关键字均比另一部分记录的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序的目的
         */
        in =sort1(in,true);

        System.out.println(Arrays.toString(in));

    }

    private static int[] sort1(int[] in, boolean b) {
        mSort(in,0,in.length-1);
        return in;
    }

    private static void mSort(int[] in, int low, int hign) {
        int pivot; // 将这样的关键字称为枢轴（pivot） 即 排序部分的 pivot 前面的比prvot位置的值小，后面部分的比prvot部分的值大
        if(low<hign){
            pivot=partition(in,low,hign);
            mSort(in,low,pivot-1);
            mSort(in,pivot+1,hign);
        }
    }

    /**
     * 交换顺序表L中子表的记录，使枢轴记录到位，并返回其所在位置 此时在它之前（后）的记录均不大（小）于它
     * @param in
     * @param low
     * @param hign
     * @return
     */
    private static int partition(int[] in, int low, int hign) {
        int prvotkey=in[low];
        while (low<hign){
            while (low<hign&&in[hign]>=prvotkey)
                hign--;
            DubbleSort.swap(in,low,hign);
            while (low<hign&&in[low]<=prvotkey)
                low++;
            DubbleSort.swap(in,low,hign);
        }
        return low;
    }

}
