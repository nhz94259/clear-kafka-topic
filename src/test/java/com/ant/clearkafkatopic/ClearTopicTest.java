package com.ant.clearkafkatopic;

import com.ant.clearkafkatopic.commonsAndUtils.*;
import lombok.extern.slf4j.Slf4j;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wolf   2018/11/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClearTopicTest {
    @Autowired private clearTopicUtil clearTopicUtil;

    @Autowired private Producer producer;
    @Autowired private  KafkaTemplate kafkaTemplate;
    @Test
    public void createTopic(){
        //zookeeper地址：端口号
        String ZkStr = "localhost:2181";
        //topic对象
        KafkaTopicBean topic = new KafkaTopicBean();
        topic.setTopicName("test-20181202");  //topic名称
        topic.setPartition(1); //分区数量设置为1
        topic.setReplication(1); //副本数量设置为1
        //创建topic
         KafkaUtil.createKafaTopic(ZkStr,topic);
    }

    @Test
    public void clearTopic(){
        //zookeeper地址：端口号
        String ZkStr = "localhost:2181";
        //topic对象
        KafkaTopicBean topic = new KafkaTopicBean();
        topic.setTopicName("test-20181202");  //topic名称
        topic.setPartition(1); //分区数量设置为1
        topic.setReplication(1); //副本数量设置为1
        //删除topic
        KafkaUtil.deleteKafaTopic(ZkStr,topic);
    }

    @Test
    public void sendMessage(){
        for(int i=0;i<20;i++){
            Message msg = new Message(i,"第"+i+"次打招呼！");
            producer.send(msg);
        }

    }
    @Test
    public void clearTopicUtil() throws IOException {
        clearTopicUtil c = new clearTopicUtil();
        ZooKeeper zk = new ZooKeeper("localhost:2181",  10000, null);
        List<String> addr = new ArrayList<String>();

        c.clearTopic("localhost:2181");
    }


}