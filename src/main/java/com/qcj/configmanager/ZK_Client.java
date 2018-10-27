package com.qcj.configmanager;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZK_Client {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000,null);

        String path = "/config/hdfs-site.xml";
        //创建节点
        zk.create("/config/hdfs-site3333.xml","rr".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //触发监听 nodedatachanged
        //zk.setData(path,"dfs.replication=33".getBytes(),-1);

        //触发nodedeleted
        //zk.delete(path,-1);
        //zk.delete("/config/hdfs-site3.xml",-1);


        //触发nodeCreated  创建节点
        //zk.create("/config/yarn-site.xml","rr".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //zk.delete("/config/yarn-site.xml",-1);
    }
}
