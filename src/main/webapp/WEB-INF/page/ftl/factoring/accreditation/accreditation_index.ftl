<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<#include "../common/macro.ftl"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>认可管理</title>
   <link href="${ctx}/factoring/css/bootstrap.min111.css" rel="stylesheet">
   <link href="${ctx}/factoring/css/theme.css" rel="stylesheet">
   <link href="${ctx}/factoring/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
   
   <script src="${ctx}/factoring/js/jquery.min.js"></script>
   <script src="${ctx}/factoring/js/jquery.js"></script>
   <script src="${ctx}/factoring/js/bootstrap.min111.js"></script>
   <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/DatePicker/WdatePicker.js"></script>
  <script src="${ctx}/common/js/common.js"></script>
   <script src="${ctx}/factoring/js/page/page.js"></script>
   
  <style type="text/css"></style>
  <script type="text/javascript">
       $(document).ready(function() {
          $('#reservationtime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            format: 'MM/DD/YYYY h:mm A'
          }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
          });
       });
         $(document).ready(function() {
          $('#reservationtimedate').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            format: 'MM/DD/YYYY h:mm A'
          }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
          });
       });
       
       
		$(function(){
			var li = $('.pagination-page li');
		
			var pageNo =0 ;
			
			pageNo = getactiveIndex(pageNo,li);
			
			li.click(function(){  
			 	 var check = $(this).attr("class");
			     if(check != "prev disabled"){
				 	 var txt = $(this).find("a").html();
				 	 var index = $(this).index();
				 	 
				 	 var financingId = $("#financingId").val();
				 	 var proposerName = $("#proposerName").val();
				 	 var status = $("#status").val();
				 	 
				 	 pageNo = getPageNo(pageNo,index,txt);
					 window.location = "${ctx}/accreditationMgController/acceptNofilter.do?page="+pageNo+"&financingId="+financingId+"&proposerName="+proposerName+"&status="+status;
				 }
			})	
		}) 
       
       
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
	 function RKAssure(){
	 		var ids="";
			$('#forLowerIds').parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,element){
				if($(element).prop("checked")==true){
					ids += $(element).val()+ ",";
				}
			})
			ids = ids.substring(0,ids.length-1);
			
			if(ids==""){
				alert("请选择所需认可的行！！");
				return;
			}
			$("#myModal1").modal('show');
		}
	
	 function FQAssure(){
 			var ids="";
			$('#forLowerIds').parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,element){
				if($(element).prop("checked")==true){
					ids += $(element).val()+ ",";
				}
			})
			ids = ids.substring(0,ids.length-1);
			
			if(ids==""){
				alert("请选择所需放弃的行！！");
				return;
			}
		$("#myModal").modal('show');
	}
	
	function executeAccept(){
			var len = parseInt(transfer_money.rows.length);
		    for(var j=0;j<len;len--){
				document.getElementById("transfer_money").deleteRow(j);
			}
	
			var ids="";
			var lockFinjJson = "";
			$('#forLowerIds').parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,element){
				if($(element).prop("checked")==true){
					ids += $(element).val()+ ",";
					
					lockFinjJson += "{\"" 
								  +$(element).val()+"\":\""+$(element).attr("param")+"\"}";
								 // +"\",\"lock\":\""+$(element).attr("param")
								
				}
				
				
			})
			ids = ids.substring(0,ids.length-1);
			//lockFinjJson = lockFinjJson.substring(0,lockFinjJson.length-1);
			lockFinjJson += "";
			
			if(ids==""){
				alert("请选择所需认可的行！！");
				return;
			}
			
		$.ajax({
				url:'${ctx}/accreditationMgController/executeAccept.do',
				dataType:"JSON",
				type:"POST",
				data:{"ids":ids,"lockFinjJson":lockFinjJson},
				success:function(data){
				 //  	data = eval("("+data+")");
				    data = data.substring(1,parseInt(data.length)-1)
			   		var arr = new Array()
			   		arr = data.split(",")
			   		
			   		for(var i = 0;i<arr.length;i++){
			   			var row = document.getElementById("transfer_money").insertRow(i);
			   			row.insertCell(0).innerHTML = '<td>'+arr[i]+'</td>';
			   		}
			   		
				  //	window.location = "${ctx}/accreditationMgController/acceptNofilter.do"
				}
			}) 
			$("#myModal1").modal('hide');	
			$("#myModal_transfer").modal("show");
	}
	
	
	function executeVeto(){
	
		var ids="";
		var lockFinjJson = "";
		$('#forLowerIds').parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,element){
			if($(element).prop("checked")==true){
				ids += $(element).val()+ ",";
				
				lockFinjJson += "{\"" 
							  +$(element).val()+"\":\""+$(element).attr("param")+"\"}";
							 // +"\",\"lock\":\""+$(element).attr("param")
							
			}
			
			
		})
		ids = ids.substring(0,ids.length-1);
		//lockFinjJson = lockFinjJson.substring(0,lockFinjJson.length-1);
		lockFinjJson += "";
		
		if(ids==""){
			alert("请选择放弃行！！");
			return;
		}

		$.ajax({
				url:'${ctx}/accreditationMgController/executeVeto.do',
				dataType:"JSON",
				type:"POST",
				data:{"ids":ids,"lockFinjJson":lockFinjJson},
				success:function(data){
				   	 data = eval("("+data+")");
				   	 if(data == ""){
				   	 	alert("已放弃！！");
				   	 }else{
				   	 	alert(data);
				   	 }
				   	 $("#myModal").modal('hide');
				 window.location = "${ctx}/accreditationMgController/acceptNofilter.do"
				}
		}) 
	}
	
	function transferMoney(){
		var financingId = $("#transfer_money tr td:eq(0)").html();
		financingId = financingId.substring(parseInt(financingId.indexOf("："))+1,financingId.length);
		$.ajax({
				url:'${ctx}/accreditationMgController/updateAccptStutas.do',
				dataType:"JSON",
				type:"POST",
				data:{"id":financingId},
				success:function(data){
				 window.location = "${ctx}/accreditationMgController/acceptNofilter.do"
				}
		}); 
		
		//window.location = "${ctx}/accreditationMgController/updateAccptStutas.do?id="+financingId;
		//window.location = "${ctx}/accreditationMgController/acceptNofilter.do?page=1";
	}
	
	
  </script>

</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">

      <div class="panel panel-default article-bj">
        <div class="panel-heading box-shodm">认可管理表</div>
        <div class="row wrapper form-margin">
          <form id="formId" action="${ctx}/accreditationMgController/acceptNofilter.do"　method="post">

            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">融资单号:</h5>
                </div>
                <input type="text" placeholder="" class="form-control" name="financingId" value="${financingId!}" id="financingId"></div>
            </div>
            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">融资流程:</h5>
                </div>
                <@select name="assureType" class="form-control" id="assureType" datas={'0':'全部','1':'先担保后认可','2':'先认可后担保'}  value="${assureType!}"/>
              </div>
            </div>
            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">认可状态:</h5>
                </div>
                <#-- 1-待认可2-待放款3-已认可4-已拒绝5-已还款6-已关闭11-超欠 -->
                <@select name="status" class="form-control" id="status" datas={'0':'全部','1':'待认可','3':'已认可','2':'待放款'}  value="${status!}"/>
              </div>
            </div>

            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">融资时间:</h5>
                </div>
                <input type="text" placeholder=""  class="form-control "  name="proposerDate" value="${proposerDate!}" id="reservationtime"></div>
            </div>
            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">融资者:</h5>
                </div>
                <input type="text" placeholder="" class="form-control" name="proposerName" value="${proposerName!}" id="input1-group3"></div>
            </div>
            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">认可操作员:</h5>
                </div>
                <input type="text" placeholder="" class="form-control" name="acceptOperatorName" value="${acceptOperatorName!}" id="input1-group3"></div>
            </div>
            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">认可时间:</h5>
                </div>
                <input type="text" placeholder=""  class="form-control "  name="acceptDate" value="${acceptDate!}" id="reservationtimedate"></div>
            </div>

            <#-- <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="#">查询</a> -->
             <input type="submit" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px"/>
          </form>
        </div>

        <div class="table-responsive panel-table-body ">
          <table class="table table-striped table-hover ">
            <thead>
              <tr>
                <th>
                <input type="checkbox" class="uniform" name="checkAll" onclick="checkAllBox(this)"></th>
                <th>融资单号</th>
                <th>融资总金额</th>
                <th>融资者</th>
                <th>融资流程</th>
                <th>申请时间</th>
                <th>认可状态</th>
                <th>认可操作员</th>
                <th>认可时间</th>
              </tr>
            </thead>
            <tbody id="repay_tbody">
              <#if fins ??>
					<#list fins as li>
						<tr>
							<td>
							 	<input type="hidden" value="${li.facFinancing.lock!}" name="lockFin" id="lockFin">
			                  	<input type="hidden" value="${li.guaranteeId!}" name="guaranteeId" id="guaranteeId">
			                  	<input type="hidden" value="${li.facFinancing.guaLock!}" name="guaLock" id="guaLock">
								<#if li.facFinancing.status ??>
									<#if li.facFinancing.status = 3>
										<input type="checkbox" value="${li.facFinancing.financingId!}" name="forLowerId"  id="forLowerIds" param="${li.facFinancing.lock!}" onclick="forLowerIdCheck(this)" disabled/>
									<#else>
										<input type="checkbox" value="${li.facFinancing.financingId!}" name="forLowerId"  id="forLowerIds" param="${li.facFinancing.lock!}" onclick="forLowerIdCheck(this)"/>
									</#if>
								<#else>
									<input type="checkbox" value="${li.facFinancing.financingId!}" name="forLowerId"  id="forLowerIds" param="${li.facFinancing.lock!}" onclick="forLowerIdCheck(this)"/>
								</#if>
							</td>
							<td>${li.facFinancing.financingId!}</td>
							<td>￥${li.facFinancing.waitPayTotal!}</td>
							<td>${li.facFinancing.proposerName!}</td>
							<#if li.facFinancing.assureType ??>
								<#if li.facFinancing.assureType = 1>
									<td>先担保</td> 
								<#elseif li.facFinancing.assureType = 2>
									<td>后担保</td>
									
								</#if>
							<#else>
								<td></td>
							</#if>
							
							<td>
								${(li.facFinancing.proposerDate?string("yyyy-MM-dd HH:mm:ss"))!}
							</td>
							
							<#-- 1-待认可2-待放款3-已认可4-已拒绝5-已还款6-已关闭11-超欠 -->
							<#if li.facFinancing.status ??>
								<#if li.facFinancing.status = 1>
								<td>待认可</td>
								<#elseif li.facFinancing.status = 2>
									<td>待放款</td>
								<#elseif li.facFinancing.status = 3>
									<td>已认可</td>
								<#elseif li.facFinancing.status = 4>
									<td>已拒绝</td>
								</#if>
							<#else>
								<td></td>
							</#if>
							
							
							<td>${li.facFinancing.acceptOperatorName!}</td>
							<#if li.acceptDate ??>
								<td>
									${(li.facFinancing.acceptDate?string("yyyy-MM-dd HH:mm:ss"))!}
								</td>
							<#else>
								<td></td>
							</#if>
						</tr>
					</#list>
				</#if>
            </tbody>
          </table>

        </div>

        <footer class="panel-footer text-right bg-light lter">
          <div class="right-footer">
          <#--
            <button class="btn btn-success btn-s-xs" type="submit" onclick="RKAssure()">认可</button>-->
            <button class="btn btn-success btn-s-xs" type="submit" onclick="executeAccept()">认可</button>
            <button class="btn btn-success btn-s-xs" type="submit" onclick="FQAssure()">放弃</button>
          </div>
          <#if fins ??>
          <div class="yema-page">
    			<ul class="pagination-page ">
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
     		</div>
     	</#if>	
            </footer>
          </div>
          <!-- /span9 -->
        </div>
       <!-- /row -->
     </div>  
     <!-- 模态框（Modal） -->
 <div class="modal fade" id="myModal1" role="dialog" aria-labelledby="gridSystemModalLabel">
      <div class="modal-dialog tanchu-box" role="document">

        <div class="col-sm-12" >
          <div class="panel panel-default article-bj" style="width:345px; margin:auto;">
            <div class="panel-heading box-shodm">确认窗口<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
        <div class="table-responsive panel-table-body ">
          <table class="table table-striped  fixed" id="Assure_select">
              <tr>
                <th>您是否对已选融资单进行认可！！！</th>
              </tr>
		</table>
	</div>
        <footer class="panel-footer text-right bg-light lter">
          <button class="btn btn-success btn-s-xs" onclick="executeAccept()">确认</button>
        </footer>
      </div>
    </div>
  </div>
</div>  
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="gridSystemModalLabel">
      <div class="modal-dialog tanchu-box" role="document">

        <div class="col-sm-12" >
          <div class="panel panel-default article-bj" style="width:345px; margin:auto;">
            <div class="panel-heading box-shodm">放弃窗口<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
	        <div class="table-responsive panel-table-body ">
	          <table class="table table-striped  fixed" id="Assure_select">
	              <tr>
	                <th>您是否对已选融资单进行放弃！！！</th>
	              </tr>
			</table>
			</div>
        <footer class="panel-footer text-right bg-light lter">
          <button class="btn btn-success btn-s-xs" onclick="executeVeto()">确认</button>
        </footer>
      </div>
    </div>
  </div>
</div> 

<div class="modal fade" id="myModal_transfer" role="dialog" aria-labelledby="gridSystemModalLabel">
      <div class="modal-dialog tanchu-box" role="document">

        <div class="col-sm-12" >
          <div class="panel panel-default article-bj" style="width:1000px; margin:auto;">
            <div class="panel-heading box-shodm">确认窗口<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
        <div class="table-responsive panel-table-body ">
          <table class="table table-striped  fixed" id="transfer_money">
		  </table>
	</div>
        <footer class="panel-footer text-right bg-light lter">
          <button class="btn btn-success btn-s-xs" onclick = "transferMoney()">确认</button>
        </footer>
      </div>
    </div>
  </div>
</div>  
 
</body>
</html>