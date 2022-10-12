package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class SocketNioServer extends NioServer {

    public static void main(String[] args) throws IOException {
        SocketNioServer nioServer = new SocketNioServer();


        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8085));
        Selector selecter = Selector.open();
        serverChannel.register(selecter, SelectionKey.OP_ACCEPT); // 将 channel 注册到 选择器上
        AtomicInteger clientCount=new AtomicInteger(0);
        boolean stopped = false;

        while (!stopped) {
            selecter.select();
            for (Iterator<SelectionKey> it = selecter.selectedKeys().iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isAcceptable()) {
                    // 服务器已就绪，可以处理客户端的连接了。
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    int i = clientCount.incrementAndGet();
                    // 对每一个 socket 的处理，应该放在一个handler中。可以通过attach 进行传递。
                    System.out.printf("客户端 %d 进行连接\n",i);
                    channel.configureBlocking(false);
                    channel.register(selecter, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    System.out.println("读取就绪");
                    // 读事件已经就绪
                    SocketChannel cr = (SocketChannel) key.channel();
                    cr.configureBlocking(false);
                    System.out.println(cr.getRemoteAddress());
                    byte[] bytes = nioServer.readFromSocketChannelToString(cr);
                    String data = new String(bytes, StandardCharsets.UTF_8);
                    System.out.printf("receive message from client. client: %s message: %s \n", cr.getRemoteAddress(), data);
                    // 逻辑处理
                    nioServer.handler(bytes, cr, key);
                    /*if(!(SelectionKey.OP_WRITE==(key.interestOps()&SelectionKey.OP_WRITE))){
//                        如果没有对写时间感兴趣，注册写
                    cr.register(selecter, SelectionKey.OP_WRITE, data);
                    }*/
                } else if (key.isWritable()) {
                    // 只要写缓冲区可写就会立即返回，所以会多次触发，所以不需要频繁去注册写事件
                    System.out.println("写入就绪");
                    SocketChannel cr = (SocketChannel) key.channel();
                    // 处理业务，响应结果
                    String attachment = (String) key.attachment();
//                    String response="i can't understande you!";
//                    if(attachment.equals("i am bya")){
//                        response="hello bya";
//                    }
                    if (null != attachment) {
                        nioServer.writeDatatoChannel(attachment.getBytes(StandardCharsets.UTF_8), cr);
                        System.out.printf("send message to client. client: %s message: %s", cr.getRemoteAddress(), attachment);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }

        }
    }

}
