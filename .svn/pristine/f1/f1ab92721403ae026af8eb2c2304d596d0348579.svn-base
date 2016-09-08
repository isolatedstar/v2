<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>菜单页</title>
<!--my css-->
<link href="${pageContext.request.contextPath}/common/css/mall_index.css" rel="stylesheet" type="text/css" />
<!-- 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/theme.css"/> -->
<!--title logo-->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/common/images/mall logo.png" type="image/x-icon">
<!--font css-->
<link href="${pageContext.request.contextPath}/common/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!--js pulic-->
<script src="${pageContext.request.contextPath}/common/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>



</head>

<body>
<div id="awarp"> 
<!---------------------------------------header start--------------------------------------------------------------->
	<div class="a_header">
		<div class="header-menu" >
	        <span id="header-menu" style="font-size:20px; color:#fff; float:right; margin-top:22px; margin-right:15px; cursor:pointer; " ><i class="fa fa-th-list "></i></span>
		</div>
    	<img src="${pageContext.request.contextPath}/common/images/logo888.png" style="width:120px; height:40px" />
        <p><i>让管理更得心应手！</i></p>
		<div class="header-right">
			<p><font style="color:#fff;font-size:16px;opacity:0.7;">欢迎您:${user.userName}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href = "${pageContext.request.contextPath}/logout.do?outCode=backMg">退出登录</a></p>
		</div>
    </div>
<!---------------------------------------header over---------------------------------------------------------------->
<!---------------------------------------content start-------------------------------------------------------------->
<div class="a_content">
<!---------------------------------------nav start------------------------------------------------------------------>
<div class="a_nav_box" id="menu">

</div>
<!---------------------------------------nav over------------------------------------------------------------------>
<!---------------------------------------ifmare start-------------------------------------------------------------->
	<div class="if_warp" id="if_warp" >
		<iframe name='iff' class="ifr"  src="${pageContext.request.contextPath}/homePage.jsp" width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>
	<div class="clear"></div>
<!---------------------------------------ifmare over-------------------------------------------------------------->
</div>
<!---------------------------------------content over-------------------------------------------------------------->
<!---------------------------------------footer start-------------------------------------------------------------->

<div class="footer"><p>中联联合投资控股集团有限公司版权所有©京ICP证：010026 海淀公安局网络备案编号：京公网安备 11010802010990</p></div>

<!---------------------------------------footer over-------------------------------------------------------------->
</div>
</body>
<script>
$("#header-menu").click(function(){
	if($("#menu").css("display")== "none"){
	
		$("#menu").attr("style","display:block");
		$("#if_warp").attr("style","height:920px;margin-left:225px;");
	}else{
		$("#menu").attr("style","display:none");
		$("#if_warp").attr("style","height:920px;margin-left:10px;");
	}
	
	
});	

</script>
<script type="text/javascript">
		$(function(){
			var teamList=""; 
			var readyList=${menuList};
			var content="<ul class='a_nav'>";
			//$("#menu").append("<ul class='a_nav'>");
			for(var p in readyList){//遍历json数组时，这么写p为索引，0,1
				//进来的元素长度等于4
				if(readyList[p].dircode.length==4){
					var myurl="${pageContext.request.contextPath}"+readyList[p].resUrl;
					
					//若进来的元素长度小于上一个元素长度,
					if(readyList[p].dircode.length<teamList.length){
						if(teamList.length==12){
							content+="</ul></ul>";
						}else if(teamList.length==8){
							content+="</ul>";
						}else{
							alert("异常！");
						}
						if(readyList[p].resUrl.length>0){
							content+="<li class='a_nav_li'><a target='iff' href='"+myurl+"'><i class='fa fa-user'></i>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}else{
							content+="<li class='a_nav_li'><a href='#'><i class='fa fa-user'></i>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}
					}else{
						if(readyList[p].resUrl.length>0){
							content+="<li class='a_nav_li'><a target='iff' href='"+myurl+"'><i class='fa fa-user'></i>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}else{
							content+="<li class='a_nav_li'><a href='#'><i class='fa fa-user'></i>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}
					}
					teamList=readyList[p].dircode;
				} else if(readyList[p].dircode.length==8){
					if(readyList[p].dircode.length>teamList.length){
						if(readyList[p].resUrl.length>0){
							content+="<ul class='a_subnav'><li class='a_subnav_li'><a target='iff' class='nav_bj_none' href='";
							content=content+"${pageContext.request.contextPath}"+readyList[p].resUrl;
							content+="'>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}else{
							content+="<ul class='a_subnav'><li class='a_subnav_li'><a class='nav_bj_none' href='#'>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}
					} else if(teamList.length==readyList[p].dircode.length){
						if(readyList[p].resUrl.length>0){
							content+="<li class='a_subnav_li'><a target='iff' class='nav_bj_none' href='";
							content=content+"${pageContext.request.contextPath}"+readyList[p].resUrl;
							content+="'>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}else{
							content+="<li class='a_subnav_li'><a class='nav_bj_none' href='#'>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}
					}else if(readyList[p].dircode.length<teamList.length){
						if(readyList[p].resUrl.length>0){
							content+="</ul><li class='a_subnav_li'><a target='iff' class='nav_bj_none' href='";
							content=content+"${pageContext.request.contextPath}"+readyList[p].resUrl;
							content+="'>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}else{
							content+="</ul><li class='a_subnav_li'><a class='nav_bj_none' href='#'>";
							content+=readyList[p].dirname;
							content+="</a></li>";
						}
					}else{
						alert("异常！");
					}
					teamList=readyList[p].dircode;
				}else if(readyList[p].dircode.length==12){
					if(readyList[p].dircode.length>teamList.length){
						content+="<ul class='b_subnav'><li><a target='iff' class='nav_bj_none' href='";
						content=content+"${pageContext.request.contextPath}"+readyList[p].resUrl;
						content+="'  style='text-decoration: none;'>";
						content+=readyList[p].dirname;
						content+="</a></li>";
					}else{
						content+="<li><a target='iff' class='nav_bj_none' href='";
						content=content+"${pageContext.request.contextPath}"+readyList[p].resUrl;
						content+="'  style='text-decoration: none;'>";
						content+=readyList[p].dirname;
						content+="</a></li>";
					}
					teamList=readyList[p].dircode;
				}
				else{	
					//alert("4");
				}
			}
			content+="</ul>";
			
			$("#menu").append(content);
		});

$(document).ready(function(e) {
	//nav js start
	$('.a_nav_li').click(function(){
		$('.a_subnav').hide()
		$(this).addClass("a_moren").siblings().removeClass("a_moren");
		$(this).next('.a_subnav').stop().slideToggle()
		})
	$('.a_subnav_li').click(function(){
		$('.b_subnav').slideUp()
		$('.a_subnav_li a').css('background-position','200px top')
		$(this).find('a').css('background-position','200px bottom')
		$(this).next('.b_subnav').stop().slideToggle()
		});
	$('.b_subnav>li').click(function(){
		$('.b_subnav>li').removeClass('c_active')
		$(this).addClass("c_active")
		})
	//over
	//if start
	
	$('.a_anav li').click(function(){
		$($(this).attr('href')).show()
		})
});
</script>
<script>
$(document).ready(function(e) {
	$('.header-right p').click(function(){	
		$(this).next('.header_sub').stop().slideToggle(200)
	})
})
</script>

</html>
