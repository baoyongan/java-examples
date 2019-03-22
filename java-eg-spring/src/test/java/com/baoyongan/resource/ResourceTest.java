package com.baoyongan.resource;

import com.baoyongan.BaseTest;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;

public class ResourceTest extends BaseTest {

    private static final String BASE_URL = "http://static.zhongchebaolian.com/photobase";

    @Test
    public void urlResourceTest() throws IOException {
        BufferedReader reader=null;
        try {
           reader= IOUtils.toBufferedReader(new FileReader(new File("D:/b1.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (reader==null) {
            System.err.println("FIlE 读取失败");
            return;
        }

        String line= null;
        while (!StringUtils.isEmpty(line=reader.readLine())){
            String url=BASE_URL+line.trim();
            System.out.println(url);
            String fileName=getFileName(url);
            if(StringUtils.isEmpty(fileName)){
                System.out.println("filename parse error");
                return;
            }
            copyUrlResourceToFile("D:/zcbl/b1/",url,fileName);
        }
        reader.close();
    }

    private void copyUrlResourceToFile(String basePath, String url, String fileName) {
        Resource resource = context.getResource(url);
        try {
            FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(new File(basePath+fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileName(){
        System.out.println(getFileName("http://static.zhongchebaolian.com/photobase/photos/breachphoto2016/2019-03-20/f03fe0af6ab1880443984cace122d8c9/f03fe0af6ab1880443984cace122d8c9_photo_20190320152904691.jpg"));
    }

    private String getFileName(String url) {
        if(StringUtils.isEmpty(url)){
            return null;
        }
        return url.substring(url.lastIndexOf("/")+1);
    }


    @Test
    public void contextResourceTest() {
//        Resource resource= context.getResource("com/baoyongan/bean/Foo.txt");
        Resource resource = context.getResource("com/baoyongan/bean/Foo.class");

        boolean has = resource.exists();
        Assert.isTrue(has, "文件不存在");

        System.out.println(resource.getFilename());

        String fileContent = null;
        try {
            fileContent = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileContent);
    }
}
