package com.baoyongan.java.eg.base.io_and_nio_ch;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileIo {


    public static void main(String[] args) throws IOException {
        //  传统Io
//        method2();
        //  nio
//        method1();

        // 顺序io
        int s=method3("C:/tmp/20.txt", "我都无所谓了是，你说是不是十分不是收费无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分" +
                "不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不" +
                "是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分" +
                "不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你" +
                "说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不、" +
                "是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收无所谓了是，你说是不是十分不是收", 0);
        method3("C:/tmp/20.txt","21321",s);
//        method4();

    }

    /**
     * 基于MappedByteBuffer实现顺序IO
     *
     * @param filepath 写入文件
     * @param content  待写入的内容
     * @param index    从指定位置开始写入
     * @return
     */
    private static int method3(String filepath, String content, int index) throws IOException {
        File f = new File(filepath);
        f.createNewFile();
//        RandomAccessFile accessFile = new RandomAccessFile(f, "rw");
//        FileChannel channel = accessFile.getChannel(); // 打开文件通道
        FileChannel channel = FileChannel.open(Paths.get(filepath), StandardOpenOption.READ, StandardOpenOption.WRITE);
        // 映射文件 buffer, size 要足够大，放不下 content 会溢出。
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024*3);
        map.position(index);
//        map.put(content.getBytes(StandardCharsets.UTF_8));
        //不需要指定位置，默认会写在文件尾部并更新position，当然也可以通过map.put(bytes, position, len)来指定写入的位置
        byte[] data = content.getBytes(StandardCharsets.UTF_8);
        int off = 0;
        while (off < data.length) {
            System.out.println(off);
            if (off > data.length - 1024) {
                map.put(data, off, data.length - off);
            } else {
                map.put(data, off, 1024);
            }
            off += 1024;
        }
        return map.position();
    }

    public static void method1() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("D:/temp/111.txt", "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            System.out.println("after allocate position:" + buf.position());
            int bytesRead = fileChannel.read(buf);
            System.out.println("after channel read position:" + buf.position());
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                System.out.println("after flip position:" + buf.position());
                while (buf.hasRemaining()) {
                    System.out.print("> " + (char) buf.get());
                    System.out.println(" after get position:" + buf.position());
                    System.out.print("|");
                }
                buf.compact();
                System.out.println("after compact position:" + buf.position());
                bytesRead = fileChannel.read(buf);
                System.out.println("after channel read position:" + buf.position());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void method2() {
        InputStream in = null;
        ByteArrayOutputStream oio = null;
        OutputStream os = null;
        File out = new File("C:/tmp/11.txt");
        try {
            oio = new ByteArrayOutputStream();
            in = new FileInputStream("D:/temp/1.txt");
            byte[] buf = new byte[1024];
            int offset;
            while (-1 != (offset = in.read(buf))) {
                oio.write(buf, 0, offset);
            }
            System.out.println(new String(oio.toByteArray(), "UTF-8"));
            if (out.createNewFile()) {
                os = new FileOutputStream(out);
            } else {
                os = new FileOutputStream(out, true); // 追加
            }
            os.write(oio.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oio != null) {
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

            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
