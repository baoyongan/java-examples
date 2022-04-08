package com.bya.redis.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {

        GenericObjectPoolConfig pools=new GenericObjectPoolConfig();
        pools.setMaxIdle(0);
        pools.setMinIdle(0);
        pools.setMaxTotal(2);
        pools.setMaxWaitMillis(1000);

        Set<HostAndPort> hosts=new HashSet<>();
        hosts.add(new HostAndPort("192.16.1.86",7000));
        hosts.add(new HostAndPort("192.16.1.86",7001));
        hosts.add(new HostAndPort("192.16.1.86",7002));
        hosts.add(new HostAndPort("192.16.1.86",7003));
        hosts.add(new HostAndPort("192.16.1.86",7004));
        hosts.add(new HostAndPort("192.16.1.86",7005));
        JedisCluster jedisCluster = new JedisCluster(hosts, 3000, 3,pools);

        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (Map.Entry<String, JedisPool> entry: clusterNodes.entrySet()) {
            System.out.println("++++++++++++++++++++");
            Jedis resource = entry.getValue().getResource();
            System.out.println(resource.info());
            System.out.println(resource.configGet("role"));
            resource.close();
        }

        jedisCluster.close();
    }
}
