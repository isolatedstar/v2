package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.common.common.model.PubRole;
import com.zllh.common.common.model.model_extend.RoleExtendBean;
import com.zllh.mall.common.model.MtCtr;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings("rawtypes")
public interface MtCtrMapper {


	
    int insert(MtCtr record);

    int insertSelective(MtCtr record);
    
	List<MtCtr> queryContractByCondition(Map map);
	
	int countContracts(Map map);
	
	int updateContracts(MtCtr MtCtr);

	List<MtCtr> getTradeName(MtCtr mtCtr);
	
//	List<MtCtr> getContractByName(String mmid);
	
	List<MtCtr>getTradeNameByOne(MtCtr MtCtr);

	int updateContractToNextStatus(Map map);

	/**
	 * 根据协议ID 和协议类型 得到合作协议
	 * @param id
	 * @param contractType
	 * @return
	 */
	MtCtr getContractById(@Param("id") String id ,@Param("type") String contractType );

	int agreeContract(Map agreeMap);

	/**
	 * app登录成功后从sessionID中取出role列表 然后查询pub_role_app_menu_rel 得到app_menu权限列表
	 * @param map
	 * @return
	 */
	List<String> getAppMenusByRoles(Map map);

	Map<String,Object> getAppVersion();

}