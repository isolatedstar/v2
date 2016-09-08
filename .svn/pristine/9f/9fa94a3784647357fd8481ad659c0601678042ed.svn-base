$.extend($.fn.validatebox.defaults.rules, {
    range:{
      validator:function(value,param){
        if(/^[1-9]\d*$/.test(value)){
          return value >= param[0] && value <= param[1]
        }else{
          return false;
        }
      },
      message:'输入的数字在{0}到{1}之间'
    }
  });