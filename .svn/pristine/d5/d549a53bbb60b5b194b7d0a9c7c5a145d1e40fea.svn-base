package com.zllh.payment.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.front.service.FundTransferService;
import com.zllh.payment.model.AcctMgt;
import com.zllh.payment.model.FundTransferDetail;
/**
 * @ClassName: PayTransferController
 * @Description: 资金划转控制器
 * @author wang-xueli
 * @date 2015年12月21日 
 */
@Controller
@RequestMapping("/fundTransferController")
public class FundTransferController extends BaseController {


	@Autowired
	private FundTransferService fundTransferService;

	@Autowired
	private AcctMgtService acctMgtService;

	//初始化转账明细画面
	@ResponseBody
	@RequestMapping("/show_transfer_detail")
	 public Map<String, Object> showTransfer(HttpServletRequest request,int page, int rows) {

		//查询转账明细
		List<FundTransferDetail> transferDetails = fundTransferService.queryAllTransferByParams(request);
		//查询转账明细总条数
		int transferCount = fundTransferService.queryTransferCount(request);

		//返回数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("rows", transferDetails);// rows键 存放每页记录 list
		jsonMap.put("total", transferCount);// total键 存放总记录数，必须的
		return jsonMap;
	}

	//弹出窗口通过银行树和公司名称（模糊匹配）查询账号信息。
	@ResponseBody
	@RequestMapping("/show_filter_account")
	 public List<AcctMgt> queryAjaxAccountByParams(HttpServletRequest request){

		List<AcctMgt> accMgtList = acctMgtService.queryAccountListByParams(request);

		return accMgtList;
	}

}
