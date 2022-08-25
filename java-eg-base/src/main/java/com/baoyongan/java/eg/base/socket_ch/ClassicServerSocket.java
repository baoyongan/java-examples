package com.baoyongan.java.eg.base.socket_ch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClassicServerSocket extends Thread {

    private int port;
    private int coreThreads;
    private ExecutorService executorService;
    private ServerSocket server;
    private volatile boolean shutDownFlag;

    public ClassicServerSocket(int port, int threads) {
        super("Server-Thread");
        this.port = port;
        this.coreThreads = threads;
    }

    @Override
    public void run() {
        executorService = Executors.newFixedThreadPool(coreThreads);
        try {
            server = new ServerSocket(this.port);
            System.out.println("服务端启动");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == server) {
            System.err.println("服务启动失败。");
            return;
        }
        do {
            try {
                if (!server.isClosed())
                    executorService.submit(new Handler(server.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (isCanGo() && !shutDownFlag);
    }

    private boolean isCanGo() {
        if (isInterrupted()) {
            shutdown();
            return false;
        }
        return true;
    }

    public void shutdown() {
        // 线程已经被中断
        shutDownFlag = true;
        try {
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.executorService.shutdown();
        System.out.println("服务端关闭");
    }

    private class Handler implements Runnable {
        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] input = new byte[10240];
            try {
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private byte[] process(byte[] input) {
            System.out.println("request: " + new String(input));
            System.out.println("............do something.......");
            return "hello wrold".getBytes(StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClassicServerSocket classicServerSocket = new ClassicServerSocket(9901, 3);
        classicServerSocket.start();
        Thread.sleep(10000);
        classicServerSocket.shutdown();
    }
}
