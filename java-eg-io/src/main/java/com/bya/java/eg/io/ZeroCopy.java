package com.bya.java.eg.io;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 零拷贝技术
 * 以下实例，以读写过程为例。
 */
public class ZeroCopy {

    public static void main(String[] args) throws IOException {

        // 方式一： mmap+write（4次切换，2次DMA拷贝，1次cpu拷贝）
        mmapWrite("D:/temp/1.txt","D:/temp/2.txt");
    }

    /**
     * mmap+write（4次切换，2次DMA拷贝，1次cpu拷贝）
     * 将文件src.txt 内容通过mmap+write拷贝到 des.txt
     */
    private static void mmapWrite(String srcPath, String desPath) throws IOException {
        FileChannel srcChannel = FileChannel.open(Paths.get(srcPath), StandardOpenOption.READ);
        // 创建改文件的映射  注意 position 参数：文件中映射区域要开始的位置，size参数：要映射的区域的大小
        MappedByteBuffer mapData = srcChannel.map(FileChannel.MapMode.READ_ONLY, 0,srcChannel.size());
        // 目标写入文件
        FileChannel desChannel = FileChannel.open(Paths.get(desPath),StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        // 写入
        desChannel.write(mapData);
        desChannel.close();
        srcChannel.close();
    }
}
