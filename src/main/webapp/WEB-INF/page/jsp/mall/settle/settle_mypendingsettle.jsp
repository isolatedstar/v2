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

<title>我的待审批结款单</title>
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
                <div class="panel-heading box-shodm">我的待审批结款单</div>
                <form id="queryPendingSettleForm">
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
                                	<h5 class="h5-margin">状态:</h5>
                                </div>
                                <select name="status" id="status" tabindex="-1"class="form-control">
                                <option value="1">执行中</option>
                                <option value="2">对方请求终止</option>
                                <option value="3">本方请求终止</option>
                                <option value="4">已终止</option>
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
                        <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px" onclick="queryPendingSettle();" id="queryPendingSettleButton"/>
       				 </div>
						<table class="table table-striped table-hover" id="tb_pendingsettle" ></table>
					</form>
					<footer class="panel-footer text-right bg-light lter">
						<button class="btn btn-success btn-s-xs " type="submit" data-toggle="modal" onclick="createSettle();">创建结款单</button>
					</footer>
				</div>
			</div>
		</div>
	</div>
	<!--创建结款单-->
<div class="modal fade" id="addMenu" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static"></div>
   <!--弹出层-->  
<div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog tanchu-box" role="document">
		<div class="container-fluid" style="margin-top:15px;">
            <div class="row-fluid">
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                    	<div class="panel-heading box-shodm modal-draggable" >
                      	  创建结款单
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <form id="stepTwoForm">
                        <input type="hidden" id="mmbpayId" name="mmbpayId">
                        <input type="hidden" id="mmbgetId" name="mmbgetId">
                        <input type="hidden" id="mmbpayName" name="mmbpayName">
                        <input type="hidden" id="mmbgetName" name="mmbgetName">
                        <input type="hidden" id="settleMoney" name="settleMoney">
                            <div class="row wrapper form-margin">
                                <div class="col-md-6">
                                   	<div class="btn-group" data-toggle="buttons">
                                    	<h5>付款方:</h5>
                                   	</div>
                                	<div id="n_payMoneyDiv" name="n_payMoneyDiv" class="btn-group"></div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5>付款账号：</h5>
                                        </div>
                                        <select id="mmbpayAccount" name="mmbpayAccount" tabindex="-1"class="form-control"></select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="btn-group" data-toggle="buttons">
                                    	<h5>收款方:</h5>
                                   	</div>
                                    <div id="n_getMoneyDiv" name="n_getMoneyDiv" class="btn-group"></div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5>收款账号：</h5>
                                        </div>
                                        <select id="mmbgetAccount" name="mmbgetAccount" tabindex="-1"class="form-control"></select>
                                    </div>
                                </div>
                                <div class="col-md-12">
        							<div class="input-group">
       							 		<div class="input-group-btn">
       							 		<h5>结款时间:</h5>
       							 		</div>
                                         <input type="text" placeholder="" class="form-control" name="ctrTime" id="ctrTime">
                                    </div>
                                </div>
                                <input type="button" class="btn btn-info btn-s-md btn-default  ah2_btn_margin " data-toggle="modal" value="添加订单行" onclick="addOrder();">
                                <input type="button" class="btn btn-info btn-s-md btn-default  ah2_btn_margin" value="删除订单行" onclick="delOrderFromSettle();">
                             </div>
                             <div class="table-responsive panel-table-body ">
                                <table class="table table-striped table-hover " id="tb_order" name="tb_order"></table>
                            </div> 
                            </form>
                            <footer class="panel-footer text-right bg-light lter">
                            	<button class="btn  btn-warning" type="submit" onClick="saveSettle();">确认结款</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div> 
	</div>
 <!--弹出层--> 
<div class="modal fade" id="addOrder" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
	<div class="modal-dialog tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm modal-draggable">
                       		 添加订单
                       		 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <form id="stepThreeForm">
                            <div class="row wrapper form-margin">
                                 <div class="col-md-4">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">订单号:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="text" placeholder="" class="form-control" name="orderId" id="orderId">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">商品名:</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <input type="text" placeholder="" class="form-control" name="goodsName" id="goodsName">
                                    </div>
                                </div>
                                <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" id="search" onclick="queryOrder();">
                             </div>
                            <div class="table-responsive panel-table-body ">
                                <table class="table table-striped table-hover " id="tb_queryOrder" name="tb_queryOrder"></table>
                            </div>
                            </form>
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn btn-warning btn-s-xs" type="submit" onClick="addOrderToSettle();">确认</button>
                            </footer>
                        </div>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
	<!--查看订单详情-->
	<div class="modal fade" id="orderDetail" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static"></div>
	<script type="text/javascript">
		//订单详细信息代码，复用需拷贝
		var orderDetail;
		var flag = 0;
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
			$("#queryPendingSettleForm").validationEngine('attach',{
				scroll:false,
				focusFirstField:false,
				autoHidePrompt:true,
				autoHideDelay:2500,
				promptPosition : "centerRight"
			});
			
			$("#stepTwoForm").validationEngine('attach',{
				scroll:false,
				focusFirstField:false,
				autoHidePrompt:true,
				autoHideDelay:2500,
				promptPosition : "centerRight"
			});
			$("#stepThreeForm").validationEngine('attach',{
				scroll:false,
				focusFirstField:false,
				autoHidePrompt:true,
				autoHideDelay:2500,
				promptPosition : "centerRight"
			});
			
			$("#changeChar").on('hide.bs.modal', function () {
				flag = 0;
			})
		});

		var TableInit = function() {
			var oTableInit = new Object();
			oTableInit.Init = function() {
				$("#tb_pendingsettle").bootstrapTable({
					url : "<%=basePath%>settle/queryMyPendingSttle.do",
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
								title : "我的提议时间",
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
								title : "我的提议金额",
								align : "center",
								valign : "middle",
								sortable : false
							}],
					});
				$("#tb_order").bootstrapTable({
					method : "post",
					dataType : "json",
					classes : "table table-no-bordered",
					contentType : "application/x-www-form-urlencoded",
					striped : true,
					cache : false,
					pagination : true,
					sortable : false,
					sortOrder : "asc",
					pageNumber : 1,
					pageSize : 2,
					pageList : [ 100 ],
					search : false,
					strictSearch : false,
					showColumns : false,
					showRefresh : false,
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
								title : "订单金额",
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "exePaymoneyNum",
								title : "待结金额",
								align : "center",
								valign : "middle",
								sortable : false
							},{
								field : "num",
								title : "结款实付金额",
								align : "center",
								valign : "middle",
								sortable : false,
								formatter : function(value,row, index) {
									return '<input type="text" class="input-size" id="num'+ row.id +'"/>';
								}
							},
							],
							onPageChange: function (number, size) {
								onPageChange();
					        },
					        onUncheck: unCheck,
					        onUncheckAll: unCheck,
					});
				$("#tb_queryOrder").bootstrapTable({
					method : "post",
					dataType : "json",
					classes : "table table-no-bordered",
					contentType : "application/x-www-form-urlencoded",
					striped : true,
					cache : false,
					pagination : true,
					sortable : false,
					sortOrder : "asc",
					pageNumber : 1,
					pageSize : 2,
					pageList : [ 100 ],
					search : false,
					strictSearch : false,
					showColumns : false,
					showRefresh : false,
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

							}
							],
					});
			};

			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {
					pageNo : params.offset,
					pageSize : params.limit,
					name : $("#name").val(),
					settleCode : $("#settleCode").val(),
					status : $("#status option:selected").val(),
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				};
				return temp;
			};
			return oTableInit;
		};
		
		function queryPendingSettle() {
			var pageSize = $("#tb_pendingsettle").bootstrapTable("getOptions").pageSize === $(
					"#tb_pendingsettle").bootstrapTable("getOptions")
					.formatAllRows() ? $("#tb_pendingsettle").bootstrapTable(
					"getOptions").totalRows : $("#tb_pendingsettle")
					.bootstrapTable("getOptions").pageSize;
			$.ajax({
				url : "<%=basePath%>settle/queryMyPendingSttle.do?pageNo=0&pageSize="+ pageSize,
				data : {
					name : $("#name").val(),
					settleCode : $("#settleCode").val(),
					status : $("#status option:selected").val(),
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_pendingsettle").bootstrapTable("load", data);
					loadInput(data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		
		function createSettle(){
			$("#addMenu").empty();
 			$("#addMenu").modal("show");
 			var html = [];
 			html.push('<div class="modal-dialog m-tanchu-box" role="document">');
 			html.push('<div class="container-fluid" style="margin-top:15px;">');
 			html.push('<div class="row-fluid">');
 			html.push('<div class="col-sm-12 ">');
 			html.push('<div class="panel panel-default article-bj">');
 			html.push('<div class="panel-heading box-shodm modal-draggable">创建结款单');
 			html.push('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></div>');
 			html.push('<form id="stepOneForm">');
 			html.push('<div class="row wrapper form-margin">');
 			html.push('<div class="col-md-12">');
 			html.push('<div class="btn-group" data-toggle="buttons">');
 			html.push('<h5><strong>付款方式:</strong></h5>');
 			html.push('<label class="btn btn-primary" onclick="changeSide(0);">');
 			html.push('<input type="radio" name="options" class="validate[required] radio" value="0"> 本方付款');
 			html.push('</label>');
 			html.push('<label class="btn btn-primary" onclick="changeSide(1);">');
 			html.push('<input type="radio" name="options" class="validate[required] radio" value="1"> 对方付款');
 			html.push('</label>');
 			html.push('</div>');
 			html.push('</div><br/><br/>');
 			html.push('<div class="col-md-12">');
 			html.push('<div class="input-group" >');
 			html.push('<div class="input-group-btn" >');
 			html.push('<h5><strong>付款方:</strong></h5>');
 			html.push('</div>');
 			html.push('<div id="payMoneyDiv" name="payMoneyDiv"></div>');
 			html.push('</div>');
 			html.push('</div>');
 			html.push('<div class="col-md-12">');
 			html.push('<div class="input-group" >');
 			html.push('<div class="input-group-btn" >');
 			html.push('<h5><strong>收款方:</strong></h5>');
 			html.push('</div>');
 			html.push('<div id="getMoneyDiv" name="getMoneyDiv"></div>');
 			html.push('</div>');
 			html.push('</div>');
 			html.push('<input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" data-toggle="modal" onclick="nextStep();" value="确认，下一步选择结款的订单">');
 			html.push('</div>');
 			html.push('</form>');
 			html.push('<footer class="panel-footer text-right bg-light lter">');
 			html.push('</footer>');
 			html.push('</div>');
 			html.push('</div>');      	
 			html.push('</div>');
 			html.push('</div>');
 			html.push('</div>');
			$("#addMenu").append(html.join(''));
			$("#stepOneForm").validationEngine('attach',{
				scroll:false,
				focusFirstField:false,
				autoHidePrompt:true,
				autoHideDelay:2500,
				promptPosition : "centerRight"
			});
		}
		function changeSide(param){
			var isBuy;
			if(param==0){
				isBuy = "buy";
			}else if(param==1){
				isBuy = "sell";
			}
			$.ajax({
				url : "<%=basePath%>settle/queryOppositeList.do",
				data : {
					isBuy : isBuy
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					var html = "<h5>"+'<%=memberName%>'+"</h5>";
					var seletionhtml = [];
					if(param==0){
						seletionhtml.push('<select name="selecter_basic" id="selecter_basic" tabindex="-1" class="form-control validate[required]">');
						$.each(data.rows, function(index, item) {
							seletionhtml.push('<option value="'+item.sellersId+'">'+item.sellersName+'</option>');
						});
						seletionhtml.push('</select>');
						var newhtml = seletionhtml.join('');
						$("#payMoneyDiv").empty();
						$("#getMoneyDiv").empty();
						$("#payMoneyDiv").append(html);
						$("#getMoneyDiv").append(newhtml);
					}else if(param==1){
						seletionhtml.push('<select name="selecter_basic" id="selecter_basic" tabindex="-1" class="form-control validate[required]">');
						$.each(data.rows, function(index, item) {
							seletionhtml.push('<option value="'+item.buyersId+'">'+item.buyersName+'</option>');
						});
						seletionhtml.push('</select>');
						var newhtml = seletionhtml.join('');
						$("#payMoneyDiv").empty();
						$("#getMoneyDiv").empty();
						$("#payMoneyDiv").append(newhtml);
						$("#getMoneyDiv").append(html);
					}
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		
		function nextStep(){
			if(!$("#stepOneForm").validationEngine("validate")){
				return false;
			}
			var html = "<h5>"+'<%=memberName%>'+"</h5>";
			var newhtml = "<h5>"+$("#selecter_basic").find("option:selected").text()+"</h5>";
			var radioValue = $("input[name=options]:checked").val();
			$("#n_payMoneyDiv").empty();
			$("#n_getMoneyDiv").empty();
			$("#tb_order").bootstrapTable("removeAll");
			$.ajax({
				url : "<%=basePath%>order/queryAddressAccount.do",
				data : {
					buyersId : '<%=memberId%>'
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					if(radioValue==0){
						$("#mmbpayAccount").empty();
						$.each(data.buyersAccountList, function(index, item) {
							$("#mmbpayAccount").append('<option value="'+item.accountno+'">'+item.bankname+'</option>');
						});
					}else if(radioValue==1){
						$("#mmbgetAccount").empty();
						$.each(data.buyersAccountList, function(index, item) {
							$("#mmbgetAccount").append('<option value="'+item.accountno+'">'+item.bankname+'</option>');
						});
					}
				},
				error : function() {
					alert("异常！");
				}
			});
			if(radioValue==0){
				$("#n_payMoneyDiv").append(html);
				$("#n_getMoneyDiv").append(newhtml);
				$("#mmbpayId").val('<%=memberId%>');
				$("#mmbgetId").val($("#selecter_basic").val());
				$("#mmbpayName").val('<%=memberName%>');
				$("#mmbgetName").val($("#selecter_basic").find("option:selected").text());
				$("#mmbgetAccount").attr("disabled","disabled");
				$("#mmbpayAccount").removeAttr("disabled");
			}else if(radioValue==1){
				$("#n_payMoneyDiv").append(newhtml);
				$("#n_getMoneyDiv").append(html);
				$("#mmbpayId").val($("#selecter_basic").val());
				$("#mmbgetId").val('<%=memberId%>');
				$("#mmbpayName").val($("#selecter_basic").find("option:selected").text());
				$("#mmbgetName").val('<%=memberName%>');
				$("#mmbpayAccount").attr("disabled","disabled");
				$("#mmbgetAccount").removeAttr("disabled");
			}
			$("#ctrTime").datetimepicker({
				locale: moment.locale('zh-cn'),
				showTodayButton: true,
				dayViewHeaderFormat: 'YYYY MM',
				format: 'YYYY-MM-DD'
			});
			$("#ctrTime").val("");
			$("#changeChar").modal("show");
		}
		
		function addOrder(){
			$("#goodsName").val("");
			$("#orderId").val("");
			var pageSize = $("#tb_pendingsettle").bootstrapTable("getOptions").pageSize === $(
			"#tb_pendingsettle").bootstrapTable("getOptions")
			.formatAllRows() ? $("#tb_pendingsettle").bootstrapTable(
			"getOptions").totalRows : $("#tb_pendingsettle")
			.bootstrapTable("getOptions").pageSize;
			$.ajax({
				url : "<%=basePath%>order/queryPayMoneyOrderForSettle.do?pageNo=0&pageSize="+ pageSize,
				data : {
					goodsName : $("#goodsName").val(),
					orderId : $("#orderId").val(),
					buyersId : $("#mmbpayId").val(),
					sellersId : $("#mmbgetId").val()
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_queryOrder").bootstrapTable("load", data);
					var dataObj = $("#tb_order").bootstrapTable("getData");
					var ids = $.map(dataObj, function (row) {
		                return row.id;
		            });
					$("#tb_queryOrder").bootstrapTable("remove", {
		                field: 'id',
		                values: ids
		            });
				},
				error : function() {
					alert("异常！");
				}
			});
			$("#tb_queryOrder").bootstrapTable("selectPage", 1);
			$("#addOrder").modal("show");
		}
		
		function queryOrder(){
			$.ajax({
				url : "<%=basePath%>order/queryPayMoneyOrderForSettle.do",
				data : {
					goodsName : $("#goodsName").val(),
					orderId : $("#orderId").val(),
					buyersId : $("#mmbpayId").val(),
					sellersId : $("#mmbgetId").val()
				},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#tb_queryOrder").bootstrapTable("load", data);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
		function addOrderToSettle(){
			$("input[name='btSelectItem']").attr("class","validate[minCheckbox[1]] checkbox");
			if(!$("#stepThreeForm").validationEngine("validate")){
				return false;
			}
			var dataObj = $("#tb_queryOrder").bootstrapTable("getSelections");
			var ids = $.map(dataObj, function (row) {
                return row.id;
            });
			$("#tb_queryOrder").bootstrapTable("remove", {
                field: 'id',
                values: ids
            });
			$("#tb_order").bootstrapTable("remove", {
                field: 'id',
                values: ids
            });
			$("#tb_order").bootstrapTable("append", dataObj);
			$("#tb_order").bootstrapTable("uncheckAll");
			onPageChange();
			$("#tb_order").bootstrapTable("selectPage", 1);
			$("#addOrder").modal("hide");
		}
		
		function delOrderFromSettle(){
			$("input[name='btSelectItem']").attr("class","validate[minCheckbox[1]] checkbox");
			var dataObj = $("#tb_order").bootstrapTable("getSelections");
			var ids = $.map(dataObj, function (row) {
				unCheck(row);
                return row.id;
            });
			$("#ctrTime").attr("class", "form-control");
			if(!$("#stepTwoForm").validationEngine("validate")){
				return false;
			}
			$("#tb_queryOrder").bootstrapTable("append", dataObj);
			$("#tb_queryOrder").bootstrapTable("uncheckAll");
			
			$("#tb_order").bootstrapTable("remove", {
                field: 'id',
                values: ids
            });
		}
		
		function saveSettle(){
			$("input[name='btSelectItem']").attr("class","validate[minCheckbox[1]] checkbox");
			var dataObj = $("#tb_order").bootstrapTable("getSelections");
			$.each(dataObj, function(index, item) {
				var numid = '#num'+item.id;
				$(numid).addClass("validate[required,min[0.01],max["+item.exePaymoneyNum+"]]");
				item.num = $(numid).val();
			});
			$("#ctrTime").addClass("validate[required]");
			if(!$("#stepTwoForm").validationEngine("validate")){
				return false;
			}
			var json = [];
			json.push($("#stepTwoForm").serializeJson());
			$("#changeChar").modal('hide');
			if(dataObj.length>0){
				$.ajax({
					url : "<%=basePath%>settle/createSettle.do",
					data : {
						mtSettle : JSON.stringify(json),
						mtOrder : JSON.stringify(dataObj)
					},
					type : "POST",
					cache : false,
					dataType : "json",
					success : function(data) {
						$("#addMenu").modal('hide');
						$("#tb_pendingsettle").bootstrapTable("refresh");
					},
					error : function() {
						alert("异常！");
					}
				});
			}
			
		}
		
		function unCheck(row){
			var numid = '#num'+row.id;
			$(numid).attr("class", "input-size");
		}
		
		function onPageChange(){
			var dataObj = $("#tb_order").bootstrapTable("getData");
			$.each(dataObj, function(index, item) {
				var numid = '#num'+item.id;
				$(numid).on("input propertychange", function() {
					$(this).validateAmount();
				});
			});
		}
		
	</script>
</body>
</html>
