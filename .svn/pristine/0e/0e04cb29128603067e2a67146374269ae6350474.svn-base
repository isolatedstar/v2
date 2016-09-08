package com.zllh.mall.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zllh.mall.common.model.MtAddressManager;
import com.zllh.mall.common.model.MtAddressManagerExample;

public interface MtAddressManagerMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MtAddressManager record);

	int insertSelective(MtAddressManager record);

	MtAddressManager selectByPrimaryKey(Integer id);

	List<MtAddressManager> selectByPrimaryKeyList();

	List<MtAddressManager> selectByAreaSysCodeList(String Id);

	int updateByExampleSelective(@Param("record") MtAddressManager record, @Param("example") MtAddressManagerExample example);

	int updateByExampleWithBLOBs(@Param("record") MtAddressManager record, @Param("example") MtAddressManagerExample example);

	int updateByExample(@Param("record") MtAddressManager record, @Param("example") MtAddressManagerExample example);

	int updateByPrimaryKeySelective(MtAddressManager record);

	int updateByPrimaryKeyWithBLOBs(MtAddressManager record);

	int updateByPrimaryKey(MtAddressManager record);

}