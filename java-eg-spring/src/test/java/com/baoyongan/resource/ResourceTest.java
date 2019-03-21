package com.baoyongan.resource;

import com.baoyongan.BaseTest;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;

public class ResourceTest extends BaseTest {

    @Test
    public void contextResourceTest(){
//        Resource resource= context.getResource("com/baoyongan/bean/Foo.txt");
        Resource resource= context.getResource("com/baoyongan/bean/Foo.class");

        boolean has=resource.exists();
        Assert.isTrue(has,"文件不存在");

        System.out.println(resource.getFilename());

        String fileContent = null;
        try {
            fileContent=FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileContent);
    }
}
