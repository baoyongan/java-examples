package com.baoyongan.java.eg.thread.simple;

public class DropTemp {
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public String takeo() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                System.out.println("takeooo---wait.");
                // This method should only be called by a thread that is the owner
                //     * of this object's monitor.
                // 所以这样会报错 IllegalMonitorStateException
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println("takeooo---enter.");
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return message;
    }

    public synchronized String take() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                System.out.println("take---wait.");
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println("take---enter.");
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }

    public static void main(String[] args) {
        DropTemp drop=new DropTemp();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(drop.takeo());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(drop.take());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                drop.put("hello world!");
            }
        }).start();
    }
}
