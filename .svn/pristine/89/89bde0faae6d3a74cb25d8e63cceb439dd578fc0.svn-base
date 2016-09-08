package com.zllh.mall.quote.service;

import java.util.Map;

import com.zllh.mall.common.model.MtQuoteScope;







public interface QuoteRelationService {
	//创建关系 用于创建地域，合作会员，群组关系
	public boolean addQutoRalation(MtQuoteScope qutoRalation);
	//根据报价Id以及type类型删除关系
	public boolean deleteQutoRalation(Map<String, Object> map);
	//根据调价，报价Id查询是否存在关系
	public boolean serachQuoteRalation(Map<String, Object> map);
	//新增会员关系
	public Map<String, Object> addMmbIds(String quoteId,String mmbIds,Integer rangType);
	//新增群组关系
	public Map<String, Object> addGroupIds(String quoteId,String groupIds,Integer rangType);
}

