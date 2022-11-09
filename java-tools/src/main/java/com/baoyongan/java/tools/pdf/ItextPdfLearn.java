package com.baoyongan.java.tools.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class ItextPdfLearn {

    public static void main(String[] args) {




        // 使用iTextAsian.jar中的字体，FontFactory方式
        Font font = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK);
        createPDF(font);


    }

    private static void createPDF(Font font){
        String path = "D:/tmp/"+System.currentTimeMillis()+".pdf";
        File file = new File(path);
        Document doc = new Document(PageSize.A4);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            PdfWriter.getInstance(doc, os);
            doc.open();
            doc.add(new Paragraph("字体测试", font));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            doc.close();
            if(null!=os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
