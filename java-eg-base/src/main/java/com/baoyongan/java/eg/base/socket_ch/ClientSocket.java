package com.baoyongan.java.eg.base.socket_ch;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocket {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 100; i++) {
            int k = i;
            new Thread(()->{
                try {
                    Socket socket = new Socket("127.0.0.1", 9901);
                    socket.getOutputStream().write(("is bya ? "+ k).getBytes(StandardCharsets.UTF_8));

                    byte[] result = new byte[10240];
                    socket.getInputStream().read(result);
                    System.out.println(new String(result));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
