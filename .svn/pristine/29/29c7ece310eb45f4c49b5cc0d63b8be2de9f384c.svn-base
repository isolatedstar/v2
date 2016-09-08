<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>待审批申请</title>
   <link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
   <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
   <script src="${ctx}/mall/js/jquery.js"></script>
   <script src="${ctx}/mall/js/common/common.js"></script>
   <script src="${ctx}/mall/js/jquery.min.js"></script>
   <script src="${ctx}/mall/js/bootstrap.min.js"></script>
   <script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
   <script type="text/javascript" src="${ctx}/mall/js/extendPagination.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		var mmbId = $("#mmbId").val();
		if(mmbId!=""){
			$.ajax({
				url : '${ctx}/mmbRela/queryMmbRelationships.do',// 跳转到 action
				data : {
					mmbId : mmbId,
					pageNo : 1,
					pageSize : 10
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
						//先清空table中的数据
						$("#relaMmbPendingTable  tr:not(:first)").remove();
						if (data!=""&&data.relaMmbs.length > 0) {
							for ( var i = 0; i < data.relaMmbs.length; i++) {
								var content = '';
								content += "<tr style='border-bottom:1px #ddd solid;'>";
								content += "<td style='padding-left:20px;'>"+(i+1)+"</td>";
								content += "<td>"+data.relaMmbs[i].fname+"</td>";
								content += "<td>"+data.relaMmbs[i].bizType_+"</td>";
								content += "<td><input class='btn btn-warning' style='width:40px; height:25px; line-height:12px; padding:5px 5px' type=button value='同意' onclick=judgeMmbRela('"+data.relaMmbs[i].id+"','0')>&nbsp;";
								content += "<input class='btn btn-danger' style='width:40px; height:25px; line-height:12px; padding:5px 5px' type=button value='拒绝' onclick=judgeMmbRela('"+data.relaMmbs[i].id+"','1')>&nbsp;";
								content += '</td></tr>';
								addTr('relaMmbPendingTable', -1, content);
							}
						} 
					setPagination(1, 10, data.relaMmbsCount);
				},
				error : function() {
					alert("异常！");
				}
			});
		}
	});

	//会员申请审批列表分页信息
	function setPagination(curr, limit, totalCount) {
		$('#callBackPager').extendPagination({
			totalCount : totalCount,
			showCount : limit,
			limit : limit,
			callback : function(curr, limit, totalCount) {
				if ($("#keyword").val() == "" && $("#keyProvince").text() == "所在省份" && $("#keyTitle").text() == "") {
					Notify('请至少选择一个条件！', 'bottom-right', '5000', 'info', 'fa-tag', true);
					return;
				}
				$.ajax({
					url : '${ctx}/mmbRela/queryMmbRelationships.do',// 跳转到 action
					data : {
						mmbId : $("#mmbId").val(),
						pageNo : curr,
						pageSize : limit
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						//先清空table中的数据
						$("#relaMmbPendingTable  tr:not(:first)").remove();
						if (data!=""&&data.relaMmbs.length > 0) {
							for ( var i = 0; i < data.relaMmbs.length; i++) {
								var content = '';
								content += "<tr style='border-bottom:1px #ddd solid;'>";
								content += "<td style='padding-left:20px;'>"+(i+1)+"</td>";
								content += "<td>"+data.relaMmbs[i].fname+"</td>";
								content += "<td>"+data.relaMmbs[i].bizType_+"</td>";
								content += "<td><input class='btn btn-warning' style='width:40px; height:25px; line-height:12px; padding:5px 5px' type=button value='同意' onclick=judgeMmbRela('"+data.relaMmbs[i].id+"','0')>&nbsp;";
								content += "<input class='btn btn-danger' style='width:40px; height:25px; line-height:12px; padding:5px 5px' type=button value='拒绝' onclick=judgeMmbRela('"+data.relaMmbs[i].id+"','1')>&nbsp;";
								content += '</td></tr>';
								addTr('relaMmbPendingTable', -1, content);
							}
						} 
					},
					error : function() {
						alert("异常！");
					}
				});
			}
		});
	}
	
	 /**
	   * 为table指定行添加一行
	   * tab 表id
	   * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
	   * trHtml 添加行的html代码
	   */
	  function addTr(tab, row, trHtml){
	     //获取table最后一行 $("#tab tr:last")
	     //获取table第一行 $("#tab tr").eq(0)
	     //获取table倒数第二行 $("#tab tr").eq(-2)
	     var $tr=$("#"+tab+" tr").eq(row);
	     if($tr.size()==0){
	        alert("指定的table id或行数不存在！");
	        return;
	     }
	     $tr.after(trHtml);
	  }
	  
	  //删除某个table下选中的行
	  function delTr(ckb){
	     //获取选中的复选框，然后循环遍历删除
	     var ckbs=$("input[name="+ckb+"]:checked");
	     if(ckbs.size()==0){
	        alert("要删除指定行，需选中要删除的行！");
	        return;
	     }
	     ckbs.each(function(){
	          $(this).parent().parent().remove();
	     });
	  } 
	  
	  //审批会员关系状态
	 function judgeMmbRela(id,mmbaStatus){
		if(id!=""&&mmbaStatus!=""){
			//暂时省略校验部分，直接提交
			$.ajax({
				url:'${ctx}/mmbRela/verifyMmbRelationship.do',
				type: "POST",
	            dataType: "json",
				data:
					{
						"id":id,
						"type":mmbaStatus
					},
				success:function(data){
				   	 data = eval(data);
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	//提示信息
				   	 	alert("操作成功！");
				   	 	//重新查询
				   	 	document.location.reload();
				   	 }
				   	 //如果失败
				   	 else{
				   	 	alert("操作失败！");
				   	 }
					}
				});
	    }
	}
</script>
<body>
<div class="panel panel-default" style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
<!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <p>待审批申请</p>
    </div>
<!---------------------------------------con top  over--------------------------------------------------------------->
<!---------------------------- table start--------------------------------------------------->
    <div class="table-responsive">
       <table class="table table-hover" id="relaMmbPendingTable">
          <thead>
             <tr class="active" style=" border:1px solid #ddd;" >
                <th width="5%" style="padding-left:20px;">序号<input type="hidden" id="mmbId" value="${mmbId!}"/></th>
                <th width="10%">会员名</th>
                <th width="10%">业务关系</th>
                <th width="10%">操作</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
       </table>
    </div> 
<!---------------------------- table over--------------------------------------------------->
<!---------------------------- 分页  start--------------------------------------------------->
	<footer class="panel-footer text-right bg-light lter">
         <div id="callBackPager" float="right;"></div>
    </footer>
<!---------------------------- 分页  over--------------------------------------------------->


</div>
</body>
</html>
