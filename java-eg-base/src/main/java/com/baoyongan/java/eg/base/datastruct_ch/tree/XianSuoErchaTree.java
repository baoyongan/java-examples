package com.baoyongan.java.eg.base.datastruct_ch.tree;

import java.util.Scanner;

import static com.baoyongan.java.eg.base.datastruct_ch.tree.XianSuoErchaTree.BiTNode.TYPE_LINK;
import static com.baoyongan.java.eg.base.datastruct_ch.tree.XianSuoErchaTree.BiTNode.TYPE_THREAD;

/**
 * 线索二叉树
 */
public class XianSuoErchaTree {

    public static void main(String[] args) {
        // 构建
        BiTNode biTNode = creatThreadBiTree();
        // 遍历
        System.out.println("中序遍历");
        iNOrderTraverse(biTNode);
    }

    /**
     * 通过输入字符来初始化二叉树，按照前序遍历的顺序录入，对于没有孩子的节点使用“#” 构建虚节点
     */
    public static BiTNode creatBiTree() {
        Scanner scanner = new Scanner(System.in);
        try {
            return creatBiInnerTree(scanner);
        } finally {
            scanner.close();
        }
    }

    /**
     * 创建线索化二叉树
     */
    public static BiTNode creatThreadBiTree() {
        BiTNode biTNode = creatBiTree();
        System.out.println("线索化分析开始");
        // 添加头节点
        BiTNode tou = new BiTNode();
        // 设置头结点的左孩子为根节点
        // tou 节点数据为 null
        tou.setlFlag(TYPE_LINK);
        tou.setlChild(biTNode);
        // 中序线索化
        BiTNode pre = tou; // 设置前置节点
        pre = inThreadBitNode(tou, pre);
        // 把线索化最后的 pre，设置为头结点的右节点
        tou.setrChild(pre);
        tou.setlFlag(TYPE_LINK);
        // 头结点后不再继续线索化，线索化完成
        System.out.println("线索化分析完成");
        return tou;
    }

    /**
     * 中序遍历线索化
     *
     * @param biTNode
     * @param pre     前驱节点
     * @return
     */
    private static BiTNode inThreadBitNode(BiTNode biTNode, BiTNode pre) {
        if (null == biTNode)
            return pre;
        // 左子树线索化 ,注意更新pre, 递归过程中会不断更新 pre
        pre = inThreadBitNode(biTNode.getlChild(), pre);
        // 处理当前节点的前驱线索化
        if (null == biTNode.getlChild()) {
            biTNode.setlFlag(TYPE_THREAD);
            biTNode.setlChild(pre);
            System.out.println(biTNode.getData()+"的前驱是："+pre.getData());
        }
        // 处理当前节点前驱的后继线索化
        if (null != pre && null == pre.getrChild()) {
            pre.setrFlag(TYPE_THREAD);
            pre.setrChild(biTNode);
            System.out.println(pre.getData()+"的后继是："+biTNode.getData());
        }
        // 头结点不处理
        if(null!=biTNode.getData()){
            // 更新前驱
            pre = biTNode;
            // 右子树线索化
            pre = inThreadBitNode(biTNode.getrChild(), pre);
        }
        return pre;
    }

    private static BiTNode creatBiInnerTree(Scanner scan) {
        System.out.println("请输入节点数据，如果节点为空，请输入#：");
        String data = scan.nextLine();
        System.out.println("获得输入的信息：" + data);
        if (null != data && "#".equals(data)) {
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
     * 中序遍历 中序线索二叉树（带头结点）
     *
     * @param node
     */
    public static void iNOrderTraverse(BiTNode node) {
        // 空节点 直接返回
        if (null == node)
            return;
        BiTNode p = node.getlChild(); // 根节点
        while(p!=node){
            // 找到中序第一个节点
            while (p.getlFlag()==TYPE_LINK)
                p=p.getlChild();
            // 打印p
            System.out.println("数据："+p.getData());
            // 根据线索找到后继节点
            while (p.getrFlag()==TYPE_THREAD && p.getrChild()!=node){
                p=p.getrChild();
                System.out.println("数据："+p.getData());
            }
            // 没有线索，说明p 的 右节点不是线索，p 更新到 右节点
            p=p.getrChild();
        }
    }

    /**
     * 线索二叉树的链表结构
     *
     * @param <T>
     */
    public static class BiTNode<T> {
        public static final int TYPE_THREAD = 1;
        public static final int TYPE_LINK = 0;
        private T data; // 数据节点
        private BiTNode lChild; // 左孩子
        private BiTNode rChild; // 右孩子
        private int lFlag; // 左孩子节点标志 0-左右孩子类型 1-前驱后继线索类型
        private int rFlag; // 右孩子节点标志

        public int getlFlag() {
            return lFlag;
        }

        public void setlFlag(int lFlag) {
            this.lFlag = lFlag;
        }

        public int getrFlag() {
            return rFlag;
        }

        public void setrFlag(int rFlag) {
            this.rFlag = rFlag;
        }

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

