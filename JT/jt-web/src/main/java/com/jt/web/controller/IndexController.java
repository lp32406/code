package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//跳转index.jsp页面
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
}
