package com.jt.manage.controller.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

@Controller
public class JSONPController {
	//http://manage.jt.com/web/testJSONP 
	/**
	 * callback(JSON)
	 * @param callback
	 * @return
	 * @throws JsonProcessingException
	 */
	/*@RequestMapping(value="/web/testJSONP",produces="html/text;charset=utf-8")
	@ResponseBody
	public String jsonp(String callback) throws JsonProcessingException{
		
		User user = new User();
		user.setId(100);
		user.setName("tomcat猫");
		user.setAge(18);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = 
				objectMapper.writeValueAsString(user);
		return callback + "(" + json +")";
	}*/
	
	//使用工具类封装JSONP返回值
	@RequestMapping(value="/web/testJSONP")
	@ResponseBody
	public MappingJacksonValue jsonp(String callback) throws JsonProcessingException{
		User user = new User();
		user.setId(100);
		user.setName("tomcat猫");
		user.setAge(18);
		MappingJacksonValue jacksonValue = 
				new MappingJacksonValue(user);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
	
	
	
	
	
	
	
}
