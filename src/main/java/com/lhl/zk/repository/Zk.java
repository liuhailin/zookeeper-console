package com.lhl.zk.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhl.zk.entity.ZkData;

public class Zk {
   private static final Logger LOGGER = LoggerFactory.getLogger(Zk.class);
   private CuratorFramework client;

   public boolean exists(String path) throws Exception {
      if (path == null || path.trim().equals("")) {
         throw new IllegalArgumentException("path can not be null or empty");
      }
      return getClient().checkExists().forPath(path)!=null;
   }

   public ZkData readData(String path) throws Exception {
      ZkData zkdata = new ZkData();
      Stat stat = getClient().checkExists().forPath(path);
      zkdata.setData(getClient().getData().forPath(getPath(path)));
      zkdata.setStat(stat);
      return zkdata;
   }

   public List<String> getChildren(String path) throws Exception {
      return getClient().getChildren().forPath(getPath(path));
   }

   public void create(String path, byte[] data) throws Exception {
      path = getPath(path);
      String result= getClient().create().creatingParentsIfNeeded().forPath(path,data);
      LOGGER.info("create: node:{}, result{}:", path, result);
   }

   public void edit(String path, byte[] data) throws Exception {
      path = getPath(path);
      Stat stat = getClient().setData().forPath(path, data);
      LOGGER.info("edit: node:{}, stat{}:", path, stat);
   }

   public void delete(String path) throws Exception {
      path = getPath(path);
      getClient().delete().deletingChildrenIfNeeded().forPath(path);
      LOGGER.info("delete: node:{}", path);
   }

   public void deleteRecursive(String path) throws Exception {
      path = getPath(path);
      getClient().delete().deletingChildrenIfNeeded().forPath(path);
      LOGGER.info("rmr: node:{}", path);
   }

   public Zk(String cxnString) {
      LOGGER.info("cxnString:{}", cxnString);
      int index = cxnString.indexOf("/");
      if(index>0){
    	  String hostPort = cxnString.substring(0,index);
          String nameSpace = cxnString.substring(index+1,cxnString.length());
          this.client = ClientCacheManager.getClient(hostPort,nameSpace);
      }else{
    	  String hostPort = cxnString;
          String nameSpace = "";
          this.client = ClientCacheManager.getClient(hostPort,nameSpace);
      }
   }

   public CuratorFramework getClient() {
      return client;
   }

   public void setClient(CuratorFramework client) {
      this.client = client;
   }

   private String getPath(String path) {
      path = path == null ? "/" : path.trim();
      if (!StringUtils.startsWith(path, "/")) {
         path = "/" + path;
      }
      return path;
   }

}
