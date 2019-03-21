package com.baoyongan.java.eg.thread.synch;

public class SynchTest
{
    private static int i=0;

    public synchronized static int di(){
        System.out.println("static in");
        for (int j = 0; j <10000; j++) {
           i--;
        }
        return i;
    }

    public synchronized int de(){
        System.out.println("in");
        for (int j = 0; j <10000; j++) {
            i++;
        }
        return i;
    }

    public synchronized int dis(){
        System.out.println("s in");
        return i--;
    }

    public static int getI() {
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchTest st=new SynchTest();
        for (int j = 0; j <100 ; j++) {
            String threadName="threadstatic"+j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(st.di());
                }
            },threadName).start();
            String threadName1="thread"+j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println( st.de());
                }
            },threadName1).start();

        }
        Thread.sleep(10000);
        boolean tr=true;
       /* while (tr) {
            int c=Thread.activeCount();
            System.out.println("count================>"+c);
            if(c==1){
                tr=false;
            }
//            if(c==2){
//                System.out.println(Thread.getAllStackTraces());
//            }
            Thread.sleep(100);
        }*/
        System.out.println("----->"+st.getI());

    }
}
