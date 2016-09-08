<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/mall/css/bootstrap.css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet">
<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/common/common.js"></script>
<script src="${ctx}/mall/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/mall/css/bootstrap-table.css">
<script src="${ctx}/mall/js/bootstrap-table.js"></script>
<script src="${ctx}/mall/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/mall/js/serializeJson.js"></script>
<script src="${ctx}/mall/js/extendPagination.js"></script>
<script src="${ctx}/mall/js/orderDetail.js"></script>
<script src="${ctx}/mall/js/editOrder.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
<title>发货</title>
</head>
<body>
<div class="container-fluid" style="margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">发货</div>
                <form id="querySendGoodsForm">
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
                                	<h5 class="h5-margin">商品名:</h5>
                                </div>
                                <input type="text" placeholder="" class="form-control" name="goodsName" id="goodsName"/>
                            </div>
                        </div>
                        <input type="button" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px" onclick="querySendGoods();" id="querySendGoodsButton"/>
       				 </div>
	                <div class="table-responsive panel-table-body ">
	                    <table class="table table-striped table-hover" id="tb_sendGoods"></table>
	    			</div> 
       			</form>
                <footer class="panel-footer text-right bg-light lter">
                    <div class="checkbox">
                        <label>
                        	<input type="checkbox"/> 自动生成电子货单
                        </label>
                        <button class="btn btn-warning btn-s-xs btn-margin" type="button" onclick="sendGoods();">发货</button>
                    </div>
                </footer>
       		 </div>
        </div>
    </div> 
    <!--查看订单详情-->
	<div class="modal fade" id="orderDetail" role="dialog" aria-labelledby="gridSystemModalLabel"></div>
	<script type="text/javascript">
	//订单详细信息代码，复用需拷贝
	var orderDetail;
	$(function() {
		//订单详细信息代码，复用需拷贝
		orderDetail = new orderDetail("orderDetail", '${Session.user.getMuser().getMmbId()}','${Request.basePath}');
		var oTable = new TableInit();
		oTable.Init();
		$("#querySendGoodsForm").validationEngine('attach',{
			scroll:false,
			focusFirstField:false,
			autoHidePrompt:true,
			autoHideDelay:2500,
			promptPosition : "centerRight"
		});
	});
	
	var TableInit = function() {
		var oTableInit = new Object();
		//初始化Table
		oTableInit.Init = function() {
			$('#tb_sendGoods').bootstrapTable({
				url : '${ctx}/order/querySendGoods.do',
				method : 'post',
				dataType: 'json',
				classes: 'table table-no-bordered',
				contentType: 'application/x-www-form-urlencoded',
				striped : true,
				cache : false,
				pagination : true,
				sortable : false,
				sortOrder : "asc",
				queryParams : oTableInit.queryParams,
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
				paginationPreText: '«',
        		paginationNextText: '»',
				columns : [ {
					field : '_checkbox',
					checkbox : true,
					align: 'center',
					valign: 'middle',
					sortable: false
				}, {
					field : 'ordertitleNumber',
					title : '订单号',
					align: 'center',
					valign: 'middle',
					sortable: false,
					formatter : function(value,row, index) {
									return '<a href="#" data-toggle="modal" onclick="showOrder(\''+row.oredertitleCode+'\');">'+value+'</a>';
							    }
				}, {
					field : 'buyersName',
					title : '收货方',
					align: 'left',
					valign: 'middle',
					sortable: false
				}, {
					field : 'goodsName',
					title : '商品名',
					align: 'left',
					valign: 'middle',
					sortable: false
				}, {
					field : 'goodsNum',
					title : '总数',
					align: 'right',
					valign: 'middle',
					sortable: false
				}, {
					field : 'sendgoodsNum',
					title : '已发',
					align: 'right',
					valign: 'middle',
					sortable: false
				}, {
					field : 'exeSendgoodsNum',
					title : '待发数量',
					align: 'right',
					valign: 'middle',
					sortable: false
					
				}, {
					field : 'num',
					title : '本次发货数量',
					align: 'center',
					valign: 'middle',
					sortable: false,
					formatter:function(value,row,index){
						var html = '<input type="text" class="input-size " id="num'+ row.id +'"/>';
                        return html;
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
				goodsName: $("#goodsName").val(),
   				orderId: $("#orderId").val()
			}
			return temp;
		};
		return oTableInit;
	};
	
	function loadInput(data){
			$.each(data.rows, function(index, item) {
	            var numid = '#num'+item.id;
					$(numid).on("input propertychange", function() {
						$(this).validateNumber();
				});
			});
		}
	
	function querySendGoods() {
		var pageSize = $("#tb_sendGoods").bootstrapTable('getOptions').pageSize === $("#tb_sendGoods").bootstrapTable('getOptions').formatAllRows() ? 
		$("#tb_sendGoods").bootstrapTable('getOptions').totalRows : $("#tb_sendGoods").bootstrapTable('getOptions').pageSize;
		$.ajax({
			url : '${ctx}/order/querySendGoods.do?pageNo=0&pageSize='+pageSize,
			data : {param : JSON.stringify($("#querySendGoodsForm").serializeJson())},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$("#tb_sendGoods").bootstrapTable('load', data);
				loadInput(data);
			},
			error : function() {
				alert("异常！");
			}
		});
	}
	//显示订单详细页面
	function showOrder(id){
		$("#orderDetail").modal("show");
		//订单详细代码，复用需拷贝
		orderDetail.queryOrderDetail(id);
	}
	function sendGoods() {
		var success = false;
		$("input[name='btSelectItem']").attr("class","validate[minCheckbox[1]] checkbox");
		var dataObj = $("#tb_sendGoods").bootstrapTable('getSelections');
//		if(dataObj.length==0){
//			alert("请选择订单！");
//			return false;
//		}
		var numids = [];
		$.each(dataObj, function(index, item) {
			var exeSendgoodsNum = item.exeSendgoodsNum;
			var _numid = "#num"+item.id;
			numids.push(_numid);
			$(_numid).addClass("validate[required,custom[integer],min[1],max["+exeSendgoodsNum+"]]");
			var sendgoodsNum = $(_numid).val();
//			if(sendgoodsNum==''){
//				alert("请输入发货数量！");
//				return false;
//			}
//			if(sendgoodsNum==0){
//				alert("发货数量不能为0！");
//				return false;
//			}
//			if(sendgoodsNum>exeSendgoodsNum){
//				alert("发货数量不能大于待发数量！");
//				return false;
//			}
			item.num = sendgoodsNum;
			success = true;
        });
        if(!$("#querySendGoodsForm").validationEngine("validate")){
        	$.each(numids,function(index, item) {
        		$(item).attr("class","input-size ");
       		});
//        	ids.length = 0;
        	numids.length = 0;
			return false;
		}
        if(success){
			$.ajax({
				url : '${ctx}/order/sendGoods.do',
				data : {param : JSON.stringify(dataObj)},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					alert(data.msg);
					$("#tb_sendGoods").bootstrapTable('refresh');
				},
				error : function() {
					alert(data.msg);
				}
			});
		}
	}
</script>
</body>
</html>