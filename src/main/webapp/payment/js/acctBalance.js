function setAccountBalancePanel() {

	var noteText = '账号余额查询';
	var exist_tab = $('#centerWorkArea').tabs('getTab', noteText);
	if (exist_tab) {
		$('#centerWorkArea').tabs('select', noteText);
		return;
	} else {
		$('#centerWorkArea').tabs('add', {
			title : noteText,
			iconCls : 'icon-acctbalance',
			content : '<div class="easyui-panel" href="../payment/js/acctBalance.jsp" border="false" style="width:100%;height:100%;padding:2px;"></div>',
			closable : true
		});

		setTimeout(function() {
			setBalanceTable();
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

function setBalanceTable() {

	$('#accountTableSelect').datagrid({
		fit : true,// 自动大小
		fitColumns : true,
		nowrap : false,
		striped : true,
		iconCls : "icon-save",// 图标
		columns : [ [ {
			field : 'accountOut',
			title : '划出账号',
			width : 120,
			align : 'center'
		}, {
			field : 'relationBalance',
			title : '带附属户',
			width : 120,
			align : 'center'
		} ] ],
	});

}

$(function() {
	$('#tableDetailBalance').datagrid({

		url : '',
		fit : true,
		fitColumns : true,
		striped : true,
		nowrap : true,
		loadMsg : '正在努力为您加载..',
		rownumbers : true,
		title : '账户余额明细',
		singleSelect : true,
		showFooter : true,
		pagination : true,
		remoteSort : true,
//		pageSize : 1,
//		pageList : [ 1, 2, 3 ],
		onDblClickRow : function(index, row) {
			var row = $('#tableDetailBalance').datagrid('getSelected');
			var rowDetail = [
	            { "name": "开户公司名称 ","value":row.companyName},
	            { "name": "开户行名称","value": row.bankName},
	            { "name": "银行账号", "value": row.amtAccount},
	            { "name": "银行冻结金额","value": row.bankFreezeAmt},
	            { "name": "支付平台的冻结","value": row.freezeAmt},
	            { "name": "账号可用余额","value": row.bankUsableAmt},
	            { "name": "支付账号可用额", "value":row.usableAmt},
	            { "name": "账号余额", "value": row.bankAmt},
	            { "name": "支付账号余额", "value": row.amt}
	        ];
			$('#propertyGridBalanceRow').propertygrid('loadData', rowDetail);
			$('#propertyGridBalanceWindow').window('open');
			$('#propertyGridBalanceWindow').window('center');
		},
		onRowContextMenu : function(e, node) {

			e.preventDefault();
			$('#showOrHide').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		columns : [ [ {
			field : 'companyName',
			title : '开户公司名称',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'bankName',
			title : '开户行名称',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'amtAccount',
			title : '银行账号',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'bankFreezeAmt',
			title : '银行冻结金额',
			width : 60,
			align : 'center'
		}, {
			field : 'freezeAmt',
			title : '支付平台的冻结',
			width : 60,
			align : 'center'
		}, {
			field : 'bankUsableAmt',
			title : '账号可用余额',
			width : 60,
			align : 'center'
		}, {
			field : 'usableAmt',
			title : '支付账号可用额',
			width : 60,
			align : 'center'
		}, {
			field : 'bankAmt',
			title : '账号余额',
			width : 60,
			align : 'center'
		}, {
			field : 'amt',
			title : '支付账号余额',
			width : 60,
			align : 'center'
		} ] ]
	});
	$('#tableAccountBala').datagrid({
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
			$('#accountOutBalance').textbox('setValue', row.bankAcct);
			$('#addAccountBalanceWindow').dialog('close');
		}
	});
	$('#propertyGridBalanceRow').propertygrid({
	    width: 450,
	    height: 'auto',
	    showGroup: false,
	    scrollbarSize: 0,
	    columns: [[
	            { field: 'name', title: '列名称', width: 200, resizable: true },
	            { field: 'value', title: '列值', width: 250, resizable: false }
	    ]]
	});
//	 var p = $('#tableDetailBalance').datagrid('getPager');
//     $(p).pagination({
//         pageSize: 10,//每页显示的记录条数，默认为10  
//         pageList: [10, 20, 30],//可以设置每页记录条数的列表  
//         beforePageText: '第',//页数文本框前显示的汉字  
//         afterPageText: '页    共 {pages} 页',
//         displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
//     });
});
function subFormBalance() {
	var tableLength = JSON.stringify($("#accountTableSelect").datagrid('getRows'));
	if (tableLength.length == 2) {
		$.messager.alert('提示信息', '余额明细查询条件不能为空！', 'info');
		return;
	}

	var $grid = $("#tableDetailBalance");
	$.extend($grid.datagrid("options"), {
		url : "../acctBalanceController/show_balance_detail.do",// 这里定义url
		queryParams : {
			tableRows : JSON.stringify($("#accountTableSelect").datagrid('getRows'))
	}
	});
	$grid.datagrid("load");
}
function addBalanceAccount() {
	// test
	if ($("#accountOutBalance").val() == null || $("#accountOutBalance").val() == "") {
		$.messager.alert('提示信息', '划出方账号不能为空！', 'info');
		return;
	} else if ($("#accountOutBalance").val().length < 16 || $("#accountOutBalance").val().length > 19) {
		$.messager.alert('提示信息', '划出方账号不能小于16位或大于19位！', 'info');
		return;
	}
	$("#accountTableSelect").datagrid('insertRow', {
		index : 0,
		row : {
			accountOut : $("#accountOutBalance").val(),
			relationBalance : $("#relationBalance").is(':checked') ? '是' : '否'
		}
	});
}
function delBalanceAccount() {
	// 删除操作
	var rows = $('#accountTableSelect').datagrid("getSelections"); // 获取你选择的所有行
	if(rows != '') {
		$.messager.confirm('确认', '确认删除?', function(ok) {
			if (ok) {
				// 循环所选的行
				for ( var i = 0; i < rows.length; i++) {
					var index = $('#accountTableSelect').datagrid('getRowIndex', rows[i]);// 获取某行的行号
					$('#accountTableSelect').datagrid('deleteRow', index); // 通过行号移除该行
				}
			}
		});
	} else {
		$.messager.alert('提示信息', '查询条件不存在或未被选中，无法删除！', 'info');
	}
}
function showAddAcctBalanceWin() {
	$('#addAccountBalanceWindow').window('open');
}

function addAccountBalance() {

	$.ajax({
		url : '../fundTransferController/show_filter_account.do',// 跳转到 action
		data : {
			acctName : $("#acctNameBalance").val(),
			bankId : $("#bankIdBalance").combobox('getValue')
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$('#tableAccountBala').datagrid('loadData', data);
		},
		error : function() {
			$.messager.alert('提示信息', '查询公司账号信息异常！', 'info');
		}
	});
}

function showOrHideSelectOption() {
	var option = document.getElementsByName("checkOptionBla");
	for ( var i = 0; i < option.length; i++) {
		if (option[i].checked) {
			$('#tableDetailBalance').datagrid('hideColumn', option[i].id);
		} else {
			$('#tableDetailBalance').datagrid('showColumn', option[i].id);
		}
	}
}
