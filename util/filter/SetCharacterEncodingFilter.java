package com.eb.dianlianbao_server.util.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;



public class SetCharacterEncodingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
 
        // 请求的uri
        String uri = request.getRequestURI();
 
        // uri中包含background时才进行过滤
        if (uri.indexOf("do1") == -1) {
            // 是否过滤
            boolean doFilter = true;
            if (doFilter) {
                // 执行过滤
            	request.setCharacterEncoding("utf-8");
            	response.setContentType("text/html;charset=utf-8");
                filterChain.doFilter(request, response);
            } else {
                // 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果uri中不包含background，则继续
            filterChain.doFilter(request, response);
        }
	}


}