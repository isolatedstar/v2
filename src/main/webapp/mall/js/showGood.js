function goodDetail(modalid,baseurl,callback){
	if(!modalid || typeof(modalid)!=='string'&&modalid.trim()!==''){
		throw new Error("modalid不能为空！");
	}
	
	if(!baseurl || typeof(baseurl)!=='string'&&baseurl.trim()!==''){
		throw new Error("baseurl不能为空！");
	}
	this._modalid = "#"+modalid;
	
	this._baseurl = baseurl;
	this._callback = callback;
	this.init.call(this);
}
goodDetail.prototype.init=function(){
	var html = [];
	html.push('<div class="modal-dialog tanchu-box" role="document" style="width:70%"; data-backdrop="static">');
	html.push('<div class="container-fluid container-margin">');
	html.push('<div class="panel panel-default ">');
	
	html.push('<div class="panel-heading box-shodm modal-draggable">');
	
	html.push('商品详情');
	
	html.push('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>');
	html.push('<input type="hidden" id="goodId" />');
	html.push('</div>');
	html.push('<div class="row wrapper form-margin"  style="margin:15px;">');
	
	html.push('<div class="panel-group" id="accordion">');
	html.push('<div class="panel panel-default">');
	html.push('<div class="panel-heading">');
	html.push('<h4 class="panel-title">');
	html.push('<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">');
	html.push('商品详情信息');
	html.push('</a>');
	html.push('</h4>');
	html.push('</div>');
	html.push('<div id="collapseOne" class="panel-collapse collapse in">');
	html.push('<div class="panel-body">');
	
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >图片:</h5>');
	html.push('</div>');
	html.push('<img id="imgPath111" src="" class=" text-left detail-margin-left2" style="height:70px; width:70px;">');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >名称:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="goodName111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >种类:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="categoryName111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >产地:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="createAddress111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >生产产家:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left4" ><p id="factory111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >生产编号:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left4" ><p id="productNum111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">');
	html.push('<h5 >品牌:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="brand111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">')
	html.push('<h5 >规格:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="specification111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">')
	html.push('<h5 >保质期:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left3" ><p id="productTime111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	
	
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">')
	html.push('<h5 >价格:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="price111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	
	
	html.push('<div class="col-md-4">');
	html.push('<div class="input-group">');
	html.push('<div class="input-group-btn">')
	html.push('<h5 >库存:</h5>');
	html.push('</div>');
	html.push('<h5 class="text-left detail-margin-left2" ><p id="stockNum111"></p></h5>');
	html.push('</div>');
	html.push('</div>');
	
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	
	html.push('<div class="panel panel-default">');
	html.push('<div class="panel-heading">');
	html.push('<h4 class="panel-title">');
	html.push('<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">');
	html.push('商品轮播图片');
	html.push('</a>');
	html.push('</h4>');
	html.push('</div>');
	html.push('<div id="collapseTwo" class="panel-collapse collapse in">');
	html.push('<div class="panel-body">');
	html.push('<div class="row wrapper form-margin ">');
	html.push('<div class="slider" > ');
	html.push('<ul class="slider-main" id="lunbo333">');
	html.push('</ul>');
	html.push('<div class="slider-extra" style="margin: 0px auto; ">');
	html.push('<ul class="slider-nav" id="lunbo444"> ');
	html.push('</ul>');
	html.push('<div class="slider-page"> ');
	html.push('<a class="slider-pre" href="javascript:;;"><</a>');
	html.push('<a class="slider-next" href="javascript:;;">></a> ');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="row wrapper" >');
	html.push('<div class="col-md-12" style="text-align: center; margin-bottom:20px;">');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	html.push('<div class="panel-footer text-right">');
	html.push('<button class="btn btn-warning btn-s-xs " data-dismiss="modal">确定</button>');
	html.push('</div>');
	html.push('</div>');
	html.push('</div>');
	$(this._modalid).append(html.join(''));
};

goodDetail.prototype.showGoodDetail=function(goodId){
	var _self = this;
	$.ajax({
		url : _self._baseurl+"/GoodController/lookGood.do",
		data : {goodId : goodId},
		type : "POST",
		cache : false,
		dataType : "json",
		success : function(data) {
			data = eval(data);
			if(data!=null){
				
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
									 var path = _self._baseurl+  data.imglist[i].materialPath ;
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
			
			   	 //	_self._callback();
			   	 	
			   	 var length;
				 var  currentIndex = 0; 
				  var interval; 
				  var hasStarted = false; //是否已经开始轮播 
		   		  var t = 3000; //轮播时间间隔 
				
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
							  clearInterval(interval); 
							   hasStarted = false; 
						   $('.slider-page').show(); 
						  }, function() { 
						   $('.slider-page').hide(); 
						   if(!hasStarted) { 
							    hasStarted = true; 
							    interval = setInterval(next, t); 
							   }  
						  }); 
						  $('.slider-item').hover(function(e) { 
							  clearInterval(interval); 
							   hasStarted = false; 
						   var preIndex = $(".slider-item").filter(".slider-item-selected").index(); 
						   currentIndex = $(this).index(); 
						   $('.slider-panel').eq(preIndex).fadeOut(500) 
						    .parent().children().eq(currentIndex).fadeIn(500); 
						   $('.slider-item').removeClass('slider-item-selected'); 
						   $('.slider-item').eq(currentIndex).addClass('slider-item-selected');
						  }, function() { 
							  if(!hasStarted) { 
								    hasStarted = true; 
								    interval = setInterval(next, t); 
								   }  
						  }); 
						  $('.slider-pre').unbind('click'); 
						  $('.slider-pre').bind('click', function() { 
							  var preIndex = currentIndex; 
							   currentIndex = (--currentIndex + length) % length; 
							   $('.slider-panel').eq(preIndex).fadeOut(500) 
							    .parent().children().eq(currentIndex).fadeIn(500); 
							   $('.slider-item').removeClass('slider-item-selected'); 
							   $('.slider-item').eq(currentIndex).addClass('slider-item-selected');  
						  }); 
						  $('.slider-next').unbind('click'); 
						  $('.slider-next').bind('click', function() { 
							  var preIndex = currentIndex; 
							   currentIndex = ++currentIndex % length; 
							   $('.slider-panel').eq(preIndex).fadeOut(500) 
							    .parent().children().eq(currentIndex).fadeIn(500); 
							   $('.slider-item').removeClass('slider-item-selected'); 
							   $('.slider-item').eq(currentIndex).addClass('slider-item-selected'); 
						  }); 
				 
			   	 	}
			}
		},
		error : function(data) {
			alert("错误!!!");
		}
	});
};