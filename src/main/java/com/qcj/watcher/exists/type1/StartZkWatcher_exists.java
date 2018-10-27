package com.qcj.watcher.exists.type1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 触发exists监听类
 */
public class StartZkWatcher_exists {
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, null);

        //创建这个节点
        zk.create("/idea_exit1", "gg".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
}
