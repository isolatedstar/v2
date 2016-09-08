
package com.zllh.factoring.accreditation.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zllh.base.controller.BaseController;
import com.zllh.factoring.accreditation.service.AccreditationMgService;
import com.zllh.factoring.assure.service.AssureService;
import com.zllh.factoring.common.dao.FacFinancingMapper;
import com.zllh.factoring.common.model.FacFinancing;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;
import com.zllh.factoring.financing.service.FinancingService;

/**
 * @ClassName: AccreditationMgController
 * @Description: 融资保理认可管理Controller
 * @author JW
 * @date 2015-12-18 下午2:58:05
 */
@Controller
@RequestMapping("/accreditationMgController")
public class AccreditationMgController extends BaseController{

	@Autowired
	private AccreditationMgService accreditationMgService;
	
	@Autowired
	private FinancingService financingService;
	
	@Autowired
	private FacFinancingMapper financingMapper;
	
	@Autowired
	private AssureService assureService;
	
//	private List<FacFinancing> Filist;
	
//	private List<String> ids_List = new ArrayList<String>();
	
	private Map<String,Object> map=new HashMap<String,Object>();
	
	
	private String jsonData;
	
	@RequestMapping("/acceptNofilter")
	public String acceptNofilter(String proposerName,String financingId) throws UnsupportedEncodingException{
		/**  获取页面参数   +  判断 **/
		String page = request.getParameter("page");
		String assureType=request.getParameter("assureType");
		if("0".equals(assureType)){
			assureType=null;
		}
		String status = request.getParameter("status");
		if(status == null){
			status = "1";
		}
		if("0".equals(status)){
			status=null;
		}
		String proposerDate = request.getParameter("proposerDate");
		if(proposerDate==""){
			proposerDate = null;
		}
		if(proposerName!=null){
			if(proposerName!=""){
				if(!proposerName.equals("undefined")){
					proposerName = new String(request.getParameter("proposerName").getBytes("iso-8859-1"), "UTF-8");
					map.put("proposerName", proposerName);
				}
			}
		}else{
			map.put("proposerName", null);
		}
		String acceptOperatorName = request.getParameter("acceptOperatorName");	
		if(acceptOperatorName==""){
			acceptOperatorName = null;
		}
		String acceptDate = request.getParameter("acceptDate");	
		if(acceptDate==""){
			acceptDate = null;
		}
		
		if(financingId == ""){
			financingId = null;
		}
		map.put("financingId", financingId);
		map.put("assureType", assureType);
		map.put("status", status);
		map.put("proposerDate", proposerDate);
		map.put("acceptOperatorName", acceptOperatorName);
		map.put("acceptDate", acceptDate);
		if(page==null){
			page = "1";
		}
		map.put("page", Integer.parseInt(page));
		
		List<FacFinancing> fins =  financingService.getAllFinancing(map);
		int length = financingService.getAllLength(map);
		if(length%10>0){
			length = length/10+1;
		}else{
			length = length/10;
		}
		request.setAttribute("fins", fins);
		request.setAttribute("length", length);
		request.setAttribute("clas", Integer.parseInt(page));
		request.setAttribute("status", status);
		request.setAttribute("assureType", assureType);
		request.setAttribute("financingId", financingId);
		request.setAttribute("proposerDate", proposerDate);
		request.setAttribute("acceptOperatorName", acceptOperatorName);
		request.setAttribute("acceptDate", acceptDate);
		
		return "factoring/accreditation/accreditation_index";
	}

	/**
	 * @Title: selectAccept 
	 * @author JW
	 * @Description: 认可查询
	 * @return String
	 * @throws
	 */
	@RequestMapping("/selectAccept")
	public String selectAccept(){
		/*String user = request.getParameter("user");
		HashMap<String, Object> param = new HashMap<String, Object>();
		List<FacFinancing> fins =  accreditationMgService.selectAccept(param);
		request.setAttribute("fin", fins);*/
		return "";
	}
	
	/**
	 * 点击认可单号，查询担保信息
	 */
	@RequestMapping("/Accept")
	public String orderInformationNofilter(){
		
		
		return "factoring/accreditation/accreditation_order_information";
	}
	
	
	/**
	 * @Title: findFinancingDetailById
	 * @author JW
	 * @Description: 根据融资单号查询订单行和担保信息明细
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping("findFinancingDetailById/{id}")
	public String findFinancingDetailById(@PathVariable("id") String id){
		
		FacFinancingExtendBean finGua =  accreditationMgService.findFinancingDetailById(id);
		request.setAttribute("orderDetails", finGua);
		
		return "***";
	}
	
    /**
     * @Title: Accept
     * @author wxx
     * @Description: 认可
     * @return String
     * @throws
     */
	
	@RequestMapping("/executeAccept")
	public @ResponseBody String executeAccept(){
		String[] ids = request.getParameter("ids").split(",");
		String lockFinjJson = request.getParameter("lockFinjJson");
		
//		String result = accreditationMgService.executeAccept(ids,lockFinjJson);//发送报文
//		String result = accreditationMgService.executeAccept_update(ids);//修改数据
		
		String result = accreditationMgService.executeAccept2(ids,lockFinjJson);//发送报文
		
		//返回页面提示
		jsonData = JSON.toJSONString(result);
		return jsonData;
	}
	
	/**
	 * @Title: executeVeto
	 * @author JW
	 * @Description: 否决
	 * @return String
	 * @throws
	 */
	@RequestMapping("/executeVeto")
	public @ResponseBody String executeVeto(){
		/**   前台勾选的融资单Id   **/
		String str_id =  request.getParameter("ids");
		List<String> id_list = Arrays.asList(str_id.split(",")) ;
		
		String[] ids  = (String[]) id_list.toArray(new String[id_list.size()]);
//		//前台的融资单乐观锁值
		String lockFinjJson = request.getParameter("lockFinjJson");
	    String result = accreditationMgService.executeVeto(ids, lockFinjJson);
	    
	    jsonData = JSON.toJSONString(result);
		return jsonData;
	}
	
	public void factoringCallback(){
//		String json = request.getParameter("JSON");
//		//判断是保理自己的回调还是商城过来的担保还款
//		refundMgService.factoringCallback(JSONObject.fromObject(json));
		
	}
	
	@RequestMapping("/updateAccptStutas")
	@ResponseBody
	public String updateAccptStutas(){
		String id = request.getParameter("id");
		String[] ids = {id};
		accreditationMgService.executeAccept_update(ids);//修改数据
		return "5";
	}
}
