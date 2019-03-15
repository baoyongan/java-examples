package com.baoyongan.java.eg.base.process_ch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InvokeOuterProcess {
    public static void main(String[] args) throws InterruptedException {

//        diaoqichengxu();
        diaoqichengxu_zhidaoguanbi();


    }

    private static void diaoqichengxu_zhidaoguanbi() {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("notepad.exe");
            new StreamWriter(p.getInputStream(), System.out);
            new StreamWriter(p.getErrorStream(), System.err);
            p.waitFor(); // 如果有必要 引起当前线程等待，直到程序终止
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("我想被打印...");
    }

    private static void diaoqichengxu() throws InterruptedException {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("notepad.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("我想被打印...");

        Thread.sleep(50000);
        System.out.println("notepad is alive "+p.isAlive());
    }

    static class StreamWriter extends Thread {
        OutputStream os;
        InputStream is;

        StreamWriter(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
            start();
        }

        public void run() {
            byte b[] = new byte[80];
            int rc;
            try {
                while ((rc = is.read(b)) > 0) {
                    os.write(b, 0, rc);
                }
            } catch (IOException e) {
            }
        }
    }
}
