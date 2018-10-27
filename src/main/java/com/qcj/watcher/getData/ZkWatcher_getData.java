package com.qcj.watcher.getData;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 *  getdata触发的监听事件
 */
public class ZkWatcher_getData {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println(type+"----------"+path);
            }});

        //添加监听
        zk.getData("/idea_exit2",true,null);

        //触发监听
        //方式一 setdata -- nodedatachanged
        //zk.setData("/idea_exit2","setdata".getBytes(),-1);
        //方式二：delete -- NodeDeleted
        zk.delete("/idea_exit2",-1);
    }
}
