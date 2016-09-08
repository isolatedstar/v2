package com.zllh.mall.common.dao;

import java.util.List;

import com.zllh.mall.common.model.MtAccountMananger;

public interface MtAccountManangerMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MtAccountMananger record);

	int insertSelective(MtAccountMananger record);

	MtAccountMananger selectByPrimaryKey(Integer id);

	List<MtAccountMananger> selectByPrimaryKeyList();

	List<MtAccountMananger> selectByPrimaryKeyId(Integer id);

	int updateByPrimaryKeySelective(MtAccountMananger record);

	int updateByPrimaryKey(MtAccountMananger record);

	List<MtAccountMananger> selectByAccountType(String id);

	MtAccountMananger queryBankByAccountCode(String id);
}