package com.zllh.mall.mmbmmanage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zllh.base.controller.BaseController;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.mall.common.model.MtMaterial;
import com.zllh.mall.common.model.MtMember;
import com.zllh.mall.common.model.MtMmbNode;
import com.zllh.mall.common.model.MtMmbWebsite;
import com.zllh.mall.common.model.MtMuser;
import com.zllh.mall.mmbmmanage.service.IMMBService;
import com.zllh.mall.mmbmmanage.service.IMmbWebsiteService;
import com.zllh.utils.base.Utils;
/**
 * @ClassName: MmbWebsiteController
 * @Description: 会员主页
 * @author zhufeiya
 * @date 2016-07-05 
 */
@Controller
@RequestMapping("/mmbwesite")
public class MmbWebsiteController extends BaseController {
	@Autowired
	private IMmbWebsiteService   web;
	@Autowired
	private IMMBService  mmb;
	/**
	 * 左边菜单进入会员查询页面
	 * @return
	 */
	@RequestMapping("/toMmbwebsite")
	public String toMmbwebsite(Model model){
		logger.info("====toMmbwebsite====");
		//获取用户模板
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = new MtMuser();
		mUser = user.getMuser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mmbId", mUser.getMmbId());
		List<MtMmbNode> list = web.getMmbNodes(params);
		model.addAttribute("nodesList", list);
		return "mall/mmb/mmb_show";
	}
	//新增或者编辑简介  摘要  logo
	@RequestMapping("/addIntroduce")
	@ResponseBody
	public String addIntroduce(Model model,
			@RequestParam(value = "mmbIntroduce", required = false, defaultValue = "") String mmbIntroduce,
			@RequestParam(value = "mmbTitle", required = false, defaultValue = "") String mmbTitle,
			@RequestParam(value = "mmbLogo", required = false, defaultValue = "") String mmbLogo){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
			MtMmbWebsite mb = new MtMmbWebsite();
			mb.setMmbId(mUser.getMmbId());
			mb.setMmbName(mUser.getMmbName());
			if (!StringUtils.isBlank(mmbIntroduce)) {
				mb.setMmbIntroduce(mmbIntroduce);
			}
			if (!StringUtils.isBlank(mmbTitle)) {
				mb.setMmbTitle(mmbTitle);
			}
			if (!StringUtils.isBlank(mmbLogo)) {
				mb.setMmbLogo(mmbLogo);
			}
			boolean flag = web.addIntroduce(mb);
			String nn = "";
			if(flag){
				nn="0";
			}else{
				nn="1";
			}
		return nn;
	}
	//新增图片
	@RequestMapping("/addMal")
	@ResponseBody
	public String addMal(Model model,
			@RequestParam(value = "type", required = false, defaultValue = "") Integer type,
			@RequestParam(value = "remark", required = false, defaultValue = "") String remark,
			@RequestParam(value = "malId", required = false, defaultValue = "") String malId){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
			boolean flag = web.addMal(mUser.getMmbId(), malId, type, remark);
			String nn = "";
			if(flag){
				nn="0";
			}else{
				nn="1";
			}
		return nn;
	}
	//删除 
	@RequestMapping("/delMal")
	@ResponseBody
	public String delMal(Model model,
			@RequestParam(value = "picType", required = false, defaultValue = "") Integer picType,
			@RequestParam(value = "type", required = false, defaultValue = "") Integer type,
			@RequestParam(value = "malId", required = false, defaultValue = "") String malId){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
			boolean flag = web.delMal(mUser.getMmbId(), malId, type,picType);
			String nn = "";
			if(flag){
				nn="0";
			}else{
				nn="1";
			}
		return nn;
	}
	//查询homepage
	@RequestMapping("/showHomePage")
	@ResponseBody
	public List<MtMaterial> showHomePage(){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId",mUser.getMmbId());
		map.put("type",0);
		List<MtMaterial> list = web.showPic(map, 0);
		return list;
	}
	
	//查询pic
	@RequestMapping("/showPic")
	@ResponseBody
	public List<MtMaterial> showPic(){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId",mUser.getMmbId());
		map.put("type",0);
		List<MtMaterial> list = web.showPic(map, 1);
		return list;
	}
	//查询商品
	@RequestMapping("/showGoodPic")
	@ResponseBody
	public List<MtMaterial> showGoodPic(){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mmbId",mUser.getMmbId());
		map.put("type",1);
		List<MtMaterial> list = web.showPic(map, 1);
		return list;
	}
	//查询简介
	@RequestMapping("/showIntroduce")
	@ResponseBody
	public MtMmbWebsite showIntroduce(){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
		MtMmbWebsite mmb = web.searchBymmbId(mUser.getMmbId());
		return mmb;
	}
	//生成自己的页面
	@RequestMapping("/viewMmb")
	@ResponseBody
	public String viewMmb(){
		logger.info("previewInfo");
    	//获取ajax传递过来的id,服务器参数
    	ServletContext servletContext = request.getSession().getServletContext();
    	UserExtendBean user =Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
		String type ="";
		boolean flag = web.previewInfo(mUser.getMmbId(), servletContext,type);
		//查询
		String path = "";
		if(flag){
			MtMmbWebsite mmb =web.searchBymmbId(mUser.getMmbId());
			path = mmb.getMmbPath();
		}
		System.out.println(path);
		return path;
	}
	//预览页面
	@RequestMapping("/viewMmbHtml")
	@ResponseBody
	public String viewMmbHtml(Model model,
			@RequestParam(value = "mmbId", required = false, defaultValue = "") String mmbId){
		logger.info("previewInfo");
    	//获取ajax传递过来的id,服务器参数
    	ServletContext servletContext = request.getSession().getServletContext();
    	String type ="";
		boolean flag = web.previewInfo(mmbId, servletContext,type);
		//查询
		String path = "";
		if(flag){
			MtMmbWebsite mmb =web.searchBymmbId(mmbId);
			path = mmb.getMmbPath();
		}
		System.out.println(path);
		return path;
	}
	
	
	//预览页面
	@RequestMapping("/viewMmbHtml2")
	@ResponseBody
	public String viewMmbHtml2(Model model,
			@RequestParam(value = "mmbId", required = false, defaultValue = "") String mmbId){
		logger.info("previewInfo");
    	//获取ajax传递过来的id,服务器参数
    	ServletContext servletContext = request.getSession().getServletContext();
    	if(mmbId==""){
    		UserExtendBean user =Utils.securityUtil.getUser();
    		MtMuser mUser = user.getMuser();
    		mmbId = mUser.getMmbId();
    	}
    	String type = "";
		boolean flag = web.previewInfo(mmbId, servletContext,type);
		//查询
		String path = "";
		if(flag){
			MtMember mm = mmb.queryMmbById(mmbId);
			path = mm.getMmbHomepage();
		}
		System.out.println(path);
		return path;
	}
	//添加关注会员关系     RedirectBefore  判断是否登录
	@RequestMapping("/followMmbRedirectBefore")
	public void foollowMmb(Model model,
			@RequestParam(value = "relaMmbId", required = false, defaultValue = "") String relaMmbId){
		String  json  = "";
		
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = new MtMuser();
		if(user!=null) {
			mUser = user.getMuser();
		 web.addRelaMMb(mUser.getMmbId(), relaMmbId);
			 MtMmbWebsite mmb =web.searchBymmbId(relaMmbId);
			 json = mmb.getMmbPath();
		}
		//type 0 添加成功  1添加失败   2   已存在   4  自己的会员
		request.setAttribute("json", json);
		
		try {
			request.getRequestDispatcher(json).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/followMmb1RedirectBefore")
	public String foollowMmb1(Model model,
			@RequestParam(value = "relaMmbId", required = false, defaultValue = "") String relaMmbId){
		
		String type = "";
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = null;
		if(user!=null) {
			mUser = user.getMuser();
			type = web.addRelaMMb(mUser.getMmbId(), relaMmbId);
			
		}
		return type;
		//type 0 添加成功  1添加失败   2   已存在   4  自己的会员
		
	}
	//删除会员主页
	@RequestMapping("/deleteMMbHome")
	@ResponseBody
	public String deleteMMbHome(Model model,
			@RequestParam(value = "mmbId", required = false, defaultValue = "") String mmbId){
		logger.info("deleteMMbHome");
		//获取ajax传递过来的id,服务器参数
    	ServletContext servletContext = request.getSession().getServletContext();
		if(mmbId==""){
    		UserExtendBean user =Utils.securityUtil.getUser();
    		MtMuser mUser = user.getMuser();
    		mmbId = mUser.getMmbId();
    	}
		MtMmbWebsite mmb =web.searchBymmbId(mmbId);
		String json = "";
		if(mmb!=null){
			//调用删除方法
			boolean flag = web.deleteMMbHome(mmb.getMmbName(),servletContext);
			 
			if(flag){
				json = "删除成功";
			}else{
				json = "该会员主页未发布";
			}
		}else{
			json = "该会员主页未发布";
		}
		
		
		return json;
	}
	//用户的模板
	@RequestMapping("/getMmbNodes")
	@ResponseBody
	public List<MtMmbNode> getMmbNodes(Model model,
			@RequestParam(value = "nodeId", required = false, defaultValue = "") String nodeId){
		UserExtendBean user = Utils.securityUtil.getUser();
		MtMuser mUser = new MtMuser();
		mUser = user.getMuser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mmbId", mUser.getMmbId());
		List<MtMmbNode> list = web.getMmbNodes(params);
		return list;
	}
	//发布会员主页
	@RequestMapping("/privewMmbPage")
	@ResponseBody
	public String  privewMmbPage(Model model,
			@RequestParam(value = "nodeId", required = false, defaultValue = "") String nodeId){
		ServletContext servletContext = request.getSession().getServletContext();
		String path = "";
		if(StringUtils.isBlank(nodeId)){
			return path;
		}
		UserExtendBean user =Utils.securityUtil.getUser();
		MtMuser mUser = user.getMuser();
		boolean flag = web.previewMmbPage(mUser.getMmbId(), servletContext, nodeId);
		//查询
		if(flag){
			MtMmbWebsite mmb =web.searchBymmbId(mUser.getMmbId());
			path = mmb.getMmbPath();
		}
		System.out.println(path);
		return path;
	}
	
}
