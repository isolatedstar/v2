
function getactiveIndex(pageNo,li){
	
	//取当前class = active的li下标
	li.each(function(i,element){
		var clas = $(element).attr("class");
		if(clas != undefined){
			if(clas.indexOf("active") > -1){
				pageNo = parseInt($(element).find("a").html());
			}
		}
	})
	return pageNo;
}

/**
 * 获取当前页
 * **/
function getPageNo(pageNo,index,txt){
	if(txt == "&gt;&gt;"){
		 pageNo += 1;
		if(index == pageNo){
			$(this).parent().find('li:eq('+index+')').addClass('disabled');
			pageNo = index - 1;
	 	}else{
	 		$(this).parent().find('li:eq('+pageNo+')').addClass('active').siblings().removeClass();
	 	}
	 }else if(txt == "&lt;&lt;"){
	 	pageNo -= 1;
	 	if(index == pageNo && index == 0){
	 		$(this).parent().find('li:eq(1)').addClass('active').siblings().removeClass();
	 		$(this).parent().find('li:eq('+index+')').addClass('disabled');
	 		pageNo = index+1;
	 	}else{
	 	 	$(this).parent().find('li:eq('+pageNo+')').addClass('active').siblings().removeClass();
	 	}
	 }
	 else{
	 	pageNo = index;
	 	$(this).parent().find('li:eq('+index+')').addClass('active').siblings().removeClass();
	 }
	
	return pageNo;
}