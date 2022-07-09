package com.baoyongan.java.tools.html;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HtmlParseLocal {

    public static void main(String[] args) throws IOException {
        jsoupExamples("http://staticx.zhongchebaolian.com/photobase/photos/signprocesspage//2022-05-13/4419202205130955105174289/016116eb-9c46-4915-89ad-2ee9f163062dpicture.html");
    }

    private static void jsoupExamples(String url) throws IOException {
        String sub1 = url.substring(0, url.lastIndexOf("/"));
        String taskno=sub1.substring(sub1.lastIndexOf("/")+1);
        System.out.println(taskno);
        Document doc = Jsoup.connect(url).get();
        String rootPath="D:/tmp/html/";
        Elements imgs = doc.select("img");
        for (Element e : imgs) {
            String src = e.attr("src");
            String subFile = src.substring(src.indexOf("photos/")+7);
            System.out.println(subFile);
            String relaxpath="imgs/"+subFile;
            String filePath=rootPath+"imgs/"+subFile;
            String dirPath=filePath.substring(0,filePath.lastIndexOf("/"));
            // 自动创建目录
            File file = new File(dirPath);
            if(!file.exists()){
                file.mkdirs();
            }
            FileUtils.writeByteArrayToFile(new File(filePath),getImg(src));
            System.out.println(e.attr("src"));
            System.out.println(filePath);
            e.attr("src",relaxpath);
            String newSrc = e.attr("src");
            System.out.println(newSrc);
        }
        // 保存 html
        FileUtils.writeByteArrayToFile(new File(rootPath+"/"+taskno+".html"), doc.toString().getBytes(StandardCharsets.UTF_8));

    }

    public static byte[] getImg(String imgUrl){
        InputStream in=null;
        ByteArrayOutputStream out=null;
        try {
            // 获取图片URL
            URL url = new URL(imgUrl);
            // 获得连接
            URLConnection connection = url.openConnection();
            // 设置10秒的相应时间
            connection.setConnectTimeout(10 * 1000);
            // 获得输入流
            in = connection.getInputStream();
            // 获得输出流
           out=new ByteArrayOutputStream();
            // 构建缓冲区
            byte[] buf = new byte[1024];
            int size;
            // 写入到文件
            while (-1 != (size = in.read(buf))) {
                out.write(buf, 0, size);
            }
            return  out.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
