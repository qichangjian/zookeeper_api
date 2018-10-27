package com.qcj.connect;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 测试连接zookeeper
 */
public class TestZKConnect {
    public static void main(String[] args) throws IOException {
        //创建一个zk对象
        /**
         * connectString:zk连接的url  string类型
         * 	多个节点需要连接，多个节点之间使用，分割开
         * sessionTimeout:超时时间
         * watcher：监听 不需要监听 传null
         */
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, null);
        System.out.println(zk);
    }
}
