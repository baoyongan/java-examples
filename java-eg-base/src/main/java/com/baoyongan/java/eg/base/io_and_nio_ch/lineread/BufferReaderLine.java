package com.baoyongan.java.eg.base.io_and_nio_ch.lineread;

import java.io.*;

public class BufferReaderLine extends LineReadIterable {

    private BufferedReader br;
    private String currentLine;
    private int row;

    public BufferReaderLine(File file) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    }

    public int getNowRow() {
        return row;
    }

    @Override
    public boolean hasNext() {
        try {
            currentLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == currentLine) {
            return false;
        }
        row++;
        return true;
    }

    @Override
    public String next() {
        return currentLine;
    }

//    @Override
//    protected void postHandle() {
//        if(null!=br) {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    protected void preHandle() {
//
//    }

    public void close(){
        if(null!=br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        BufferReaderLine r=null;
        try {
            r=new BufferReaderLine(new File("D:/tmp/pi-10million.txt"));
            r.ParseLine(s -> {
                System.out.println(s);
            });
            System.out.printf("共 %d 行\n",r.getNowRow());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(null!=r)
                r.close();
        }
    }
}
