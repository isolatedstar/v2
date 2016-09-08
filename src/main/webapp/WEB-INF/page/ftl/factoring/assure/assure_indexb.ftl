<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<#include "../common/macro.ftl"/>
<html>
	<head>
		<title>担保登记</title>
		<link href="${ctx}/factoring/css/bootstrap.min111.css" rel="stylesheet">
	    <link href="${ctx}/factoring/css/theme.css" rel="stylesheet">
       <link href="${ctx}/factoring/css/font-awesome.min.css" rel="stylesheet">
 		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
	    <script src="${ctx}/factoring/js/jquery.js"></script>
	    <script src="${ctx}/factoring/js/jquery.min.js"></script>
	    <script src="${ctx}/factoring/js/bootstrap.min111.js"></script>
	     <script src="${ctx}/factoring/js/page/page.js"></script>
       <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
  		<script type="text/javascript" src="${ctx}/factoring/js/DatePicker/WdatePicker.js"></script>
	     <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
	     <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
	     <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
	     
<style>
.modal-dialog{width:50%;}
</style>
	     
<script>

<#--  分页    --> 
   $(function(){
	
		$("#formId").validationEngine('attach',{
		scroll:false,
		autoHidePrompt:true,
		autoHideDelay:2500,
		promptPosition : "bottomLeft"
		 });
	
		var li = $('.pagination li');
		var pageNo =0 ;
		
		pageNo = getactiveIndex(pageNo,li);
		
		//当点击的时候添加样式，获取
		li.click(function(){  
		 	 //取点击的a标签中的值
		 	 var txt = $(this).find("a").html();
		 	 //取点击的下标
		 	 var index = $(this).index();
		 	 
		 	 //取查询条件的值
		    // var change = $("#selecter_basic").val();
		 	 var payerName = $("#payerName").val();
		 	 var payeeName = $("#payeeName").val();
		 	 
		 	 //获取当前页
		 	 pageNo = getPageNo(pageNo,index,txt);
			
			 window.location = "${ctx}/assureController/toAssureNoFilterb.do?page="+pageNo+"&payerName="+payerName+"&payeeName="+payeeName;
		})	
	})
<#--  分页over   -->	

<#-- 全选  -->	
	function checkAllBox(obj){
		$(obj).parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		})
	}
	
	 function forLowerIdCheck(obj){
 		var forLowerCheckbox;
     $(obj).parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,thisElement){
           if($(thisElement).prop("checked")==false){
            // alert($(obj).parent().parent().parent().find("[name = checkAll]").prop("checked"));
                forLowerCheckbox = 0;
				return false;
			}
			forLowerCheckbox = 1;
			return true;
  		});
     if(forLowerCheckbox==0){
        $(obj).parent().parent().parent().parent().find("[name = checkAll]").prop("checked",false);
     }else{
       $(obj).parent().parent().parent().parent().find("[name = checkAll]").prop("checked",true);
     }
 }
<#-- 全选结束  -->	


<#--  查询  -->
 function getMassage(){
 	var payerName = $("#payerName").val();
 	var payeeName = $("#payeeName").val();
 	
 	//var type = $("#selecter_basic").val();
 	
 	window.location = "${ctx}/assureController/toAssureNoFilterb.do?payerName="+payerName+"&payeeName="+payeeName;//+"&type="+type;
 }
 <#--  查询结束   -->


<#-- 显示提示框 -->
function myModalTS(){
	
	$("#myModalTS").modal("show");
	$("#myModal").modal("hide");
	
}
<#-- 显示提示框结束 -->


<#-- 进行登记操作 -->
function assureRegister(){
	
	var guaranteetId;
	
	$("#forLowerId").parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,element){
		if($(element).prop("checked")==true){
			guaranteetId = $(element).val();
		}
	})
	
	if(guaranteetId=="undefind" || guaranteetId==null){
		$("#myModalTS").modal("hide");
		alert("请选择登记行！！");
		return;
	}
	
	$.ajax({
		url:'${ctx}/assureController/assureRegisterNoFilter.do',
		dataType:'JSON',
		type:'POST',
		data:{"guaranteetId":guaranteetId},
		success:function(data){
			data = eval("("+data+")")
			$("#myModalTS").modal("hide");
			alert(data);
			window.location = "${ctx}/assureController/toAssureNoFilterb.do";
		}
	})
}
<#-- 进行登记操作结束   -->

<#--   驳回      -->
function turnDown(obj){
	
	var guaranteetId="";
	
	$(obj).parent().find("[name = forLowerId]:checkbox").each(function(i,element){
		if($(element).prop("checked")==true){
			guaranteetId += $(element).val()+",";
		}
	})
	
	guaranteetId = guaranteetId.substr(0,guaranteetId.length-1);

   alert(guaranteetId);

	$.ajax({
		url:'${ctx}/assureController/turnDownNoFilter.do',
		dataType:'JSON',
		type:'POST',
		data:{"guaranteetIds":guaranteetId},
		success:function(data){
			data = eval("("+data+")")
			if(data!=""){
				alert(data+"驳回失败！");
			}else{
				alert("已驳回！");
				window.location = "${ctx}/assureController/toAssureNoFilterb.do"
			}
		}
	
	})
} 

<#--   驳回结束      -->

<#--   撤销     -->
function revoke(obj){
	var guaranteetId="";
	
	$(obj).parent().find("[name = forLowerId]:checkbox").each(function(i,element){
		if($(element).prop("checked")==true){
			guaranteetId += $(element).val()+",";
		}
	})
	
	guaranteetId = guaranteetId.substr(0,guaranteetId.length-1);

	$.ajax({
		url:'${ctx}/assureController/revokeNoFilter.do',
		dataType:'JSON',
		type:'POST',
		data:{"guaranteetIds":guaranteetId},
		success:function(data){
			data = eval("("+data+")")
			if(data!=""){
				alert("担保单"+data+"正在使用，不能注销！！！");
			}else{
				alert("已注销！");
				window.location = "${ctx}/assureController/toAssureNoFilterb.do"
			}
		}
	
	})
}
<#--   撤销    -->

<#--   获取guaranteetId   -->
function getguranteeId(obj){
	var guaranteetId="";
	
	$(obj).parent().find("[name = forLowerId]:checkbox").each(function(i,element){
		if($(element).prop("checked")==true){
			guaranteetId += $(element).val()+",";
		}
	})
	
	guaranteetId = guaranteetId.substr(0,guaranteetId.length-1);
}

<#--   获取guaranteetId  over   -->

function getOrderDetail(obj){
    
    var guaranteetId = $(obj).html();
    
	$.ajax({
		url:'${ctx}/assureController/getOrderDetail.do',
		dataType:'JSON',
		type:'POST',
		data:{"guaranteetId":guaranteetId},
		success:function(data){
			data = eval("("+data+")")
			for(var i = 0;i<parseInt(data.length);i++){
				var row = document.getElementById("select_order").insertRow(i+1)
				
				row.insertCell(0).innerHTML='<td></td>';
				row.insertCell(1).innerHTML='<td></td>';
				row.insertCell(2).innerHTML='<td></td>';
				row.insertCell(3).innerHTML='<td></td>';
				row.insertCell(4).innerHTML='<td></td>';
				row.insertCell(5).innerHTML='<td></td>';
				row.insertCell(6).innerHTML='<td></td>';
				row.insertCell(7).innerHTML='<td></td>';
			}
			$("#myModalDD").modal('show');
		}
	})


	
}

</script>

</head>
   <body>
<!-- head -->
<div class="container-fluid">
	<div class="row-fluid ">

		
		<div class="fs">
		    
			<div class="panel panel-default article-bj">
			  <div class="panel-heading box-shodm">已登记担保</div>
			  <!-- 查询条件 -->
			  <div class="row wrapper form-margin">
				<form id="formId" action ="${ctx}/assureController/toAssureNoFilterb.do" method="post">
				  <div class="col-md-3">
					<div class="input-group">
					  <div class="input-group-btn">
						<h5 class="h5-margin">付款方:</h5>
					  </div>
					  <input type="text" placeholder="" class="form-control validate[custom[chinese]]" name="payerName" id="payerName" value="${payerName!}"></div>
				  </div>
				  <div class="col-md-3">
					<div class="input-group">
					  <div class="input-group-btn">
						<h5 class="h5-margin">收款方:</h5>

					  </div>
					  <input type="text" placeholder="" class="form-control validate[custom[chinese]]" name="payeeName" id="payeeName" value = "${payeeName!}"></div>
				  </div>
				 <#-- <div class="col-md-3">
					<div class="input-group">
					  <div class="input-group-btn">
						<h5 class="h5-margin">担保资源状态:</h5>
					  </div>
					  <@select name="type" class="form-control" id="selecter_basic" datas={'1':'待登记','2':'已登记','3':'已驳回','5':'已注销','4':'全部'}  value="${status!}"/>
					  
					<#-- <select name="type" value="${status!}" class="form-control" id="selecter_basic">
	                  <option value="1">待登记</option>
	                  <option value="2">已登记</option>
	                  <option value="3">已驳回</option>
	                  <option value="4">全部</option>
	                </select>
					  
					</div>
				  </div>-->
				  <input type="submit" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询"  style="height:35px;width:65px"/>
				</form>
            </div>
	          <!-- 查询条件 over-->
	
			<div class="table-responsive panel-table-body " style="padding-top:15px">
				<table class="table table-striped table-hover ">
	
					<thead>
							<tr>
							  <th>
								<input type="checkbox" class="uniform" name="checkAll" onclick="checkAllBox(this)"></th>
							  <th>结款单号</th>
							  <th>结款金额</th>
							  <th>已支付金额</th>
							  <th>未支付金额</th>
							  <th>到期时间</th>
							  <th>付款方</th>
							  <th>收款方</th>
							</tr>
					</thead>
					<tbody>
						<#if list ??>
							<#list list as li>
								<tr>
									<td><input type="checkbox" id="forLowerId"  name="forLowerId" onclick="forLowerIdCheck(this)" value="${li.guaranteeId!}"/></td>
									<td class="tab_xhline"><a onclick="getOrderDetail(this)">${li.guaranteeId!}</a></td>
									<td>￥${li.guaranteeAmount!}</td>
									<td>￥${li.paymentAmount!}</td>
									<td>￥${li.nonPayAmount!}</td>
									<td>${li.expireDate?datetime?string}</td>
									<td>${li.payerName}</td>
									<td>${li.payeeName}</td>
								</tr>
							</#list>
						</#if>
						
					</tbody>
					
				</table>
			</div>
	         <#if status ??>
	         	<#if status == "2">
	         		<button class="btn btn-success btn-s-xs" id="turnDown" type="submit" style ="float:right;margin-top:20px;margin-right:15px;" onclick = "revoke(this)">注销</button>
	         	</#if>
	         	<#if status == "1">
	         		<button class="btn btn-success btn-s-xs" id="turnDown" type="submit" style ="float:right;margin-top:20px;margin-right:15px;" onclick = "turnDown(this)">驳回</button>
	         	</#if>
	         	<#if status == "4">
	         		<button class="btn btn-success btn-s-xs" id="turnDown" type="submit" style ="float:right;margin-top:20px;margin-right:15px;" onclick = "turnDown(this)">驳回</button>
	         		<button class="btn btn-success btn-s-xs" id="turnDown" type="submit" style ="float:right;margin-top:20px;margin-right:15px;" onclick = "revoke(this)">注销</button>
	         	</#if>
	         </#if>
			
			<center><div class = "page">
				<ul class="pagination">
				  <li class="prev disabled">
		              <a href="#"><<</a>
		            </li>
				   <#if length ??>
		            	<#list 0..length as len>
			            	<#if len gt 0 && len lt len+1>
			            		<#if clas ??>
				            		<#if clas != len>
				            			<li>
				            		<#else>
				            			<li  class = "active">
				            		</#if>
			            		</#if>
						         	<a href="#">${len}</a>
						         </li>
					         </#if>
				         </#list>
		            </#if>
				  <li class="next">
		              <a href="#" >>></a>
		            </li>
				</ul>
			</div></center>
	
			</div>
	</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
         </div>
         <div class="modal-body">
            	请确定是否所有法律文件都已完备？
         </div>
         <div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="myModalTS()">确认</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModalTS" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
         </div>
         <div class="modal-body" style="color:red;">
            	您是否确定要将此结款单登记为担保资源(注：此操作不能撤销)？
         </div>
         <div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="assureRegister()">确认</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModalDD" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
         </div>
         <div class="modal-body">
				<!--10002内页开始-->
				 <div id="db_inner_warp">
						<table class="table table-striped select_order" cellspacing="0"  cellpadding="5">
							<tr class="fac_tr_tab">
								<td width="6%">订单号</td>
								<td width="7%">订单行号</td>
								<td width="8%">商品名</td>
								<td width="7%">发货数量</td>
								<td width="7%">收货数量</td>
								<td width="8%">已支付金额</td>
								<td width="10%">剩余支付金额</td>
								<td width="8%">已担保金额</td>
							</tr>
						</table>
				</div>
				 <!--10002内页结束-->
         </div>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

	</div>
</div>
<!-- head结束-->
</body>
</html>