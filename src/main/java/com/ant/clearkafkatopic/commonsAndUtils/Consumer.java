package com.ant.clearkafkatopic.commonsAndUtils;

import com.google.gson.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wolf   2018/11/30
 */
@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "hello-topic")
    public void reviceMessage(ConsumerRecord<?, ?> record){
        log.info("kafka的key: " + record.key());
        log.info("kafka的value: " + record.value().toString());
    }


}
