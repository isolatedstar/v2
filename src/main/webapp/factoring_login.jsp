<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录界面</title>
 <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"></link>
 <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
 <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/factoring/css/login.css"></link>
 <style type="text/css">
      body{background-image: url("${pageContext.request.contextPath}/factoring/images/2.jpg");background-repeat: no-repeat;};
 </style>
 <script type="text/javascript">
 	$(function(){
 		var message = "${message}";
 		if(message!=""){
 			alert(message);
 		}
 	});
 </script>
</head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container container-box ">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><img src="${pageContext.request.contextPath}/factoring/images/-3.png"><strong>中国农校对接服务网</strong> </h1>
                            <div class="description">
                            	<p>
	                            	<strong><a href="http://www.nxdjfuw.org.cn/">农校对接服务网</strong> </a>将全国高校农产品采购交易信息有效整合，利用大数据，云计算，形成种类多，体量大，相对稳定的订单信息。优质的订单信息将专业大户，家庭农场，农民合作社，龙头企业等新型农业生产经营主体按照订单需求安排生产计划，实现农业生产导向向消费导向转变。</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登录</h3>
                            		<p>请输入您的用户名和密码:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa"><img src="${pageContext.request.contextPath}/factoring/images/aa.png"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="${pageContext.request.contextPath}/login.do" method="post" class="login-form">
			                    	<input type="hidden" value="backMg" name="loginCode"/>
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" name="username" placeholder="用户名..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="password" placeholder="密码..." class="form-password form-control" id="form-password">
			                        </div>
                                   <!--
                                    <div class="form-group">
                                        <label class="sr-only" for="form-username">用户名</label>
                                        <select name="selecter_basic" tabindex="-1"class="form-control form-control1 ">
                                        <option value="1">---请正确输入用户名---</option>
                                        <option value="2">---请正确输入用户名---</option>
                                     </select>
                                    </div> 
                                     
                                    <div class="form-group">
                                       
                                         <div class="yanzheng"><input type="text"class="yanzheng-input" id="form-password">
                                          <img src="${pageContext.request.contextPath}/images/yanzheng.jpg">
                                         <span><a href="#">点击刷新</a></span>
                                         </div>
                                    </div> -->
			                        <button type="submit" class="btn">登录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                 
                </div>
            </div>
            
       
 </div>

    </body>

</html>