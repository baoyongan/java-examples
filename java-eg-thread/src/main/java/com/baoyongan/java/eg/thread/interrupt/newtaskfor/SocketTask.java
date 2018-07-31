package com.baoyongan.java.eg.thread.interrupt.newtaskfor;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketTask<String> extends SocketUsingTask<String>{

    private final InputStream in;

    public SocketTask(Socket socket) throws IOException {
        super.setSocket(socket);
        in=socket.getInputStream();
    }

    @Override
    public String call() throws Exception {
        try {
            byte[] buf= new byte[1024];
            while (true){
                System.out.println("runing...............");
                int count=in.read(buf);
                if(count<0){
                    break;
                }else if(count>0){
                    processBuffer(buf,count);
                }

            }
        }catch (IOException e){
            System.out.println("read by colse....");
            e.printStackTrace();
            // ignore
        }

        return null;
    }

    private void processBuffer(byte[] buf, int count) {
        System.out.println("read something...");
    }

}
