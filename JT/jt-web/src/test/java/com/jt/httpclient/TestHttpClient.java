package com.jt.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import sun.net.www.http.HttpClient;

public class TestHttpClient {

	//测试get提交
	@Test
	public void testGet() throws ClientProtocolException, IOException{
		String url = "https://item.jd.com/10450760685.html";
		//定义请求对象
		HttpGet  httpGet = new HttpGet(url);
		HttpPost httpPost = new HttpPost(url);
		//定义httpClient
		CloseableHttpClient httpClient = 
				HttpClients.createDefault();
		//获取httpResponse对象
		CloseableHttpResponse httpResponse 
		 = httpClient.execute(httpPost);
		//判断状态信息
		if(httpResponse.getStatusLine()
				.getStatusCode() == 200){
			//请求正确   获取正确的响应数据
			String data = 
		EntityUtils.toString(httpResponse.getEntity());
			System.out.println(data);
		}
	}
}
