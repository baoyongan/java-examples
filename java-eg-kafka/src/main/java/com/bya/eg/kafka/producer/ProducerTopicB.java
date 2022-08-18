package com.bya.eg.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Topic B: 2分区，3副本
 * ./bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic my_topic_b --partitions 2 --replication-factor 3
 * demo 生产者定义分区策略，将消息发送到指定分区
 */
public class ProducerTopicB<K,V> {

    private Logger log=Logger.getLogger(this.getClass().getName());
    private String topic_name;
    private Properties prop;
    private Producer<K,V> producer;

    public ProducerTopicB(String topic_name, Properties prop) {
        this.topic_name = topic_name;
        this.prop = prop;
        this.producer=new KafkaProducer<>(prop);
    }

    public void sendMsg (K k,V v){
        producer.send(new ProducerRecord<>(topic_name, k, v), new
                Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if(exception == null){
                            System.out.printf("发送成功, 主题：%s, 分区：%d \n",metadata.topic(),metadata.partition());
                        }else {
                            log.log(Level.WARNING,"事件发送失败",exception);
                        }
                    }
                });
    }

    public Producer<K, V> getProducer() {
        return producer;
    }

    public void sendMsg(ProducerRecord<K,V> record, Callback callback){
        producer.send(record, callback);
    }

    public Future<RecordMetadata> sendMsgFuture(ProducerRecord<K,V> record, Callback callback){
        return producer.send(record, callback);
    }

    public static void main(String[] args) throws InterruptedException {
        Properties prop=new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"vm1.feelfly.com:9092,vm2.feelfly.com:9092");
        prop.put(ProducerConfig.LINGER_MS_CONFIG,2);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.bya.eg.kafka.producer.SelfPartitioner");
        prop.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"transaction_b_myself"); // kafka 代理会检查相同id的Open transaction 并完成

        ProducerTopicB<String,String>  producerTopicB =new ProducerTopicB<String,String>("my_topic_b",prop);
        Producer<String, String> producer = producerTopicB.getProducer();

        producer.initTransactions();
        CountDownLatch countDownLatch=new CountDownLatch(1);

        // 启动一个线程控制何时执行 提交
        new Thread(()->{
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }).start();

        try {
            producer.beginTransaction();
            for (int i = 0; i < 1000; i++) {
                producerTopicB.sendMsg("b" + i, "b" + i);
            }
            System.out.println("全部发送，等待提交");
            countDownLatch.await();
            System.out.println("决定执行提交");
            producer.commitTransaction();
        }catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            producer.close();
        }catch (KafkaException e){
            producer.abortTransaction(); // 也可以重试
        }catch (Exception e){
            producer.abortTransaction();
        }
        producer.close();
        System.out.println("执行完成");
    }
}


