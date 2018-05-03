package com.baoyongan.java.eg.thread.simple;

public class InterrupteMessages {

    public static void main(String args[])
            throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            if(Thread.interrupted()){
                throw new InterruptedException();
            }
            //Print a message
            System.out.println(importantInfo[i]);
        }
    }
}
