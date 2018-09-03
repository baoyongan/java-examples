package com.baoyongan.java.eg.thread.volatile_ch;

public class T001 extends Thread {


    public static void main(String[] args) throws InterruptedException {
        T001 t=new T001();
        t.start();

        Thread.sleep(100);
        t.setFlag(true);

        for (int i = 0; i <10 ; i++) {
            System.out.println(t.isFlag()+"---------"+t.getCount());
            Thread.sleep(1000);
        }
        System.out.println("over");
    }

    boolean flag=false;

    long count=0;

    @Override
    public void run() {
        //  如果不加 volatile 修饰，在虚拟机不同的启动模式下结果是不一样的  server 模式下会提升判断条件到循环体外面
        while (!flag){
            count++;
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


}
