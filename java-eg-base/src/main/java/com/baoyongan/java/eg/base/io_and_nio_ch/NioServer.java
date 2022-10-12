package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {

    static final int BUFF_SIZE = 1024;

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    public byte[] readFromSocketChannelToString(SocketChannel channel) throws IOException {
        ByteBuffer buff = ByteBuffer.allocate(BUFF_SIZE);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int n;
        while (0 != (n = channel.read(buff))) {
            buff.flip();
            byte[] temp = new byte[n];
            buff.get(temp, 0, n);
            bos.write(temp, 0, temp.length);
            buff.clear();
        }
        bos.flush();
        bos.close();
        return bos.toByteArray();
    }

    public void writeDatatoChannel(byte[] data, SocketChannel channel) throws IOException {
        ByteBuffer buff = ByteBuffer.allocate(BUFF_SIZE);
        int len = data.length;
        int offset = 0;
        int size;
        while (offset < len) {
            if (len - offset < BUFF_SIZE) {
                size = len - offset;
            } else {
                size = BUFF_SIZE;
            }
            buff.put(data, offset, size);
            buff.flip();
            while (buff.hasRemaining()) {
                channel.write(buff);
            }
            buff.clear();
            offset = offset + size;
        }
    }

    public void handler(byte[] data, SocketChannel channel, SelectionKey key) {
//        executorService.submit(new Hander(data,channel,key));
        new Hander(data,channel,key).run();
    }

    class Hander implements Runnable{
        private byte[] data;
        private SocketChannel channel;
        private SelectionKey key;

        public Hander(byte[] data, SocketChannel channel, SelectionKey key) {
            this.data = data;
            this.channel = channel;
            this.key = key;
        }

        @Override
        public void run() {
            String data=new String(this.data);
            CompletableFuture<String> responseFuture = CompletableFuture.supplyAsync(() -> {
                System.out.printf("request data: %s\n",data);
                System.out.println("do something....");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String response = "i can't understande you!";
                if (data.equals("i am bya")) {
                    response = "hello bya";
                }
                return response;
            }, executorService);
            try {
                key.attach(responseFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(!(SelectionKey.OP_WRITE==(key.interestOps()&SelectionKey.OP_WRITE))) {
                // 如果没有对写时间感兴趣，注册写
//                try {
//                    channel.register(key.selector(), SelectionKey.OP_WRITE, response);
//                } catch (ClosedChannelException e) {
//                    e.printStackTrace();
//                }
                key.interestOps(key.interestOps()+SelectionKey.OP_WRITE);
            }

        }
    }

}
