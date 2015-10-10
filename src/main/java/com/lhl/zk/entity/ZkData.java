package com.lhl.zk.entity;

import java.nio.charset.Charset;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

import org.apache.zookeeper.data.Stat;

/**
 * 
 * @author liuhailin
 *
 */
@Getter
@Setter
public class ZkData {

   private byte[] data;
   private Stat stat;

   public byte[] getBytes() {
      return data;
   }

   @Override
   public String toString() {
      return "ZkData [data=" + Arrays.toString(getData()) + ",stat=" + getStat() + "]";
   }

   public String getDataString() {
      return new String(getData(), Charset.forName("UTF-8"));
   }
}
