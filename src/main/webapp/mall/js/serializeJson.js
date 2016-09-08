(function($) {
	/**
	 * 表单序列化，可以支持下拉列表等的序列化
	 */
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};
	/**
	 * 校验输入金额
	 */
	$.fn.validateAmount = function() {
		var that = this;
		var regStrs = [
		               ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
		               ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
		               ['^\\.', '0.'],      //禁止录入小数点开始的数字
		               ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
		               ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
		           ];
		$.each(regStrs, function(index, item) {
			var reg = new RegExp(item[0]);
			that.val(that.val().replace(reg, item[1]));
		});
		return that;
	};
	/**
	 * 校验整数
	 */
	$.fn.validateNumber = function() {
		var that = this;
		var regStrs = [
		               ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
		               ['[^\\d]+$', ''], //禁止录入任何非数字
		           ];
		$.each(regStrs, function(index, item) {
			var reg = new RegExp(item[0]);
			that.val(that.val().replace(reg, item[1]));
		});
		return that;
	};
	/**
	 * Unix时间戳转换成日期
	 */
	$.changeDate = function(value) {
		var time = new Date(value);
		var ymdhis = "";
		ymdhis += time.getFullYear() + "-";
		if(time.getMonth()+1<10){
			ymdhis += "0"+(time.getMonth()+1) + "-";
		}else{
			ymdhis += (time.getMonth()+1) + "-";
		}
		if(time.getDate()<10){
			ymdhis += "0"+time.getDate();
		}else{
			ymdhis += time.getDate();
		}
//		if(time.getHours()<10){
//			ymdhis += " 0" + time.getHours() + ":";
//		}else{
//			ymdhis += " " + time.getHours() + ":";
//		}
//		if(time.getMinutes()<10){
//			ymdhis += "0"+time.getMinutes() + ":";
//		}else{
//			ymdhis += time.getMinutes() + ":";
//		}
//		if(time.getSeconds()<10){
//			ymdhis += "0"+time.getSeconds();
//		}else{
//			ymdhis += time.getSeconds();
//		}
		return ymdhis;
	};

	$.changeTime = function(value) {
		var time = new Date(value);
		var ymdhis = "";
		ymdhis += time.getFullYear() + "-";
		if(time.getMonth()+1<10){
			ymdhis += "0"+(time.getMonth()+1) + "-";
		}else{
			ymdhis += (time.getMonth()+1) + "-";
		}
		if(time.getDate()<10){
			ymdhis += "0"+time.getDate();
		}else{
			ymdhis += time.getDate();
		}
		if(time.getHours()<10){
			ymdhis += " 0" + time.getHours() + ":";
		}else{
			ymdhis += " " + time.getHours() + ":";
		}
		if(time.getMinutes()<10){
			ymdhis += "0"+time.getMinutes() + ":";
		}else{
			ymdhis += time.getMinutes() + ":";
		}
		if(time.getSeconds()<10){
			ymdhis += "0"+time.getSeconds();
		}else{
			ymdhis += time.getSeconds();
		}
		return ymdhis;
	};
})(jQuery);