package com.qcj.configmanager;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

public class ZK_Server {
    private static String parent="/config";
    private static ZooKeeper zk;
    private static List<String> children =null;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建zk对象 10:12
        zk = new ZooKeeper("hadoop1:2181", 5000, new Watcher() {
            //监听触发的时候回调这个方法
            @Override
            public void process(WatchedEvent event) {
                //获取事件的类型
                Event.EventType type = event.getType();

                //nodecreated事件 是 nodechildrenchanged事件监听的(nodedeleted  nodecreated)
                if(Event.EventType.NodeChildrenChanged.equals(type)){
                    try {
                        //在外边getChildren添加监听触发，跑到process调用方法，到这里注册监听，就能循环监听
                        List<String> newChildren = zk.getChildren(parent,true);//再去获取父节点的子节点
                        //判断两个集合的大小 new > 原来大小
                        if(newChildren.size() > children.size()){//证明是nodecreated
                            //判断两个集合中不同的元素 得到新添加的节点
                            String diff = getDiff(children,newChildren);
                            //新添加的节点也添加监听: 获取内容 添加监听
                            String context = new String(zk.getData(parent+"/"+diff,true,null));
                            System.out.println("添加了一个节点，节点名字："+diff+"节点的内容是："+context);

                            //每次添加完对原来的children进行重新赋值
                            children=newChildren;
                        }
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //nodedatachanged事件  修改一个节点
                if(Event.EventType.NodeDataChanged.equals(type)){
                    //返回的是触发事件的节点 /config/core-sit.xml
                    String path = event.getPath();
                    String name = path.substring(path.lastIndexOf("/")+1);
                    try {
                        byte[] context = zk.getData(path,true,null);
                        String text = new String(context);
                        System.out.println("修改了一个配置文件，节点为："+ name + "节点为：" + text);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //nodedeleted事件
                if(Event.EventType.NodeDeleted.equals(type)){
                    String path = event.getPath();
                    String name = path.substring(path.lastIndexOf("/")+1);
                    System.out.println("删除了一个配置文件：配置文件的名字：" +name);
                    try {
                        children = zk.getChildren(parent,true,null);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }});

        //准备数据
        if(zk.exists(parent,null)==null){//没有创建
            zk.create(parent,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        if(zk.exists(parent+"/hdfs-site.xml",null)==null){//没有创建
            zk.create(parent+"/hdfs-site.xml","aa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        if(zk.exists(parent+"/core-site.xml",null)==null){//没有创建
            zk.create(parent+"/core-site.xml","bb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        //添加监听
        //先添加父节点监听
        children = zk.getChildren(parent, true);
        //循环遍历添加所有的节点添加监听
        for (String c:children) {
            String child_path = parent+"/" + c;
            zk.getData(child_path,true,null);//每个子节点添加监听
        }

        Thread.sleep(Long.MAX_VALUE);

    }

    /**
     * 判断两个集合中不同的元素 得到新添加的节点
     */
    static String getDiff(List<String> oldList,List<String> newList){
        //遍历新的在老的里边查找，没有是新的元素，返回
        String diff = "";
        for (String newL:newList) {
            if(!oldList.contains(newL)){
                diff = newL;
                break;
            }
        }
        return diff;
    }
}
