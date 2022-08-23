package com.bya.eg.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.IsolationLevel;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Properties;

/**
 * 消费主题 my_topic_b , 主题分区中主要包含 事务消息
 * 在读取事务消息时候，如果设置 隔离级别 为读已提交，那么只有提交的事务消息才能被被读取，如果是默认读未提交，那么即使生产者没有进行commit,消息也可以被读取。
 */
public class ConsumerTopicB {
    public static final String TOPIC_NAME = "my_topic_b";

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm1.feelfly.com:9092,vm2.feelfly.com:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // 手动提交offset
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup_b");
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed" );


        Thread t0 = new Thread(() -> {
            KafkaConsumer consumer = new KafkaConsumer(props);
            // 指定订阅主题，并指定 offset 位置。
            consumer.subscribe(Arrays.asList(TOPIC_NAME), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    for (TopicPartition p : partitions) {
//                        consumer.committed()
                        long offset=getlocalOffset(p);
                        System.out.println(", Seeking to " + offset);
                        consumer.seek(p, offset);
                    }
                }

                private long getlocalOffset(TopicPartition p) {
                    System.out.printf("分区：%s, %d",p.topic(),p.partition());
                    return 1053;
                }
            });
            // 指定订阅主题和分区
//            consumer.assign(Stream.of(new TopicPartition(TOPIC_NAME, 0)).collect(Collectors.toList()));
            // 从指定的 offset 位置开始消费

            String name = Thread.currentThread().getName();
            boolean go = true;
            while (go) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("线程=%s ,分区= %d, offset = %d, key = %s, value = %s%n", name, record.partition(), record.offset(), record.key(), record.value());
                }
            }

            consumer.close();
        }, "t0");
        t0.start();
        t0.join();
        System.out.println("Over ");
    }
}
