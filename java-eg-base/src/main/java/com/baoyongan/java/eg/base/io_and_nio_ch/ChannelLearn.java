package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelLearn {

    public static void main(String[] args) throws IOException {

//        fileChannelTest("C:/tmp/1.txt");
        channelTransfer("C:/tmp/1.txt","C:/tmp/11.txt");
    }

    private static void channelTransfer(String src, String des) throws IOException {
        RandomAccessFile srcFile = new RandomAccessFile(src, "rw");
        FileChannel fromChannel = srcFile.getChannel();
        long position=0;
        long count=fromChannel.size();

        RandomAccessFile desFile=new RandomAccessFile(des,"rw");
        FileChannel toChannel = desFile.getChannel();
        toChannel.transferFrom(fromChannel,position,count);
//        fromChannel.transferTo(position,count,toChannel);
        desFile.close();
        srcFile.close();
    }

    private static void fileChannelTest(String filePath) throws IOException {
        RandomAccessFile raf=new RandomAccessFile(filePath,"rw");
        FileChannel channel = raf.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(24);
        int bytesReads;
        while (-1!=(bytesReads=channel.read(byteBuffer))){
            System.out.println("read "+bytesReads);
            byteBuffer.flip(); // limit=position; position=0
            while (byteBuffer.hasRemaining()) // position < limit
                System.out.println((char)byteBuffer.get()); // get 方法 读取此缓冲区当前位置的字节，然后增加该位置。返回：缓冲区当前位置的字节
            byteBuffer.clear();
        }
        raf.close();
    }
}
