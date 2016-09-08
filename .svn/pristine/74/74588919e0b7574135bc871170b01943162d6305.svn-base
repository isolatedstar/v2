<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@ include file="fundTransferWin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<div class="easyui-layout" fit="true">
		<div region="north" border="true" title="转账明细查询条件" split="true" style="height:177px">
			<div class="easyui-layout" fit="true">
				<div region="west" border="false" style="width:40%;">
					<table>
						<tr>
							<td><div id="toolbar">
									划出方:<input id="accountOut" class="easyui-textbox" class="easyui-validatebox textbox" data-options="prompt:'请输入16-19位银行账号',validType:'length[16,19]'"> 
									 <a href="#" class="easyui-linkbutton" onclick="showAddAcctTransferWin();">...</a> <input type="checkbox" id="relation" style="vertical-align:middle"><label style="vertical-align:middle" >带附属户        &nbsp; &nbsp;划入方:</label>
									<input id="accountIn" class="easyui-textbox" class="easyui-validatebox textbox" data-options="prompt:'请输入16-19位银行账号',validType:'length[16,19]'"> 
									 <a href="#" class="easyui-linkbutton" id="inSelectButton" onclick="showAddAcctInTransferWin();">...</a>
									<a href="#" class="easyui-linkbutton" onclick="addTransferAccount();" iconcls="icon-pay-add">添  加</a> <a href="#" class="easyui-linkbutton" onclick="delTransferAccount();" iconcls="icon-pay-delete">删  除</a>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div style="height:107px">
									<table id="step2AccountTable"></table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div region="east" border="false" style="width:60%;">
					<table>
					<tr><td><label>网银请求：
					<a href="#" class="easyui-linkbutton" onclick="wangyin();">
					</label><tr>
						<tr>
							<td><label>开始时间：</label>
							 <input type="text" id="startTime" name="startTime" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:150px"> 
							 <label>结束时间：</label> 
							 <input type="text" id="endTime" name="endTime" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:150px"> 
							 <label>接口类型：</label>
							 <input id="interfaceType" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=interfaceType&type=0',method:'get',prompt:'请选择接口类型',valueField:'itemCode',textField:'itemName'" style="width:150px">
							 <label>操作人员： </label>
							 <input type="text" id="operator" class="easyui-textbox" value="" name="operator" style="width:150px" /> <a href="#" class="easyui-linkbutton" onclick="subForm();" iconcls="icon-pay-serach">查询</a></td>
						</tr>
						<tr>
							<td><label>业务类型：</label>
							<input id="businessType" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=busType&type=0',method:'get',prompt:'请选择业务类型',valueField:'itemCode',textField:'itemName'" style="width:150px">
							 <label>报文来源：</label>
							 <input id="businessFrom" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=msgFrom&type=0',method:'get',prompt:'请选择业务来源',valueField:'itemCode',textField:'itemName'" style="width:150px">
							<label>划转状态：</label>
							<input id="transferStatus" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=trasferStatus&type=0',method:'get',prompt:'请选择划转状态',valueField:'itemCode',textField:'itemName'" style="width:150px">
							 <label>动作类型：</label> 
							 <input id="actionType" class="easyui-combobox" editable="false" data-options=" url:'../utilController/getDataDictByCode.do?code=actionType&type=0',method:'get',prompt:'请选择动作类型',valueField:'itemCode',textField:'itemName'" style="width:150px">	 
							<a href="#" class="easyui-linkbutton" iconcls="icon-print">导出</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="acctTablead" border="false" region="center" style="height:100%;">
			<table id="tableDetail"></table>
		</div>
	</div>
	<div id="showOrHideTransfer" class="easyui-menu" style="width:150px;">
		---选择隐藏列---</br>
		<input type="checkbox" id="bankFlowId" name="checkOption"/>  编号</br>
		<input type="checkbox" id="msgSource" name="checkOption"/>  报文来源</br>
		<input type="checkbox" id="tradeTimeStr" name="checkOption"/>  交易时间</br>
		<input type="checkbox" id="operator" name="checkOption"/>  操作人</br>
		<input type="checkbox" id="outBankName" name="checkOption"/>  资金划出银行</br>
		<input type="checkbox" id="outMasterAcct" name="checkOption"/>  所属主户（出）</br>
		<input type="checkbox" id="outAcct" name="checkOption"/>  资金划出账号</br>
		<input type="checkbox" id="inBankName" name="checkOption"/>  资金划入银行</br>
		<input type="checkbox" id="inMasterAcct" name="checkOption"/>  所属主户（入）</br>
		<input type="checkbox" id="inAcct" name="checkOption"/>  资金划入账号</br>
		<input type="checkbox" id="businessTypeName" name="checkOption"/>  业务类型</br>
		<input type="checkbox" id="interfaceStatusName" name="checkOption"/>  接口类型</br>
		<input type="checkbox" id="workTypeName" name="checkOption"/>  动作类型</br>
		<input type="checkbox" id="transferAmt" name="checkOption"/>  资金划转金额</br>
		<input type="checkbox" id="transferStatusName" name="checkOption"/>  资金划转状态</br>
		<input type="checkbox" id="transferContext" name="checkOption"/>  交易说明</br>
		<div onclick="showOrHideTransferSelect();" iconcls="icon-pay-serach" >确定</div>
	</div>

</body>
</html>