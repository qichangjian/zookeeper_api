package com.qcj.watcher.exists.type3;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 当创建对象和exists方法中都添加了watcher对象的时候，触发监听的时候，调用的是exists方法上的监听器
 *
 * 打印结果：
     * 启动此类的时候打印：
     * 方式二------------
     * None----------null
     * 触发监听的时候打印
     * 方式一------------
     * NodeDeleted----------/idea_exit2
 */
public class ZkWatcher_exists {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //exists添加监听的方式二
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("方式二------------");
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println(type+"----------"+path);
            }});

        zk.exists("/idea_exit2",new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("方式一------------");
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println(type+"----------"+path);
            }});

        //让它一直睡眠等待触发
        Thread.sleep(100000);
    }
}
