package com.baoyongan.java.eg.base.io_and_nio_ch.lineread;

import java.io.*;
import java.util.Scanner;

public class ScannerReaderLine  {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(new BufferedInputStream(new FileInputStream(new File("D:/tmp/leipzig1m.txt"))),"UTF-8");
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            System.out.println(s);
        }

    }
}
