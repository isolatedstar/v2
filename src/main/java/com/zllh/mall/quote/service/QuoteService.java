package com.zllh.mall.quote.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtQuote;






public interface QuoteService {
	//创建报价
	public boolean addQute(MtQuote qute);
	//编辑报价 根据报价Id修改报价
	public boolean editQuto(MtQuote quto);
	//查询报价
	public List<MtQuote> searchQuote(Map<String, Object> map);
	//修改报价状态
	public boolean editQutoByQuoteID(Map<String, Object> map);
	//通过报价Id，单查询报价
	public MtQuote searchQuoteById(String quoteId);
	//查询报价数量
	public long searchQuoteCount(Map<String, Object> map);
	//查询商城报价  searchShop
	public List<MtQuote> searchShop(Map<String, Object> map);
	//查询商城报价数量  searchShopCount
	public Long searchShopCount(Map<String, Object> map);
	
}
