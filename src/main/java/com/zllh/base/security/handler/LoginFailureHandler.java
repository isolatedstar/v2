package com.zllh.base.security.handler;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.alibaba.fastjson.JSON;
import com.zllh.base.security.exception.LoginErrException;
import com.zllh.base.security.exception.MethodErrorException;
import com.zllh.base.security.exception.ValidateCodeException;

/**
 * @ClassName: LoginFailureHandler
 * @Description: 登录失败异常
 * @author JW
 * @date 2015年11月24日 下午2:28:58
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {

	    String loginCode = request.getParameter("loginCode");
	    if("mall".equals(loginCode)){
	           
	        HashMap<String, String> remap = new HashMap<String, String>();
	        if (ex instanceof UsernameNotFoundException) {
	            // 账号错误
	            remap.put("err", ex.getMessage());
	        } else if (ex instanceof BadCredentialsException) {
	            // 密码错误
	            remap.put("err", "用户/密码错误,请重新输入!");
	        } else if (ex instanceof ValidateCodeException) {
	            // 验证码错误
	             remap.put("err", ex.getMessage());
	        } else if (ex instanceof MethodErrorException) {
	            // 请求方法错误
	            remap.put("err", ex.getMessage());
	        } else if (ex instanceof SessionAuthenticationException) {
	            // 登陆超时错误
	            response.sendRedirect(request.getContextPath() + "/timeout.jsp");
	        } else if (ex instanceof LoginErrException) {
	            // 登陆超时错误
	            response.sendRedirect(request.getContextPath() + "/timeout.jsp");
	        } else {
	            // 未知异常错误
	            request.setAttribute("message", "未知异常错误,请联系相关技术人员!");
	            response.sendRedirect(request.getContextPath() + "/error.jsp");
	        }
	        
	        String json = JSON.toJSONString(remap);
	        response.setContentType("text/html;charset=utf-8");
	        response.getWriter().write(json);
	        response.getWriter().flush();
	        response.getWriter().close();

        }else if("backMg".equals(loginCode)){
	    
    		response.setContentType("text/plain");
    		response.setCharacterEncoding("UTF-8");
    		if (ex instanceof UsernameNotFoundException) {
    			// 账号错误
    			request.setAttribute("message", ex.getMessage());
    			request.getRequestDispatcher("/factoring_login.jsp").forward(request, response);
    		} else if (ex instanceof BadCredentialsException) {
    			// 密码错误
    			request.setAttribute("message", "用户/密码错误,请重新输入!");
    			request.getRequestDispatcher("/factoring_login.jsp").forward(request, response);
    		} else if (ex instanceof ValidateCodeException) {
    			// 验证码错误
    			String message = ex.getMessage();
    			request.setAttribute("message", message);
    			request.getRequestDispatcher("/factoring_login.jsp").forward(request, response);
    		} else if (ex instanceof MethodErrorException) {
    			// 请求方法错误
    			request.setAttribute("message", ex.getMessage());
    			request.getRequestDispatcher("/factoring_login.jsp").forward(request, response);
    		} else if (ex instanceof SessionAuthenticationException) {
    			// 登陆超时错误
    			response.sendRedirect(request.getContextPath() + "/timeout.jsp");
    		} else if (ex instanceof LoginErrException) {
    			// 登陆超时错误
    			response.sendRedirect(request.getContextPath() + "/timeout.jsp");
    		} else {
    			// 未知异常错误
    			request.setAttribute("message", "未知异常错误,请联系相关技术人员!");
    			response.sendRedirect(request.getContextPath() + "/error.jsp");
    		}
        }else if("app".equals(loginCode)){

            HashMap<String, Object> reMap = new HashMap<String, Object>();
            reMap.put("obj", null);
            reMap.put("success", false);
            if (ex instanceof UsernameNotFoundException) {
                // 账号错误
                reMap.put("msg", ex.getMessage());
            } else if (ex instanceof BadCredentialsException) {
                // 密码错误
                reMap.put("msg", "用户/密码错误,请重新输入!");
            } else if (ex instanceof ValidateCodeException) {
                // 验证码错误
                reMap.put("msg", ex.getMessage());
            } else if (ex instanceof MethodErrorException) {
                // 请求方法错误
                reMap.put("msg", ex.getMessage());
            } else if (ex instanceof SessionAuthenticationException) {
                // 登陆超时错误
                reMap.put("msg", "登录超时！");
            } else if (ex instanceof LoginErrException) {
                // 登陆超时错误
                reMap.put("msg", "登录超时！");
            } else {
                // 未知异常错误
                reMap.put("msg", "未知异常错误,请联系相关技术人员!");
            }
            
            String json = JSON.toJSONString(reMap);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        }
	}
}