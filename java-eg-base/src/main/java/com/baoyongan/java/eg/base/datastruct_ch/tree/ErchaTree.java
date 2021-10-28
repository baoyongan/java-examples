package com.baoyongan.java.eg.base.datastruct_ch.tree;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.util.Scanner;

/**
 * 二叉树
 */
public class ErchaTree {

    public static void main(String[] args) {
        // 构建
        BiTNode biTNode = creatBiTree();
        // 遍历
        System.out.println("前序遍历");
        preOrderTraverse(biTNode);
        System.out.println("中序遍历");
        iNOrderTraverse(biTNode);
    }

    /**
     * 通过输入字符来初始化二叉树，按照前序遍历的顺序录入，对于没有孩子的节点使用“#” 构建虚节点
     */
    public static BiTNode creatBiTree(){
        Scanner scanner = new Scanner(System.in);
        try {
            return creatBiInnerTree(scanner);
        }finally {
            scanner.close();
        }
    }

    private static BiTNode creatBiInnerTree(Scanner scan){
        System.out.println("请输入节点数据，如果节点为空，请输入#：");
        String data=scan.nextLine();
        System.out.println("获得输入的信息："+data);
        if(null!=data && "#".equals(data)){
            // 说明该节点为空
            return null;
        }
        // 有效节点
        BiTNode node = new BiTNode();
        node.setData(data);
        node.setlChild(creatBiInnerTree(scan));
        node.setrChild(creatBiInnerTree(scan));
        return node;
    }

    /**
     * 前序遍历 （先根，左孩子，右孩子）
     * @param node
     */
    public static void preOrderTraverse(BiTNode node){
        // 空节点 直接返回
        if(null==node)
            return;
        // 打印根节点
        System.out.println("data :"+node.getData());
        // 遍历左子树
        preOrderTraverse(node.getlChild());
        // 遍历右子树
        preOrderTraverse(node.getrChild());
    }

    /**
     * 中序遍历 （左孩子，根，右孩子）
     * @param node
     */
    public static void iNOrderTraverse(BiTNode node){
        // 空节点 直接返回
        if(null==node)
            return;
        // 遍历左子树
        preOrderTraverse(node.getlChild());
        // 打印根节点
        System.out.println("data :"+node.getData());
        // 遍历右子树
        preOrderTraverse(node.getrChild());
    }

    /**
     * 后序遍历 （左孩子，右孩子，根）
     * @param node
     */
    public static void postOrderTraverse(BiTNode node){
        // 空节点 直接返回
        if(null==node)
            return;
        // 遍历左子树
        preOrderTraverse(node.getlChild());
        // 遍历右子树
        preOrderTraverse(node.getrChild());
        // 打印根节点
        System.out.println("data :"+node.getData());
    }

    /**
     * 二叉树的链表结构
     * @param <T>
     */
    public static class BiTNode<T> {
        private T data; // 数据节点
        private BiTNode lChild; // 左孩子
        private BiTNode rChild; // 右孩子

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BiTNode getlChild() {
            return lChild;
        }

        public void setlChild(BiTNode lChild) {
            this.lChild = lChild;
        }

        public BiTNode getrChild() {
            return rChild;
        }

        public void setrChild(BiTNode rChild) {
            this.rChild = rChild;
        }
    }
}

