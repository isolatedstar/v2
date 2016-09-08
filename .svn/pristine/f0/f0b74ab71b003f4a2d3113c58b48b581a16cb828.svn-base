<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/manulAjust.js"></script>
</head>
<body>
	<div id="addManulWindow" class="easyui-window" title="添加" closed="true" modal="true" style="display: none;width: 950px; height: 500px;">
	<form id="addManulForm" class="easyui-form" method="post" data-options="novalidate:true">
		<input type="button" value="添加action" onclick="addManulData()">
		<table cellpadding="5" style="margin:auto;width:100%;">
			<tr>
				<td>integrity:</td>
				<td>
					<input name="integrity" class="easyui-validatebox" type="radio" value="all" checked="checked">all
	       			<input name="integrity" class="easyui-validatebox" type="radio" value="part">part
				</td>
				<td>操作员:</td>
				<td><input id="addOperator" class="easyui-validatebox textbox"  value="congshuai" style="width:100px"></td>
				<td>客户流水号:</td>
				<td><input id="addFlowID" class="easyui-validatebox textbox" value="" data-options="prompt:'请输入客户流水号'" style="width:100px"></td>
			</tr>
			<tr>
				<td>商城回调路径:</td>
				<td><input name="mallUrl" class="easyui-validatebox textbox" type="text" value="http://shangcheng" style="width:100px" data-options="prompt:'请输入商城回调路径'" ></td>
				<td>保理回调路径:</td>
				<td><input name="factoringUrl" class="easyui-validatebox textbox" type="text" value="http://baoli" style="width:100px" data-options="prompt:'请输入保理回调路径'" ></td>
				<td>发送方:</td>
				<td>
					<input name="sender" class="easyui-validatebox" type="radio" value="1" checked="checked">保理
	       			<input name="sender" class="easyui-validatebox" type="radio" value="2">商城
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<table id="addManulAjustTable" cellpadding="5" style="margin:auto; width:100%; height:60%">
						<tr>
							<td style="width:8%;">code</td>
							<td style="width:8%;">用户名</td>
							<td style="width:8%;">clientId</td>
							<td style="width:12%;">付款帐号</td>
							<td style="width:12%;">付款附属帐号</td>
							<td style="width:12%;">收款附属帐号</td>
							<td style="width:12%;">收款附属帐号名称</td>
							<td style="width:8%;">交易金额</td>
							<td style="width:5%;">freezeNo</td>
							<td style="width:4%;">tranFlag</td>
							<td style="width:4%;">转账类型</td>
							<td style="width:5%;">摘要</td>
							<td style="width:4%;">操作</td>
						</tr>
						<tr data-id='0' class="datatr">
							<td><input name="code" class="easyui-validatebox" value="ZLMDTFER" style="width:100%;"></td>
							<td><input name="userName" class="easyui-validatebox"  value="zhonglianlianhe" style="width:100%;" ></td>
							<td><input name="clientID" class="easyui-validatebox"  value="1234567890000000" style="width:100%;" ></td>
							<td><input name="accountNo" class="easyui-validatebox" value="8110701012900005992"  style="width:100%;" data-options="prompt:'请输入16-19位银行账号'"></td>
							<td><input name="payAccNo" class="easyui-validatebox"  value="3110710000781001030" style="width:100%;" ></td>
							<td><input name="recvAccNo" class="easyui-validatebox"  value="3110710000780000006" style="width:100%;" ></td>
							<td><input name="recvAccNm" class="easyui-validatebox" value="北京供应商"  style="width:100%;" ></td>
							<td><input name="tranAmt" class="easyui-validatebox" value="100"  style="width:100%;" ></td>
							<td><input name="freezeNo" class="easyui-validatebox" value="0"  style="width:100%;" ></td>
							<td><input name="tranFlag" class="easyui-validatebox" value="1"  style="width:100%;" ></td>
							<td><input name="transType" class="easyui-validatebox" value="BF"  style="width:100%;" ></td>
							<td><input name=" memo" class="easyui-validatebox" value=""  style="width:100%;" ></td>
							<td class="remove">--</td>
						</tr>
						<tr data-id='1' class="datatr">
							<td><input name="code" class="easyui-validatebox" value="ZLMDTFER" style="width:100%;"></td>
							<td><input name="userName" class="easyui-validatebox"  value="zhonglianlianhe" style="width:100%;" ></td>
							<td><input name="clientID" class="easyui-validatebox"  value="1234567891234567" style="width:100%;" ></td>
							<td><input name="accountNo" class="easyui-validatebox" value="8110701012900005992"  style="width:100%;" data-options="prompt:'请输入16-19位银行账号'"></td>
							<td><input name="payAccNo" class="easyui-validatebox"  value="3110710000780000006" style="width:100%;" ></td>
							<td><input name="recvAccNo" class="easyui-validatebox"  value="3110710000781001030" style="width:100%;" ></td>
							<td><input name="recvAccNm" class="easyui-validatebox" value="出入金"  style="width:100%;"></td>
							<td><input name="tranAmt" class="easyui-validatebox" value="5"  style="width:100%;" ></td>
							<td><input name="freezeNo" class="easyui-validatebox" value="0"  style="width:100%;" ></td>
							<td><input name="tranFlag" class="easyui-validatebox" value="0"  style="width:100%;" ></td>
							<td><input name="transType" class="easyui-validatebox" value="BF"  style="width:100%;" ></td>
							<td><input name=" memo" class="easyui-validatebox" value=""  style="width:100%;" ></td>
							<td class="remove">--</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addManul()" iconcls="icon-pay-save">保  存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#addManulWindow').dialog('close')" iconcls="icon-pay-cancel">取  消</a>
        </div> 
	</div>
	
	<div id="editManulWindow" class="easyui-window" title="修改账户" closed="true" modal="true" style="display: none;width: 650px; height: 500px;">
	<form id="editAcctForm" class="easyui-form" method="post" data-options="novalidate:true">
		<table cellpadding="5" style="margin:auto;">
		<tr>
			<td>integrity:</td>
			<td>
			<input name="uIntegrity" class="easyui-validatebox" type="radio" value="all" checked="checked">all
			<input name="uIntegrity" class="easyui-validatebox" type="radio" value="part">part
			</td>
		</tr>
		<tr>
			<td>操作员:</td>
			<td><input id="uOperator" class="easyui-validatebox textbox"  value="" style="width:200px"></td>
		</tr>
		<tr>
			<td>客户流水号:</td>
			<td><input id="uFlowID" class="easyui-validatebox textbox" value="" data-options="prompt:'请输入客户流水号'" style="width:200px"></td>
		</tr>
		<tr>
			<td>code:</td>
			<td><input name="uCode" class="easyui-validatebox" editable="false" value="ZLFONDIN" readonly="true"></td>
		</tr>
		<tr>
			<td>用户名:</td>
			<td><input name="uUserName" class="easyui-validatebox"  value="" style="width:200px" ></td>
		</tr>
		<tr>
			<td>付款帐号:</td>
			<td><input name="uAccountNo" class="easyui-validatebox" value=""  style="width:200px" data-options="prompt:'请输入16-19位银行账号'"></td>
		</tr>
		<tr>
			<td>收款附属帐号:</td>
			<td><input name="uSubAccNo" class="easyui-validatebox"  value="" style="width:200px" ></td>
		</tr>
		<tr>
			<td>收款附属帐号名称:</td>
			<td><input name="uSubAccNm" class="easyui-validatebox" value=""  style="width:200px" ></td>
		</tr>
		<tr>
			<td>交易金额:</td>
			<td><input name="uTranAmt" class="easyui-validatebox" value=""  style="width:200px" ></td>
		</tr>
		<tr>
			<td>摘要:</td>
			<td><input name="uMemo" class="easyui-validatebox" value=""  style="width:200px" ></td>
		</tr>
		</table>
		</form>
		<div id="uDlg-buttons"  style="width: 150px; float: right"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="editManul()" iconcls="icon-pay-save">保  存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#editManulWindow').dialog('close')" iconcls="icon-pay-cancel">取  消</a>
        </div> 
	</div>
</body>
</html>