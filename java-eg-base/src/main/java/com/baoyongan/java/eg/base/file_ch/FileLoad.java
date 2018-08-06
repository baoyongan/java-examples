package com.baoyongan.java.eg.base.file_ch;

import java.io.InputStream;

public class FileLoad {


    public static void main(String[] args) {

        // 相对目录 /代表bin
        InputStream is1 = FileLoad.class.getResourceAsStream("/1.txt");
        if (null == is1)
            System.out.println("1.txt not load!");
        else{
            System.out.println("1.txt load!");
        }

        // 也是相对目录 ""代表 bin
        InputStream is2 =Thread.currentThread().getContextClassLoader().getResourceAsStream("1.txt");
        if (null == is2)
            System.out.println("1.txt not load!");
        else{
            System.out.println("1.txt load!");
        }
    }
}

