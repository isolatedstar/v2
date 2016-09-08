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

<title>付款</title>
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
		style=" margin-top:15px;">
		<div class="row-fluid">
			<!-- col-sm-12 -->
			<div class="col-sm-12 ">
				<div class="panel panel-default article-bj">
					<div class="panel-heading box-shodm">付款</div>
					<form id="queryPayMoneyForm">
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
							<input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px" onclick="queryPayMoney();" id="queryPayMoneyButton"/>
						</div>
						<div class="table-responsive panel-table-body ">
							<table class="table table-striped table-hover" id="tb_payMoney" ></table>
						</div>
					</form>
					<footer class="panel-footer text-right bg-light lter">
						<button class="btn btn-success btn-s-xs" type="button" onclick="payMoneyOffline();">线下付款</button>
						<button class="btn btn-warning btn-s-xs" type="button" onclick="payMoneyOnline();">线上付款</button>
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
			$("#queryPayMoneyForm").validationEngine('attach',{
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
				$("#tb_payMoney").bootstrapTable({
					url : "<%=basePath%>order/queryPayMoney.do",
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
								field : "ordertitleNumber",
								title : "订单号",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<a href="#" data-toggle="modal" onclick="showOrder(\''+row.oredertitleCode+'\');">'+value+'</a>';
								}
							},
							{
								field : "sellersName",
								title : "收款方",
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
								field : "money",
								title : "总金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "paymoneyNum",
								title : "已付金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "exePaymoneyNum",
								title : "待付金额",
								align : "center",
								valign : "middle",
								sortable : false
	
							},
							{
								field : "num",
								title : "本次付款金额",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<input type="text" class="input-size" id="num'+ row.id +'"/>';
								}
							},
							{
								field : "paymoneyCode",
								title : "本次付款账号",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									var html = [];
									html.push('<select name="select'+row.id+'" id="select'+row.id+'" tabindex="-1" class="form-control">');
									$.each(row.accountList, function(index, item) {
										html.push('<option value="'+item.accountno+'">'+item.bankname+'</option>');
									});
									html.push('</select>');
									var newhtml = html.join('');
									return newhtml;
								}
							},{
								field : "getPayRemark",
								title : "备注",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<input type="text" class="input-size" id="remark'+ row.id +'"/>';
								}
							},],
							onLoadSuccess: loadInput
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
		
		function loadInput(data){
			$.each(data.rows, function(index, item) {
	            var numid = '#num'+item.id;
					$(numid).on("input propertychange", function() {
						$(this).validateAmount();
				});
			});
		}
		
		function queryPayMoney() {
			var pageSize = $("#tb_payMoney").bootstrapTable("getOptions").pageSize === $(
					"#tb_payMoney").bootstrapTable("getOptions")
					.formatAllRows() ? $("#tb_payMoney").bootstrapTable(
					"getOptions").totalRows : $("#tb_payMoney")
					.bootstrapTable("getOptions").pageSize;
			$.ajax({
				url : "<%=basePath%>order/queryPayMoney.do?pageNo=0&pageSize="+ pageSize,
				data : {
					param : JSON.stringify($("#queryPayMoneyForm")
							.serializeJson())
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_payMoney").bootstrapTable("load", data);
					loadInput(data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		function payMoneyOffline() {
			var success = false;
			var numids = [];
			$("input[name='btSelectItem']").attr("class","validate[minCheckbox[1]] checkbox");
			var dataObj = $("#tb_payMoney").bootstrapTable("getSelections");
// 			if (dataObj.length == 0) {
// 				alert("请选择订单！");
// 				return false;
// 			}
			$.each(dataObj, function(index, item) {
				var exePaymoneyNum = item.exePaymoneyNum;
				var _numid = "#num" + item.id;
				var _remark = "#remark" + item.id;
				var _addressid = "#select" + item.id + " option:selected";
				numids.push(_numid);
				$(_numid).addClass("validate[required,min[0.01],max["+exePaymoneyNum+"]]");
				$(_addressid).addClass("validate[required]");
				$(_remark).addClass("validate[maxSize[100]]");
				var paymoneyNum = $(_numid).val();
				var addressid = $(_addressid).val();
// 				var regex = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
// 				if (paymoneyNum == ""||paymoneyNum == 0||paymoneyNum == 0.0||paymoneyNum == 0.00) {
// 					alert("请输入付款金额！");
// 					return false;
// 				}
// 				if(!regex.test(paymoneyNum)){
// 					alert("请输入正确的金额！");
// 					return false;
// 			    }
// 				if (paymoneyNum > exePaymoneyNum) {
// 					alert("付款金额不能大于待付金额！");
// 					return false;
// 				}
// 				if(addressid == ""){
// 					alert("请选择付款账号！");
// 					return false;
// 				}
				item.paymoneyCode = addressid;
				item.num = paymoneyNum;
				item.getPayRemark = $(_remark).val();
				success = true;
			});
			if(!$("#queryPayMoneyForm").validationEngine("validate")){
				$.each(numids,function(index, item) {
	        		$(item).attr("class","input-size ");
	       		});
				return false;
			}
			if(success){
				$.ajax({
					url : "<%=basePath%>order/payMoneyOffline.do",
					data : {
						param : JSON.stringify(dataObj)
					},
					type : "POST",
					cache : false,
					dataType : "json",
					success : function(data) {
						alert(data.msg);
						$("#tb_payMoney").bootstrapTable("refresh");
					},
					error : function() {
						alert(data.msg);
					}
				});
			}
		}
		function payMoneyOnline() {
			alert("此功能暂未开通，敬请期待！");
			return false;
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
