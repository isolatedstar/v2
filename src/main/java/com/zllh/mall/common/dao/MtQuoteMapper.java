package com.zllh.mall.common.dao;

import java.util.List;
import java.util.Map;

import com.zllh.mall.common.model.MtQuote;







public interface MtQuoteMapper {
    int insert(MtQuote record);

    int insertSelective(MtQuote record);
    //根据报价Id修改报价状态
    int updateQuoteByQuoteId(Map<String,Object> params);
    //根据报价Id修改报价对象
    int updateQuate(MtQuote record);
    //查询报价
  	List<MtQuote> searchQuote(Map<String, Object> map);
  	//通过报价Id，单查询报价
  	MtQuote searchQuoteById(String quoteId);
  	//查询报价数量
  	Long searchQuoteCount(Map<String, Object> map);
  	//查询商城报价  searchShop
  	List<MtQuote> searchShop(Map<String, Object> map);
  	//查询商城报价数量  searchShopCount
    Long searchShopCount(Map<String, Object> map);
}