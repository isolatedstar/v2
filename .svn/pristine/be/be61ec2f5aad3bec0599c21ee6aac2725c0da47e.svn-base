<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@ include file="acctBalanceWin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<div class="easyui-layout" fit="true">
		<div region="north" border="true" title="余额明细查询条件" split="true" style="height:177px">
			<table>
						<tr>
							<td><div id="toolbar">
									划出方:<input id="accountOutBalance" class="easyui-textbox" class="easyui-validatebox textbox" data-options="prompt:'请输入16-19位银行账号',validType:'length[16,19]'"> 
									 <a href="#" class="easyui-linkbutton" onclick="showAddAcctBalanceWin();">...</a> <input type="checkbox" id="relationBalance" style="vertical-align:middle"><label style="vertical-align:middle" >带附属户 </label>
									<a href="#" class="easyui-linkbutton" onclick="addBalanceAccount();" iconcls="icon-pay-add">添  加</a> <a href="#" class="easyui-linkbutton" onclick="delBalanceAccount();" iconcls="icon-pay-delete">删  除</a><a href="#" class="easyui-linkbutton" onclick="subFormBalance();" style="align:right" iconcls="icon-pay-serach">查 询</a>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div style="height:107px">
									<table id="accountTableSelect"></table>
								</div>
							</td>
						</tr>
					</table>
		</div>
		<div id="acctTableBa" border="false" region="center" style="height:107px">
			<table id="tableDetailBalance"></table>
		</div>
	</div>

	<div id="showOrHide" class="easyui-menu" style="width:150px;">
		---选择隐藏列---</br>
		<input type="checkbox" id="companyName" name="checkOptionBla"/> 开户公司名称</br>
		<input type="checkbox" id="bankName"  name="checkOptionBla"/> 开户行名称</br>
		<input type="checkbox" id="amtAccount"  name="checkOptionBla"/> 银行账号</br>
		<input type="checkbox" id="bankFreezeAmt"  name="checkOptionBla"/> 银行冻结金额</br>
		<input type="checkbox" id="freezeAmt"  name="checkOptionBla"/> 支付平台的冻结</br>
		<input type="checkbox" id="bankUsableAmt"  name="checkOptionBla"/> 账号可用余额</br>
		<input type="checkbox" id="usableAmt"  name="checkOptionBla"/> 支付账号可用额</br>
		<input type="checkbox" id="bankAmt"  name="checkOptionBla"/> 账号余额</br>
		<input type="checkbox" id="amt"  name="checkOptionBla"/> 支付账号余额</br>
		<div onclick="showOrHideSelectOption();" iconcls="icon-pay-serach" >确定</div>
	</div>
</body>
</html>