package com.zllh.mall.goods.service;

public interface UserCategoryService {
	//判断是否存在该id，不存在就新增
	boolean addUserCategory(String parentIds,String categoryId,String mmbId);

}
