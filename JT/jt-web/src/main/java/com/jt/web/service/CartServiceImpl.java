package com.jt.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private HttpClientService httpClient;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 查询
	 */
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		String url = "http://cart.jt.com/cart/query/"+userId;
		List<Cart> cartList = new ArrayList<>();
		String resultJSON = httpClient.get(url);
		try {
			SysResult sysResult 
			 = objectMapper.readValue(resultJSON, SysResult.class);
			//LinkedHashMap
			cartList = (List<Cart>) sysResult.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cartList;
	}

	/**
	 * 添加购物车
	 */
	@Override
	public void saveCart(Cart cart) {
		String url = "http://cart.jt.com/cart/save";
		Map<String,String> params = new HashMap<>();
		try {
			String cartJSON = 
					objectMapper.writeValueAsString(cart);
			params.put("cartJSON", cartJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpClient.post(url, params);
		System.out.println("购物车入库成功!!!!");
	}

	/**
	 * 修改
	 */
	@Override
	public void updateCartNum(Cart cart) {
		String url = "http://cart.jt.com/cart/update/num/" + cart.getUserId()+
				"/"+cart.getItemId()+"/"+cart.getNum();
		httpClient.get(url);
		
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteCart(Cart cart) {
		// 这底下的url是怎么来的
		String url = "http://cart.jt.com/cart/delete/" + cart.getUserId() + "/" + cart.getItemId();
		httpClient.get(url);
		System.out.println("删除消息发送成功");
	}
	
	

}
