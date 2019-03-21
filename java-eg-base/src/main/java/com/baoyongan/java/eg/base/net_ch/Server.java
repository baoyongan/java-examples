package com.baoyongan.java.eg.base.net_ch;

import jdk.net.Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Server {

    private static volatile AtomicLong count=new AtomicLong(0);

    private static ExecutorService executor= Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket server=null;
        try {
            server = new ServerSocket(8991);
            System.out.println("服务器已启动,等待客户连接...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            Socket socket = server.accept();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
                    InputStreamReader inputStreamReader = null;
                    BufferedReader bufferedReader = null;
                    OutputStream outputStream = null;
                    PrintStream printStream = null;
                    String a;
                    String b = "地瓜地瓜,我是土豆";
                    try {
                        inputStream = socket.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    inputStreamReader = new InputStreamReader(inputStream);
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        while((a = bufferedReader.readLine()) != null){
                            System.out.println("客户端说:"+a);
                            socket.shutdownInput();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                   /* try {
                        outputStream = socket.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    printStream = new PrintStream(outputStream);
                    try {
                        printStream.write(b.getBytes());
                        socket.shutdownOutput();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            });

            System.out.println("这是第"+count.incrementAndGet()+"位用户");
            System.out.println("此用户的IP地址为"+socket.getInetAddress().getHostAddress());
        }

    }



}
