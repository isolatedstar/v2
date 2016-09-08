<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页面导航</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/factoring_list.css"/>
<script type='text/javascript' src='${ctx}/js/jquery-1.4.2.min.js'></script>
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
$(function(){
	/******** iframe高度自动适应 ********/
   function SetWinHeight() {
		var ifm= document.getElementByClass("ifr");
		var subWeb = document.frames ? document.frames["ifr"].document :
		ifm.contentDocument;
		if(ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
		}
	}
	/******** iframe高度自动适应 over ********/
	/******** 左侧菜单选项 *****/
	$('.fac_content:gt(0)').hide();
	var div_cont = $('#fac_cont .fac_content');
	$('#fac_menu li').each(function(index){
		$(this).click(function(){
			$(this).addClass('fac_menu_one').siblings().removeClass();
			div_cont.eq(index).show().siblings('.fac_content').hide();
		});
		
	});
	
	/******** 左侧菜单选项 ********/
});
</script>
</head>
<body>
<div class="fac_wrap">
	<!-- head -->
	<div class="fac_head_all">
	<iframe name='iff' class="ifr" src="factoring_head.html"  onload="Javascript:SetWinHeight(this)" width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto" ></iframe>
	</div>
	<!-- head over-->
	<!-- cont -->
	<div id="fac_cont">
		<div class="fac_wnav">
			<ul class="fac_wnav01">
				<li><a target="_bank" href="factoring_index.html"><img src="images/bleft_01.png" width="77" height="75" /><br/>担保登记</a></li>
				<li><a target="_bank" href="factoring_index.html"><img src="images/bleft_02.png" width="77" height="75" /><br/>融资管理</a></li>
				<li><a target="_bank" href="factoring_index.html"><img src="images/bleft_03.png" width="77" height="75" /><br/>认可管理</a></li>
				<li><a href="factoring_d.html""><img src="images/bleft_04.png" width="77" height="75" /><br/>融资担保</a></li>
			</ul>
			<div class="clearfix"></div>
			<ul class="fac_wnav01">
				<li><a target="_bank" href="factoring_index.html"><img src="images/bleft_05.png" width="77" height="75" /><br/>还款管理</a></li>
				<li><a target="_bank" href="factoring_index.html"><img src="images/bleft_06.png" width="77" height="75" /><br/>直接融资</a></li>
				<li><a target="_bank" href="factoring_index.html"><img src="images/bleft_07.png" width="77" height="75" /><br/>过期处理</a></li>
			</ul>
		</div>
	</div>
	<!-- cont over -->
	<!-- bottom -->
	<div class="fac_bottom">
	
	</div>
	<!-- bottom over-->
</div>























</body>
</html>