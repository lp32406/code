package com.eb.dianlianbao_server.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.dianlianbao_server.util.alipay.Md5Util;

public class PaypreIdRequestHandler extends RequestHandler {

	public PaypreIdRequestHandler(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public String createMD5Sign(final String key) {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.append("key=" + key).substring(0);
		String sign = Md5Util.MD5Encode(params, "utf8");
		return sign.toUpperCase();
	}

	// 提交预支�?
	public Map<String, String> sendPrepay() throws Exception {
		String prepayid = "";
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		StringBuffer sb = new StringBuffer("<xml>");
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append("<" + k + ">" + v + "</" + k + ">");
		}
		sb.append("</xml>");
		String params = sb.substring(0);
		String requestUrl = super.getGateUrl();
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		Map<String, String> map=new HashMap<>();
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			map = XMLUtil.doXMLParse(resContent);
		}else{
			map.put("return_msg", "发送请求失败");
		}
		return map;
	}
}