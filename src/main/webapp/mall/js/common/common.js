$(function(){
	$("#header").load("header.html");
	$("#sideleft").load("menu.html");
	$("#footer").load("footer.html");
	
	$("#content").css("min-height",$(window).height()-$("#header").height()-82);
	
	if ($.support.msie) {
		if ($.support.version == "6.0") {
			$("#frame_content").width($(window).width() - 250);
			$(window).resize(function(){
				$("#frame_content").width($(window).width() - 254);
			})
		}
	}
	
	$("#query").click(function(){
		$("form").submit()
	})
	//刷新
	$(".pReload").click(function() {
		window.location.reload();
});
	//全选
	$("#allSelBox").bind('click',function(){
		var flag = $(this).attr("checked");
		if(flag == undefined){
			flag = false;
		}
		$("input[type='checkbox']").attr("checked",flag);
	})
	//统计每页显示多少条记录
	function countRecordEveryPage(){
		$("input[name='pageSize']").val($("#pageRecordNum").val());
	}
	//首页
	$(".pFirst").click(function(){
		$("input[name='pageNo']").val(1);
		countRecordEveryPage();
		$("form").submit();
	})
	//尾页
	$(".pLast").click(function(){
		var totalPage = $("#totalPage").text();
		$("input[name='pageNo']").val(totalPage);
		countRecordEveryPage();
		$("form").submit();
	})
	//下一页
	$(".pNext").click(function(){
		//当至尾页时,将[下一页按钮变至disabled]    
		var currentPage = $("#currentPage").val();
		var totalPage = $("#totalPage").text();
		if(currentPage == totalPage){
			$(".pNext.pButton").attr("disabled","disabled")
			return false;
		}
		var currentPage = $("#currentPage").val();
		currentPage = parseInt(currentPage) + 1;
		$("input[name='pageNo']").val(currentPage);
		countRecordEveryPage();
		$("#currentPage").val(currentPage);
		$("form").submit();
	})
	//上一页
	$(".pPrev").click(function(){
		//当至首页时,将[上一页按钮变至disabled]    
		var currentPage = $("#currentPage").val();
		if(currentPage == 1){
			$(".pPrev.pButton").attr("disabled","disabled")
			return false;
		}
		var currentPage = $("#currentPage").val();
		currentPage = parseInt(currentPage) - 1;
		$("input[name='pageNo']").val(currentPage);
		countRecordEveryPage();
		$("form").submit();
	})
	//每页显示记录条数改变时触发Ajax请求
	$("#pageRecordNum").change(function(){
		var currentPage = $("#currentPage").val();
		$("#page_pageNo").val(1);
		$("#page_pageSize").val($("#pageRecordNum").val());
		$("form").submit();
	})
	//修改
	$("#modify").click(function(){
		var len = $("input:checked").length;
		var param = $("input[id != 'allSelBox']:checked").val();
	 	if(len == 0){
	 		alert("你还没有选择要修改的数据呢！！");
	 		return false;
	 	}
	 	if(len > 1){
	 		alert("只能选择一行哟！");
			return false;
	 	}
		window.location.href = $("#reqUrl").val() + param;
	})
	//删除
	$("#delete").click(function(){
		var isSelect = "";
		isSelect = $("#cb:checked").map(function(){
			return this.value;
		}).get().join(",");
		if (isSelect != "") {
			if(confirm("确定要删除选中的数据吗?")){
				window.location.href = $("#reqUrlDel").val() + isSelect;
			}
		}
		else {
			alert("你没有选择任何记录！！");
		}
	})
	
	//页面跳转  by zhanghu 2014.4.21
	$('#currentPage').keypress(function(event){  
	    var keycode = (event.keyCode ? event.keyCode : event.which);  
	    if(keycode == '13'){
	    	var str = $("#currentPage").val();
	    	if(!isNaN(str)){
	    			var currentPage = parseInt($("#currentPage").val());
	    			var totalPage = parseInt($("#totalPage").text());
	    			if(currentPage<1){
	    				currentPage = 1;
	    			}else if(currentPage>totalPage){
	    				currentPage = totalPage;
	    			}
	    			
	    			$("input[name='pageNo']").val(currentPage);
	    			countRecordEveryPage();
	    			$("form").submit();
	    		}else{
	    		  alert("请输入正确的页码！");
	    		  this.select();
	    		  return;
	    	}
	    }  
	});


	/** 拖拽模态框*/
	var mouseStartPoint = {"left":0,"top":  0};
	var mouseEndPoint = {"left":0,"top":  0};
	var mouseDragDown = false;
	var oldP = {"left":0,"top":  0};
	var moveTartet ;
	$(document).on("mousedown",".modal-draggable",function(e){
		if($(e.target).hasClass("close"))//点关闭按钮不能移动对话框
			return;
		mouseDragDown = true;
		moveTartet = $(this).parent();
		mouseStartPoint = {"left":e.clientX,"top":  e.clientY};
		oldP = moveTartet.offset();
	});
	$(document).on("mouseup",function(e){
		mouseDragDown = false;
		moveTartet = undefined;
		mouseStartPoint = {"left":0,"top":  0};
		oldP = {"left":0,"top":  0};
	});
	$(document).on("mousemove",function(e){
		if(!mouseDragDown || moveTartet == undefined)return;
		var mousX = e.clientX;
		var mousY = e.clientY;
		if(mousX < 0)mousX = 0;
		if(mousY < 0)mousY = 25;
		mouseEndPoint = {"left":mousX,"top": mousY};
		var width = moveTartet.width();
		var height = moveTartet.height();
		mouseEndPoint.left = mouseEndPoint.left - (mouseStartPoint.left - oldP.left);//移动修正，更平滑
		mouseEndPoint.top = mouseEndPoint.top - (mouseStartPoint.top - oldP.top);
		moveTartet.offset(mouseEndPoint);
	});

})
