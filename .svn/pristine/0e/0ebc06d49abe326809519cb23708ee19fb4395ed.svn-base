<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>中联联合支付系统</title>



<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/jquery.cookie.js"></script>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/payment/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/payment/jquery-easyui-1.4.4/themes/icon.css">
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/demo.css"> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/changeEasyuiTheme.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/acctMgt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/fundTransfer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/bankMgt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/msgSend.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/acctBalance.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/manulAjust.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/jquery-easyui-1.4.4/themes/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:12%;background-image:url('${pageContext.request.contextPath}/payment/images/banner.jpg');padding:10px">

		<div id="dlg-buttons" style="width: 200px; float: right">
			<a href="#" onclick="changeThemeFun('default');"><img src="${pageContext.request.contextPath}/payment/images/default.png" title="默认主题"></a> <a href="#" onclick="changeThemeFun('black');"><img src="${pageContext.request.contextPath}/payment/images/black.png" title="黑色主题"></a> <a href="#" onclick="changeThemeFun('bootstrap');"><img src="${pageContext.request.contextPath}/payment/images/gray.png" title="银灰主题"></a> <a href="#" onclick="changeThemeFun('ui-sunny');"><img src="${pageContext.request.contextPath}/payment/images/sunny.png" title="阳光主题"></a> <a href="#" onclick="changeThemeFun('ui-pepper-grinder');"><img src="${pageContext.request.contextPath}/payment/images/paper.png" title="纸质主题"></a> <a href="#" onclick="changeThemeFun('metro-green');"><img src="${pageContext.request.contextPath}/payment/images/green.png" title="绿色主题"></a> <a href="#" onclick="changeThemeFun('metro-orange');"><img src="${pageContext.request.contextPath}/payment/images/orange.png" title="橙色主题"></a> <a href="#"
				onclick="changeThemeFun('metro-red');"><img src="${pageContext.request.contextPath}/payment/images/red.png" title="红色主题"></a>
		</div>
	</div>
	<div data-options="region:'south',split:false" style="text-align:center;height:25px;">中联联合投资控股集团有限公司版权所有©京ICP证：010026 海淀公安局网络备案编号：京公网安备 11010802010990 &nbsp;&nbsp;&nbsp;电信与信息服务业务经营许可证 140335</div>
	<!-- 	<div data-options="region:'east',split:true" title="East" style="width:100px;padding:10px;"> -->
	</div>
	<div data-options="region:'west',split:true" title="<div style='width:100%;font-size:14px;'>导航菜单</div>" style="width:12%;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="业务处理" data-options="selected:true" style="padding:10px;font-size:12px;">
				<ul class="tree">
					<li><button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bankmgt'" onclick="setBankMgtPanel()">银行信息管理</button></li>
					<li><button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-acctmgt'" onclick="setAcctMgtPanel()">账户信息管理</button></li>
					<li><button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-rgtzmgt'" onclick="setManulAjustPanel()">人工调整管理</button></li>
				</ul>
			</div>
			<div title="报表分析" style="padding:10px;">
				<ul class="tree">
					<li><button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fundtransfer'" onclick="setPayTransferPanel()">业务转账明细</button></li>
					<li><button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-msgsend'" onclick="setMessagePanel()">报文发送明细</button></li>
					<li><button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-acctbalance'" onclick="setAccountBalancePanel()">账号余额查询</button></li>
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'<div style=width:100%;font-size:14px;>支付平台</div>',iconCls:'icon-ok'">
		<div id="centerWorkArea" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title="首  页" data-options="href:'${pageContext.request.contextPath}/payment/js/homePage.jsp'" style="background-image:url('${pageContext.request.contextPath}/payment/images/backgroud.jpg');"></div>
		</div>
	</div>


</body>
</html>