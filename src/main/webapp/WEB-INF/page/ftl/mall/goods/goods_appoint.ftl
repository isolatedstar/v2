<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品管理</title>
<link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/SimpleTree.css" rel="stylesheet" type="text/css">

 
<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery.min.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/SimpleTree.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
<script src="${ctx}/mall/js/extendPagination.js"></script>
<script src="${ctx}/mall/js/showGood.js"></script>
 <script src="${ctx}/common/js/common.js"></script>

<script type="text/javascript">
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
	//查询商品
	function showGood(){
		//alert("查询");
		$.ajax({
				url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
				data : {
							categoryId:$("#showCategory").val(),
							pageNo : 1,
							pageSize : 2
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showTable  tr:not(:first)").remove();
					if(data.mtList.length>0){
						for ( var i = 0; i < data.mtList.length; i++) {
					
							var content = '';
							
							var path = "${ctx}"+data.mtList[i].imgPath;
							var idName = data.mtList[i].goodsId+","+data.mtList[i].name;
							content += "<tr>";
							content += "<td style='vertical-align:middle'><input type='checkbox' name='goodCheckbox' value='"+idName+"' onclick='checkRedio(this);' /></td>";
							content += "<td><img src='"+path+"' style='height:50px; width:50px;'></td>";
							content += "<td style='vertical-align:middle'>"+data.mtList[i].minPrice+"--"+data.mtList[i].maxPrice+"</td>";
							content += "<td style='vertical-align:middle'>"+data.mtList[i].name+"</td>";
						
							content += "<td style='vertical-align:middle'>"+data.mtList[i].productNum+"</td>";
							content += "<td style='vertical-align:middle'>"+data.mtList[i].factory+"</td>";
							
							content += '</tr>';
							addTr('showTable', -1, content);
							
						}
						//alert("数据条数="+data.mtCount);
						setPagination(1, 2, data.mtCount);
					}else{
						alert("没有搜索的结果");
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	}
	function setPagination(curr, limit, totalCount) {
			//alert("分页");
			$('#callBackPager').extendPagination({
				totalCount : totalCount,
				showCount : limit,
				limit : limit,
				callback : function(curr, limit, totalCount) {
					
					$.ajax({
						url : '${ctx}/GoodController/serachGood.do',// 跳转到 action
						data : {
									categoryId:$("#showCategory").val(),
									pageNo : curr,
									pageSize : limit
						},
						type : 'POST',
						cache : false,
						dataType : 'json',
						success : function(data) {
							data = eval(data);
							//先清空table中的数据
									$("#showTable  tr:not(:first)").remove();
									if(data.mtList.length>0){
											for ( var i = 0; i < data.mtList.length; i++) {
								
													var content = '';
												
													var path = "${ctx}"+data.mtList[i].imgPath;
													var idName = data.mtList[i].goodsId+","+data.mtList[i].name;
													content += "<tr>";
													content += "<td style='vertical-align:middle'><input type='checkbox' name='goodCheckbox' value='"+idName+"' onclick='checkRedio(this);' /></td>";
													content += "<td><img src='"+path+"' style='height:50px; width:50px;'></td>";
													content += "<td style='vertical-align:middle'>"+data.mtList[i].minPrice+"--"+data.mtList[i].maxPrice+"</td>";
													content += "<td style='vertical-align:middle'>"+data.mtList[i].name+"</td>";
												
													content += "<td style='vertical-align:middle'>"+data.mtList[i].productNum+"</td>";
													content += "<td style='vertical-align:middle'>"+data.mtList[i].factory+"</td>";
													
													content += '</tr>';
													addTr('showTable', -1, content);
										
											}
											alert("赋值");
									}else{
											alert("无结果");
									}
						},
						error : function() {
							alert("异常！");
						}
				});
				}
			});
	}
	//多选框实现单选
	function checkRedio(obj){
 
		if($(obj).prop('checked')==true){
			$("#showMalTable tr td [name =malCheckbox]:checkbox").removeAttr('checked');
			$(obj).prop('checked','true');
		}				
	}
</script>
</head>

<body>
 <div class="panel panel-default article-bj" >
 	<input type="hidden" id="showCategory" />
    <div class="panel-heading box-shodm modal-draggable">
    商品信息
    </div>
    <div class="row wrapper form-margin">
         <div class="col-md-6">
            <div class="input-group">
                 <div class="input-group-btn">
                    <h5 class="h5-margin" style="margin-left:20px;">名称:</h5>
                 <!--   <button class="btn" type="button"></button> -->
                 </div>
                 <input type="text" placeholder="" class="form-control" name="input1-group3" id="input1-group3">
            </div>
        </div>
        <a id="searchGood" class="btn btn-info btn-s-md btn-default " href="#" onclick="showGood();">查询</a>
     </div>
    <div class="table-responsive panel-table-body ">
        <table class="table table-striped table-hover" id = "showTable">
            <thead>
                <tr>
                	<td>选择</td>
                    <th>图片</th>
                    <th>商品名称</th>
                    <th>价格范围</th> 
                    <th>生产编号</th>
                    <th>生产厂家</th>
                </tr>
            </thead>
            <tbody>
               
            </tbody>
        </table>
    </div> 
    <footer class="panel-footer text-right bg-light lter">
        <div id="callBackPager" float="right;"></div>
    </footer>
 </div>
</div>
</body>
</html>
