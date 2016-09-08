
package com.zllh.factoring.writeoffacc.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.fac_enum.FinancingGuaranteeEnum;
import com.zllh.factoring.common.model.FacFinancingGuarantee;
import com.zllh.factoring.writeoffacc.service.WriteOffAccService;

/**
 * @ClassName: WriteOffAccController
 * @Description: 销账管理
 * @author JW
 * @date 2016年6月16日 上午8:27:03
 */
@Controller
@RequestMapping("/writeOffAccController")
public class WriteOffAccController extends BaseController{

	@Autowired
	private WriteOffAccService writeOffAccService;
	
	/**
	 * @Title: toWriteOffAcc 
	 * @author JW
	 * @Description: 跳转销账
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/toWriteOffAcc", method=RequestMethod.GET)
	public String toWriteOffAcc(){
		HashMap<String, Object> param = new HashMap<String, Object>();
	    param.put("financingId", "");
        param.put("guaranteeId", "");
		Page<FacFinancingGuarantee> page = writeOffAccService.selectOutInterest(param, 1);
		request.setAttribute("finGuas", page.getResult());
		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", 1);
		return "writeoffacc/writeoffacc";
	}
	
	/**
	 * @Title: toWriteOffAccSelect 
	 * @Description: 销账记录跳转
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/toWriteOffAccSelect", method=RequestMethod.GET)
	public String toWriteOffAccSelect(){
	    HashMap<String, Object> param = new HashMap<String, Object>();
	    param.put("financingId", "");
	    param.put("guaranteeId", "");
	    param.put("writeoffacc", FinancingGuaranteeEnum.WRITEOFFACC.getValue());
	    Page<FacFinancingGuarantee> page = writeOffAccService.selectOutInterestHistry(param, 1);
	    request.setAttribute("finGuas", page.getResult());
	    request.setAttribute("totalPage", page.getTotalPage());
	    request.setAttribute("nowPage", 1);
	    return "writeoffacc/writeoffaccselect";
	}
	
	/**
	 * @Title: selectOutInterest 
	 * @Description: 销账查询
	 * @param @param financingId
	 * @param @param guaranteeId
	 * @param @param status
	 * @param @param nowPage
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="selectOutInterest", method=RequestMethod.POST)
	public String selectOutInterest(
			@RequestParam(value ="financingId",required = false, defaultValue = "") String financingId,
			@RequestParam(value ="guaranteeId",required = false, defaultValue = "") String guaranteeId,
			@RequestParam(value ="nowPage",required = false, defaultValue = "1") Integer nowPage){
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("financingId", financingId);
		param.put("guaranteeId", guaranteeId);
		Page<FacFinancingGuarantee> page = writeOffAccService.selectOutInterest(param, nowPage);
		request.setAttribute("finGuas", page.getResult());
		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", nowPage);
		return "writeoffacc/writeoffacc"; 
	}
	
	/**
	 * @Title: selectOutInterestHistry 
	 * @Description: 销账记录查询
	 * @param @param financingId
	 * @param @param guaranteeId
	 * @param @param status
	 * @param @param nowPage
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="selectOutInterestHistry", method=RequestMethod.POST)
	public String selectOutInterestHistry(
	        @RequestParam(value ="financingId",required = false, defaultValue = "") String financingId,
	        @RequestParam(value ="guaranteeId",required = false, defaultValue = "") String guaranteeId,
	        @RequestParam(value ="nowPage",required = false, defaultValue = "1") Integer nowPage){
	    HashMap<String, Object> param = new HashMap<String, Object>();
	    param.put("financingId", financingId);
	    param.put("guaranteeId", guaranteeId);
	    param.put("writeoffacc", FinancingGuaranteeEnum.WRITEOFFACC.getValue());
	    Page<FacFinancingGuarantee> page = writeOffAccService.selectOutInterestHistry(param, nowPage);
	    request.setAttribute("finGuas", page.getResult());
	    request.setAttribute("totalPage", page.getTotalPage());
	    request.setAttribute("nowPage", nowPage);
	    return "writeoffacc/writeoffaccselect"; 
	}
	
	/**
	 * @Title: executeWriteOff 
	 * @author JW
	 * @Description: 销账
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/executeWriteOffAcc",method=RequestMethod.POST)
	@ResponseBody
	public String executeWriteOffAcc(@RequestParam(value ="ids",required = false, defaultValue = "") String ids){
		if("".equals(ids)) return "请选择销账的单子！";
		boolean bool = true;
		try {
			writeOffAccService.executeWriteOffAcc(ids); 
		} catch (Exception e) {
			bool = false;
			throw new RuntimeException(e.getMessage());
		}
		if(bool){
			return "操作成功！";
		}else{
			return "操作失败！";
		}
	}
}
