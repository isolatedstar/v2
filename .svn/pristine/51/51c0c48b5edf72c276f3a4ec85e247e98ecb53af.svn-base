<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.zllh.common.common.model.model_extend.UserExtendBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");
String memberId = "";
if(userExtendBean!=null){
	memberId = userExtendBean.getMuser().getMmbId();
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>执行中订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>mall/css/bootstrap.css" rel="stylesheet" >
<link href="<%=basePath%>mall/css/theme.css" rel="stylesheet">
<script src="<%=basePath%>mall/js/jquery.js"></script>
<script src="<%=basePath%>mall/js/common/common.js"></script>
<script src="<%=basePath%>mall/js/extendPagination.js" type="text/javascript" ></script>
<jsp:include page="orderJsInclude.jsp"></jsp:include>
</head>

<body>
	<div class="container-fluid ">
		<div class="row-fluid">
			<!-- col-sm-12 -->
			<div class="col-sm-12 ">
				<div class="panel panel-default article-bj">
					<div class="panel-heading box-shodm">执行中订单</div>
						<form id="queryOrderManageForm">
							<div class="row wrapper form-margin">
								<div class="col-md-4">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 class="h5-margin">订单号:</h5>
										</div>
										<input type="text" placeholder="" class="form-control" name="orderId" id="orderId"/>
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 class="h5-margin">订单状态:</h5>
										</div>
										<select id="executeStatus" name="executeStatus" tabindex="-1" class="form-control">
											<option value="0">全部</option>
											<option value="1">执行中</option>
											<option value="2">已完成</option>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 class="h5-margin">订单类型:</h5>
										</div>
										<select id="status" name="status" tabindex="-1" class="form-control">
											<option value="1">全部</option>
											<option value="2">采购</option>
											<option value="3">销售</option>
										</select>
									</div>
								</div>
								<br>
								<div class="col-md-4">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 class="h5-margin">交易对方:</h5>
										</div>
										<input type="text" placeholder="" class="form-control" name="name" id="name"/>
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 class="h5-margin">签约时间:</h5>
										</div>
										<input type="text" placeholder="" class="ui_timepicker form-control" id="startTime" name="startTime" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="input-group">
										<div class="input-group-btn">
											<h5 style="margin-right:19px;">至</h5>
										</div>
										<input type="text" placeholder="" class="ui_timepicker form-control" id="endTime" name="endTime" />
									</div>
								</div>
								<input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px" onclick="queryOrderManage();" id="queryOrderManageButton"/>
							</div>
						</form>
					<div class="table-responsive panel-table-body ">
						<table class="table table-striped table-hover " id="tb_orderManage"></table>
					</div>
					<footer class="panel-footer text-right bg-light lter">
					</footer>
				</div>
			</div>
		</div>
	</div>
	<!--查看订单详情-->
	<div class="modal fade" id="orderDetail" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static"></div>
	<script type="text/javascript">
	//订单详细信息代码，复用需拷贝
	var orderDetail;
	$(function() {
			$("#startTime").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
	        $("#endTime").datetimepicker({
	        	locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
	        });
	        $('#endTime').datetimepicker();
	        $("#startTime").on("dp.change",function (e) {
	            $('#endTime').data("DateTimePicker").minDate(e.date);
	        });
	        $("#endTime").on("dp.change",function (e) {
	            $('#startTime').data("DateTimePicker").maxDate(e.date);
	        });
			var oTable = new TableInit();
			oTable.Init();
			//订单详细信息代码，复用需拷贝
			orderDetail = new orderDetail("orderDetail",'<%=memberId%>','<%=basePath%>');
		});

		var TableInit = function() {
			var oTableInit = new Object();
			oTableInit.Init = function() {
				$("#tb_orderManage").bootstrapTable({
					url : "<%=basePath%>order/queryOrderManage.do",
					method : "post",
					dataType : "json",
					classes : "table table-no-bordered",
					contentType : "application/x-www-form-urlencoded",
					striped : true,
					cache : false,
					pagination : true,
					sortable : false,
					sortOrder : "asc",
					queryParams : oTableInit.queryParams,
					sidePagination : "server",
					pageNumber : 1,
					pageSize : 10,
					pageList : [ 10 ],
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
								field : "ordertitleCode",
								title : "订单号",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<a href="#" data-toggle="modal" onclick="showOrder(\''+row.id+'\');">'+value+'</a>';
								}
							},
							{
								title : "交易对方",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									if('<%=memberId%>' == row.buyersId){
										value = row.sellersName;
									}else{
										value = row.buyersName;
									}
									return value;
								}
							},
							{
								field : "totalMoney",
								title : "总金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "createTime",
								title : "签约时间",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return $.changeDate(value);;
								}
							},
							{
								field : "executeStatus",
								title : "状态",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									if(row.executeStatus == 1){
										value = "执行中";
									}else{
										value = "执行结束";
									}
									return value;
								}
							},
							{
								title : "操作",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									var html = [];
									var stopflag = row.stopStatus;
									var executeStatus = row.executeStatus;
									var memberId = '<%=memberId%>';
									if(executeStatus != 2){
										if(stopflag == 1){
											html.push('<a href="javascript:void(0);" onclick="stopOrder(\''+row.id+'\')">请求终止</a>');
										}else if(stopflag == 2){
											if(memberId == row.buyersId){
												html.push('<a href="javascript:void(0);" onclick="revokeToStop(\''+row.id+'\')">撤回终止请求</a>');
											}else{
												html.push('<a href="javascript:void(0);" onclick="allowToStop(\''+row.id+'\')">同意终止</a>&nbsp;<a href="javascript:void(0);" onclick="refuseToStop(\''+row.id+'\')">拒绝终止</a>');
											}
										}else if(stopflag == 3){
											if(memberId == row.buyersId){
												html.push('<a href="javascript:void(0);" onclick="allowToStop(\''+row.id+'\')">同意终止</a>&nbsp;<a href="javascript:void(0);" onclick="refuseToStop(\''+row.id+'\')">拒绝终止</a>');
											}else{
												html.push('<a href="javascript:void(0);" onclick="revokeToStop(\''+row.id+'\')">撤回终止请求</a>');
											}
										}
									}
									var newhtml = html.join('');
									return newhtml;
								}
							}]
					});
			};

			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {
					pageNo : params.offset,
					pageSize : params.limit,
					name : $("#name").val(),
					orderId : $("#orderId").val(),
					status : $("#status option:selected").val(),
					executeStatus : $("#executeStatus option:selected").val(),
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				};
				return temp;
			};
			return oTableInit;
		};
		function queryOrderManage() {
			var pageSize = $("#tb_orderManage").bootstrapTable("getOptions").pageSize === $(
					"#tb_orderManage").bootstrapTable("getOptions")
					.formatAllRows() ? $("#tb_orderManage").bootstrapTable(
					"getOptions").totalRows : $("#tb_orderManage")
					.bootstrapTable("getOptions").pageSize;
			$.ajax({
				url : "<%=basePath%>order/queryOrderManage.do?pageNo=0&pageSize="+ pageSize,
				data : {
					name : $("#name").val(),
					orderId : $("#orderId").val(),
					status : $("#status option:selected").val(),
					executeStatus : $("#executeStatus option:selected").val(),
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_orderManage").bootstrapTable("load", data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		function stopOrder(id) {
			var row = [];
			row.push($("#tb_orderManage").bootstrapTable("getRowByUniqueId", id));
			$.ajax({
				url : "<%=basePath%>order/stopOrder.do",
				data : {
					param : JSON.stringify(row)
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					alert(data.msg);
					$("#tb_orderManage").bootstrapTable("refresh");
				},
				error : function() {
					alert(data.msg);
				}
			});
		}
		function revokeToStop(id) {
			var row = [];
			row.push($("#tb_orderManage").bootstrapTable("getRowByUniqueId", id));
			$.ajax({
				url : "<%=basePath%>order/revokeToStop.do",
				data : {
					param : JSON.stringify(row)
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					alert(data.msg);
					$("#tb_orderManage").bootstrapTable("refresh");
				},
				error : function() {
					alert(data.msg);
				}
			});
		}
		function allowToStop(id) {
			var row = [];
			row.push($("#tb_orderManage").bootstrapTable("getRowByUniqueId", id));
			$.ajax({
				url : "<%=basePath%>order/allowToStop.do",
				data : {
					param : JSON.stringify(row)
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					alert(data.msg);
					$("#tb_orderManage").bootstrapTable("refresh");
				},
				error : function() {
					alert(data.msg);
				}
			});
		}
		function refuseToStop(id) {
			var row = [];
			row.push($("#tb_orderManage").bootstrapTable("getRowByUniqueId", id));
			$.ajax({
				url : "<%=basePath%>order/refuseToStop.do",
				data : {
					param : JSON.stringify(row)
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					alert(data.msg);
					$("#tb_orderManage").bootstrapTable("refresh");
				},
				error : function() {
					alert(data.msg);
				}
			});
		}
		//显示订单详细页面
		function showOrder(id){
			$("#orderDetail").modal("show");
			//订单详细代码，复用需拷贝
			orderDetail.queryOrderDetail(id);
		}
	</script>
</body>
</html>
