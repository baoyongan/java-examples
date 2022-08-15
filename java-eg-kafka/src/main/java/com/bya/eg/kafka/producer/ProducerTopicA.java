package com.bya.eg.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Topic A: 3分区，3副本
 * ./bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic my_topic_a --partitions 3 --replication-factor 3
 * demo 生产者定义分区策略，将消息发送到指定分区
 */
public class ProducerTopicA {
    public static void main(String[] args) throws InterruptedException {
        Properties prop=new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"vm1.feelfly.com:9092,vm2.feelfly.com:9092");
        prop.put(ProducerConfig.LINGER_MS_CONFIG,2);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.bya.eg.kafka.producer.SelfPartitioner");

        Producer<String, String> producer=new KafkaProducer<String, String>(prop);
        // 模拟多线程下发送消息
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> result=new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            int k = i;
            result.add(executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    producer.send(new ProducerRecord<>("my_topic_a", "h" + k, "h" + k), new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if(exception == null){
                                System.out.printf("发送成功, 主题：%s, 分区：%d \n",metadata.topic(),metadata.partition());
                            }else {
                                exception.printStackTrace();
                            }
                        }
                    });
                    return k;
                }
            }));
        }

        Thread t1=new Thread(()->{
            while (true){
                Map<MetricName, ? extends Metric> metrics = producer.metrics();
                metrics.forEach((k,v)->{
                    System.out.println(k+"-----"+v);
                });
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        t1.join();
        producer.close();
        System.out.println("执行完成");
    }
}


