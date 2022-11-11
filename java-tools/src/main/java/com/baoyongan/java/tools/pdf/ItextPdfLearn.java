package com.baoyongan.java.tools.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class ItextPdfLearn {

    public static void main(String[] args) throws IOException, DocumentException {

        // 使用classpath 中的字体 ,不行，路径有问题
        Font font = new Font(BaseFont.createFont("./simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED),16f,Font.NORMAL,BaseColor.BLACK);
        // 使用系统字体
//        Font font = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED),16f,Font.NORMAL,BaseColor.BLACK);

        // 使用iTextAsian.jar中的字体，FontFactory方式
//        Font font = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK);
        // 使用iTextAsian.jar中的字体，create font 方式
//        Font font = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED),10f,Font.NORMAL,BaseColor.BLACK);
        createPDF(font,"字体测试，为什么就不能多说点呢，嗯嗯，是吧,这是宋体");


    }

    private static void createPDF(Font font,String data){
        String path = "D:/tmp/"+System.currentTimeMillis()+".pdf";
        File file = new File(path);
        Document doc = new Document(PageSize.A4);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            PdfWriter.getInstance(doc, os);
            doc.open();
            doc.add(new Paragraph(data, font));
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
