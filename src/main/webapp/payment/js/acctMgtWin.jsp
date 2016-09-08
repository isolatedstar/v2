<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/payment/js/acctMgt.js"></script>
</head>
<body>




	<div id="addAcctWindow" class="easyui-window" title="添加账户" closed="true" modal="true" style="display: none;width: 400px; height: 360px;">
	<form id="addAcctForm" class="easyui-form" method="post" data-options="novalidate:true">
		<table cellpadding="5" style="margin:auto;">
			<tr>
				<td>所属银行:</td>
				<td><input id="bankId" class="easyui-combotree" data-options="url:'../bankMgtController/getBankTree.do',method:'get',required:true,prompt:'请选择所属银行'" style="width:200px;"></td>
			</tr>
			<tr>
				<td>银行账号:</td>
				<td><input id="bankAcct" class="easyui-validatebox textbox" data-options="prompt:'请输入16-19位银行账号',required:true,validType:'length[16,19]'" style="width:200px"></td>
			</tr>
			<tr>
				<td>账户名称:</td>
				<td><input id="acctName" class="easyui-validatebox textbox" data-options="prompt:'请输入2字以上账户名称',required:true,validType:'length[2,32]'" style="width:200px"></td>
			</tr>
			<tr>
				<td>账户类型:</td>
				<td><input id="acctTypeId" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=acctType',method:'get',required:true,prompt:'请选择账号类型',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
			</tr>
			<tr>
				<td>账户状态:</td>
				<td><input id="status" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=acctStatus',method:'get',required:true,prompt:'请选择账号状态',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
			</tr>
			<tr>
				<td>所属主户:</td>
				<td><input id="masterAcct" class="easyui-combobox" editable="false" style="width:200px" ></td>
			</tr>
			<tr>
				<td>所属集团:</td>
				<td>
				<input id="orgGroupName" class="easyui-validatebox textbox" style="width:180px"  data-options="prompt:'请选择所属集团',required:true"> 
				<a href="#" class="easyui-linkbutton" style="width:20px" id="groupSelectButton" onclick="showAddOrgGroupIdWin();">...</a>
				<input id="orgGroupId" type="hidden"> 
				</td>
			</tr>
			<tr>
				<td>默认账户:</td>	
				<td><input id="isDefault" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=yesNo',method:'get',required:true,prompt:'请选择是否默认',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
			</tr>
		</table>
		</form>
		<div id="dlg-buttons" float="right;" style="position:absolute; right:15px; bottom:15px; width:180px;"> 
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reset()" iconcls="icon-pay-reset">重  置</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addAcct()" iconcls="icon-pay-save">保  存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#addAcctWindow').dialog('close')" iconcls="icon-pay-cancel">取  消</a> 
        </div> 
	</div>
	
	<div id="editAcctWindow" class="easyui-window" title="修改账户" closed="true" modal="true" style="display: none; width: 400px; height: 360px;">
	<form id="editAcctForm" class="easyui-form" method="post" data-options="novalidate:true">
		<table cellpadding="5" style="margin:auto;">
			<tr>
				<td>所属银行:</td>
				<td><input id="uBankId" class="easyui-combotree" data-options="url:'../bankMgtController/getBankTree.do',method:'get',required:true,prompt:'请选择所属银行'" style="width:200px;"></td>
			</tr>
			<tr>
				<td>银行账号:</td>
				<td><input id="uBankAcct" class="easyui-validatebox textbox" readonly="true" style="width:200px"></td>
			</tr>
			<tr>
				<td>账户名称:</td>
				<td><input id="uAcctName" class="easyui-validatebox textbox" data-options="prompt:'请输入2字以上账户名称',validType:'length[2,32]'" style="width:200px"></td>
			</tr>
			<tr>
				<td>账户类型:</td>
				<td><input id="uAcctTypeId" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=acctType',method:'get',required:true,prompt:'请选择账号类型',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
			</tr>
			<tr>
				<td>账户状态:</td>
				<td><input id="uStatus" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=acctStatus',method:'get',required:true,prompt:'请选择账号状态',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
			</tr>
			<tr>
				<td>所属主户:</td>
				<td><input id="uMasterAcct" class="easyui-combobox" editable="false" style="width:200px" ></td>
			</tr>
			<tr>
				<td>所属集团:</td>
				<td>
				<input id="uOrgGroupName" class="easyui-validatebox textbox" style="width:180px"  data-options="prompt:'请选择所属集团',required:true"> 
				<a href="#" class="easyui-linkbutton" style="width:20px" id="ugroupSelectButton" onclick="showEditOrgGroupIdWin();">...</a>
				<input id="uOrgGroupId" type="hidden"> 
			</tr>
			<tr>
				<td>默认账户:</td>
				<td><input id="uIsDefault" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=yesNo',method:'get',required:true,prompt:'请选择是否默认',valueField:'itemCode',textField:'itemName'" style="width:200px" ></td>
			</tr>
			
		</table>
		</form>
		<div id="uDlg-buttons"  float="right;" style="position:absolute; right:15px; bottom:15px; width:120px;"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="editAcct()" iconcls="icon-pay-save">保  存</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#editAcctWindow').dialog('close')" iconcls="icon-pay-cancel">取  消</a> 
        </div> 
	</div>
	<div id="addOrgGroupIdWindow" class="easyui-window" title="选择集团" closed="true" modal="true" style="width: 450px; height: 340px;">
		<form id="addOrgGroupIdForm" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>集团名称:</td>
					<td><input id="orgGroupNamePara" class="easyui-validatebox textbox" style="width:150px"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
        <a href="#" class="easyui-linkbutton" onclick="queryOrgGroupIdInfo()" iconcls="icon-pay-serach" iconcls="icon-pay-serach">查询</a> 
        </div><br>
        <div id="tableOrgGroupId">
        </div>
	</div>
	<div id="editOrgGroupIdWindow" class="easyui-window" title="选择集团" closed="true" modal="true" style="width: 450px; height: 340px;">
		<form id="editOrgGroupIdForm" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>集团名称:</td>
					<td><input id="orgGroupNameParaEdit" class="easyui-validatebox textbox" style="width:150px"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
        <a href="#" class="easyui-linkbutton" onclick="queryOrgGroupIdEditInfo()" iconcls="icon-pay-serach" iconcls="icon-pay-serach">查询</a> 
        </div><br>
        <div id="tableOrgGroupIdEdit">
        </div>
	</div>
	<div id="orgGroupIdBottomWindow" class="easyui-window" title="选择集团" closed="true" modal="true" style="width: 450px; height: 340px;">
		<form id="orgGroupIdFormBottom" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5" style="margin:auto;">
				<tr>
					<td>集团名称:</td>
					<td><input id="orgGroupNameParaBottom" class="easyui-validatebox textbox" style="width:150px"></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons"  style="width: 150px; float: right"> 
        <a href="#" class="easyui-linkbutton" onclick="queryOrgGroupIdInfoBottom()" iconcls="icon-pay-serach" iconcls="icon-pay-serach">查询</a> 
        </div><br>
        <div id="tableOrgGroupIdBottom">
        </div>
	</div>
	
</body>
</html>