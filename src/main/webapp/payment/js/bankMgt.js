function setBankMgtPanel() {
	var noteText = '银行信息管理';
	var exist_tab = $('#centerWorkArea').tabs('getTab', noteText);
	if (exist_tab) {
		$('#centerWorkArea').tabs('select', noteText);
		return;
	} else {
		$('#centerWorkArea').tabs('add', {
			title : noteText,
			iconCls : 'icon-bankmgt',
			content : '<div class="easyui-panel" href="../payment/js/bankMgt.jsp" border="false" style="width:100%;height:100%;padding:2px;"></div>',
			closable : true
		});
		setTimeout(function() {
			setBankMgtTree();
			setBankMgtTable();
			setBankMgtTableinterface();
			setBankMgtTablerule();
			setAcctTypeOnSelect();
		}, 500);
	}
}

function setBankMgtTree() {
	$('#banktree').tree({
		lines : true,
		url : '../bankMgtController/getBankTree.do',
		animate : true,
		onContextMenu : function(e, node) {
			e.preventDefault();
			$(this).tree('select', node.target);
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onClick : function(node) {

			if (node.id != 0) {
				var $grid = $("#bankTableserver");
				$.extend($grid.datagrid("options"), {
					url : "../bankMgtController/pay_querybankserver.do?id=" + node.id,// 这里定义url

				});
				$grid.datagrid("load");
				setTimeout(function() {

					var $interface = $("#bankTableinterface");
					$.extend($interface.datagrid("options"), {
						url : "../bankMgtController/pay_querybankinterface.do?id=" + node.id,// 这里定义url

					});
					$interface.datagrid("load");
				}, 200);
				setTimeout(function() {

					var $bankTablerule = $("#bankTablerule");
					$.extend($bankTablerule.datagrid("options"), {
						url : "../bankMgtController/pay_querybank.do?id=" + node.id,// 这里定义url

					});
					$bankTablerule.datagrid("load");
				}, 400);
			}

			if (node.status == 0 && node.id != 0) {
				$('#addServerButton').linkbutton('enable');
				$('#updateServerButton').linkbutton('enable');
				$('#deleteServerButton').linkbutton('enable');
				$('#addInterfaceButton').linkbutton('enable');
				$('#updateInterfaceButton').linkbutton('enable');
				$('#deleteInterfaceButton').linkbutton('enable');
				$('#addRuleButton').linkbutton('enable');
				$('#updateRuleButton').linkbutton('enable');
				$('#deleteRuleButton').linkbutton('enable');
			} else {
				$('#addServerButton').linkbutton('disable');
				$('#updateServerButton').linkbutton('disable');
				$('#deleteServerButton').linkbutton('disable');
				$('#addInterfaceButton').linkbutton('disable');
				$('#updateInterfaceButton').linkbutton('disable');
				$('#deleteInterfaceButton').linkbutton('disable');
				$('#addRuleButton').linkbutton('disable');
				$('#updateRuleButton').linkbutton('disable');
				$('#deleteRuleButton').linkbutton('disable');
				if (node.id != 0) {
					$.messager.alert('提示信息', "该银行已经停用！", 'info');
				} else {
					$.messager.alert('提示信息', "选择的节点无效！", 'info');
				}

			}

		}
	});
}
function setBankMgtTable() {
	$('#bankTableserver').datagrid({
		url : '',
		pagination : true,// 显示分页
		pageSize : 5,// 分页大小
		pageList : [ 5, 10, 15, 20 ],// 每页的个数
		rownumbers : true,
		height : 'auto',
		fit : true,// 自动大小
		fitColumns : true,
		collapsible : false,// 是否可折叠的
		nowrap : false,
		striped : true,
		singleSelect : true,// 是否单选
		rownumbers : true,
		iconCls : "icon-save",// 图标
		title : "服务器管理",
		collapsible : false,
		columns : [ [ {
			field : 'serverId',
			title : '服务器编号',
			width : 100,
			align : "center",
			hidden : true
		}, {
			field : 'bankId',
			title : '银行编号',
			width : 100,
			align : "center",
			hidden : true
		}, {
			field : 'serverType',
			title : '服务器类型',
			width : 100,
			align : "center"
		}, {
			field : 'serverName',
			title : '服务器名称',
			width : 100,
			align : "center"
		}, {
			field : 'serverAddress',
			title : '服务器地址',
			width : 100,
			align : "center"
		}, {
			field : 'createTime',
			title : '创建时间',
			width : 200,
			align : "center",
			formatter : function(value, row, index) {
				var unixTimestamp = new Date(value);
				return unixTimestamp.toLocaleString();
			}
		}, {
			field : 'status',
			title : '状态',
			width : 100,
			align : "center"
		}, ] ],
		toolbar : [ {
			id : 'addServerButton',
			text : '添加',
			iconCls : 'icon-pay-add',
			handler : function() {
				showAddServer();
			}
		}, '-', {
			id : 'updateServerButton',
			text : '修改',
			iconCls : 'icon-pay-update',
			handler : function() {
				showupdateServer();
			}
		}
		// , '-', {
		// id:'deleteServerButton',
		// text : '删除',
		// iconCls : 'icon-pay-delete',
		// handler : function() {
		// showDeleteServer();
		// }
		// }
		],
	});

	$('#addServerButton').linkbutton('enable');
	$('#updateServerButton').linkbutton('enable');
	$('#deleteServerButton').linkbutton('enable');

}

function setBankMgtTableinterface() {
	$('#bankTableinterface').datagrid({
		url : '',
		pagination : true,// 显示分页
		pageSize : 5,// 分页大小
		pageList : [ 5, 10, 15, 20 ],// 每页的个数
		fit : true,// 自动大小
		fitColumns : true,
		nowrap : false,
		striped : true,
		// border: false,
		singleSelect : true,// 是否单选
		rownumbers : true,
		iconCls : "icon-save",// 图标
		title : "接口管理",
		columns : [ [ {
			field : 'interfaceId',
			title : '接口编号',
			width : 100,
			align : "center",
			hidden : true
		}, {
			field : 'bankId',
			title : '银行编号',
			width : 100,
			align : "center",
			hidden : true
		}, {
			field : 'serverType',
			title : '接口类型',
			width : 100,
			formatter : typeFormat,
			align : "center"
		}, {
			field : 'interfaceClass',
			title : '接口操作类',
			width : 100,
			align : "center"
		}, {
			field : 'createTime',
			title : '创建时间',
			width : 200,
			align : "center",
			formatter : function(value, row, index) {
				var unixTimestamp = new Date(value);
				return unixTimestamp.toLocaleString();
			}
		}, {
			field : 'status',
			title : '状态',
			width : 100,
			formatter : statusFormat,
			align : "center"
		}, ] ],
		toolbar : [ {
			id : 'addInterfaceButton',
			text : '添加',
			iconCls : 'icon-pay-add',
			handler : function() {
				showAddInterface();
			}
		}, '-', {
			id : 'updateInterfaceButton',
			text : '修改',
			iconCls : 'icon-pay-update',
			handler : function() {
				showUpdateInterface();
			}
		}
		// , '-', {
		// id:'deleteInterfaceButton',
		// text : '删除',
		// iconCls : 'icon-pay-delete',
		// handler : function() {
		// showDeleteInterface();
		// }
		// }
		],
	});
	$('#addInterfaceButton').linkbutton('enable');
	$('#updateInterfaceButton').linkbutton('enable');
	$('#deleteInterfaceButton').linkbutton('enable');
}
function setBankMgtTablerule() {
	$('#bankTablerule').datagrid({
		url : '',
		pagination : true,// 显示分页
		pageSize : 5,// 分页大小
		height : 'auto',
		pageList : [ 5, 10, 15, 20 ],// 每页的个数
		fit : true,// 自动大小
		fitColumns : true,
		nowrap : false,
		striped : true,
		// border: false,
		singleSelect : true,// 是否单选
		rownumbers : true,
		iconCls : "icon-save",// 图标
		title : "银行规则管理",
		pageNumber : 1,
		columns : [ [ {
			field : 'acctTypeName',
			title : '账户类型',
			width : 100,
			align : "center"
		}, {
			field : 'acctLength',
			title : '账户长度',
			width : 100,
			align : "center"
		}, {
			field : 'ruleStart',
			title : '规则起始位置',
			width : 100,
			align : "center"
		}, {
			field : 'ruleEnd',
			title : '规则结束位置',
			width : 100,
			align : "center"
		}, {
			field : 'ruleContent',
			title : '规则内容',
			width : 100,
			align : "center"
		}, {
			field : 'createTime',
			title : '创建时间',
			width : 200,
			align : "center",
			formatter : function(value, row, index) {
				var unixTimestamp = new Date(value);
				return unixTimestamp.toLocaleString();
			}
		}, {
			field : 'statusName',
			title : '状态',
			width : 100,
			align : "center"
		}, ] ],
		toolbar : [ {
			id : 'addRuleButton',
			text : '添加',
			iconCls : 'icon-pay-add',
			handler : function() {
				showAddRule();
			}
		}, '-', {
			id : 'updateRuleButton',
			text : '修改',
			iconCls : 'icon-pay-update',
			handler : function() {
				showUpdateRule();
			}
		}
		// , '-', {
		// id:'deleteRuleButton',
		// text : '删除',
		// iconCls : 'icon-pay-delete',
		// handler : function() {
		// showDeleteRule();
		// }
		// }
		],
	});

}
function showAddServer() {
	var node = $('#banktree').tree('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}

	$("#server").panel({
		title : '添加服务器'
	});

	$('#serverStatus').combobox({
		url : '../utilController/getDataDictByCode.do?code=serverStatus',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});

	$('#serverType').combobox({
		url : '../utilController/getDataDictByCode.do?code=serverType',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});

	// 初始化数据
	$('#serverAddress').val('');
	$('#serverStatus').combobox('setValue', '');
	$('#serverNamebig').val('');
	$('#operate').val("add");

	$('#serverType').combobox('setValue', '');
	$('#updateServerBankId').val(node.id);

	var divs = document.getElementById("updateServer");
	divs.style.display = "none";
	var divs = document.getElementById("saveServer");
	divs.style.display = "";
	$("#server").show();
	$('#server').window('open');
	$('#server').window('center');
}
function showDeleteServer() {
	var row = $('#bankTableserver').datagrid('getSelected');
	if (row == null) {
		$.messager.alert('提示信息', '请选择服务器！', 'info');
		return;
	}
	$.messager.confirm("操作提示", "您确定要执行操作吗？", function(data) {
		if (data) {
			$.ajax({
				url : '../bankMgtController/pay_closeserver.do',
				data : {
					id : row.serverId,
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$.messager.alert('提示信息', data, 'info');
				},
				error : function() {

					$.messager.alert('提示信息', '删除异常！', 'info');
				}
			});
		} else {
		}
	});
}
function showDeleteInterface() {
	var row = $('#bankTableinterface').datagrid('getSelected');
	if (row == null) {
		$.messager.alert('提示信息', '请选择接口！', 'info');
		return;
	}
	$.messager.confirm("操作提示", "您确定要执行操作吗？", function(data) {
		if (data) {
			$.ajax({
				url : '../bankMgtController/pay_closeinterface.do',
				data : {
					id : row.interfaceId,
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$.messager.alert('提示信息', data, 'info');
				},
				error : function() {

					$.messager.alert('提示信息', '删除异常！', 'info');
				}
			});
		} else {
		}
	});
}
function showDeleteRule() {
	var row = $('#bankTablerule').datagrid('getSelected');
	if (row == null) {
		$.messager.alert('提示信息', '请选择规则！', 'info');
		return;
	}
	$.messager.confirm("操作提示", "您确定要执行操作吗？", function(data) {
		if (data) {
			$.ajax({
				url : '../bankMgtController/pay_closerule.do',
				data : {
					id : row.bankRuleId,
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$.messager.alert('提示信息', data, 'info');
				},
				error : function() {
					$.messager.alert('提示信息', '删除异常！', 'info');
				}
			});
		} else {
		}
	});
}
function showupdateServer() {
	var node = $('#banktree').tree('getSelected');
	var row = $('#bankTableserver').datagrid('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	if (row == null) {
		$.messager.alert('提示信息', '请选择服务器！', 'info');
		return;
	}
	$("#server").panel({
		title : '修改服务器'
	});

	$('#serverStatus').combobox({
		url : '../utilController/getDataDictByCode.do?code=serverStatus',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});

	$('#serverType').combobox({
		url : '../utilController/getDataDictByCode.do?code=serverType',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});

	$('#updateServerBankId').val(node.id);
	$('#serverId').val(row.serverId);
	$('#serverType').combobox('setValue', row.serverType);
	$('#serverNamebig').val(row.serverName);
	$('#serverAddress').val(row.serverAddress);
	$('#serverStatus').combobox('setValue', row.status);
	$('#serverLock').val(row.lockFlag);
	$('#updateServerBankId').val(row.bankId);

	$('#previousName').val(row.serverName);
	$('#operate').val("update");
	$("#previousServerType").val($('#serverType').combobox('getValue'));
	$("#previousServerAddress").val(row.serverAddress);

	var divs = document.getElementById("saveServer");
	divs.style.display = "none";
	var divs = document.getElementById("updateServer");
	divs.style.display = "";
	$("#server").show();
	$('#server').window('open');
	$('#server').window('center');
}
function showUpdateInterface() {
	var node = $('#banktree').tree('getSelected');
	var row = $('#bankTableinterface').datagrid('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	if (row == null) {
		$.messager.alert('提示信息', '请选择接口！', 'info');
		return;
	}

	$('#interfaceStatus').combobox({
		url : '../utilController/getDataDictByCode.do?code=interfaceStatus',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});
	$('#interfaceType').combobox({
		url : '../utilController/getDataDictByCode.do?code=interfaceType',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});

	$("#interface").panel({
		title : '修改接口'
	});

	$('#updateServerBankId').val(node.id);
	$('#interfaceName').val(row.interfaceId);
	$('#interfaceType').combobox('setValue', row.serverType);
	$('#interfaceAddress').val(row.interfaceClass);
	$('#interfaceStatus').combobox('setValue', row.status);
	$('#interFaceLock').val(row.lockFlag);
	$('#updateInterfaceBankId').val(row.bankId);
	var divs = document.getElementById("saveInterface");
	divs.style.display = "none";
	var divs = document.getElementById("updateInterface");
	divs.style.display = "";
	$("#interface").show();
	$('#interface').window('open');
	$('#interface').window('center');
}
function showAddRule() {
	var node = $('#banktree').tree('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}

	if (node.parentBankId != 0) {
		// 显示父级
		$("#parentRuleId").closest("tr").show();
		// $('#parentRuleId').combobox({
		// url : '../bankMgtController/getFatherRule.do?bankRuleId=' + node.parentBankId,
		// editable : false,
		// valueField : 'bankRuleId',
		// textField : 'rulePreview',
		// });
	} else {
		$("#parentRuleId").closest("tr").hide();
		// $("#accountLength").numberbox("enable");
		$('#parentRuleId').combobox('setValue', '0');
	}

	$("#addRule").show();
	$('#addRule').window('open');
	$('#addRule').window('center');
	$('#acctType').combobox('clear');
	$('#ruleStatus').combobox('clear');
//	$('#parentRuleId').combobox('clear');
	$('#acctLength').val('');
	$('#ruleStart').val('');
	$('#ruleEnd').val('');
	$('#ruleContext').val('');
}
function LookRule() {
	if ($("#addRuleform").form('validate')) {
		var start = $('#ruleStart').val();
		var end = $('#ruleEnd').val();
		var content = $('#ruleContext').val();
		var length = $('#acctLength').val();

		if (ruleCheck(length, start, end, content)) {

			$.messager.alert('提示信息', buildPreview(length, start, end, content), 'info');

		}

	}

}
function updateLookRule() {
	if ($("#updateRuleform").form('validate')) {
		var start = $('#uRuleStart').val();
		var end = $('#uRuleEnd').val();
		var content = $('#uRuleContext').val();
		var length = $('#uAcctLength').val();

		if (ruleCheck(length, start, end, content)) {

			$.messager.alert('提示信息', buildUpdatePreview(length, start, end, content), 'info');

		}

	}

}
function showUpdateRule() {
	var node = $('#banktree').tree('getSelected');
	var row = $('#bankTablerule').datagrid('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	if (row == null) {
		$.messager.alert('提示信息', '请选择规则！', 'info');
		return;
	}

	if (node.parentBankId != 0) {
		// 显示父级
		$("#uParentRuleId").closest("tr").show();
	} else {
		$("#uParentRuleId").closest("tr").hide();
		// $("#accountLength").numberbox("enable");
		$('#uParentRuleId').combobox('setValue', '0');
	}

	$('#uAcctType').combobox('setValue', row.acctType);
	$('#uParentRuleId').combobox('setValue', row.parentRuleId);
	$('#uAcctLength').val(row.acctLength);
	$('#uRuleStart').val(row.ruleStart);
	$('#uRuleEnd').val(row.ruleEnd);
	$('#uRuleContext').val(row.ruleContent);
	$('#uRuleStatus').combobox('setValue', row.status);

	$("#updateRule").show();
	$('#updateRule').window('open');
	$('#updateRule').window('center');
}
function showAddInterface() {
	var node = $('#banktree').tree('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	$("#interface").panel({
		title : '添加接口'
	});

	$('#interfaceStatus').combobox({
		url : '../utilController/getDataDictByCode.do?code=interfaceStatus',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});
	$('#interfaceType').combobox({
		url : '../utilController/getDataDictByCode.do?code=interfaceType',
		method : 'get',
		editable : false,
		valueField : 'itemCode',
		textField : 'itemName'
	});

	$('#updateInterfaceBankId').val(node.id);
	var divs = document.getElementById("updateInterface");
	divs.style.display = "none";
	var divs = document.getElementById("saveInterface");
	divs.style.display = "";

	$('#interfaceName').val('');
	$('#interfaceType').combobox('setValue', '');
	$('#interfaceAddress').val('');

	$("#interface").show();
	$('#interface').window('open');
	$('#interface').window('center');
}
function showAddBankWin() {
	var node = $('#banktree').tree('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	if (node.id.length > 8) {
		$.messager.alert('提示信息', '不能增加子行了！', 'info');
		return;
	}
	$('#addParentId').val(node.id);
	$('#addtext').val(node.text);
	$('#addBankWin').window('open');
}
function openBank() {
	var node = $('#banktree').tree('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	$.ajax({
		url : '../bankMgtController/pay_openbank.do',
		data : {
			id : node.id
		},
		type : 'POST',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$.messager.alert('提示信息', data, 'info');
			// node.status = 0;
			$('#banktree').tree("reload");
		},
		error : function() {
			$.messager.alert('提示信息', '异常！', 'info');
		}
	});
}
function closeBank() {
	var node = $('#banktree').tree('getSelected');
	if (node == null) {
		$.messager.alert('提示信息', '请选择银行！', 'info');
		return;
	}
	$.ajax({
		url : '../bankMgtController/pay_closebank.do',
		data : {
			id : node.id
		},
		type : 'POST',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$.messager.alert('提示信息', data, 'info');
			// node.status = 1;
			$('#banktree').tree("reload");
		},
		error : function() {
			$.messager.alert('提示信息', '异常！', 'info');
		}
	});
}
function closeout() {
	$('#addBankWin').window('close');
	$('#updateBankWin').window('close');
	$('#interface').window('close');
	$('#server').window('close');
	$('#addRule').window('close');
	$('#updateRule').window('close');
}
function showUpdateBankWin() {
	var node = $('#banktree').tree('getSelected');
	$('#updateParentId').val(node.parentBankId);
	$('#updateBankId').val(node.id);
	$('#updateBankName').val(node.text);
	$('#updateBankWin').window('open');
}
function saveBank() {
	if ($("	").form('validate')) {
		$.ajax({
			url : '../bankMgtController/addbank.do',
			data : {
				id : $('#addBankId').val(),
				parentid : $('#addParentId').val(),
				text : $('#addtext').val()
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data, 'info');
				$('#addBankWin').window('close');
				$('#banktree').tree("reload");
			},
			error : function() {

				$.messager.alert('提示信息', '异常！', 'info');
			}
		});
	} else {
		$.messager.alert('提示信息', '请检查数据的格式！', 'info');
	}
}
function saveInterface() {
	if ($("#interfaceform").form('validate')) {
		$.ajax({
			url : '../bankMgtController/pay_addinterface.do',
			data : {
				interfacename : $('#interfaceName').val(),
				interfacetype : $('#interfaceType').combobox('getValue'),
				interfaceaddress : $('#interfaceAddress').val(),
				interfacestatus : $('#interfaceStatus').combobox('getValue'),
				updateInterfaceBankId : $('#updateInterfaceBankId').val(),
				interfacelock : $('#interFaceLock').val(),
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data, 'info');
				$('#bankTableinterface').datagrid('reload');

				$('#interface').window('close');
			},
			error : function() {

				$.messager.alert('提示信息', '异常！', 'info');
			}
		});
	} else {
		$.messager.alert('提示信息', '请检查数据的格式！', 'info');
	}
}

$('#serverNamebig').focusout(function() {
	var serverNamebig = $(this).val();
	if (serverNamebig != "") {
		var operate = $('#operate').val();
		var previousName = $('#previousName').val();
		var data = {};
		if (operate == "add") {
			data = {
				'serverNamebig' : serverNamebig
			};
		} else if (operate == "update") {
			data = {
				'serverNamebig' : serverNamebig,
				'previousName' : previousName
			};
		}
		$.ajax({
			url : '../bankMgtController/is_server_named.do',
			type : 'POST',
			data : data,
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data) {
					$.messager.alert('提示信息', "服务器名称已被使用，请重新输入！", 'info');
					$('#serverNamebig').val("");
				}
			},
			error : function() {
				$.messager.alert('提示信息', '异常！', 'info');
			}
		});
	} else {
		$.messager.alert('提示信息', "请输入服务器名称！", 'info');
	}
});

function saveServer() {
	if ($("#serverform").form('validate')) {
		var serverType = $('#serverType').combobox('getValue');
		var serverAddress = $('#serverAddress').val();
		var serverStatus = $('#serverStatus').combobox('getValue');
		var serverNamebig = $('#serverNamebig').val();
		var operate = $('#operate').val();
		var updateBankId = $('#updateServerBankId').val();
		var data = {};
		if (operate == "add") {
			data = {
				'serverType' : serverType,
				'serverAddress' : serverAddress
			};
		}
		$.ajax({
			url : '../bankMgtController/is_server_exist.do',
			data : data,
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data) {
					$.messager.alert('提示信息', "存在相同的服务器地址,不能添加！", 'info');
				} else {
					$.ajax({
						url : '../bankMgtController/pay_saveserver.do',
						data : {
							serverType : serverType,
							serverStatus : serverStatus,
							serverLock : $('#serverLock').val(),
							updateBankId : updateBankId,
							serverAddress : serverAddress,
							serverNamebig : serverNamebig
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							$.messager.alert('提示信息', data, 'info');
							$('#bankTableserver').datagrid('reload');
							$('#server').window('close');
						},
						error : function() {

							$.messager.alert('提示信息', '异常！', 'info');
						}
					});
				}
			},
			error : function() {
				$.messager.alert('提示信息', '异常！', 'info');
			}
		});
	} else {
		$.messager.alert('提示信息', '请检查数据的格式！', 'info');
	}
}
function updateServer() {
	if ($("#serverform").form('validate')) {
		var serverType = $('#serverType').combobox('getValue');
		var serverAddress = $('#serverAddress').val();
		var serverStatus = $('#serverStatus').combobox('getValue');
		var serverNamebig = $('#serverNamebig').val();
		var operate = $('#operate').val();
		var data = {};
		if (operate == "update") {
			var previousServerType = $("#previousServerType").val();
			var previousServerAddress = $("#previousServerAddress").val();
			data = {
				'serverType' : serverType,
				'serverAddress' : serverAddress,
				'previousServerAddress' : previousServerAddress,
				'previousServerType' : previousServerType
			};
		}
		$.ajax({
			url : '../bankMgtController/is_server_exist.do',
			data : data,
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data) {
					$.messager.alert('提示信息', "存在相同的服务器地址,不能添加！", 'info');
				} else {
					$.ajax({
						url : '../bankMgtController/pay_updateserver.do',
						data : {
							servertype : serverType,
							serverstatus : serverStatus,
							serverlock : $('#serverLock').val(),
							updatebankId : $('#updateServerBankId').val(),
							serveraddress : serverAddress,
							servername : $('#serverId').val(),
							serverNamebig : serverNamebig
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							$.messager.alert('提示信息', data, 'info');
							$('#bankTableserver').datagrid('reload');
							$('#server').window('close');

						},
						error : function() {
							$.messager.alert('提示信息', '异常！', 'info');
						}
					});
				}
			}
		});
	} else {
		$.messager.alert('提示信息', '请检查数据的格式！', 'info');
	}
}
function updateInterface() {
	if ($("#interfaceform").form('validate')) {

		$.ajax({
			url : '../bankMgtController/pay_updateinterface.do',
			data : {
				interfaceId : $('#interfaceName').val(),
				serverType : $('#interfaceType').combobox('getValue'),
				interfaceClass : $('#interfaceAddress').val(),
				status : $('#interfaceStatus').combobox('getValue'),
				bankId : $('#updateInterfaceBankId').val()
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data, 'info');
				$('#bankTableinterface').datagrid('reload');
				$('#interface').window('close');
			},
			error : function() {

				$.messager.alert('提示信息', '异常！', 'info');
			}
		});
	} else {
		$.messager.alert('提示信息', '请检查数据的格式！', 'info');
	}
}

// $('#ruleContext').focusout(function() {
// var reg = new RegExp("^/d*$");
// var obj = $(this).val();
// if ("" == obj) {
// $.messager.alert('提示信息', '请输入规则内容！', 'info');
// return;
// }
// if (!reg.test(obj.value)) {
// $.messager.alert('提示信息', '规则内容的位数不符合条件！', 'info');
// return;
// }
// });

function saveRule() {

	if ($("#addRuleform").form('validate')) {
		var start = $('#ruleStart').val();
		var end = $('#ruleEnd').val();
		var content = $('#ruleContext').val();
		var length = $('#acctLength').val();
		if (ruleCheck(length, start, end, content)) {
			var node = $('#banktree').tree('getSelected');

			var perview = buildPreview(length, start, end, content);

			$.ajax({
				url : '../bankMgtController/pay_saverule.do',
				data : {
					acctType : $('#acctType').combobox('getValue'),
					bankId : node.id,
					parentRuleId : $('#parentRuleId').combobox('getValue'),
					acctLength : $('#acctLength').val(),
					ruleStart : $('#ruleStart').val(),
					ruleEnd : $('#ruleEnd').val(),
					ruleContent : $('#ruleContext').val(),
					status : $('#ruleStatus').combobox('getValue'),
					rulePreview : perview
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$.messager.alert('提示信息', data, 'info');
					$('#addRule').window('close');
					$('#bankTablerule').datagrid('reload');

				},
				error : function() {
					$.messager.alert('提示信息', '异常！', 'info');
				}
			});
		}
	}
}

function updateRule() {
	if ($("#updateRuleform").form('validate')) {
		var start = $('#uRuleStart').val();
		var end = $('#uRuleEnd').val();
		var content = $('#uRuleContext').val();
		var length = $('#uAcctLength').val();
		if (ruleCheck(length, start, end, content)) {
			var node = $('#banktree').tree('getSelected');

			var perview = buildUpdatePreview(length, start, end, content);
			var row = $('#bankTablerule').datagrid('getSelected');

			$.ajax({
				url : '../bankMgtController/pay_updaterule.do',
				data : {
					bankRuleId : row.bankRuleId,
					acctType : $('#uAcctType').combobox('getValue'),
					bankId : node.id,
					parentRuleId : $('#uParentRuleId').combobox('getValue'),
					acctLength : $('#uAcctLength').val(),
					ruleStart : $('#uRuleStart').val(),
					ruleEnd : $('#uRuleEnd').val(),
					ruleContent : $('#uRuleContext').val(),
					status : $('#uRuleStatus').combobox('getValue'),
					rulePreview : perview
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					$.messager.alert('提示信息', data, 'info');
					$('#bankTablerule').datagrid('reload');
					$('#updateRule').window('close');
				},
				error : function() {
					$.messager.alert('提示信息', '异常！', 'info');
				}
			});
		}
	}
}
function updateBank() {
	if ($("#bankupdateform").form('validate')) {
		$.ajax({
			url : '../bankMgtController/updatebank.do',
			data : {
				id : $('#updateBankId').val(),
				parentid : $('#updateParentId').val(),
				text : $('#updateBankName').val()
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data, 'info');
				$('#updateBankWin').window('close');
				$('#banktree').tree("reload");
			},
			error : function() {
				$.messager.alert('提示信息', '异常！', 'info');
			}
		});
	} else {
		$.messager.alert('提示信息', '请检查数据的格式！', 'info');
	}
}

function buildPreview(length, start, end, content) {
	var tempStr = "";
	if (!$("#parentRuleId").closest("tr").is(":hidden")) {
		var pid = $("#parentRuleId").combobox('getText');
		if (pid != '') {
			tempStr = pid.substring(0, start - 1) + content + pid.substring(end, length);
		} else {
			$.messager.alert('提示信息', '请选择父规则！', 'info');
		}
	} else {
		var pidDefault = "********************";
		tempStr = pidDefault.substring(0, start - 1) + content + pidDefault.substring(end, length);
	}

	return tempStr;
}
function buildUpdatePreview(length, start, end, content) {
	var tempStr = "";
	if (!$("#uParentRuleId").closest("tr").is(":hidden")) {
		var pid = $("#uParentRuleId").combobox('getText');
		if (pid != '') {
			tempStr = pid.substring(0, start - 1) + content + pid.substring(end, length);
		} else {
			$.messager.alert('提示信息', '请选择父规则！', 'info');
		}
	} else {
		var pidDefault = "********************";
		tempStr = pidDefault.substring(0, start - 1) + content + pidDefault.substring(end, length);
	}

	return tempStr;
}

function ruleCheck(length, start, end, content) {

	if (Number(start) > Number(length)) {
		$.messager.alert('提示信息', '起始位置不能大于银行卡位数！', 'info');
		return false;
	}

	if (Number(end) > Number(length)) {
		$.messager.alert('提示信息', '终止位置不能大于银行卡位数！', 'info');
		return false;
	}

	if (Number(start) >= Number(end)) {
		$.messager.alert('提示信息', '起始位置不能大于等于终止位置！', 'info');
		return false;
	}

	if (Number(content.length) != ((Number(end) - Number(start)) + 1)) {
		$.messager.alert('提示信息', '规则内容与长度不符！', 'info');
		return false;
	}

	// 验证父规则是否为空
	if (!$("#parentRuleId").closest("tr").is(":hidden")) {
		if ($('#parentRuleId').combobox('getValue') == '') {
			$.messager.alert('提示信息', '请选择父规则！', 'info');
			return false;
		}
	}

	return true;
}

function setAcctTypeOnSelect() {
	
	$('#acctType').combobox({
		//银行卡类型变化触发事件
		onChange : function(n,o){
			var acctType = $('#acctType').combobox('getValue');
			var node = $('#banktree').tree('getSelected');
			var lv =  $('#banktree').tree("getLevel",node.target);
			if(lv>2){
				$('#parentRuleId').combobox({
					url : '../bankMgtController/getFatherRule.do?bankRuleId=' + node.parentBankId + '&acctType=' + acctType,
					editable : false,
					valueField : 'bankRuleId',
					textField : 'rulePreview',
					onChange : function(n,o) {
						$("#acctLength").val($('#parentRuleId').combobox('getText').length);
						$("#acctLength").attr('readonly',true);
					}
				});
			} else {
				$("#acctLength").attr('readonly',false);
			}
		}
	});
	$('#uAcctType').combobox({
		onChange : function(n,o){
			var acctType = $('#uAcctType').combobox('getValue');
			var node = $('#banktree').tree('getSelected');
			var lv =  $('#banktree').tree("getLevel",node.target);
			if(lv>2){
				$('#uParentRuleId').combobox({
					url : '../bankMgtController/getFatherRule.do?bankRuleId=' + node.parentBankId+ '&acctType=' + acctType,
					editable : false,
					valueField : 'bankRuleId',
					textField : 'rulePreview',
					onChange : function(n,o) {
						$("#uAcctLength").val($('#uParentRuleId').combobox('getText').length);
						$("#uAcctLength").attr('readonly',true);
					}
				});
			}else {
				$("#acctLength").attr('readonly',false);
			}
		}
	});
}

function statusFormat(val, row) {
	if (val == 0) {
		// return '<span style="color:red;">('+val+')</span>';
		return '启用';
	} else if (val == 1) {
		return '停用';
	} else {
		return '未知';
	}
}

function typeFormat(val, row) {
	if (val == 1) {
		// return '<span style="color:red;">('+val+')</span>';
		return '银企直联';
	} else if (val == 2) {
		return '网银';
	} else {
		return '未知';
	}
}

$.extend($.fn.tree.methods, {
	getLevel:function(jq,target){
	var l = $(target).parentsUntil("ul.tree","ul");
	return l.length+1;
	}
});
