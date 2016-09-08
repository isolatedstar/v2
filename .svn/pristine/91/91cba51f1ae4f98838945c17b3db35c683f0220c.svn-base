<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.zllh.common.common.model.model_extend.UserExtendBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserExtendBean userExtendBean = (UserExtendBean)session.getAttribute("user");
String memberId = "";
String memberName = "";
if(userExtendBean!=null){
	memberId = userExtendBean.getMuser().getMmbId();
	memberName = userExtendBean.getMuser().getMmbName();
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>结款单签章</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>mall/css/bootstrap.css" rel="stylesheet" >
<link href="<%=basePath%>mall/css/theme.css" rel="stylesheet">
<script src="<%=basePath%>mall/js/jquery.js"></script>
<script src="<%=basePath%>mall/js/common/common.js"></script>
<script src="<%=basePath%>mall/js/extendPagination.js" type="text/javascript" ></script>
<jsp:include page="../order/orderJsInclude.jsp"></jsp:include>
</head>

<body>
	<div class="container-fluid" style="margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">结款单签章</div>
                <form id="querySettleForm">
        			<div class="row wrapper form-margin">
       					 <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">结款单号:</h5>
       							 </div>
       							 <input type="text" placeholder="" class="form-control" name="settleCode" id="settleCode">
        					</div>
        				</div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">结款对方:</h5>
                                </div>
                                <input type="text" placeholder="" class="form-control" name="name" id="name">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">签章状态:</h5>
                                </div>
                                <select name="statusSign" id="statusSign" tabindex="-1"class="form-control">
	                                <option value="0">全部</option>
	                                <option value="1">双方未签</option>
	                                <option value="2">本方已签</option>
	                                <option value="3">对方已签</option>
                                </select>
                        	</div>
                        </div>
                        <br/>
                         <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">开始时间:</h5>
       							 </div>
       							 <input type="text" placeholder="" class="form-control" name="startTime" id="startTime">
        					</div>
        				</div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">结束时间:</h5>
                                </div>
                                <input type="text" placeholder="" class="form-control" name="endTime" id="endTime">
                            </div>
                        </div>
                        <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px" onclick="querySettleManage();" id="querySettleButton"/>
       				 </div>
						<table class="table table-striped table-hover" id="tb_settle" ></table>
					</form>
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
			$("#querySettleForm").validationEngine('attach',{
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
				$("#tb_settle").bootstrapTable({
					url : "<%=basePath%>settle/querySttleSignature.do",
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
								field : "settleCode",
								title : "结款单号",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<a href="#" data-toggle="modal" onclick="showOrder(\''+row.oredertitleCode+'\');">'+value+'</a>';
								}
							},
							{
								title : "结款对方",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									if('<%=memberId%>' == row.mmbpayId){
										value = row.mmbgetName;
									}else{
										value = row.mmbpayName;
									}
									return value;
								}
							},
							{
								field : "ctrTime",
								title : "预计结款时间",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return $.changeDate(value);
								}
							},
							{
								field : "settleMoney",
								title : "结款订单金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "ctrMoney",
								title : "约定金额",
								align : "center",
								valign : "middle",
								sortable : false
							},{
								field : "gotMoney",
								title : "已付金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								title : "电子签章",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									var html = [];
									var statusSign = row.statusSign;
									var memberId = '<%=memberId%>';
									if(statusSign == 1){
										html.push('<a href="javascript:void(0);" onclick="signature(\''+row.id+'\')">签章</a>');
									}else if(statusSign == 3){
										if(memberId == row.mmbpayId){
											html.push('<a href="javascript:void(0);" onclick="signature(\''+row.id+'\')">签章</a>');
										}else{
											html.push('等待对方确认签章');
										}
									}else if(statusSign == 2){
										if(memberId == row.mmbpayId){
											html.push('等待对方确认签章');
										}else{
											html.push('<a href="javascript:void(0);" onclick="signature(\''+row.id+'\')">签章</a>');
										}
									}
									var newhtml = html.join('');
									return newhtml;
								}
							}],
					});
			};

			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {
					pageNo : params.offset,
					pageSize : params.limit,
					name : $("#name").val(),
					settleCode : $("#settleCode").val(),
					statusSign : $("#statusSign option:selected").val(),
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				};
				return temp;
			};
			return oTableInit;
		};
		
		function querySettleManage() {
			var pageSize = $("#tb_settle").bootstrapTable("getOptions").pageSize === $(
					"#tb_settle").bootstrapTable("getOptions")
					.formatAllRows() ? $("#tb_settle").bootstrapTable(
					"getOptions").totalRows : $("#tb_settle")
					.bootstrapTable("getOptions").pageSize;
			$.ajax({
				url : "<%=basePath%>settle/querySttleSignature.do?pageNo=0&pageSize="+ pageSize,
				data : {
					name : $("#name").val(),
					settleCode : $("#settleCode").val(),
					statusSign : $("#statusSign option:selected").val(),
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_settle").bootstrapTable("load", data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		
		function signature(id) {
			var row = [];
			row.push($("#tb_settle").bootstrapTable("getRowByUniqueId", id));
			
			
			window.location = "${pageContext.request.contextPath}/settle/signature.do?param="+JSON.stringify(row);
			
			
		}
		
	</script>
</body>
</html>
