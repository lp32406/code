package com.jt.web.pojo;


import com.jt.common.po.BasePojo;
public class Item extends BasePojo{
	private Long id;
	private String title;	    //商品标题
	private String sellPoint;	//卖点信息
	private Long   price;		//价格  计算速度快 精确  
	private Integer num;		//商品数量
	private String barcode;		//二维码
	private String image;		//图片内容  1.jpg,2.jpg
	private Long cid;			//商品分类Id
	private Integer status;		//商品状态  1正常，2下架，3删除'
	
	//${item.images[0]} 满足数据获取 添加get方法.
	public String[] getImages(){
		
		return image.split(",");
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
