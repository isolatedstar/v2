<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>测试页</title>
     <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/factoring/css/login.css">
</head>
    <body>
        <div class="top-content">
            <div class="inner-bg">
                <div class="container container-box ">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><img src="${pageContext.request.contextPath}/factoring/images/-3.png"><strong>测试页</strong> </h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                            <div class="form-bottom">
			                    <form role="form" action="${pageContext.request.contextPath}/userController/testcsrf.do" method="post" class="login-form">
			                    	<sec:csrfInput/>
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" value="guest" name="username" placeholder="用户名..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="text" value="123"  name="password" placeholder="密码..." class="form-password form-control" id="form-password">
			                        </div>
			                        <button type="submit" class="btn">登录</button>
			                    </form>
			                    
			                    <form role="form" action="${pageContext.request.contextPath}/userController/test.do" method="post" class="login-form">
			                        <button type="submit" class="btn">测试事物</button>
			                    </form>
			                    
			                    <form role="form" action="${pageContext.request.contextPath}/assureController/test.do" method="post" class="login-form">
			                        <button type="submit" class="btn">添加担保单</button>
			                    </form>
			                    
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
 		</div>
    </body>
</html>