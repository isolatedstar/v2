<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ include file="head.jsp"%>
	<%@ include file="manulAjustWin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>AJAX Content</title>
</head>
<body>
	<div class="easyui-layout" border="false" fit="true" style="width:100%;height:100%;" data-options="region:'center'" >
		<!-- 		<div id="acctTree" class="easyui-tree" data-options="region:'west',split:true" style="width:16%;padding:2px"></div> -->
			<div region="north" border="true" title="查询条件" split="true" style="height:30%;">
				<table id="manulSearchTable" cellpadding="5" style="display: none;position:absolute;left:20px;top:45px;height:100%">
					<tr>
						<td>操作员:</td>
						<td><input id="operator" class="easyui-textbox" data-options="prompt:''" style="width:200px;"></td>
						<td>用户名:</td>
						<td><input id="userName" class="easyui-textbox" data-options="prompt:''" style="width:200px;"></td>
						<td>交易金额:</td>
						<td><input id="tranAmt" class="easyui-textbox" data-options="prompt:''" style="width:200px;"></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>收款附属帐号:</td>
						<td><input id="recAccNo" class="easyui-textbox" data-options="prompt:'请输入16-19位银行账号'" style="width:200px;"></td>
						<td>客户流水号:</td>
						<td><input id="flowID" class="easyui-textbox" data-options="prompt:''" style="width:200px;"></td>
						<td>付款账号:</td>
						<td><input id="payAccNo" class="easyui-textbox" data-options="prompt:'请输入16-19位银行账号'" style="width:200px;"></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="queryManulByCon()" iconcls="icon-pay-serach">查 询</a></td>
					</tr>
				</table>
			</div>
			<div border="false" region="center"  style="height:70%;">
				<table title="报文队列" id="manulTreeGridTable" class="easyui-treegrid" style="width:100%;position:absolute;left:20px;top:25%;height:100%">
					<!--thead frozen="true">
						<tr>
							<th field="name" width="24%">name</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th field="code" width="7%" align="center">code</th>
							<th field="accountNo" width="11%" align="center">accountNo</th>
							<th field="payAccNo" width="11%" align="center">payAccNo</th>
							<th field="tranAmt" width="8%" align="center">tranAmt</th>
							<th field="recvAccNo" width="11%" align="center">recvAccNo</th>
							<th field="userName" width="10%" align="center">userName</th>
							<th field="recvAccNm" width="10%" align="center">recvAccNm</th>
							<th field="operator" width="8%" align="center">操作</th>
						</tr>
					</thead-->
					
				</table>
			</div>
	</div>
</body>
</html>