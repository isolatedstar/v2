<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>

<#include "../common/macro.ftl"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>直接融资</title>
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
  <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
 <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
   
   <style>
   .modal-dialog{width:65%;}
   .ipt-form{float:right;margin-right:25px;height:30px;}
   .li-wid{float:left;display:block;width:100%}
   .rzrq{display:inline-block;}
   .fac_c_bot li{margin:5px;}
   .fac_c_bot input{width:43%;}
   .pay_button_3{margin:40px 20px;}
   .fac_c_bot_bot label{ margin:10px;}
   .fac_table .fac_tr_tab{background:#99ccff; font-weight:bold;}
   ul li{list-style:none;}
   .ipt-form{height:33px;}
   .fac_c_bot_1 li{float:left;}
   .col{background-color:#f5f5f5;}
   
   </style>
   <script type="text/javascript">
               $(document).ready(function() {
                  $('#reservationtime').daterangepicker({
                  }, function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                  });
               });
               $(document).ready(function() {
                  $('#reservationtimedate').daterangepicker({
                    timePicker: true,
                  }, function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                  });
               });
   </script>
   <script>
  
  <#--  分页    --> 
   $(function(){
	
		$("#formId").validationEngine('attach',{
		scroll:false,
		autoHidePrompt:true,
		autoHideDelay:2500,
		promptPosition : "bottomLeft"
		 });
	
		var li = $('.pagination-page li');
		
		var pageNo =0 ;
		
		pageNo = getactiveIndex(pageNo,li);
		
		//当点击的时候添加样式，获取
		li.click(function(){  
		 	 var check = $(this).attr("class");
		     if(check != "prev disabled"){
			 	 var txt = $(this).find("a").html();
			 	 var index = $(this).index();
			 	 
			 	 var financingId = $("#financingId").val();
			 	 var proposerName = $("#proposerName").val();
			 	 
			 	 
			 	 pageNo = getPageNo(pageNo,index,txt);
				
				 window.location = "${ctx}/directFinancingController/directFinancingNoFilter.do?page="+pageNo+"&financingId="+financingId+"&proposerName="+proposerName;
			 }
		})	
	})
   
   
	<#-- 
		删除行 
	-->
	function addFinancing(){
		//删除行
		var len = parseInt(Select_Assure.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("Select_Assure").deleteRow(j);
		}
		
		$("#yjhk_time").val("").css('background-color','#fff');
		
		$("#myModal").modal('show');
	}
	
	<#-- 
		选择担保资源
	 -->
	function XZAssure(){
		//删除行
		var len = parseInt(Assure_select.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("Assure_select").deleteRow(j);
		}
		
		
		var len1 = $("#append_ul_li").find("li").length;
	  	for (var ii=0;ii<len1;ii++){
	   	  $("#append_ul_li").find("li").remove(ii);
	    }
		
		
	
		var groupId = $("#applyOrganizationId").val();
		//付款方
		var payerName = $("#payerName").val();
		//担保登记员
		var guaranteeName = $("#guaranteeName").val();
		$.ajax({
			url:'${ctx}/directFinancingController/assureResourcesNoFilter.do',
			dataType:"JSON",
			type:"POST",
			data:{"groupId":groupId,"payerName":payerName,"guaranteeName":guaranteeName},
			success:function(data){
			    data = eval("("+data+")")
			    
			    for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="guaranteetId">'+data[i].guaranteeId+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="guaranteeAmount">￥'+data[i].guaranteeAmount+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="paymentAmount">￥'+data[i].paymentAmount+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="usableAmount">￥'+data[i].usableAmount+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="payerName">'+data[i].payerName+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="guaranteeName">'+data[i].guaranteeName+'</td>';
			    	row1.insertCell(6).innerHTML='<td id="expireDate">'+getFormatDateByLong(data[i].expireDate,'yyyy-MM-dd hh:mm:ss')+'</td>';
			    	row1.insertCell(7).innerHTML='<td id="bcsy_money">￥<input id="inp_txt" type="text" value="" style="width:70px; height:20px;"></td>';
			    }
			     
			    $("#append_ul_li").append("<li class='prev disabled'><a href='#'><<</a></li>");
			    var page_li;
			    if(parseInt(data.length)%10>0){
			    	page_li = parseInt(data.length/10)+1;
			    }else{
			    	page_li = parseInt(data.length/10);
			    }
			    for(var j=1;j<page_li+1;j++){
			    	$("#append_ul_li").append("<li><a href='#'>"+j+"</a></li>");
			    }
			    
			    $("#append_ul_li").append("<li class='next'><a href='#' >>></a></li>");
			    $("#myModal1").modal('show');
			}
		})
	}
	
	<#--  移除  -->
	function removeAssure(){
		
		$('#Select_Assure').find("tr:not(first)").each(function(i,element){
			var check = $(element).find("input[name=forLowerId]:checkbox").prop("checked");
			if(check == true){
				 document.getElementById('Select_Assure').deleteRow(i);
			}
		})
	}
	
	
	<#--  添加担保资源     -->
	function addAssure(){
		var assure_id="";
			if(Select_Assure.rows.length>1){
				$('#Select_Assure').find("tr:not(first)").each(function(i,element){
					assure_id += $(element).find("td:eq(1)").html()+",";
					
				})
			}
		 assure_id = assure_id.substring(0,assure_id.length-1);
	
		$('#Assure_select').find("tr:not(first)").each(function(i,element){
			
			if($(element).find("[id=inp_txt]").val()!=null || $(element).find("[id=inp_txt]").val()!=undefined ){
				if($(element).find("[id=inp_txt]").val() == ""){
					$("#myModal1").modal('hide');
				}else{
					var guaranteetId= $(element).find("td:eq(0)").html();
					var guaranteeAmount= $(element).find("td:eq(1)").html();
					var nonPayAmount= $(element).find("td:eq(3)").html();
					var guaranteeName= $(element).find("td:eq(5)").html();
					var bcsy_money = $(element).find("[id=inp_txt]").val();
					
					//如果添加的担保资源存在 ，则金额相加  不再添加行
					if(assure_id!=""){
						
						if(assure_id.indexOf(guaranteetId)>0){
							$('#Select_Assure').find("tr:not(first)").each(function(i,e){
								var id = $(e).find("td:eq(1)").html();alert(id)
								if(id==guaranteetId){
									var money = parseFloat($(e).find("td:eq(5)").html().substring(1))+parseFloat(bcsy_money);
									$(e).find("td:eq(5)").html("￥"+money);
								}
							})
							
						}else{
							var row2 = document.getElementById("Select_Assure").insertRow(Select_Assure.rows.length);
							row2.insertCell(0).innerHTML='<td id="checkbox"><input type="checkbox" name="forLowerId"/></td>';
							row2.insertCell(1).innerHTML='<td id="guaranteetId">'+guaranteetId+'</td>';
							row2.insertCell(2).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
							row2.insertCell(3).innerHTML='<td id="nonPayAmount">'+nonPayAmount+'</td>';
							row2.insertCell(4).innerHTML='<td id="guaranteeName">'+guaranteeName+'</td>';
							row2.insertCell(5).innerHTML='<td id="bcsy_money">￥'+bcsy_money+'</td>';
						}
						
					}else{//不存在 添加行
						var row2 = document.getElementById("Select_Assure").insertRow(Select_Assure.rows.length);
						row2.insertCell(0).innerHTML='<td id="checkbox"><input type="checkbox" name="forLowerId"/></td>';
						row2.insertCell(1).innerHTML='<td id="guaranteetId">'+guaranteetId+'</td>';
				    	row2.insertCell(2).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
				    	row2.insertCell(3).innerHTML='<td id="nonPayAmount">'+nonPayAmount+'</td>';
				    	row2.insertCell(4).innerHTML='<td id="guaranteeName">'+guaranteeName+'</td>';
				    	row2.insertCell(5).innerHTML='<td id="bcsy_money">￥'+bcsy_money+'</td>';
					}
				}
			}
				$("#myModal1").modal('hide');	
		})
	}
	
	<#---
		/**
		*点击融资
		**/
	-->
	function financing(){
	   //取本次使用的担保资源总金额
		var bcsy_money_total=parseFloat(0);
		$('#Select_Assure').find("tr:gt(0)").each(function(i,element){
			bcsy_money = parseFloat($(element).find("td:eq(5)").html().substring(1));
			bcsy_money_total=bcsy_money_total+bcsy_money;
		})
		
		//取本次融资金额
	
	    var bcrz_money ;
		
		var bcrzJE = $("#bcrz_money").val();
		if(bcrzJE.length==0){
			alert("融资金额不能为空！");
			return;
		}else{
			bcrz_money = parseFloat($("#bcrz_money").val());
		}
		
		//判断预计还款日期是否为空
		var yjhk_time = $("#yjhk_time").val();
			
		if(yjhk_time == null || yjhk_time == ""){
			alert("预计还款日期不能为空！！！");
			return;
		}
	 
	//比较本次融资金额和呢每次担保使用总金额的大小
		if(bcrz_money > bcsy_money_total){
			$("#myModalJets").modal("show");
		}else{
			
			var ids="" ;
			var jsonAss = "[";
			$('#Select_Assure').find("tr:gt(0)").each(function(i,element){
				var id = $(element).find("td:eq(1)").html();
				ids += $(element).find("td:eq(1)").html()+",";
				
				var bcsy_money = $(element).find("td:eq(5)").html().substring(1);
				
				jsonAss += "{" 
							  +"\"guaranteeId\":\""+id
							  +"\",\"usableAmount\":\""+bcsy_money
							  +"\"},";
				
			})
			ids = ids.substring(0,ids.length-1);
			
			jsonAss = jsonAss.substring(0,jsonAss.length-1);
			jsonAss += "]";
			console.info(jsonAss);
			
			//放贷放id
			var member_name = $("#factoringName").find("option:selected").text();
			//放贷放name
			//var index1 = factoringId_name.indexOf('[')
			//var index2 = factoringId_name.indexOf(']')
			
			var factoringId = $("#factoringName").val();
			//var member_name = factoringId_name.substring(parseInt(index1)+1,parseInt(index2));
			
			//融资金额
			var bcrz_money = $("#bcrz_money").val();
			//预计还款日期
			var yjhk_time = $("#yjhk_time").val();
			
			var repayDays = parseInt(repayDay());
			//预计服务费
			var fee = parseFloat(bcrz_money*0.01).toFixed(2);
			var interest = bcrz_money*0.08/360*(repayDays);
			
			$.ajax({
				url:'${ctx}/directFinancingController/FinancingNoFilter.do',
				dataType:"JSON",
				type:"POST",
				data:{"ids":ids,"loanData":jsonAss,"factoringId":factoringId,"bcrz_money":bcrz_money,"yjhk_time":yjhk_time,"repayDays":repayDays,"member_name":member_name},
				success:function(data){
				   	 data = eval("("+data+")");
				   	 alert(data);
				   	 if(data == "融资成功！"){
				   	 	window.location = "${ctx}/directFinancingController/directFinancingNoFilter.do?page=1"
				   	 }
				}
			})
			
		}
		
	}
	
	<#--
	  	获取融资天数
	-->
	function repayDay(){
			var nowDate = new Date();
			var dateStr = $("#yjhk_time").val();
			if(dateStr == ''){
				return -1;
			}
			var repayDate = new Date();
			repayDate.setYear(parseInt(dateStr.substring(0,4)));
			repayDate.setMonth(parseInt(dateStr.substring(5,7)));
			repayDate.setDate(parseInt(dateStr.substring(8,10)));
			repayDate.setHours(parseInt(dateStr.substring(11,13)));
			repayDate.setMinutes(parseInt(dateStr.substring(14,16)));
			repayDate.setSeconds(parseInt(dateStr.substring(17,19)));
			var days = Math.ceil((repayDate.getTime() - nowDate.getTime())/(24*3600*1000)) - 30;
			var days1 = Math.ceil((repayDate.getTime() - nowDate.getTime())/(24*3600*1000)) - 28;
			var days2 = Math.ceil((repayDate.getTime() - nowDate.getTime())/(24*3600*1000)) - 31;
			if(repayDate.getMonth() == 2){
			   return parseInt(days1);
			}else if(repayDate.getMonth() == 4 || repayDate.getMonth() == 6 || repayDate.getMonth() == 9 || repayDate.getMonth() == 11){
			   return parseInt(days);
			}
			return parseInt(days2);
		}
		
		
		<#--
	/**
	 *预计还款日改变时，修改预计利息和预计服务费
	**-->
		function repaymentDateChange(obj){
			var repayDays = parseInt(repayDay());
			var bcrz_money = parseInt($("#bcrz_money").val());
			var fee;
			var interest;
			
			if(bcrz_money != null || bcrz_money != ""){
				
			    fee = parseFloat(bcrz_money*0.01).toFixed(2);
				$("#fee").html('<label>预计服务费：</label>¥'+((fee=='NaN')?'0.00':fee));
				
				interest = parseFloat(bcrz_money*0.08/360*(repayDays)).toFixed(2);
				
				$("#interest").html('<label>预计利息：</label>¥'+((interest=='NaN')?'0.00':interest));
			}
		}
	
	<#--
	/**
	 *融资金额改变时，修改预计利息和预计服务费
	**-->
	function financingMoneyChange(obj){
			var repayDays = parseInt(repayDay());
			var bcrz_money = $("#bcrz_money").val();
			var fee;
			var interest;
			
			//预计还款日期
			var yjhk_time = $("#yjhk_time").val();
			if(yjhk_time == null || yjhk_time == ""){
				return;
			}else{
			    fee = parseFloat(bcrz_money*0.01).toFixed(2);
				$("#fee").html('<label>预计服务费：</label>¥'+((fee=='NaN')?'0.00':fee));
				
				interest = parseFloat(bcrz_money*0.08/360*(repayDays)).toFixed(2);
				$("#interest").html('<label>预计利息：</label>¥'+((interest=='NaN')?'0.00':interest));
			}
	}
	
	<#--
		选择担保方式，查询选择的担保信息
	-->
	function assuseWay(obj){
	
		//删除行
		var len = parseInt(Assure_way.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("Assure_way").deleteRow(j);
		}
		
		//获取融资单号
		var financingId = $(obj).parent().find('a').attr("temp");
		$.ajax({
			url:'${ctx}/directFinancingController/getAssureMssgNoFilter.do',
			dataType:"JSON",
			type:"POST",
			data:{"financingId":financingId},
			success:function(data){
			   	 data = eval("("+data+")");
			   	 
			   	  for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_way").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="guaranteetId">'+data[i].guaranteeId+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="guaranteeAmount">￥'+data[i].guaranteeAmount+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="paymentAmount">￥'+data[i].paymentAmount+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="usableAmount">￥'+data[i].usableAmount+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="payerName">'+data[i].payerName+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="expireDate">'+getFormatDateByLong(data[i].expireDate,'yyyy-MM-dd hh:mm:ss')+'</td>';
			    	row1.insertCell(6).innerHTML='<td id="bcsy_money">￥'+data[i].facFinancingGuarantee.paymentsAmount+'</td>';
			    	row1.insertCell(7).innerHTML='<td id="freezeAmount">￥'+data[i].facFinancingGuarantee.freezeAmount+'</td>';
			    }
			    	$("#myModalType").modal('show');
			}
		})
		
	}
	 
	function selectMass(){
		
		//取当前class = active的li下标
		var pageNo =0 ;
		li.each(function(i,element){
			var clas = $(element).attr("class");
			if(clas != undefined){
				if(clas.indexOf("active") > -1){
					pageNo = parseInt($(element).find("a").html());
				}
			}
		})
		
		
	}
		
		
	<#-- 时间控制   -->
  function now(){
    var date = new Date();
    var year = date.getFullYear();
    var mth = date.getMonth() + 1;
    var day = date.getDate();
    return year+"-"+mth+"-"+day;
  }
			
   </script>

</head>
<body>
<div>
<div class="container-fluid ">
	 <div class="row-fluid ">

        <div class="panel panel-default article-bj ">

          <div class="panel-heading box-shodm">直接融资表</div>

	<!-- 融资查询 -->
	 <div class="row wrapper form-margin">
            <form id="formId" action="${ctx}/directFinancingController/directFinancingNoFilter.do?page=1"　method="post">
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">融资单号:</h5>
                </div>
                <input type="text" class="form-control" name="financingId" id="financingId" value="${financingId!}"></div>
             </div>
           

            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">操作员:</h5>
                </div>
                <input type="text" placeholder="" class="form-control validate[custom[chinese]" value="${proposerName!}" name="proposerName" id="proposerName"></div>
            </div>

            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">还款日期:</h5>
                </div>
                <input type="text" placeholder=""class="form-control " value="${predictRefundDate!}" name="predictRefundDate" id="reservationtime" ></div>
            </div>
            <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">融资日期:</h5>
              </div>
              <input type="text" placeholder="" class="form-control " value="${proposerDate!}" name="proposerDate" id="reservationtimedate"></div>
          </div>
        
		<#--
        <div class="col-md-3">
          <div class="input-group">
            <div class="input-group-btn">
              <h5 class="h5-margin">状态:</h5>
            </div>
            <select name="selecter_basic" tabindex="-1"class="form-control">
              <option value="1">已确认</option>
              <option value="2">待确认</option>

            </select>
          </div>
        </div>-->
        <input type="submit" class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" value="查询" style="height:35px;width:65px"/>

      </form>
    </div>
		<!-- 查询条件 over-->
      	<!--content-->
		<!--表单-->
					 <div class="panel-body">

          <div class="table-responsive">
            
            <table id="sample_2" class="table table-striped table-hover ">
              <thead>
                <tr>
                  <th>融资单号</th>
                  <th>融资金额</th>
                  <th>预计还款日期</th>
                  <th>担保方式</th>
                  <th>预计利息</th>
                  <th>预计服务费</th>
                  <th>融资时间</th>
				  <th>操作员</th>
				  <th>状态</th>
                </tr>
              </thead>
			  <tbody>
				<#if Flist ??>
					<#list Flist as li>
						<tr>
							<input type="hidden" id="applyOrganizationId" value="${li.facFinancing.applyOrganizationId!}"/>
							<td>${li.facFinancing.financingId}</td>
							<td>${li.facFinancing.waitPayTotal}</td>
							<td>${li.facFinancing.predictRefundDate?datetime?string}</td>
							<td><a onclick="assuseWay(this)" temp = "${li.facFinancing.financingId}">先担保后认可</a></td>
							<td>￥${li.facFinancing.predictInterest!0}</td>
							<td>￥${li.facFinancing.predictServiceFee!0}</td>
							<td>${li.facFinancing.proposerDate!?datetime?string}</td>
							<td>${li.facFinancing.proposerName!}</td>
							<#if li.facFinancing.status ??>
								<#if li.facFinancing.status==1>
									<td>待认可</td>
								<#elseif li.facFinancing.status==2>
									<td>待放款</td>
								<#elseif li.facFinancing.status==3>
									<td>已认可</td>
								<#elseif li.facFinancing.status==4>
									<td>已拒绝</td>
								<#elseif li.facFinancing.status==5>
									<td>已还款</td>
								<#elseif li.facFinancing.status==6>
									<td>已关闭</td>
								<#elseif li.facFinancing.status==7>
									<td><font color = "red">超欠<font></td>
								</#if>
							</#if>
						</tr>
					</#list>
				</#if>
			  </tbody> 
            </table>
          </div>
        </div>
          <footer class="panel-footer text-right bg-light lter">
		       <div class="right-footer">
		     		<button class="btn btn-success btn-s-xs" type="submit" onclick="addFinancing()">添加新融资</button>
		       </div>
			<#if Flist ??>
			 <div class="yema-page">
    			<ul class="pagination-page ">
		            <#if clas ??>
	            		<#if clas == 1>
	            			<li class="prev disabled">
	            		<#else>
	            			<li>
	            		</#if>
            		<#else>
	            		<li class="prev disabled">
	            	</#if>
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
		            <#if length ??>
			            <#if clas ??>
		            		<#if clas == length>
		            			<li class="prev disabled">
		            		<#else>
		            			<li>
		            		</#if>
	            		<#else>
		            		<li class="prev disabled">
		            	</#if>
		            <#else>
		            		<li class="prev disabled">
		            </#if>
		                <a href="#" >>></a>
		            </li>
		        </ul>
     		</div>
     		</#if>
  	    </footer>
	</div>
	<!-- 融资查询 over -->



<!-- 模态框（Modal） -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
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
            <!--添加新融资页-->
				<div class="fac_c_none_warp">
					<div>
						<ul class="fac_c_bot">
							<li class="li-wid"><label style="padding-left:38px;float:left">放贷方：</label>
								<select class="form-control ipt-form" id="factoringName" style="width:43%;float:left">
									<option>请选择</option>
									<#if mebers ??>
										<#list mebers as meber>
											<option value="${meber.id!}" name="${meber.mmbSname!}">${meber.mmbSname!}</option>
										</#list>
									</#if>
								</select>
							</li>
							<li class="li-wid"><label style="padding-left:25px;float:left">融资金额：</label>
								<input id = "bcrz_money" type="text" class="form-control ipt-form" value="" onchange = "financingMoneyChange(this)" style="float:left;"/>
							</li>
							<li class="li-wid"><label style="float:left;">预计还款日期：</label>
								<input class="Wdate form-control ipt-form" id="yjhk_time" type="text" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})" onChange = "repaymentDateChange(this)" style="float:left;"/></li>
						</ul>
					</div>
					<div class="clearfix" style="margin:20px 0;"></div>
					<div class="db_wrap">
						<table class="table table-striped" cellspacing="0"  cellpadding="5" id = "Select_Assure">
							<tr class="fac_tr_tab">
								
								<td width="2%"><input type="checkbox" /></td>
								<td width="6%">担保单号</td>
								<td width="6%">担保金额</td> 
								<td width="6%">担保可用金额</td>
								<td width="6%">担保登记员</td>
								<td width="6%">本次使用金额</td>
							</tr>
						 </table>
					</div> 
					 <div id="cinbtn">
					 	<input type="button" class="btn btn-default" value="选择担保资源" onclick="XZAssure()"/>
					 	<input type="button" class="btn btn-default" value="移除"  onclick="removeAssure()"/> 
					 </div>
					 <div class="fac_c_bot_bot" id="cinbtnfhsyj_box">
						 <ul class="fac_c_bot_1">
							<li id="interest"><label>预计利息：</label>￥0.00</li>
							<li id="fee"><label>预计服务费：</label>￥0.00</li>
						 </ul>
						<input type="button" class="btn btn-success btn-s-xs" value="融资" onclick = "financing();"/> 
					 </div> 
				</div>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
 <div class="modal fade" id="myModal1" role="dialog" aria-labelledby="gridSystemModalLabel">
      <div class="modal-dialog tanchu-box" role="document">

        <div class="col-sm-12 " >
          <div class="panel panel-default article-bj">
            <div class="panel-heading box-shodm">选择担保资源表<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></div>
            <div class="row wrapper form-margin">
              <form>
                <div class="col-md-3" >
                  <div class="input-group" style="width:300px">
                    <div class="input-group-btn">
                      <h5 class="h5-margin">付款方:</h5>
                  </div>
                  <input type="text" placeholder="" class="form-control" name="payerName" id="payerName"></div>
              </div>
              <div class="col-md-3" style="width:350px;left:100px">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">担保登记员:</h5>
                </div>
                <input type="text" placeholder="" class="form-control" name="guaranteeName" id="guaranteeName"></div>
            </div>
            <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" onclick="XZAssure()">查询</a>
          </form>
        </div>

        <div class="table-responsive panel-table-body ">
          <table class="table table-striped  fixed" id="Assure_select">
              <tr>
                <th>担保单号</th>
                <th>担保单金额</th>
                <th>已支付金额</th>
                <th>可用担保金额</th>
                <th>付款方</th>
                <th>担保登记员</th>
                <th>到期时间</th>
                <th>本次使用金额</th>
              </tr>
		</table>
	</div>
        <footer class="panel-footer text-right bg-light lter">
        <div class="right-footer">
         	 <button class="btn btn-success btn-s-xs" onclick="addAssure()">确认</button>
         </div>
    	<div class="yema-page">
    		<ul class="pagination-page" id="append_ul_li">
			 
			</ul>
		</div>
			
        </footer>
        
      </div>
    </div>
  </div>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModalType" tabindex="-1" role="dialog" 
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
             <!--担保方式 one-->
				<div>
					<div>
				        <table class="table table-striped " cellspacing="0"  cellpadding="5" id = "Assure_way">
				            <tr class="fac_tr_tab">
				                <td width="10%">担保单号</td>
				                <td width="10%">担保单金额</td>
				                <td width="10%">已支付金额</td>
				                <td width="11%">可用担保金额</td>
				                <td width="8%">付款方</td>
				                <td width="10%">到期时间</td>
				                <td width="11%">本次使用金额</td>
				                <td width="10%">本次冻结金额</td>
				            </tr>
				        </table>
				    </div>
				</div>
			<!--担保方式  one  over-->  

         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModalJets" tabindex="-1" role="dialog" 
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
            	选择的担保资源的本次使用金额小于融资金额，请您继续添加担保资源或修改融资金额！
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


</body>
</html>