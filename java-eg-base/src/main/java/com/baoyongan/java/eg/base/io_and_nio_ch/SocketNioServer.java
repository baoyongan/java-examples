package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SocketNioServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8085));
        Selector selecter = Selector.open();
        serverChannel.register(selecter, SelectionKey.OP_ACCEPT); // 将 channel 注册到 选择器上

        boolean stopped = false;

        while (!stopped) {
            selecter.select();
            for (Iterator<SelectionKey> it = selecter.selectedKeys().iterator(); it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isAcceptable()) {
                    System.out.println("服务器已接受客户端连接");
                    // 服务器已就绪，可以处理客户端的连接了。
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selecter, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    System.out.println("读取就绪");
                    // 读事件已经就绪
                    SocketChannel cr = (SocketChannel) key.channel();
                    cr.configureBlocking(false);
                    System.out.println(cr.getRemoteAddress());
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int n;
                    while (0 != (n = cr.read(buff))) {
                        buff.flip();
                        byte[] temp = new byte[n];
                        buff.get(temp, 0, n);
                        bos.write(temp, 0, temp.length);
                        buff.clear();
                    }
                    String data = new String(bos.toByteArray());
                    System.out.println("读取到的内容：");
                    System.out.println(data);
                    // TODO handler
                    // toDo result wire
                    bos.flush();
                    bos.close();
                }
            }

        }
    }
}
