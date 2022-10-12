package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SocketNioServerAdvance extends NioServer {

    final ServerSocketChannel serverChannel;
    final Selector selecter;
    int port;
    AtomicInteger clientCount=new AtomicInteger(0);
    volatile boolean stop;
    final ExecutorService executor;

    public SocketNioServerAdvance(int port,int coresize) throws IOException {
        this.port = port;
        this.serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        this.selecter = Selector.open();
        serverChannel.register(selecter, SelectionKey.OP_ACCEPT); // 将 channel 注册到 选择器上
        executor=Executors.newFixedThreadPool(coresize);
    }

    private class Handler implements Runnable{

        private ExecutorService executor;

        @Override
        public void run() {

        }
    }

    public void run() throws IOException {
        while (!stop) {
            selecter.select();
            for (Iterator<SelectionKey> it = selecter.selectedKeys().iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isAcceptable()) {

                } else if (key.isReadable()) {

                } else if (key.isWritable()) {

                }
            }

        }
    }

    public static void main(String[] args) throws IOException {

    }

}
