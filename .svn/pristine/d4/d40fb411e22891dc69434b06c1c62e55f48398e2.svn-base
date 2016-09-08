package com.zllh.mall.mmbmmanage.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMemberRelationship;


/**
 * @ClassName: IMmbRelationshipService 
 * @Description: 会员关系service
 * @author luobo
 * @date 2016-03-07 下午4:47:41 
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public interface IMmbRelationshipService{
	/**
	 * @Title: createMmbRelationship 
	 * @Description: 创建会员地域业务
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public boolean createMmbRelationship(MtMemberRelationship mmbRelationship);
	
	public boolean batchAddMmbRelationship(List<MtMemberRelationship> list);
	
	/**
	 * @Title: createMMB 
	 * @Description: 修改会员关系
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public boolean updateMmbRelationship(MtMemberRelationship mmbRelationship);

	/**
	 * @Title: queryMMBList 
	 * @Description: 根据条件查询会员关系集合
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMemberRelationship> queryMmbRelationShipForBuy(Map map);
	
	/**
	 * @Title: queryMMBList 
	 * @Description: 根据条件查询买方为传入id的总数
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public int countMmbRelationShipForBuy(Map map);
	
	/**
	 * @Title: queryMmbRelationShips 
	 * @Description: 根据条件查询会员关系集合
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMemberRelationship> queryMmbRelationShips(Map map);
	
	/**
	 * @Title: queryMmbRelationShipsByGrade 
	 * @Description: 根据等级以及会员id查询关注目录信息
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMemberRelationship> queryMmbRelationShipsByGrade(Map map);
	
	/**
	 * @Title: queryMmbRelationShipsByGrade 
	 * @Description: 根据等级以及会员id查询关注目录信息
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public List<MtMemberRelationship> queryMmbRelationShipsByType(Map map);
	
	/**
	 * @Title: countMmbRelationShips 
	 * @Description: 根据条件查询买方为传入id的总数
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author luobo
	 * @date 2016-03-07 下午4:47:41 
	 */
	public int countMmbRelationShips(Map map);
	
	/**
	 * JSON数组转对象
	 * @param jsonArrayStr
	 * @return
	 */
	public List<MtMemberRelationship> jsonArrayStrToObjects(String jsonArrayStr);
	
	/**
	 * 批量删除关系会员信息
	 * @param ids
	 * @return
	 */
	public boolean deleteMmbRelationships(List<String> ids);
	
	/**
	 * 删除关系会员信息
	 * @param id
	 * @return
	 */
	public boolean deleteMmbRelationshipById(String id);
	
	/**
	 * 升级关系至业务合作关系
	 * @param mmbId
	 * @param relaMmbId
	 * @return
	 */
	public Map<String,Object> upMmbRelaToBizOperation(String mmbId,String relaMmbId);
	
	/**
	 * 根据条件删除关系数据
	 * @param map
	 * @return
	 */
	public boolean deleteMmbRelationships(Map map);
	
	/**
	 * 批量处理会员关系信息
	 * @param mmbIds
	 * @param mmbId
	 * @param type
	 * @return
	 */
	public boolean batchAddMmbRelas(String mmbIds,String mmbId,String type);
	
	/**
	 * 停止会员关系
	 * @param mmbId
	 * @param relaMmbId
	 * @param type
	 * @return
	 */
	public String stopBizRelationShip(String mmbId,String relaMmbId,String type);
	
	/**
	 * 开启会员关系
	 * @param mmbId
	 * @param relaMmbId
	 * @param type
	 * @return
	 */
	public String openBizRelationShip(String mmbId,String relaMmbId,String type);
	
	/**
	 * 根据当前会员查询有关系的会员信息
	 * @param mid
	 * @return
	 */
	public List<MtMemberRelationship> queryMmbRelationshipByMid(String mid);

	/**
	 * 根据当前会员及协议类型取得对应的关系会员列表
	 * @param map
	 * @return
	 */
	public List<MtMemberRelationship> getMmbRealByContract(Map  map);

	/**
	 * 根据当前会员ID 得到有关系的会员目录（买卖借贷）
	 * @param mmbId
	 * @return
	 */
	public List getOperationRelaByMmbId(String mmbId);


	/**
	 * 降级为仅关注
	 * @param map
	 * @return
	 */
	public int lowerToConcernOperation(Map map);


	/**
	 * 根据mmbId relaMmbId 和 关系类型 判断是否有会员关系
	 * @param map
	 * @return
	 */
	public MtMemberRelationship getMembRelaByMap(Map map);

	/**
	 * 根据关注等级 得到改关注等级下有多少关注会员
	 * @param level
	 * @return
	 */
	public int getConcernRelaNumsByLevel(Map map);
	
}
