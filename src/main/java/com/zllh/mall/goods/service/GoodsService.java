package com.zllh.mall.goods.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtGoods;






public interface GoodsService {
	//查询某会员下该品类的所有商品
	public List<MtGoods> searchGoods(Map<String, Object> params);
	//查询出商品数量
	long searchGoodCount(Map<String, Object> params);
	//新增商品
	public Map<String, Object> addGood(MtGoods good,String imageIds);
	//编辑商品
	public Map<String, Object> editGood(MtGoods good,String imageIds);
	//删除商品
	public boolean editGood(String categoryId,String goodsId);
	//增加商品数量
	public boolean addGoodNUm (String GoodId,long num );
	//减少商品数量
	public boolean reduceGoodNUm (String GoodId,long num );
	//通过商品Id，修改商品状态
	public boolean updateByGoodId(Map<String, Object> params);
	//通过商品Id，得到该商品对象
	public MtGoods searchGoodById(String goodsId);
	//通过商品Id，得到商品详细信息 关联其他表
	public MtGoods lookGoodById(String goodsId);
	

}
