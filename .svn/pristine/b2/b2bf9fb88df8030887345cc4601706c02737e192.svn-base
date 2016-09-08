function setManulAjustPanel() {
	var noteText = '人工调整管理';
	var exist_tab = $('#centerWorkArea').tabs('getTab', noteText);
	if (exist_tab) {
		$('#centerWorkArea').tabs('select', noteText);
		return;
	} else {
		$('#centerWorkArea').tabs('add', {
			title : noteText,
			iconCls : 'icon-rgtzmgt',
			content : '<div class="easyui-panel" href="../payment/js/manulAjust.jsp" border="false" style="width:100%;height:100%;padding:2px;"></div>',
			closable : true
		});
		setTimeout(function() {
			$('#manulSearchTable').show();
			setManulTreeGridTable();
		}, 100);
	}
}

function removePanel() {
	var tab = $('#centerWorkArea').tabs('getSelected');
	if (tab) {
		var index = $('#centerWorkArea').tabs('getTabIndex', tab);
		$('#centerWorkArea').tabs('close', index);
	}
}

//data-options="method: 'get',rownumbers: true,showFooter: true,idField: 'id',treeField: 'name'"
function setManulTreeGridTable() {
	$('#manulTreeGridTable').treegrid({
		url:'../manulAjustController/list.do',
		method: 'get',
		rownumbers: false,
		showFooter: false,
		idField: 'id',
		treeField: 'name',
		toolbar:[ 
		{
			text : '添加',
			iconCls : 'icon-pay-add',
			handler : function() {
				showAddManulWin();
			}
		}, '-', 
		{
			text : '修改',
			iconCls : 'icon-pay-update',
			handler : function() {
				showEditManulWin();
			}
		}, '-', 
		{
			text : '删除',
			iconCls : 'icon-pay-delete',
			handler : function() {
				deleteManul();
			}
		}],
		columns:[[
			{
				title:'name',field:'name',width:'18%'
			},
		    {
		    	title:'操作类型',field:'code',width:'6%',align:'center'
		    },
		    {
		    	title:'主账号',field:'accountNo',width:'11%',align:'center'
		    },
		    {
		    	title:'支付帐号',field:'payAccNo',width:'11%',align:'center'
		    },
		    {
		    	title:'转帐金额',field:'tranAmt',width:'7%',align:'center'
		    },
		    {
		    	title:'收款帐号',field:'recvAccNo',width:'11%',align:'center'
		    },
		    {
		    	title:'收款帐号名称',field:'recvAccNm',width:'10%',align:'center'
		    },
		    {
		    	title:'用户名称',field:'userName',width:'6%',align:'center'
		    },
		    {
		    	title:'处理状态',field:'bankStatus',width:'6%',align:'center'
		    },
		    {
		    	title:'银行提示',field:'statusText',width:'8%',align:'center'
		    },
		    {
		    	title:'操作',field:'id',width:'7%',formatter:function(value, row, index){
	    			if (row.nodeLevel == 0){
	    				var $html = '<input type="button" value="处理成功" name="manulOperate" data-id="'+ row.id + '"><br/><input type="button" value="放入任务队列" name="addIntoQueue" data-id="'+ row.id + '">';
	    				return $html;
	    			}
		    	}
		    }
	    ]],
	    onLoadSuccess:function(row){
	    	$("input[name='addIntoQueue']").bind("click",function(){
	    		$(this).css("disabled","disabled");
	    		//后台接收报文接口js
	    		var flowID = $(this).attr("data-id");
	    		$.ajax({
	    			url : '../manulAjustController/into_queue.do?time='+new Date(),// 跳转到 action
	    			data : {'flowID':flowID},
	    			type : 'POST',
	    			cache : false,
	    			dataType : 'json',
	    			success : function(data) {
	    				$.messager.alert('提示信息', data.content, 'info');
	    				if (data.result == 'success') {
	    					$('#manulTreeGridTable').treegrid('reload');
	    				}
	    			},
	    			error : function() {
	    				$.messager.alert("异常！");
	    			}
	    		});
	    		
	    	});
	    	$("input[name='manulOperate']").bind("click",function(){
	    		$(this).css("disabled","disabled");
	    		//后台接收报文接口js
	    		var flowID = $(this).attr("data-id");
	    		$.ajax({
	    			url : '../manulAjustController/success.do?time='+new Date(),// 跳转到 action
	    			data : {'flowID':flowID},
	    			type : 'POST',
	    			cache : false,
	    			dataType : 'json',
	    			success : function(data) {
	    				$.messager.alert('提示信息', data.content, 'info');
	    				if (data.result == 'success') {
	    					$('#manulTreeGridTable').treegrid('reload');
	    				}
	    			},
	    			error : function() {
	    				$.messager.alert("异常！");
	    			}
	    		});
	    		
	    	});
	    }
	});
}

function showAddManulWin() {
	$("#addManulWindow").show();
	$('#addManulWindow').window('open');
	$('#addManulWindow').window('center');
}
function showEditManulWin() {

	var row = $('#manulTreeGridTable').treegrid('getSelected');
	if (row) {
		$("#editManulWindow").show();
		$('#editManulWindow').window('open');
		$('#editManulWindow').window('center');
		$('#flowID').val(row.flowID);

	} else {
		$.messager.alert('提示信息', '请先选择要更新的记录。', 'info');
	}
}

function addManul() {
	if ($("#addManulForm").form('validate')) {
		//处理表单信息，并序列化
		var $addManulForm = $("#addManulForm");
		var  integrity= $addManulForm.find("input[name='integrity']").val();
		var  operator = $("#addOperator").val();
		var  flowID= $("#addFlowID").val();
		var mallUrl=$addManulForm.find("input[name='mallUrl']").val();
		var factoringUrl=$addManulForm.find("input[name='factoringUrl']").val();
		var actionArray = new Array();
		var $itemTr = $("#addManulAjustTable tr.datatr");
		$itemTr.each(function(){
			var  code = $(this).find("input[name='code']").val();
			var  userName = $(this).find("input[name='userName']").val();
			var  accountNo = $(this).find("input[name='accountNo']").val();
			var  recvAccNo =$(this).find("input[name='recvAccNo']").val();
			var  recvAccNm = $(this).find("input[name='recvAccNm']").val();
			var  tranAmt = $(this).find("input[name='tranAmt']").val();
			var  memo = $(this).find("input[name='memo']").val();
			var freezeNo = $(this).find("input[name='freezeNo']").val();
			var tranFlag = $(this).find("input[name='tranFlag']").val();
			var payAccNo = $(this).find("input[name='payAccNo']").val();
			var clientID = $(this).find("input[name='clientID']").val();
			var tranType = $(this).find("input[name='transType']").val();
			
			var action = new Object();
			action.code = code;
			action.userName = userName;
			action.accountNo = accountNo;
			action.payAccNo = payAccNo;
			action.recvAccNo = recvAccNo;
			action.recvAccNm = recvAccNm;
			action.tranAmt = tranAmt;
			action.memo = memo;
			action.freezeNo = freezeNo;
			action.tranFlag = tranFlag;
			action.clientID = clientID;
			action.tranType = tranType;//tansfer 转账
			actionArray.push(action);

		});

		var message = new Object();
		message.integrity = integrity;
		message.operator = operator;

		var userDataList = new Array();
		var msgList = new Object();
		msgList.flowID=flowID;
		msgList.action = actionArray;
		
		userDataList.push(msgList);
		message.list = userDataList;

		var callBack = new Object();
		callBack.mallUrl = mallUrl;//"http://127.0.0.1:8080/Payment/bankMgtController/toBank.do";
		callBack.factoringUrl = factoringUrl;//"http://127.0.0.1:8080/Payment/bankMgtController/toBank.do";
		message.callBack = callBack;

		console.log(JSON.stringify(message));

		var  sender= $addManulForm.find("input[name='sender']").val();
		//后台接收报文接口js
		$.ajax({
			url : '../manulAjustController/add_task.do?time='+new Date(),// 跳转到 action
			data : {'data':JSON.stringify(message), 'sender':sender},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data.content, 'info');
		
				if (data.result == 'success') {
//					$('#addManulWindow').dialog('close');
//					$("#manulTreeGridTable").treegrid("reload", {
//					operator : $("#operator").val(),
//					userName : $("#userName").val(),
//					tranAmt : $("#tranAmt").val(),
//					recvAccNo : $("#recvAccNo").val(),
//					flowID : $("#flowID").val()
//					});
					$("#addOperator").val("");
					$("#addFlowID").val("");
					//$("#addManulAjustTable tr.datatr").remove();
				}
        
			},
			error : function() {
        
				alert("异常！");
			}
		});

	}

}

function editManul() {

	if ($("#editManulForm").form('validate')) {
		$.ajax({
			url : '..//.do',// 跳转到 action
			data : {
				operator : $("#operator").val(),
				userName : $("#userName").val(),
				tranAmt : $("#tranAmt").val(),
				recvAccNo : $("#recvAccNo").val(),
				flowID : $("#flowID").val()
				//isDefault : $("#uIsDefault").combobox('getValue')
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示信息', data.result, 'info');
				$('#editManulWindow').dialog('close');
				$("#manulTreeGridTable").treegrid("reload", {
					operator : $("#operator").val(),
					userName : $("#userName").val(),
					tranAmt : $("#tranAmt").val(),
					recvAccNo : $("#recvAccNo").val(),
					flowID : $("#flowID").val()
					//isDefault : $("#cIsDefault").combobox('getValue')
				});
			},
			error : function() {

				alert("异常！");
			}
		});

	}

}

function deleteManul() {
	$.ajax({
		url : '../acctMgtController/testRedis.do',// 跳转到 action
		data : {
		// 参数 row.
		},
		type : 'POST',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$.messager.alert('提示信息', data.result, 'info');
		},
		error : function() {
			$.messager.alert("异常！");
		}
	});

}

function queryManulByCon() {
	$("#manulTreeGridTable").treegrid("load", {
		operator : $("#operator").val(),
		userName : $("#userName").val(),
		tranAmt : $("#tranAmt").val(),
		recvAccNo : $("#recvAccNo").val(),
		flowID : $("#flowID").val()
	});

}


function addManulData(){
	var index = $("#addManulAjustTable").find("tr").length;
	
	var trHtml = '<tr data-id='+index+' class="datatr">'
		+'<td><input name="code" class="easyui-validatebox" value="" style="width:100%;"></td>'
		+'<td><input name="userName" class="easyui-validatebox"  value="测试" style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="clientID" class="easyui-validatebox"  value="dotest" style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="accountNo" class="easyui-validatebox" value=""  style="width:100%;" data-options="prompt:\'请输入16-19位银行账号\',missingMessage:\'此项为必填项\',required:true,validType:\'length[16,19]\'"></td>'
		+'<td><input name="payAccNo" class="easyui-validatebox"  value="" style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="recvAccNo" class="easyui-validatebox"  value="" style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="recvAccNm" class="easyui-validatebox" value=""  style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="tranAmt" class="easyui-validatebox" value=""  style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="freezeNo" class="easyui-validatebox" value="0"  style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="tranFlag" class="easyui-validatebox" value="false"  style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name="transType" class="easyui-validatebox" value="BF"  style="width:100%;" data-options="missingMessage:\'此项为必填项\',required:true"></td>'
		+'<td><input name=" memo" class="easyui-validatebox" value=""  style="width:100%;" ></td>'
		+'<td class="remove"><a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-pay-serach" style="width:100%;">删 除</a></td>'
		+'</tr>';
	$("#addManulAjustTable").append(trHtml);
	$("#addManulAjustTable tr[data-id='"+index+"'] td.remove a.easyui-linkbutton").click(function(){
		var $tr = $(this).parent().parent();//.closest("tr.datatr");
		$tr.remove();
	});
}

