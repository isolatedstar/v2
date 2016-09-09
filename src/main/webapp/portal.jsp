<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国农校对接服务网</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/portal/plugins/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/portal/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/style.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/main.css">
<script src="${pageContext.request.contextPath}/portal/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/portal/js/fudong.js"></script>
<script src="${pageContext.request.contextPath}/portal/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		var user = "${user}";
		if(user!=""){
			$("#loginBefor").hide();
			$("#loginAfter").show();
			$("#purchase").show();
			$("#sell").show();
			$("#main").show();
		}else{
			$("#loginAfter").hide();
			$("#loginBefor").show();
			$("#purchase").hide();
			$("#sell").hide();
			$("#main").hide();
		}
		
	});
	
	function login(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(username=="") {alert("用户名不能为空！"); return false}
		if(password=="") {alert("密码不能为空！"); return false}
		$.ajax({
			url:'${pageContext.request.contextPath}/login.do',
			dataType:"JSON",
			type:"POST",
			data:{"username":username, "password":password, "loginCode":"mall"},
			success:function(data){
				if(data.err!=undefined){
					alert(data.err);
				}else{
					var userName = data.userName;
					$("#userSpan").html(userName);
					$("#loginBefor").hide();
					$("#loginAfter").show();
					$("#purchase").show();
					$("#sell").show();
					$("#main").show();
				}
			}
		});
	}
	
	function outLogin(){
		$.ajax({
			url:'${pageContext.request.contextPath}/logout.do',
			dataType:"JSON",
			type:"POST",
			data:{"outCode":"mall"},
			success:function(data){
			}
		});
		
		window.location.reload();
	}
	
	function toHome(){
		window.open("${pageContext.request.contextPath}/userController/toHome.do");
	}
	
	function purchase(){
		window.open("${pageContext.request.contextPath}/QuoteController/toshowShop1RedirectBefore.do");
	}
	
	function sell(){
		window.open("${pageContext.request.contextPath}/QuoteController/toshowShopRedirectBefore.do");
	}
	
</script>
</head>
<body class="c-body">

<!-- ===  === -->
<div id="main">
  	<div id="pop" style="display:none;">
	  	<div id="popHead">
			<a id="popClose" title="关闭">关闭</a>
			<h4>推荐</h4>
		</div>
		<div id="popContent">
		<iframe src="${pageContext.request.contextPath}/mmbAdvert/toMmbAdvert_Auth.do" frameborder="0" height="100%"></iframe>
		</div>
	</div>
	<script> var popad=new Pop(); </script>
</div>

<!--=== Header v1 ===--> 
<div class="container-fluid"> 
	<div class="row"> 
		<div class="header-v1 ">
             <!-- Topbar -->
            <div class="topbar-v1">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12">
                          <div class="row text-right">
                          	<ul class="list-inline top-v1-contacts" id="loginBefor">
                                <li>
                                	<h5 >用户:</h5>
                                    <div class="input-group">
                                         <input type="text" class="form-control topbar-v1-input" id="username" name="username">
                                    </div>
                                </li>
                                <li>
                                	<h5>密码:</h5>
                                    <div class="input-group">
                                         <input type="password" class="form-control topbar-v1-input" id="password" name="password">
                                    </div>
                                </li>
                                <li>
                                    <input name="submit" type="image" value="登录" onclick="return login();" src="${pageContext.request.contextPath}/portal/image/login_btn.png"/>
                                </li>
                                <li>
                                    <input name="submit" type="image" value="成为会员" src="${pageContext.request.contextPath}/portal/image/memeber_btn.png"/>
                                </li>
                            </ul>
                            <ul class="list-inline top-v1-contacts" id = "loginAfter">
                                <li>
                                  	<span id="userSpan" >${user.userName}</span>	 您好！ 欢迎来到
                                	<a href="#" title="农校对接服务网">
										农校对接服务网
									</a>
                                </li>
                                <li>
                                    <input name="loginOut" type="image" value="退出" onclick="outLogin();" src="${pageContext.request.contextPath}/portal/image/dengchu_03.png"/>
                                </li>
                                <li>
                                    <input name="usebe" type="image" value="开始使用" onclick="toHome();" src="${pageContext.request.contextPath}/portal/image/kaishishiyong_05.png"/>
                                </li>
                            </ul>
                          </div>
                        </div>
                    </div>        
                </div>
            </div>
            <!-- End Topbar -->
         	<div>
                <div class="topbar-v2 text-center">
                      <div> 
                          <img  id="zhonglianjituan" alt="中联集团" src="${pageContext.request.contextPath}/portal/image/logo.png">
                          <input id="purchase" name="purchase" type="image" value="采购" onclick="purchase();" src="${pageContext.request.contextPath}/portal/image/caigou_btn.png"/>
                          <input id="sell" name="sell" type="image" value="销售" onclick="sell();" src="${pageContext.request.contextPath}/portal/image/xiaoshou_btn.png"/>
                      	  <img  id="phone" alt="联系电话" src="${pageContext.request.contextPath}/portal/image/phone.png">  
                      </div>
                  </div>
              </div>
              <!-- End Topbar -->
		</div>
	</div>
</div>
<!-- End Header -->
<!--=== banner ===-->
<div class="banner-v">
    <div id="myCarousel" class="carousel slide">
       <!-- 轮播（Carousel）指标 -->
       <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
       </ol>   
       <!-- 轮播（Carousel）项目 -->
       <div class="carousel-inner">
          <div class="item active">
             <img src="${pageContext.request.contextPath}/portal/image/banner.png" alt="First slide">
          </div>
          <div class="item">
             <img src="${pageContext.request.contextPath}/portal/image/banner.png" alt="Second slide">
          </div>
          <div class="item">
             <img src="${pageContext.request.contextPath}/portal/image/banner.png" alt="Third slide">
          </div>
       </div>
       <!-- 轮播（Carousel）导航 -->
       <a class="carousel-control left" href="#myCarousel" 
          data-slide="prev">&lsaquo;</a>
       <a class="carousel-control right" href="#myCarousel" 
          data-slide="next">&rsaquo;</a>
    </div> 
        
</div>
<!--===End banner ===-->
<!--=== 推荐企业 ===-->
<div class="conmpany">
<div class="container "  style="min-width:1180px;">
    <div class="row" >
        <div class="col-xs-12" style="border-bottom:3px #e49d04 solid;padding-bottom:20px;"> 
            <div class="myCompany" >
            <img src="${pageContext.request.contextPath}/portal/image/company_icon.png">
            <h2>推荐企业</h2>
            <select class="form-control" id="company_area">
                 <option value="0">北京</option>
                 <option value="1">上海</option>
                 <option value="2">广州</option>
             </select>
            </div>
   		 </div>
    </div>
</div>
<iframe id='bj_com' name='iffcom' class="ifr" src="${pageContext.request.contextPath}/portal/html/company.html" width="100%" height="100%" frameborder="0" marginheight="0px;" marginwidth="0" scrolling="auto" onLoad="iFrameHeight()" style="margin-top:15px; "></iframe>
</div>
<script>
function iFrameHeight() {   
	var ifm= document.getElementById("bj_com");   
	var subWeb = document.frames ? document.frames["bj_com"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	}   
	} 
$(document).ready(function(){
	$("#bj_com").attr("src","${pageContext.request.contextPath}/portal/html/company.html");
	$('#company_area').change(function(){ 
		 var checkValue=$("#company_area").val();  
		 if(checkValue==0){
			 $("#bj_com").attr("src","${pageContext.request.contextPath}/portal/html/company.html");
		 }
		 if(checkValue==1){
			 $("#bj_com").attr("src","${pageContext.request.contextPath}/portal/html/sh_company.html");
		 }
		 if(checkValue==2){
			 $("#bj_com").attr("src","${pageContext.request.contextPath}/portal/html/gz_company.html");
		 }
	}) 
}) 
</script>
<!--=== End 推荐企业 ===-->
<!--=== 交易额 ===-->
<div class="trade">
<iframe name='iff' class="ifr" src="${pageContext.request.contextPath}/portal/html/html_ck.html" width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>
</div>
<!--=== End  交易额===-->
<!--=== 合作企业 ===-->
<div class="cooperation">
  <iframe name='iffcoop' class="ifr" src="${pageContext.request.contextPath}/portal/html/cooperation.html" width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>  
</div>
<!--=== End  合作企业===-->
<!--=== 第二个banner图 ===-->
<div class="banner-second">

</div>
<!--=== End  第二个banner图===-->
<!--=== 多快好省 ===-->
<div class="foursign">
    <div class="container ">
        <div class="row">
            <div class="col-xs-3 ">
            	<div class="signfourdetail">
                <span><strong>多</strong></span>
                </div>
                <div class="signfourtxt">
                <span>品类齐全 轻松购物</span>
                </div>
            </div>
            <div class="col-xs-3 ">
            	<div class="signfourdetail">
                <span><strong>快</strong></span>
                </div>
                <div class="signfourtxt">
                <span>多仓直发 极速配送</span>
                </div>
            </div>
            <div class="col-xs-3 ">
            	<div class="signfourdetail">
                <span><strong>好</strong></span>
                </div>
                <div class="signfourtxt">
                <span>正品行货 极致服务</span>
                </div>
            </div>
            <div class="col-xs-3 ">
            	<div class="signfourdetail">
                <span><strong>省</strong></span>
                </div>
                <div class="signfourtxt">
                <span>天天低价 畅选无忧</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!--=== End  多快好省===-->
<!--=== footer===-->
<div class="footer">
    <div class="footer-txt text-center">
    	<p>中国教育后勤协会密函[2014]7号|关于我们|服务条款|友情链接|广告服务|法律申明|联系我们|帮助中心|诚聘精英</p>
        <p>支持单位：教育部 农业部 商务部 中华全国供销合作总社 主办单位：中国教育后勤协会</p>
        <p>地址：北京市海淀区上园村3号北京交通大学科技大厦1402室</p>
        <p>中联联合投资控股集团有限公司版权所有©京ICP证：010026 海淀公安局网络备案编号：京公网安备 11010802010990</p>
        <p>电信与信息服务业务经营许可证 140335</p>
        <p>电话：010-62513620 邮箱：zhouyi@nxdjfuw.org.cn</p>
    </div>
</div>
<!--=== End  footer===-->
</body>
</html>