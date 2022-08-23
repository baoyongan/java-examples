package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

public class SocketNioServer {





    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8085));
        Selector selecter = Selector.open();
        serverChannel.register(selecter, SelectionKey.OP_ACCEPT); // 将 channel 注册到 选择器上

        boolean stopped=false;

        while (!stopped){
            selecter.select();
            for (Iterator<SelectionKey> it = selecter.selectedKeys().iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();
                if(key.isAcceptable()){
                    // 服务器已就绪，可以处理客户端的连接了。
                    ServerSocketChannel server  = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selecter,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    // 读事件已经就绪
//                    key.channel();

                }
            }

        }
    }
}
