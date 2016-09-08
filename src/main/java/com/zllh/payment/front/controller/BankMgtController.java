package com.zllh.payment.front.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.payment.front.service.BankMgtService;
import com.zllh.payment.model.Bank;
import com.zllh.payment.model.BankAcctRule;
import com.zllh.payment.model.BankServer;
import com.zllh.payment.model.InterfaceMgt;

@Controller
@RequestMapping("/bankMgtController")
public class BankMgtController extends BaseController {

	@Autowired
	private BankMgtService bankMgtService;

	// 初始化银行账号画面
	@RequestMapping("/getBankTree")
	@ResponseBody
	public List<Bank> getBankTree(HttpServletRequest request, String type) {
		List<Bank> bankTree = null;
		if (type != null && type.equals("enable")) {
			bankTree = bankMgtService.getBankTree(type);
		} else {
			bankTree = bankMgtService.getBankTree(null);
		}
		return bankTree;
	}

	// 查询单一银行所有信息
	@RequestMapping("/pay_querybank")
	public @ResponseBody
	Map<String, Object> QueryBankInformation(HttpServletRequest request) {
		String id = request.getParameter("id");
		BankAcctRule BankAcctRule = new BankAcctRule();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		BankAcctRule.setPage((Integer.parseInt(page) - 1 )* Integer.parseInt(rows));
		BankAcctRule.setRows(Integer.parseInt(rows));
		BankAcctRule.setBankId(id);
		List<BankAcctRule> list = bankMgtService.QueryBankInformationrule(BankAcctRule);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", bankMgtService.selectByBankidCount(BankAcctRule));// total键 存放总记录数，必须的
		return map;
	}

	// 查询父规则信息
	@RequestMapping("/getFatherRule")
	public @ResponseBody
	List<BankAcctRule> getFatherRule(HttpServletRequest request) {
		String fatherRuleId = request.getParameter("bankRuleId");
		int acctType = Integer.parseInt(request.getParameter("acctType"));
		List<BankAcctRule> list = bankMgtService.getFatherRule(fatherRuleId,acctType);
		return list;
	}

	// 查询银行id最大值
	@RequestMapping("/gettopnum")
	public @ResponseBody
	String GetTopNum(HttpServletRequest request) {
		String id = request.getParameter("id");
		String topid = bankMgtService.GetTopNum(id);
		return topid;
	}

	// 新增银行
	@RequestMapping("/addbank")
	public @ResponseBody
	String addBank(HttpServletRequest request) {
	
		String partent = request.getParameter("parentid");
		String text = request.getParameter("text");
		
		String result = "";
		//查询有没有同名的银行
		List<Bank> banks = bankMgtService.getBankByName(text);
		if(banks.size() == 0)
		{
			Bank bank = new Bank();
			String max = bankMgtService.GetTopNum(partent);
			if(max==null){
				bank.setId(partent+"1111");
			}else if(max.equals("0")){
				bank.setId("1111");
			} else{
				bank.setId((new BigDecimal(max).add(new BigDecimal("1"))).toString());
			}		
			bank.setText(text);
			bank.setCreateTime(new Date());
			bank.setLockFlag(0);
			bank.setStatus(0);
			bank.setAddressId("1");
			bank.setParentBankId(partent);
			bank.setIconCls("icon-pay-zxbank"); 
			
			if (bankMgtService.addBank(bank) == 1) {
				result = "增加银行成功！";
			} else {
				result = "增加银行失败！";
			}
		} else {
			result = "同名银行存在，请重新添加！";
		}
		return result;
	}

	// 更新银行
	@RequestMapping("/updatebank")
	public @ResponseBody
	String updatebank(HttpServletRequest request) {
		String bankId = request.getParameter("id");
		String partent = request.getParameter("parentid");
		String text = request.getParameter("text");
		Bank bank = new Bank();
		bank.setId(bankId);
		bank.setText(text);
		bank.setParentBankId(partent);
		int i = bankMgtService.updateBank(bank);
		String result = "";
		if (i == 1) {
			result = "更新银行成功！";
		} else {
			result = "更新银行失败！";
		}
		return result;
	}

	// 启用银行
	@RequestMapping("/pay_openbank")
	public @ResponseBody
	String openbank(HttpServletRequest request) {
		String bankId = request.getParameter("id");
		Bank bank = new Bank();
		bank.setIconCls("icon-pay-zxbank");
		bank.setId(bankId);
		bank.setStatus(0);
		int i = bankMgtService.updateBank(bank);
		String result = "";
		if (i == 1) {
			result = "启用银行成功！";
		} else {
			result = "启用银行失败！";
		}
		return result;
	}

	// 停用银行
	@RequestMapping("/pay_closebank")
	public @ResponseBody
	String closebank(HttpServletRequest request) {
		String bankId = request.getParameter("id");
		Bank bank = new Bank();
		bank.setIconCls("icon-pay-zxbankstop");
		bank.setId(bankId);
		bank.setStatus(1);
		int i = bankMgtService.updateBank(bank);
		String result = "";
		if (i == 1) {
			result = "停用银行成功！";
		} else {
			result = "停用银行失败！";
		}
		return result;
	}

	// 停用服务器相关信息
	@RequestMapping("/pay_closeserver")
	public @ResponseBody
	String closeserver(HttpServletRequest request) {
		String id = request.getParameter("id");
		BankServer BankServer = new BankServer();
		BankServer.setServerId(id);
		BankServer.setStatus("1");
		int i = bankMgtService.updateserverfw(BankServer);
		String result = "";
		if (i == 1) {
			result = "停用服务器成功！";
		} else {
			result = "停用服务器失败！";
		}
		return result;
	}

	// 停用接口相关信息
	@RequestMapping("/pay_closeinterface")
	public @ResponseBody
	String closeinterface(HttpServletRequest request) {
		String id = request.getParameter("id");
		InterfaceMgt InterfaceMgt = new InterfaceMgt();
		InterfaceMgt.setInterfaceId(id);
		InterfaceMgt.setStatus("1");
		int i = bankMgtService.updateinterfacefw(InterfaceMgt);
		String result = "";
		if (i == 1) {
			result = "停用接口成功！";
		} else {
			result = "停用接口失败！";
		}
		return result;
	}

	// 停用规则相关信息
	@RequestMapping("/pay_closerule")
	public @ResponseBody
	String closerule(HttpServletRequest request) {
		String id = request.getParameter("id");
		BankAcctRule BankAcctRule = new BankAcctRule();
		BankAcctRule.setBankRuleId(id);
		BankAcctRule.setStatus("1");
		int i = bankMgtService.updateRule(BankAcctRule);
		String result = "";
		if (i == 1) {
			result = "停用银行规则成功！";
		} else {
			result = "停用银行规则失败！";
		}
		return result;
	}

	// 查询单一银行的服务器信息
	@RequestMapping("/pay_querybankserver")
	public @ResponseBody
	Map<String, Object> selectserver(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String,Object>();
		//第几页
		int page = Integer.parseInt(request.getParameter("page"));
		//每页显示几行
		int rows = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> mapPara = new HashMap<String, Object>();
		mapPara.put("bankId", id);
		mapPara.put("pageIndex", (page-1)*rows);
		mapPara.put("pageSize", rows);
		map = bankMgtService.QueryBankInformationserver(mapPara);
		List<BankServer> list = (List<BankServer>) map.get("listServerKey");
		int count = (int) map.get("count");
		Map<String, Object> mapTtn = new HashMap<String, Object>();
		mapTtn.put("rows", list);
		mapTtn.put("total", count);
		return mapTtn;
	}

	// 查询单一银行的接口信息
	@RequestMapping("/pay_querybankinterface")
	public @ResponseBody
	Map<String, Object> selectinterface(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String,Object>();
		//第几页
		int page = Integer.parseInt(request.getParameter("page"));
		//每页显示几行
		int rows = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> mapPara = new HashMap<String, Object>();
		mapPara.put("bankId", id);
		mapPara.put("pageIndex", (page-1)*rows);
		mapPara.put("pageSize", rows);
		map = bankMgtService.QueryBankInformationinterface(mapPara);
		List<InterfaceMgt> list = (List<InterfaceMgt>) map.get("listInterfaceKey");
		int count = (int) map.get("count");
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		mapRtn.put("rows", list);
		mapRtn.put("total", count);
		return mapRtn;
	}

	// 更新服务器相关信息
	@RequestMapping("/pay_updateserver")
	public @ResponseBody
	String updateserver(HttpServletRequest request) {
		String bankid = request.getParameter("updatebankId");
		String serveraddress = request.getParameter("serveraddress");
		String serverId = request.getParameter("servername");
		String servertype = request.getParameter("servertype");
		String serverstatus = request.getParameter("serverstatus");
		String serverName = request.getParameter("serverNamebig");
		BankServer BankServer = new BankServer();
		if (servertype.endsWith("银企直联")) {
			servertype = "0";
		} else if (servertype.endsWith("网银")) {
			servertype = "1";
		}
		if (serverstatus.endsWith("启用")) {
			serverstatus = "0";
		} else if (serverstatus.endsWith("停用")) {
			serverstatus = "1";
		}
		BankServer.setServerId(serverId);
		BankServer.setBankId(bankid);
		BankServer.setServerAddress(serveraddress);
		BankServer.setStatus(serverstatus);
		BankServer.setServerType(servertype);
		BankServer.setServerName(serverName);
		int i = bankMgtService.updateserverfw(BankServer);
		String result = "";
		if (i == 1) {
			result = "更新服务器成功！";
		} else {
			result = "更新服务器失败！";
		}
		return result;
	}

	// 增加接口相关信息
	@RequestMapping("/pay_addinterface")
	public @ResponseBody
	String addinterface(HttpServletRequest request) {
		String interfacename = request.getParameter("interfacename");
		String interfacetype = request.getParameter("interfacetype");
		String interfaceaddress = request.getParameter("interfaceaddress");
		String interfacestatus = request.getParameter("interfacestatus");
		String updateInterfaceBankId = request.getParameter("updateInterfaceBankId");
		InterfaceMgt InterfaceMgt = new InterfaceMgt();
		if (interfacetype.endsWith("银企直联")) {
			interfacetype = "0";
		} else if (interfacetype.endsWith("网银")) {
			interfacetype = "1";
		}
		if (interfacestatus.endsWith("启用")) {
			interfacestatus = "0";
		} else if (interfacestatus.endsWith("停用")) {
			interfacestatus = "1";
		}
		InterfaceMgt.setInterfaceClass(interfaceaddress);
		InterfaceMgt.setInterfaceId(interfacename);
		InterfaceMgt.setLockFlag(Byte.parseByte("1"));
		InterfaceMgt.setServerType(interfacetype);
		InterfaceMgt.setStatus(interfacestatus);
		InterfaceMgt.setBankId(updateInterfaceBankId);
		int i = bankMgtService.addInterface(InterfaceMgt);
		String result = "";
		if (i == 1) {
			result = "增加接口成功！";
		} else {
			result = "增加接口失败！";
		}
		return result;
	}
	
	@RequestMapping("/is_server_named")
	public @ResponseBody boolean isServerNamed(String previousName, String serverNamebig){
		if (StringUtils.isBlank(serverNamebig)){
			return false;
		}
		if (!StringUtils.isBlank(previousName) && previousName.equals(serverNamebig)){
			return false;
		}
		return bankMgtService.isServerNamed(serverNamebig);
	}
	
	@RequestMapping("/is_server_exist")
	public @ResponseBody boolean isServerExist(String serverType, String serverAddress, String previousServerAddress, String previousServerType){
		if (StringUtils.isBlank(serverType) || StringUtils.isBlank(serverAddress)){
			return false;
		}
		if (!StringUtils.isBlank(previousServerType) && !StringUtils.isBlank(previousServerAddress) 
				&& previousServerType.equals(serverType) && previousServerAddress.equals(serverAddress)){
			return false;
		}
		if (serverType.endsWith("银企直联")) {
			serverType = "0";
		} else if (serverType.endsWith("网银")) {
			serverType = "1";
		}
		
		return bankMgtService.isServerExist(serverType, serverAddress);
	}

	// 增加服务器相关信息
	@RequestMapping("/pay_saveserver")
	public @ResponseBody
	String addserver(HttpServletRequest request) {
		String bankId = request.getParameter("updateBankId");
		String serverAddress = request.getParameter("serverAddress");
		String serverType = request.getParameter("serverType");
		String serverStatus = request.getParameter("serverStatus");
		//String serverLock = request.getParameter("serverlock");
		// String serverId = request.getParameter("servername");
		String serverName = request.getParameter("serverNamebig");
		BankServer BankServer = new BankServer();
		if (serverType.endsWith("银企直联")) {
			serverType = "0";
		} else if (serverType.endsWith("网银")) {
			serverType = "1";
		}
		if (serverStatus.endsWith("启用")) {
			serverStatus = "0";
		} else if (serverStatus.endsWith("停用")) {
			serverStatus = "1";
		}
		BankServer.setServerName(serverName);
		BankServer.setBankId(bankId);
		BankServer.setServerAddress(serverAddress);
		BankServer.setLockFlag(Byte.parseByte("1"));
		BankServer.setStatus(serverStatus);
		BankServer.setServerType(serverType);
		int i = bankMgtService.addServer(BankServer);
		String result = "";
		if (i == 1) {
			result = "增加服务器成功！";
		} else {
			result = "增加服务器失败！";
		}
		return result;
	}

	// 增加银行规则相关信息
	@RequestMapping("/pay_saverule")
	public @ResponseBody
	String addRule(HttpServletRequest request,BankAcctRule bankAcctRule) {
		
		String result = "";
		if (bankMgtService.addRule(bankAcctRule) == 1) {
			result = "增加银行规则成功！";
		} else {
			result = "增加银行规则失败！";
		}
		return result;
	}

	// 更新银行规则信息
	@RequestMapping("/pay_updaterule")
	public @ResponseBody
	String updaterule(HttpServletRequest request,BankAcctRule bankAcctRule) {
		
		String result = "";
		if (bankMgtService.updateRule(bankAcctRule) == 1) {
			result = "更新银行规则成功！";
		} else {
			result = "更新银行规则失败！";
		}
		return result;
	}

	// 删除服务器相关信息
	@RequestMapping("/pay_deleteserver")
	public @ResponseBody
	String deleteserver(HttpServletRequest request) {
		String id = request.getParameter("id");
		int i = bankMgtService.deleteserverfw(id);
		String result = "";
		if (i == 1) {
			result = "删除服务器成功！";
		} else {
			result = "删除服务器失败！";
		}
		return result;
	}

	// 更新接口相关信息
	@RequestMapping("/pay_updateinterface")
	public @ResponseBody
	String updateinterface(HttpServletRequest request,InterfaceMgt interfaceMgt) {

		String result = "";
		if (bankMgtService.updateinterfacefw(interfaceMgt) == 1) {
			result = "更新接口成功！";
		} else {
			result = "更新接口失败！";
		}
		return result;
	}

	// 删除接口相关信息
	@RequestMapping("/pay_deleteinterface")
	public @ResponseBody
	String deleteinterface(HttpServletRequest request) {
		String id = request.getParameter("id");
		int i = bankMgtService.deleteinterfacewy(id);
		String result = "";
		if (i == 1) {
			result = "删除接口成功！";
		} else {
			result = "删除接口失败！";
		}
		return result;
	}

	// 删除银行规则相关信息
	@RequestMapping("/pay_deleterule")
	public @ResponseBody
	String deleterule(HttpServletRequest request) {
		String id = request.getParameter("id");
		int i = bankMgtService.deleteRule(id);
		String result = "";
		if (i == 1) {
			result = "删除银行规则成功！";
		} else {
			result = "删除银行规则失败！";
		}
		return result;
	}

	@RequestMapping("/toBank")
	public String toBank(HttpServletRequest request) {
		return "complex";
	}
}
