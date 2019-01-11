package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;

@Service
public class CartServiceImpl extends BaseService<Cart> implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		return cartMapper.select(cart);
	}

	/**
	 * 1,根据userId和itemId查询购物车数据
	 * 如果购物车中没有数据,则做数量的更新操作
	 * 封装数据后直接入库
	 */
	@Override
	public void saveCart(Cart cart) {
		// 根据userId和
		Cart cartDB = cartMapper.findCartByUI(cart);
		if(cartDB == null) {
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			// 数据库中有数据
			int num = cart.getNum() + cartDB.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cartDB);
		}
	}

	@Override
	public void updateCartNum(Cart cart) {
		cart.setUpdated(new Date());
		cartMapper.updateCartNum(cart);;
		
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteCart(Cart cart) {
		cartMapper.delete(cart);
	}
}
