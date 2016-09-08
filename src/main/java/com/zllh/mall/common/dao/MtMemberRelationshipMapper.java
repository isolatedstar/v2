package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;
import com.zllh.mall.common.model.MtMemberRelationship;

@SuppressWarnings("rawtypes")
public interface MtMemberRelationshipMapper {
	// 新增会员关系
	int insert(MtMemberRelationship record);

	// 新增by条件
	int insertSelective(MtMemberRelationship record);

	// 修改会员关系
	int updateMmbRelationship(MtMemberRelationship record);

	// 统一查询总数
	int countMmbRelationshipByCondition(Map map);

	// 模糊查询
	List<MtMemberRelationship> queryMmbRelationshipsByCon(Map map);

	// 根据id删除会员关系
	int deleteMmbRelationshipById(String id);

	//根据mmbId 和 relammbid 删除会员关系
	int deleteRelaByRelationShip(Map map);

	// 根据会员以及关系会员id删除相关数据
	int deleteMmbRelationships(Map map);

	// 查询待审核会员关系总数
	int countMmbRelationships(Map map);

	// 查询待审核会员关系数据
	List<MtMemberRelationship> queryMmbRelationships(Map map);

	// 根据会员等级查询数据
	List<MtMemberRelationship> queryMmbRelationshipsByGrade(Map map);
	//根据会员等级查询数据
	List<MtMemberRelationship> queryMmbRelationshipsByGrade1(Map map);

	// 根据会员合作类型查询数据
	List<MtMemberRelationship> queryMmbRelationshipsByType(Map map);
	
	//根据条件查询对象是否存在
	List<MtMemberRelationship> queryMmbRelationshipsByCondition(MtMemberRelationship ship);
	
	//根据条件删除会员关系信息
	int deleteRelaMmbsByCondition(Map map);
	
	//根据当前mmbId查询所有会员关系
	List<MtMemberRelationship> queryMmbRelationshipByMid(String mid);

	//根据当前会员ID 得到有关系的会员目录（买卖借贷）
	List getOperationRelaByMmbId(String mmbId);

	//降级为仅关注
	int lowerToConcernOperation(Map map);

	//查看2个会员是否已有关系
	MtMemberRelationship getMembRelaByMap(Map map);

	//根据当前会员和合作协议类型 得到关系会员列表
	List<MtMemberRelationship> getMmbRealByContract(Map map);

	//得到各关注等级个数
	int getConcernRelaNumsByLevel(Map map);
}