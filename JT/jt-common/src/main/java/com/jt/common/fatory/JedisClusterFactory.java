package com.jt.common.fatory;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterFactory implements FactoryBean<JedisCluster> {
	
	private JedisPoolConfig poolConfig;
	private Resource redisSource;
	private String perKey;	//表示前缀
	
	@Override
	public JedisCluster getObject() throws Exception {
		Set<HostAndPort> nodes = getNodes();
		JedisCluster jedisCluster = 
				new JedisCluster(nodes, poolConfig);
		return jedisCluster;
	}

	public Set<HostAndPort> getNodes(){
		Set<HostAndPort> nodes = 
				new HashSet<HostAndPort>();
		Properties properties = new Properties();
		try {
			properties.load(redisSource.getInputStream());
			
			//遍历Pro中的数据获取redis节点数据信息
			for (Object node : properties.keySet()) {
				String strNode = (String) node;
				//判断是否为redis节点数据信息
				if(strNode.startsWith(perKey)){
					//表示是redis节点信息的key  192.168.126.166:7000  
					String value = 
							properties.getProperty(strNode);
					String[] args = value.split(":");
					HostAndPort hostAndPort = 
					new HostAndPort(args[0],Integer.parseInt(args[1]));
					nodes.add(hostAndPort);
 				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}
	
	@Override
	public Class<?> getObjectType() {
		
		return JedisCluster.class;
	}

	@Override
	public boolean isSingleton() {

		return false;
	}

	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public Resource getRedisSource() {
		return redisSource;
	}

	public void setRedisSource(Resource redisSource) {
		this.redisSource = redisSource;
	}

	public String getPerKey() {
		return perKey;
	}

	public void setPerKey(String perKey) {
		this.perKey = perKey;
	}
}
