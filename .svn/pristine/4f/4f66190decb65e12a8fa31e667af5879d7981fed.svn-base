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

<title>收款</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>mall/css/bootstrap.css" rel="stylesheet" >
<link href="<%=basePath%>mall/css/theme.css" rel="stylesheet">
<script src="<%=basePath%>mall/js/jquery.js"></script>
<script src="<%=basePath%>mall/js/common/common.js"></script>
<script src="<%=basePath%>mall/js/extendPagination.js" type="text/javascript" ></script>
<jsp:include page="orderJsInclude.jsp"></jsp:include>
</head>

<body>
	<div class="container-fluid"
		style="margin-top:15px;">
		<div class="row-fluid">
			<!-- col-sm-12 -->
			<div class="col-sm-12 ">
				<div class="panel panel-default article-bj">
					<div class="panel-heading box-shodm">收款</div>
					<form id="queryGetMoneyForm">
						<div class="row wrapper form-margin">
							<div class="col-md-4">
								<div class="input-group">
									<div class="input-group-btn">
										<h5 class="h5-margin">订单号:</h5>
									</div>
									<input type="text" placeholder="" class="form-control"  name="orderId" id="orderId"/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="input-group">
									<div class="input-group-btn">
										<h5 class="h5-margin">商品名:</h5>
									</div>
									<input type="text" placeholder="" class="form-control" name="goodsName" id="goodsName"/>
								</div>
							</div>
							<input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px" onclick="queryGetMoney();" id="queryGetMoneyButton"/>
						</div>
						<div class="table-responsive panel-table-body ">
							<table class="table table-striped table-hover" id="tb_getMoney" ></table>
						</div>
					</form>
					<footer class="panel-footer text-right bg-light lter">
						<button class="btn btn-warning btn-s-xs" type="button" onclick="getMoney();">收款</button>
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
			var oTable = new TableInit();
			oTable.Init();
			//订单详细信息代码，复用需拷贝
			orderDetail = new orderDetail("orderDetail",'<%=memberId%>','<%=basePath%>');
			$("#queryGetMoneyForm").validationEngine('attach',{
				scroll:false,
				focusFirstField:false,
				autoHidePrompt:true,
				autoHideDelay:2500,
				promptPosition : "centerRight"
			});
		});

		var TableInit = function() {
			var oTableInit = new Object();
			oTableInit.Init = function() {
				$("#tb_getMoney").bootstrapTable({
					url : "<%=basePath%>order/queryGetMoney.do",
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
								field : "_checkbox",
								checkbox : true,
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "ordertitle_number",
								title : "订单号",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<a href="#" data-toggle="modal" onclick="showOrder(\''+row.ordertitleId+'\');">'+value+'</a>';
								}
							},
							{
								field : "paymoneyName",
								title : "付款方",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "goodsName",
								title : "商品名",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "paymoneyTime",
								title : "付款时间",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return $.changeDate(value);;
								}
							},
							{
								field : "money",
								title : "付款金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "remark",
								title : "备注",
								align : "center",
								valign : "middle",
								sortable : false
							},]
					});
			};

			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {
					pageNo : params.offset,
					pageSize : params.limit,
					goodsName : $("#goodsName").val(),
					orderId : $("#orderId").val()
				};
				return temp;
			};
			return oTableInit;
		};
		function queryGetMoney() {
			var pageSize = $("#tb_getMoney").bootstrapTable("getOptions").pageSize === $(
					"#tb_getMoney").bootstrapTable("getOptions")
					.formatAllRows() ? $("#tb_getMoney").bootstrapTable(
					"getOptions").totalRows : $("#tb_getMoney")
					.bootstrapTable("getOptions").pageSize;
			$.ajax({
				url : "<%=basePath%>order/queryGetMoney.do?pageNo=0&pageSize="+ pageSize,
				data : {
					param : JSON.stringify($("#queryGetMoneyForm")
							.serializeJson())
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_getMoney").bootstrapTable("load", data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		function getMoney() {
			var allData = $("#tb_getMoney").bootstrapTable("getData");
			var dataObj = $("#tb_getMoney").bootstrapTable("getSelections");
			$("input[name='btSelectItem']").attr("class","validate[minCheckbox[1]] checkbox");
			if (allData.length == 0) {
				return false;
			}
// 			$.each(dataObj, function(index, item) {
// 				delete item._checkbox;
// 			});
			if(!$("#queryGetMoneyForm").validationEngine("validate")){
				return false;
			}
			$.ajax({
				url : "<%=basePath%>order/getMoney.do",
				data : {
					param : JSON.stringify(dataObj)
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					alert(data.msg);
					$("#tb_getMoney").bootstrapTable("refresh");
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
