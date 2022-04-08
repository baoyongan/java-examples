package com.baoyongan.java.tools.html;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baoyongan.java.tools.utils.HttpsUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HtmlParseJsoupDpcq {

    public static void main(String[] args) throws IOException, ScriptException, InterruptedException {
        jsoupExamples();
//        appendWriteFile("D:/tmp/MP3.txt", "1");
//        appendWriteFile("D:/tmp/MP3.txt", "1");
//        appendWriteFile("D:/tmp/MP3.txt", "1");
//        appendWriteFile("D:/tmp/MP3.txt", "1");
    }

    private static void jsoupExamples() throws IOException, ScriptException, InterruptedException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine jsEngine = sem.getEngineByName("js");
        jsEngine.eval("" +
                "var FonHen_JieMa=function (u){\n" +
                "\tvar tArr = u.split(\"*\");\n" +
                "\tvar str = '';\n" +
                "\tfor(var i=1,n=tArr.length;i<n;i++){\n" +
                "\t\tstr += String.fromCharCode(tArr[i]);\n" +
                "\t}\n" +
                "\treturn str;\n" +
                "}");
        Document doc = Jsoup.connect("http://m.tingshubao.com/book/26.html?ivk_sa=1024320u").get();

        Elements select = doc.select("#playlist li a");
        for (Element e : select) {
            // http://m.tingshubao.com/video/?26-0-1655.html
            String href = e.attr("href");
            String url = "http://m.tingshubao.com" + href;
            Document medieDoc = Jsoup.connect(url).get();
            Elements scripts = medieDoc.select("script");

            Element script = scripts.get(4);
            String html = script.html();
            String content = html.substring(0, html.indexOf(";") + 1);
            System.out.println("##########");
            System.out.println(content);
            jsEngine.eval(content);
            jsEngine.eval("var urlss=datas[0]");
            Object urlss = jsEngine.get("urlss");
            String urlkey = "https://www.gushiciju.com/player/key.php?url=" + urlss;
            byte[] bytes = HttpsUtil.doGet(urlkey);
            JSONObject jsonObject = JSON.parseObject(new String(bytes, "utf-8"));
            String mp3Url = jsonObject.getString("url");
            System.out.println(mp3Url);
            appendWriteFile("D:/tmp/MP3.txt", mp3Url);
            Thread.sleep(5000);
//            String fileName = mp3Url.substring(mp3Url.lastIndexOf("/") + 1, mp3Url.lastIndexOf("?"));
//            System.out.println(fileName);
//            File file = ResourceUtils.getFile(mp3Url);
//            String filePath = "D:/tmp/MP3/" + fileName;
//            saveToFile(mp3Url, filePath);
//            byte[] bytes1 = HttpsUtil.doGet(mp3Url);
//            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
//
//            fileOutputStream.write(mp3);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            int a = 1 / 0;
        }


    }

    public static void appendWriteFile(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
//            randomFile.writeBytes();
            content+="\r\n";
            byte[] cc=content.getBytes(StandardCharsets.UTF_8);
            randomFile.write(cc);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(String destUrl, String fileName) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpconn = null;
        URL url = null;
        byte[] buf = new byte[10240];
        int size = 0;

        // 建立链接
        url = new URL(destUrl);
        httpconn = (HttpURLConnection) url.openConnection();
        //        Accept: */*
        //Accept-Encoding: identity;q=1, *;q=0
        //Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6
        //Cache-Control: no-cache
        //Connection: keep-alive
        //Host: t3344t.tingchina.com
        //Pragma: no-cache
        //Range: bytes=0-
        //Referer: http://m.tingshubao.com/
        //sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="96", "Microsoft Edge";v="96"
        //sec-ch-ua-mobile: ?0
        //sec-ch-ua-platform: "Windows"
        //Sec-Fetch-Dest: audio
        //Sec-Fetch-Mode: no-cors
        //Sec-Fetch-Site: cross-site
        //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36 Edg/96.0.1054.34
        httpconn.setRequestProperty("Accept", "*/*");
        httpconn.setRequestProperty("Referer", "http://m.tingshubao.com/");
        httpconn.setRequestProperty("Sec-Fetch-Dest", "audio");
        httpconn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36 Edg/96.0.1054.34");
//        httpconn.setRequestProperty("Accept","*");
//        httpconn.setRequestProperty("Accept","*");
        // 连接指定的资源
        httpconn.connect();
        // 获取网络输入流
        bis = new BufferedInputStream(httpconn.getInputStream());
        // 建立文件
        fos = new FileOutputStream(fileName);

        System.out.println("正在获取链接[" + destUrl + "]的内容\n将其保存为文件[" + fileName
                + "]");
        // 保存文件
        while ((size = bis.read(buf)) != -1)
            fos.write(buf, 0, size);
        fos.close();
        bis.close();
        httpconn.disconnect();
    }
}
