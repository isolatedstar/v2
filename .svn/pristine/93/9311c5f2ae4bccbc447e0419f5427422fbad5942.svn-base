<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品详情</title>
<link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/SimpleTree.css" rel="stylesheet" type="text/css">


<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery.min.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>

 <script src="${ctx}/common/js/common.js"></script>

<script type="text/javascript">
		$(function(){
	   		 //alert("加载商品");
	   		 lookGood();
	   		
	   });	
       //显示商品
       function lookGood(){
   		   //清空img
   		   $("#imgPath").attr("src","");
       	   //清空要展示的内容
       	   $("#name").html("");
       	   $("#categoryName").html("");
       	   $("#createAddress").html("");
       	   $("#factory").html("");
       	   $("#productNum").html("");
       	   $("#brand").html("");
       	   $("#specification").html("");
       	   $("#productTime").html("");
       		var goodId = $("#goodId").val();
       		if(goodId==""){
       			alert("id为空");
       			return false;
       		}
       		$.ajax({
					url : '${ctx}/GoodController/lookGood.do',// 跳转到 action
					data : {
							goodId : goodId,				
					},
					type : 'POST',
					cache : false,
					dataType : 'json',
					success : function(data) {
						data = eval(data);
						if(data!=null){
							 var path = "${ctx}"+data.imgPath;	
							 $("#imgPath").attr("src",path);
					       	   //清空要展示的内容
					       	   $("#name").html(data.name);
					       	   $("#categoryName").html(data.categoryName);
					       	   $("#createAddress").html(data.createAddress);
					       	   $("#factory").html(data.factory);
					       	   $("#productNum").html(data.productNum);
					       	   $("#brand").html(data.brand);
					       	   $("#specification").html(data.specification);
					       	   $("#productTime").html(data.productTime);
					       		
						}
					},
			});
       }
       function closePage(){
       		parent.$("#goodsDetail").modal('hide');
       }
</script>
</head>

<body>
	
	<!--商品管理详情弹出层-->

    <div class="modal-dialog tanchu-box" role="document" style="width:50%; ">
        <div class="container-fluid container-margin">
            <div class="panel panel-default ">
                <div class="panel-heading box-shodm modal-draggable">
                    	商品详情
                    	<input type="hidden" id="goodId" />
                        
                </div>
                <div class="row wrapper form-margin"  style="margin-left:25px;">
               		<div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >图片:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <img id="imgPath" src="" class=" text-left detail-margin-left2" style="height:70px; width:70px;">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >名称:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left2" ><p id="name"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >种类:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left2" ><p id="categoryName"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >产地:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left2" ><p id="createAddress"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >生产产家:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left4" ><p id="factory"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >生产编号:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left4" ><p id="productNum"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >品牌:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left2" ><p id="brand"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >规格:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left2" ><p id="specification"></p></h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                             <div class="input-group-btn">
                                <h5 >保质期:</h5>
                             <!--   <button class="btn" type="button"></button> -->
                             </div>
                             <h5 class="text-left detail-margin-left3" ><p id="productTime"></p></h5>
                        </div>
                    </div> 
            	</div> 
                <!--选项卡结束-->
                <div class="panel-footer text-right">
                    <button class="btn btn-warning btn-s-xs " data-dismiss="modal">确定</button>
                </div>
        	</div>
        </div> 
    </div> 

</body>
</html>
                   

