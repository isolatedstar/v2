package com.zllh.mall.quote.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtMaterialMapper;
import com.zllh.mall.common.dao.MtQuoteMapper;
import com.zllh.mall.common.model.MtMaterial;
import com.zllh.mall.common.model.MtQuote;
import com.zllh.mall.quote.service.QuoteService;




@Service
public class QuoteServiceImp implements QuoteService{
	@Autowired
	private MtQuoteMapper QuoteMapper;
	@Autowired
	private MtMaterialMapper  matericalMapper;
	//创建报价
	@Override
	public boolean addQute(MtQuote qute){
		boolean flag = true;
		//调用dao新增方法
		int num = QuoteMapper.insertSelective(qute);
		if(num>0){
			flag = true;
		}else{
			flag = false;
		}
		//根据结果返回ture或false
		return flag;
			
	}
	//编辑报价 根据报价Id修改报价
	@Override
	public boolean editQuto(MtQuote quto){
		//调用dao修改方法
		boolean flag = true;
		int num = QuoteMapper.updateQuate(quto);
		//根据结果返回ture或false
		if(num>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	//查询报价
	@Override
	public List<MtQuote> searchQuote(Map<String, Object> map){
		//在action中将查询条件放入以键值对形式放入Map关系
		return QuoteMapper.searchQuote(map);
	}
	//修改报价状态
	@Override
	public boolean editQutoByQuoteID(Map<String, Object> map) {
		boolean flag = true;
		//调用dao修改方法
		int num = QuoteMapper.updateQuoteByQuoteId(map);
		//根据结果返回ture或false
		if(num>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	@Override
	//通过报价Id，单查询报价
	public MtQuote searchQuoteById(String quoteId) {
		
		return QuoteMapper.searchQuoteById(quoteId);
	}
	@Override
	public long searchQuoteCount(Map<String, Object> map) {
		long num = QuoteMapper.searchQuoteCount(map);
		return num;
	}
	@Override
	public List<MtQuote> searchShop(Map<String, Object> map) {
		List<MtQuote> list = QuoteMapper.searchShop(map);
		MtMaterial mal = new MtMaterial();
		//获取它的标题图片路径
		if(list!=null&&list.size()>0){
		for (MtQuote mq : list) {
			if(!StringUtils.isBlank(mq.getTitlePic())){
				mal =matericalMapper.showById(mq.getTitlePic());
				if(mal!=null){
					mq.setImgPath(mal.getPicPath());
				}
				
			}
		}
		}
		return list;
	}
	@Override
	public Long searchShopCount(Map<String, Object> map) {
		Long num = QuoteMapper.searchShopCount(map);
		return num;
	}  
	

}
