package com.zllh.factoring.messagecheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.model.FacMessage;
import com.zllh.factoring.messagecheck.service.MessageCheckService;

/**
 * @ClassName: MessageCheckController
 * @Description: 调用支付报文处理审核
 * @author JW
 * @date 2016-07-05 上午10:52:00
 */
@Controller
@RequestMapping("/messageCheckController")
public class MessageCheckController extends BaseController{

    @Autowired
    private MessageCheckService messageCheckService;
    
	/**
	 * @Title: toRepaymenNoFilter
	 * @author JW
	 * @Description: 跳转到调用支付报文处理审核界面
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/toMessageCheck",method=RequestMethod.GET)
	public String toMessageCheck(){
	    Page<FacMessage> page = messageCheckService.findMessageCheckList("", -1, 1);
        request.setAttribute("messList", page.getResult());
        request.setAttribute("totalPage", page.getTotalPage());
        request.setAttribute("nowPage", 1);
	    return "factoring/messagecheck/messagecheck";
	}
	
	/**
	 * @Title: findMessageCheckList 
	 * @Description: 查询报文列表
	 * @param @param createDate
	 * @param @param refundDate
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/findMessageCheckList",method=RequestMethod.POST)
	public String findMessageCheckList(
	        @RequestParam(value ="createDate",required = false, defaultValue = "") String createDate,
	        @RequestParam(value ="nowPage",required = false, defaultValue = "") Integer nowPage,
            @RequestParam(value ="messageSource",required = false, defaultValue = "-1") Integer messageSource){
	    
	    Page<FacMessage> page = messageCheckService.findMessageCheckList(createDate, messageSource, nowPage);
	    request.setAttribute("messList", page.getResult());
        request.setAttribute("totalPage", page.getTotalPage());
        request.setAttribute("nowPage", nowPage);
	    return "factoring/messagecheck/messagecheck";
	}
	
	@ResponseBody
	@RequestMapping(value="/findDetailMessage",method=RequestMethod.POST)
	public String findDetailMessage(
	        @RequestParam(value ="messId",required = false, defaultValue = "") String messId){
	    
	    FacMessage mess = messageCheckService.findDetailMessage(messId);
	    request.setAttribute("mess", mess);
	    return mess.getRefunddescription();
	}
	
	
	@ResponseBody
	@RequestMapping(value="/executeMessage",method=RequestMethod.POST)
	public String executeMessage(@RequestParam(value ="messIds",required = false, defaultValue = "") String messIds){
	    if("".equals(messIds))return "请选择要审核的数据！";
	    String res = messageCheckService.executeMessage(messIds);
	    return res;
	}
	
}
