package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 归并排序
 *
 *
 * 归并排序是一种比较占用内存，但却效率高且稳定的算法。总的时间复杂度为O(nlogn) 空间复杂度为O(n+logn)
 */
public class MergingSort {

    public static void main(String[] args) {
        int[] in={14,5,2,4,6,1,8,8,9};
        System.out.println(Arrays.toString(in));
        /**
         * 递归方式
         * 归并排序（Merging Sort）就是利用归并的思想实现的排序方法。它的原理是假设初始序列含有n个记录，则可以看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，
         * 得到|n/2|（|x|表示不小于x的最小整数）个长度为2或1的有序子序列；再两两归并，……，如此重复，直至得到一个长度为n的有序序列为止，这种排序方法称为2路归并排序。
         */
//        in =sort1(in,true);

        /**
         * 非递归的迭代方法，避免了递归时深度为log2n的栈空间，空间只是用到申请归并临时用的TR数组，因此空间复杂度为O(n)，并且避免递归也在时间性能上有一定的提升，应该说，使用归并排序时，尽量考虑用非递归方法。
         */
        in =sort2(in,true);

        System.out.println(Arrays.toString(in));
    }

    private static int[] sort2(int[] in, boolean b) {

        int[] tr=new int[in.length];
        int k=1;
        while (k<in.length){
            // 按K的增量 两两 归并排序
            MergePass(in,tr,k,in.length);
            k=2*k;
            MergePass(tr,in,k,in.length); // 不需要再开辟新的数组空间
            k=2*k;
        }

        return in;
    }

    private static void MergePass(int[] in, int[] tr, int k, int length) {

        int i=0;
        int j;
        while (i<=length-2*k-1){ // 剩下的数量要够增量，不够的就是最后剩下来的一组的
            // 两两归并
            merge(in,tr,i,i+k-1,i+2*k-1);
            i=i+2*k; // 下一个
        }
        // 下来的是两个序列个的处理
        if(i<length-k){
            merge(in,tr,i,i+k-1,length-1);
        // 剩下来只又一个序列 直接添加上    
        }else{
            for (j = i; j <length ; j++) {
                tr[j]=in[j];
            }
        }
    }

    private static int[] sort1(int[] in, boolean b) {

        mSort(in,in,0,in.length-1);
        return in;
    }


    /**
     * 将sr[s...t] 归并排序为tr[s...t]
     * @param sr
     * @param tr1
     * @param s
     * @param t
     */
    private static void mSort(int[] sr, int[] tr1, int s, int t) {
        int m;
        if(s==t){
           tr1[s]=sr[s];
        }else {
            // 将sr[s...t] 平分为sr[s...m] 和 sr[m+1...t]
            m=(s+t)/2;
            int[] tr2= new int[sr.length];
            // 递归将sr[s...m] 归并排序为 tr2[s...m]
            mSort(sr,tr2,s,m);
            // 递归将sr[m+1...t] 归并排序为 tr2[m+1...t]
            mSort(sr,tr2,m+1,t);
            // 归并tr2[]到tr1[]
            merge(tr2,tr1,s,m,t);
        }
    }

    /**
     * 将有序的SR[i..m]和SR[m+1..n]归并为有序的TR[i..n]
     * @param tr2
     * @param tr1
     * @param s
     * @param m
     * @param t
     */
    private static void merge(int[] tr2, int[] tr1, int s, int m, int t) {
        int i,k;
        // 将tr2中记录由小到大归并入TR1
        for (i = m+1,k=s; s<=m&&i<=t ; k++) {
            if(tr2[s]>tr2[i])
                tr1[k]=tr2[i++];
            else
                tr1[k]=tr2[s++];
        }
        // 将tr2中记录由小到大归并入TR1
        if(s<=m){
            for (int j = 0; j <=m-s ; j++) {
                tr1[k+j]=tr2[s+j];
            }
        }
        // 将tr2中记录由小到大归并入TR1
        if(i<=t){
            for (int j = 0; j <=t-i ; j++) {
                tr1[k+j]=tr2[i+j];
            }
        }
    }
}
