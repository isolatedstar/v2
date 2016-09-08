<!DOCTYPE HTML>
<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">

	<link href="${ctx}/mall/css/bootstrap-table.css" rel="stylesheet">

	<script src="${ctx}/mall/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
	<script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
	<script src="${ctx}/mall/js/bootstrap-table.js"></script>
	<script src="${ctx}/mall/js/bootstrap-table-zh-CN.js"></script>

    <script src="${ctx}/mall/js/serializeJson.js"></script>
    <!-- 协议详情 js-->
    <script src="${ctx}/mall/js/contractDetail.js"></script>

    <script src="${ctx}/mall/js/common/common.js"></script>
</head>

<body>
<div class="container-fluid ">
	<div class="row-fluid">
		<div class="col-sm-12 ">
			<div class="panel panel-default article-bj">
				<div class="panel-heading box-shodm">执行中合作协议</div>
				<form id="queryPendingOrderForm">
					<div class="row wrapper form-margin" style="margin:15px;">

						<div class="col-md-3">
							<div class="input-group">
								<div class="input-group-btn">
									<h5 class="h5-margin">协议类型:</h5>
								</div>
								<select id="contractType" name="contractType" tabindex="-1" class="form-control" onchange="queryRunningContract()">
									<option value="1" selected="selected">采购协议</option>
									<option value="2">销售协议</option>
								</select>
							</div>
						</div>

						<div class="col-md-4">
							<div class="input-group">
								<div class="input-group-btn">
									<h5 class="h5-margin">协议对方:</h5>
								</div>
								<input type="text" placeholder="" class="form-control" name="name" id="name"/>
							</div>
						</div>

                        <div class="col-md-3">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <h5 class="h5-margin">协议状态:</h5>
                                </div>
                                <select id="contractStatus" name="contractStatus" tabindex="-1" class="form-control" onchange="queryRunningContract()">
                                    <option value="3" selected="selected">执行中</option>
                                    <option value="7">已终止</option>
                                </select>
                            </div>
                        </div>


						<input type="hidden" name ="mmbId" id="mmbId" value="${mmbId!}">

						<input type="button" class="btn btn-info btn-s-md btn-default  cx-btn-margin"  value="查询" onclick="queryRunningContract();" id="queryRunningContractButton"/>
					</div>
				</form>
				<div class="table-responsive panel-table-body ">
					<table class="table table-striped table-hover " id="tb_runningContract"></table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 协议详情modal-->
<div class="modal fade" id="contractDetail" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static"></div>

<script type="text/javascript">

	$(function(){
		var oTable = new tableInit();
        oTable.Init();

	});


	var tableInit = function(){
		var oTableInit = new Object();
		//初始化table
		oTableInit.Init = function(){
			$("#tb_runningContract").bootstrapTable({
				url : "${ctx}/contract/queryContractByPageType.do",
				method : "POST",
				dataType : "json",
				classes : "table table-no-bordered",
                contentType : "application/x-www-form-urlencoded",
				striped : true,
				cache : false,
				pagination : true,
				sortable : false,
				sortOrder : "asc",
				queryParams : oTableInit.queryParams, //传递参数（*）
				sidePagination : "server",
				pageNumber : 1,
				pageSize : 10,
				pageList : [10],
				search : false,
                strictSearch : false,
                showColumns : false,
                showRefresh : false,
                showFooter: true,
                minimumCountColumns : 2,
                clickToSelect : false,
                uniqueId : "id",
                showToggle : false,
                cardView : false,
                detailView : false,
                paginationPreText : "«",
                paginationNextText : "»",
				columns : [
					{
						field : "contractTitle",
						title : "标题",
						align : "center",
						valign : "middle",
						sortable : false,
						formatter : function(value,row,index){
							return '<a data-toggle="modal" href="#" onclick="openContractDetail(\''+row.id+'\')">'+row.contractTitle+'</a>'
						}
					},
                    {
                        field : "mmbName",
                        title : "协议对方",
                        align : "center",
                        valign : "middle",
                        sortable : false
                    },
					{
						field : "startTime",
						title : "开始时间",
                        align : "center",
                        valign : "middle",
                        sortable : false,
                        formatter : function(value,row,index){
                            return $.changeDate(value);
                        }
					},
                    {
                        field : "endTime",
                        title : "结束时间",
                        align : "center",
                        valign : "middle",
                        sortable : false,
                        formatter : function(value,row,index){
                            return $.changeDate(value);
                        }
                    },
                    {
                        field : "pay_type_",
                        title : "付款期",
                        align : "center",
                        valign : "middle",
                        sortable : false
                    },
                    {
                        field : "flow_type_",
                        title : "运输方式",
                        align : "center",
                        valign : "middle",
                        sortable : false
                    },
                    {
                        title: "操作",
                        align: "center",
                        valign: "middle",
                        sortable: false,
                        formatter: function (value, row, index) {

							var btnHtml ='';
							var currentId = $("#mmbId").val();
							if(currentId==row.firstMmbId){
								if(row.contractStatus==3){
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="operateContract(\'terminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">申请终止</button> </a> ';
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="toCreateOrder(\'' + row.id + '\');"><button class="btn btn-info btn-xs">下订单</button> </a> ';
								}else if(row.contractStatus==5){
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="operateContract(\'recallTerminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">撤回终止 </button></a>';
								}else if(row.contractStatus==6){
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="operateContract(\'agreeTerminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">同意终止 </button></a>'+
                                    '<a href="#" data-toggle="modal" onclick="operateContract(\'refuseTerminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">拒绝终止 </button></a>';
								}else if(row.contractStatus==7){
                                    btnHtml += '<button class="btn btn-info btn-xs" disabled="disabled">已终止 </button>';
                                }
							}else {
                                if(row.contractStatus==3){
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="operateContract(\'terminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">申请终止</button> </a> ';
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="toCreateOrder(\'' + row.id + '\');"><button class="btn btn-info btn-xs">下订单</button> </a> ';
								}else if(row.contractStatus==5){
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="operateContract(\'agreeTerminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">同意终止 </button></a>'+
                                            '<a href="#" data-toggle="modal" onclick="operateContract(\'refuseTerminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">拒绝终止 </button></a>';
                                }else if(row.contractStatus==6){
                                    btnHtml += '<a href="#" data-toggle="modal" onclick="operateContract(\'recallTerminate\',\'' + row.id + '\',\''+row.contractStatus+'\');"><button class="btn btn-info btn-xs">撤回终止 </button></a>';
                                }else if(row.contractStatus==7){
                                    btnHtml += '<button class="btn btn-info btn-xs" disabled="disabled">已终止 </button>';
                                }
							}
                            return btnHtml;

                        }
                    }
                ]


			});
		};

        //得到查询的参数
        oTableInit.queryParams = function(params) {
            var temp = {
				pageType : "running",
                pageNo : params.offset,
                pageSize : params.limit,
                name : $("#name").val(),
                mmbId : $("#mmbId").val(),
                contractType : $("#contractType option:selected").val(),
                contractStatus : $("#contractStatus option:selected").val()
            };
            return temp;
        };
        return oTableInit;
	};

	//查询
	function queryRunningContract(){
        var pageSize =  $("#tb_runningContract").bootstrapTable("getOptions").pageSize;
		$.ajax({

			url : "${ctx}/contract/queryContractByPageType.do?pageNo=0&pageSize="+pageSize,
			type : "post",
			dataType : "json",
			data: {
                pageType : "running",
                mmbId : $("#mmbId").val(),
                name : $("#name").val(),
                contractType : $("#contractType option:selected").val(),
                contractStatus : $("#contractStatus option:selected").val()
			},
            cache : false,
			error : function(){
				alert("系统异常！");
			},
            success : function(data){
				$("#tb_runningContract").bootstrapTable("load",data);
			}

		});

	}

    //跳转到创建订单页面
    function toCreateOrder(id){
        var contractType = $("#contractType option:selected").val();
        window.location.href = "${ctx}/contract/toCreateOrder.do?id="+id+"&contractType="+contractType;
    }

//    申请终止，根据当前会员是买卖双方，状态置为 买卖方终止确认
//    撤回终止，状态置为 双方确认
//    拒绝终止，状态置为 双方确认
//    同意终止，状态置为 双方终止确认
	function operateContract(operateType,id,currentStatus){
		$.ajax({
			url : "${ctx}/contract/operateContract.do",
			type : "post",
			dataType : "json",
			cache : false,
			data : {
				"contractType" : $("#contractType option:selected").val(),
				"operateType" : operateType,
				"id" : id,
				"currentStatus" :currentStatus
			},
			error : function(){
				alert("系统异常！");
                queryRunningContract();
			},
			success : function(data){
				if(data.msg!=null){
					alert(data.msg);
				}
                queryRunningContract();
			}

		});
	}

	//显示协议详情模态框
	function openContractDetail(id){

		var contractType = $("#contractType").val();
		var url = '${ctx}/contract/toContractDetailPage.do';

	    new contractDetail("contractDetail",id,contractType,url);
	}



</script>
</body>

</html>