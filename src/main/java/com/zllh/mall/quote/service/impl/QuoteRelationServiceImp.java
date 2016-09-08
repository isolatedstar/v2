package com.zllh.mall.quote.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtQuoteMapper;
import com.zllh.mall.common.dao.MtQuoteScopeMapper;
import com.zllh.mall.common.model.MtQuote;
import com.zllh.mall.common.model.MtQuoteScope;
import com.zllh.mall.quote.service.QuoteRelationService;
import com.zllh.utils.common.DictionaryUtil;
import com.zllh.utils.common.UUIDCreater;
@Service
public class QuoteRelationServiceImp implements QuoteRelationService {
		@Autowired
		private MtQuoteScopeMapper MtQuoteScopeMapper;
		@Autowired
		private MtQuoteMapper quoteMapper;
		//创建关系 用于创建地域，合作会员，群组关系
		public boolean addQutoRalation(MtQuoteScope qutoRalation){
			//创建关系对象，封装必要的参数
			boolean flag = true;
			//调用dao
			int num = MtQuoteScopeMapper.insertSelective(qutoRalation);
			//根据结果返回ture或false
			if(num>0){
				flag =true;
			}else{
				flag = false;
			}
			return flag;
		}
		//根据报价Id以及type类型删除关系
		public boolean deleteQutoRalation(Map<String, Object> map){
			boolean flag = true;
			//调用dao
			int num = MtQuoteScopeMapper.deleteQutoRalation(map);
			
			//根据结果返回ture或false
			if(num>0){
				flag =true;
			}else{
				flag = false;
			}
			return flag;
		}
		//根据调价，报价Id查询是否存在关系
		@Override
		public boolean serachQuoteRalation(Map<String, Object> map) {
			boolean flag = true;
			List<MtQuoteScope> pr = MtQuoteScopeMapper.serachQuoteRalation(map);
			if(pr!=null&&pr.size()>0){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		}
		@Override
		public Map<String, Object> addMmbIds(String quoteId, String mmbIds,
				Integer rangType) {
			Map<String, Object> map = new HashMap<String, Object>();
			boolean flag = true;
			//判断是否需要修改状态
			if(rangType!=DictionaryUtil.RELA_QUOTE_RANGTYPE_PRI){
				//调用修改方法
				MtQuote mt = new MtQuote();
				mt.setId(quoteId);
				mt.setRangType(DictionaryUtil.RELA_QUOTE_RANGTYPE_PRI);
				//调用修改方法
				int num = quoteMapper.updateQuate(mt);
				if(num>0){
					flag = true;
				}else{
					flag = false;
				}
			}
			int num = 0;
			if(flag){
				List<String> mmbIdList = java.util.Arrays.asList(mmbIds.split(","));
				for (String mmbId : mmbIdList) {
					MtQuoteScope mp = new MtQuoteScope();
					mp.setId(UUIDCreater.getUUID());
					mp.setQuoteId(quoteId);
					mp.setScopeId(mmbId);
					mp.setType(DictionaryUtil.RELA_QUOTE_TYPE_OMMB);
					//新增数据之前，先删除此条数据，防止重复
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("quoteId", quoteId);
					params.put("scopeId",mmbId );
					params.put("type", DictionaryUtil.RELA_QUOTE_TYPE_OMMB);
					MtQuoteScopeMapper.deleteQutoRalation(params);
					MtQuoteScopeMapper.insert(mp);
					num++;
				}
				if(num==mmbIdList.size()){
					map.put("successMsg", "添加会员成功！");
				}else{
					map.put("errorMsg", "添加会员失败！");
					throw new RuntimeException("添加会员失败！");
				}
				
			}else{
				map.put("errorMsg", "修改报价类型失败！");
				throw new RuntimeException("修改报价类型失败！");
			}
			
			
			return map;
		}
		@Override
		public Map<String, Object> addGroupIds(String quoteId, String groupIds,
				Integer rangType) {
			Map<String, Object> map = new HashMap<String, Object>();
			boolean flag = true;
			//判断是否需要修改状态
			if(rangType!=1){
				//调用修改方法
				MtQuote mt = new MtQuote();
				mt.setId(quoteId);
				mt.setRangType(DictionaryUtil.RELA_QUOTE_RANGTYPE_PRI);
				//调用修改方法
				int num = quoteMapper.updateQuate(mt);
				if(num>0){
					flag = true;
				}else{
					flag = false;
				}
			}
			int num = 0;
			if(flag){
				List<String> groupIdList = java.util.Arrays.asList(groupIds.split(","));
				for (String groupId : groupIdList) {
					MtQuoteScope mp = new MtQuoteScope();
					mp.setId(UUIDCreater.getUUID());
					mp.setQuoteId(quoteId);
					mp.setScopeId(groupId);
					mp.setType( DictionaryUtil.RELA_QUOTE_TYPE_GROUP);
					//新增数据之前，先删除此条数据，防止重复
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("quoteId", quoteId);
					params.put("scopeId",groupId );
					params.put("type", DictionaryUtil.RELA_QUOTE_TYPE_GROUP);
					MtQuoteScopeMapper.deleteQutoRalation(params);
					MtQuoteScopeMapper.insert(mp);
					num++;
				}
				if(num==groupIdList.size()){
					map.put("successMsg", "添加群组成功！");
				}else{
					map.put("errorMsg", "添加群组失败！");
					throw new RuntimeException("添加群组失败！");
				}
				
			}else{
				map.put("errorMsg", "修改报价类型失败！");
				throw new RuntimeException("修改报价类型失败！");
			}
			return map;
		}

}
