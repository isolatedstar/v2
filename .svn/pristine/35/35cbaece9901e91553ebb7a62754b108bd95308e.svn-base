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

<title>地址管理</title>
</head>

<body>
<div class="container-fluid" style=" margin-top:15px;">
    <div class="row-fluid">
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                地址管理
                </div>
                    <div class="table-responsive panel-table-body ">
                        <table class="table table-striped table-hover ">
                            <thead>
                                <tr>
                                    <th>详细</th>
                                    <th>联系人</th>
                                    <th>电话</th>
                                    <th>邮编</th>
                                    <th>删除</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--<tr>
                                	<td><input type="checkbox"></td>
                                    <td>北京市海淀区上园25号</td>
                                    <td>收货</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"></td>
                                    <td>北京市朝阳区大望路第三仓库</td>
                                    <td>收发货</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"></td>
                                    <td>北京市海淀区清华东路5号</td>
                                    <td>发货</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>-->
                                <#if hc ??>
                                      <#list hc as compantName>
                                        <tr>
                                        	<td>${compantName.addresstype}</td>
                                        	<td>${compantName.contactor}</td>
                                        	<td>${compantName.phone}</td>
                                        	<td>${compantName.postcode}</td>
                                        	<td><a href="" onclick = "deleteAddress(${compantName.id})">删除</a></td>
                                        </tr>
                                      </#list>
                                     </#if>
                            </tbody>
                        </table>
                    </div> 
                    <footer class="panel-footer text-right bg-light lter">
                    <button class="btn btn-info btn-s-xs" type="submit" data-toggle="modal" data-target="#addAddress">添加</button>
                    <button class="btn btn-warning btn-s-xs" hidden="hidden" style="display:none;" type="submit">删除</button>
                    </footer>
        		</div>
       		 </div>
        </div>
    </div> 
</div>
<!--添加收货地址-->
<!--弹出层--> 
<div class="modal fade" id="addAddress" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog s-tanchu-box" role="document"> 
        <div class="container-fluid" style="margin-left:212px; margin-top:15px;">
            <div class="row-fluid">
                <!-- col-sm-12 -->
                <div class="col-sm-12 ">
                    <div class="panel panel-default article-bj">
                        <div class="panel-heading box-shodm">
                        添加收货地址
                        </div>
                           <script type="text/javascript">
   function addAddress(){
   		$.ajax({
		url:'${ctx}/BalanceSheetAction/insertAddress.do',
		dataType:'JSON',
		type:'POST',	
		data:{"addresstype":$("#addressName").val(),"contactor":$("#phoneName").val(),"phone":$("#phone").val(),"postcode":$("#postCode").val()},
		success:function(data){
			data = eval("("+data+")")
			alert(data);
			window.location = "${ctx}/BalanceSheetAction/toAddressManage.do";
		}
	})
   }
      function deleteAddress(addressId){
   	$.ajax({
   		url:'${ctx}/BalanceSheetAction/deleteAddress.do',
   		dateType:'JSON',
   		type:'POST',
   		data:{"id":addressId},
   		success:function(data){
   			data = eval("("+data+")")
   			alert(data);
   			window.location = "${ctx}/BalanceSheetAction/toAddressManage.do";
   		}	
   	
   	})
   }
   </script>
                            <div class="row wrapper form-margin">
                                 <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">详细地址:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="textarea" placeholder="" class="form-control" name="input1-group3" id="addressName">
                                    </div>
                                </div>
                                <#--<div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">所属地域:</h5>
                                        <!--   <button class="btn" type="button"></button>
                                        </div>
                                        <select name="selecter_basic" tabindex="-1"class="form-control">
                                            <option value="1">北京</option>
                                            <option value="2">河北</option>
                                            <option value="3">山东</option>
                                         </select>
                                    </div>
                                </div>-->
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">联系人:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="textarea" placeholder="" class="form-control" name="input1-group3" id="phoneName">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">手机:</h5>
                                        <!--   <button class="btn" type="button"></button> -->
                                        </div>
                                        <input type="text" placeholder="" class="form-control" name="input1-group3" id="phone">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="input-group">
                                         <div class="input-group-btn">
                                            <h5 class="h5-margin">邮编:</h5>
                                         <!--   <button class="btn" type="button"></button> -->
                                         </div>
                                         <input type="textarea" placeholder="" class="form-control" name="input1-group3" id="postCode">
                                    </div>
                                </div>
                               <#--> <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <h5 class="h5-margin">地址类型:</h5>
                                           <button class="btn" type="button"></button>
                                        </div>
                                        <select name="selecter_basic" tabindex="-1"class="form-control">
                                            <option value="1">收发货地址</option>
                                            <option value="2">收货地址</option>
                                            <option value="3">发货地址</option>
                                         </select>
                                    </div>
                                </div>-->
                            </div> 
                            <footer class="panel-footer text-right bg-light lter">
                            <button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal" onclick="addAddress()">确定</button>
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
