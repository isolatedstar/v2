/**
 * @author Liujf
 * @Version V1.0
 * @Description 初始化创建订单模态框
 * @param modalid:页面模态框id
 * @param memberId:当前登录人的会员id
 * @param baseurl:请求的基础路径
 * @param callback:回掉函数
 */

function createOrder(modalid,memberId,baseurl,callback){
	if(!modalid || typeof(modalid)!=='string'&&modalid.trim()!==''){
		throw new Error("modalid不能为空！");
	}
	if(!memberId || typeof(memberId)!=='string'&&memberId.trim()!==''){
		throw new Error("memberId不能为空！");
	}
	if(!baseurl || typeof(baseurl)!=='string'&&baseurl.trim()!==''){
		throw new Error("baseurl不能为空！");
	}
	this._modalid = "#"+modalid;
	this._memberId = memberId;
	this._baseurl = baseurl;
	this._callback = callback;
	this.init.call(this);
}
createOrder.prototype.init=function(){
	var html = [];
	html.push('<div class="modal-dialog tanchu-box" role="document">');
	html.push('<div class="container-fluid container-margin">');
	html.push('<div class="row-fluid">');
	html.push('<div class="col-sm-12 ">');
	html.push('<div class="panel panel-default">');
	html.push('<div class="panel-heading box-shodm modal-draggable">订单表');
	html.push('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></div>');
	html.push('<form id="createOrderForm">');
	html.push('<div class="table-responsive panel-table-body ">');
	html.push('<table class="table table-striped table-hover " id="tb_createOrder"></table>');
	html.push('<div><p>共<span style="color:red" id="span_num"></span>种商品，总金额<span style="color:red" id="span_money"></span>元</p></div>');
	html.push('</div>');
	html.push('<input type="hidden" id="id" name="id">');
	html.push('<input type="hidden" id="buyersAddressName" name="buyersAddressName">');
	html.push('<input type="hidden" id="sellersAddressName" name="sellersAddressName">');
	html.push('<input type="hidden" id="totalMoney" name="totalMoney">');
	html.push('<input type="hidden" id="buyersId" name="buyersId">');
	html.push('<input type="hidden" id="buyersName" name="buyersName">');
	html.push('<input type="hidden" id="sellersId" name="sellersId">');
	html.push('<input type="hidden" id="sellersName" name="sellersName">');
	html.push('<input type="hidden" id="payBank" name="payBank">');
	html.push('<input type="hidden" id="getBank" name="getBank">');
	html.push('<div class="row wrapper form-margin">');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn" >');
	html.push('<h5 class="h5-margin">付款时间:</h5>');
	html.push('</div>');
	html.push('<input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[date]]" id="payTime" name="payTime"/>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn" >');
	html.push('<h5 class="h5-margin">订单流程:</h5>');
	html.push('</div>');
	html.push('<select name="workflowTypeId" tabindex="-1" class="form-control validate[required]" id="workflowTypeId">');
	html.push('<option value="1">货款两清</option>');
	html.push('<option value="2">先货后款</option>');
	html.push('<option value="3">先货后款已交货</option>');
	html.push('<option value="4">先款后货</option>');
	html.push('<option value="5">先款后货已收款</option>');
	html.push('</select>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn" >');
	html.push('<h5 class="h5-margin">送货开始时间:</h5>');
	html.push('</div>');
	html.push('<input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[date]]" id="executeStartTime" name="executeStartTime"/>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn" >');
	html.push('<h5 class="h5-margin">送货结束时间:</h5>');
	html.push('</div>');
	html.push('<input type="text" placeholder="" class="ui_timepicker form-control validate[required,custom[date]]" id="executeEndTime" name="executeEndTime"/>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 class="h5-margin">支付账号:</h5>');
	html.push('</div>');
	html.push('<select tabindex="-1" class="form-control" id="payAccount" name="payAccount"></select>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 class="h5-margin">收货地址:</h5>');
	html.push('</div>');
	html.push('<select tabindex="-1" class="form-control" id="buyersAddressId" name="buyersAddressId"></select>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 class="h5-margin">收款账号:</h5>');
	html.push('</div>');
	html.push('<select tabindex="-1" class="form-control" id="getAccount" name="getAccount"></select>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-6">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 class="h5-margin">发货地址:</h5>');
	html.push('</div>');
	html.push('<select tabindex="-1" class="form-control" id="sellersAddressId" name="sellersAddressId"></select>');
	html.push('</div>');
	html.push('</div>');
	html.push('<a class="btn btn-success btn-s-md btn-default float-right cx-btn-margin" href="javascript:void(0);" onClick="$(\'#changeChar\').modal(\'hide\');">取消</a>');
	html.push('<!-- <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="javascript:void(0);">保存</a> -->');
	html.push('<a class="btn btn-warning btn-s-md btn-default float-right cx-btn-margin" href="javascript:void(0);" onclick="createOrder.save(\'createOrderForm\');">提交</a>');
	html.push('</div>');
	html.push('</form>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	$(this._modalid).append(html.join(''));
	$("#payTime").datetimepicker({
		locale: moment.locale('zh-cn'),
		showTodayButton: true,
		dayViewHeaderFormat: 'YYYY MM',
		format: 'YYYY-MM-DD'
	});
	$("#executeStartTime").datetimepicker({
		locale: moment.locale('zh-cn'),
		showTodayButton: true,
		dayViewHeaderFormat: 'YYYY MM',
		format: 'YYYY-MM-DD'
	});
	$("#executeEndTime").datetimepicker({
		locale: moment.locale('zh-cn'),
		showTodayButton: true,
		dayViewHeaderFormat: 'YYYY MM',
		format: 'YYYY-MM-DD'
	});
	$("#executeStartTime").on("dp.change",function (e) {
        $('#executeEndTime').data("DateTimePicker").minDate(e.date);
    });
    $("#executeEndTime").on("dp.change",function (e) {
        $('#executeStartTime').data("DateTimePicker").maxDate(e.date);
    });
    $("#createOrderForm").validationEngine('attach',{
		scroll:false,
		focusFirstField:false,
		autoHidePrompt:true,
		autoHideDelay:2500,
		promptPosition : "bottomLeft"
	});
};
createOrder.prototype.initOrder=function(data){
	var _self = this;
	$("#tb_createOrder").bootstrapTable("destroy");
	_self.createTable();
	$("#tb_createOrder").bootstrapTable("load", data);
	var dataObj = $("#tb_createOrder").bootstrapTable("getData");
	var money = new Number(0.00);
	var dataObjsize = dataObj.length;
	$("#payTime").val("");
	$("#executeStartTime").val("");
	$("#executeEndTime").val("");
	$("#buyersAddressId").empty();
	$.each(data.buyersAddressList, function(index, item) {
		$("#buyersAddressId").append('<option value="'+item.id+'">'+item.address+'</option>');
	});
	$("#sellersAddressId").empty();
	$.each(data.sellersAddressList, function(index, item) {
		$("#sellersAddressId").append('<option value="'+item.id+'">'+item.address+'</option>');
	});
	$("#buyersAddressId").change(function(){
		$("#buyersAddressName").val($("#buyersAddressId").find("option:selected").text());
	});
	$("#sellersAddressId").change(function(){
		$("#sellersAddressName").val($("#sellersAddressId").find("option:selected").text());
	});
	$("#payAccount").empty();
    $.each(data.buyersAccountList, function(index, item) {
        $("#payAccount").append('<option value="'+item.accountno+'">'+item.bankname+' '+item.accountno+'</option>');
    });
    $("#getAccount").empty();
    $.each(data.sellersAccountList, function(index, item) {
        $("#getAccount").append('<option value="'+item.accountno+'">'+item.bankname+' '+item.accountno+'</option>');
    });
    $("#payAccount").change(function(){
        var arr = $("#payAccount").find("option:selected").text().split(" ");
        $("#payBank").val(arr[0]);
    });
    $("#getAccount").change(function(){
        var arr = $("#getAccount").find("option:selected").text().split(" ");
        $("#getBank").val(arr[0]);
    });
	$("#id").val(data.ordertitle.id);
	$("#totalMoney").val(data.ordertitle.totalMoney);
	$("#buyersAddressId").val(data.ordertitle.buyersAddressId);
	$("#buyersAddressName").val(data.ordertitle.buyersAddressName);
	$("#sellersAddressName").val(data.ordertitle.sellersAddressName);
	$("#payAccount").val(data.ordertitle.payAccount);
	$("#getAccount").val(data.ordertitle.getAccount);
	$("#workflowTypeId").val(data.ordertitle.workflowTypeId);
	$("#buyersAddressId").val(data.ordertitle.buyersAddressId);
	$("#sellersAddressId").val(data.ordertitle.sellersAddressId);
	$("#buyersId").val(data.ordertitle.buyersId);
	$("#buyersName").val(data.ordertitle.buyersName);
	$("#sellersId").val(data.ordertitle.sellersId);
	$("#sellersName").val(data.ordertitle.sellersName);
//	$("#buyersAddressId").click(function(){
//	    $(this).empty();
//	    $.ajax({
//			url : _self._baseurl+"order/queryAddress.do",
//			data : {memberId : $("#buyersId").val()},
//			type : "POST",
//			cache : false,
//			dataType : "json",
//			success : function(data) {
//				$.each(data.addressList, function(index, item) {
//					$("#buyersAddressId").append('<option value="'+item.id+'">'+item.name+'</option>');
//				});
//			},
//			error : function(data) {
//			}
//		});
//	});
//	$("#sellersAddressId").click(function(){
//	    $(this).empty();
//	    $.ajax({
//			url : _self._baseurl+"order/queryAddress.do",
//			data : {memberId : $("#sellersId").val()},
//			type : "POST",
//			cache : false,
//			dataType : "json",
//			success : function(data) {
//				$.each(data.addressList, function(index, item) {
//					$("#sellersAddressId").append('<option value="'+item.id+'">'+item.name+'</option>');
//				});
//			},
//			error : function(data) {
//			}
//		});
//	});
	if(data.ordertitle.buyersId==_self._memberId){
		$("#getAccount").attr("disabled","disabled");
		$("#sellersAddressId").attr("disabled","disabled");
		$("#getBank").attr("disabled","disabled");
		$("#sellersAddressName").attr("disabled","disabled");
		$("#payAccount").removeAttr("disabled");
		$("#buyersAddressId").removeAttr("disabled");
		$("#payAccount").addClass("validate[required]");
		$("#buyersAddressId").addClass("validate[required]");
	}else{
		$("#payAccount").attr("disabled","disabled");
		$("#buyersAddressId").attr("disabled","disabled");
		$("#payBank").attr("disabled","disabled");
		$("#buyersAddressName").attr("disabled","disabled");
		$("#getAccount").removeAttr("disabled");
		$("#sellersAddressId").removeAttr("disabled");
		$("#getAccount").addClass("validate[required]");
		$("#sellersAddressId").addClass("validate[required]");
	}
	$.each(dataObj, function(index, item) {
        if (item.money != "" && item.money != null) {
            money += item.money;
        }
		var priceid = "#price"+ item.goodsId;
		var goodsNumid = "#goodsNum"+ item.goodsId;
		var moneyid = "#money"+ index;
		var min = item.min;
		var max = item.max;
		if(min!=null&&min!=""&&max!=null&&max!=""){
			$(priceid).addClass("validate[required,min["+min+"],max["+max+"]]");
		}else{
			$(priceid).addClass("validate[required]");
		}
		$(goodsNumid).addClass("validate[required,custom[integer]]");
//		if(min!=null&&min!=""&&max!=null&&max!=""){
//			$(priceid).blur(function(){
//			    if($(this).val()<min||$(this).val()>max){
//			    	alert(item.goodsName+"的价格区间是"+min+"元到"+max+"元。请重新输入!");
//			    	$(this).select();
//			    }
//			});
//		}
		$(priceid).on("input propertychange", function() {
			$(this).validateAmount();
			$(moneyid).html((Number($(this).val())*Number($(goodsNumid).val())).toFixed(2));
			var sum = 0;
			for(var i=0;i<dataObjsize;i++){
				var id = "#money"+i;
				sum += $(id).text()*1;
			}
			$("#span_money").text(sum.toFixed(2));
			$("#totalMoney").val(sum.toFixed(2));
		});
		$(goodsNumid).on("input propertychange", function() {
			$(this).validateNumber();
			$(moneyid).html((Number($(this).val())*Number($(priceid).val())).toFixed(2));
			var sum = 0;
			for(var i=0;i<dataObjsize;i++){
				var id = "#money"+i;
				sum += $(id).text()*1;
			}
			$("#span_money").text(sum.toFixed(2));
			$("#totalMoney").val(sum.toFixed(2));
		});
	});
	$("#span_num").text(data.total);
	$("#span_money").text(money.toFixed(2));
};
createOrder.prototype.createTable=function(){
	$("#tb_createOrder").bootstrapTable({
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
		pageSize : 100,
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
					title : "序号",
					align : "center",
					valign : "middle",
					sortable : false,
					formatter : function(value,row, index) {
						value = index+1;
						return value;
					}
				},
				{
					field : "goodsName",
					title : "商品",
					align : "center",
					valign : "middle",
					sortable : false
				},
				{
					field : "price",
					title : "单价（￥）",
					align : "center",
					valign : "middle",
					sortable : false,
					formatter : function(value,row, index) {
						return '<input type="text" class="input-size" id="price'+ row.goodsId +'" value="'+value+'" />';
					}
				},
				{
					field : "goodsNum",
					title : "数量（pcs）",
					align : "center",
					valign : "middle",
					sortable : false,
					formatter : function(value,row, index) {
						return '<input type="text" class="input-size" id="goodsNum'+ row.goodsId +'" value="'+value+'"/>';
					}
				},
				{
					field : "money",
					title : "总金额",
					align : "center",
					valign : "middle",
					sortable : false,
					formatter : function(value,row, index) {
						return '<div><span id="money'+index+'">'+Number(value).toFixed(2)+'</span></div>';
					}
				}
				]
		});
};
createOrder.prototype.save=function(formid){
	var success = false;
	var _self = this;
	var _formid = "#"+formid;
	if(!$(_formid).validationEngine("validate")){
		return false;
	}
	var order = [];
	var json = $(_formid).serializeJson();
	var dataObj = $("#tb_createOrder").bootstrapTable("getData");
	$.each(dataObj, function(index, item) {
		var priceid = "#price"+ item.goodsId;
		var goodsNumid = "#goodsNum"+ item.goodsId;
		var moneyid = "#money"+ index;
		if($(priceid).val()==""){
			alert("请填写价格!");
			return false;
		}
		if($(goodsNumid).val()==""){
			alert("请填写数量!");
			return false;
		}
		item.price = $(priceid).val();
		item.goodsNum = $(goodsNumid).val();
		item.money = $(moneyid).text();
		success = true;
	});
	if(success){
		order.push(json);
		json['orderList'] = dataObj;
		$.ajax({
			url : _self._baseurl+"order/createOrder.do",
			data : {param : JSON.stringify(order)},
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(data) {
				alert(data.msg);
				$(_self._modalid).modal("hide");
				if(typeof(_self._callback)==='function'){
					_self._callback();
				}
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	}
};