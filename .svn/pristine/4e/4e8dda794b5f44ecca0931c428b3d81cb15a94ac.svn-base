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

<title>账号管理</title>
</head>

<body>
<div class="container-fluid" style=" margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                账号管理
                </div>
                    <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover " id ="bankNum" >
                            <thead>
                                <tr>
                                    <th>开户行</th>
                                    <th>户名</th>
                                    <th>账号</th>
                                    <th>联系方式</th>
                                    <th>删除</th>
                                </tr>
                            </thead>
                            <tbody>
                               <#-- <tr>
                                	<td><input type="checkbox"></td>
                                    <td>中信银行</td>
                                    <td>顺平信德商贸</td>
                                    <td>62000126239</td>
                                    <td>收款</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"></td>
                                    <td>交通银行</td>
                                    <td>张三</td>
                                    <td>82081623901</td>
                                    <td>收、付款</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"></td>
                                    <td>交通银行</td>
                                    <td>顺平信德北京分部</td>
                                    <td>82081623922</td>
                                    <td>付款</td>
                                </tr>-->
                                <#if hc ??>
                                       <#list hc as compantName>
                                        <tr>
                                        	<td>${compantName.openmanager}</td>
                                        	<td>${compantName.name}</td>
                                        	<td>${compantName.accountnumber}</td>
                                        	<td>${compantName.phonenumber}</td>
                                        	<td><a href="" onclick="deleteBank(${compantName.id})">删除</a></td>
                                        </tr>
                                        </#list>
                                        </#if>
                            </tbody>
                        </table>
                    </div> 
                    <footer class="panel-footer text-right bg-light lter">
                    <button class="btn btn-info btn-s-xs" type="submit" data-toggle="modal" data-target="#addCode">添加</button>
                    <button class="btn btn-warning btn-s-xs" hidden = "hidden"style= "display:none;"type="submit">删除</button>
                    </footer>
        		</div>
       		 </div>
        </div>
    </div> 
</div>
   <script type="text/javascript">
   function addBankNum(){
   		var obj = document.getElementById("openmanager");
   		var index = obj.selectedIndex;
		//var row2 = document.getElementById("bankNum").insertRow(bankNum.rows.length);
		//row2.insertCell(0).innerHTML='<td id=""><input type="checkbox" id="checkbox"/></td>';
		//row2.insertCell(1).innerHTML='<td id="bankName">'+bankName+'</td>';
		
	$.ajax({
		url:'${ctx}/BalanceSheetAction/insertBank.do',
		dataType:'JSON',
		type:'POST',
		data:{"accountnumber":$("#accountnumber").val(),"phonenumber":$("#phonenumber").val(),"openmanager":obj.options[index].text},
		success:function(data){
			data = eval("("+data+")")
			alert(data);
			window.location = "${ctx}/BalanceSheetAction/toBankManage.do";
		}
	})
   }
   
   function deleteBank(bankId){
   	$.ajax({
   		url:'${ctx}/BalanceSheetAction/deleteBank.do',
   		dateType:'JSON',
   		type:'POST',
   		data:{"id":bankId},
   		success:function(data){
   			data = eval("("+data+")")
   			alert(data);
   			window.location = "${ctx}/BalanceSheetAction/toBankManage.do";
   		}	
   	
   	})
   }
      </script>
<!--添加账号-->
<!--弹出层--> 
<div class="modal fade" id="addCode" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog s-tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        添加账号
                        </div>
                            <div class="row wrapper form-margin">
                                 <#--<div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin-left">户名:</h5>
                                         <!--   <button class="btn" type="button"></button> 
                                         </div>
                                         <input type="textarea" placeholder="" class="form-control" name="input1-group3" id="bankName">
                                    </div>
                                </div>-->
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin-left">账号:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="textarea" placeholder="" class="form-control" name="input1-group3" id="accountnumber">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin-left">手机:</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <input type="text" placeholder="" class="form-control" name="input1-group3" id="phonenumber">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">开户银行:</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <select name="openmanager" id="openmanager" tabindex="-1"class="form-control">
                                            <option value="1">交通银行</option>
                                            <option value="2">中信银行</option>
                                            <option value="3">建设银行</option>
                                         </select>
                                    </div>
                                </div>
                                <#--<div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">账号类型:</h5>
                                        <!--   <button class="btn" type="button"></button> 
                                        </div>
                                        <select name="selecter_basic" tabindex="-1"class="form-control">
                                            <option value="1">收付款</option>
                                            <option value="2">收款</option>
                                            <option value="3">付款</option>
                                         </select>
                                    </div>
                                </div>-->
                            </div> 
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal" onclick="addBankNum()">确定</button>
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
