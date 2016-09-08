package com.zllh.base.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


public class LogoutSuccessExHandler implements LogoutSuccessHandler{

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        
        request.getSession().removeAttribute("user");
        String outCode = request.getParameter("outCode");
        if("backMg".equals(outCode)){
            request.getRequestDispatcher("/factoring_login.jsp").forward(request, response);
        }
    }
}
