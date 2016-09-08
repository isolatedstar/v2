package com.zllh.mall.goods.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtCategory;
import com.zllh.mall.common.model.MtMmbCategory;





public interface CategoryService {

	//查询所有品类
	public List<MtCategory> serachAll();
	//查询所有品类（easyui）
	public List<MtCategory> searchAllCategory(Integer type);
	//新增用户品类数据
	public boolean addMmbCtr(String mmbId,String categoryId);
	//删除用户品类数据
	public boolean delMmbCtr(String mmbId,String categoryId);
	//查询用户品类数据
	public MtMmbCategory serachById(String mmbId,String categoryId);
	//查询所有品类的树
	public List<MtCategory> getAllMtCategory();
	//查询得到用户的品类树
	public List<MtCategory> getUserMtCategory(String mmbId);
	public List<MtCategory> getUserMtGoods(String mmbId);
	//查询单个品类
	public MtCategory getById(String categoryId);
	//根据合作协议ID得到商品大类
	public List<MtCategory> getMtCategoryByContractId(String contractId,String sellMmbId);
}
