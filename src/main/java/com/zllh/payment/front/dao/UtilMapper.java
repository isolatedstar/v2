package com.zllh.payment.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zllh.payment.model.DataDict;

public interface UtilMapper {

	List<DataDict> selectDataDictByCode(@Param("code") String code);
	
}