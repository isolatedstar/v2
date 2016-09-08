<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>保理</title>
<script type='text/javascript' src='${ctx}/js/jquery-1.4.2.min.js'></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/factoring_list.css"/>

<script language="javascript"> 
	now=new Date();
	nyear=now.getFullYear();
	nmonth=now.getMonth()+1;
	nday=now.getUTCDate();
	var weekday=new Array(7)
	weekday[0]="日"
	weekday[1]="一"
	weekday[2]="二"
	weekday[3]="三"
	weekday[4]="四"
	weekday[5]="五"
	weekday[6]="六"
	nwday=weekday[now.getDay()];	
</script>
</head>
<body>
<!-- head -->
	<div class="fac_head">
		<div class="fac_hleft">
			<img src="${ctx}/images/baol_01.png" width="110px" height="55px" />
			<span class="fac_title">农校对接保理管理系统</span>
		</div>
		<div class="fac_hright">
			<p>您好！管理员 欢迎使用本系统！</p>
			<script language="javascript"> document.write('<p class="fac_data">'+nyear+'年'+nmonth+'月'+nday+'日&nbsp;星期'+nwday+'</p>');</script>
		</div>
	</div>
<!-- head -->
</body>
</html>