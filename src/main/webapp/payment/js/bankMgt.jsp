<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@ include file="bankMgtWin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>


	<div class="easyui-layout" border="false" fit="true">
		<div id="banktree" class="easyui-tree" data-options="region:'west',split:true" style="width:15%;padding:2px"></div>
		<div data-options="region:'center'" style="padding:2px">
			<div class="easyui-layout" fit="true">
				<div border="false" data-options="region:'north',collapsible:true,split:true" style="height:33%;">
					<table id="bankTableserver"></table>
				</div>
				<div border="false" data-options="region:'center',collapsible:true,split:true" style="height:33%;padding:2px 0 2px 0">
					<table id="bankTableinterface"></table>
				</div>
				<div border="false" data-options="region:'south',collapsible:true,split:true" style="height:33%;">
					<table id="bankTablerule"></table>
				</div>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="showAddBankWin();" id="append" data-options="iconCls:'icon-add'">增加子行</div>
		<div onclick="showUpdateBankWin();" id="update" data-options="iconCls:'icon-remove'">更新银行</div>
		<div onclick="openBank();" id="open" data-options="iconCls:'icon-remove'">启用银行</div>
		<div onclick="closeBank();" data-options="iconCls:'icon-remove'">停用银行</div>
	</div>
</body>
</html>