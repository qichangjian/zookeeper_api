package com.qcj.api;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
/**
 * 基本api
 */
public class TestZKAPI {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		//获取zk对象
		ZooKeeper zk = new ZooKeeper("hadoop1:2181", 5000, null);
		//操作文件系统
		//1.创建节点 create
		/**
		 * 参数一：节点路径   string类型
		 * 参数二：节点的内容  byte[]
		 *参数三：权限  默认（所有权限）
		 *参数四：节点的类型  临时，有无编号
		 *	永久PERSISTENT
		 *	临时EPHEMERAL
		 *	永久有编号PERSISTENT_SEQUENTIAL
		 *	临时无编号EPHEMERAL_SEQUENTIAL
		 */
		//创建一个永久节点
		zk.create("/test_api_idea", "eclispejvaApi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//创建一个临时节点:临时节点对当前客户端起作用，对eclipse来说，运行的时候是客户端，运行结束，客户端退出
		//zk.create("/test_api_java_e", "eclispejvaApi_linshi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		//创建一个永久有编号
		//zk.create("/test_api_java_p", "eclispejvaApi_yongjiubianhao".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		//临时有编号节点
		//zk.create("/test_api_java_ep", "eclispejvaApi_yongjiubianhao".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

		//2.修改节点内容 set
		/**
		 * 参数一：节点路径
		 * 参数二：修改的内容
		 * 参数三：数据版本  如果不知道版本给  -1 :代表获取最新版本
		 */
		//zk.setData("/test_api_java", "change".getBytes(), -1);

		//3.删除节点 delete rmr
		//delete：只能删除空节点
		//非空节点需要递归删除：   作业------------------------------
		//zk.delete("/test_api_java", -1);

		//4.查看节点内容 get
		/**
		 * 参数一：节点路径
		 * 参数二：监听
		 * 参数三：节点状态对象  不知道传null,让它自己获取
		 */
		/*byte[] content = zk.getData("/test_api_java", null, null);
		System.out.println(content);
		String con = new String(content);
		System.out.println(con);*/

		//5.查看子节点 ls
		/**
		 * 参数一：节点路径
		 * 参数二：监听
		 */
		/*List<String> children = zk.getChildren("/", null);
		System.out.println(children);*/

		//6.判断节点是否存在 exists
		// Stat exists = zk.exists("/test_api_java", null);
		//返回值是节点状态对象，封装节点的状态信息的  如果当前节点不存在返回null
		//System.out.println(exists);
		//System.out.println(exists.getCzxid());
	}
}
