package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpClientService httpClient;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	public void saveUser(User user) {
		String url = "http://sso.jt.com/user/register";
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getPhone());//代替邮箱
		String resultJSON = httpClient.post(url,params);
		try {
			SysResult sysResult = 
			objectMapper.readValue(resultJSON, SysResult.class);
			//如果后台回传数据不是200表示执行有误.
			if(sysResult.getStatus() != 200){
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public String findUserByUP(User user) {
		String url = "http://sso.jt.com/user/login";
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		String resultJSON = httpClient.post(url, params);
		String token = null;
		try {
			SysResult sysResult = 
			objectMapper.readValue(resultJSON, SysResult.class);
			if(sysResult.getStatus() != 200){
				throw new RuntimeException();
			}
			token = (String) sysResult.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}
	
	
	
	
	
	
	
	

}
