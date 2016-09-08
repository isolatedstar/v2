<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/fundTransfer.js"></script> 
</head>
<body>

	<div id="addAccountWindow" class="easyui-window" title="选择公司账号" closed="true" modal="true" style="width: 450px; height: 340px;">
		<form id="addAccountForm" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>所属银行:</td>
					<td><input id="bankIdTransfer" class="easyui-combotree" style="width:150px" data-options="url:'../bankMgtController/getBankTree.do',method:'get'"></td>
				</tr>
				<tr>
					<td>公司名称:</td>
					<td><input id="acctName" class="easyui-validatebox textbox" style="width:150px"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
       		<a href="#" class="easyui-linkbutton" onclick="addAccountTransfer()" iconcls="icon-pay-serach">查询</a> 
        </div><br>
        <div id="tableAccount">
        </div>
	</div>

	<div id="addAccountInWindow" class="easyui-window" title="选择公司账号" closed="true" modal="true" style="width: 450px; height: 340px;">
		<form id="addAccountInForm" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>所属银行:</td>
					<td><input id="bankIdInTransfer" class="easyui-combotree" style="width:150px" data-options="url:'../bankMgtController/getBankTree.do',method:'get'"></td>
				</tr>
				<tr>
					<td>公司名称:</td>
					<td><input id="acctInName" class="easyui-validatebox textbox" style="width:150px"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
        <a href="#" class="easyui-linkbutton" onclick="addAccountInTransfer()" iconcls="icon-pay-serach" iconcls="icon-pay-serach">查询</a> 
        </div><br>
        <div id="tableAccountIn">
        </div>
	</div>

	<div id="propertyGridTransferWindow" class="easyui-window"  closed="true" modal="true" title="转账信息详细窗口" >
		<form id="propertyGridTransferForm" class="easyui-form" style="margin: 0.1em 0.1em 0.1em 0.1em;">
			<table id="propertyGridTransferRow"></table>
		</form>
	</div>
</body>
</html>