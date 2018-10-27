package com.qcj.task;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 *  4.触发exists循环监听类LoopAddWatcherServer
 */
public class LoopAddWatcherClient {
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, null);

        //创建这个节点
        zk.create("/idea_exit2", "gg".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //zk.delete("/idea_exit2",-1);
    }
}
