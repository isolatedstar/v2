<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/bankMgt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/customValidate.js"></script>
</head>
<body>

	<div id="addBankWin" class="easyui-window" title="添加银行" closed="true" modal="true" style="width: 400px; height: 300px;">
		<form id="bankaddform" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>银行名称:</td>
					<td><input id="addtext" name="addtext" class="easyui-validatebox textbox" data-options="prompt:'请输入银行名称',required:true,validType:'length[3,20]'"></td>
				</tr>

				<tr hidden="hidden">
					<td>父id:</td>
					<td><input id="addParentId" class="easyui-validatebox textbox" data-options="prompt:'Enter your phone number.',required:true"></td>
				</tr>
				<tr hidden="hidden">
					<td>id:</td>
					<td><input id="addBankId" name="bankId" class="easyui-validatebox textbox" data-options="prompt:'Enter your phone number.',required:true"></td>
				</tr>
			</table>
		</form>

		<div id="dlg-buttons" style="width: 150px; left:200px;align:right; top:100px; position:relative;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveBank()" iconcls="icon-pay-save">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeout()" iconcls="icon-cancel">取消</a>
		</div>
	</div>


	<div id="updateBankWin" class="easyui-window" title="修改银行" closed="true" modal="true" style="width: 400px; height: 300px; position:relative;">
		<form id="bankupdateform" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>银行名称:</td>
					<td><input id="updateBankName" name="updateBankId" class="easyui-validatebox textbox" data-options="prompt:'请输入银行名称',required:true,validType:'length[3,20]'"></td>
				</tr>

				<tr hidden="hidden">
					<td>父id:</td>
					<td><input id="updateParentId" name="updateParentId" class="easyui-validatebox textbox" data-options="prompt:'Enter your phone number.',required:true"></td>
				</tr>
				<tr hidden="hidden">
					<td>id:</td>
					<td><input id="updateBankId" name="updatebankId" class="easyui-validatebox textbox" data-options="prompt:'Enter your phone number.',required:true"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons" style="width: 150px; left:200px;align:right; top:100px; position:relative;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateBank()" iconcls="icon-pay-save">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeout()" iconcls="icon-cancel">取消</a>
		</div>
	</div>

	<div id="interface" class="easyui-window" title="添加接口" closed="true" modal="true" style="width: 400px; height: 200px;">
		<form id="interfaceform" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr hidden="hidden">
					<td>接口编号:</td>
					<td><input id="interfaceName" name="interfaceName" readonly="readonly" class="easyui-validatebox textbox" data-options="prompt:'接口编号不可输入'" style="width:200px"></td>
				</tr>
				<tr>
					<td>接口类型:</td>
					<td><input id="interfaceType" name="interfaceType" class="easyui-combobox" data-options="prompt:'请输入接口类型',required:true" style="width:198px"></td>
				</tr>
				<tr>
					<td>接口地址:</td>
					<td><input id="interfaceAddress" name="interfaceAddress" class="easyui-validatebox textbox" data-options="prompt:'请输入接口地址',required:true" style="width:200px"></td>
				</tr>
				<tr>
					<td>接口状态:</td>
					<td><input id="interfaceStatus" name="interfaceStatus" class="easyui-combobox" data-options="prompt:'请输入接口状态', required:true" style="width:198px"></td>
				</tr>
				<tr hidden="hidden">
					<td>id:</td>
					<td><input id="updateInterfaceBankId" name="updateInterfaceBankId" class="easyui-validatebox textbox" data-options="prompt:'Enter your phone number.'" style="width:200px"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons float=" right;" style="position:absolute; right:10px; bottom:15px; width:120px;">
			<a href="javascript:void(0)" id="saveInterface" class="easyui-linkbutton" onclick="saveInterface()" iconcls="icon-pay-save">保存</a> <a href="javascript:void(0)" id="updateInterface" class="easyui-linkbutton" onclick="updateInterface()" iconcls="icon-save">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeout()" iconcls="icon-cancel">取消</a>
		</div>
	</div>

	<div id="server" class="easyui-window" title="添加服务器" closed="true" modal="true" style="width: 400px; height: 240px;">
		<form id="serverform" class="easyui-form" method="post" data-options="novalidate:true">
			<input type="hidden" id="operate" value=""> <input type="hidden" id="previousName" value=""> <input type="hidden" id="previousServerType" value=""> <input type="hidden" id="previousServerAddress" value="">
			<table cellpadding="5" style="margin:auto;">
				<tr hidden="hidden">
					<td>服务器编号:</td>
					<td><input id="serverId" name="serverId" readonly="readonly" class="easyui-validatebox textbox" data-options="prompt:'服务器编号不可输入'" style="width:200px"></td>
				</tr>
				<tr>
					<td>服务器类型:</td>
					<td><input id="serverType" name="serverType" class="easyui-combobox" data-options="prompt:'请输入服务器类型',required:true" style="width:200px"></td>
				</tr>
				<tr>
					<td>服务器名称:</td>
					<td><input id="serverNamebig" name="serverNamebig" class="easyui-validatebox textbox" data-options="prompt:'请输入服务器名称',required:true,validType:'length[0,80]'" style="width:198px"></td>
				</tr>
				<tr>
					<td>服务器地址:</td>
					<td><input id="serverAddress" name="serverAddress" class="easyui-validatebox textbox" data-options="prompt:'请输入服务器地址',required:true,validType:'url'" style="width:198px"></td>
				</tr>
				<tr>
					<td>服务器状态:</td>
					<td><input id="serverStatus" name="serverStatus" class="easyui-combobox" data-options="prompt:'请输入服务器状态', required:true" style="width:200px"></td>
				</tr>
				<tr hidden="hidden">
					<td>锁定:</td>
					<td><input id="serverLock" name="serverLock" class="easyui-validatebox textbox" data-options="prompt:'Enter User Name.'"></td>
				</tr>

				<tr hidden="hidden">
					<td>id:</td>
					<td><input id="updateServerBankId" name="updatebankId" readonly="readonly" class="easyui-validatebox textbox" data-options="prompt:'Enter your phone number.'"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons" float="right;" style="position:absolute; right:10px; bottom:15px; width:120px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="saveServer" onclick="saveServer()" iconcls="icon-pay-save">保存</a> <a href="javascript:void(0)" id="updateServer" class="easyui-linkbutton" onclick="updateServer()" iconcls="icon-save">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeout()" iconcls="icon-cancel">取消</a>
		</div>
	</div>

	<div id="addRule" class="easyui-window" title="添加规则" closed="true" modal="true" style="width: 400px; height: 350px;">
		<form id="addRuleform" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">

				<tr>
					<td>银行卡类型:</td>
					<td><input id="acctType" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=bankCardType',method:'get',required:true,prompt:'请选择银行卡类型',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
				</tr>
				<tr>
					<td>父规则:</td>
					<td><input id="parentRuleId" class="easyui-combobox" data-options="prompt:'请选择父规则'" style="width:200px"></td>
				</tr>
				<tr>
					<td>银行卡位数:</td>
					<td><input id="acctLength" class="easyui-validatebox textbox" data-options="prompt:'请输入银行卡位数',required:true,validType:'range[1,19]'" style="width:200px"></td>
				</tr>
				<tr>
					<td>规则起始位置:</td>
					<td><input id="ruleStart" class="easyui-validatebox textbox" data-options="prompt:'请输入规则起始位置',required:true,validType:'range[1,19]'" style="width:200px"></td>
				<tr>
					<td>规则结束位置:</td>
					<td><input id="ruleEnd" class="easyui-validatebox textbox" data-options="prompt:'请输入规则结束位置',required:true,validType:'range[1,19]'" style="width:200px"></td>
				</tr>
				<tr>
					<td>规则内容:</td>
					<td><input id="ruleContext" class="easyui-validatebox textbox" data-options="prompt:'请输入规则内容',required:true" style="width:200px"></td>
				</tr>
				<tr>
					<td>状态:</td>
					<td><input id="ruleStatus" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=ruleStatus',method:'get',required:true,prompt:'请选择规则状态',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons" float="right" style="position:absolute; right:10px; bottom:15px; width:180px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="" onclick="LookRule()" iconcls="icon-pay-save">预览</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="saveRule" onclick="saveRule()" iconcls="icon-pay-save">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeout()" iconcls="icon-cancel">取消</a>
		</div>
	</div>
	
	<div id="updateRule" class="easyui-window" title="修改规则" closed="true" modal="true" style="width: 400px; height: 350px;">
		<form id="updateRuleform" class="easyui-form" method="post" data-options="novalidate:true">
				<table cellpadding="5" style="margin:auto;">

				<tr>
					<td>银行卡类型:</td>
					<td><input id="uAcctType" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=bankCardType',method:'get',required:true,prompt:'请选择银行卡类型',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
				</tr>
				<tr>
					<td>父规则:</td>
					<td><input id="uParentRuleId" class="easyui-combobox" data-options="prompt:'请选择父规则'" style="width:200px"></td>
				</tr>
				<tr>
					<td>银行卡位数:</td>
					<td><input id="uAcctLength" class="easyui-validatebox textbox" data-options="prompt:'请输入银行卡位数',required:true,validType:'range[1,19]'" style="width:200px"></td>
				</tr>
				<tr>
					<td>规则起始位置:</td>
					<td><input id="uRuleStart" class="easyui-validatebox textbox" data-options="prompt:'请输入规则起始位置',required:true,validType:'range[1,19]'" style="width:200px"></td>
				<tr>
					<td>规则结束位置:</td>
					<td><input id="uRuleEnd" class="easyui-validatebox textbox" data-options="prompt:'请输入规则结束位置',required:true,validType:'range[1,19]'" style="width:200px"></td>
				</tr>
				<tr>
					<td>规则内容:</td>
					<td><input id="uRuleContext" class="easyui-validatebox textbox" data-options="prompt:'请输入规则内容',required:true" style="width:200px"></td>
				</tr>
				<tr>
					<td>状态:</td>
					<td><input id="uRuleStatus" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=ruleStatus',method:'get',required:true,prompt:'请选择规则状态',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons float=" right;" style="position:absolute; right:10px; bottom:15px; width:180px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="" onclick="updateLookRule()" iconcls="icon-pay-save">预览</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateRule()" iconcls="icon-save">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeout()" iconcls="icon-cancel">取消</a>
		</div>
	</div>

</body>
</html>