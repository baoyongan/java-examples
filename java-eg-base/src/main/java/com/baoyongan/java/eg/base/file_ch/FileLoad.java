package com.baoyongan.java.eg.base.file_ch;

import org.apache.commons.io.FilenameUtils;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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


        Path templatePath = Paths.get(System.getProperty("user.dir"), FilenameUtils.getName("templates"), FilenameUtils.getName("fileGuid_"+System.currentTimeMillis()), FilenameUtils.getName("a.pdf"));
        Path crmFilePath = Paths.get(templatePath.getParent().toString(), FilenameUtils.getName("replaced"), FilenameUtils.getName("a.pdf"));

        System.out.println(templatePath);
        System.out.println(templatePath.getParent().toString());
        System.out.println(crmFilePath);

    }
}

