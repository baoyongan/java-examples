package com.baoyongan.java.eg.base.error_ch;

import java.io.*;

public class IO {
    public static void main(String[] args) {
        String src="D:\\tmp\\woa.txt";
        String dest="D:\\tmp\\wob.txt";
        copy(src,dest);
    }

    private static void copy(String src, String dest) {
        InputStream is=null;
        OutputStream os=null;

        try{
            is=new FileInputStream(src);
            os=new FileOutputStream(dest);
            byte[] buf=new byte[1024];
            int n;
            while ((n=is.read(buf))>0){
                os.write(buf,0,n);
            }
            os.flush();
        } catch (IOException e) {
             close(is,os);
        }finally {
            close(is,os);
        }
    }

    private static void close(InputStream is, OutputStream os) {
        if(null!=is){
            try {
                is.close();
            } catch (IOException e) {
                // ignor e
            }
        }
        if (null!=os) {
            try {
                os.close();
            } catch (IOException e) {
                // ignor e
            }
        }
    }
}
