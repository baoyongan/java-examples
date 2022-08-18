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
 *
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
            // 指定订阅主题，不指定分区
            consumer.subscribe(Arrays.asList(TOPIC_NAME), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    for (TopicPartition p : partitions) {
//                        consumer.committed()
                    }
                }
            });
            // 指定订阅主题和分区
//            consumer.assign(Stream.of(new TopicPartition(TOPIC_NAME, 0)).collect(Collectors.toList()));
            // 从指定的 offset 位置开始消费
            consumer.seek(new TopicPartition(TOPIC_NAME,0),1000);
            consumer.seek(new TopicPartition(TOPIC_NAME,1),1000);

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
