<#--<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />  引入security.tld标签-->
<#assign ctx = request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
   
 <!-- <script src="${ctx}/factoring/js/jquery.js"></script>
  <script src="${ctx}/factoring/js/bootstrap.min111.js"></script>

  <link href="${ctx}/factoring/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
  <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
   <script type="text/javascript" src="${ctx}/common/js/common.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>-->
   <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
   <script src="${ctx}/mall/js/jquery.js"></script>
     <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
   <script src="${ctx}/mall/js/jquery.min.js"></script>	
      <script src="${ctx}/mall/js/bootstrap.min.js"></script>
     <script src="${ctx}/mall/js/page.js"></script>
     	     <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
	     <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
	     <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
       <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>	
  <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/DatePicker/WdatePicker.js"></script>
      <script type="text/javascript">
      	$(function(){
   		 $("#option1").click(function(){
   		 	var option = $("#option1").val();
   		 	//alert(option);
   		 	if(option == 1){
   		 	$("#postMoneyCompanyInput").prop("hidden",false);
   		 	$("#postMoneyCompany").prop("hidden",true);
   		 	$("#getMoneyCompanyInput").prop("hidden",true);
   		 	$("#getMoneyCompany").prop("hidden",false);
   		 	}else if(option == 0){
   		 	$("#getMoneyCompanyInput").prop("hidden",true);
   		 	$("#getMoneyCompany").prop("hidden",true);
   		 	$("#postMoneyCompanyInput").prop("hidden",true);
   		 	$("#postMoneyCompany").prop("hidden",false);
   		 	}
	  	 });
	  	                   $('#selectTime').daterangepicker(
                  //{	
                    //timePicker: true,
                //  }, 
                  //function(start, end, label) {
                    //console.log(start.toISOString(), end.toISOString(), label);
                  //}
                  );
                  	  function now(){
    var date = new Date();
    var year = date.getFullYear();
    var mth = date.getMonth() + 1;
    var day = date.getDate();
    return year+"-"+mth+"-"+day;
  }
   });
     
        /*$(function(){
   		 $('#register').click(function(){
		  	$('#selectForm').submit();
	  	 });
   });*/
     
        /*function getBalanceDate(){
           var id = document.getElementById("id").value;
        //alert(id);
        id = "1";
	  	var len = parseInt(BalanceData.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("BalanceData").deleteRow(j);

		}
	         								var payerName = $("#payerName").val();
 								var payeeName = $("#payeeName").val();
 	
 								var type = $("#selecter_basic").val();
 	
 					window.location = "${ctx}/assureController/toAssureNoFilter.do?payerName="+payerName+"&payeeName="+payeeName+"&type="+type;
	 	$.ajax({
			url:'${ctx}/BalanceSheetAction/querySTLList.do',
			dataType:"JSON",
			type:"POST",
			data:{"id":id},
			success:function(data){
			alert(data);		
			    data = eval(data);
			 for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }
			    window.location ="${ctx}/BalanceSheetAction/toBanlance.do";
			}
		});
	var payerName = $("#payerName").val();
 	var payeeName = $("#payeeName").val();
 	
 	var type = $("#selecter_basic").val();
 	
 	window.location = "${ctx}/assureController/toAssureNoFilter.do?payerName="+payerName+"&payeeName="+payeeName+"&type="+type;
  }*/	
  function deleteOrder(){
  	/*var len = parsenInt(balanceTable.rows.length);
  	for(){	
  		if(document.){
  		}
  	}*/
  	var j = 0;
  	$('#balanceTable').children("tbody").find("tr:not(first)").each(function(i,element){
  		alert(i);
  		
  	if($(element).find("[id=checkbox]").prop("checked")){
  					document.getElementById("balanceTable").deleteRow(j);
  					j=j-1;
  	}
  	j=j+1;
  	})
  }
                    	  function now(){
    var date = new Date();
    var year = date.getFullYear();
    var mth = date.getMonth() + 1;
    var day = date.getDate();
    return year+"-"+mth+"-"+day;
  }
  function addOrderNum(){
  	$('#addOrder').modal('hide');

  	/*var len = parseInt(balanceTable.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("balanceTable").deleteRow(j);
		}*/
  				  			$('#Assure_select').children("tbody").find("tr:not(first)").each(function(i,element){
			//alert("check的值" + $(element).find("[id=order]").prop("checked"));
			//alert("check的值" + $(element).find("td:eq(0)").prop("checked"));
			//alert("check的值" + $(element).find("td:eq(1)").html());
			//alert($(element).find("[id=order]").prop("checked"));
			//alert("进入新方式");
			//alert("check的值" + $(element>input:checkbox).checked);
			//if($(element).find("checkbox").val()!=null || $(element).find("checkbox").val()!=undefined ){
				if($(element).find("[id=order]").val() == ""){
				//alert("空");
					$("#changeChar").modal('hide');
				}else if($(element).find("[id=order]").prop("checked")){
					
					//var guaranteetId= $(element).find("td:eq(0)").html();
					var guaranteeAmount= $(element).find("td:eq(1)").html();
					var guaranteeAmount1= $(element).find("td:eq(2)").html();
					var nonPayAmount= $(element).find("td:eq(3)").html();
					var guaranteeName= $(element).find("td:eq(4)").html();
					//var bcsy_money = $(element).find("[id=inp_txt]").val();
					
					var row2 = document.getElementById("balanceModel").insertRow(balanceModel.rows.length);
					row2.insertCell(0).innerHTML='<td ><input type="checkbox" value="'+guaranteeAmount+'" name ="balanceCheckBox"id="checkbox"/></td>';
					//row2.insertCell(1).innerHTML='<td id="guaranteetId">'+guaranteetId+'</td>';
			    	row2.insertCell(1).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	row2.insertCell(2).innerHTML='<td id="guaranteeAmount1">'+guaranteeAmount1+'</td>';
			    	row2.insertCell(3).innerHTML='<td id="nonPayAmount">'+nonPayAmount+'</td>';
			    	row2.insertCell(4).innerHTML='<td id="guaranteeName"><input type="text"class="input-checkbox" value = "'+guaranteeName+'" id="PayBalanceNum" name="PayBalanceNum"></td>';
			    	row2.insertCell(5).innerHTML='<td ><input type="text"class="input-checkbox" id="confirmBalanceNum" name="confirmBalanceNum"></td>';
			    	//row2.insertCell(3).innerHTML='<td id="nonPayAmount">'+nonPayAmount+'</td>';
			    	//row2.insertCell(4).innerHTML='<td id="guaranteeName">'+guaranteeName+'</td>';
			    	//row2.insertCell(5).innerHTML='<td id="bcsy_money">￥'+bcsy_money+'</td>';
			    	//row2.insertCell(2).innerHTML='<td id=""><input id="checkbox" hidden = "hidden" type="text" value="" style="width:70px; height:20px;"></td>';
				}
			//}
		})
				$("#addOrder").modal('hide');	
  		  	<#--var len = parseInt(BalanceData.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("BalanceData").deleteRow(j);
		}
					    for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	//row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	//row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }-->
			    	

  }

  	function passBalance(passId,status){
  		//alert(passId);
  			 	$.ajax({
			url:'${ctx}/BalanceSheetAction/editSTL.do',
			dataType:"JSON",
			type:"POST",
			data:{"id":passId,"status":status},
			success:function(data){
			alert(data);
			    data = eval(data);
			    
			   /* for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(i);
			    	row1.insertCell(0).innerHTML='<td id=""><input type="checkbox" id="order"/></td>';
			    	//row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	//row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	//row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    window.location ="${ctx}/BalanceSheetAction/toBanlance.do";
			}
		});
  	}
  	function passBalanceSin(passId,signatureStatus,status){
  		//alert(passId);
  			 	$.ajax({
			url:'${ctx}/BalanceSheetAction/editSTL.do',
			dataType:"JSON",
			type:"POST",
			data:{"id":passId,"signatureStatus":signatureStatus,"status":status},
			success:function(data){
			alert(data);
			    data = eval(data);
			    
			   /* for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(i);
			    	row1.insertCell(0).innerHTML='<td id=""><input type="checkbox" id="order"/></td>';
			    	//row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	//row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	//row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    window.location ="${ctx}/BalanceSheetAction/toBanlance.do";
			}
		});
  	}
  	function passBalanceManage(passId,status,obj){
  	//alert(status);
  	//alert(obj);
  	//alert(obj.id);
  	//alert($(obj).value);
  	//alert($(obj).val());
  	//alert(obj.parentNode.childNodes[0].nodeValue);
  	//alert($(obj).parent().val());
  	//alert($(obj).parent().parent().val());
  	var ctrTimeChange = $(obj).parent().parent().find("[id = ctrTimeChange]").val();
  	var myMoney = $(obj).parent().parent().find("[id = myMoney]").val();
  	//alert(myMoney);
  	//alert(ctrTimeChange);
  	//alert(passId);
  			 	$.ajax({
			url:'${ctx}/BalanceSheetAction/editSTL.do',
			dataType:"JSON",
			type:"POST",
			data:{"id":passId,"status":status,"ctrTimeChange":ctrTimeChange,"ctrMoney":myMoney},
			success:function(data){
			alert(data);
			    data = eval(data);
			    
			   /* for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(i);
			    	row1.insertCell(0).innerHTML='<td id=""><input type="checkbox" id="order"/></td>';
			    	//row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	//row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	//row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    window.location ="${ctx}/BalanceSheetAction/toBanlance.do";
			}
		});
  	}
     </script>
<title>待审批结款单</title>
</head>

<body>
<div class="container-fluid" style=" margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                待审批结款单
                </div>
        			<div class="row wrapper form-margin">
       					 <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">结款单号:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <input type="text" placeholder="" class="form-control" name="id" id="id">
        					</div>
        				</div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">结款方:</h5>
                                <!--   <button class="btn" type="button"></button> -->
                                </div>
                        <script type="text/javascript">
        function getBalanceDate(){
        // var id = document.getElementById("id").value;
       ////alert(id);
        /*id = "1";
	  	var len = parseInt(BalanceData.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("BalanceData").deleteRow(j);

		}
	         								var payerName = $("#payerName").val();
 								var payeeName = $("#payeeName").val();
 	
 								var type = $("#selecter_basic").val();
 	
 					window.location = "${ctx}/assureController/toAssureNoFilter.do?payerName="+payerName+"&payeeName="+payeeName+"&type="+type;
	 	$.ajax({
			url:'${ctx}/BalanceSheetAction/querySTLList.do',
			dataType:"JSON",
			type:"POST",
			data:{"id":id},
			success:function(data){
			alert(data);		
			    data = eval(data);
			 for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }
			    window.location = "${ctx}/assureController/toAssureNoFilter.do?payerName="+payerName+"&payeeName="+payeeName+"&type="+type;
			}
		});	*/
	var id = $("#id").val();
 	var payerName = $("#payerName").val();
 	
 	var status = $("#selecter_basic").val();
 	var selectTime = $("#selectTime").val();
 	alert(id+payerName+status);
 	window.location = "${ctx}/BalanceSheetAction/querySTLListByBalance.do?id="+id+"&payerName="+payerName+"&status="+status+"&selectTime="+selectTime;
  }
                                      </script>
                                <input type="text" placeholder="" class="form-control" name="payerName" id="payerName">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">状态:</h5>
                                <!--   <button class="btn" type="button"></button> -->
                                </div>
                                <select name="selecter_basic" id="selecter_basic" tabindex="-1"class="form-control">
                                <option value="">执行中</option>
                                <option value="b2">对方请求终止</option>
                                <option value="b3">本方请求终止</option>
                                <option value="b4">已终止</option>	
                                </select>
                        	</div>
                        </div>
                        <br/>
                        <script type="text/javascript">

                        </script>
                         <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">开始时间:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <input type="text" placeholder=""  class="form-control" name="selectTime" id="selectTime"/>
        					</div>
        				</div>
       					<a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin"  onclick ="getBalanceDate()"href="#">查询</a>
       				 </div>
                    <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover " id="BalanceData">
                            <thead>
                                <tr>
                                    <th>结款单号</th>
                                    <th>结款对方</th>
                                    <th>对方提议时间</th>
                                    <th>我的提议时间</th>
                                    <th>结款订单金额</th>
                                    <th>对方提议金额</th>
                                    <th>我的提议金额</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                               <#-- <tr>
                                    <td>10001</td>
                                    <td>顺平信德</td>
                                    <td>6333  1309  465</td>
                                    <td><input type="text"class="input-size"></td>
                                    <td>230</td>
                                    <td><input type="text"class="input-size"></td>
                                    <td><a href="#">请求终止</a></td>
                                </tr>
                                <tr>
                                	<td>10003</td>
                                    <td>天津绿鲜源</td>
                                    <td>6333  1309  231</td>
                                    <td><input type="text"class="input-size"></td>
                                    <td>150</td>
                                    <td><input type="text"class="input-size"></td>
                                    <td><a href="#" >同意终止</a>
                                    	<a href="#" >拒绝终止</a>
                                    </td>
                                </tr>
                                <tr>
                                	<td>10005</td>
                                    <td>福建壹戈</td>
                                    <td>6333  1309   155</td>
                                    <td><input type="text"class="input-size"></td>
                                    <td>95</td>
                                    <td><input type="text"class="input-size"></td>
                                    <td><a href="#">撤回终止请求</a></td>
                                </tr>-->
                                <#if hc ??>
                                <#list hc as company>
              					<#--<#if company.status == "a1">
              					                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" value ="longer"id ="${company.settleMoney}"onclick="passBalanceManage(${company.id},'${company.status}',this)">编辑</a></td>
                                <#if company.payerName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a2')">同意</a></td>
                                </#if>
                                <#if company.beneficiaryName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a3')">同意</a></td>
                                </#if>-->
								<#if company.status == "a2" && company.beneficiaryName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
								<td><a href="#" value ="longer"id ="${company.settleMoney}"onclick="passBalanceManage(${company.id},'a3',this)">编辑</a></td>
								<td><a href="#" onclick="passBalance(${company.id},'a4')">确认</a></td>
								<#elseif company.status == "a3" && company.payerName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
								<td><a href="#" value ="longer"id ="${company.settleMoney}"onclick="passBalanceManage(${company.id},'a2',this)">编辑</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">确认</a></td>
                                <#elseif company.status == "a4" && company.beneficiaryName == companyName && company.signatureStatus =="c1">           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b3')">申请终止</a></td>
                                <td><a href="#" onclick="passBalanceSin(${company.id},'c3','${company.status}')">完成同意</a></td>
                                <#elseif company.status == "a4" && company.payerName == companyName && company.signatureStatus =="c1">           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b2')">申请终止</a></td>
                                <td><a href="#" onclick="passBalanceSin(${company.id},'c2','${company.status}')">完成同意</a></td>
                                <#elseif company.status == "b3" && company.payerName  == companyName>           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b4')">同意终止</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">拒绝终止</a></td>
                                <#elseif company.status == "b3" && company.beneficiaryName == companyName>           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">撤销终止</a></td>
                                <#elseif company.status == "b2" && company.beneficiaryName == companyName>           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b4')">同意终止</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">拒绝终止</a></td>
                                <#elseif company.status == "b2" && company.payerName  == companyName>           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">撤销终止</a></td>
                                <#--
                                <#elseif company.status == "b4">           <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" >禁止全部操作</a></td>
                                <#--><#elseif company.status == "b2" && company.beneficiaryName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b2')">同意终止</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">撤销终止</a></td>
                               	<#elseif company.status == "b1" && company.beneficiaryName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b3')">同意</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">撤销</a></td>
                                <#elseif company.status =="b2" && company.payerName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                               	<td><a href="#" onclick="passBalance(${company.id},'b4')">同意</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">撤销</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b1')">拒绝</a></td>
                                <#elseif company.status == "b3" && company.beneficiaryName == companyName>                                <tr id = "${company.id}">
                                <td>${company.id}</td>
                                <td>${company.payerName}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>
                                
                                
                                
                                                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="${company.settleMoney}" id="ctrTimeChange">
                                
                                
                                
                                
                                </td>
                                <td>${company.settleMoney}</td>	
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size" id ="myMoney" ></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b4')">同意</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'a4')">撤销</a></td>
                                <td><a href="#" onclick="passBalance(${company.id},'b1')">拒绝</a></td>-->
								</#if>
                                </tr>
                                </#list>
                                </#if>

                            </tbody>
                        </table>
                    </div> 
                    <footer class="panel-footer text-right bg-light lter">
                    	<button class="btn btn-success btn-s-xs " type="submit" data-toggle="modal" data-target="#addMenu">创建结款单</button>
                    	<div hidden="hidden">
                        <button class="btn btn-info btn-s-xs btn-margin" hidden ="hidden"type="submit" >提交编辑</button>
                        <button class="btn btn-warning btn-s-xs btn-margin" hidden ="hidden"type="submit">同意</button>
                        <button class="btn btn-danger btn-s-xs btn-margin" hidden ="hidden" type="submit">拒绝</button>
                        </div>
                    </footer>
        		</div>
       		 </div>
        </div>
    </div> 
</div>
      <script type="text/javascript">
      function confirmOrder(){
         		 	var radio =  document.getElementsByName("options");
   		 	   var radioLength = radio.length;
   for(var i =0;i < radioLength;i++)
   {
    if(radio[i].checked)
    {
     var radioValue = radio[i].value;
     //alert(radioValue);
       if(radioValue == 1){
      	$("#postMoney").attr('text',$("#postCompanyName").attr("name"));
   		 	        var obj = document.getElementById("getMoneyCompanySelect");
        //alert(obj);
      	var index = obj.selectedIndex;
      	//alert(index);
      	var text  =obj.options[index].text;
      	//alert(text);	
      	var value = obj.options[index].value;
      	//alert(value);
      	$("#getMoney").attr('value',text);
      	$("#getMoney").attr('text',value);
      	var postname = $("#postCompanyName").attr("name");
      	$("#postMoney").attr('value',$("#postCompanyName").val());
      	alert(${memberId});
      	//alert();
      	//$("#postMoney").attr('value',value);
      	      		 	$.ajax({
			url:'${ctx}/BalanceSheetAction/postMoneyNum.do',
			dataType:"JSON",
			type:"POST",
			data:{"accounttype":${memberId}},
			success:function(data){
			//alert(data);
			    data = eval(data);
			    /*for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    	document.getElementById("getMoneyNum").options.length = 0;
			    for(var i=0;i<parseInt(data.length);i++){
			    	document.getElementById("getMoneyNum").options.add(new Option(data[i].accountnumber,i));
			    }
			}
		});
		      	      		 	$.ajax({
			url:'${ctx}/BalanceSheetAction/postMoneyNum.do',
			dataType:"JSON",
			type:"POST",
			data:{"accounttype":value},
			success:function(data){
			//alert(data);
			    data = eval(data);	
			    /*for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    	document.getElementById("postMoneyNum").options.length = 0;
			    for(var i=0;i<parseInt(data.length);i++){
			    	document.getElementById("postMoneyNum").options.add(new Option(data[i].accountnumber,i));
			    }
			}
		});
   		 	}else if(radioValue == 0){

   		 	        var obj = document.getElementById("postMoneyCompanySelect");
   		 	        
        //alert(obj);
      	var index = obj.selectedIndex;
      	//alert(index);
      	var text  =obj.options[index].text;
      	//alert(text);	
      	var value = obj.options[index].value;
      	//alert(value);postMoney
      	$("#postMoney").attr('value',text);
      	$("#postMoney").attr('text',value);
      	$("#getMoney").attr('text',$("#getCompanyName").attr("name"));
      	$("#getMoney").attr('value',$("#getCompanyName").val());
      	//alert($("#getCompanyName").attr("name"));
      	//$("#postMoney").attr('value',value);
      	      	      		 	$.ajax({
			url:'${ctx}/BalanceSheetAction/postMoneyNum.do',
			dataType:"JSON",
			type:"POST",
			data:{"accounttype":value},
			success:function(data){
			//alert(data);
			    data = eval(data);
			    /*for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    	document.getElementById("getMoneyNum").options.length = 0;
			    for(var i=0;i<parseInt(data.length);i++){
			    	document.getElementById("getMoneyNum").options.add(new Option(data[i].accounttype,i));
			    }
			}
		});
      	      		 	$.ajax({
			url:'${ctx}/BalanceSheetAction/postMoneyNum.do',
			dataType:"JSON",
			type:"POST",
			data:{"accounttype":${memberId}},
			success:function(data){
			//alert(data);
			    data = eval(data);	
			    /*for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("BalanceData").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			    	document.getElementById("postMoneyNum").options.length = 0;
			    for(var i=0;i<parseInt(data.length);i++){
			    	document.getElementById("postMoneyNum").options.add(new Option(data[i].accounttype,i));
			    }
			}
		});
   		 	}
    }
   }

      	


      	
      	
      	/*document.getElementById("postMoneyNum").options.length=0;
	
    	document.getElementById("postMoneyNum").options.add(new Option("黄浦区","1"));
	
    	document.getElementById("postMoneyNum").options.add(new Option("闸北区","2"));*/
      	
      }
   		function change(){
   		 	var radio =  document.getElementsByName("options");
   		 	   var radioLength = radio.length;
   for(var i =0;i < radioLength;i++)
   {
    if(radio[i].checked)
    {
     var radioValue = radio[i].value;
     //alert(radioValue);
        		 	if(radioValue == 1){
   		 	$("#postMoneyCompanyInput").prop("hidden",false);
   		 	$("#postMoneyCompany").prop("hidden",true);
   		 	$("#getMoneyCompanyInput").prop("hidden",true);
   		 	$("#getMoneyCompany").prop("hidden",false);
   		 	}else if(radioValue == 0){
   		 	$("#getMoneyCompanyInput").prop("hidden",false	);
   		 	$("#getMoneyCompany").prop("hidden",true);
   		 	$("#postMoneyCompanyInput").prop("hidden",true);
   		 	$("#postMoneyCompany").prop("hidden",false);
   		 	}
    }
   }

	  	 }
      
      </script>
<!--创建结款单-->
<div class="modal fade" id="addMenu" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog m-tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        创建结款单
                        </div>
                            <div class="row wrapper form-margin">
                                 <div class="col-md-12">
                                    <div class="btn-group" data-toggle="buttons">
                                    <h5><strong>付款方式:</strong></h5>
                                   <label class="btn btn-primary">
                                      <input type="radio" name="options" id="option1" value="0" onchange ="change();"> 本方付款
                                   </label>
                                   <label class="btn btn-primary">
                                      <input type="radio" name="options" id="option1" value="1"onchange ="change();"> 对方付款
                                   </label>
                                   </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5><strong>对方付款:</strong></h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <div id ="getMoneyCompanyInput" hidden ="hidden" value="${companyName}" text="${companyName}">
                                        		<h5>
                                        			${companyName}
                                        			<input type="text" hidden="hidden" value="${companyName}" name="${memberId}" id="postCompanyName">
                                        		</h5>
                                        
                                        </div>
                                        <div id="getMoneyCompany" hidden ="hidden">
                                      	<select name="selecter_basic" tabindex="-1"class="form-control" id="getMoneyCompanySelect" hidden ="hidden">
                                        <option value="1">顺平信德</option>
                                        <option value="2">中联联合</option>
                                        <#list hcSelect as compantName>
                                        <option value="${compantName.id}"id="${compantName.id}">${compantName.sellersName}</option>
                                        </#list>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5><strong>收款方:</strong></h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <div id ="postMoneyCompanyInput" hidden ="hidden" value="${companyName}" name="${companyName}">
                                        		<h5>
                                        			${companyName}
                                        			<input type="text" hidden="hidden" value="${companyName}" name="${memberId}" id="getCompanyName">
                                        		</h5>
                                        
                                        </div>
                                        <div id="postMoneyCompany" hidden ="hidden">
                                        <select name="selecter_basic" tabindex="-1"class="form-control"id="postMoneyCompanySelect" >
                                        <option value="1">顺平信德</option>
                                        <option value="2">中联联合</option>
                                        <#list hcSelect as compantName>
                                        <option value="${compantName.id}"id="${compantName.id}">${compantName.buyersName}</option>
                                        </#list>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                                <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" data-toggle="modal" 
                                data-target="#changeChar" onclick="confirmOrder()">确认，下一步选择结款的订单</a>
                             </div>
                            <footer class="panel-footer text-right bg-light lter">
                            </footer>
                        </div>
                     </div>        	
                </div>
            </div> 
        </div>
	</div>
</div>

      <script type="text/javascript">
		function confirmMoney(){
		var status = 0;
		         		 	var radio =  document.getElementsByName("options");
   		 	   var radioLength = radio.length;
   for(var i =0;i < radioLength;i++)
   {
    //alert("得到的选定的checkbox"+radio[i].checked);
    if(radio[i].checked)
    {
     var radioValue = radio[i].value;
    //alert("得到的选定的checkbox"+radioValue);
     if(radioValue == 1){
     status = "a2";
     }
     else if(radioValue == 0){
     status = "a3";
     }}
     }
    //alert(status);
    //alert($("#postMoney").attr("text"));
		var balanceCheckBox = $("input[name='balanceCheckBox']:checked").serialize();
		var confirmBalanceNum = $("input[name='confirmBalanceNum']").serialize();
		var PayBalanceNum = $("input[name='PayBalanceNum']").serialize();
		var check2 = $("input[name='balanceCheckBox']:checked").val();
		
		alert(balanceCheckBox+"#"+check2);
			   	 	$.ajax({
			url:'${ctx}/BalanceSheetAction/insertConfirmMoneyNum.do',
			dataType:"JSON",
			type:"POST",
			//data:{"settleMoney":1.00,"payerName":$("#postMoney").val(),"ctrMoney":1.00,balanceCheckBox},
			data:"settleMoney="+1.00+"&payerName="+$("#postMoney").val()+"&ctrMoney="+1.00+"&"+balanceCheckBox+"&status="+status+"&balanceTime="+$("#ctrTime").val()+"&"+confirmBalanceNum
			+"&beneficiaryCode="+$("#postMoneyNum").find("option:selected").text()+"&beneficiaryName="+$("#getMoney").val()+"&payerCode="+$("#getMoneyNum").find("option:selected").text()+"&payer_id="
			+$("#postMoney").attr("text")+"&beneficiary_id="+$("#getMoney").attr("text")+"&"+PayBalanceNum,
			success:function(data){
			alert(data);
			    data = eval(data);
			    
			  /*  for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(i);
			    	row1.insertCell(0).innerHTML='<td id=""><input type="checkbox" id="order"/></td>';
			    	//row1.insertCell(0).innerHTML='<td id="">'+data[i].id+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	//row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].id+'</td>';
			    	//row1.insertCell(3).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(4).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }*/
			   window.location ="${ctx}/BalanceSheetAction/toBanlance.do";
			}
		});
		}
		function selectOrder(){
			
        var id = document.getElementById("id").value;
       //alert(id);
        id = "1";
	  	var len = parseInt(Assure_select.rows.length);
	    for(var j=0;j<len;len--){
			document.getElementById("Assure_select").deleteRow(j);

		}
		var buyersId ="";
		var sellersId = "";
		alert($("#postMoneyCompanyInput").prop("hidden"));
		alert($("#getMoneyCompanyInput").prop("hidden"));
	        if($("#postMoneyCompanyInput").prop("hidden")){
	         buyersId = $("#getMoneyCompanySelect").val();
	         sellersId = $("#getCompanyName").attr("name");
	        }else if($("#getMoneyCompanyInput").prop("hidden")){
	         buyersId = $("#getCompanyName").attr("name");
	         sellersId = $("#postMoneyCompanySelect").val();
	        }
	 	$.ajax({
			url:'${ctx}/BalanceSheetAction/getOrders.do',
			dataType:"JSON",
			type:"POST",
			data:{"buyersId":buyersId,"sellersId":sellersId},
			success:function(data){
			alert(data);
			    data = eval(data);
			    
			    for(var i=0;i<parseInt(data.length);i++){
			    	var row1 =document.getElementById("Assure_select").insertRow(j);
			    	row1.insertCell(0).innerHTML='<td id=""><input type="checkbox" id="order"/></td>';
			    	//row1.insertCell(0).innerHTML='<td id="">'+data[i].goodsName+'</td>';
			    	row1.insertCell(1).innerHTML='<td id="id">'+data[i].id+'</td>';
			    	//row1.insertCell(1).innerHTML='<td id="id">'+getSmpFormatDate(new Date(data[i].refundDate), true)+'</td>';
			    	row1.insertCell(2).innerHTML='<td id="id">￥'+data[i].goodsName+'</td>';
			    	row1.insertCell(3).innerHTML='<td id="id">'+data[i].paymoneyNum+'</td>';
			    	row1.insertCell(4).innerHTML='<td id="id">'+data[i].exePaymoneyNum+'</td>';
			    	//row1.insertCell(5).innerHTML='<td id="id">'+data[i].id+'</td>';
			    }

			}
		});
		}
			function checkAllBox(obj){
		$("#balanceTable").children("tbody").find("tr:not(first)").each(function(i,thisElement){
		alert($(thisElement).find("[id=checkbox]").prop("checked"));
		if($(thisElement).find("[id=checkbox]").prop("checked")){
		      $(thisElement).find("[id=checkbox]").prop("checked",'false');	
			}else{
				$(thisElement).find("[id=checkbox]").prop("checked",'true');
			}
		})
	}	
      </script>


   <!--弹出层-->  
<div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog tanchu-box" role="document">
		<div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                    	<div class="panel-heading box-shodm">
                        创建结款单
                        </div>
                            <div class="row wrapper form-margin">
                                <div class="col-md-6">
                                    <div class="btn-group" data-toggle="buttons">
                                     <h5>付款方: <input type="text" name="payer_name" id="postMoney" readonly = "readonly"/></h5>
                                    
                               
                                   </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5>付款账号：</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <select name="payer_code" tabindex="-1"class="form-control" id="postMoneyNum">
                                        <option value="1">付款账号</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="btn-group" data-toggle="buttons">
                                    <h5>收款方:<input type="text" name="beneficiary_name" id="getMoney" readonly="readonly"/></h5>
                                   </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5>收款账号：</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <select name="beneficiary_code" tabindex="-1"class="form-control" id="getMoneyNum">
                                        <option value="1">收款账号</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12">
        							<div class="input-group">
       							 		<div class="input-group-btn">
       							 		<h5>结款时间:</h5>
       							 		</div>
                                         <input type="text" placeholder="" class=" form-control" onfocus="javascript:WdatePicker({readOnly:true,isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:now()})"  style="float:left;"name="ctrTime" id="ctrTime">
                                    </div>
                                </div>
                                <a class="btn btn-info btn-s-md btn-default  ah2_btn_margin " data-toggle="modal" 
                                data-target="#addOrder" onclick="selectOrder()">添加订单行</a>
                                <a class="btn btn-info btn-s-md btn-default  ah2_btn_margin" onclick="deleteOrder()">删除订单行</a>
                             </div>
                             <div class="table-responsive panel-table-body ">
                                <table class="table table-striped table-hover " id="balanceModel">
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="balanceTitle" onclick="checkAllBox(this)"></th>
                                            <th>订单号</th>
                                            <th>商品名</th>
                                            <th>订单金额</th>
                                            <th>待结金额</th>
                                            <th>结款实付金额</th>
                                        </tr>
                                    </thead>
                                    
                                </table>
                                <table class="table table-striped table-hover " id="balanceTable">
                                    <tbody>
                                       <#--> <tr>
                                            <td><input type="checkbox" name="balanceCheckBox" value="10003" id="checkbox"></td>
                                            <td>10003</td>
                                            <td>玉米</td>
                                            <td>515</td>
                                            <td>50</td>
                                            <td><input type="text"class="input-checkbox" id="confirmBalanceNum"></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox"id="checkbox" name="balanceCheckBox" value="10004"></td>
                                            <td>10004</td>
                                            <td>白菜</td>
                                            <td>600</td>
                                            <td>100</td>
                                            <td><input type="text"class="input-size" ></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox"id="checkbox" name="balanceCheckBox" value="10005"></td>
                                            <td>10005</td>
                                            <td>萝卜</td>
                                            <td>1000</td>
                                            <td>250</td>
                                            <td><input type="text"class="input-size"></td>
                                        </tr>-->
                                    </tbody>
                                </table>
                            </div> 
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn  btn-warning" type="submit" onClick="confirmMoney()">确认结款</button>
                            </footer>
                        </div>
                     </div>        	
                </div>
                </form>
            </div> 
        </div> 
	</div>
</div>
      <script type="text/javascript">
  /* $(function(){
	
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
		     var change = $("#selecter_basic").val();
		 	 var payerName = $("#payerName").val();
		 	 var payeeName = $("#payeeName").val();
		 	 
		 	 //获取当前页
		 	 pageNo = getPageNo(pageNo,index,txt);
			
			 window.location = "${ctx}/assureController/toAssureNoFilter.do?page="+pageNo+"&type="+5+"&payerName="+payerName+"&payeeName="+payeeName;
		})	
	})
	 function getMassage(){
 	var payerName = $("#payerName").val();
 	var payeeName = $("#payeeName").val();
 	
 	var type = $("#selecter_basic").val();
 	
 	window.location = "${ctx}/assureController/toAssureNoFilter.do?payerName="+payerName+"&payeeName="+payeeName+"&type="+5;
 }*/
 	function getMassage(){

 	var findNum= document.getElementById("orderNum").value;
 	alert(findNum);
 	alert('查询');
 	$('#Assure_select').children("tbody").find("tr:not(first)").each(function(i,element){
			//alert("check的值" + $(element).find("[id=order]").prop("checked"));
			//alert("check的值" + $(element).find("td:eq(0)").prop("checked"));
			//alert("check的值" + $(element).find("td:eq(1)").html());
			//alert($(element).find("[id=order]").prop("checked"));
			alert("进入新方式");
			//alert("check的值" + $(element>input:checkbox).checked);
			//if($(element).find("checkbox").val()!=null || $(element).find("checkbox").val()!=undefined ){
				//if($(element).find("[id=order]").val() == ""){
				//alert("空");
					//$("#changeChar").modal('hide');
				//}else if($(element).find("[id=order]").prop("checked")){
					
					//var guaranteetId= $(element).find("td:eq(0)").html();
					var guaranteeAmount= $(element).find("td:eq(1)").html();
					alert(guaranteeAmount);
					//var nonPayAmount= $(element).find("td:eq(3)").html();
					//var guaranteeName= $(element).find("td:eq(5)").html();
					//var bcsy_money = $(element).find("[id=inp_txt]").val();
					alert(find1(findNum,guaranteeAmount) == true);
					$(element).prop("hidden",true);
					alert("隐藏");
					if(find1(findNum,guaranteeAmount) == true){
					$(element).prop("hidden",false);
					//var row2 = document.getElementById("Assure_selectBack").insertRow(Assure_selectBack.rows.length);
					//row2.insertCell(0).innerHTML='<td id=""><input type="checkbox" name ="balanceCheckBox" id="checkbox"/></td>';
					//row2.insertCell(1).innerHTML='<td id="guaranteetId">'+guaranteeAmount+'</td>';
					}
			    	//row2.insertCell(1).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	//row2.insertCell(2).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	//row2.insertCell(3).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	//row2.insertCell(4).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	//row2.insertCell(5).innerHTML='<td id="guaranteeAmount">'+guaranteeAmount+'</td>';
			    	//row2.insertCell(3).innerHTML='<td id="nonPayAmount">'+nonPayAmount+'</td>';
			    	//row2.insertCell(4).innerHTML='<td id="guaranteeName">'+guaranteeName+'</td>';
			    	//row2.insertCell(5).innerHTML='<td id="bcsy_money">￥'+bcsy_money+'</td>';
			    	//row2.insertCell(2).innerHTML='<td id=""><input id="checkbox" hidden = "hidden" type="text" value="" style="width:70px; height:20px;"></td>';
				//}
			//}
		})
		 	/*var len = parseInt(Assure_select.rows.length);
	    for(var j=0;j<len;len--){
			document.getElementById("Assure_select").deleteRow(j);
		}*/
 	}		
 	
 	
 	function find1(sFind, sObj)  
{  
    var nSize = sFind.length;  
    var nLen = sObj.length;   
    var sCompare;  
   
    if(nSize <= nLen ){  
        for(var i = 0; i <= nLen - nSize + 1; i++){  
            sCompare = sObj.substring(i, i + nSize);  
            if(sCompare == sFind){  
                return true;  
            }  
        }  
    }  
    return -1;  
} 
	</script>
 <!--弹出层--> 
<div class="modal fade" id="addOrder" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        添加订单
                        </div>


                            <div class="row wrapper form-margin">
                                 <div class="col-md-4">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">订单号:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="text" placeholder="" class="form-control" name="input1-group3" id="orderNum">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">商品名:</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <input type="text" placeholder="" class="form-control" name="input1-group3" id="goodsName">
                                    </div>
                                </div>
                                <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" onclick = "getMassage()"href="#">查询</a>
                             </div>
                            <div class="table-responsive panel-table-body ">
                                <table class="table table-striped table-hover " id="Assure_select1">
                                                                    <thead>
                                        <tr>
                                            <th><input type="checkbox"></th>
                                            <th>订单号</th>
                                            <th>商品名</th>
                                            <th>已付金额</th>
                                            <th>代付金额</th>
                                        </tr>
                                    </thead>
                                </table>
                                <table class="table table-striped table-hover " id="Assure_selectBack">

                                </table>
                                <table class="table table-striped table-hover " id="Assure_select">

                                    <tbody>
                                        <#--<tr>
                                            <td><input type="checkbox" id="order"></td>
                                            <td>10003</td>
                                            <td>萝卜</td>
                                            <td>500</td>
                                            <td>300</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox"id="order"></td>
                                            <td>10005</td>
                                            <td>白菜</td>
                                            <td>600</td>
                                            <td>200</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox"id="order"></td>
                                            <td>10007</td>
                                            <td>土豆</td>
                                            <td>800</td>
                                            <td>100</td>
                                        </tr>-->
                                    </tbody>
                                </table>
                            </div> 
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn btn-warning btn-s-xs" type="submit" onClick="addOrderNum()">确认</button>
                            </footer>	
                        </div>
                     </div>
                </div>
            </div> 
        </div>
	</div> 
</div>
</body>
</html>
