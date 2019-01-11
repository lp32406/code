package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Cart;
import com.jt.web.service.OrderService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@RequestMapping("/create")
	public String toOrderCart(Model model) {
		/*Long userId = UserThreadLocal.get().getId();
		List<Cart> carts = orderService.findOrderCart(userId);
		model.addAttribute("carts", "carts");*/
		return "order-cart";
	}
}
