package com.baoyongan.java.eg.thread.threadlocal;

public class ThreadLocalTest {

    private static ThreadLocal<Context> contexts=new ThreadLocal<>();


    public Context getCurrentContext(String id){
        Context context= contexts.get();
        if (null==context){
            context=new Context(id,Thread.currentThread().getName());
            contexts.set(context);
        }
        return context;
    }

    public void removeCurrentContext(){
       contexts.remove();
       System.out.println(Thread.currentThread().getName()+"try get():result:"+contexts.get());
    }


    public static void main(String[] args) {
        ThreadLocalTest t=new ThreadLocalTest();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<10){
                   Context context= t.getCurrentContext("001");
                   System.out.println("t1--context:"+context);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i+=2;
                }
                t.removeCurrentContext();
            }
        },"t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<10){
                   Context context= t.getCurrentContext("002");
                   System.out.println("t2--context:"+context);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i+=2;
                }
                t.removeCurrentContext();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
