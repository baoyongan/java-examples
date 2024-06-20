package com.baoyongan.java.tools.file;

import java.io.File;

public class FileUtils {

    public static void repairPath (String path){
        // 原始路径
        System.out.println("原路径：" + path);
        // 使用 File.separator 替换斜杠
//        String correctedPath = path.replaceAll("\\\\", File.separator);
        String correctedPath = path.trim().replaceAll("\\\\", "/").replace("//", "/");

        // 打印修正后的路径
        System.out.println("Corrected path: " + correctedPath);


    }

    public static void main(String[] args) {
        File file = new File("D:\\workspace\\java-examples\\java-tools\\src\\main\\java\\com\\baoyongan\\java\\tools\\file\\FileUtils.java");
        System.out.println(file.getAbsoluteFile());
        repairPath("\\PUB\\CFKH\\SQHT\\证件反面照_B156496.jpg");
        repairPath("//PUB/CFKH/TZZM/7844/初天罡_投资经历证明_1.jpg");

        repairPath(" \\PUB\\CFKH\\SQHT\\证件反面照_B156496.jpg");
        repairPath("");
    }
}
