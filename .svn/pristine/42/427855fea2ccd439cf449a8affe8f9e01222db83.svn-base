<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@ include file="acctMgtWin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<div class="easyui-layout" border="false" fit="true">
		<!-- 		<div id="acctTree" class="easyui-tree" data-options="region:'west',split:true" style="width:16%;padding:2px"></div> -->
		<div data-options="region:'center'" style="padding:2px">
			<div class="easyui-layout" id="subWrap" fit="true" style="width:100%;height:100%;">
				<div region="north" border="true" title="查询条件" split="true" style="height:20%;">
					<form id="queryAcctForm" class="easyui-form" method="post" data-options="novalidate:false">
						<table id="conTable" cellpadding="5" style="display: none;position:absolute;left:20px;top:45px;">
							<tr>
								<td>银行账号:</td>
								<td><input id="cBankAcct" class="easyui-validatebox textbox" data-options="prompt:'请输入16-19位银行账号',validType:'length[16,19]'" style="width:200px"></td>
								<td>账户名称:</td>
								<td><input id="cAcctName" class="easyui-validatebox textbox" data-options="prompt:'请输入2字以上账户名称',validType:'length[2,32]'" style="width:200px"></td>
								<td>账户类型:</td>
								<td><input id="cAcctTypeId" class="easyui-combobox" editable="false" value="0" data-options=" url:'../utilController/getDataDictByCode.do?code=acctType&type=0',method:'get',prompt:'请选择账号类型',valueField:'itemCode',textField:'itemName'" style="width:200px"></td>
								<td>账户状态:</td>
								<td><input id="cStatus" class="easyui-combobox" editable="false" value="0" data-options=" url:'../utilController/getDataDictByCode.do?code=acctStatus&type=0',method:'get',prompt:'请选择账号状态',valueField:'itemCode',textField:'itemName'" style="width:200px"></td>
							</tr>
							<tr>
								<td>所属银行:</td>
								<td><input id="cBankId" class="easyui-combotree" value="0" data-options="url:'../bankMgtController/getBankTree.do',method:'get',prompt:'请选择所属银行'" style="width:200px;"></td>
								<td>所属主户:</td>
								<td><input id="cMasterAcct" class="easyui-combobox" editable="false" value="全部" data-options=" url:'../acctMgtController/queryMasterAcct.do?type=0',method:'get',prompt:'请选择所属主户',valueField:'bankAcct',textField:'bankAcct'" style="width:200px"></td>
								<td>所属集团:</td>
								<td><input id="cOrgGroupName" class="easyui-validatebox textbox" editable="false" style="width:180px"><a href="javascript:void(0)" class="easyui-linkbutton" onclick="showOrgGroupWin()" style="width:20px">...</a></td>
								<input id="cOrgGroupId" type="hidden" >
								<td>默认账户:</td>
								<td><input id="cIsDefault" class="easyui-combobox" editable="false" value="0" data-options=" url:'../utilController/getDataDictByCode.do?code=yesNo&type=0',method:'get',prompt:'请选择是否默认',valueField:'itemCode',textField:'itemName'" style="width:200px"></td>
								<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="queryAcctByCon()" iconcls="icon-pay-serach">查 询</a></td>
							</tr>
						</table>
					</form>
				</div>
				<div border="false" region="center" style="height:100%;">
					<table id="acctTable"></table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>