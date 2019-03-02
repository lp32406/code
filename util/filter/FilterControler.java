package com.eb.dianlianbao_server.util.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截登录
 * @author Administrator
 *
 */
public class FilterControler implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) throws Exception {

		req.setCharacterEncoding("utf-8");
		rep.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();

		// 判断是否登录操作
		String url = req.getServletPath();
		url = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		if ("login".equals(url)) {
			return true;
		}

		Object obj = session.getAttribute("adminUser");
		if (obj == null || "".equals(obj.toString())) {
			PrintWriter out = rep.getWriter();
			StringBuffer sb = new StringBuffer("<script type=\"text/javascript\" charset=\"UTF-8\" language='javascript'>");
			// 设置session超时，未操作退出会话，
			sb.append(
					"if(confirm('你的账号被挤掉，或没有登录，页面已经过期，请重新登录?')){parent.location.href = '/dianlianbao_server/pages/login/login.html';}else{parent.location.href = '/socialfianace/pages/login/login.html';}");
			sb.append("</script>");
			out.print(sb.toString());
			out.close();
			return false;
		}
		return true;
	}

}
