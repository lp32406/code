package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		
		//int total = itemMapper.findItemCount();
		  int total = itemMapper.selectCount(null);
		//商品分页后的List集合
		/**
		 * select * from tb_item limit 起始位置,每页的行数
		 * select * from tb_item limit 0,20   第一页
		   select * from tb_item LIMIT 20,20 第二页
           select * from tb_item limit 40,20 第三页
           select * from tb_item limit (page-1) * rows,20  
                      第N页
		 */
		int start = (page - 1) * rows;
		List<Item> itemList = 
				itemMapper.findItemListByPage(start,rows);
		
		return new EasyUIResult(total,itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) {
		
		return itemMapper.findItemCatNameById(itemId);
	}

	
	//封装Item数据  使用通用Mapper实现商品新增
	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1); //1.表示上架 正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);  //itemId
		
		//10,11,12,14,15,17,19,20  查询当前线程内最大值,就是当前ID
		//Long id = itemMapper.select(item).getId();
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item,String desc) {
		item.setUpdated(new Date());
		//动态更新
		itemMapper.updateByPrimaryKeySelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void deleteItems(Long[] ids) {
		
		itemMapper.deleteByIDS(ids);
	}

	@Override
	public void updateState(Long[] ids, int status) {
		
		itemMapper.updateState(ids,status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public Item findItembyId(Long itemId) {
		
		return itemMapper.selectByPrimaryKey(itemId);
	}
	
	
	
	
	
	
	
	
}
