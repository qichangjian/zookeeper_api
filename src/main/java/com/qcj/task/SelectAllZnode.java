package com.qcj.task;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 *  1、级联查看某节点下所有节点及节点值
 *  	判断当前的节点是否有子节点
 *  		有子节点
 *  			循环遍历
 *  				递归
 *  		没有子节点
 *  			输出节点及节点值
 */
public class SelectAllZnode {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //获取zk对象
        ZooKeeper zk = new ZooKeeper("hadoop1:2181",5000,null);
        //查看子节点
        String deletePath="/1";
        //递归删除所有子节点
        slectSubNode(zk,deletePath);
    }

    //循环遍历输出
    private static void slectSubNode(ZooKeeper zk, String deletePath) throws KeeperException, InterruptedException {
        String nodePath = deletePath;
        byte[] content = zk.getData(nodePath, null, null);
        String con = new String(content);
        System.out.println(nodePath+":"+con);
        //如果子节点为0删除
        if(zk.getChildren(nodePath,false).size()!=0){
            List<String> list = zk.getChildren(nodePath,true);
            for (String s:list) {
                slectSubNode(zk,nodePath+"/"+s);//拼接路径 递归
            }
        }
    }
}
