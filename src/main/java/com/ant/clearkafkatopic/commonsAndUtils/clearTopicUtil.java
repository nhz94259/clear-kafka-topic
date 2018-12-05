package com.ant.clearkafkatopic.commonsAndUtils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wolf   2018/12/3
 */
@Component
public class clearTopicUtil {

    public void clearTopic(String zookeeperaddr) {
        try {
            ZooKeeper zk = new ZooKeeper(zookeeperaddr, 10000, null);
            Thread.sleep(5000);
            List<String> ids = zk.getChildren("/brokers/topics", false);
            for (String id : ids) {
                String brokerInfo = new String(zk.getData("/brokers/topics/"
                        + id, false, null));
                Stat stat = zk.exists("/brokers/topics/" + id, false);
                if (System.currentTimeMillis() - stat.getMtime() > 7 * 24 * 3600 * 1000l) {
                    try {
                        List<String> addr = new ArrayList<String>();
                        listnode(zk,"/brokers/topics/" + id, addr);
                        for(int i=addr.size()-1;i>=0;i--){
                            zk.delete(addr.get(i), -1);
                        }
                        System.out.println("清除topic=========>" + id
                                + ",brokerInfo信息：" + brokerInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void listnode(ZooKeeper zk,String groupName,List<String> addr) throws Exception {
        String path = groupName;
        addr.add(path);
        try {
            List<String> node = zk.getChildren(path, false);
            if(node.size()>0){
                for(String n : node){
                    n=path+"/"+n;
                    listnode(zk,n,addr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
