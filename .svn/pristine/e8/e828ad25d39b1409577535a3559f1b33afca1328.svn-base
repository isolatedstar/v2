package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtCategory;
import com.zllh.mall.common.model.MtGoods;

public interface MtGoodsMapper {
	int insert(MtGoods record);

	int insertSelective(MtGoods record);

	// 条件查询
	List<MtGoods> searchGoods(Map<String, Object> params);

	// 根据商品Id修改商品状态
	int updateByGoodId(Map<String, Object> params);

	// 根据商品Id修改商品对象
	int updateGoods(MtGoods record);

	// 商品ID，得到该商品对象
	MtGoods searchGoodById(String goodsId);

	// 条件查询，得到该商品数量
	long searchGoodCount(Map<String, Object> params);

	// 根据商品Id 增加或减少商品数量
	int updateNum(Map<String, Object> params);
	//hecheng
	List<MtCategory> searchGoodByCategoryId(Map<String, Object> map);
  	//商品详情展示
  	MtGoods lookGoodById (String goodsId);
}