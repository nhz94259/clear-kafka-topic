package com.ant.clearkafkatopic.commonsAndUtils;

import lombok.Data;

/**
 * Created by wolf   2018/12/2
 */

@Data
public class KafkaTopicBean {
    // topic 名称
    private String topicName;
    // partition 分区数量
    private Integer partition;
    // replication 副本数量
    private Integer replication;
    //描述
    private String descrbe;
}
