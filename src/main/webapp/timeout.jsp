<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<style type="text/css">
#warning{ width:99%;}
#warning .warn_warp{width:70%; margin:0 auto;}
#warning .login_warp{ width:50%;margin:0 auto;}
#warning img{ width:128px; height:128px;}
#warning b{ font-size:32px; color:#666; position:relative; top:-20px; left:20px;}
#warning .warn_button{display:block;cursor:pointer;background:url(images/warning_but.png) no-repeat; width:112px; height:32px; line-height:36px; color:#FFF; text-align:center; font-weight:bold; margin:40px 0 0 36%;}
.warn_fhjm{font-size:26px; display:block;margin:40px 0 0 28%;}
</style>
<script type='text/javascript'>
	var secs = 2; //倒计时的秒数   
	var URL;
	function Load(url) {
		URL = url;
		for ( var i = secs; i >= 0; i--) {
			window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);
		}
	}
	function doUpdate(num) {
		document.getElementById('showInfo').innerHTML = '将在' + num + '秒后自动跳转到登录页面';
		if (num == 0) {
			parent.location.href= URL;
		}
	}
	function goBack(){
		parent.location.href= URL;   
	}
</script>
<title>登录超时</title>
</head>
<body>

<div id="warning">
	<div class="login_warp">
	    <p><img src="${pageContext.request.contextPath}/images/warning.png" /><b>登录超时或者会话无效,请重新登录！</b></p>
		<span id="showInfo" class="warn_fhjm"></span>
	    <span class="warn_button" onclick="goBack()">返回</span>
    </div>
</div>
	<script language="javascript">
		Load("${pageContext.request.contextPath}/factoring_login.jsp"); //要跳转到的页面
	</script>
</body>
</html>