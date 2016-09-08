<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>采购拣单</title>
   <link rel="stylesheet" href="${ctx}/mall/css/bootstrap.min.css">
   <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
   <script src="${ctx}/mall/js/jquery.js"></script>
   <script src="${ctx}/mall/js/jquery.min.js"></script>
   <script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
   <script src="${ctx}/mall/js/bootstrap.min.js"></script>
  
  <!-- 创建订单导入开始 -->
<link href="${ctx}/mall/css/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/mall/css/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="${ctx}/mall/js/bootstrap.min.js"></script>
<script src="${ctx}/mall/js/bootstrap-table.js"></script>
<script src="${ctx}/mall/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/mall/js/moment-with-locales.js"></script>
<script src="${ctx}/mall/js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/mall/js/serializeJson.js"></script>
<script src="${ctx}/mall/js/createOrder.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
<!-- 创建订单导入结束 -->
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
	  
	//校验数量
	function jiaoyanNum(obj){
		$(obj).validateNumber();
	}  
	 //自动加载
	var createOrder;
	var pp = "";
	$(function(){
			//alert("自动加载");
			showShpc();
			createOrder = new createOrder("changeChar",'${Session.user.getMuser().getMmbId()}','${Request.basePath}',delMany);
			 $("#shopForm").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			
			$("#showShpcTable tr td [name='num']").on("input propertychange", function() {
				$(this).validateNumber();
				
			});
			
	});
	//全选反选  showShpcTable
	function checkAllShpc(obj,index){
		
		$("#showShpcTable tr td [name ='"+index+"']:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		});
	}
	
	//删除方法
	function delShpc(index){
		//获取选中的报价在捡单车中的位置
		//alert(index);
		$.ajax({
				url : '${ctx}/shpc/deleteQuoteSHPC.do',// 跳转到 action
				data : {
							type : 1,
							index : index
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					if(data==0){
						alert("删除成功");
						//重新查询
						showShpc();
					}else{
						alert("删除失败");
					}
				}
		});
	}
	
	//下单  采购报价登陆人座位买家
	function showOrder(mmbId){
		
		//获取选中的多选框按钮的值
	  	var checkedGroups = $("#showShpcTable tr td [name = '"+mmbId+"']:checked");
		var stringsId = "";
		checkedGroups.each(function(i,thisElement){
			if(thisElement.value!=""){
				stringsId+=thisElement.value+":";
			}
		});
		
		if(stringsId.length==0){
			alert("请先勾选报价!");
			return false;
		}
		
		//alert(mmbId);
		//获取登录人Id 以及name 作为买家
		var buyersId = "${Session.user.getMuser().getMmbId()}";
		var buyersName = "${Session.user.getMuser().getMmbName()}";
		//mmbId 作为卖家Id
		var sellersId = mmbId;
		var sellersName ="";
		//alert(buyersId+"=============="+buyersName)
		//赋值的参数
		var dataOrder = new Object();
		//商品编号字符串
		var indexs ="";
		//商品行集合
		var list = new Array();
		var i = 0;
		
		$('#showShpcTable').find("tr:not(first)").each(function(i,element){
			var check = $(element).find("input[name='"+mmbId+"']:checkbox").prop("checked");
			if(check == true){
				//var goodName = document.getElementById("showShpcTable").rows[i].cells[1].innerHTML;
				
				//获取输入的数量
				var number = $(element).find("input[name='num']").val();
				if(number==null||number==""){
					alert("数量不能为空");
					$("#changeChar").modal("hide");
					return fasle;
				}
				//获取数据
				var goods = $(element).find("input[name='"+mmbId+"']").val();
				//alert(goods);
				 if(goods!=null&&goods!=""){
				 	 var nn = new Array();
					// 0goodsid 1name 2min 3man 4mmbName 5index
					 nn = goods.split(",");
					 var good = new Object;
					 good.id = "";
					 good.goodsId = nn[0]
					 good.goodsName = nn[1];
					 good.goodsNum = number;
					 good.price ="";
					 good.money ="";
					 good.buyersId = buyersId;
					 good.buyersName = buyersName;
					 good.sellersId = sellersId
					 sellersName = nn[4];
					 good.sellersName = sellersName;
					 good.min = nn[2];
					 good.max = nn[3];
				 	 list.push(good);
					 indexs += nn[5]+","; 
					 i += 1;
					
				 }else{
				    alert("请先选择一个报价");
				    return false;
				 }
				 
			}
		});
		dataOrder.data = list;
		dataOrder.total = list.length;
		if(dataOrder.total>100){
			alert("商品数量不能超过100条");
			$("#changeChar").modal("hide");
			return fasle;
		}
		//ajax 获取地址银行
		$.ajax({
				url : '${ctx}/order/queryAddressAccount.do',// 跳转到 action
				data : {
							buyersId : buyersId,
							sellersId : sellersId
				},
				async: false,
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					if(data!=null&&data!=""){
						dataOrder.buyersAddressList = data.buyersAddressList;
						dataOrder.sellersAddressList = data.sellersAddressList;
						dataOrder.buyersAccountList = data.buyersAccountList;
						dataOrder.sellersAccountList = data.sellersAccountList;
					}
				}
		});
		//alert(indexs);
		//订单头对象
		var title = new Object();
		title.buyersId = buyersId;
		title.buyersName = buyersName;
		title.sellersId = sellersId;
		title.sellersName = sellersName;
		title.totalMoney = "";
		title.payBank = "";
		title.payAccount = "";
		title.getBank = "";
		title.getAccount = "";
		dataOrder.ordertitle = title;
		//alert(dataOrder.total);
		//alert(indexs);
		$("#changeChar").modal("show");
		createOrder.initOrder(dataOrder);
		//将下单的报价移除
		indexs = indexs.substring(0,indexs.length-1);
		pp = indexs;
			
	}
	//批量删除
	
	function delMany(){
	  // alert("====="+pp);
	   
		
			$.ajax({
					url : '${ctx}/shpc/deleteMoreSHPC.do',// 跳转到 action
					data : {
								type : 1,
								index : pp
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data=="0"){
							//重新显示
							showShpc();
						}
						
					}
			});
	}
	//显示页面  采购捡单车·  获取销售报价
	function showShpc(){
		$.ajax({
				url : '${ctx}/shpc/serachSHPC.do',// 跳转到 action
				data : {
							type : 1,
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					
					//先清空table中的数据
					$("#showShpcTable  tr:not(:first)").remove();
					if(data!=null&&data!=""){
						//遍历Map key值为id+name
						for(var key in data) { 
   							 var nn= new Array(); 
   							 //添加用户
   							 if(key!=null&&key!=""){
   							 	
	   							 nn = key.split(",");
	   							 var user = "";
								 user += "<tr>"
								 user += "<td><input type='checkbox' id='"+nn[0]+"' onclick=checkAllShpc(this,'"+nn[0]+"') /></td>";
								 user += "<td colspan='8'>销售方："+nn[1]+"</td></tr>";
								 addTr('showShpcTable', -1, user);
   							  
   							 
							 //遍历报价
							 if(data[key]!=null&&data[key].length>0){
							 		for ( var i = 0; i < data[key].length; i++) {
										var content = "";
										content +=" <tr class='success'>";
										//拼接传值顺序  商品Id  商品Name  最小值  最大值 会员名称  顺序
										var aa = data[key][i].goodsId+","+data[key][i].goodsName+","+data[key][i].minPrice+","+data[key][i].maxPrice+","+data[key][i].mmbName+","+data[key][i].index;
										content +="<td><input type='checkbox'  name='"+nn[0]+"' id='"+data[key][i].index+"' value='"+aa+"'></td>"
										content +="<td>"+data[key][i].goodsName+"</td>"
										content +="<td>"+data[key][i].minPrice+"---"+data[key][i].maxPrice+"</td>";
										content +="<td><input type='text' class='input-size validate[required,custom[integer]]' name='num' onpropertychange=jiaoyanNum(this) oninput=jiaoyanNum(this) /></td>";
										content +="<td>"+data[key][i].mmbName+"</td>";
										content +="<td><a href='#'   onclick=delShpc('"+data[key][i].index+"')>删除</a></td>";
										content += '</tr>';
										addTr('showShpcTable', -1, content);
									}	
							 }
							 //添加下单
							 var xiadan ="";
							 xiadan +="<tr class='text-right'>";
							 xiadan +="<td colspan='5' style='vertical-align: middle;'></td>";
							 xiadan +="<td colspan='1' ><button class='btn btn-warning cx-btn-margin' type='button'onclick=showOrder('"+nn[0]+"')>下单</button></td>";
							 xiadan +="</tr>";
							 addTr('showShpcTable', -1, xiadan);	 
						} 
						}
					}else{
						//alert("该用户没有数据!");
					}
				},
				error : function() {
					alert("异常！");
				}
		});		
	};
	
</script>
</head>

<body>
<div class="container-fluid" style="margin-top:15px;">
	<div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel"></div>
    <div class="row-fluid">
    	<input type="hidden" id="deIds" />
    	<input type="hidden" id="success" />
        <!-- col-sm-12 -->
        <div class="col-sm-12 ">
        	<div class="panel panel-default article-bj">
                <div class="panel-heading box-shodm">
                采购拣单车
                </div>
                    <div class="table-responsive panel-table-body ">
                    	<form id="shopForm" 　method="post">
                        <table class="table table-striped table-hover" id="showShpcTable" method="post">
                            <thead>
                                <tr>
                                    <th colspan="2">商品信息</th>
                                    <th>单价（元）</th>
                                    <th>数量（pcs）</th>
                                    <th>所属商家</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                        </form> 
                    </div> 
                    <footer class="panel-footer text-right bg-light lter">
                    		<div id="callBackPager" float="right;"></div>
                    </footer>
        		</div>
       		 </div>
        </div>
    </div> 
    
</div>
</body>
</html>
