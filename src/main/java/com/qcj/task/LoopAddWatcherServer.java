package com.qcj.task;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 *  4.循环添加监听
 */
public class LoopAddWatcherServer {
    private static ZooKeeper zk;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper("hadoop1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println(type+"----------"+path);

                try {
                    zk.exists("/idea_exit2",true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }});

        zk.exists("/idea_exit2",true);

        //让它一直睡眠等待触发
        Thread.sleep(Long.MAX_VALUE);
    }
}
