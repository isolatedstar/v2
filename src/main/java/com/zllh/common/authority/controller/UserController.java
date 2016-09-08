package com.zllh.common.authority.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.zllh.base.controller.BaseController;
import com.zllh.common.authority.service.UserService;
import com.zllh.common.common.dao.PubResDirRoleMapper;
import com.zllh.common.common.model.PubResDirRole;
import com.zllh.common.common.model.model_extend.UserExtendBean;
import com.zllh.utils.base.Utils;
import com.zllh.utils.common.VerificationCode;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private PubResDirRoleMapper resDirRoleMapper;
	
	@RequestMapping("/toTest")
	public void toTest(HttpServletRequest request) throws IOException{
	    System.err.println("测试>>>>>>>>>>>>>>>>>>");
	    String aa = "{\"testHouTai\": \""+request.getSession().getId()+"\"}";
	    response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(aa);
        response.getWriter().flush();
        response.getWriter().close();
	}
	
	@SuppressWarnings("rawtypes")
	public String listOutJson( List list){
		String jsonarray= JSONArray.toJSONString(list);
		return jsonarray;
    }
	
	@RequestMapping("/generatorNoFilter")
	public void generatorNoFilter(HttpServletRequest request, HttpServletResponse response){
		try {
			VerificationCode.generator(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/toHome")
	public String toHome(HttpServletRequest request){
		try{
			Object ojb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserExtendBean user = (UserExtendBean) ojb;
			Object[] roles = user.getAuthorities().toArray();
			String roleIds = Utils.commonUtil.toStringByComma(roles);
			List<PubResDirRole> mes =resDirRoleMapper.selecByUserRoles(roleIds);
			String listString=listOutJson(mes);
			request.setAttribute("menuList", listString);
			request.getSession().setAttribute("user", user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "common/home";
	}
}
