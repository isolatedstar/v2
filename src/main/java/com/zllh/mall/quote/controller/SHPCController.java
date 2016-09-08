package com.zllh.mall.quote.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.common.model.MtQuote;
import com.zllh.mall.quote.service.QuoteService;
import com.zllh.mall.quote.service.QuoteServiceRedis;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.Page;





/** 
 * @ClassName: SCController 
 * @Description: 拣单车管理
 * @author zfy
 * 
 */
@Controller
@RequestMapping("/shpc")
public class SHPCController extends BaseController  {
	 
	@Autowired
	private QuoteServiceRedis quoteRedis;
	@Autowired
	private QuoteService quoteService;
	/*
	 * 跳转到采购捡单车页面
	 */
	@RequestMapping("/toshowShoc0")
	public String toshowShoc0(){
		logger.info("跳转采购页面");
		return "mall/shpc/shpc_0";
	}
	/*
	 * 跳转到销售捡单车页面
	 */
	@RequestMapping("/toshowShoc1")
	public String toshowShoc1(){
		logger.info("跳转销售页面");
		return "mall/shpc/shpc_1";
	}
	/*
	 * 获取登录员的采购信息
	 */
	@RequestMapping("/serachSHPC")
	@ResponseBody
	public Map<String, List<MtQuote>> serachQuoteToSHPC(
			Model model,
			@RequestParam(value = "type", required = false) Integer type) {
		// 获取登陆人的信息 登陆人Id作为Key
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		//添加数据
//		MtQuote quote = quoteService.searchQuoteById("1");
//		quoteRedis.addQuoteIn(user.getId()+quote.getType(), quote);
		//查找出指定的任务
//		MtQuote ss = quoteRedis.getQuoteByIndex("10", 0);
//		System.out.println(ss.getGoodsName()+ss.getId());
		//quoteRedis.removeQuoteByKey("11", quote);
		// 获取查询对象
		Map<String, List<MtQuote>> map  = quoteRedis.getListByNameByUser(user.getId()+type);
		//System.out.println(map.get(quote.getMmbId()+","+quote.getMmbName()).size());
		System.out.println(map.toString());
		return map;
	}
	/**
	 * 
	 * @Title: addQuoteToSHPC 
	 * @Description:添加报价到拣单车中
	 * @param @param o
	 *   
	 * @return String    
	 * @throws 
	 */
	@RequestMapping("/addQuoteToSHPC")
	@ResponseBody
	public void addQuoteToSHPC(Model model,
			@RequestParam(value = "num", required = false, defaultValue = "") Double num,
			@RequestParam(value = "quoteId", required = false, defaultValue = "") String quoteId){
		// 获取登陆人的信息 登陆人Id作为Key
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		//通过quoteId查出报价对象
		MtQuote quote = quoteService.searchQuoteById(quoteId);
		//设置报价的商品数量
//		if(num==null||num==0){
//			num = 1.00; 
//		}
//		quote.setNum(num);
		//查询所有的redis该用户所有的redis
		boolean flag = true;
		String json = "";
		List<MtQuote> all = quoteRedis.getListByName(user.getId()+quote.getType());
		for (MtQuote mq : all) {
			if(mq.getId().equals(quote.getId())){
				flag = false;
				json = "2";//该报价已经存在
				break;
			}
			//匹配报价  采购报价不匹配商品 销售报价匹配商品
			if(quote.getType()==1){
				//System.out.println(mq.getGoodsId()+"======"+quote.getGoodsId());
				if(mq.getGoodsId().equals(quote.getGoodsId())){
					flag = false;
					json = "3";//该商品已经存在
					break;
				}
			}
			
		}
		//添加到redis
		if(flag){
			flag = quoteRedis.addQuoteIn(user.getId()+quote.getType(), quote);
			if(flag){
				json = "0";
			}else{
				json = "1";
			}
		}
		outJson(json);
	}
	
	/**
	 * 
	 * @Title: deleteQuoteSHPC 
	 * @Description:删除报价到拣单车中
	 * @param @param 
	 * @param @return    
	 *     
	 * @throws 
	 * @author zfy
	 * 
	 */
	@RequestMapping("/deleteQuoteSHPC")
	@ResponseBody
	public void deleteQuoteSHPC(Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "index", required = false, defaultValue = "0") long index){
		// 获取登陆人的信息 登陆人Id作为Key
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		//通过选中的序列号查找出选中的购物车报价 
		MtQuote quote = quoteRedis.getQuoteByIndex(user.getId()+type, index);
		//移除
		if(quote!=null){
			quoteRedis.removeQuoteByKey(user.getId()+type, quote);
			outJson("0");
		}else{
			outJson("1");
		}
	}
	//编辑指定任务
	/**
	 * 
	 * @param model
	 * @param stringsId  goodId+name
	 * @param type 类型
	 * @param index 位置
	 */
	@RequestMapping("/updateQuoteSHPC")
	public void updateQuoteSHPC(Model model,
			@RequestParam(value = "stringsId", required = false) String stringsId,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "index", required = false, defaultValue = "0") long index){
		// 获取登陆人的信息 登陆人Id作为Key
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		//通过选中的序列号查找出选中的购物车报价 
		MtQuote quote = quoteRedis.getQuoteByIndex(user.getId()+type, index);
		//编辑
		if(quote!=null){
			List<String> nn = java.util.Arrays.asList(stringsId.split(","));
			quote.setMateId(nn.get(0));
			quote.setGoodsName(nn.get(1));
			quoteRedis.updateQuoteByKey(user.getId()+type, index, quote);
			outJson("0");
		}else{
			outJson("1");
		}
	}
	/**
	 * 
	 * @Title: deleteQuoteSHPC 
	 * @Description:删除报价到拣单车中
	 * @param @param 
	 * @param @return    
	 *     
	 * @throws 
	 * @author zfy
	 * 
	 */
	@RequestMapping("/deleteMoreSHPC")
	@ResponseBody
	public void deleteMoreSHPC(Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "index", required = false, defaultValue = "") String index){
		// 获取登陆人的信息 登陆人Id作为Key
		UserExtendBean user1 = Utils.securityUtil.getUser();
		MtMuser user = user1.getMuser();
		//通过选中的序列号查找出选中的购物车报价 
		  List<String> list = java.util.Arrays.asList(index.split(","));
		  for (String aa : list) {
			  MtQuote quote = quoteRedis.getQuoteByIndex(user.getId()+type, Long.parseLong(aa));
			//移除
			if(quote!=null){
				quoteRedis.removeQuoteByKey(user.getId()+type, quote);
				
			}
		}
		  outJson("0");
		
	}
}
