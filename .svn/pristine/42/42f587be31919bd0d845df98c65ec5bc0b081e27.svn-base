package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtMember;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings("rawtypes")
public interface MtMemberMapper {
	//新增会员
    int insert(MtMember record);
    //修改会员
    int updatePubMmb(MtMember record);
    //修改会员状态
    int updatePubMmbStatus(MtMember record);
    //查询单个会员信息
    MtMember queryMmbById(String id);
    //条件查询所有会员信息
    List<MtMember> queryAllMmbByCondition(Map map);
    //条件查询会员总数
	int countMmbsByCondition(Map map);
	//根据当前会员id以及关联会员id查询信息
	MtMember queryInfoById(Map<String,Object> map);
	//根据报价id以及类型查询相关会员信息
	List<MtMember> queryMmbsInfoByQuoteId(Map<String, Object> map);
	//根据会员id以及type分页查询信息
	List<MtMember> queryMmbsByQuoteType(Map<String,Object> map);
	//根据会员id以及type查询数量信息
	int countMmbsByQuoteType(Map<String,Object> map);
	//支付系统—账户添加画面集团名称获取 add by---wxl
	List<MtMember> quetyMmbByOrgName(Map<String,Object> map);

	//验证简称:mmbSname  英文简称:mmbEngSname 注册全称:mmbFname 是否唯一
	int getNumByCheckType(@Param("checkField") String checkField,@Param("checkValue") String checkValue,@Param("id")String id);
	//验证管理员：operationName 账号是否唯一
	int getNumByOperationName(@Param("checkField") String checkField,@Param("checkValue") String checkValue,@Param("id")String id);
	
	//<!--  根据群组Id查询出群组所包含的会员-->
	List<MtMember> selsectMmmbByGroupId(Map<String,Object> map);
	int selsectMmmbByGroupIdCount(Map<String,Object> map);
	
	/**
	 * +@Title: findAllMtMember 
	 * @Description: 查询所有会员
	 * @param @return
	 * @return List<MtMember>
	 * @throws
	 */
    List<MtMember> findAllMtMember();
    /**
     * +@Title: findAllMtMember2
     * @Description: 查询会员1004
     * @param @return
     * @return List<MtMember>
     * @throws
     */
    List<MtMember> findAllMtMember2();
}