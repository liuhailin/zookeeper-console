package com.lhl.zk.repository;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 
 * @author liuhailin
 *
 */
public class ClientCacheManager {
	private static final Logger log = LoggerFactory.getLogger(ClientCacheManager.class);
	private static final int WAITING_SECONDS = 2;

	public static CuratorFramework getClient(String hostPort,String nameSpace) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		String key = hostPort;
		CuratorFramework client = null;
		Object obj = session.getAttribute(key);
		if(obj == null) {
			log.info("zk host:{}",hostPort);
			client = CuratorFrameworkFactory.builder()
					.connectString(hostPort).namespace(nameSpace).retryPolicy(new ExponentialBackoffRetry(1000, 3,3000))
					.connectionTimeoutMs(5000).build();
	        client.start();
	        boolean established = false;
	        try {
	            established = client.blockUntilConnected(WAITING_SECONDS, TimeUnit.SECONDS);
	        } catch (final InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	        if (established) {
	        	session.setAttribute(key, client);
	            return client;
	        }
	        CloseableUtils.closeQuietly(client);
			return null;
		} else {
			client = (CuratorFramework) obj;
		}
		return client;
	}
	
}
