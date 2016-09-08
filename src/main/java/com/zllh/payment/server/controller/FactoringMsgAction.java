package com.zllh.payment.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.payment.front.service.AcctMgtService;
import com.zllh.payment.server.service.FactoringMsgService;

@Controller
@RequestMapping("/factoringMsgAction")
public class FactoringMsgAction {

	@Autowired
	public FactoringMsgService factoringMsgService;

	@Autowired
	public AcctMgtService acctMgtService;

	public String msgFlowNo;

	public String outBankId;

	public FactoringMsgAction() {
		// TODO Auto-generated constructor stub
	}

	// 入口，接收从保理发来的报文
	@RequestMapping("/factoringMsgReceive")
	@ResponseBody
	public void factoringMsgReceive(String msgXml) {
		System.out.println("########################" + acctMgtService);
		System.out.println("########################" + factoringMsgService);

	}

}
