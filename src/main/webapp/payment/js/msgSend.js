function setMessagePanel() {
	var noteText = '报文发送明细';
	var exist_tab = $('#centerWorkArea').tabs('getTab', noteText);
	if (exist_tab) {
		$('#centerWorkArea').tabs('select', noteText);
		return;
	} else {
		$('#centerWorkArea').tabs('add', {
			title : noteText,
			iconCls : 'icon-acctbalance',
			closable : true
		});

		setTimeout(function() {
			$('#msgSendConTable').show();
			setMsgSendTable();
		}, 500);
		
	}
}

function setMsgSendTable() {
	$('#msgSendTable').datagrid({
		url : '',
		pagination : true,// 显示分页
		fit : true,// 自动大小
		fitColumns:true,	
		nowrap : false,
		striped : true,	
		singleSelect : true,// 是否单选
		rownumbers : true,
		iconCls : "icon-save",// 图标
		title : "报文信息列表",
		columns : [ [ {
			field : 'bankFlowId',
			title : '银行流水号',
			width : 100,
			align : "center"
		},{
			field : 'msgFlowId',
			title : '报文流水号',
			width : 100,
			align : "center"
		},{
			field : 'bankAcct',
			title : '银行账号',
			width : 100,
			align : "center"
		},  {
			field : 'msgSender',
			title : '报文发送方',
			width : 100,
			align : "center"
		}, {
			field : 'msgRecevicer',
			title : '报文接收方',
			width : 100,
			align : "center"
		}, {
			field : 'statusName',
			title : '报文状态',
			width : 100,
			align : "center"
		}, {
			field : 'msgTypeName',
			title : '报文类型',
			width : 100,
			align : "center"
		}, {
			field : 'sendMsgTime',
			title : '报文发送时间',
			width : 200,
			align : "center",
			formatter : function(value, row, index) {
				var unixTimestamp = new Date(value);
				return unixTimestamp.toLocaleString();
			}
		}, {
			field : 'msgContext',
			title : '报文内容',
			width : 100,
			align : "center"
		},{
			field : 'lockAmt',
			title : '锁定金额',
			width : 100,
			align : "center"
		} ] ],
//		双击网格事件
//		onDblClickCell:function(rowIndex, field, value){
//			//if(field=="msgContext"){
//				showDetail(value);
//			//}
//		}
		onDblClickRow:function(rowIndex, rowData){
			showDetail(rowData.msgContext);
		}
	});
}

function showDetail(value) {
	$("#msgDetail").text(value);
	$("#detailWin").show();
	$('#detailWin').window('open');
	$('#detailWin').window('center');
}

function queryMsgSendByCon() {

	if ($("#queryMsgSendForm").form('validate')) {
		var grid = $("#msgSendTable");
		$.extend(grid.datagrid("options"), {
			url : "../msgSendController/queryMsgSendByCon.do",// 这里定义url
			queryParams : {
				bankAcct : $("#mcBankAcct").val(),
				msgFlowId : $("#cMsgFlowId").val(),
				msgType : $('#cMsgType').combobox('getValue'),
				status : $("#mcStatus").combobox('getValue'),
				msgSender : $("#cMsgSender").combobox('getText'),
				msgRecevicer : $("#cMsgRecevicer").combobox('getText')
			}
		});
		grid.datagrid("load");
	}
}