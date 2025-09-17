package com.hli.order.integration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class KafkaConsumerTest {
    public static void main(String[] args) {
        //todo 创建配置对象
        Map<String,Object> consumerConfigs = new HashMap<>();
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //反序列化
        consumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, "hli");

        //todo 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfigs);


        //todo 订阅主题
        consumer.subscribe(Collections.singleton("test"));

        //todo 从kafka的主题中拉取数据
        // 消费者从kafka中拉取数据(根据自己的消费能力拉取)此处设置超时时间100s
        while (true){
            ConsumerRecords<String, String> datas = consumer.poll(Duration.ofSeconds(100));
            //遍历获取到的数据
            for (ConsumerRecord<String, String> data : datas) {
                System.out.println(data.value());
            }
        }
        //todo 关闭消费者对象
        //consumer.close();
    }
}