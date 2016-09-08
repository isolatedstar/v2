<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/jsp/common/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>群组准入</title>
</head>

<body>
	<div class="container-fluid" style="margin-top:15px;">
	    <div class="row-fluid">
	        <!-- col-sm-12 -->
	        <div class="col-sm-12 ">
	        	<div class="panel panel-default article-bj">
	                <div class="panel-heading box-shodm">
	              	  申请表
	                </div>
	        			<div class="row wrapper form-margin">
		       				<div class="col-md-6">
		        				<div class="input-group">
		        					
		       						<div class="input-group-btn">
		       							<h5 class="h5-margin">群名称:</h5>
		       						</div>
		       						<select name="groupId" id="groupId" tabindex="-1"class="form-control" onchange="queryInfoById()">
			                            <option value="0">请选择</option>
			                            <c:forEach items="${groups}" var="group">
			                                <option value="${group.groupId}"  gName="${group.groupName}">${group.groupName}</option>
			                            </c:forEach>
			                        <select>
		        				</div>
		        			</div>
		        			<input type="hidden" name="status" id="status" value="1"/>
	       				</div>
	                    <div class="table-responsive panel-table-body ">
	                        <table class="table table-striped table-hover " id="tb_groupApply">
	                        </table> 
	                    </div> 
	                    <div class="panel-footer text-right">
	                    	<input type="button" class="btn btn-info btn-s-xs" onclick="check(1)" value="同意" />
	                    	<input type="button" class="btn btn-info btn-s-xs" onclick="check(2)" value="拒绝" />
	                    </div>
	        		</div>
	       		 </div>
	        </div>
	    </div> 
	</div>
</body>
	<script type="text/javascript">
		$(function(){
			$(document).ready(function(){
				$("#groupId option[value='${gId}']").attr("selected",true);
			});
		}); 

    	$(function(){
    		//复选框全选
    		$("#checkAll").click(function() {
    			$('[name=realId]:checkbox').prop("checked",this.checked);
    		});
    	});
    	
    	
    	$(function() {
			//初始化table
			var oTable = new TableInit();
			oTable.Init();
		});

		var TableInit = function() {
			var oTableInit = new Object();
			oTableInit.Init = function() {
				$("#tb_groupApply").bootstrapTable({
					
					method : "post",
					dataType : "json",
					classes : "table table-no-bordered",
					contentType : "application/x-www-form-urlencoded",
					striped : true,
					cache : false,
					pagination : true,
					sortable : false,
					sortOrder : "asc",
					queryParams : oTableInit.queryParams,
					pageNumber : 1,
					pageSize : 2,
					pageList : [ 10 ],
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
								field : "checkAll",
								checkbox : true,
								align : "center",
								valign : "middle",
								sortable : false
							},
							{
								field : "mmbFname",
								title : "会员名称",
								align : "center",
								valign : "middle",
								sortable : false
							},]
					});
			};
			
			//得到查询的参数
			oTableInit.queryParams = function(params) {
				var temp = {
					pageNo : params.offset,
					pageSize : params.limit,
					groupName : $("#groupId option:selected").attr("gName")
				};
				return temp;
			};
			return oTableInit;
		};
    	
    	
    	//筛选某一组递交申请的会员
    	function queryInfoById(){
    		//用于判断是否为第一个
    		var flag=$("#groupId").val();
    		if(flag!="0"){
    			var status=$("#status").val();
	    		//var groupName=$("#groupId option:selected").attr("gName");
	    		//var ge=$("#groupId").find("option:selected").attr("gName");
	    		//var pageSize = $("#tb_groupApply").bootstrapTable('getOptions').pageSize === $("#tb_groupApply").bootstrapTable('getOptions').formatAllRows() ? 
				//$("#tb_groupApply").bootstrapTable('getOptions').totalRows : $("#tb_groupApply").bootstrapTable('getOptions').pageSize;
	    		
	    		//alert(flag);
	    		$.ajax({
					url : '${pageContext.request.contextPath}/group/queryGroupListOfIn.do',
					//参数必须一一对应
					data : {
						flag:flag,
						status:1
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						//alert(data.rows.length);
						$("#tb_groupApply").bootstrapTable('load', data.rows);
					},
					error : function() {
						alert("异常！");
					}
				});
    		}else{
    			alert("请选择群名称！");
    		}
    	}
    	
    	//批量审核会员入群
    	function check(status){
    		//开始先设置深度序列化的值
    		$.ajaxSettings.traditional = true;
    		//获取当前选中的群组Id
    		var groupId=$("#groupId").val();
    		//获取选中的复选框信息
    		var check = $("#tb_groupApply").bootstrapTable('getSelections');
    		
    		if(check==""||check.length<1){
    			 alert('请选择审批的会员！');
 		        return;
    		}
    		var realIds="";
    		for(var i =0;i<check.length;i++){
    			realIds  += check[i].id+",";
    		}	
		    realIds=realIds.substring(0,realIds.length-1);
			$.ajax({
				url:'${pageContext.request.contextPath }/group/checkuserforGroup.do',
				data:{
					"realIds":realIds,
					"status":status,
					"groupId":groupId
				},
				dataType:'json',
				type:"post",
				success:function(data){
					if(data=="0"){
						alert("操作成功");
						queryInfoById();
					}else{
						alert("操作失败!");
					}
				}
			});
    	}
    	
    </script>
</html>

