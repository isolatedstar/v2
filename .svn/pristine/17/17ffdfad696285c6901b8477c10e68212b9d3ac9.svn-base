package com.zllh.mall.mmbmmanage.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMmbAreaBiz;

/**
 * 
 * @ClassName: IMMBService 
 * @Description: 会员地域业务service
 * @author luobo
 * @date 2016-03-07 下午4:47:41 
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public interface IMmbAreaBizService{
	/**
	 * @Title: createMMB 
	 * @Description: 创建会员地域业务
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public boolean createMMBAreaBiz(MtMmbAreaBiz pubMmbAreaBiz);
	
	public boolean batchAddAreaBiz(List<MtMmbAreaBiz> list,String mid);
	
	/**
	 * @Title: createMMB 
	 * @Description: 修改会员地域业务
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public boolean updateMMBAreaBiz(MtMmbAreaBiz pubMmbAreaBiz);

	/**
	 * @Title: queryMMBList 
	 * @Description: 根据条件查询会员地域业务
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMmbAreaBiz> queryMMBAreaBizList(Map map);
	
	/**
	 * @Title: queryMmbAreaBizByMid 
	 * @Description: 根据条件查询会员地域业务
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMmbAreaBiz> queryMmbAreaBizByMid(String mid);
	
	/**
	 * @Title: queryMMBList 
	 * @Description: 根据条件匹配查询会员地域业务总数
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public int countMMBAreaBizList(Map map);
	
	/**
	 * 查询单个会员地域业务对象
	 * @param id
	 * @return
	 */
	public MtMmbAreaBiz queryMMBAreaBizById(String id);
	
	/**
	 * JSON数组转对象
	 * @param jsonArrayStr
	 * @return
	 */
	public List<MtMmbAreaBiz> jsonArrayStrToObjects(String jsonArrayStr,String mmbId);

	/**
	 * 根据mid删除其下所有的地域数据信息
	 * @param mid
	 * @return
	 */
	public boolean deleteMmbBizsByMid(String mid);
	
}
