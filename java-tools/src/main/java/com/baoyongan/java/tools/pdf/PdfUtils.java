package com.baoyongan.java.tools.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

public class PdfUtils {

    private final static Logger log = LoggerFactory.getLogger(PdfUtils.class);

    // 利用模板生成pdf，这将直接保存到指定路径
    public static void pdfout(Map<String, Object> o, String templatePath, String newPDFPath) {

        PdfReader reader;
        FileOutputStream out = null;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper;
        try {
            //系统字体
            String prefixFont = "";
            String os = System.getProperties().getProperty("os.name");
            if (os.startsWith("win") || os.startsWith("Win")) {
                prefixFont = "C:\\Windows\\Fonts" + File.separator;
            } else {
                prefixFont = "/usr/share/fonts/chinese" + File.separator;
            }
            //必须加“，0”或“，1”，否则会报错：com.itextpdf.text.DocumentException: Font 'C:\Windows\Fonts\simsun.ttc' with 'Identity-H' is not recognized.
            BaseFont bf = BaseFont.createFont(prefixFont + "simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            out = new FileOutputStream(newPDFPath);     // 输出流
            reader = new PdfReader(templatePath);       // 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String, String> datemap = (Map<String, String>) o.get("datemap");
            form.addSubstitutionFont(bf);
            for (String key : datemap.keySet()) {
                //为了文字可以有下划线，并且换行，控制每行字数，当字数超过时，将剩余文字填充至下一备选域
                if ("hzbz".equals(key)) {
                    String hzbz = datemap.get(key);
                    String[] hzbzArray = stringToStringArray(hzbz, 24);

                    for (int i = 0; i < hzbzArray.length; i++) {
                        String fkey = "hzbz-line" + (i + 1);
                        form.setField(fkey, hzbzArray[i]);
                    }

                } else if ("bz".equals(key)) {

                    String bz = datemap.get(key);
                    String[] bzArray = stringToStringArray(bz, 24);

                    for (int i = 0; i < bzArray.length; i++) {
                        String fkey = "bz-line" + (i + 1);
                        form.setField(fkey, bzArray[i]);
                    }

                } else {
                    String value = datemap.get(key);
                    form.setField(key, value);
                }

            }
            //图片类的内容处理
            Map<String, Image> imgmap = (Map<String, Image>) o.get("imgmap");
            for (String key : imgmap.keySet()) {
                Image value = imgmap.get(key);
                //String imgpath = value;
                Image image = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                //Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.close();
            Document doc = new Document(PageSize.A4, 50, 40, 40, 50);
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();

            //form.getTotalRevisions();
            int pages = stamper.getReader().getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), i);
                copy.addPage(importPage);
            }
            doc.close();
        } catch (IOException e) {
            log.error("pdfout", e);
        } catch (DocumentException e) {
            log.error("pdfout", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {

                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {

                }

            }

        }
    }

    // 将字符串按照指定长度分割成字符串数组
    public static String[] stringToStringArray(String src, int length) {
        //检查参数是否合法
        if (null == src || src.equals("")) {
            return null;
        }

        if (length <= 0) {
            return null;
        }
        int n = (src.length() + length - 1) / length; //获取整个字符串可以被切割成字符子串的个数
        String[] split = new String[n];
        for (int i = 0; i < n; i++) {
            if (i < (n - 1)) {
                split[i] = src.substring(i * length, (i + 1) * length);
            } else {
                split[i] = src.substring(i * length);
            }
        }
        return split;
    }

    public static byte[] inputstream2Bytes(InputStream inStream) throws IOException {
        byte[] in_b = null;
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            in_b = swapStream.toByteArray(); //in_b为转换之后的结果

        } catch (Exception e) {
            log.error("inputstream2Bytes", e);
        } finally {
            inStream.close();
        }
        return in_b;
    }

    /**
     * @Description: 文件转流
     */
    public static InputStream file2InputStream(File file) throws IOException {
        return new FileInputStream(file);
    }
}
