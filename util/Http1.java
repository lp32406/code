package com.eb.dianlianbao_server.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class Http1 {

	public static String sendPhone(String phone, String contents) throws HttpException, IOException {

		// http://sz.ipyy.com:8080/Account/LogOn
		String Url = "http://sz.ipyy.net/sms.aspx?action=send";

		HttpClient client = new HttpClient();

		PostMethod post = new PostMethod(Url);
		post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		NameValuePair userid = new NameValuePair("userid", "");
		NameValuePair account = new NameValuePair("account", "eb18066");
		NameValuePair password = new NameValuePair("password", GetPhoneCode.getMD5("eb18066#1"));
		NameValuePair mobile = new NameValuePair("mobile", phone);
		NameValuePair content = new NameValuePair("content", contents);
		NameValuePair sendTime = new NameValuePair("sendTime", "");
		NameValuePair extno = new NameValuePair("extno", "");
		post.setRequestBody(new NameValuePair[] { userid, account, password, mobile, content, sendTime, extno });
		int statu = client.executeMethod(post);
		System.out.println("statu=" + statu);
		String str = post.getResponseBodyAsString();

		System.out.println(str);

		try {
			Document doc = DocumentHelper.parseText(str);
			Element rootElt = doc.getRootElement();
			String returnstatus = rootElt.elementText("returnstatus").trim();
//			String message = rootElt.elementText("message").trim();
//			String remainpoint = rootElt.elementText("remainpoint").trim();
//			String taskID = rootElt.elementText("taskID").trim();
//			String successCounts = rootElt.elementText("successCounts").trim();

			if (returnstatus.equals("Success")) 
			{
				return "success";
			} else {
				return rootElt.elementText("message").trim();
			}
		} catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		return null;

	}

}
