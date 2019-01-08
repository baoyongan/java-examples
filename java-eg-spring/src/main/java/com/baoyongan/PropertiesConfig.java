package com.baoyongan;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ComponentScan("com.baoyongan.bean")
@PropertySource({"classpath:config.properties"})
public class PropertiesConfig {

    @Value("${book.name}")
    private String bookName;

    @Value("http://www.baidu.com")
    private Resource url;

    @Value("#{systemEnvironment['os.name']}")
    private String osName;

    @Value("#{demoService.author}")
    private String fromAnthor;

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public String toString() {
        try {
            return "PropertiesConfig{" +
                    "bookName='" + bookName + '\'' +
                    ", url=" + IOUtils.toString(url.getInputStream(),"utf-8") +
                    ", osName='" + osName + '\'' +
                    ", fromAnthor='" + fromAnthor + '\'' +
                    ", environment=" + environment.getProperty("book.author") +
                    '}';
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
