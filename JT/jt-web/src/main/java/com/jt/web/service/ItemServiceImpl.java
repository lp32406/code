package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private HttpClientService httpClient;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	//前台调用后台的方法获取item数据  JSONP???
	@Override
	public Item findItemById(Long itemId) {
		//1.定义远程url
		String url = 
		"http://manage.jt.com/web/item/findItemById";
		//添加参数 findItemById?itemId=199922
		Map<String,String> params = new HashMap<>();
		params.put("itemId", itemId+"");
		String itemJSON = httpClient.get(url,params);
		Item item = null;
		try {
			item = objectMapper.readValue(itemJSON,Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		//1.定义远程url
		String url = 
		"http://manage.jt.com/web/item/findItemDescById";
		//添加参数 findItemById?itemId=199922
		Map<String,String> params = new HashMap<>();
		params.put("itemId", itemId+"");
		String itemDescJSON = httpClient.get(url,params);
		ItemDesc itemDesc = null;
		try {
			itemDesc = objectMapper.readValue(itemDescJSON,ItemDesc.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
}
