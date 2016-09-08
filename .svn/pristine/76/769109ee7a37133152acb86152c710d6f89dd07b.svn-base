package com.zllh.mall.quote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.model.MtQuote;
import com.zllh.mall.quote.service.QuoteServiceRedis;
import com.zllh.utils.redis.base.BaseRedisDaoImpl;





@Service
public class QuoteServiceRedisImpl  implements QuoteServiceRedis {
	

	@Autowired
	private BaseRedisDaoImpl<String, MtQuote> baseRedisDaoImpl;
	
	/*
	 * 通过key获取 redis 的list
	 * @param listName 登陆人Id
	 * @return  选择的报价对象集合
	 */
	@Override
	public List<MtQuote> getListByName(String key){
		return baseRedisDaoImpl.getListByName(key);
	}
	// 添加到队列  先进先出
	@Override
	public boolean addQuoteIn(String key ,MtQuote quote){
		Long num = baseRedisDaoImpl.in(key, quote);
		boolean flag = true;
		if(num<=0){
			flag = false;  
		}
		return flag;
	}
	//获取队列的长度
	@Override
	public Long getQuoteLengthByKey(String key){
		return baseRedisDaoImpl.length(key);
	}
	//队列是否为空
	@Override
	public boolean keyEmpty(String key) {                                    
		return baseRedisDaoImpl.length(key) == 0;
	}

	// 移除指定的任务    1表示移除的个数
	@Override
	public void removeQuoteByKey(String key,MtQuote quote) {
		baseRedisDaoImpl.remove(key, 1, quote);
	}
	// 清空任务队列
	@Override
	public void clearByKey(String key) {
		baseRedisDaoImpl.removeAll(key);
	}
	// 更新指定的任务
	@Override
	public void updateQuoteByKey(String key,long index,MtQuote task) {
		baseRedisDaoImpl.set(key, index, task);
	}
	//查找出指定的任务
	@Override
	public MtQuote getQuoteByIndex(String key ,long index){
		return baseRedisDaoImpl.index(key, index);
	}
	//根据范围查找出指定的任务集合  起始为0
	@Override
	public List<MtQuote> getListByNameByRang(String key,long start,long end){
		return baseRedisDaoImpl.range(key, start, end);
	}
	//获取该登陆人的集合，并根据报价所属的mmbId分类
	@Override
	public Map<String, List<MtQuote>> getListByNameByUser(String key){
		List<MtQuote> list= baseRedisDaoImpl.getListByName(key);
		Map<String, List<MtQuote>> map =  new HashMap<String, List<MtQuote>>();
		Integer index =0; 
		for( MtQuote mt : list){ 
			mt.setIndex(index);
			String  aa = mt.getMmbId()+","+mt.getMmbName();
			//containsKey方法——判断是否包含指定的键名
			if(map.containsKey(aa)){
				 map.get(aa).add(mt);
			}else{
				List<MtQuote> nn = new ArrayList<MtQuote>();
				nn.add(mt);
				map.put(aa, nn);
			}
			index++;
		}
		return map;
	}
}
