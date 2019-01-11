package com.jt.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.pojo.User;
import com.jt.web.thread.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

public class UserInterceptor implements HandlerInterceptor{

	@Autowired
	private JedisCluster jedisClust;
	private static ObjectMapper objectMapper = new ObjectMapper();
	// handler
	// 在执行Controller之前执行
	/**
	 * boolean 表示是否放行
	 * 	true:放行,用户可以跳转页面
	 * 	false:拦截之后给定重定向路径
	 * 
	 * 业务逻辑:
	 * 		1,判断用户客户端是否有Cookie/token数据,如果没有token数据则重定向到用户登陆页面.
	 *		2,用户的token中有数据,则从redis缓存中获取数据,如果Redis中的数据为空,则重定向到登陆页面.
	 *		3,如果redis中有数据,则放行请求.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		if(!StringUtils.isEmpty(token)) {
			// 检测缓存中是否有数据
			String userJSON = jedisClust.get(token);
			if(!StringUtils.isEmpty(userJSON)) {
				// 将userJSON转化为
				User user = objectMapper.readValue(userJSON, User.class);
				// 实现sessin数据共享
				//request.getSession().setAttribute("JT_USER", user);
				UserThreadLocal.set(user);
				return true;
			}
		}
		response.sendRedirect("/user/login.html");
		return false;
	}

	// 在执行完业务逻辑之后拦截
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 在返回页面之前
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 将TreadLocal数据清空
		UserThreadLocal.remove();
		
	}

}
