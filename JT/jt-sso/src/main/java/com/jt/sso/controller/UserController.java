package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

import redis.clients.jedis.JedisCluster;
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue findCheckUser(
			@PathVariable String param,
			@PathVariable Integer type,
			String callback){
		boolean flag = userService.findCheckUser(param,type);
		MappingJacksonValue jacksonValue = 
				new MappingJacksonValue(SysResult.oK(flag));
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
	
	//实现用户新增
	@RequestMapping("/register")
	@ResponseBody
	public SysResult saveUser(User user){
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"新增用户失败");
	}
	
	//用户登陆操作
	@RequestMapping("/login")
	@ResponseBody
	public SysResult findUserByUP(User user){
		try {
			String token = userService.findUserByUP(user);
			return SysResult.oK(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户查询失败");
	}
	
	//通过token获取用户信息
	@RequestMapping("/query/{token}")
	@ResponseBody
	public MappingJacksonValue findUserByToken(@PathVariable String token,String callback){
		MappingJacksonValue jacksonValue = null;
		//根据token查询userJSON数据
		String userJSON = jedisCluster.get(token);
		if(StringUtils.isEmpty(userJSON)){
			//用户查询失败
			jacksonValue = 
					new MappingJacksonValue(SysResult.build(201, "用户查询失败"));
		}else{
			jacksonValue = 
					new MappingJacksonValue(SysResult.oK(userJSON));
		}
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
