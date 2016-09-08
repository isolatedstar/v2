<#--<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />  引入security.tld标签-->
<#assign ctx = request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
   <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
   <script src="${ctx}/mall/js/jquery.js"></script>
   <script src="${ctx}/mall/js/jquery.min.js"></script>
   <script src="${ctx}/mall/js/bootstrap.min.js"></script>
           <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
          <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/DatePicker/WdatePicker.js"></script>
     <script type="text/javascript">
     
  /*  function getBalanceDate(){
		var len = parseInt(BalanceData.rows.length);
		for(var j=1;j<len;len--){
			document.getElementById("BalanceData").deleteRow(j);

		}
		var id = "1";
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
			}
		});   
   }*/
   </script>
<title>结款单确权与融资</title>
</head>

<body>
<div class="container-fluid" style=" margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                结款单确权与融资
                </div>
        			<div class="row wrapper form-margin">
       					 <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">结款单号:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <input type="text" placeholder="" class="form-control" name="input1-group3" id="id">
        					</div>
        				</div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">结款方:</h5>
                                <!--   <button class="btn" type="button"></button> -->
                                </div>
                                <input type="text" placeholder="" class="form-control" name="input1-group3" id="payerName">
                            </div>
                        </div>
                        <#--<div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">签章状态:</h5>
                                <!--   <button class="btn" type="button"></button> 
                                </div>
                                <select name="selecter_basic" id="selecter_basic" tabindex="-1"class="form-control">
                                <option value="1">全部</option>
                                <option value="2">双方未签</option>
                                <option value="3">本方已签</option>
                                <option value="4">对方已签</option>
                                <option value="5">双方已签</option>
                                </select>
                        	</div>
                        </div>-->
                        <br/>
                         <div class="col-md-4">
        					<div class="input-group">
       							 <div class="input-group-btn">
       							 	<h5 class="h5-margin">开始时间:</h5>
       							 <!--   <button class="btn" type="button"></button> -->
       							 </div>
       							 <input type="text" placeholder="" class="form-control"  name="selectTime" id="selectTime">
        					</div>
        				</div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-btn">
                                	<h5 class="h5-margin">担保登记状态:</h5>
                                <!--   <button class="btn" type="button"></button> -->
                                </div>
                                <select name="selecter_basic" id="selecter_basic" tabindex="-1"class="form-control">
                                <option value="d1">未申请登记</option>
                                <option value="d2">已申请登记</option>
                                <option value="d3">已完成登记</option>
                                </select>
                        	</div>
                        </div>
       					<a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" onclick="getBalanceDate()" href="#">查询</a>
       				 </div>
       				                                                 <script type="text/javascript">
       				                                                          	$(function(){
	  	                   $('#selectTime').daterangepicker(
                  //{
                    //timePicker: true,
                //  }, 
                  //function(start, end, label) {
                    //console.log(start.toISOString(), end.toISOString(), label);
                  //}
                  );
   });
        function getBalanceDate(){
        // var id = document.getElementById("id").value;
       // alert(id);
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
 	window.location = "${ctx}/BalanceSheetAction/querySTLListByBalanceThree.do?id="+id+"&payerName="+payerName+"&signatureStatus="+status+"&selectTime="+selectTime;
  }
                                      </script>
                    <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover " id="BalanceData">
                            <thead>
                                <tr>
                                    <th>结款单号</th>
                                    <th>结款方</th>
                                    <th>付款账号</th>
                                    <th>预计结款时间</th>
                                    <th>约定金额</th>
                                    <th>已付金额</th>
                                    <th>电子签章</th>
                                    <th>担保资源登记</th>
                                </tr>
                            </thead>
                            <tbody>
                               <#-- <tr>
                                    <td>10001</td>
                                    <td>顺平信德</td>
                                    <td>6333 1309 465</td>
                                    <td>2015-12-31</td>
                                    <td>230</td>
                                    <td>200</td>
                                    <td><a href="#">签章</a></td>
                                    <td></td>
                                </tr>
                                <tr>
                                	<td>10003</td>
                                    <td>天津绿鲜源</td>
                                    <td>6333 1309 231</td>
                                    <td>2016-02-30</td>
                                    <td>150</td>
                                    <td>150</td>
                                    <td><a href="#">等待对方确认签章</a></td>
                                    <td></td>
                                </tr>
                                <tr>
                                	<td>10005</td>
                                    <td>福建壹戈</td>
                                    <td>9554 7098 155</td>
                                    <td>2016-01-16</td>
                                    <td>95</td>
                                    <td>90</td>
                                    <td><a href="#">签章完成</a></td>
                                    <td><a href="#">已待审批</a></td>
                                </tr>
                                <tr>
                                	<td>10006</td>
                                    <td>洪茂源</td>
                                    <td>7154 7098 289</td>
                                    <td>2016-04-09</td>
                                    <td>1120</td>
                                    <td>0</td>
                                    <td><a href="#">签章完成</a></td>
                                    <td><a href="#">已登记</a></td>
                                </tr>-->
                                                                  <script type="text/javascript">
                                     	function passBalance(passId,signatureStatus){
                                     	alert(signatureStatus);
  		//alert(passId);
  			 	$.ajax({
			url:'${ctx}/BalanceSheetAction/editSTL.do',
			dataType:"JSON",
			type:"POST",
			data:{"id":passId,"signatureStatus":signatureStatus},
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
			    window.location ="${ctx}/BalanceSheetAction/toBanlanceThree.do";
			}
		});
  	}
                                   </script>
                                <#if hc ??>
                                <#list hc as company>
								<#if company.status != "b4" && company.signatureStatus== "c4"&& company.beneficiaryName == companyName>                                <tr>
                                <td>${company.id}</td>
                                <td>${company.payerName}</td> 
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.settleMoney}</td>
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size"></td>
                                <td><a href="#" onclick="passBalance(${company.id},'d2')">申请登记</a></td>
								<#--><#elseif company.status == "d1" && company.beneficiaryName == companyName>
                                <td><a href="#" onclick="passBalance(${company.id},d3)">登记</a></td><-->
								<#elseif company.status != "b4" && company.signatureStatus == "d4">                                <tr>
                                <td>${company.id}</td>
                                <td>${company.payerName}</td> 
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.settleMoney}</td>
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size"></td>
                                <td><a href="#" >登记失败，请返回重新签章</a></td>
								<#--><#elseif company.status == "d1" && company.beneficiaryName == companyName>
                                <td><a href="#" onclick="passBalance(${company.id},d3)">登记</a></td><-->
								<#elseif company.status != "b4" && company.signatureStatus== "d3">                                <tr>
                                <td>${company.id}</td>
                                <td>${company.payerName}</td> 
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.settleMoney}</td>
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size"></td>
                                <td><a href="#" >登记成功！</a></td>
								<#--><#elseif company.status == "d1" && company.beneficiaryName == companyName>
                                <td><a href="#" onclick="passBalance(${company.id},d3)">登记</a></td><-->
                                <#elseif company.status != "b4" && company.signatureStatus == "d2" && company.payerName == companyName>                                <tr>
                                <td>${company.id}</td>
                                <td>${company.payerName}</td> 
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.settleMoney}</td>
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size"></td>
                                <td><a href="#" >完成申请</a></td>
                                <#elseif company.status != "b4" && company.signatureStatus == "d2" && company.beneficiaryName == companyName>                                <tr>
                                <td>${company.id}</td>
                                <td>${company.payerName}</td> 
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.settleMoney}</td>
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size"></td>
                                <td><a href="#" >完成申请</a></td>
                                <#--<#elseif company.status == "d3" && company.beneficiaryName == companyName>
                                <td><a href="#" onclick="passBalance(${company.id},d4)">完成申请</a></td>
                                <#elseif company.status == "d4" && company.signatureStatus == 0>                                <tr>
                                <td>${company.id}</td>
                                <td>${company.payerName}</td> 
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.ctrTime?datetime?string}</td>
                                <td>${company.settleMoney}</td>
                                <td>${company.ctrMoney}</td>
                                <td><input type="text"class="input-size"></td>
                                <td><a href="#" >已登记</a> </td>-->
                                <#else>
								</#if>
                                </tr>
                                </#list>
                                </#if>
                            </tbody>
                        </table>
                    </div> 
                    <footer class="panel-footer text-right bg-light lter">
                    </footer>
        		</div>
       		 </div>
        </div>
    </div> 
</div>
</body>
</html>
