package com.zllh.payment.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.payment.front.service.ManulAjustService;
import com.zllh.payment.model.MsgBean;
import com.zllh.payment.model.TaskBean;
import com.zllh.payment.server.service.FactoringMsgService;
import com.zllh.payment.server.service.MallMsgService;
import com.zllh.payment.utils.Util;

/**
 * @ClassName: PayTransferController
 * @Description: 资金划转控制器
 * @author wang-xueli
 * @date 2015年12月21日 
 */
@Controller
@RequestMapping("/manulAjustController")
public class ManulAjustController extends BaseController {

	@Autowired
	private MallMsgService mallMsgService;
	@Autowired
	private FactoringMsgService factoringMsgService;
	@Autowired
	private ManulAjustService manulAjustService;

	
	@ResponseBody
	@RequestMapping("/add_task")
	public Map<String, Object> addTask(String data, String sender,HttpServletRequest request){
		//mallMsgService.addTaskInQ(data);测试队列
		//测试报文
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		boolean isHandle  = false;
		if ("1".equals(sender)){
			isHandle = factoringMsgService.handleFactoringMsg(data);
		}else{
			isHandle = mallMsgService.handleMallMsg(data);
		}
		if (isHandle){
			jsonMap.put("result", "success");
			jsonMap.put("content", "添加成功");
		}else{
			jsonMap.put("result", "error");
			jsonMap.put("content", "处理失败");
		}
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public Map<String, Object> findList(String flowID, String userName, String operator){
		//测试报文
		Map<String, Object> taskBeansMap = new HashMap<String, Object>();// 定义map
		
		List<TaskBean> taskBeans =  manulAjustService.findTaskBeanList();
		if (null != taskBeans && taskBeans.size() > 0 ){
			List<Map<String, Object>> rtnTaskBeanMaps = new ArrayList<Map<String, Object>>();
			for (TaskBean taskBean : taskBeans) {
				Map<String, Object> rtnTaskBeanMap = new HashMap<String, Object>();
				List<MsgBean> msgBeans = taskBean.getActMsgLst();
				String parentId = taskBean.getFlowId();
				rtnTaskBeanMap.put("id", parentId);
				rtnTaskBeanMap.put("name", parentId);
				rtnTaskBeanMap.put("nodeLevel",0);
				rtnTaskBeanMap.put("bankStatus", taskBean.getBankStatus() == null ?"": taskBean.getBankStatus());
				rtnTaskBeanMap.put("statusText", taskBean.getStatusText()== null ? "" : taskBean.getStatusText());
				rtnTaskBeanMaps.add(rtnTaskBeanMap);
				if (null != msgBeans && msgBeans.size() > 0){
					for (MsgBean msgBean : msgBeans) {
						Map<String, Object> rtnMsgBeanMap = new HashMap<String, Object>();
						rtnMsgBeanMap.put("id", msgBean.getMsgId());
						rtnMsgBeanMap.put("name", msgBean.getMsgId());
						rtnMsgBeanMap.put("code", Util.getContentByLabel(msgBean.getMsgContent(), "action"));
						rtnMsgBeanMap.put("accountNo", msgBean.getAccount());
						rtnMsgBeanMap.put("payAccNo", msgBean.getAccountOut());
						rtnMsgBeanMap.put("tranAmt", msgBean.getAmt());
						rtnMsgBeanMap.put("recvAccNo", msgBean.getAccountIn());
						rtnMsgBeanMap.put("userName", msgBean.getUserName());
						rtnMsgBeanMap.put("recvAccNm", msgBean.getUserName());
						rtnMsgBeanMap.put("bankStatus", msgBean.getBankStatus() == null ?"": msgBean.getBankStatus());
						rtnMsgBeanMap.put("statusText", msgBean.getStatusText()== null ? "" : msgBean.getStatusText());
						rtnMsgBeanMap.put("nodeLevel",1);
						rtnMsgBeanMap.put("_parentId", parentId);
						rtnTaskBeanMaps.add(rtnMsgBeanMap);
					}
				}
			}
			taskBeansMap.put("rows", rtnTaskBeanMaps);
			taskBeansMap.put("total", taskBeans.size());
		}
		return taskBeansMap;
	}

	@ResponseBody
	@RequestMapping("/success")
	public Map<String, Object> dealSuccess(String flowID, HttpServletRequest request){
		// 定义rtn map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		boolean isHandle  = false;
		isHandle = manulAjustService.handleTaskBean(flowID);
		if (isHandle){
			jsonMap.put("result", "success");
			jsonMap.put("content", "处理成功");
		}else{
			jsonMap.put("result", "error");
			jsonMap.put("content", "处理失败");
		}
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/into_queue")
	public Map<String, Object> intoQueue(String flowID, HttpServletRequest request){
		// 定义rtn map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		boolean isHandle  = false;
		isHandle = manulAjustService.addIntoTaskQueue(flowID);
		if (isHandle){
			jsonMap.put("result", "success");
			jsonMap.put("content", "处理成功");
		}else{
			jsonMap.put("result", "error");
			jsonMap.put("content", "处理失败");
		}
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/test")
	public void test( HttpServletRequest request){
		// 定义rtn map
		manulAjustService.updateTaskBankStatus("ad2c6cd62c5f48c1bea6", "1111111", "22222222");
		manulAjustService.updateTaskBankStatus("ad2c6cd62c5f48c1bea6", "3333333", "44444444");
		
	}
	
}
