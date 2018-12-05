package com.ant.clearkafkatopic.commonsAndUtils;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;

import java.util.Properties;

/**
 * Created by wolf   2018/12/2
 */
public class KafkaUtil {

    public static void createKafaTopic(String ZkStr,KafkaTopicBean topic) {
        ZkUtils zkUtils = ZkUtils.
                apply(ZkStr, 30000, 30000, JaasUtils.isZkSecurityEnabled());

        AdminUtils.createTopic(zkUtils, topic.getTopicName(),  topic.getPartition(),
                topic.getReplication(),  new Properties(), new RackAwareMode.Enforced$());
        zkUtils.close();
    }

    public static void deleteKafaTopic(String ZkStr,KafkaTopicBean topic) {
        ZkUtils zkUtils = ZkUtils.
                apply(ZkStr, 30000, 30000,JaasUtils.isZkSecurityEnabled());

        AdminUtils.deleteTopic(zkUtils, topic.getTopicName());
        zkUtils.close();
    }
}
