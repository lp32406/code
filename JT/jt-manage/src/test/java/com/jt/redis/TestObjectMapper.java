package com.jt.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.User;

public class TestObjectMapper {
	
	//将对象转化JSON
	/**
	 * 对象转化为JSON需要调用对象的get方法.
	 * @throws IOException 
	 */
	@Test
	public void test01() throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		user.setId(1);
		user.setName("tomcat");
		user.setAge(18);
		user.setSex("男");
		String json = 
				objectMapper.writeValueAsString(user);
		System.out.println(json);
		
		
		//将json串转化为java对象
		User jsonToUser =  objectMapper.readValue(json, User.class);
		System.out.println(jsonToUser.toString());
	}
	
	@Test
	public void test02() throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		user.setId(1);
		user.setName("tomcat");
		user.setAge(18);
		user.setSex("男");
		
		User user1 = new User();
		user1.setId(2);
		user1.setName("tomcat");
		user1.setAge(18);
		user1.setSex("男");
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user);
		String json = 
				objectMapper.writeValueAsString(userList);
		System.out.println(json);
		
		//[{"id":2,"age":18,"sex":"男"},{"id":1,"age":18,"sex":"男"}]
		List<User> jsonList = new ArrayList<>();
		jsonList = 
		objectMapper.readValue(json,jsonList.getClass());
		System.out.println(jsonList);
	}
}
