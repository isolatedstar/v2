<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>保理</title>

    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"> 
    <link href="${ctx}/css/bootstrap.min111.css" rel="stylesheet">
    <link href="${ctx}/css/theme.css" rel="stylesheet">
	 
	 <script src="js/jquery.js"></script>
	<script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/bootstrap.min111.js"></script>
	<script type="text/javascript" src="${ctx}/js/left-menu.js"></script>
	
    <script type="text/javascript">

       $(function(){
            $(".ad-menu").niceScroll({cursorborder:"0 none",cursorcolor:"#1a1a19",cursoropacitymin:"0",boxzoom:false});
        })

    </script>
</head>
<body>
    <nav class="navbar navbar-default navbar-inverse navbar-fixed-top " role="navigation">
	    <div class="container-fluid">
	      <div class="row">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navleft-shrink" aria-expanded="false">
	            <span class="sr-only">折叠头部导航三条横杠</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	           </button>
	             
      <div class="nx-logo">
            <!-- logo部分 -->
            <img src="${ctx}/images/dssss.png">
            <h3 >农校对接保理管理系统</h3>
          </div>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right nav-topright-margin">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top-admim" data-toggle="dropdown">
                <span class="glyphicon glyphicon-user"></span>
                管理员 <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a href="#">设置</a>
                </li>
                <li>
                  <a href="#">个人资料</a>
                </li>
                <li>
                  <a href="#">账户中心</a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="${ctx}">退出登录</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <!--头部链接部分 --> </div>
    </div>
  </nav>
  <!-- 以上为头部 -->





<div class="container-fluid">
      <div class="row">
 <!-- /左侧导航-->

<div class="col-xs-6 span3 nav-left"id="navleft-shrink">

<div id="admin">

    		<div class="ad-menu" id="ad-menu">


                <div class="ad-list">

                    <ul>
                      
                         <li>
						  <div class="li-item active" >
							<span class="glyphicon glyphicon-list-alt ico-margin"></span>
							<a href="${ctx}/assureController/toAssureNoFilter.do" target="menuFrame"  >担保登记</a>
							<span class="scm arrow"></span>
						  </div>
						</li>
                        <li>
                           <div class="li-item"><span class=" glyphicon glyphicon-lock ico-margin"></span><a href="${ctx}/directFinancingController/directFinancingNoFilter.do?pageNo="+1 target="menuFrame" >直接融资<span class="scm arrow"></a></span></div>
                       </li>
                        <li>
                           <div class="li-item"><span class="glyphicon glyphicon-barcode  ico-margin"></span><a href="${ctx}/accreditationMgController/acceptNofilter.do" target="menuFrame" >认可管理</a><span class="scm arrow"></span></div>
                        </li>
                        <li>
                          <div class="li-item"><span class="glyphicon glyphicon-tasks ico-margin"></span><a href="${ctx}/refundMgController/toRepaymentNoFilter.do" target="menuFrame" >还款管理</a><span class="scm arrow"></span></div>
                       </li>
                       <#--
                       <li>
                         <div class="li-item"><span class="glyphicon glyphicon-briefcase ico-margin"></span><a href="${ctx}/tenderController/tenderNoFilter.do" target="menuFrame" >融资意向管理</a><span class="scm arrow"></span></div>
                       </li>
                       <li>
                            <div class="li-item"><span class="glyphicon glyphicon-resize-small ico-margin"></span><a href="${ctx}/respondController/respondNoFilter.do" target="menuFrame" >意向回应管理</a><span class="scm arrow"></span></div>
                        </li>-->
                    </ul>

                </div>

            </div>
</div>
</div>
<!-- /左侧导航结束span3 -->

  

<div class="col-sm-9 right-nav "> <!-- col-sm-offset-3 col-md-10 col-md-offset-2 main -->
 
     <div class="mian_content">
            <div id="page_content">
                <iframe id="menuFrame" name="menuFrame" src="main.html" style="overflow:visible;" scrolling="yes" frameborder="no"height="1200px" width="100%"></iframe>
            </div>
        </div>
</div>






 

</div> <!-- /row -->
</div>
<footer class="panel-footer text-right bg-light lter">
<p>中联联合投资控股集团有限公司版权所有©京ICP证：010026</p>
</footer>
</body>
</html>