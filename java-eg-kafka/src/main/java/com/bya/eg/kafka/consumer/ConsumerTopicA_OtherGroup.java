package com.bya.eg.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 测试两个分组的情况
 */
public class ConsumerTopicA_OtherGroup {
    public static final String TOPIC_NAME = "my_topic_a";

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm1.feelfly.com:9092,vm2.feelfly.com:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "othergroup");


        Thread t0 = new Thread(() -> {
            KafkaConsumer consumer = new KafkaConsumer(props);
            // 指定订阅主题，不指定分区
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
            // 指定订阅主题和分区
//            consumer.assign(Stream.of(new TopicPartition(TOPIC_NAME, 0)).collect(Collectors.toList()));
            String name = Thread.currentThread().getName();
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("线程=%s ,分区= %d, offset = %d, key = %s, value = %s%n", name, record.partition(), record.offset(), record.key(), record.value());
                }
            }
        }, "t0");

/*        Thread t1 = new Thread(() -> {
            KafkaConsumer consumer = new KafkaConsumer(props);
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
//            consumer.assign(Stream.of(new TopicPartition(TOPIC_NAME, 1)).collect(Collectors.toList()));
            String name = Thread.currentThread().getName();
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("线程=%s ,分区= %d, offset = %d, key = %s, value = %s%n", name, record.partition(), record.offset(), record.key(), record.value());
                }
            }
        }, "t1");*/

/*        Thread t2 = new Thread(() -> {
            KafkaConsumer consumer = new KafkaConsumer(props);
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
//            consumer.assign(Stream.of(new TopicPartition(TOPIC_NAME, 2)).collect(Collectors.toList()));
            String name = Thread.currentThread().getName();
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("线程=%s ,分区= %d, offset = %d, key = %s, value = %s%n", name, record.partition(), record.offset(), record.key(), record.value());
                }
            }
        }, "t2");*/

       /* Thread t3 = new Thread(() -> {
            KafkaConsumer consumer = new KafkaConsumer(props);
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
//            consumer.assign(Stream.of(new TopicPartition(TOPIC_NAME, 2)).collect(Collectors.toList()));
            String name = Thread.currentThread().getName();
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("线程=%s ,分区= %d, offset = %d, key = %s, value = %s%n", name, record.partition(), record.offset(), record.key(), record.value());
                }
            }
        }, "t3");*/

        t0.start();
//        t1.start();
//        t2.start();
//        t3.start();

        t0.join();
//        t1.join();
//        t2.join();
//        t3.join();

        System.out.println("Over ");
    }
}
