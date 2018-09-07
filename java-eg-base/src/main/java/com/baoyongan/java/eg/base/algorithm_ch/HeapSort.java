package com.baoyongan.java.eg.base.algorithm_ch;

import java.util.Arrays;

/**
 * 堆排序
 * 将待排序的序列构造成一个大顶堆。（大顶堆满足所有节点大于等于其子孩子）。
 * 所以最大值就是顶节点也就是根节点。 然后交换根节点和队尾节点。再将除了队尾节点外的其他节点构建成一个大顶堆。
 * 继续进行根节点和未排序的队尾节点替换。反复如此就可以得到一个有序序列了。
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] in={14,5,2,4,6,1,8,8,9};
        System.out.println(Arrays.toString(in));
        in =sort1(in,true);

        System.out.println(Arrays.toString(in));
    }

    private static int[] sort1(int[] in, boolean b) {
        int i;
        // 构建数组成一个大顶堆
        for (i = in.length/2; i> 0; i--) { // 非叶子节点
            heapAdjust(in,i,in.length);
        }
        // 依次将堆顶记录和当前未经排序子序列的最后一个记录交换，并同时再调整堆
        for (int j = in.length; j >0 ; j--) {
            DubbleSort.swap(in,0,j-1); // 交换
            heapAdjust(in,1,j-1); // 再调整
        }
        return in;
    }

    /**
     * 堆调整 (这里的入参s,要理解成调整子树根节点的序号)，如果是数组下标还是要-1的。
     * 已知L->r[s..m]中记录的关键字除L->r[s]之外均满足堆的定义
     * 本函数调整L->r[s]的关键字，使L->r[s..m]成为一个大顶堆
     * @param in
     * @param s
     * @param m
     */
    private static void heapAdjust(int[] in, int s, int m) {
        int temp,j;
        temp=in[s-1];

        for (j = 2*s; j <=m ; j*=2) {
            if(j<m&&in[j-1]<in[j]) // 存在右子树，并且右边大于左边
                j++; // j 换成右边
            if(temp>=in[j-1]){
                break; // 跳出循环s子树已经是一个大顶堆
            }
            in[s-1]=in[j-1]; // s位置的数组值替换成孩子里面的大值
            s=j; // s 换到 j 的位置
            // 然后就是如果j 的位置有孩子继续交换大值上移
        }
        // 最后s 的位置就是放最初的值temp
        in[s-1]=temp;
    }
}
