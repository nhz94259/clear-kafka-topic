package com.ant.clearkafkatopic.commonsAndUtils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by wolf   2018/11/30
 */
@Component
public class Producer {

    @Autowired private KafkaTemplate kafkaTemplate;

    public void send( Message message) {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        this.kafkaTemplate.send("hello-topic",  json);
        System.out.println("Sent sample message [" + message + "]");
    }

}
