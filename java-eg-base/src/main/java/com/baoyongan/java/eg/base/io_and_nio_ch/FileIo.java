package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileIo {


    public static void main(String[] args) {
        //  传统Io
//        method2();
        //  nio
        method1();
    }

    public static void method1(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("D:/Temp/1.txt","rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            System.out.println("after allocate position:"+buf.position());
            int bytesRead = fileChannel.read(buf);
            System.out.println("after channel read position:"+buf.position());
            System.out.println(bytesRead);
            while(bytesRead != -1)
            {
                buf.flip();
                System.out.println("after flip position:"+buf.position());
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                    System.out.println("after get position:"+buf.position());
                    System.out.print("|");
                }
                buf.compact();
                System.out.println("after compact position:"+buf.position());
                bytesRead = fileChannel.read(buf);
                System.out.println("after channel read position:"+buf.position());
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public static void method2() {
        InputStream in = null;
        ByteArrayOutputStream oio = null;
        try {
            oio = new ByteArrayOutputStream();
            in = new FileInputStream("D:/Temp/1.txt");
            byte[] buf = new byte[1024];
            int offset;
            while (-1 != (offset = in.read(buf))) {
                oio.write(buf, 0, offset);
            }
            System.out.println(new String(oio.toByteArray(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (oio!=null){
                try {
                    oio.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    oio.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
