package com.qcj.task;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 *   2、删除一个节点，不管有有没有任何子节点
 */
public class DeleteAllZnode {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181",5000,null);
        //查看子节点
        String deletePath="/1";
        //递归删除所有子节点
        deleteSubNode(zk,deletePath);
    }

    /**
     * 递归删除方法
     */
    private static void deleteSubNode(ZooKeeper zk, String deletePath) throws KeeperException, InterruptedException {
        String nodePath = deletePath;
        System.out.println(nodePath+"==============");
        //如果子节点为0删除
        if(zk.getChildren(nodePath,false).size()==0){
            zk.delete(nodePath,-1);
            System.out.println(nodePath+"---------已经被删除");
        }else{
            List<String> list = zk.getChildren(nodePath,true);
            for (String s:list) {
                deleteSubNode(zk,nodePath+"/"+s);//拼接路径 递归
            }
            if(zk.getChildren(nodePath,false).size()==0){//递归后是否能 为空目录了
                zk.delete(nodePath,-1);
                System.out.println(nodePath+"---------已经被删除");
            }
        }
    }
}
