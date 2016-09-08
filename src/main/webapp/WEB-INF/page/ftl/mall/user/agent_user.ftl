<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   <meta charset="UTF-8">
	   <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
	   <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
	   <script src="${ctx}/mall/js/jquery.js"></script>
	   <script src="${ctx}/mall/js/jquery.min.js"></script>
	   <script src="${ctx}/mall/js/bootstrap.min.js"></script>
	   <script type="text/javascript">

			function jinru(accountName){
				alert("开始跳转到"+accountName);
//                window.opener=null;
//                window.open('','_self');
//                window.close();
				window.open("${ctx}/switch.do?username="+accountName);
			}
	   </script>
	   <title>客服</title>
	</head>

	<body>
		<div class="container-fluid" style="margin-top:15px;">
		    <div class="row-fluid">
		        <!-- col-sm-12 -->
		        <div class="col-sm-12 ">
		        	<div class="panel panel-default article-bj">
		                <div class="panel-heading box-shodm">
		                	客服
		                </div>
		                     <div class="table-responsive panel-table-body ">
		                        <table class="table table-striped table-hover ">
		                            <thead>
		                                <tr>
		                                    <th >已代理的操作员</th>
		                                    <th >所属会员</th>
		                                    <th >角色</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            	<#if muRealList ??>
											<#list muRealList as muReal>
												<tr>
				                                    <td><a href="javascript:void(0);" onclick="jinru('${muReal.accountName!}')">${muReal.name}</a></td>
				                                    <td>${muReal.mmbName}</td>
				                                    <td>${muReal.roleName}</td> 
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
