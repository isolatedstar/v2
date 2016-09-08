<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员商品详情</title>
<link href="${ctx}/mall/css/mall.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/mall/css/media.css" rel="stylesheet" type="text/css">
<script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
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
});
</script>
<script>
window.onload = function(){
	var oTxt = document.getElementById('con_sh_input');
	var oBtn = document.getElementById('con_sh_btn');
	
	oTxt.value = '请输入内容'
	oTxt.onfocus = function(){
		this.value = '';
		this.style.color = '#000'
		}
	oBtn.onclick = function(){
		oTxt.value = '';
		}
	}
</script>
<script type="text/javascript"> 
	function showGood(){
			var goodId = '${goodId!}';
			$.ajax({
				url : "${ctx}/GoodController/lookGoodAnonymously.do",
				data : {goodId : goodId},
				type : "POST",
				cache : false,
				dataType : "json",
				success : function(data) {
					data = eval(data);
					if(data!=null){
						$("#goodName111").html("商品名称："+data.name);
				       	   $("#categoryName111").html("品类:"+data.categoryName);
				       	   $("#createAddress111").html("生产地址:"+data.createAddress);
				       	   $("#factory111").html("生产厂家:"+data.factory);
				       	  
				       	   var pp ="";
				       	   if(data.unitSpecification=='1'){
				       		   pp="千克";
				       	   }else{
				       		   pp="克";
				       	   }
				       	   $("#specification111").html("规格:"+data.specification+","+pp);
				       	    $("#described111").html("说明:"+data.described);
				       	   $("#productTime111").html(data.productTime);
				       	   var price = data.minPrice+data.unitPrice+"--"+data.maxPrice+data.unitPrice;
				       	   $("#price111").html("价格:"+price);
				       	   $("#stockNum111").html("库存:"+data.stockNum);
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
	function aa(){
		 		length = $('.slider-panel').length; 
				  
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
 
 var length, 
   currentIndex = 0, 
   interval, 
   hasStarted = false, //是否已经开始轮播 
    t = 3000; //轮播时间间隔 
 
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
  
 $(document).ready(function() { 
  
 }); 
 	$(function(){
			//初始化
			showGood();
	});
 
</script> 

</head>

<body>
<div id="mallwarp" >
<!----------------------header start--------------------------->
	<div class="m_header">
    	<img src="${ctx}/mall/images/logo.png"/>
        <h2>农校对接</h2>
       
    </div>
<!----------------------header over--------------------------->
<div class="m_con_box">
		<!------------------------------------------nav start----------------------------------------------------------->
    
</div>
<!------------------------------------------nav over------------------------------------------------------------>
<!------------------------------------------conter bot start------------------------------------------------------------>
<div class="content_3_warp">
    <div class="slider"> 
      <ul class="slider-main" id="lunbo333"> 
       <li class="slider-panel"> 
        
      </ul> 
      <div class="slider-extra"> 
       <ul class="slider-nav" id="lunbo444"> 
        
       </ul> 
       <div class="slider-page"> 
        <a class="slider-pre" href="javascript:;;"><</a> 
        <a class="slider-next" href="javascript:;;">></a> 
       </div> 
      </div> 
     </div> 
     <div class="content_3_txt">
        <p id="goodName111"></p>
        <p id="specification111"></p>
        <p id="price111"></p>
        <p id="stockNum111"></p>
    </div>
<div class="content_3_bot">
	
	<p id="categoryName111"></p>
	<p id="createAddress111"></p>
	<p id="factory111"></p>
	<p id="described111">* 说明：每批20袋以内价格125；100袋以内价格118；100袋以上价格110。北京五环内送货上门，以外地区自行运输。</p>
</div>
</div>
<div class="clear"></div>
<!------------------------------------------conter bot over------------------------------------------------------------> 
  </div>  
</div>
</body>
</html>
