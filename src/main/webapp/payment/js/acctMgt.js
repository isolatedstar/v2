function setAcctMgtPanel() {
	var noteText = '账户信息管理';
	var exist_tab = $('#centerWorkArea').tabs('getTab', noteText);
	if (exist_tab) {
		$('#centerWorkArea').tabs('select', noteText);
		return;
	} else {
		$('#centerWorkArea').tabs('add', {
			title : noteText,
			iconCls : 'icon-acctmgt',
			content : '<div class="easyui-panel" href="../payment/js/acctMgt.jsp" border="false" style="width:100%;height:100%;padding:2px;"></div>',
			closable : true
		});

		setTimeout(function() {
			$('#conTable').show();
			setAcctMgtTable();
			setMasterAcct();
		}, 500);
	}
}

function setAcctMgtTable() {

	$('#acctTable').datagrid({
		url : '',
		pagination : true,// 显示分页
		fit : true,// 自动大小
		fitColumns : true,
		nowrap : false,
		striped : true,
		total : '0',
		singleSelect : true,// 是否单选
		rownumbers : true,
		iconCls : "icon-save",// 图标
		title : "银行账号管理",
		columns : [ [ {
			field : 'bankName',
			title : '银行',
			width : 230,
			align : 'center'
		}, {
			field : 'bankAcct',
			title : '银行账号',
			width : 200,
			align : 'center'
		}, {
			field : 'acctName',
			title : '账户名称',
			width : 200,
			align : 'center'
		}, {
			field : 'acctTypeName',
			title : '账户类型',
			width : 100,
			align : 'center'
		}, {
			field : 'masterAcct',
			title : '所属主户',
			width : 200,
			align : 'center'
		}, {
			field : 'masterAcctName',
			title : '所属主户名称',
			width : 200,
			align : 'center'
		}, {
			field : 'status',
			title : '账户状态',
			width : 100,
			formatter : statusFormat,
			align : 'center'
		}, {
			field : 'orgGroupName',
			title : '集团名称',
			width : 200,
			align : 'center'
		}, {
			field : 'isDefault',
			title : '默认账户',
			width : 100,
			formatter : isDefaultFormat,
			align : 'center'
		} ] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-pay-add',
			handler : function() {
				showAddAcctWin();
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-pay-update',
			handler : function() {
				showEditAcctWin();
			}
		}
		// , '-', {
		// text : '删除',
		// iconCls : 'icon-pay-delete',
		// handler : function() {
		// deleteAcct();
		// }
		// }
		],
	});

}

function showAddAcctWin() {
	$("#addAcctWindow").show();
	$('#addAcctWindow').window('open');
	$('#addAcctWindow').window('center');

}

function showEditAcctWin() {

	var row = $('#acctTable').datagrid('getSelected');
	if (row) {

		$("#editAcctWindow").show();
		$('#editAcctWindow').window('open');
		$('#editAcctWindow').window('center');
		$('#uBankId').combotree('setValue', row.bankId);
		$('#uBankAcct').val(row.bankAcct);
		$('#uAcctName').val(row.acctName);
		$('#uStatus').combobox('setValue', row.status);
		$('#uOrgGroupId').val(row.orgGroupId);
		$('#uOrgGroupName').val(row.orgGroupName);
		$('#uIsDefault').combobox('setValue', row.isDefault);
		$('#uAcctTypeId').combobox('setValue', row.acctTypeId);
		
		if (row.acctTypeId == "2") {
			$('#uMasterAcct').combobox({
				disabled : false,
				required:true
			});
			$('#uMasterAcct').combobox({  
				editable:'false',
				url:'../acctMgtController/queryMasterAcct.do?bankId='+row.bankId,
				method:'get',
				valueField:'bankAcct',
				textField:'bankAcct'
		    });  
			$('#uMasterAcct').combobox('setValue', row.masterAcct);
		} else {
			$('#uMasterAcct').combobox({
				disabled : true
			});
		}

	} else {
		$.messager.alert('提示信息', '请先选择要更新的记录。', 'info');
	}
}

function reset() {

	$('#bankId').combotree('clear');
	$('#bankAcct').val('');
	$('#acctName').val('');
	$('#status').combobox('clear');
	$('#masterAcct').combobox({
		disabled : false
	});
	$('#masterAcct').combobox('clear');
	$('#orgGroupId').val('');
	$('#orgGroupName').val('');
	$('#isDefault').combobox('clear');
	$('#acctTypeId').combobox('clear');

}

function setMasterAcct() {

	$('#acctTypeId').combobox({
		onSelect : function(record) {

			if (record.itemCode == "2") {
				$('#masterAcct').combobox({
					disabled : false,
					required:true
				});
			} else {
				$('#masterAcct').combobox({
					disabled : true
				});
			}
		}
	});

	$('#uAcctTypeId').combobox({
		onSelect : function(record) {

			if (record.itemCode == "2") {
				$('#uMasterAcct').combobox({
					disabled : false,
					required:true
				});
			} else {
				$('#uMasterAcct').combobox({
					disabled : true
				});
			}
		}
	});

	$('#bankId').combotree({
		onSelect : function(record) {

			if (record.id == 0) {
				$('#bankId').combotree('clear');
				$('#masterAcct').combobox('loadData','[]');  
				$.messager.alert('提示信息', '不能选择为 ' + record.text, 'info');
				return;
			}
			if (record.status == 1) {
				$('#bankId').combotree('clear');
				$('#masterAcct').combobox('loadData','[]');  
				$.messager.alert('提示信息', '不能选择已停用的银行', 'info');
				$('#masterAcct').combobox('clear');
				return;
			}
			$('#masterAcct').combobox({  
				editable:'false',
				url:'../acctMgtController/queryMasterAcct.do?bankId='+record.id,
				method:'get',
				prompt:'请选择所属主户',
				valueField:'bankAcct',
				textField:'bankAcct'
		    });  
		}
	});

	$('#uBankId').combotree({
		onSelect : function(record) {

			if (record.id == 0) {
				$('#uBankId').combotree('clear');
				$('#uMasterAcct').combobox('loadData','[]');  
				$.messager.alert('提示信息', '不能选择为 ' + record.text, 'info');
				return;
			}
			if (record.status == 1) {
				$('#uBankId').combotree('clear');
				$('#uMasterAcct').combobox('loadData','[]');  
				$.messager.alert('提示信息', '不能选择已停用的银行', 'info');
				return;
			}
			$('#uMasterAcct').combobox({  
				editable:'false',
				url:'../acctMgtController/queryMasterAcct.do?bankId='+record.id,
				method:'get',
				prompt:'请选择所属主户',
				valueField:'bankAcct',
				textField:'bankAcct'
		    });  
		}
	});

}

function addAcct() {

	if ($("#addAcctForm").form('validate')) {
		$.ajax({
			url : '../acctMgtController/addAcct.do',// 跳转到 action
			data : {
				bankAcct : $("#bankAcct").val(),
				acctName : $("#acctName").val(),
				acctTypeId : $('#acctTypeId').combobox('getValue'),
				acctTypeName : $("#acctTypeId").combobox('getText'),
				status : $("#status").combobox('getValue'),
				orgGroupId : $("#orgGroupId").val(),
				orgGroupName : $("#orgGroupName").val(),
				bankId : $("#bankId").combobox('getValue'),
				masterAcct : $("#masterAcct").combobox('getValue'),
				isDefault : $("#isDefault").combobox('getValue')
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {

				$.messager.alert('提示信息', data.result, 'info');

				if (data.result == '添加成功！') {
					$('#addAcctWindow').dialog('close');
					$("#acctTable").datagrid("reload", {
						bankAcct : $("#cBankAcct").val(),
						acctName : $("#cAcctName").val(),
						acctTypeId : $('#cAcctTypeId').combobox('getValue'),
						acctTypeName : $("#cAcctTypeId").combobox('getText'),
						status : $("#cStatus").combobox('getValue'),
						orgGroupId : $("#cOrgGroupId").val(),
						orgGroupName : $("#cOrgGroupName").val(),
						bankId : $("#cBankId").combobox('getValue'),
						masterAcct : $("#cMasterAcct").combobox('getValue'),
						isDefault : $("#cIsDefault").combobox('getValue')
					});
				}

			},
			error : function() {

				alert("异常！");
			}
		});

	}

}

function editAcct() {

	if ($("#editAcctForm").form('validate')) {
		$.ajax({
			url : '../acctMgtController/editAcct.do',// 跳转到 action
			data : {
				bankAcct : $("#uBankAcct").val(),
				acctName : $("#uAcctName").val(),
				acctTypeId : $('#uAcctTypeId').combobox('getValue'),
				acctTypeName : $("#uAcctTypeId").combobox('getText'),
				status : $("#uStatus").combobox('getValue'),
				orgGroupId : $("#uOrgGroupId").val(),
				orgGroupName : $("#uOrgGroupName").val(),
				orgGroupId : $("#uOrgGroupId").val(),
				bankId : $("#uBankId").combobox('getValue'),
				masterAcct : $("#uMasterAcct").combobox('getValue'),
				isDefault : $("#uIsDefault").combobox('getValue')
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data.result, 'info');
				$('#editAcctWindow').dialog('close');
				$("#acctTable").datagrid("reload", {
					bankAcct : $("#cBankAcct").val(),
					acctName : $("#cAcctName").val(),
					acctTypeId : $('#cAcctTypeId').combobox('getValue'),
					acctTypeName : $("#cAcctTypeId").combobox('getText'),
					status : $("#cStatus").combobox('getValue'),
					orgGroupId : $("#cOrgGroupId").val(),
					orgGroupName : $("#cOrgGroupName").val(),
					bankId : $("#cBankId").combobox('getValue'),
					masterAcct : $("#cMasterAcct").combobox('getValue'),
					isDefault : $("#cIsDefault").combobox('getValue')
				});
			},
			error : function() {
				$.messager.alert('提示信息', '异常！', 'info');
			}
		});

	}

}

function deleteAcct() {

	var row = $('#acctTable').datagrid('getSelected');
	if (row) {

		var deleteMsg = $.messager.confirm("操作提示", "您确定要执行操作吗？", function(data) {
			if (data) {
				$.ajax({
					url : '../acctMgtController/deleteAcct.do',// 跳转到 action
					data : {
						bankAcct : row.bankAcct
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						$.messager.alert('提示信息', data.result, 'info');
						$('#editAcctWindow').dialog('close');
						$("#acctTable").datagrid("reload", {
							bankAcct : $("#cBankAcct").val(),
							acctName : $("#cAcctName").val(),
							acctTypeId : $('#cAcctTypeId').combobox('getValue'),
							acctTypeName : $("#cAcctTypeId").combobox('getText'),
							status : $("#cStatus").combobox('getValue'),
							orgGroupId : $("#cOrgGroupId").val(),
							orgGroupName : $("#cOrgGroupName").val(),
							bankId : $("#cBankId").combobox('getValue'),
							masterAcct : $("#cMasterAcct").combobox('getValue'),
							isDefault : $("#cIsDefault").combobox('getValue')
						});
					},
					error : function() {

						alert("异常！");
					}
				});
			} else {
				deleteMsg.close();
			}
		});

	} else {
		$.messager.alert('提示信息', '请先选择要删除的记录。', 'info');
	}

}

function queryAcctByCon() {

	if ($("#queryAcctForm").form('validate')) {
		var grid = $("#acctTable");
		$.extend(grid.datagrid("options"), {
			url : "../acctMgtController/queryAcctByCon.do",// 这里定义url
			queryParams : {
				bankAcct : $("#cBankAcct").val(),
				acctName : $("#cAcctName").val(),
				acctTypeId : $('#cAcctTypeId').combobox('getValue'),
				acctTypeName : $("#cAcctTypeId").combobox('getText'),
				status : $("#cStatus").combobox('getValue'),
				orgGroupId : $("#cOrgGroupId").val(),
				orgGroupName : $("#cOrgGroupName").val(),
				bankId : $("#cBankId").combobox('getValue'),
				masterAcct : $("#cMasterAcct").combobox('getValue'),
				isDefault : $("#cIsDefault").combobox('getValue')
			}
		});
		grid.datagrid("load");
	}
}

function statusFormat(val, row) {
	if (val == 1) {
		// return '<span style="color:red;">('+val+')</span>';
		return '启用';
	} else if (val == 2) {
		return '停用';
	} else {
		return '未知';
	}
}

function isDefaultFormat(val, row) {
	if (val == 1) {
		// return '<span style="color:red;">('+val+')</span>';
		return '是';
	} else if (val == 2) {
		return '否';
	} else {
		return '未知';
	}
}

function showAddOrgGroupIdWin() {
	$('#addOrgGroupIdWindow').window('open');
}

function showEditOrgGroupIdWin() {
	$('#editOrgGroupIdWindow').window('open');
}

function showOrgGroupWin(){
	$('#orgGroupIdBottomWindow').window('open');
}

function queryOrgGroupIdInfo() {
	$('#tableOrgGroupId').datagrid({
		fit : true,
		singleSelect:true,
		columns : [ [ {
			field : 'mmbFname',
			title : '集团名称',
			width : "100%",
			align : 'center'
		}, {
			field : 'id',
			title : '集团ID',
			width : "0%",
			align : 'center'
		} ] ],
		onDblClickRow : function(index, row) {
			document.getElementById("orgGroupName").value = row.mmbFname;
			document.getElementById("orgGroupId").value = row.id;
			$('#addOrgGroupIdWindow').dialog('close');
		}
	});
	$("#tableOrgGroupId").datagrid('hideColumn', 'id');
	$.ajax({
		url : '../acctMgtController/queryOrg.do',// 跳转到 action
		data : {
			orgGroupName : $("#orgGroupNamePara").val()
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$('#tableOrgGroupId').datagrid('loadData', data);
		},
		error : function() {
			alert("查询集团信息异常！");
		}
	});
}

function queryOrgGroupIdEditInfo() {
	$('#tableOrgGroupIdEdit').datagrid({
		fit : true,
		singleSelect:true,
		columns : [ [ {
			field : 'mmbFname',
			title : '集团名称',
			width : "100%",
			align : 'center'
		}, {
			field : 'id',
			title : '集团ID',
			width : "0%",
			align : 'center'
		} ] ],
		onDblClickRow : function(index, row) {
			document.getElementById("uOrgGroupName").value = row.mmbFname;
			document.getElementById("uOrgGroupId").value = row.id;
			$('#editOrgGroupIdWindow').dialog('close');
		}
	});
	$("#tableOrgGroupIdEdit").datagrid('hideColumn', 'id');
	$.ajax({
		url : '../acctMgtController/queryOrg.do',// 跳转到 action
		data : {
			orgGroupName : $("#orgGroupNameParaEdit").val()
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$('#tableOrgGroupIdEdit').datagrid('loadData', data);
		},
		error : function() {
			alert("查询集团信息异常！");
		}
	});
}

function queryOrgGroupIdInfoBottom() {
	$('#tableOrgGroupIdBottom').datagrid({
		fit : true,
		singleSelect:true,
		columns : [ [ {
			field : 'mmbFname',
			title : '集团名称',
			width : "100%",
			align : 'center'
		}, {
			field : 'id',
			title : '集团ID',
			width : "0%",
			align : 'center'
		} ] ],
		onDblClickRow : function(index, row) {
			document.getElementById("cOrgGroupName").value = row.mmbFname;
			document.getElementById("cOrgGroupId").value = row.id;
			$('#orgGroupIdBottomWindow').dialog('close');
		}
	});
	$("#tableOrgGroupIdBottom").datagrid('hideColumn', 'id');
	$.ajax({
		url : '../acctMgtController/queryOrg.do',// 跳转到 action
		data : {
			orgGroupName : $("#orgGroupNameParaBottom").val()
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$('#tableOrgGroupIdBottom').datagrid('loadData', data);
		},
		error : function() {
			alert("查询集团信息异常！");
		}
	});
}