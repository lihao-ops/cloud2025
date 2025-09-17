package com.hli.product.integration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class KafkaProducerTest {
    public static void main(String[] args) {
        //todo 创建生产者对象配置
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //对生产的数据k,v进行序列化(这里简单都用String序列化)
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //todo 创建生产者对象
        // 生产者对象需要设定泛型参数，指定key和value(数据类型的约束)
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

        //todo 创建数据
        //  构建数据时,需要传递三个参数
        //  1.表示 topic,2.key,3.value
        for (int i = 0; i < 1000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", "key", "value" + i);
            //todo 通过生产者对象将数据发送到kafka
            producer.send(record);
        }
        //todo关闭生产者对象,节省资源
        producer.close();
    }
}