package com.zllh.mall.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zllh.mall.common.dao.MtGoodMalMapper;
import com.zllh.mall.common.dao.MtGoodsMapper;
import com.zllh.mall.common.model.MtGoodMal;
import com.zllh.mall.common.model.MtGoods;
import com.zllh.mall.goods.service.GoodsService;
import com.zllh.utils.common.DateUtil;
import com.zllh.utils.common.UUIDCreater;




@Service
public class GoodsServiceImp implements GoodsService {
	
	@Autowired
	private MtGoodsMapper goodsMapper;
	@Autowired
	private MtGoodMalMapper malMapper;
	//GoodsService
	//查询某会员下该品类的所有商品
	@Override
	public List<MtGoods> searchGoods(Map<String, Object> params){
		//通过会员Id 品类Id查询商品
		//调用dao查询方法
		List<MtGoods> list = goodsMapper.searchGoods(params);
		//返回Good集合
		return list;
	}	
	//查询某会员下该品类的所有商品的数目
	@Override
	public long searchGoodCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		long num = goodsMapper.searchGoodCount(params);
		return num;
	}
	//新增商品
	@Override
	public Map<String, Object>addGood(MtGoods good,String imageIds){
		//将商品添加到所属的品类表中 并添加所属会员id以及所属品类Id
		//调用dao新增方法
		Map<String, Object> map = new HashMap<String, Object>();
		int num = goodsMapper.insertSelective(good);  
		
		//创建成功
		MtGoodMal mal = new MtGoodMal();
		if(num>0){
			
			if(!StringUtils.isBlank(imageIds)){
				if (imageIds.contains(",")) {
					List<String> imageIdList = java.util.Arrays.asList(imageIds.split(","));
					for (String imageId : imageIdList) {
						//调用商品的图片关系表，新增图片
					 	mal.setId(UUIDCreater.getUUID());
						mal.setGoodId(good.getGoodsId());
						mal.setCreatetime(DateUtil.getNowDate());
						mal.setMalId(imageId);
						malMapper.insertSelective(mal);
					}
					map.put("successMsg", "创建商品成功！");
				}else{
					//调用商品的图片关系表，新增图片
					
					map.put("successMsg", "创建商品成功！");
				}
			}
			/*
			else{
				map.put("errorMsg", "轮播图片不能为空，请上传！");
				throw new RuntimeException("轮播图片不能为空，请上传！");
			}
			*/
		}else{
			map.put("errorMsg", "创建商品失败！");
			throw new RuntimeException("创建商品失败！");
		}

		return map;
	}
	//编辑商品
	@Override
	public Map<String, Object> editGood(MtGoods good,String imageIds){
		//通过商品Id修改商品
		Map<String, Object> map = new HashMap<String, Object>();
		//调用dao修改方法
		int num = goodsMapper.updateGoods(good);	
		//根据结果返回ture或false 
		
		MtGoodMal mal = new MtGoodMal();
		if(num>0){
			//先删除关系
			Map<String, Object> pareams = new HashMap<String, Object>();
			pareams.put("goodId", good.getGoodsId());
			malMapper.delById(pareams);
			//通过商品Id，删除商品图片关系
			if(!StringUtils.isBlank(imageIds)){
				
				if (imageIds.contains(",")) {
					List<String> imageIdList = java.util.Arrays.asList(imageIds.split(","));
					for (String imageId : imageIdList) {
						//调用商品的图片关系表，新增图片
						mal.setId(UUIDCreater.getUUID());
						mal.setGoodId(good.getGoodsId());
						mal.setCreatetime(DateUtil.getNowDate());
						mal.setMalId(imageId);
						malMapper.insertSelective(mal);
					}
					map.put("successMsg", "修改商品成功！");
				}else{
					//调用商品的图片关系表，新增图片
					mal.setId(UUIDCreater.getUUID());
					mal.setGoodId(good.getGoodsId());
					mal.setCreatetime(DateUtil.getNowDate());
					mal.setMalId(imageIds);
					malMapper.insertSelective(mal);
					map.put("successMsg", "修改商品成功！");
				}
			}else{
				map.put("successMsg", "修改商品成功！");
			}
			
		}else{
			map.put("errorMsg", "修改商品失败！");
			throw new RuntimeException("修改商品失败！");
		}
		return map;
	}
	//通过商品Id，修改商品状态
	@Override
	public boolean updateByGoodId(Map<String, Object> params){
		boolean flag = true;
		int num = goodsMapper.updateByGoodId(params);
		//根据结果返回ture或false 
		if(num>0){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	//删除商品
	@Override
	public boolean editGood(String categoryId,String goodsId){
		//通过品类Id，品类Id，会员Id，品类Id删除商品
		//调用dao删除方法
		//根据品类Id  获取所述属性表 删除属性表中数据
		//根据结果返回ture或false 
		return true;
	}
	//增加商品数量
	@Override
	public boolean addGoodNUm (String GoodId,long num ){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", GoodId);
		params.put("number", num);
		// 新增商品的商品数量 根据结果返回ture或false
		int n = goodsMapper.updateNum(params);
		boolean flag = true;
		if(n>0){
			flag = true;
		}else{
			flag = false;
		}
		return true;
	}
	//减少商品数量
	@Override
	public boolean reduceGoodNUm (String GoodId,long num ){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", GoodId);
		params.put("number", -num);
		// 减少该商品的商品数量 根据结果返回ture或false
		int n = goodsMapper.updateNum(params);
		boolean flag = true;
		if(n>0){
			flag = true;
		}else{
			flag = false;
		}
		return true;
	}
	@Override
	public MtGoods searchGoodById(String goodsId) {
		// TODO Auto-generated method stub
		
		return goodsMapper.searchGoodById(goodsId);
	}
	@Override
	public MtGoods lookGoodById(String goodsId) {
		
	
		return goodsMapper.lookGoodById(goodsId);
		
	}
	


}
