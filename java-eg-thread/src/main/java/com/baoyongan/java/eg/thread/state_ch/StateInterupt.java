package com.baoyongan.java.eg.thread.state_ch;

public class StateInterupt {

    Thread t1;
    public void t1(){
        t1=new Thread(()->{
            boolean flag=true;
            while(flag){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("线程t1已经被中断了");
                    flag=false;
                }
            }
            int i=0;
            while(!flag&&i<10000000){
                i++;
            }
            System.out.println("线程t1的中断状态："+t1.isInterrupted());
            System.out.println("i当前值："+i);
        });
    }

    Thread t2;
    public void t2(){
        t2=new Thread(()->{
            boolean rupted=false;
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("我在sleep中被中断了。");
                    rupted=true;
                }
                System.out.println("t2 当前的中断状态："+Thread.currentThread().isInterrupted());
                if(rupted){
                    break;
                }
            }
        });
    }



    public static void main(String[] args) {
        StateInterupt st=new StateInterupt();
        System.out.println("启动 t1");

        st.t1();
        System.out.println("触发t1中断");
        st.t1.interrupt();  // 未启动的线程，修改中断状态是无效的
        System.out.println("t1 的中断状态"+st.t1.isInterrupted());
        st.t1.start();
        System.out.println("t1 的中断状态"+st.t1.isInterrupted());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("触发中断");
        st.t1.interrupt();  // 只会把线程中断状态置为true

        System.out.println("t1 的中断状态"+st.t1.isInterrupted());
        System.out.println("t1 的中断状态"+st.t1.isInterrupted());
        System.out.println("t1 的中断状态"+st.t1.isInterrupted());

        st.t2();
        st.t2.start();
        System.out.println("触发t2中断");
        st.t2.interrupt();
        System.out.println("t2 的中断状态"+st.t2.isInterrupted()); // 如果线程当前状态正好是wait,join,sleep 中，中断状态会被清除。


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 线程的中断状态："+Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println("main 线程中断后的中断状态："+Thread.currentThread().isInterrupted());
        boolean interrupted = Thread.interrupted();
        System.out.println("main interrupted() 返回状态："+interrupted);
        System.out.println("main 线程的中断状态："+Thread.currentThread().isInterrupted());
    }
}
