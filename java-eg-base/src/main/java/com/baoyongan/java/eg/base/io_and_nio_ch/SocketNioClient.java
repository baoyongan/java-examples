package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class SocketNioClient extends NioServer  {
    public static void main(String[] args) throws IOException {
        SocketNioClient nioClient=new SocketNioClient();
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
                    String info = "i am bya";
                    nioClient.writeDatatoChannel(info.getBytes(StandardCharsets.UTF_8),channel);
                    System.out.printf("send message to server. server: %s message: %s \n", c1.getRemoteAddress(), info);
                }else if(key.isReadable()){
                    System.out.println("读就绪");
                    // 读事件已经就绪
                    SocketChannel cr = (SocketChannel) key.channel();
                    cr.configureBlocking(false);
                    System.out.println(cr.getRemoteAddress());
                    String data = new String(nioClient.readFromSocketChannelToString(cr),StandardCharsets.UTF_8);
                    System.out.printf("receive message from client. client: %s message: %s \n", cr.getRemoteAddress(), data);
                } else if (key.isWritable()) {
                    System.out.println("写入就绪");
                    SocketChannel cr = (SocketChannel) key.channel();
                    // 处理业务，响应结果
                    String attachment = (String) key.attachment();
                    if(null!=attachment){
                        nioClient.writeDatatoChannel(attachment.getBytes(StandardCharsets.UTF_8),cr);
                        System.out.printf("send message to server. server: %s message: %s \n", cr.getRemoteAddress(), attachment);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                } else {
                  new UnsupportedOperationException("不支持的操作: "+key);
                }
            }
        }
    }
}
