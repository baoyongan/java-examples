package com.baoyongan.java.eg.thread.collection.deque;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class SecretWorkFetch {

    // 工作
    private static class Work implements Runnable{
        private static Object object=new Object();
        private static int count=0;
        public final int id;
        private long putThread;
        public Work(){
            synchronized(object){
                id=count++;
            }
        }
        @Override
        public void run() {
            if(Thread.currentThread().getId()!=putThread){
                System.out.println("===================================================");
            }
            System.out.println(Thread.currentThread().getId()+":"+putThread+"// finish job "+id);

        }
        public long getPutThread() {
            return putThread;
        }
        public void setPutThread(long putThread) {
            this.putThread = putThread;
        }



    }
    public static Work generateWork(){
        return new Work();
    }
    private static class ConsumerAndProducer implements Runnable{
        private Random random=new Random();
        private final LinkedBlockingDeque<Work> deque;
        private final LinkedBlockingDeque<Work> otherWork;
        public ConsumerAndProducer(LinkedBlockingDeque<Work> deque,LinkedBlockingDeque<Work> otherWork){
            this.deque=deque;
            this.otherWork=otherWork;
        }
        @Override
        public void run() {
            while(!Thread.interrupted()){
                try {
                    Thread.sleep(200);
                    if(random.nextBoolean()){
                        // 生产工作
                        int count=random.nextInt(5);
                        for(int i=0;i<count;i++){
                            Work w=generateWork();
                            w.setPutThread(Thread.currentThread().getId());
                            deque.putLast(w);
                        }
                    }
                    // 自己的工作没有，其他人的工作有人，做其他人的最后面的那个工作
                    if(deque.isEmpty()){
                        if(!otherWork.isEmpty()){
                            otherWork.takeLast().run();;
                        }
                    }else{
                        // 做自己的工作
                        deque.takeFirst().run();
                    }
                } catch (InterruptedException e) {

                }
            }



        }


    }


    public static void main(String[] args) {
        LinkedBlockingDeque<Work> deque=new LinkedBlockingDeque<Work>();
        LinkedBlockingDeque<Work> other=new LinkedBlockingDeque<Work>();
        new Thread(new ConsumerAndProducer(deque,other)).start();
//        new Thread(new ConsumerAndProducer(deque,other)).start();

        new Thread(new ConsumerAndProducer(other,deque)).start();
//        new Thread(new ConsumerAndProducer(other,deque)).start();
    }

}
