package com.qcj.watcher.exists.type2;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 测试zk监听  exists 方式二
 *          * 添加监听的三个操作
 *          * getData
 *          * getChildren
 *          * exists
 */
public class ZkWatcher_exists {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //exists添加监听的方式二
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println(type+"----------"+path);
            }});

        /**
         * 参数二：传入boolean
         * true：需要监听，意味着前面已经指定了监听对象，
         */
        zk.exists("/idea_exit2",true);

        //让它一直睡眠等待触发
        Thread.sleep(100000);
    }
}
