package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
	
	/**
	 * get请求:
	 * 	 localhost:8091/addUser?id=1&name=tom
	 * restFul
	 * 	 localhost:8091/addUser/1/tom
	 * 
	 * 参数格式要求:
	 * 	1.使用{moduleName}包裹参数
	 *  2.如果有多个参数使用"/"分割
	 * 	3.参数位置是固定的
	 *  4.@PathVariable String moduleName 动态获取参数
	 * 
	 * @return
	 * url:/page/item-add
	 */
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName){
		
		//跳转商品新增
		return moduleName;
	}
	
	/*//url:localhost:8091/add/1/tom/18
	@RequestMapping("/add/{ids}/{name}/{age}")
	public String addUser(@PathVariable(value="ids") Long id,
				@PathVariable String name)
	*/
	
	
	
	
	
	
}
