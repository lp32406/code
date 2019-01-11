package com.jt.manage.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	
	
	@Autowired
	private ItemService itemService;
	
	//http://localhost:8091/item/query?page=1&rows=50
	@RequestMapping("/query")
	@ResponseBody    //将对象转化为JSON串
	public EasyUIResult findItemByPage(Integer page,Integer rows){
		
		return itemService.findItemByPage(page,rows);
	}
	
	/**
	 * @ResponseBody 解析说明
	 * 	如果解析的是对象 则默认使用UTF-8编码格式
	 *  如果解析的是String字符串,则默认使用ISO-8859-1编码
	 *  源码:
	 *  public static final Charset DEFAULT_CHARSET = 
	 *  Charset.forName("ISO-8859-1");
	 * @param itemId
	 * @return
	 *  
	 */
	@RequestMapping(value="/cat/queryItemName",produces="html/text;charset=utf-8")
	@ResponseBody
	public String findItemCatNameById(Long itemId){
		
		return itemService.findItemCatNameById(itemId);
	}
	
	
	
	/*
	 * 传统方式ajax请求
	 * @RequestMapping("/cat/queryItemName")
	public void findItemCatNameById(Long itemId,
			HttpServletResponse response){
		String name = itemService.findItemCatNameById(itemId);
		//指定字符集编码格式
		response.setContentType("html/text;charset=utf-8");
		try {
			response.getWriter().write(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return itemService.findItemCatNameById(itemId);
	}*/
	
	
	
	/**
	 * 实现商品新增
	 * 需要同时入库2张表  tb_item 和 tb_item_desc
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK(); //正确请求 返回数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品新增失败");
	}
	
	
	private static final Logger logger = 
	Logger.getLogger(ItemController.class);
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			//System.out.println("记录程序执行状态");
			logger.info("!!!!!!!!!!记录状态");
			return SysResult.oK();
		} catch (Exception e) {
			logger.error("~~~~~~~~~~~~~"+e.getMessage());
			e.printStackTrace();
		}
		return SysResult.build(201,"修改商品失败");
	}
	
	//实现商品的删除
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItems(Long[] ids){
		try {
			itemService.deleteItems(ids);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "删除商品失败");
	}
	
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instock(Long[] ids){
		try {
			int state = 2;
			itemService.updateState(ids,state);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品下架失败");
	}
	
	
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelf(Long[] ids){
		try {
			int status = 1;
			itemService.updateState(ids,status);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品上架失败");
	}
	
	//根据itemId查询商品详情信息
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(
			@PathVariable Long itemId){
		try {
			//将结果返回给客户端
			ItemDesc itemDesc = 
					itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品详情查询失败");
	}
	
	
	
	
	
	
	
	
	
}
