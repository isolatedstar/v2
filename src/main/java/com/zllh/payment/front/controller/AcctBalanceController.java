package com.zllh.payment.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.payment.front.service.AcctBalanceService;
import com.zllh.payment.model.AccountBalance;
/**
 * @ClassName: accountBalanceController
 * @Description: 余额明细
 * @author wang-xueli
 * @date 2015年12月21日 
 */
@Controller
@RequestMapping("/acctBalanceController")
public class AcctBalanceController extends BaseController {

	@Autowired
	private AcctBalanceService acctBalanceService;

	//初始化余额明细画面
	@ResponseBody
	@RequestMapping("/show_balance_detail")
	 public Map<String, Object> showTransfer(HttpServletRequest request) {

		//查询余额明细
		List<AccountBalance> accountDetails = acctBalanceService.queryAccountBalanceByParams(request);
		//查询件数
		int balanceCount = acctBalanceService.queryAccountCount(request);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("rows", accountDetails);// rows键 存放每页记录 list
		jsonMap.put("total", balanceCount);// total键 存放总记录数，必须的
		//返回数据
		return jsonMap;
	}

}
