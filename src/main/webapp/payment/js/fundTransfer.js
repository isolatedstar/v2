function setPayTransferPanel() {

	var noteText = '业务转账明细';
	var exist_tab = $('#centerWorkArea').tabs('getTab', noteText);
	if (exist_tab) {
		$('#centerWorkArea').tabs('select', noteText);
		return;
	} else {
		$('#centerWorkArea').tabs('add', {
			title : noteText,
			iconCls : 'icon-fundtransfer',
			content : '<div class="easyui-panel" href="../payment/js/fundTransfer.jsp" border="false" style="width:100%;height:100%;padding:2px;"></div>',
			closable : true
		});

		setTimeout(function() {
			setAccountTable();
		}, 500);
	}
}

function removePanel() {
	var tab = $('#centerWorkArea').tabs('getSelected');
	if (tab) {
		var index = $('#centerWorkArea').tabs('getTabIndex', tab);
		$('#centerWorkArea').tabs('close', index);
	}
}

function setAccountTable() {

	$('#step2AccountTable').datagrid({
		fit : true,// 自动大小
		fitColumns : true,
		nowrap : false,
		striped : true,
		iconCls : "icon-save",// 图标
		columns : [ [ {
			field : 'accountOut',
			title : '划出账号',
			width : 100,
			align : 'center'
		}, {
			field : 'masterRelation',
			title : '带附属户',
			width : 100,
			align : 'center'
		}, {
			field : 'accountIn',
			title : '划入账户',
			width : 100,
			align : 'center'
		} ] ],
	});
}
var datagrid;
// 初期化
$(function() {
	datagrid = $('#tableDetail').datagrid({
		url : '',
		title : '业务划转明细',
		fit : true,
		fitColumns : true,
		striped : true,
		nowrap : true,
		loadMsg : '正在努力为您加载..',
		rownumbers : true,
		singleSelect : true,
		showFooter : true,
		pagination : true,
		remoteSort : true,
//		pageSize : 1,
//		pageList : [ 1, 2, 3 ],
		onDblClickRow : function(index, row) {
			var row = $('#tableDetail').datagrid('getSelected');
			var rowDetail = [
	            { "name": "编号 ","value":row.bankFlowId},
	            { "name": "报文来源","value": row.msgSource},
	            { "name": "交易时间", "value": row.tradeTimeStr},
	            { "name": "操作人","value": row.operator},
	            { "name": "资金划出银行","value": row.outBankName},
	            { "name": "所属主户（出）","value": row.outMasterAcct},
	            { "name": "资金划出账号", "value":row.outAcct},
	            { "name": "资金划入银行", "value": row.inBankName},
	            { "name": "所属主户（入）", "value": row.inMasterAcct},
	            { "name": "资金划入账号", "value": row.inAcct},
	            { "name": "业务类型", "value": row.businessTypeName},
	            { "name": "动作类型", "value": row.workTypeName},
	            { "name": "资金划转金额", "value": row.transferAmt},
	            { "name": "资金划转状态", "value": row.transferStatusName},
	            { "name": "交易说明", "value": row.transferContext}
	        ];
			$('#propertyGridTransferRow').propertygrid('loadData', rowDetail);
			$('#propertyGridTransferWindow').window('open');
			$('#propertyGridTransferWindow').window('center');
		},
		onRowContextMenu : function(e, node) {

			e.preventDefault();
			$('#showOrHideTransfer').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		columns : [ [ {
			field : 'bankFlowId',
			title : '编号',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'msgSource',
			title : '报文来源',
			width : 50,
			align : 'center',
			sortable : true
		}, {
			field : 'tradeTimeStr',
			title : '交易时间',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'operator',
			title : '操作人',
			width : 60,
			align : 'center',
			sortable : true
		}, {
			field : 'outBankName',
			title : '资金划出银行',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'outMasterAcct',
			title : '所属主户（出）',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'outAcct',
			title : '资金划出账号',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'inBankName',
			title : '资金划入银行',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'inMasterAcct',
			title : '所属主户（入）',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'inAcct',
			title : '资金划入账号',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'businessTypeName',
			title : '业务类型',
			width : 60,
			align : 'center',
			sortable : true
		}, {
			field : 'interfaceStatusName',
			title : '接口类型',
			width : 60,
			align : 'center',
			sortable : true
		},{
			field : 'workTypeName',
			title : '动作类型',
			width : 60,
			align : 'center',
			sortable : true
		}, {
			field : 'transferAmt',
			title : '资金划转金额',
			width : 60,
			align : 'center',
			sortable : true
		}, {
			field : 'transferStatusName',
			title : '资金划转状态',
			width : 60,
			align : 'center',
			sortable : true
		}, {
			field : 'transferContext',
			title : '交易说明',
			width : 60,
			align : 'center',
			sortable : true
		} ] ]

	});

	$('#tableAccount').datagrid({
		fit : true,
		singleSelect:true,
		columns : [ [ {
			field : 'acctName',
			title : '公司名称',
			width : "50%",
			align : 'center'
		}, {
			field : 'bankAcct',
			title : '账号',
			width : "50%",
			align : 'center'
		} ] ],
		onDblClickRow : function(index, row) {
			$('#accountOut').textbox('setValue', row.bankAcct);
			$('#addAccountWindow').dialog('close');
		}
	});

	$('#tableAccountIn').datagrid({
		fit : true,
		singleSelect:true,
		columns : [ [ {
			field : 'acctName',
			title : '公司名称',
			width : "50%",
			align : 'center'
		}, {
			field : 'bankAcct',
			title : '账号',
			width : "50%",
			align : 'center'
		} ] ],
		onDblClickRow : function(index, row) {
			$('#accountIn').textbox('setValue', row.bankAcct);
			$('#addAccountInWindow').dialog('close');
		}
	});

	$('#propertyGridTransferRow').propertygrid({
	    width: 450,
	    height: 'auto',
	    showGroup: false,
	    scrollbarSize: 0,
	    columns: [[
	            { field: 'name', title: '列名称', width: 200, resizable: true },
	            { field: 'value', title: '列值', width: 250, resizable: false }
	    ]]
	});

});

function subForm() {
	var tableLength = JSON.stringify($("#step2AccountTable").datagrid('getRows'));
	if (tableLength.length == 2) {
		$.messager.alert('提示信息', '账号列表不能为空！', 'info');
		return;
	}

	var grid = $("#tableDetail");
	$.extend(grid.datagrid("options"), {
		url : "../fundTransferController/show_transfer_detail.do",// 这里定义url
		queryParams : {
			startTime : $("#startTime").datebox('getValue'),
			endTime : $("#endTime").datebox('getValue'),
			interfaceType : $('#interfaceType').combobox('getValue'),
			operator : $("#operator").val(),
			businessType : $("#businessType").combobox('getValue'),
			businessFrom : $("#businessFrom").combobox('getValue'),
			transferStatus : $("#transferStatus").combobox('getValue'),
			actionType : $("#actionType").combobox('getValue'),
			tableRows : JSON.stringify($("#step2AccountTable").datagrid('getRows'))
		}
	});
	grid.datagrid("load");

}
function addTransferAccount() {
	if ($("#accountOut").val() == null || $("#accountOut").val() == "") {

		$.messager.alert('提示信息', '划出方账号不能为空！', 'info');
		return;
	} else if ($("#accountOut").val().length < 16 || $("#accountOut").val().length > 19) {
		$.messager.alert('提示信息', '划出方账号不能小于16位或大于19位！', 'info');
		return;
	}
	if ($("#accountIn").val() != null && $("#accountIn").val() != "") {
		if ($("#accountIn").val().length < 16 || $("#accountIn").val().length > 19) {
			$.messager.alert('提示信息', '划入方账号不能小于16位或大于19位！', 'info');
			return;
		}
	}
	if ($("#relation").is(':checked')) {
		if ($("#accountIn").val() != "" && $("#accountIn").val() != null) {
			$.messager.confirm("操作提示", "带附属户条件下，划入方将不作为过滤条件。", function(data) {
				if (data) {

					$("#step2AccountTable").datagrid('insertRow', {
						index : 0,
						row : {
							accountIn : "",
							accountOut : $("#accountOut").val(),
							masterRelation : $("#relation").is(':checked') ? '是' : '否'
						}
					});
				}

			});
		} else {
			$("#step2AccountTable").datagrid('insertRow', {
				index : 0,
				row : {
					accountIn : "",
					accountOut : $("#accountOut").val(),
					masterRelation : $("#relation").is(':checked') ? '是' : '否'
				}
			});
		}
	} else {
		$("#step2AccountTable").datagrid('insertRow', {
			index : 0,
			row : {
				accountIn : $("#accountIn").val(),
				accountOut : $("#accountOut").val(),
				masterRelation : $("#relation").is(':checked') ? '是' : '否'
			}
		});
	}

}
function delTransferAccount() {
	// 删除操作
	var rows = $('#step2AccountTable').datagrid("getSelections"); // 获取你选择的所有行
	if(rows != '') {
		$.messager.confirm('确认', '确认删除?', function(ok) {
			if (ok) {
				// 循环所选的行
				for ( var i = 0; i < rows.length; i++) {
					var index = $('#step2AccountTable').datagrid('getRowIndex', rows[i]);// 获取某行的行号
					$('#step2AccountTable').datagrid('deleteRow', index); // 通过行号移除该行
				}
			}
		});
	} else {
		$.messager.alert('提示信息', '查询条件不存在或未被选中，无法删除！', 'info');
	}
}
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
function showAddAcctTransferWin() {
	$('#addAccountWindow').window('open');
}
function addAccountTransfer() {

	$.ajax({
		url : '../fundTransferController/show_filter_account.do',// 跳转到 action
		data : {
			acctName : $("#acctName").val(),
			bankId : $("#bankIdTransfer").combobox('getValue')
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$('#tableAccount').datagrid('loadData', data);
		},
		error : function() {

			alert("查询公司账号信息异常！");
		}
	});
}

function showAddAcctInTransferWin() {
	$('#addAccountInWindow').window('open');
}
function addAccountInTransfer() {

	$.ajax({
		url : '../fundTransferController/show_filter_account.do',// 跳转到 action
		data : {
			acctName : $("#acctInName").val(),
			bankId : $("#bankIdInTransfer").combobox('getValue')
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$('#tableAccountIn').datagrid('loadData', data);
		},
		error : function() {

			alert("查询公司账号信息异常！");
		}
	});
}

function showOrHideTransferSelect(){
	var option=document.getElementsByName("checkOption");
	for(var i=0;i<option.length;i++){
		if(option[i].checked){
			$('#tableDetail').datagrid('hideColumn',option[i].id);
		} else {
			$('#tableDetail').datagrid('showColumn',option[i].id);
		}
	} 
}

$('#relation').click(function() {
	if ($("#relation").is(':checked')) {
		$('#accountIn').combobox('disable');
		$('#accountIn').textbox('clear');
		$('#inSelectButton').linkbutton('disable');
	} else{
		$('#accountIn').combobox('enable');
		$('#inSelectButton').linkbutton('enable');
	}
});

function wangyin(){
	alert("进入方法");
	$.ajax({
		url : '../fundTransferController/test_wangyin.do',// 跳转到 action
		data : {aa:'aa'},
		type : 'POST',
		dataType : 'json',
		success: function(data){
//			data = eval("("+data+")");
			alert(data.msg);
				if(data.result=="success"){
					window.open(encodeURI("../payment/js/zhongxin.jsp?xmlParam="+data.msg).replace(/\+/g,'%2B'),"_blank");
					$.layer({
					      shade: [0],
                           area: ['aut  o','auto'],
                           dialog: {
	                           msg: '直接支付已完成，点击【确定】按钮，将刷新本页面！',
	                           btns: 1,                    
	                           type: 1,
	                           btn: ['确定'],
	                           yes: function(){
	                               window.location.href="../payment/js/fundTransfer.jsp";
	                           }
                           }
                     });
				}
			}
	});
}