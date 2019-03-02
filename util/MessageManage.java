package com.eb.dianlianbao_server.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class MessageManage
{

	public static String phoneTestCode()
	{
		String code = String.valueOf((int)((Math.random()*9+1)*100000));
		return code;
	}

	public static String requestCode()
	{
		String code = String.valueOf((int)((Math.random()*9+1)*1000));
		return code;
	}

	public static boolean sendMessage(String phone, String contents) throws HttpException, IOException {

		// http://sz.ipyy.com:8080/Account/LogOn
		String Url = "http://sz.ipyy.net/sms.aspx?action=send";

		HttpClient client = new HttpClient();

		PostMethod post = new PostMethod(Url);
		post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		NameValuePair userid = new NameValuePair("userid", "");
		NameValuePair account = new NameValuePair("account", "huashen123");
		NameValuePair password = new NameValuePair("password", getMD5("huashen123"));
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
				return true;
			} else {
				return false;
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return false;

	}

	public static String getMD5(String str) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());
            String md5=new BigInteger(1, md.digest()).toString(16);
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("md5加密异常"+e.getMessage(),e);
        }
    }
	public static String fillMD5(String md5)
    {
        return (md5.length()==32?md5:fillMD5("0"+md5)).toUpperCase();
    }

}
