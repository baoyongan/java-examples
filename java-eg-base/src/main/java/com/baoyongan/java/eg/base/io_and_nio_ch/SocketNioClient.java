package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class SocketNioClient {
    public static void main(String[] args) throws IOException {

        SocketChannel channel=SocketChannel.open();
        channel.configureBlocking(false);
        Selector selector= Selector.open();
        channel.connect(new InetSocketAddress("127.0.0.1",8085));
        channel.register(selector, SelectionKey.OP_CONNECT);

        boolean stop=false;

        while (!stop){
            selector.select();
            for (Iterator<SelectionKey> it = selector.selectedKeys().iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();

                if(key.isConnectable()){
                    System.out.println("客户端已经连接服务端");
                    // 连接ok
                    SocketChannel c1= (SocketChannel) key.channel();
                    if(c1.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                    // TODO 发送消息
                    ByteBuffer temp= ByteBuffer.allocate(1024);
                    String info = "i am bya";
                    temp.put(info.getBytes(StandardCharsets.UTF_8));
                    temp.flip();
                    while (temp.hasRemaining()){
                        channel.write(temp);
                        System.out.println("写入了");
                    }
                    System.out.println("连接over.");
                }else if(key.isReadable()){
                    System.out.println("读就绪");
                    // 处理读取操作
                }else {
                  new UnsupportedOperationException("不支持的操作: "+key);
                }
            }
        }
    }
}
