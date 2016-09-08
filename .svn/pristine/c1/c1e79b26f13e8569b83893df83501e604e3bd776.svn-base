package com.zllh.mall.user.service;

import java.util.List;
import java.util.Map;

import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.PubRoleUserBiz;
import com.zllh.common.common.model.PubUser;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.MtMuserBiz;
import com.zllh.mall.common.model.MtUserRelationship;
import com.zllh.mall.common.model.MtUserRelationshipBiz;


public interface IMUserService {

	/**
	 * 
	 * @param pUser 
	 * @Title: createUser 
	 * @Description: 创建操作员
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author yangdm
	 * @date 2015-12-28 下午5:11:02
	 */
	public boolean createUser(PubUser pUser, MtMuser user,String roles);
	
	/**
	 * 
	 * @Title: updateUser 
	 * @Description:修改操作员
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @date 2015-12-28 下午5:11:02
	 */
	public boolean updateUser(MtMuser user);
	
	
	/**
	 * 
	 * @Title: updateUserofPassword 
	 * @Description:修改操作员的密码
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author yangdm
	 * @date 2015-12-28 下午5:11:02
	 */
	public String queryUser(MtMuser user);
	
	/**
	 * 
	 * @Title: queryMRTSList 
	 * @Description: 查询操作员列表
	 * @param @param o
	 * @param @return    
	 * @return String    
	 * @throws 
	 * @author yangdm
	 * @date 2015-12-28 下午5:14:38
	 */
	public String queryUserList(MtMuser user);

//	创建代理操作员与会员关系
	public String addUserMMBReal(Object o);
//		调用DAO方法
//		根据结果返回ture或false
	
//	删除代理操作员与会员关系
	public String deleteUserMMBReal(Object o);
//		调用DAO方法
//		根据结果返回ture或false
	
//	查询所有的代理操作员：
	public String queryUserMMBReal(Object o);
//		我们需要在开始的时候判断操作员是否有此权限，
//		若查询返回不为空，返回ture
//		否则返回false

	public long countUserByContiontion(Map<String, Object> map);

	public List<MtMuserBiz> queryUserList(Map<String, Object> map);

	//生成添加操作员的界面,带回备选的角色类型
	public List<PubRole> toAddUser(String mmbId);

	public MtMuser checkUser(MtMuser muser);

	public List<PubRoleUserBiz> selectUserRoles(String userId);

	public MtMuser selectUserById(String userId);

	public boolean updateUser(PubUser pUser, MtMuser user, String roles);

	public boolean updateUser(MtMuser muser, PubUser pUser);

	public boolean addUserRela(MtUserRelationship muserRela);

	public boolean deleteUserRela(String ids);

	public List<MtUserRelationshipBiz> selectUserReal(String userId);

	public boolean createSupUser(String account,String email,String telephone,
			String name,String mmbName,String password,String mmbId);

	//查询所有未停用的平台操作员
	public List<MtMuser> queryUserList(String mmbId);

	//验证操作员唯一性
	public int getNumByAccount(String account,String id);

	/**
	 *停用/启用会员时 停用/启用其下属操作员
	 *
	 */
	public int updateMtUserStateByMember(Map map);
}