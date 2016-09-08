<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   
<title>会员主页</title>
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
 <script src="${ctx}/common/js/common.js"></script>
 
 <!-- 校验开始 -->

<script src="${ctx}/mall/js/serializeJson.js"></script>
<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css" />
<!-- 校验结束 -->
<style>
.thumbnail{margin-bottom:0px;}
.myalttu{margin-left:28%;} 
</style>
</head>
<script type="text/javascript">

	$(function(){
			 $("#remark01").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 
			  $("#remark02").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 
			  $("#remark03").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 
			 $("#remark04").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 
			 $("#remark05").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			  $("#remark06").validationEngine('attach',{
					scroll:false,
					autoHidePrompt:true,
					autoHideDelay:2500,
					promptPosition : "bottomLeft"
			 });
			 
		showHomepage();
		showPic();
		showGoodPic();
		showIntruduce();
	});
	
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
	  };
	  
	  //homepage点击上传按钮
	function homeImg(){
		//给子页面getType赋值  表示为会员轮播图片新增	
		childBase.$("#getType").val("5");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		//alert(childBase.$("#getType").val()); 
		$("#childPage").modal('show');
	};
	//homepage页面删除按钮  titlePic1
	function delhomeImg(){
		//清除
		$("#titlePic1").val("");
		
		$("#showPath1").attr("src","");
		//
		$("#homeRemark").val("");
	};
	//pic点击上传按钮
	function picImg(){
		//给子页面getType赋值  表示为会员证书新增
		childBase.$("#getType").val("6");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		//alert(childBase.$("#getType").val()); 
		$("#childPage").modal('show');
	};
	//pic删除按钮  titlePic2
	function delpicImg(){
		//清除
		$("#titlePic2").val("");
		
		$("#showPath2").attr("src","");
		$("#picRemark").val("");
	};
	//good点击上传按钮
	function goodImg(){
		//给子页面getType赋值  表示为会员商品新增
		childBase.$("#getType").val("7");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		
		$("#childPage").modal('show');
	};
	//good删除按钮  titlePic3
	function delgoodImg(){
		//清除
		$("#titlePic3").val("");
		
		$("#showPath3").attr("src","");
		$("#goodRemark").val("");
	};
	
	//logo点击上传按钮
	function logoImg(){
		//给子页面getType赋值  表示为会员商品新增
		childBase.$("#getType").val("8");
		//给子页面赋值  判断是添加标题图片还是添加轮播图  1标题图片  2轮播图
	   	childBase.$("#changeType").val("1");
		
		$("#childPage").modal('show');
	};
	  
	 //显示homePage
	function showHomepage(){
		//alert("轮播");
		$.ajax({
				url : '${ctx}/mmbwesite/showHomePage.do',// 跳转到 action
				data : {
							
							pageNo : 1,
							pageSize : 3
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showHomePage  tr:not(:first)").remove();
					if(data.length>0){
						for ( var i = 0; i < data.length; i++) {
					      	var path = "${ctx}"+data[i].picPath;
							var content = '';
							
							content += '<tr>';
							content += "<td class='col-md-2'>";
							content += "<div class='col-xs-5 col-md-5 myalttu'>";
							content += "<a href='#' class='thumbnail'>";
							content += "<img src='"+path+"'></a></div></td>";
							content += "<td class='col-md-8 col-xs-8 text-center'>"+data[i].remark1+"</td>";
							content += "<td class='col-md-2 text-center'>";
							content += "<button type='button' class='btn btn-link ' onclick=delMal('"+data[i].id+"','0','0')>删除</button>";
							content += "</td>";
							
							content += '</tr>';
							addTr('showHomePage', -1, content);
							
						}
						
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	};
	
	//显示 商品图片
	function showGoodPic(){
		//alert("证书");
		$.ajax({
				url : '${ctx}/mmbwesite/showGoodPic.do',// 跳转到 action
				data : {
							
							pageNo : 1,
							pageSize : 3
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showGoodTable  tr:not(:first)").remove();
					if(data.length>0){
						for ( var i = 0; i < data.length; i++) {
					      	var path = "${ctx}"+data[i].picPath;
							var content = '';
							content += '<tr>';
							content += "<td class='col-md-2'>";
							content += "<div class='col-xs-5 col-md-5 myalttu'>";
							content += "<a href='#' class='thumbnail'>";
							content += "<img src='"+path+"'></a></div></td>";
							content += "<td class='col-md-8 col-xs-8 text-center'>"+data[i].remark2+"</td>";
							content += "<td class='col-md-2 text-center'>";
							content += "<button type='button' class='btn btn-link ' onclick=delMal('"+data[i].id+"','1','1')>删除</button>";
							content += "</td>";
							content += '</tr>';
							addTr('showGoodTable', -1, content);
							
						}
						
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	};
	//显示pic
	function showPic(){
		//alert("证书");
		$.ajax({
				url : '${ctx}/mmbwesite/showPic.do',// 跳转到 action
				data : {
							
							pageNo : 1,
							pageSize : 3
				},
				type : 'POST',
				cache : false,
				dataType : 'json',
				success : function(data) {
					data = eval(data);
					//先清空table中的数据
					$("#showPic  tr:not(:first)").remove();
					if(data.length>0){
						for ( var i = 0; i < data.length; i++) {
					      	var path = "${ctx}"+data[i].picPath;
							var content = '';
							content += '<tr>';
							content += "<td class='col-md-2'>";
							content += "<div class='col-xs-5 col-md-5 myalttu'>";
							content += "<a href='#' class='thumbnail'>";
							content += "<img src='"+path+"'></a></div></td>";
							content += "<td class='col-md-8 col-xs-8 text-center'>"+data[i].remark2+"</td>";
							content += "<td class='col-md-2 text-center'>";
							content += "<button type='button' class='btn btn-link ' onclick=delMal('"+data[i].id+"','1','0')>删除</button>";
							content += "</td>";
							content += '</tr>';
							addTr('showPic', -1, content);
							
						}
						
					}
					
				},
				error : function() {
					alert("异常！");
				}
		});		
	};
	
	//查询简介
	function showIntruduce(){
		//alert("简介");
		$.ajax({
			url : '${ctx}/mmbwesite/showIntroduce.do',// 跳转到 action
			data : {
						type : 0,
						

			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				data = eval(data);
				if(data!=null&&data!=""){
					$("#introduce").val(data.mmbIntroduce);
					$("#mmbTitle").val(data.mmbTitle);
					if(data.mmbLogo!=""&&data.mmbLogo!=null&&data.mmbLogo.length>0){
						var showLogo = "${ctx}"+data.mmbLogo;
						$("#showLogo").attr("src",showLogo);
						$("#mmbLogo").val(data.mmbLogo);
					}
					
				}
			},
			
		});	
	};
	//添加introduce
	function addIntroduce(){
		var introduce = $("#introduce").val();
		//校验是否正确
		if(!$("#remark02").validationEngine("validate")){
			return;
		}
		$.ajax({
			url : '${ctx}/mmbwesite/addIntroduce.do',// 跳转到 action
			data : {
						mmbIntroduce : introduce,
						

			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				data = eval(data);
				if(data=="0"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
			},
			
		});	
	};
	//添加  logo图片
	function addLogo(){
		var mmbLogo = $("#mmbLogo").val();
		if(mmbLogo==""){
			alert("请先选择图片");
			return false;
		}
		$.ajax({
			url : '${ctx}/mmbwesite/addIntroduce.do',// 跳转到 action
			data : {
						mmbLogo : mmbLogo,
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				data = eval(data);
				if(data=="0"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
			},
			
		});	
	};
	//添加   摘要
	function addTitle(){
		var mmbTitle = $("#mmbTitle").val();
		if(!$("#remark04").validationEngine("validate")){
			return;
		}
		$.ajax({
			url : '${ctx}/mmbwesite/addIntroduce.do',// 跳转到 action
			data : {
						mmbTitle : mmbTitle,
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				data = eval(data);
				if(data=="0"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
			},
			
		});	
	};
	//清空  简介
	function delIntroduce(){
		$("#introduce").val("");
	};
	//清空  摘要
	function delmmbTitle(){
		$("#mmbTitle").val("");
	};
	
	//添加    2的时候添加pictype==1
	function addMal(type){
		var remark = "";
		var malId = "";
		if(type=='0'){
			remark = $("#homeRemark").val();
			malId = $("#titlePic1").val();
		}
		if(type=='1'){
			remark = $("#picRemark").val();
			malId = $("#titlePic2").val();
		}
		if(type=='2'){
			remark = $("#goodRemark").val();
			malId = $("#titlePic3").val();
		}
		$("#showType1").val("");
		$("#showType1").val(type);
		
		if(type=='0'){
			//校验是否正确
			if(!$("#remark01").validationEngine("validate")){
				return;
			}
		}
		if(type=='1'){
			//校验是否正确
			if(!$("#remark03").validationEngine("validate")){
				return;
			}
		}
		if(type=='2'){
			//校验是否正确
			if(!$("#remark06").validationEngine("validate")){
				return;
			}
		}
		
		
		if(malId==""){
			alert("请先选择图片");
			return false;
		}
		
		$.ajax({
			url : '${ctx}/mmbwesite/addMal.do',// 跳转到 action
			data : {
						type : type,
						malId : malId,
						remark :remark

			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				//data = eval(data);
				//alert(data);
				if(data=="0"){
					//成功
					alert("添加成功");
					//重新加载
					var type1 = $("#showType1").val();
					if(type1=="0"){
						//从新查询  清空
						showHomepage();
						delhomeImg();
					}
					if(type1=="1"){
						//从新查询  清空
						showPic();
						delpicImg();
					}
					if(type1=="2"){
						//从新查询  清空
						showGoodPic();
						delgoodImg();
					}
					
				}else{
					//删除失败
					alert("添加失败");
				}
			},
			
		});	
	};
	//删除
	function delMal(malId,type,picType){
		$("#showType2").val("");
		$("#showType2").val(type);
		$("#showType3").val("");
		$("#showType3").val(picType);
		$.ajax({
			url : '${ctx}/mmbwesite/delMal.do',// 跳转到 action
			data : {
						picType:picType,
						type : type,
						malId : malId,

			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				//data = eval(data);
				if(data=="0"){
					//删除成功
					alert("删除成功");
					//重新加载
					var type2 = $("#showType2").val();
					if(type2=="0"){
						showHomepage();
					}
					if(type2=="1"){
						var type3 = $("#showType3").val();
						if(type3=="0"){
							showPic();
						}else{
							showGoodPic();
						}
						
					}
				}else{
					//删除失败
					alert("删除失败");
				}
			},
			
		});	
	}
	//预览
	function priviewMmb(){
		var nodeId = $("#selectNodeId").val();
		if(nodeId==""){
			alert("请先选择模板");
			return false;
		}
		$.ajax({
			url : '${ctx}/mmbwesite/privewMmbPage.do',// 跳转到 action
			data : {
						nodeId : nodeId,
						

			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				//data = eval(data);		
				if(data!=null&&data!=""){
					
					if (data.substr(0,1)=='/'){
						data=data.substr(1);
					}
					
					var path = '${Request.basePath}'+data;
					window.open(path);
				}
			},
			
		});	
	}	
	//废除会员页面
	function deleteMmbPage(){
		
		$.ajax({
			url : '${ctx}/mmbwesite/deleteMMbHome.do',// 跳转到 action
			data : {
						mmbId: "",
						

			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				data = eval(data);
				alert(data);
			},
			
		});	
	}		
	
</script>

<body>

<div>
     <div class="panel-body">
     	<div class="col-md-3">
            <div class="input-group">
                <div class="input-group-btn">
                    <h5 >选择模板:</h5>
                <!--   <button class="btn" type="button"></button> -->
                </div>
                <select name="selecter_basic" tabindex="-1"class="form-control" id="selectNodeId">
                    <option value="">请选择</option>
                    <option value="test">通用模板</option>
                    <#if nodesList ??>
                        <#list nodesList as text>
                            <option value="${text.nodeId!}">${text.nodeTitle!}</option>
                        </#list>
                    </#if>
                 </select>
            </div>
        </div>
     	
		<button class="pull-right btn btn-primary col-sm-1" type="button" onclick="deleteMmbPage();" style="margin-left:20
		px;" >废除</button>
        <button class="pull-right btn btn-primary col-sm-1" type="button" onclick="priviewMmb();" >发布</button>
        <div class="clearfix"></div>
	</div>
   <div class="panel-body">
      
	<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseOne">
           业务介绍图
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">

<table class="table table-condensed" id="showHomePage">
   <input type= "hidden" id = "showType1" /> 
   <input type= "hidden" id = "showType2" />
   <input type= "hidden" id = "showType3" />
   <input type= "hidden" id = "titlePic1" />
   <input type= "hidden" id = "titlePic2" />
   <input type= "hidden" id = "titlePic3" />
   <thead>
      <tr>
         <th class="text-center">图片</th>
         <th class="text-center">说明</th>
         <th class="text-center">操作</th>
      </tr>
   </thead>
   <tbody>
 
   </tbody>
</table>

</div><!--第1个 pannel body 结束--> 
      
<div class="panel-body ">
<form id="remark01" 　method="post">
<blockquote class="pull-left">
    <button type="button" class="btn btn-default" onclick="homeImg();">选择图片</button>
   <div class="col-xs-5 col-md-5">
      <a href="#" class="thumbnail" >
         <img src=""  id="showPath1" >
      </a>
   </div>
</blockquote>

<button class="pull-right btn btn-default col-md-1" type="button" class="btn btn-default" onclick="addMal('0');">添加</button>

<textarea class="form-control validate[maxSize[50]]" rows="3"  placeholder="请输入说明文字" id="homeRemark"></textarea>
</form>
</div><!--第二个 pannel body 结束-->      
    </div>
  </div>
  
 
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">
		<a data-toggle="collapse" data-parent="#accordion"  href="#collapseTwo">
		 企业介绍
		</a>
	    </h4>
	</div>
     
    <div id="collapseTwo" class="panel-collapse collapse">
    	
    	<ul class="list-group"> 
    		 <form id="remark05" 　method="post">
	        <li class="list-group-item">
			           会员logo:
	           <img src="" alt="" style="height:80px;width:80px;margin-left:10px;" id="showLogo" >
	           <input type="hidden" id="mmbLogo"  /> 
	           <button type="button" class="btn btn-info" style="margin-top:42px; margin-left:20px;" onclick="logoImg();">选择</button> 
	           
	        </li> 
	        <li class="list-group-item">
		        <p class="text-right" >
			        
					<button type="button" class="btn btn-default" onclick="addLogo();">添加</button>
				</p>
	        </li>
	        </form> 
	        <form id="remark04" 　method="post">
	        <li class="list-group-item">会员摘要:
	        	<textarea class="form-control validate[required,maxSize[100]]" rows="3"    id="mmbTitle"></textarea>
	        </li>
	         <li class="list-group-item">
		        <p class="text-right" >
			        <button type="button" class="btn btn-default" onclick="delmmbTitle();">清空</button>
					<button type="button" class="btn btn-default" onclick="addTitle();">保存</button>
				</p>
	        </li> 
	         </form>
	        <form id="remark02" 　method="post">
	        <li class="list-group-item">会员简介：
	        	<textarea class="form-control validate[required,maxSize[800]]" rows="3"    id="introduce"></textarea>
	        </li>
	        <li class="list-group-item">
		        <p class="text-right" >
			        <button type="button" class="btn btn-default" onclick="delIntroduce();">清空</button>
					<button type="button" class="btn btn-default" onclick="addIntroduce();">保存</button>
				</p>
	        </li>
	        </form>
   		</ul>
		
	</div>  
</div>
  
 
  
  
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseThree">
          资质证书
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse">
      <div class="panel-body">
<table class="table table-condensed" id="showPic">
   <thead>
      <tr>
         <th class="text-center">图片</th>
         <th class="text-center">说明</th>
         <th class="text-center">操作</th>
      </tr>
   </thead>
   <tbody>
     
   </tbody>
</table>
</div><!--第1个 pannel body 结束--> 
     
<div class="panel-body ">
 <form id="remark03" 　method="post">
<blockquote class="pull-left">
    <button type="button" class="btn btn-default" onclick="picImg();">选择图片</button>
   <div class="col-xs-5 col-md-5">
      <a href="#" class="thumbnail">
         <img src=""  id="showPath2"   >
      </a>
   </div>
</blockquote>

<button class="pull-right btn btn-default col-md-1" type="button" class="btn btn-default" onclick="addMal('1');">添加</button>


<textarea class="form-control validate[maxSize[50]]" rows="3"  placeholder="请输入说明文字" id="picRemark"></textarea>


</div><!--第二个 pannel body 结束-->  
</form>      
</div>
    </div>
 <!--商品图片开始-->   
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">
			<a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
			 商品图片
			</a>
		</h4>
	</div>
    <div id="collapseFour" class="panel-collapse collapse">
		<div class="panel-body">
			<table class="table table-condensed" id="showGoodTable" >
				<thead>
					<tr>
						<th class="text-center">图片</th>
						<th class="text-center">说明</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>    
				</tbody>
			</table>
		</div><!--第1个 pannel body 结束--> 
     
		<div class="panel-body ">
		<form id="remark06" 　method="post">
			<blockquote class="pull-left">
				<button type="button" class="btn btn-default" onclick="goodImg();" >选择图片</button>
				<div class="col-xs-5 col-md-5">
					<a href="#" class="thumbnail">
					<img src=""  id="showPath3"  >
					</a>
				</div>
			</blockquote>
			<button class="pull-right btn btn-default col-md-1" type="button" class="btn btn-default" onclick="addMal('2');">添加</button>
			<textarea class="form-control validate[maxSize[50]]" rows="3"  placeholder="请输入说明文字" id="goodRemark"></textarea>
		</div><!--第二个 pannel body 结束-->  
		</form>		      
	</div>
</div>    
 <!--商品图片结束-->     
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 嵌入资料库-->
<div class="modal fade " id="childPage" role="dialog" aria-labelledby="gridSystemModalLabel" style="width:1200px; height:750px;" >
	
		<div class="container-fluid modal-draggable"  style="margin:0px; padding:0px;" >
            <div class="row-fluid" >
                <!-- col-sm-12 -->
                <div class="col-sm-12 " style="margin:0px; padding:0px;">
                    <div class="panel panel-default article-bj"  style="min-height:750px; height:100%; width:100%">
                        <div class="panel-heading box-shodm ">
                        资源库信息
                        <button type="button" class="close" 
                           data-dismiss="modal" aria-hidden="true" onclick="childCloseImg();">
                              &times;
                        </button>
                        
                        </div>
                       
                        <div >
                         <iframe  id="childBase" name="childBase"  src="${ctx}/mbase/toshowBase1.do" width="100%" height="100%" frameborder="0"   class=" modal-draggable " />
                   	    </div>
                    </div>
                </div>
            </div>
        </div>
		
	
</div>
</body>
</html>
