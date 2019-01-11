package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/{moduleName}")
	public String module(@PathVariable String moduleName){
		
		return moduleName;
	}
	
	//实现用户注册
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user){
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"用户新增失败");
	}
	
	
	//实现用户登陆
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult findUserByUP(User user,HttpServletResponse response ){
		try {
			//获取加密后的秘钥
			String token = userService.findUserByUP(user);
			
			//判断token是否为null
			if(StringUtils.isEmpty(token)){
				throw new RuntimeException();
			}
			//将token写入客户端Cookie中 4k
			Cookie cookie = new Cookie("JT_TICKET", token);
			cookie.setPath("/"); //保存到根目录
			//cookie.setMaxAge(0); 立即销毁Cookie
			//cookie.setMaxAge(-1); 会话关闭后 Cookie销毁
			cookie.setMaxAge(60 * 60 * 24 * 7);//7天免密登录  最大生命周期 单位秒
			response.addCookie(cookie);
			//程序正常返回
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "登陆失败");
	}
	
	
	//用户登出是删除1.redis缓存 2.删除Cookie
	/**
	 * 1.获取Cookie中数据
	 * 2.删除redis缓存
	 * 3.将Cookie删除
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		String token = null;
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())){
				token = cookie.getValue();
				break;
			}
		}
		jedisCluster.del(token);//删除缓存
		Cookie cookie = new Cookie("JT_TICKET", "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/index.html";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
