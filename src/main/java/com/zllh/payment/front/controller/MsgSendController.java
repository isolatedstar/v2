package com.zllh.payment.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.payment.front.service.MsgSendService;
import com.zllh.payment.model.SendBankMsg;

@Controller
@RequestMapping("/msgSendController")
public class MsgSendController extends BaseController {

	@Autowired
	private MsgSendService msgSendService;

	// 查询报文
	@RequestMapping("/queryMsgSendByCon")
	public @ResponseBody
	Map<String, Object> queryMsgSendByCon(HttpServletRequest request, SendBankMsg sendBankMsg, int page, int rows) {

		List<SendBankMsg> list = msgSendService.getMessageByCon(sendBankMsg, page, rows);
		int acctCount = msgSendService.getMsgSendCount(sendBankMsg);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", acctCount);
		return map;
	}

}
