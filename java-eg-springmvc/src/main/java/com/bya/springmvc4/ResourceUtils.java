package com.bya.springmvc4;

import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.support.ServletContextResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * spring 的resources 可以脱离Spring 容器使用
 */
public class ResourceUtils {

    public static void main(String[] args) {
        UrlResource resource;
        ClassPathResource resource1;
        FileSystemResource resource2;
        ServletContextResource resource3;
        InputStreamResource resource4;
        ByteArrayResource resource5;
        FileCopyUtils fileCopyUtils;
        PropertiesLoaderUtils propertiesLoaderUtils;

        PathResource resource6;

        ResourcePatternResolver resourcePatternResolver=new PathMatchingResourcePatternResolver();

        Resource[] resources = null;
        try {
            resources=resourcePatternResolver.getResources("classpath*:views/**/*.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Resource res:
             resources) {

            System.out.println("FileName:"+res.getFilename());
            try {
                System.out.println("FileContent:"+FileCopyUtils.copyToString(new InputStreamReader(res.getInputStream())));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
