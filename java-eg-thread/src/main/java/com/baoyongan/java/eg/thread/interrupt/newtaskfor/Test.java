package com.baoyongan.java.eg.thread.interrupt.newtaskfor;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Socket s = null;
        try {
            s = new Socket("127.0.0.1", 8991);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CancellingExecutor executor = new CancellingExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

        Future<String> future = null;
        try {
            future = executor.submit(new SocketTask<String>(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(3000);
        future.cancel(true);
    }
}
