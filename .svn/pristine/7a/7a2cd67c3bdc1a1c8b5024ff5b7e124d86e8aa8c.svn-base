<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <title>报价详情</title>
<link href="${ctx}/mall/css/mall.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/media.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/theme.css" rel="stylesheet" type="text/css">
<script src="${ctx}/mall/js/jquery.js"></script>
<script src="${ctx}/mall/js/jquery.min.js"></script>
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/SimpleTree.js" type="text/javascript"></script>
<script src="${ctx}/mall/js/bootstrap-treeview.js"></script>
<script src="${ctx}/mall/js/serializeJson.js"></script>
 <script src="${ctx}/common/js/common.js"></script>
<script>
$(document).ready(function(e) {
	$('.m_menu').hover(function(){
		$('.m_subnav').hide()
		$('.m_menu_ul>li>a').css('background-position','right top')
		$('.m_menu_ul').show();
		},function(){
			})
	$('.m_menu_ul').hover(function(){
		},function(){
		$(this).hide()
			})
	$('.m_menu_2').hover(function(){
		$('.m_subnav').hide()
		$('.m_menu_ul>li>a').css('background-position','right top')
		$('.m_menu_ul').show();
		},function(){
			})
    $('.m_menu_ul>li').click(function(){
		$('.m_subnav').stop().slideUp()
		$(this).next('.m_subnav').stop().slideToggle(100);
		$('.m_menu_ul>li>a').css('background-position','right top')
		$(this).find('a').css('background-position','right bottom')
		})
		
		
	$('.m_subnav li').click(function(){
		$('.m_subnav_2').stop().slideUp()
		$(this).next('.m_subnav_2').stop().slideToggle(100);
	})
});
</script>

<script type="text/javascript"> 
	 var goodDetail;  
	 
	  $(document).ready(function() { 
	  		    var quoteId = ${RequestParameters["quoteId"]};
	   		 $("#showQuoteId").val(quoteId);
	   		 quoteDetail(quoteId);  
		 });  
		  var length;
		 var  currentIndex = 0; 
		  var interval; 
		  var hasStarted = false; //是否已经开始轮播 
   		  var t = 3000; //轮播时间间隔 
		 function aa(){
		 		length = $('.slider-panel').length; 
				  //alert(length);
				  //将除了第一张图片隐藏 
				  $('.slider-panel:not(:first)').hide(); 
				  //将第一个slider-item设为激活状态 
				  $('.slider-item:first').addClass('slider-item-selected'); 
				  //隐藏向前、向后翻按钮 
				  $('.slider-page').hide(); 
				  //鼠标上悬时显示向前、向后翻按钮,停止滑动，鼠标离开时隐藏向前、向后翻按钮，开始滑动 
				  $('.slider-panel, .slider-pre, .slider-next').hover(function() { 
				   stop(); 
				   $('.slider-page').show(); 
				  }, function() { 
				   $('.slider-page').hide(); 
				   start(); 
				  }); 
				  $('.slider-item').hover(function(e) { 
				   stop(); 
				   var preIndex = $(".slider-item").filter(".slider-item-selected").index(); 
				   currentIndex = $(this).index(); 
				   play(preIndex, currentIndex); 
				  }, function() { 
				   start(); 
				  }); 
				  $('.slider-pre').unbind('click'); 
				  $('.slider-pre').bind('click', function() { 
				   pre(); 
				  }); 
				  $('.slider-next').unbind('click'); 
				  $('.slider-next').bind('click', function() { 
				   next(); 
				  }); 
		 }
		  /** 
		   * 向前翻页 
		   */
		  function pre() { 
		   var preIndex = currentIndex; 
		   currentIndex = (--currentIndex + length) % length; 
		   play(preIndex, currentIndex); 
		  } 
		  /** 
		   * 向后翻页 
		   */
		  function next() { 
		   var preIndex = currentIndex; 
		   currentIndex = ++currentIndex % length; 
		   play(preIndex, currentIndex); 
		  } 
		  /** 
		   * 从preIndex页翻到currentIndex页 
		   * preIndex 整数，翻页的起始页 
		   * currentIndex 整数，翻到的那页 
		   */
		  function play(preIndex, currentIndex) { 
		   $('.slider-panel').eq(preIndex).fadeOut(500) 
		    .parent().children().eq(currentIndex).fadeIn(500); 
		   $('.slider-item').removeClass('slider-item-selected'); 
		   $('.slider-item').eq(currentIndex).addClass('slider-item-selected'); 
		  } 
		  /** 
		   * 开始轮播 
		   */
		  function start() { 
		   if(!hasStarted) { 
		    hasStarted = true; 
		    interval = setInterval(next, t); 
		   } 
		  } 
		  /** 
		   * 停止轮播 
		   */
		  function stop() { 
		   clearInterval(interval); 
		   hasStarted = false; 
		  } 
		  //开始轮播 
		  start(); 
	//商品详情
	function seeGood(goodId){
		$("#lunbo1").empty();
		$("#lunbo2").empty();
		$.ajax({
			url : "${ctx}/GoodController/lookGood.do",
			data : {goodId : goodId},
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(data) {
				data = eval(data);
				if(data!=null){
					$("#goodlook").modal("show");
					 var path = data.imgPath;	
					 //alert(path);
					 $("#imgPath111").attr("src",path);
			       	   //展示的内容
					 	//alert(data.categoryName);
			       	   $("#goodName111").html(data.name);
			       	   $("#categoryName111").html(data.categoryName);
			       	   $("#createAddress111").html(data.createAddress);
			       	   $("#factory111").html(data.factory);
			       	   $("#productNum111").html(data.productNum);
			       	   $("#brand111").html(data.brand);
			       	   var pp ="";
			       	   if(data.unitSpecification=='1'){
			       		   pp="千克";
			       	   }else{
			       		   pp="克";
			       	   }
			       	   $("#specification111").html(data.specification+",单位:"+pp);
			       	   $("#productTime111").html(data.productTime);
			       	   var price = data.minPrice+data.unitPrice+"--"+data.maxPrice+data.unitPrice;
			       	   $("#price111").html(price);
			       	   $("#stockNum111").html(data.stockNum);
			       	   //轮播图插入 获取图片Id的集合 赋值调用方法
				   	   if(data.imglist!=null&&data.imglist.length>0){
							$("#lunbo333").empty();
							$("#lunbo444").empty();
				   	 		 var lunbo3 = document.getElementById("lunbo333");
							var lunbo4 =document.getElementById("lunbo444");
				   	 		for ( var i = 0; i < data.imglist.length; i++) {
										 var id = i+1;
										 var li= document.createElement("li");  
										 var path = "${ctx}"+  data.imglist[i].materialPath ;
									     var href_a = document.createElement("a");  
									             
									            href_a.target="_blank"
									            href_a.innerHTML ="<img  src='"+path+"' >";  
									            li.id=id;
									            li.setAttribute("class","slider-panel");  
									            li.appendChild(href_a);  
									            lunbo3.appendChild(li); 
									            
									    var li1= document.createElement("li");  
									            li1.id=id;  
									            li1.setAttribute("class", "slider-item");  
									            li1.innerHTML=id;  
									            lunbo4.appendChild(li1); 
							} 
							aa();
				
				   		}
					 
				  } 	 
				
			},
			error : function(data) {
				alert("错误!!!");
			}
		});
	} 
	//确认  关闭按钮
	function closeQuote(){
		 var quoteId = $("#showQuoteId").val();
	   	 quoteDetail(quoteId);  
	}
	//报价详情
	function quoteDetail(quoteId){
		$("#lunbo333").empty();
		$("#lunbo444").empty();
		$.ajax({
			url : '${ctx}/QuoteController/detailQuote.do',// 跳转到 action
			data : {
					quoteId : quoteId,	
			},
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.mq!=null&&data.mq!=""){
					
					$("#goodsName").html("商品名称："+data.mq.goodsName);
					$("#num").html("库存："+data.mq.num);
					$("#price").html(data.mq.unitPrice+":"+data.mq.minPrice+"~"+data.mq.maxPrice+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick=seeGood('"+data.mq.goodsId+"')><span>查看商品明细</span></a></p>");
					$("#time").html("适用期间："+$.changeDate(data.mq.startTime)+"至 "+$.changeDate(data.mq.startEnd));
					$("#mmbName").html("发 布 人  ："+data.mq.mmbName);
					$("#explan").html("* 说明："+data.mq.explan);
					$("#areaName").html("适用地区："+data.areaName);
					$("#showType").val(data.mq.type);
				}
				if(data.pathlist!=null&&data.pathlist!=""&&data.pathlist.length>0){
					var lunbo1 = document.getElementById("lunbo1");
					var lunbo2 =document.getElementById("lunbo2");
					for ( var i = 0; i < data.pathlist.length; i++) {
							 var id = i+1;
							 var li= document.createElement("li");  
							 var path = "${ctx}"+  data.pathlist[i];
						     var href_a = document.createElement("a");  
						             
						            href_a.target="_blank"
						            href_a.innerHTML ="<img  src='"+path+"' >";  
						            li.id=id;
						            li.setAttribute("class", "slider-panel");  
						            
						            li.appendChild(href_a);  
						            lunbo1.appendChild(li); 
						            
						    var li1= document.createElement("li");  
						            li1.id=id;  
						            li1.setAttribute("class", "slider-item");  
						            li1.innerHTML=id;  
						            lunbo2.appendChild(li1); 
							
					} 
					 aa();
				}
				
			},
		});
	}
	//添加到购物车
		function addShpc(){
			
			$.ajax({
				url:'${ctx}/shpc/addQuoteToSHPC.do',
				type: "POST",
	            dataType: "json",
				data:{"quoteId":$("#showQuoteId").val()},
				success:function(data){
				   	data = eval(data);
				   	//alert(data);
				   	 //如果成功
				   	 if(data!=null&&data== "0"){
				   	 	//提示成功
				   	 	alert("添加购物车成功！");
				   	 }else if(data=="2"){
				   	 	alert("该报价已放入购物车");
				   	 }else if(data=="3"){
				   	 	alert("该商品已存在于购物车");
				   	 }
				   	 //失败
				   	 else{
				   	 	alert("添加失败！");
				   	 }
				   	}
				});
				
				
				
				
		}
	//跳转购物车采购页面
	function showShpc(){
		var type = $("#showType").val();
		//type=0搜索采购 跳转销售  type=1搜索销售 跳转采购
		if(type=="0"){
			window.location.href="${ctx}/shpc/toshowShoc1.do";
		}
		if(type=="1"){
			window.location.href="${ctx}/shpc/toshowShoc0.do";
		}
		
	}
	
	//选择品类
		function checkCategory(id){
			
			$("#checkCategoryId").val(id);
			
			$("#checkCategoryId").val("");
			$('.m_menu_ul').hover(function(){
		},function(){
		$(this).hide()
			});
		}
 
</script> 


<script type="text/javascript"> 
 
</script> 
</head>

<body>
<div id="mallwarp" >
<!----------------------header start--------------------------->
	<input type="hidden" id="showQuoteId"  />
	<input type="hidden" id="showType"  />
	<div class="m_header">
    	<img src="${ctx}/mall/images/logo.png"/>
        <h2>农校对接</h2>
    </div>
<!----------------------header over--------------------------->
<div class="m_con_box">
	<div class="m_content_top">
>
        <form class="m_sech_form">
        	
				<div class="con_shapping_box">
				<i class="fa fa-shopping-cart">
				<span style="padding-left:5px;" onclick="addShpc();">购物车</span>
				</i>
				</div> 
				
			
		
        </form>
	</div>
		<!------------------------------------------nav start----------------------------------------------------------->
     <div class="m_nav">
        <div class="m_menu"><p>商品分类<i class="fa fa-qrcode"></i></p></div>
        <div class="m_menu_2"><p><i class="fa fa-list"></i></p></div>
        <ul class="m_menu_ul">
            <li><a href='#'>粮油</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#'>面粉</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1001001001')>袋装面粉</a></li>
			      <li><a href='#' onclick=checkCategory('1001001002')>散装面粉</a></li>
			    </ul>
			    <li><a href='#'>大米</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1001002001')>袋装大米</a></li>
			      <li><a href='#' onclick=checkCategory('1001002002')>散装大米</a></li>
			    </ul>
			    <li><a href='#' onclick=checkCategory('1001003')>杂粮</a></li>
			    <li><a href='#'>食用油</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1001004001')>瓶装油</a></li>
			      <li><a href='#' onclick=checkCategory('1001004002')>散装油</a></li>
			    </ul>
			    <li><a href='#' onclick=checkCategory('1001005')>其他粮油</a></li>
			  </ul>
			<li><a href='#'>副食品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#'>蔬菜</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002001001')>叶花类菜</a></li>
			      <li><a href='#' onclick=checkCategory('1002001002')>瓜果类菜</a></li>
			      <li><a href='#' onclick=checkCategory('1002001003')>根茎类菜</a></li>
			      <li><a href='#' onclick=checkCategory('1002001004')>其他蔬菜</a></li>
			    </ul>
			    <li><a href='#'>果品</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002002001')>鲜果</a></li>
			      <li><a href='#' onclick=checkCategory('1002002002')>干果</a></li>
			      <li><a href='#' onclick=checkCategory('1002002003')>其他果品</a></li>
			    </ul>
			    <li><a href='#'>畜类</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002003001')>畜类鲜品</a></li>
			      <li><a href='#' onclick=checkCategory('1002003002')>畜类冻品</a></li>
			      <li><a href='#' onclick=checkCategory('1002003003')>畜类干品</a></li>
			    </ul>
			    <li><a href='#'>禽类</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002004001')>禽类鲜品</a></li>
			      <li><a href='#' onclick=checkCategory('1002004002')>禽类冻品</a></li>
			      <li><a href='#' onclick=checkCategory('1002004003')>禽类干品</a></li>
			    </ul>
			    <li><a href='#'>水产</a></li>
			    <ul class='m_subnav_2'>
			      <li><a href='#' onclick=checkCategory('1002005001')>水产鲜品</a></li>
			      <li><a href='#' onclick=checkCategory('1002005002')>水产冻品</a></li>
			      <li><a href='#' onclick=checkCategory('1002005003')>水产干品</a></li>
			    </ul>
			    <li><a href='#' onclick=checkCategory('1002006')>蛋类</a></li>
			    <li><a href='#' onclick=checkCategory('1002007')>其他副食</a></li>
			  </ul>
			<li><a href='#'>加工食品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1003001')>面制品</a></li>
			    <li><a href='#' onclick=checkCategory('1003002')>豆制品</a></li>
			    <li><a href='#' onclick=checkCategory('1003003')>乳制品</a></li>
			    <li><a href='#' onclick=checkCategory('1003004')>酒类</a></li>
			    <li><a href='#' onclick=checkCategory('1003005')>无酒精饮料</a></li>
			    <li><a href='#' onclick=checkCategory('1003006')>冲泡品</a></li>
			    <li><a href='#' onclick=checkCategory('1003007')>糖果糕点</a></li>
			    <li><a href='#' onclick=checkCategory('1003008')>干货炒货</a></li>
			    <li><a href='#' onclick=checkCategory('1003009')>其他加工食品</a></li>
			  </ul>
			<li><a href='#'>调味品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1004001')>基础调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004002')>酿造类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004003')>腌菜类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004004')>鲜菜类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004005')>干货类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004006')>水产类调味品</a></li>
			    <li><a href='#' onclick=checkCategory('1004007')>其他调味品</a></li>
			  </ul>
			<li><a href='#'>文具办公</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1005001')>办公设备</a></li>
			    <li><a href='#' onclick=checkCategory('1005002')>办公耗材</a></li>
			    <li><a href='#' onclick=checkCategory('1005003')>教学用具</a></li>
			    <li><a href='#' onclick=checkCategory('1005004')>娱乐用品</a></li>
			    <li><a href='#' onclick=checkCategory('1005005')>其他文具</a></li>
			  </ul>
			<li><a href='#'>日用品</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1006001')>家纺</a></li>
			    <li><a href='#' onclick=checkCategory('1006002')>家具</a></li>
			    <li><a href='#' onclick=checkCategory('1006003')>器皿</a></li>
			    <li><a href='#' onclick=checkCategory('1006004')>灯具</a></li>
			    <li><a href='#' onclick=checkCategory('1006005')>卫生用品</a></li>
			    <li><a href='#' onclick=checkCategory('1006006')>厨房用品</a></li>
			    <li><a href='#' onclick=checkCategory('1006007')>其他日用品</a></li>
			  </ul>
			<li><a href='#'>工具材料</a></li>
			  <ul class='m_subnav'>
			    <li><a href='#' onclick=checkCategory('1007001')>包装材料</a></li>
			    <li><a href='#' onclick=checkCategory('1007002')>电工工具</a></li>
			    <li><a href='#' onclick=checkCategory('1007003')>木工工具</a></li>
			    <li><a href='#' onclick=checkCategory('1007004')>园艺工具</a></li>
			    <li><a href='#' onclick=checkCategory('1007005')>水暖配件</a></li>
			    <li><a href='#' onclick=checkCategory('1007006')>墙面材料</a></li>
			    <li><a href='#' onclick=checkCategory('1007007')>装饰用品</a></li>
			    <li><a href='#' onclick=checkCategory('1007008')>其他工具材料</a></li>
			  </ul>
        </ul>
    </div>
</div>
<!------------------------------------------nav over------------------------------------------------------------>
<!------------------------------------------conter bot start------------------------------------------------------------>

<div class="content_3_warp" >
       <div class="slider" > 
      <ul class="slider-main" id="lunbo1"> 
        
      </ul> 
      <div class="slider-extra"> 
       <ul class="slider-nav" id="lunbo2"> 
        
       </ul> 
         <div class="slider-page"> 
        <a class="slider-pre" href="javascript:;;"><</a> 
        <a class="slider-next" href="javascript:;;">></a> 
       </div> 
      </div> 
     </div> 
     <div class="content_3_txt">
        <p id="goodsName"></p>
        <p class="content_div_p2" id="price"></p>
        <p id="num"></p>
    </div>
<div class="content_3_bot">
	<p id="mmbName">  </p>
	<p id="time"></p>
	<p id="areaName"></p>
	<p id="explan"></p>
</div>
</div>
<div class="clear"></div>
<!------------------------------------------conter bot over------------------------------------------------------------>

<div class="footer">
<div class="footer_in_bot">
        	<ul class="footer_in_ul">
            	<li><a href="http://www.nxdjfuw.org.cn/html/001.htm">中国教育后勤协会密函[2014]7号</a><span>|</span></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/002.htm">关于我们<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/003.htm">服务条款<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/004.htm">友情链接<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/005.htm">广告服务<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/006.htm">法律声明<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/007.htm">联系我们<span>|</span></a></li>
                <li><a href="http://www.nxdjfuw.org.cn/html/008.htm">帮助中心<span>|</span></a></li>
                <li><a href="http://www.bjzllh.com/join.php">诚聘精英</a></li>
            </ul>
            <div class="clear"></div>
        	<p>支持单位：教育部 农业部 商务部 中华全国供销合作总社 主办单位：中国教育后勤协会</p>
        	<p>地址：北京市海淀区上园村3号北京交通大学科技大厦1402室</p>
        	<p>中联联合投资控股集团有限公司版权所有©京ICP证：010026 海淀公安局网络备案编号：京公网安备 11010802010990</p>
        	<p><a href="http://www.nxdjfuw.org.cn/html/010.htm">电信与信息服务业务经营许可证 140335</a></p>
        	<p>电话：010-62513620 邮箱：zhouyi@nxdjfuw.org.cn</p>
        </div>
</div> 
   
   
   
   
  </div>  
  <div class="modal fade" id="goodlook" role="dialog" aria-labelledby="gridSystemModalLabel" data-backdrop="static">
  	<div class="modal-dialog tanchu-box" role="document" style="width:70%"; >
	<div class="container-fluid container-margin">
	<div class="panel panel-default ">
	
	<div class="panel-heading box-shodm modal-draggable">
	
	商品详情
	
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeQuote();">&times;</button>
	<input type="hidden" id="goodId" />
	</div>
	<div class="row wrapper form-margin"  style="margin:15px;">
	
	<div class="panel-group" id="accordion">
	<div class="panel panel-default">
	<div class="panel-heading">
	<h4 class="panel-title">
	<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
	商品详情信息
	</a>
	</h4>
	</div>
	<div id="collapseOne" class="panel-collapse collapse in">
	<div class="panel-body">
	
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >图片:</h5>
	</div>
	<img id="imgPath111" src="" class=" text-left detail-margin-left2" style="height:70px; width:70px;">
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >名称:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="goodName111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >种类:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="categoryName111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >产地:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="createAddress111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >生产产家:</h5>
	</div>
	<h5 class="text-left detail-margin-left4" ><p id="factory111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >生产编号:</h5>
	</div>
	<h5 class="text-left detail-margin-left4" ><p id="productNum111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >品牌:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="brand111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >规格:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="specification111"></p></h5>
	</div>
	</div>
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >保质期:</h5>
	</div>
	<h5 class="text-left detail-margin-left3" ><p id="productTime111"></p></h5>
	</div>
	</div>
	
	
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >价格:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="price111"></p></h5>
	</div>
	</div>
	
	
	<div class="col-md-4">
	<div class="input-group">
	<div class="input-group-btn">
	<h5 >库存:</h5>
	</div>
	<h5 class="text-left detail-margin-left2" ><p id="stockNum111"></p></h5>
	</div>
	</div>
	
	</div>
	</div>
	</div>
	
	<div class="panel panel-default">
	<div class="panel-heading">
	<h4 class="panel-title">
	<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
	商品轮播图片
	</a>
	</h4>
	</div>
	<div id="collapseTwo" class="panel-collapse collapse in">
	<div class="panel-body">
	<div class="row wrapper form-margin ">
	<div class="slider" > 
	<ul class="slider-main" id="lunbo333">
	</ul>
	<div class="slider-extra" style="margin: 0px auto; ">
	<ul class="slider-nav" id="lunbo444">
	</ul>
	<div class="slider-page"> 
	<a class="slider-pre" href="javascript:;;"><</a>
	<a class="slider-next" href="javascript:;;">></a>
	</div>
	</div>
	</div>
	</div>
	<div class="row wrapper" >
	<div class="col-md-12" style="text-align: center; margin-bottom:20px;">
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<div class="panel-footer text-right">
	<button class="btn btn-warning btn-s-xs " data-dismiss="modal" onclick="closeQuote();">确定</button>
	</div>
	</div>
	</div>
  
  
  
  </div>
</div>
</body>
</html>
