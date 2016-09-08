<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/acctBalance.js"></script>
<meta charset="UTF-8">
</head>
<body>

	<div id="addAccountBalanceWindow" class="easyui-window" title="选择公司账号" closed="true" modal="true" style="width: 450px; height: 340px;">
	<form id="addAccountBalanceForm" class="easyui-form" method="post" data-options="novalidate:true">
		<table cellpadding="5" style="margin:auto;">
			<tr>
				<td>所属银行:</td>
				<td><input id="bankIdBalance" class="easyui-combotree" style="width:150px" data-options="url:'../bankMgtController/getBankTree.do',method:'get'"></td>
			</tr>
			<tr>
				<td>公司名称:</td>
				<td><input id="acctNameBalance" class="easyui-validatebox textbox" style="width:150px"></td>
			</tr>
		</table>
	</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
        <a href="#" class="easyui-linkbutton" onclick="addAccountBalance()" iconcls="icon-pay-serach">查询</a> 
        </div><br>
        <div id="tableAccountBala">
        
        </div>
	</div>
	<div id="propertyGridBalanceWindow" class="easyui-window"  closed="true" modal="true" title="余额信息详细窗口" >
		<form id="propertyGridBalanceForm" class="easyui-form">
			<table id="propertyGridBalanceRow"  style="margin:auto;" style="margin: 0.1em 0.1em 0.1em 0.1em;"></table>
		</form>
	</div>
</body>
</html>