	//扩展Date的format方法   
    Date.prototype.format = function (format) {  
        var o = {  
            "M+": this.getMonth() + 1,  
            "d+": this.getDate(),  
            "h+": this.getHours(),  
            "m+": this.getMinutes(),  
            "s+": this.getSeconds(),  
            "q+": Math.floor((this.getMonth() + 3) / 3),  
            "S": this.getMilliseconds()  
        }  
        if (/(y+)/.test(format)) {  
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(format)) {  
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return format;  
    }  
    /**   
    *转换日期对象为日期字符串   
    * @param date 日期对象   
    * @param isFull 是否为完整的日期数据,   
    *               为true时, 格式如"2000-03-05 01:05:04"   
    *               为false时, 格式如 "2000-03-05"   
    * @return 符合要求的日期字符串   
    */    
    function getSmpFormatDate(date, isFull) {  
        var pattern = "";  
        if (isFull == true || isFull == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        } else {  
            pattern = "yyyy-MM-dd";  
        }  
        return getFormatDate(date, pattern);  
    }  
    /**   
    *转换当前日期对象为日期字符串   
    * @param date 日期对象   
    * @param isFull 是否为完整的日期数据,   
    *               为true时, 格式如"2000-03-05 01:05:04"   
    *               为false时, 格式如 "2000-03-05"   
    * @return 符合要求的日期字符串   
    */    
  
    function getSmpFormatNowDate(isFull) {  
        return getSmpFormatDate(new Date(), isFull);  
    }  
    /**   
    *转换long值为日期字符串   
    * @param l long值   
    * @param isFull 是否为完整的日期数据,   
    *               为true时, 格式如"2000-03-05 01:05:04"   
    *               为false时, 格式如 "2000-03-05"   
    * @return 符合要求的日期字符串   
    */    
  
    function getSmpFormatDateByLong(l, isFull) {  
        return getSmpFormatDate(new Date(l), isFull);  
    }  
    /**   
    *转换long值为日期字符串   
    * @param l long值   
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
    * @return 符合要求的日期字符串   
    */    
  
    function getFormatDateByLong(l, pattern) {  
        return getFormatDate(new Date(l), pattern);  
    }  
    /**   
    *转换日期对象为日期字符串   
    * @param l long值   
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
    * @return 符合要求的日期字符串   
    */    
    function getFormatDate(date, pattern) {  
        if (date == undefined) {  
            date = new Date();  
        }  
        if (pattern == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        }  
        return date.format(pattern);  
    }
    
    /**   
     * 全选反选
     * @param l long值   
     * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
     * @return 符合要求的日期字符串   
     */ 
    function checkAllBox(obj){
		$(obj).parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,thisElement){
			if($(obj).prop("checked")==true){
		      $(thisElement).prop("checked",'true');
			}else{
				$(thisElement).prop("checked",false);
			}
		})
    }
	
    function forLowerIdCheck(obj){
	   var forLowerCheckbox;
       $(obj).parent().parent().parent().parent().find("[name = forLowerId]:checkbox").each(function(i,thisElement){
         if($(thisElement).prop("checked")==false){
            // alert($(obj).parent().parent().parent().parent().find("[name = checkAll]").prop("checked"));
                forLowerCheckbox = 0;
				return false;
			}
			forLowerCheckbox = 1;
			return true;
  		 });
      if(forLowerCheckbox==0){
         $(obj).parent().parent().parent().parent().find("[name = checkAll]").prop("checked",false);
      }else{
         $(obj).parent().parent().parent().parent().find("[name = checkAll]").prop("checked",true);
      }
   }


	/** 拖拽模态框*/
	var mouseStartPoint = {"left":0,"top":  0};
	var mouseEndPoint = {"left":0,"top":  0};
	var mouseDragDown = false;
	var oldP = {"left":0,"top":  0};
	var moveTartet ;
	$(document).ready(function(){
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
	});



	(function($){
    	var ms = {
    		init:function(obj,args){
    			return (function(){
    				ms.fillHtml(obj,args);
    				ms.bindEvent(obj,args);
    			})();
    		},
    		fillHtml:function(obj,args){
    			return (function(){
    				obj.empty();
    				if(args.current > 1){
    					obj.append('<li><a href="javascript:;" class="prevPage">上一页</a></li>');
    				}else{
    					obj.remove('.prevPage');
    					obj.append('<li class="prev disabled"><span class="disabled">上一页</span></li>');
    				}
    				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
    					obj.append('<li><a href="javascript:;" class="tcdNumber">'+1+'</a></li>');
    				}
    				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
    					obj.append('<li><span>...</span></li>');
    				}
    				var start = args.current -2,end = args.current+2;
    				if((start > 1 && args.current < 4)||args.current == 1){
    					end++;
    				}
    				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
    					start--;
    				}
    				for (;start <= end; start++) {
    					if(start <= args.pageCount && start >= 1){
    						if(start != args.current){
    							obj.append('<li><a href="javascript:;" class="tcdNumber">'+ start +'</a></li>');
    						}else{
    							obj.append('<li class = "active"><span class="current">'+ start +'</span></li>');
    						}
    					}
    				}
    				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
    					obj.append('<li><span>...</span></li>');
    				}
    				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
    					obj.append('<li><a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a></li>');
    				}
    				//涓嬩竴椤�
    				if(args.current < args.pageCount){
    					obj.append('<li><a href="javascript:;" class="nextPage">下一页</a></li>');
    				}else{
    					obj.remove('.nextPage');
    					obj.append('<li class="prev disabled"><span class="disabled">下一页</span></li>');
    				}
    			})();
    		},
    		bindEvent:function(obj,args){
    			return (function(){
    				obj.on("click","a.tcdNumber",function(){
    					var current = parseInt($(this).text());
    					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
    					if(typeof(args.backFn)=="function"){
    						args.backFn(current);
    					}
    				});
    				obj.on("click","a.prevPage",function(){
    					var current = parseInt(obj.children().children("span.current").text());
    					ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
    					if(typeof(args.backFn)=="function"){
    						args.backFn(current-1);
    					}
    				});
    				obj.on("click","a.nextPage",function(){
    					var current = parseInt(obj.children().children("span.current").text());
    					ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
    					if(typeof(args.backFn)=="function"){
    						args.backFn(current+1);
    					}
    				});
    			})();
    		}
    	}
    	$.fn.createPage = function(options){
    		var args = $.extend({
    			pageCount : 10,
    			current : 1,
    			backFn : function(){}
    		},options);
    		ms.init(this,args);
    	}
    	
    	var ajax=$.ajax;
        $.ajax=function(s){
            var old=s.error;
            var errHeader=s.errorHeader||"Error-Json";
            s.error=function(xhr,status,err){
                var errMsg = xhr.getResponseHeader(errHeader);
                var obj = eval("("+errMsg+")");
                if(obj.code==302){
                	window.location.href=obj.url;
                }
            }
            ajax(s);
        }
    	
    })(jQuery);
