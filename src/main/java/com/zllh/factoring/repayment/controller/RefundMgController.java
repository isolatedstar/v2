
package com.zllh.factoring.repayment.controller;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.base.mybatis.Page;
import com.zllh.factoring.common.fac_enum.FinancingEnum;
import com.zllh.factoring.common.fac_enum.IsOrNoEnum;
import com.zllh.factoring.common.fac_enum.RepaymentEnum;
import com.zllh.factoring.common.fac_enum.SourceEnum;
import com.zllh.factoring.common.model.FacRefund;
import com.zllh.factoring.common.model.model_extend.FacFinancingExtendBean;
import com.zllh.factoring.common.model.model_extend.FacFinancingGuaranteeExtendBean;
import com.zllh.factoring.common.model.model_extend.FacGuaranteeBillExtend;
import com.zllh.factoring.repayment.service.RefundMgService;


/**
 * @ClassName: RefundMgController
 * @Description: 还款管理
 * @author JW
 * @date 2015-12-21 上午10:52:00
 */
@Controller
@RequestMapping("/refundMgController")
public class RefundMgController extends BaseController{

	@Autowired
	private RefundMgService refundMgService;
	
	/**
	 * @Title: toRepaymenNoFilter
	 * @author JW
	 * @Description: 跳转到融资还款界面
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/toRepayment",method=RequestMethod.GET)
	public String toRepaymen(){
		request.setAttribute("isOrNoEnum", IsOrNoEnum.values());
		request.setAttribute("sourceEnum", SourceEnum.values());
		request.setAttribute("repaymentEnum", RepaymentEnum.values());

		Page<FacFinancingExtendBean> page = refundMgService.selectFinancingRepaymen("", "", Integer.toString(-1), FinancingEnum.ACCEPTED.getValue().toString(), 1);
		request.setAttribute("finList", page.getResult());
		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", 1);
		return "factoring/repayment/factoting_f";
	}
	
	/**
	 * @Title: toRepaymenDb 
	 * @Description: 跳转担保还款界面
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/toRepaymenDb",method=RequestMethod.GET)
    public String toRepaymenDb(){
        return "factoring/repayment/factoting_db";
    }
	
	/**
	 * @Title: toRepaymenZh 
	 * @Description: 跳转自还款
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/toRepaymenZh",method=RequestMethod.GET)
	public String toRepaymenZh(){
	    return "factoring/repayment/factoting_zh";
	}
	
	@ResponseBody
    @RequestMapping(value="/selectRepaymenZhList",method=RequestMethod.POST)
    public String selectRepaymenZhList(){
        
	    String financingId = request.getParameter("financingId");
        String refundCount = request.getParameter("refundCount");
        
        FacFinancingExtendBean fin = refundMgService.selectFinancingRepaymenByFinId(financingId);
        JSONObject obj = new JSONObject();
        obj.put("fin", fin);
        if(fin!=null){
            JSONObject lockGuaJson = new JSONObject();
            String lockFinJson = "{\""+financingId+"\": \""+fin.getLock()+"\"}";
            String repaymentJson = "{\""+financingId+"\": \""+refundCount+"\"}";
            String[] guaIds = fin.getGuaranteeId().split(",");
            String[] guaLock = fin.getGuaLock().split(",");
            for(int i=0;i<guaIds.length;i++){
                lockGuaJson.put(guaIds[i], guaLock[i]);
            }
            HashMap<String, Object> resMap = refundMgService.executeFinancingRefund(financingId, lockFinJson, lockGuaJson.toString(), repaymentJson);
            obj.put("restr", resMap.get("rel"));
            obj.put("financingId", financingId);
            obj.put("lockFinJson", lockFinJson);
            obj.put("lockGuaJson", lockGuaJson.toString());
            obj.put("repaymentJson", repaymentJson);
        }else{
            obj.put("err", "融资单不存在,请输入正确的融资单!");
        }
        return obj.toString();
    }
	
	/**
	 * @Title: batExecuteFinancingRefund 
	 * @Description: 融资自还款(财务版)
	 * @param @return
	 * @return String
	 * @throws
	 */
    @ResponseBody
    @RequestMapping(value="/executeFinancingRefund",method=RequestMethod.POST)
    public String executeFinancingRefund(){
        
        String financingId = request.getParameter("financingId");
        String lockFinJson = request.getParameter("lockFinJson");
        String lockGuaJson = request.getParameter("lockGuaJson");
        String repaymentJson = request.getParameter("repaymentJson");
        boolean bool = true;
        HashMap<String, Object> resMap = null;
        try{
            resMap = refundMgService.executeFinancingRefund(financingId,lockFinJson,lockGuaJson, repaymentJson);
        }catch(Exception e){
            bool = false;
        }
        
        try{
            if(bool) refundMgService.disposeFacRefundFinancialCallback(resMap, financingId, lockFinJson, lockGuaJson, repaymentJson, 1);
        }catch(Exception e){
            bool = false;
        }

        return bool?"操作成功！":"操作失败！";
    }
	
	/**
	 * @Title: selectFinancingRepaymen 
	 * @author JW
	 * @Description: 查询还款列表信息
	 * @param orderId
	 * @param guaranteetId
	 * @param refundDate
	 * @param isExpire
	 * @param refundSource
	 * @param status
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/selectFinancingRepaymen",method=RequestMethod.POST)
	public String selectFinancingRepaymen(
			@RequestParam(value ="financingId",required = false, defaultValue = "") String financingId,
			@RequestParam(value ="refundDate",required = false, defaultValue = "") String refundDate,
			@RequestParam(value ="refundSource",required = false, defaultValue = "") String refundSource,
			@RequestParam(value ="status",required = false, defaultValue = "") String status,
			@RequestParam(value ="nowPage",required = false, defaultValue = "1") Integer nowPage){
		
		Page<FacFinancingExtendBean> page = refundMgService.selectFinancingRepaymen(refundDate, financingId, refundSource, status, nowPage);
		request.setAttribute("finList", page.getResult());
		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", nowPage);
		return "factoring/repayment/factoting_f";
	}
	
	/**
	 * @Title: findRepaymentByFinancingId 
	 * @author JW
	 * @Description: 跳转到融资还款详细页查询
	 * @param financingId
	 * @return String
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findRepaymentByFinancingId",method=RequestMethod.POST)
	public Page<FacRefund> findRepaymentByFinancingId(
			@RequestParam(value ="financingId",required = false,defaultValue = "") String financingId,
			@RequestParam(value ="nowPage",required = false, defaultValue = "1") Integer nowPage){
		
		Page<FacRefund> page = refundMgService.findRepaymentByFinancingId(financingId, nowPage);
		List<FacRefund> refunds = page!=null?page.getResult():null;
		for(FacRefund refund : refunds){
			for(SourceEnum s : SourceEnum.values()){
				if(refund.getRefundSource().equals(String.valueOf(s.getValue()))) refund.setRefundSource(s.getText());
			}
		}
		page.setResult(refunds);
		page.setIndexCode(nowPage);
		return page;
	}
	
	/**
	 * @Title: executeFinancingRefund
	 * @author JW
	 * @Description: 融资还款
	 * @throws
	 */
//	@ResponseBody
//	@RequestMapping(value="/batExecuteFinancingRefund",method=RequestMethod.POST)
//	public String batExecuteFinancingRefund(){
//		
//		String financingIds = request.getParameter("financingIds");
//		String lockFinJson = request.getParameter("lockFinJson");
//		String lockGuaJson = request.getParameter("lockGuaJson");
//		String repaymentJson = request.getParameter("repaymentJson");
//		HashMap<String, Object> resMap = null;
//		if(financingIds!=null&&!"".equals(financingIds)){
//			resMap = refundMgService.executeFinancingRefund(financingIds,lockFinJson,lockGuaJson, repaymentJson);
//		}else{
//			return "请选择要还款的融资单!";
//		}
//		
//		StringBuffer sb = new StringBuffer();
//		if(resMap!=null&&resMap.size()>0){
//			for(Entry<String, Object> map : resMap.entrySet()){
//				sb.append(map.getKey()+":"+map.getValue());
//			}
//		}
//		return sb.toString();
//	}
	
	@ResponseBody
    @RequestMapping(value="/selectRepaymenDbList",method=RequestMethod.POST)
    public String selectRepaymenDbList(){
        
        String guaId = request.getParameter("guaId");
        String refundCount = request.getParameter("refundCount");
        
        List<FacGuaranteeBillExtend> finGuas = refundMgService.selectFinancingRepaymenByGuaId(guaId);
        JSONObject obj = new JSONObject();
        if(finGuas!=null&&finGuas.size()>0){
            FacGuaranteeBillExtend finGua = finGuas.get(0);
            JSONObject lockFinJson = new JSONObject();
            String lockGuaJson = "{\""+guaId+"\": \""+finGua.getLock()+"\"}";
            String repaymentJson = "{\""+guaId+"\": \""+refundCount+"\"}";
            List<FacFinancingGuaranteeExtendBean> fins = finGua.getFings();
            for(FacFinancingGuaranteeExtendBean fin : fins){
                FacFinancingExtendBean finx = fin.getFacFinancing();
                if(finx!=null) lockFinJson.put(finx.getFinancingId(), finx.getLock());
            }
            HashMap<String, Object> resMap = refundMgService.executeGuaranteeRefund(guaId, refundCount);
            obj.put("restr", resMap.get("rel"));
            obj.put("guaId", guaId);
            obj.put("lockFinJson", lockFinJson.toString());
            obj.put("lockGuaJson", lockGuaJson);
            obj.put("repaymentJson", repaymentJson);
        }else{
            obj.put("err", "担保单不存在,请输入正确的担保单!");
        }
        return obj.toString();
    }
	
	/**
	 * @Title: disposeGuaranteeRefund 
	 * @Description: 担保还款(财务版)
	 * @param @return
	 * @return String
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/disposeGuaranteeRefund",method=RequestMethod.POST)
	public String disposeGuaranteeRefund(){
	    String guaId = request.getParameter("guaId");
        String lockFinJson = request.getParameter("lockFinJson");
        String lockGuaJson = request.getParameter("lockGuaJson");
        String repaymentJson = request.getParameter("repaymentJson");
		boolean bool = true;
        HashMap<String, Object> resMap = null;
        try{
            JSONObject jsons = JSONObject.fromObject(repaymentJson);
            resMap =  refundMgService.executeGuaranteeRefund(guaId, jsons.get(guaId).toString());
        }catch(Exception e){
            bool = false;
        }
        
        try{
            if(bool) refundMgService.disposeFacRefundFinancialCallback(resMap, guaId, lockFinJson, lockGuaJson, repaymentJson, 2);
        }catch(Exception e){
            bool = false;
        }
        return bool?"操作成功！":"操作失败！";
	}
	
	/**
	 * @Title: factoringCallback 
	 * @author JW
	 * @Description: 保理回调接口
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/disposeFacRefundCallback",method=RequestMethod.POST)
	public JSON disposeFacRefundCallback(){
		
		String json = request.getParameter("JSON");
//		String jsonx = Utils.httpUtil.xmlIinputStream(request);
		//判断是保理自己的回调还是商城过来的担保还款
		refundMgService.disposeFacRefundCallback(JSONObject.fromObject(json));
		return null;
	}
	
}
