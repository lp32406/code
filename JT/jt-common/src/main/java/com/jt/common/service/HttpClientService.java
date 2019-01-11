package com.jt.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired(required=false)
    private CloseableHttpClient httpClient;

    @Autowired(required=false)
    private RequestConfig requestConfig;
    
    /**
     * 编辑get请求
     * 参数定义: 参数定义几个
     * get请求数据如何动态拼接 
     * 	localhost:8091?id=1&name=tom	
     * 
     * /*String paramUrl = url + "?";
    		for (Map.Entry<String,String> entry : params.entrySet()) {
    			paramUrl  = paramUrl + entry.getKey() + "="
    					+ entry.getValue() + "&";
			}
    		
    		paramUrl = paramUrl.substring(0, paramUrl.length()-1);
     */
    public String get(String url,Map<String,String> params,String charset){
    	
    	String result = null;
    	
    	//1.判断字符集编码是否为null
    	if(StringUtils.isEmpty(charset)){
    		
    		charset = "UTF-8";
    	}
    	
    	//2.判断参数是否为null  拼接url参数
    	//localhost:8091?id=1&name=tom&
    	try {
    		if(params !=null){
        		URIBuilder builder = new URIBuilder(url);
        		for (Map.Entry<String,String> entry : params.entrySet()) {
					
        			builder.addParameter(entry.getKey(),entry.getValue());
        		}
        		//生成get请求具体路径localhost:8091?id=1....
        		url = builder.build().toString();
        	}
    		
    		//3.定义请求对象
    		HttpGet httpGet = new HttpGet(url);
    		httpGet.setConfig(requestConfig);//定义连接时长
    		//4.发起请求
    		CloseableHttpResponse httpResponse = 
    				httpClient.execute(httpGet);
    		//5判断响应结果是否正确
    		if(httpResponse
    				.getStatusLine()
    				.getStatusCode() == 200){
    			result = EntityUtils.toString
    					(httpResponse.getEntity(),charset);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    //为了满足用户需要 添加下列方法
    public String get(String url){
    	
    	return get(url, null, null);
    }
    
    public String get(String url,Map<String,String> params){
    	
    	return get(url, params, null);
    }
    
    /**
     * 实现post请求
     * post请求需要将数据封装到Form表单对象中
     */
    public String post(String url,Map<String,String> params,String charset){
    	//判断字符集编码
    	if(StringUtils.isEmpty(charset)){
    		
    		charset = "UTF-8";
    	}
    	
    	//1.定义请求对象
    	HttpPost httpPost = new HttpPost(url);
    	httpPost.setConfig(requestConfig);
    	
    	//2.判断是否有参数
    	if(params != null){	
	    	List<NameValuePair> parameters = new ArrayList<>();
	    	for (Map.Entry<String, String> entry : params.entrySet()) {
				BasicNameValuePair valuePair = 
				new BasicNameValuePair(entry.getKey(), entry.getValue());
				parameters.add(valuePair);
			}
	    	try {
	    		UrlEncodedFormEntity formEntity = 
	        	new UrlEncodedFormEntity(parameters,charset);
	        	httpPost.setEntity(formEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	String result = null;
    	try {
    		//3.发起请求
        	CloseableHttpResponse httpResponse = 
        			httpClient.execute(httpPost);
        	if(httpResponse.getStatusLine().getStatusCode() == 200){
        		result = EntityUtils.toString(httpResponse.getEntity(),charset);
        	}
		} catch (Exception e) {
			e.printStackTrace();
			//抛出自定义异常
			throw new RuntimeException();
		}
    	return result;
    }
    
    public String post(String url){
    	
    	return post(url, null, null);
    }
    
    
    public String post(String url,Map<String,String> params){
    	
    	return post(url,params,null);
    }
    
    
    
    
    
    
    
    
    
    
}
