package com.zllh.mall.mmbmmanage.service;

import java.util.List;
import com.zllh.mall.common.model.MtMmbType;


/**
 * 
 * @ClassName: IMMBBizTypeService 
 * @Description: 业务关系类型service
 * @author luobo
 * @date 2016-3-8 下午4:47:41 
 * @version V1.0
 */
public interface IMMBBizTypeService{
	/**
	 * 
	 * @Title: createMmbBizType 
	 * @Description: 创建会员业务类型
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	public boolean createMmbBizType(MtMmbType pubMmbType);
	
	/**
	 * 
	 * @Title: searchAllTypesByMid 
	 * @Description: 根据操作员id查询所有业务类型
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	public List<MtMmbType> searchAllTypesByMid(String mid);
	
	/**
	 * 
	 * @Title: deleteTypesByMid 
	 * @Description: 根据会员id删除所有业务类型
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	public boolean deleteTypesByMid(String mid);
	
	/**
	 * 
	 * @Title: deleteTypesByMid 
	 * @Description: 根据业务类型id删除
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	public boolean deleteBizTypeById(String id);
	
	/**
	 * 
	 * @Title: createMmbBizType 
	 * @Description: 修改会员业务类型
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-3-8 下午4:47:41 
	 */
	public boolean updateMmbBizType(MtMmbType pubMmbType);
	
}
