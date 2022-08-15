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
 * 消费- 一个消费小组，消费者数量大于分区数，多余的消费者会空闲，一个分区的消息只能被一个消费者处理；
 * [root@vm1 kafka_2.13-3.2.1]# ./bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group mygroup
 *
 * GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                             HOST            CLIENT-ID
 * mygroup         my_topic_a      0          800             800             0               consumer-mygroup-1-4cc6eb92-ec66-4e3b-9ead-aa6c5b4b83e3 /192.168.111.1  consumer-mygroup-1
 * mygroup         my_topic_a      1          600             600             0               consumer-mygroup-2-b01a6b3c-d1d6-4c3e-8e79-4eb3661e7089 /192.168.111.1  consumer-mygroup-2
 * mygroup         my_topic_a      2          600             600             0               consumer-mygroup-3-e7840c93-5875-42fe-b704-0c0c9a2e2733 /192.168.111.1  consumer-mygroup-3
 * 消费者数量小于分区数，会有一个消费者同时消费多个分区的数据。
 * [root@vm1 kafka_2.13-3.2.1]# ./bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group mygroup
 *
 * GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                             HOST            CLIENT-ID
 * mygroup         my_topic_a      0          1600            1600            0               consumer-mygroup-1-55974032-9cca-4861-b8c8-d65f08383636 /192.168.111.1  consumer-mygroup-1
 * mygroup         my_topic_a      1          1200            1200            0               consumer-mygroup-1-55974032-9cca-4861-b8c8-d65f08383636 /192.168.111.1  consumer-mygroup-1
 * mygroup         my_topic_a      2          1200            1200            0               consumer-mygroup-2-dfbf22d3-601d-4af0-913d-ec1e86e2d140 /192.168.111.1  consumer-mygroup-2
 */
public class ConsumerTopicA {
    public static final String TOPIC_NAME = "my_topic_a";

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "vm1.feelfly.com:9092,vm2.feelfly.com:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup");


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

        Thread t1 = new Thread(() -> {
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
        }, "t1");

        /*Thread t2 = new Thread(() -> {
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
        t1.start();
//        t2.start();
//        t3.start();

        t0.join();
        t1.join();
//        t2.join();
//        t3.join();

        System.out.println("Over ");
    }
}
