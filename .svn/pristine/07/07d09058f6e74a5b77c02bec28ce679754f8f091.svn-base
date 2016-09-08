package com.zllh.mall.quote.service;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtQuote;




public interface QuoteServiceRedis {

	/*
	 * 通过key获取 redis 的list
	 * @param listName 登陆人Id
	 * @return  选择的报价对象集合
	 */
	public List<MtQuote> getListByName(String key);
	// 添加到队列  先进先出
	public boolean addQuoteIn(String key ,MtQuote quote);
	//获取队列的长度
	public Long getQuoteLengthByKey(String key);
	//队列是否为空
	public boolean keyEmpty(String key);

	// 移除指定的任务    1表示移除的个数
	public void removeQuoteByKey(String key,MtQuote quote) ;
	// 清空任务队列
	public void clearByKey(String key);
	// 更新指定的任务
	public void updateQuoteByKey(String key,long index,MtQuote task);
	//查找出指定的任务
	public MtQuote getQuoteByIndex(String key ,long index);
	//根据范围查找出指定的任务集合  起始为0
	public List<MtQuote> getListByNameByRang(String key,long start,long end);
	//获取该登陆人的集合，并根据mmbId分类
	public Map<String, List<MtQuote>> getListByNameByUser(String key);

}
