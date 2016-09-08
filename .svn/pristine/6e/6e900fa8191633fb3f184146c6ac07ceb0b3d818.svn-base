package com.zllh.mall.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMmbCategoryMapper;
import com.zllh.mall.common.model.MtMmbCategory;
import com.zllh.mall.goods.service.UserCategoryService;
import com.zllh.utils.common.UUIDCreater;

@Service
public class UserCategoryServiceImp implements UserCategoryService {
	@Autowired
	private MtMmbCategoryMapper mmcMapper;//用户品类

	@Override
	public boolean addUserCategory(String parentIds, String categoryId,String mmbId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId", mmbId);
		map.put("categoryId",categoryId);
		MtMmbCategory mc = mmcMapper.serachByIds(map);
		if(mc==null){
			List<String> categoryIdList = java.util.Arrays.asList(parentIds.split(","));
			for (String category : categoryIdList) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("mmbId", mmbId);
				maps.put("categoryId",category);
				MtMmbCategory muc = mmcMapper.serachByIds(map);
				if(muc==null){
					//查不到就新增
					MtMmbCategory mmc = new MtMmbCategory();
					mmc.setId(UUIDCreater.getUUID());
					mmc.setMmbId(mmbId);
					mmc.setCategoryId(category);
					mmcMapper.insertSelective(mmc);
				}
			}
		}
		return true;
	}

}
