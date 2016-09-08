package com.zllh.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.zllh.utils.common.Page;

/**
 * @ClassName: BaseController
 * @Description: Controller基类
 * @author JW
 * @date 2015�?1�?4�?上午9:38:19
 */
public class BaseController  {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session; 
    private static ObjectMapper objectMapper=new ObjectMapper();
    //分页数据
    public Page page;
    //日志显示
    public final Logger logger = LoggerFactory.getLogger(BaseController.class); 
    
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    }  
    
    /**
     * 写出json格式数据
     * @param object
     */
    public void outJson(Object object) {
		try {
			String json = JSON.toJSONString(object);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    protected String createJsonStr(Object o) throws Exception {
		return objectMapper.writeValueAsString(o);
	}
	
	protected Integer getPageSize() {
		String pageSize = this.request.getParameter("pageSize");
		return StringUtils.isBlank(pageSize) ? 20 : Integer.valueOf(pageSize);
	}

	public Integer getPageNo() {
		String pageNo = this.request.getParameter("pageNo");
		return StringUtils.isBlank(pageNo) ? 1 : Integer.valueOf(pageNo);
	}

	//根据当前页得到开始行 目前供app 使用
	public Integer getStartNo() {
		String pageNo = this.request.getParameter("pageNo");
		return  (Integer.valueOf(pageNo)-1)*getPageSize()+1;
	}
	
}
