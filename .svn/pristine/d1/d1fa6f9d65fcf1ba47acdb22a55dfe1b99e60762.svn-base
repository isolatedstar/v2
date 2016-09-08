package com.zllh.mall.material.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMaterialBaseMapper;
import com.zllh.mall.common.model.MtMaterialBase;
import com.zllh.mall.material.service.MBaseService;
@Service
public class MBaseServiceImp implements MBaseService {
		
	@Autowired
	private MtMaterialBaseMapper mbaseMapper;

	@Override
	public boolean addMBase(MtMaterialBase mmb) {
		boolean flag = false;
		int num = mbaseMapper.insertSelective(mmb);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean updateMBase(MtMaterialBase mmb) {
		
		boolean flag = false;
		int num = mbaseMapper.updateByPrimaryKeySelective(mmb);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delMBase(String mbaseId) {
		//修改状态  0到1
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("id", mbaseId);
		map.put("status", 1);
		boolean flag = false;
		int num = mbaseMapper.updateById(map);
		if(num>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<MtMaterialBase> showMBase(Map<String, Object> map) {
		List<MtMaterialBase> list = null;
		list = mbaseMapper.selectByPrimaryKey(map);
		return list;
	}

	@Override
	public List<MtMaterialBase> getPub(String mmbId) {
		List<MtMaterialBase> pub = null;
		pub = mbaseMapper.selectBymmbId(mmbId);
		return pub;
	}

}
