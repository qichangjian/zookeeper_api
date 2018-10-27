package com.qcj.watcher.exists.type1;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 测试zk监听  exists
 *          * 添加监听的三个操作
 *          * getData
 *          * getChildren
 *          * exists
 */
public class ZkWatcher_exists {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, null);

        //exists添加监听的方式一
        /**
         * exists的两个参数的参数二的传入的方法
         * 1.Watcher对象
         * 	如果需要监听，则需要传入一个watcher对象 如果不需要监听传入null
         * 2.boolean类型参数
         */
        zk.exists("/idea_exit1", new Watcher() {

            /**
             zk监听触发的时候调用的方法
             又叫zk的回调方法

             参数：WatchedEvent event监听事件对象
             事件类型：final private EventType eventType;
             触发事件的节点路径 ：private String path;

             答应触发监听的事件类型和节点路径
             */
            @Override
            public void process(WatchedEvent event) {
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println(type+"----------"+path);
            }});

        //让它一直睡眠等待触发
        Thread.sleep(100000);

    }
}
