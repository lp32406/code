package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 实现商品分类目录显示
	 * 为了实现EasyUI的树形结构,传递Id的值
	 * @RequestParam(
	 * 	defaultValue="",     如果该参数没有传值 则为默认值
	 *  required=true/false, true则表示参数必须传递否则报错
	 *  value=""		         代表接收的参数
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCatList(
	@RequestParam(defaultValue="0",value="id")Long parentId){
		//查询一级商品分类目录
		//Long parentId = 0L;
		List<EasyUITree> treeList = 
				itemCatService.findItemCatCache(parentId);
		return treeList;
	}
}
