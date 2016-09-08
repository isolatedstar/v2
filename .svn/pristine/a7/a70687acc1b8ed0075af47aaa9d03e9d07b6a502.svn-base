package com.zllh.mall.mmbmmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zllh.mall.common.dao.MtMmbTypeMapper;
import com.zllh.mall.common.model.MtMmbType;
import com.zllh.mall.mmbmmanage.service.IMMBBizTypeService;
	

@Service
public class MmbBizTypeServiceImpl implements IMMBBizTypeService{

	@Autowired
	private MtMmbTypeMapper bizTypeMapper;
	
	/**
	 * 创建会员业务关系
	 */
	@Override
	public boolean createMmbBizType(MtMmbType pubMmbType) {
		boolean flag = false;
		int result = bizTypeMapper.insert(pubMmbType);
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 修改会员业务关系
	 */
	@Override
	public boolean updateMmbBizType(MtMmbType pubMmbType) {
		boolean flag = false;
		int result = bizTypeMapper.updateByPrimaryKey(pubMmbType);
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * @Title: searchAllTypesByMid 
	 * @Description: 根据操作员id查询所有业务类型
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	@Override
	public List<MtMmbType> searchAllTypesByMid(String mid) {
		return bizTypeMapper.queryAllBizTypesByMid(mid);
	}

	/**
	 * @Title: deleteTypesByMid 
	 * @Description: 根据操作员id删除所有业务类型
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	@Override
	public boolean deleteTypesByMid(String mid) {
		boolean flag = false;
		int result = bizTypeMapper.deleteAllBizTypesByMid(mid);
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * @Title: deleteTypesByMid 
	 * @Description: 根据业务类型id删除
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	@Override
	public boolean deleteBizTypeById(String id) {
		boolean flag = false;
		int result = bizTypeMapper.deleteByPrimaryKey(id);
		if(result>0){
			flag = true;
		}
		return flag;
	}


}
