<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Language" content="zh-CN">
<title>中国农校对接服务网</title>
<link href="${rootUrl}${templatePath}css/pulic.css" rel="stylesheet" type="text/css">
<link href="${rootUrl}${templatePath}css/index.css" rel="stylesheet" type="text/css">
<link href="${rootUrl}${templatePath}css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="${rootUrl}${templatePath}js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${rootUrl}/common/js/common.js"></script>
</head>
<script>
	 
	 
	 
	 function addMmbId(){
        	var mmbId = $("#relaMmbId").val()
        	$.ajax({
				url:'${rootUrl}/mmbwesite/followMmbRedirectBefore.do?relaMmbId='+mmbId,
				type: "GET",
                dataType: "json",
				
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 alert(data);
				   	 if(data!=""){
				   	 	window.location = "http://www.baidu.com";
				   	 }
				}
			});
			
        }
</script>
<body>
<div id="head_top">
	<div class="head_top_in">
    	<p>欢迎来到<span>农校对接服务网！</span></p>
    	<input type="hidden" id="relaMmbId" value="${mmb.id}" />
    </div>
</div>
<div id="head_bot">
	<div class="logo"><img src="${rootUrl}${templatePath}images/logo_03.png" alt="农校对接服务网" style="width:224px; height:109px;"/></div>
    <div class="head_hy">
    	<div class="head_hy_in">
        	<h2>${mmb.mmbFname}</h2>
            <p class="hjhy">黄金会员</p>
            <p class="scdp"><i class="fa fa-star"></i><a href="#" onclick="addMmbId();">收藏店铺</a></p>
        </div>
    </div>
    <div class="head_adress">
    	<p class="head_adr_p1">TEL:${mmb.mmbPhone}</p>
        <p class="head_adr_p2">E-mail：${mmb.mmbEmail}</p>
    </div>
    <div class="head_right">
    	<p class="wycg"><a href="${rootUrl}/QuoteController/toshowShop1RedirectBefore.do">我要采购</a></p>
    	<p class="wyxs"><a href="${rootUrl}/QuoteController/toshowShopRedirectBefore.do">我要销售</a></p>
    </div>
</div>
<div id="nav">
	<div class="nav_in">
    	<ul>
        	<li><a href="${rootUrl}${showPath}"><i class="fa fa-home"></i>店铺首页</a></li>
        	<li><a href="${rootUrl}${showPath}#main_zs"><i class="fa fa-picture">&#xf03e;</i>产品展示</a></li>
        	<li><a href="${rootUrl}${showPath}#Quality"><i class="fa fa-beer"></i>企业资质</a></li>
        	<li><a href="${rootUrl}${contextPath}" class="nav_active"><i class="fa fa-phone"></i>联系我们</a></li>
        </ul>
    </div>
</div>
<div id="tel_main">
	<div class="tel_main_top">
    	<div class="tel_top_left">
        	<img src="${rootUrl}${mmbLogo}" alt="农校服务网"/>
        </div>
        <div class="tel_top_title">
            <h2>${mmb.mmbFname}</h2>
            <p>${mmbIntroduce}</p>
        </div>
    </div>
    <div class="tel_main_bot">
    	<div class="tel_bot_title">
        	<h2>详细信息</h2>
        </div>
        <div class="tel_bot_con">
        	<h2>${mmb.mmbSname}</h2>
            <table  cellspacing="0" cellpadding="0">
            	<tbody>
                	<tr>
                    	<td width="40%">联系人：${mmb.mmbFname}</td>
                    	<td width="40%" rowspan="3" style="border-bottom:2px #8c8c8b solid">企业所在地:${mmb.mmbAddress}</td>
                    	<td width="20%" rowspan="5" style="border-right:2px #8c8c8b solid;border-bottom:2px #8c8c8b solid; padding-left:16px;">
                        	<img src="${rootUrl}${imgPath}" alt="农校对接服务网" style="width:188px;height:188px;"/>
                        </td>
                    </tr>
                	<tr>
                    	<td>手机号码：${mmb.mmbPhone}</td>
                    </tr>
                	<tr>
                    	<td style="border-bottom:2px #8c8c8b solid">E-mail：${mmb.mmbEmail}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!--footer start-->
<div id="footer">
	<div class="footer_in">
        <div class="footer_in_bot">
        	<ul class="footer_in_ul">
            	<li><a href="http://www.nxdjfuw.org.cn/html/001.htm">中国教育后勤协会密函[2014]7号</a><span>|</span></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/002.htm">关于我们<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/003.htm">服务条款<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/004.htm">友情链接<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/005.htm">广告服务<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/006.htm">法律声明<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/007.htm">联系我们<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/008.htm">帮助中心<span>|</span></a></li>
                <li><a href="http://www.bjzllh.com/join.php">诚聘精英</a></li>
            </ul>
            <div class="clear"></div>
        	<p>支持单位：教育部 农业部 商务部 中华全国供销合作总社 主办单位：中国教育后勤协会</p>
        	<p>地址：北京市海淀区上园村3号北京交通大学科技大厦1402室</p>
        	<p>中联联合投资控股集团有限公司版权所有©京ICP证：010026 海淀公安局网络备案编号：京公网安备 11010802010990</p>
        	<p><a href="http://www.nxdjfuw.org.cn/html/010.htm">电信与信息服务业务经营许可证 140335</a></p>
        	<p>电话：010-62513620 邮箱：zhouyi@nxdjfuw.org.cn</p>
        
        </div>
    </div>
</div>
</body>
</html>
