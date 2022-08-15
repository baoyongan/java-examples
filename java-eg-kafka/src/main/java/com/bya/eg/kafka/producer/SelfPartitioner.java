package com.bya.eg.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class SelfPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        System.out.println("配置的主题分区"+partitionInfos);
        List<PartitionInfo> availdpartitionInfo = cluster.availablePartitionsForTopic(topic);
        System.out.println("可用的的主题分区"+availdpartitionInfo);
        String k= (String) key;
        int n=k.charAt(k.length()-1);
        if(null!=availdpartitionInfo && !availdpartitionInfo.isEmpty()){
            return n % availdpartitionInfo.size();
        }else {
            return n % partitionInfos.size();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
