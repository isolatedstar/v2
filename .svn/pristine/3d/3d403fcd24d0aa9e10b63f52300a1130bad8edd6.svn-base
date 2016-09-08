<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@ include file="msgSendWin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="easyui-layout" border="false" fit="true">
		<div data-options="region:'center'" style="padding:2px">
			<div class="easyui-layout" id="subWrap" fit="true" style="width:100%;height:100%;">
				<div region="north" border="true" title="查询条件" split="true" style="height:20%;">
					<form id="queryMsgSendForm" class="easyui-form" method="post" data-options="novalidate:false">
						<table id="msgSendConTable" cellpadding="5" style="display: none;position:absolute;left:20px;top:45px;">
							<tr>
								<td>银行账号:</td>
								<td><input id="mcBankAcct" class="easyui-validatebox textbox" data-options="prompt:'请输入16-19位银行账号',validType:'length[16,19]'" style="width:200px"></td>
								<td>报文类型:</td>
								<td><input id="cMsgType" class="easyui-combobox" editable="false" value="0" data-options=" url:'../utilController/getDataDictByCode.do?code=msgType&type=0',method:'get',prompt:'请选择账号类型',valueField:'itemCode',textField:'itemName'" style="width:200px"></td>
								<td>报文状态:</td>
								<td><input id="mcStatus" class="easyui-combobox" editable="false" value="0" data-options=" url:'../utilController/getDataDictByCode.do?code=msgStatus&type=0',method:'get',prompt:'请选择账号状态',valueField:'itemCode',textField:'itemName'" style="width:200px"></td>
							</tr>
							<tr>
								<td>报文流水号:</td>
								<td><input id="cMsgFlowId" class="easyui-validatebox textbox" data-options="prompt:'请输入6-19位银行账号',validType:'length[6,19]'" style="width:200px"></td>
								<td>报文发送方:</td>
								<td><input id="cMsgSender" class="easyui-combobox" editable="false" value="全部" data-options=" url:'../utilController/getDataDictByCode.do?code=msgFrom&type=0',method:'get',prompt:'请选择所属主户',valueField:'itemCode',textField:'itemName'" style="width:200px"></td>
								<td>报文接收方:</td>
								<td><input id="cMsgRecevicer" class="easyui-combotree" value="0" data-options="url:'../bankMgtController/getBankTree.do',method:'get',prompt:'请选择所属银行'" style="width:200px;"></td>
								<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="queryMsgSendByCon()" iconcls="icon-pay-serach">查 询</a></td>
							</tr>
						</table>
					</form>
				</div>
				<div border="false" region="center" style="height:100%;">
					<table id="msgSendTable"></table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>