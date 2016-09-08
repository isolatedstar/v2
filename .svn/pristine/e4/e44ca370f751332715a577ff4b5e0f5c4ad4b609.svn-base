package com.zllh.factoring.financing.service;

import java.util.List;
import java.util.Map;

import com.zllh.factoring.common.model.FacFinancing;

public interface FinancingService {

	public void updateAssureMoney();
	
	public List<Map<String,Object>> getTypeById();
	
	public List<FacFinancing> getAllFinancing(Map<String,Object> map);
	
	public int saveFinancing(Map<String,Object> map);
	 
	/**
	 * 生成融资单号
	 */
	public String getMaxFinancingId();
	
	/**
	 * 获取长度
	 */
	public int getAllLength(Map<String,Object> map);
}
