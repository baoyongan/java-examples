package com.feelfly.test.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.io.File;
import java.io.IOException;

public class RedissonSingleTest {

    public static void main(String[] args) {
        // 程序化配置
        Config config=new Config();
        config.setTransportMode(TransportMode.EPOLL); // TODO
        config.useSingleServer().setAddress("vm1.feelfly.com:6379");
        // 文件方式配置
        Config fileConfig=null;
        try {
            fileConfig = Config.fromYAML(new File("/redisson.yaml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        RedissonClient redissonClient=Redisson.create(config);
    }
}
