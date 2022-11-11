package com.baoyongan.java.tools.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleTest {

    public static void main(String[] args) {
        PdfReader reader = null;
        ByteArrayOutputStream bos = null;
        PdfStamper ps = null;
        OutputStream fos = null;
        try {
            reader = new PdfReader("C:\\Users\\baoya\\Desktop\\wts_md1.pdf");
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);
            // 设置字体
            BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            ArrayList<BaseFont> fontList = new ArrayList<>();
//            fontList.add(font);
            // 参数处理
            AcroFields acroFields = ps.getAcroFields();
            for (String key:
                 acroFields.getFields().keySet()) {
                System.out.println(key);
            }
//            acroFields.setSubstitutionFonts(fontList);
            acroFields.addSubstitutionFont(font);

            acroFields.setField("jia_name", "北京中车宝联科技有限责任公司");
            acroFields.setField("yi_name", "王二狗");
            acroFields.setField("yewu_name", "机动车驾驶证延期");
            acroFields.setField("shijian", "2022年11月15日");

            ps.setFormFlattening(true);
            ps.close();
            fos = new FileOutputStream("D:/tmp/wts_" + System.currentTimeMillis() + ".pdf");
            fos.write(bos.toByteArray());
//            fos.flush();
//            fos.close();
//            bos.close();
            System.out.println("over");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != reader) {
                reader.close();
            }
        }
    }
}
