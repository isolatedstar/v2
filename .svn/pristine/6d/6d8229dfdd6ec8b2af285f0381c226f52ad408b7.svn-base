package com.zllh.base.security.serviceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;

public class LoginUrlAuthenticationEntryPointExtends extends LoginUrlAuthenticationEntryPoint {

	public LoginUrlAuthenticationEntryPointExtends(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String returnUrl = buildHttpReturnUrlForRequest(request);  
        request.getSession().setAttribute("ru", returnUrl);
        if(returnUrl!=null&&returnUrl.contains("RedirectBefore")){
        	request.getSession().setAttribute("relaMmbId", returnUrl.substring(returnUrl.indexOf("=")+1, returnUrl.length()));
        	request.getSession().setAttribute("mbcode", "Y");
        }
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			 response.setCharacterEncoding("UTF-8");
             response.addHeader("Error-Json", "{code:302,msg:'会话超时',script:'',url:'"+request.getContextPath() + "/timeout.jsp"+"'}");
             response.setStatus(300);
		} else {
			super.commence(request, response, authException);
		}
	}
	
	protected String buildHttpReturnUrlForRequest(HttpServletRequest request)throws IOException, ServletException {  
        RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();  
        urlBuilder.setScheme("http");  
        urlBuilder.setServerName(request.getServerName());  
        urlBuilder.setPort(request.getServerPort());  
        urlBuilder.setContextPath(request.getContextPath());  
        urlBuilder.setServletPath(request.getServletPath());  
        urlBuilder.setPathInfo(request.getPathInfo());  
        urlBuilder.setQuery(request.getQueryString());  
        return urlBuilder.getUrl();  
    }  
}
