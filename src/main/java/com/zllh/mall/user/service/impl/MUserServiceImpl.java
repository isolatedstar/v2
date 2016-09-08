package com.zllh.mall.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zllh.utils.common.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.common.common.dao.PubRoleMapper;
import com.zllh.common.common.dao.PubRoleUserMapper;
import com.zllh.common.common.dao.PubUserMapper;
import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.PubRoleUser;
import com.zllh.common.common.model.PubRoleUserBiz;
import com.zllh.common.common.model.PubUser;
import com.zllh.mall.common.dao.MtMuserMapper;
import com.zllh.mall.common.dao.MtUserRelationshipMapper;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.MtMuserBiz;
import com.zllh.mall.common.model.MtUserRelationship;
import com.zllh.mall.common.model.MtUserRelationshipBiz;
import com.zllh.mall.user.service.IMUserService;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.UUIDCreater;

@Service
public class MUserServiceImpl implements IMUserService{
	
	@Autowired
	private MtMuserMapper muserMapper;
	
	@Autowired
	private PubUserMapper puserMapper; 
	
	@Autowired
	private PubRoleMapper pRoleMapper;
	
	@Autowired
	private PubRoleUserMapper pRoleUserMapper;
	
	@Autowired
	private MtUserRelationshipMapper muserRelaMapper;
	
	@Override
	public boolean createUser(PubUser pUser, MtMuser user,String roles) {
//		创建平台操作员，对面传过来一个当前会员对应的业务动能的集合（通过查询mmb_mmbtype和membertype实现）
//		选择业务功能作为当前操作员的工作界限
//		后台：首先需要添加一条操作员的数据（人员表 user）,并确定他所属的会员
//		然后添加操作员和业务类型之间的关系（member_type）
//		根据结果返回ture或false
		int flag=0;
		//生成用户角色表的ID
		String UserId=user.getId();
		int mark=muserMapper.insertSelective(user);
		int mark2=puserMapper.insertSelective(pUser);
		if (roles!=null) {
			//判断选择的角色是否为多个
			if(roles.contains(",")){
				//判断传过来的群组是否为多个
				List<String> roleList=java.util.Arrays.asList(roles.split(","));
				for(String role:roleList){
					flag=addUserRoleByOne(UserId,role);
				}
			}else{
				flag=addUserRoleByOne(UserId,roles);
			}
		}
		if(mark>0&&mark2>0){
			if (roles!=null){
				if (flag<1) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}

	//添加用户角色信息
	private int addUserRoleByOne(String userId, String roles) {
		/*
		 * 根据传过来的参数组装为一个对象
		 * 把此对象添加到数据库中
		 */
		int flag=0;
		//UserRole的标志ID
		String id=UUIDCreater.getUUID();
		PubRoleUser pRoleUser=new PubRoleUser();
		//封装对象
		pRoleUser.setId(id);
		pRoleUser.setRoleId(roles);
		pRoleUser.setUserId(userId);
		//添加用户角色
		flag=pRoleUserMapper.insertSelective(pRoleUser);
		return flag;
	}
	
	//新增超级操作员信息,会员使用
	@Override
	public boolean createSupUser(String account,String email,String telephone,
			String name,String mmbName,String password,String mmbId) {
		boolean flag=false;
		MtMuser muser=new MtMuser();
		PubUser pUser=new PubUser();
		String id=UUIDCreater.getUUID();
		String newDate=DateUtil.getNow();
		//封装数据
		//密码
		if(!StringUtils.isBlank(password)){
			String enPass = Utils.securityUtil.encodePassword(account,password);
			muser.setPassword(enPass);
			pUser.setPassword(enPass);
		}
		//登录账号
		if(!StringUtils.isBlank(account)){
			muser.setAccount(account);
			pUser.setAcountName(account);
		}
		if(!StringUtils.isBlank(email)){
			muser.setEmail(email);
		}
		if(!StringUtils.isBlank(telephone)){
			muser.setTelephone(telephone);
		}
		//操作员名称
		if(!StringUtils.isBlank(name)){
			muser.setName(name);
			pUser.setUserName(name);
		}
		//会员ID
		if(!StringUtils.isBlank(mmbId)){
			muser.setMmbId(mmbId);
		}
		//会员姓名
		if(!StringUtils.isBlank(mmbName)){
			muser.setMmbName(mmbName);
		}
		//添加操作员ID 
		muser.setId(id);
		pUser.setUserId(id);
		//添加创建时间
		pUser.setCreatedatetime(newDate);
		//添加状态值,默认是1,表示已开启
		muser.setState(1);
		
		//添加操作员，默认赋于的角色ID为101
		flag=createUser(pUser,muser,"101");
		return flag;
	}
	
	@Override
	public boolean updateUser(PubUser pUser, MtMuser user, String roles) {
//		首先,同样在开始的时候需要查询出当前操作员所拥有的业务类型
//		修改操作员的相关属性,以业务集合和对象的形式他们传到后台进行修改
//		修改业务的时候,需要两张表(user和mmbtype_role)一起联查
//		修改个人信息的时候直接传对象即可
//		根据结果返回ture或false
		
		int flag=0;
		//生成用户角色表的ID
		String UserId=user.getId();
		//通过会员ID删除用户原来的角色信息
		int flag2=pRoleUserMapper.deleteByUserId(UserId);
		int mark=muserMapper.updateByPrimaryKeySelective(user);
		int mark2=puserMapper.updateByPrimaryKeySelective(pUser);
		//判断选择的角色是否为多个
		if(roles.contains(",")){
			//判断传过来的群组是否为多个
			List<String> roleList=java.util.Arrays.asList(roles.split(","));
			for(String role:roleList){
				flag=addUserRoleByOne(UserId,role);
			}
		}else{
			flag=addUserRoleByOne(UserId,roles);
		}
		if(mark>0&&flag>0&&mark2>0&&flag2>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateUser(MtMuser muser) {
//		根据传入的ID，调用DAO方法，修改操作员状态值
//		删除操作员与业务业务角色的所属关系
//		根据结果返回ture或false
		int falg=muserMapper.updateByPrimaryKeySelective(muser);
		if (falg>0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public String queryUser(MtMuser user) {
//		调用查询操作员的DAO方法
//		根据结果返回相关操作员
		return null;
	}

	@Override
	public String queryUserList(MtMuser user) {
//		调用查询操作员的DAO方法
//		根据结果返回相关操作员列表
		return null;
	}

	@Override
	public String addUserMMBReal(Object o) {
//		添加平台代理员与会员操作员，调用DAO方法
//		根据结果返回ture或false
		return null;
	}

	@Override
	public String deleteUserMMBReal(Object o) {
//		删除平台代理员与会员操作员，调用DAO方法
//		根据结果返回ture或false
		return null;
	}

	@Override
	public String queryUserMMBReal(Object o) {
//		我们需要在开始的时候判断操作员是否有此权限，
//		若查询返回不为空，返回ture
//		否则返回false
		return null;
	}

	@Override
	public long countUserByContiontion(Map<String, Object> map) {
		int total=muserMapper.countUserByContiontion(map);
		return total;
	}

	@Override
	public List<MtMuserBiz> queryUserList(Map<String, Object> map) {
		List<MtMuserBiz> list=muserMapper.queryUserList(map);
		return list;
	}

	@Override
	public List<PubRole> toAddUser(String mmbId) {
		List<PubRole> list=pRoleMapper.toAddUser(mmbId);
		return list;
	}

	//检查此操作员登录账号是否已经被使用
	@Override
	public MtMuser checkUser(MtMuser muser) {
		MtMuser mtMuser=new MtMuser();
		mtMuser=muserMapper.checkUser(muser);
		return mtMuser;
	}

	//通过操作员的ID查询自己的角色集合
	@Override
	public List<PubRoleUserBiz> selectUserRoles(String userId) {
		List<PubRoleUserBiz> list=pRoleUserMapper.selectUserRoles(userId);
		return list;
	}

	//通过操作员的ID查询自己的相关信息
	@Override
	public MtMuser selectUserById(String id) {
		MtMuser muser=muserMapper.selectUserById(id);
		return muser;
	}

	//修改用户的密码
	@Override
	public boolean updateUser(MtMuser muser, PubUser pUser) {
		int falg=muserMapper.updateByPrimaryKeySelective(muser);
		int flag2=puserMapper.updateByPrimaryKeySelective(pUser);
		if (falg>0&&flag2>0) {
			return true;
		}else {
			return false;	
		}
	}

	//添加代理操作员关系
	@Override
	public boolean addUserRela(MtUserRelationship muserRela) {

		//插入之前先进行删除操作
		if(StringUtils.isNotEmpty(muserRela.getBusUserId()) && StringUtils.isNotEmpty(muserRela.getPlaUserId())){
			Map<String,Object> relMap = new HashMap<>();
			relMap.put("busUserId",muserRela.getBusUserId());
			relMap.put("plaUserId",muserRela.getPlaUserId());
			muserRelaMapper.deleteMtUserRelBeforeInsert(relMap);
		}

		if(muserRelaMapper.insertSelective(muserRela)>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteUserRela(String ids) {
		int flag=0;
		if (ids.contains(",")) {
			List<String> idList=java.util.Arrays.asList(ids.split(","));
			for(String id:idList){
				flag=muserRelaMapper.deleteByPrimaryKey(id);
			}
		}else {
			flag=muserRelaMapper.deleteByPrimaryKey(ids);
		}
		if (flag>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<MtUserRelationshipBiz> selectUserReal(String userId) {
		List<MtUserRelationshipBiz> list=muserRelaMapper.selectUserRealByUserId(userId);
		return list;
	}

	//查询所有未停用的平台操作员
	@Override
	public List<MtMuser> queryUserList(String mmbId) {
		List<MtMuser> list=muserMapper.queryUserListAll(mmbId);
		return list;
	}

	@Override
	public int getNumByAccount(String account,String id) {
		return puserMapper.getNumByAccount(account,id);
	}

	@Override
	public int updateMtUserStateByMember(Map map) {
		return muserMapper.updateMtUserStateByMember(map);
	}

}
