package com.zllh.mall.common.model;

import java.util.Date;
import java.util.List;

public class MtGoods {

	private String goodsId;

	private String mmbId;

	private String categoryId;

	private String name;

	private String described;

	private String imgId;

	private Double maxPrice;

	private Double minPrice;

	private String unitPrice;

	private Integer status;
	private String productNum;

	private String productTime;

	private String factory;

	private String createAddress;

	private String specification;

	private Long stockNum;

	private String goodsName;
	// 品类名称
	private String categoryName;
	// 图片路径
	private String imgPath;
	private Date createTime;
	
	//工具 
	private List<MtMaterial> imglist;

	//根据协议生成订单--商品单价 默认为空
	private String price;

	//根据协议生成订单--商品数量 默认为空
	private String goodsNum;

	//根据协议生成订单--总金额 默认为空
	private String money;

	//根据协议生成订单
	private String	buyersId;
	//根据协议生成订单
	private String	buyersName;
	//根据协议生成订单
	private String	sellersId;
	//根据协议生成订单
	private String	sellersName;
	//根据协议生成订单
	private Double min;
	//根据协议生成订单
	private Double max;




	public List<MtMaterial> getImglist() {
		return imglist;
	}


	public void setImglist(List<MtMaterial> imglist) {
		this.imglist = imglist;
	}

	@Override
	public String toString() {
		return "MtGoods [goodsId=" + goodsId + ", mmbId=" + mmbId + ", categoryId=" + categoryId + ", name=" + name + ", described=" + described + ", imgId=" + imgId + ", maxPrice=" + maxPrice + ", minPrice=" + minPrice + ", unitPrice=" + unitPrice + ", status=" + status + ", productNum=" + productNum + ", productTime=" + productTime + ", factory=" + factory + ", createAddress=" + createAddress + ", specification=" + specification + ", stockNum=" + stockNum + ", goodsName=" + goodsName + ", categoryName=" + categoryName + ", imgPath=" + imgPath + ", brand=" + brand + ", createTime=" + createTime + ", unitSpecification=" + unitSpecification + "]";
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private String brand;

	// 规格单位
	private String unitSpecification;

	public String getUnitSpecification() {
		return unitSpecification;
	}

	public void setUnitSpecification(String unitSpecification) {
		this.unitSpecification = unitSpecification == null ? null : unitSpecification.trim();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId == null ? null : goodsId.trim();
	}

	public String getMmbId() {
		return mmbId;
	}

	public void setMmbId(String mmbId) {
		this.mmbId = mmbId == null ? null : mmbId.trim();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId == null ? null : categoryId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described == null ? null : described.trim();
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId == null ? null : imgId.trim();
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice == null ? null : unitPrice.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum == null ? null : productNum.trim();
	}

	public String getProductTime() {
		return productTime;
	}

	public void setProductTime(String productTime) {
		this.productTime = productTime == null ? null : productTime.trim();
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory == null ? null : factory.trim();
	}

	public String getCreateAddress() {
		return createAddress;
	}

	public void setCreateAddress(String createAddress) {
		this.createAddress = createAddress == null ? null : createAddress.trim();
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification == null ? null : specification.trim();
	}

	public Long getStockNum() {
		return stockNum;
	}

	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand == null ? null : brand.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getBuyersId() {
		return buyersId;
	}

	public void setBuyersId(String buyersId) {
		this.buyersId = buyersId;
	}

	public String getBuyersName() {
		return buyersName;
	}

	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}

	public String getSellersId() {
		return sellersId;
	}

	public void setSellersId(String sellersId) {
		this.sellersId = sellersId;
	}

	public String getSellersName() {
		return sellersName;
	}

	public void setSellersName(String sellersName) {
		this.sellersName = sellersName;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}
}