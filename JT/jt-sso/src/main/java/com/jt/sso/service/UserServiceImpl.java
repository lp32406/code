package com.jt.sso.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	private static ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private JedisCluster jedisCluster;
	
	
	/**
	 * 实现思路:
	 * 	param:用户输入内容
	 * 	type: 1 2 3 判断不同的输入类型 usernmae 电话 邮箱
	 * 	校验原则:存在true  不存在返回false
	 */
	@Override
	public boolean findCheckUser(String param, Integer type) {
		//根据类型定义查询的字段
		String cloumn = null;
		switch (type) {
			case 1:
				cloumn = "username";break;
			case 2:
				cloumn = "phone";  break;
			case 3:
				cloumn = "email";  break;
		}

		int count =  userMapper.findCheckUser(cloumn,param);
		//返回数据true用户已存在，false用户不存在
		return count == 0 ? false : true;
	}

	@Override
	public void saveUser(User user) {
		//补齐参数   一般盐值  公司域名+用户名
		String md5Password = 
				DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	/**
	 * 1.根据用户名和密码查询数据
	 * 2.判断数据库中是否有数据 
	 * 	if user!=null   数据库中有数据
	 * 		准备token数据,将user转化JSON 之后保存redis中
	 *		返回token信息
	 *	if user ==null
	 *		则抛出异常即可
	 */
	@Override
	public String findUserByUP(User user) {
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		User userDB = super.queryByWhere(user);
		if(userDB == null){
			//表示用户名或密码错误
			throw new RuntimeException();
		}
		String token = 
		DigestUtils.md5Hex("JT_TICKET_"+System.currentTimeMillis()+userDB.getUsername());
		String userJSON = null;
		try {
			userJSON = objectMapper.writeValueAsString(userDB);
			jedisCluster.setex(token, 3600 * 24 * 7, userJSON);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}
}
