package com.qcj.watcher.getChildren;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 *  getChildren触发的监听事件
 */
public class ZkWatcher_getChildren {
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
        zk.getChildren("/1",true);

        //触发监听
        //方式一 创建子节点 create -- NodeChildrenChanged
        //zk.create("/1/44","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        //方式二： 删除子节点 delete
        zk.delete("/1/44",-1);

        //zk.getChildren("/1/44",true);
        //删除父节点（只能删除费控） nodedeleted事件
        //zk.delete("/1/44",-1);

    }
}
