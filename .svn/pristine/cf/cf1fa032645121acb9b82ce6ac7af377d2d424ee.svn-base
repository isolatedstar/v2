/**
 * 显示合作协议详情页面 *
 * @param modalId 页面模态框id
 * @param ctrId  协议ID
 * @param contractType 协议类型
 * @param url 协议详情URL
 * Created by xxx on 2016/7/28.
 */
function contractDetail(modalId,ctrId,contractType,url){
    if(modalId.trim() =="" || typeof(modalId)!="string" ||modalId == null){
        throw new Error ("modalId不能为空！");
    }
    if(ctrId.trim() =="" || typeof(ctrId)!="string" ||ctrId == null){
        throw new Error ("协议ID不能为空！");
    }
    if(contractType.trim() =="" || typeof(contractType)!="string" ||contractType == null){
        throw new Error ("协议类型不能为空！");
    }
    this._modalId = "#"+modalId;
    this._ctrId = ctrId;
    this._contractType = contractType;
    this._url = url;

    this.init.call(this);

}

contractDetail.prototype.init = function(){
    var html = [];
    html.push('<div class="modal-dialog m-tanchu-box" role="document">');
    html.push('<div class="container-fluid" style="margin-top:15px;">');
    html.push('<div class="row-fluid"> ');
    html.push('<div class="col-sm-12 ">');
    html.push('<div class="panel panel-default article-bj"> ');
    html.push('<div class="panel-heading box-shodm modal-draggable">');
    html.push('协议详情 ');
    html.push('<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button> ');
    html.push('</div>');
    html.push('<div class="row wrapper form-margin">');
    html.push(' <div class="col-md-4"> ');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin" id="contractTypeInfo"></h5>');
    html.push('</div>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-4">');
    html.push('<div class="input-group">');
    html.push('<div class="input-group-btn"> ');
    html.push('<h5 class="h5-margin" id="buyer"></h5> ');
    html.push('</div>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-4">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin" id="seller"></h5> ');
    html.push(' </div>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">操作人:</h5>');
    html.push(' </div>');
    html.push(' <input type="text" placeholder="" class="form-control"  id="workerName1" readonly="readonly"/> ');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">操作时间:</h5>');
    html.push(' </div>');
    html.push(' <input type="text"  class="form-control"  id="workTime1"  readonly="readonly"/> ');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">标题:</h5> ');
    html.push(' </div>');
    html.push(' <input type="text" placeholder="" class="form-control"  id="contractTitle1" readonly="readonly"/>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">有效时间:</h5>');
    html.push(' </div>');
    html.push(' <input type="text" placeholder="" class="form-control"  id="userTime1" readonly="readonly"/>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">结款规则:</h5>');
    html.push(' </div>');
    html.push(' <select name="selecter_basic" tabindex="-1" class="form-control" id="payType1" disabled="disabled"> ');
    html.push(' </select>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">发货规则:</h5>');
    html.push(' </div>');
    html.push(' <select name="selecter_basic" tabindex="-1"class="form-control" id="flowType1" disabled="disabled"> ');
    html.push(' </select>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">运输方式:</h5>');
    html.push(' </div>');
    html.push(' <select name="selecter_basic" tabindex="-1"class="form-control" id="sendgoodsType1" disabled="disabled"> ');
    html.push(' </select>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">付款账号:</h5>');
    html.push(' </div>');
    html.push(' <select name="selecter_basic" tabindex="-1"class="form-control" id="payerCode1" disabled="disabled"> ');
    html.push(' </select>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-6">');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">收货地址:</h5>');
    html.push(' </div>');
    html.push(' <select name="selecter_basic" tabindex="-1"class="form-control" id="getgoodsAddress1" disabled="disabled"> ');
    html.push(' </select>');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-12"> ');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">已选商品列表:</h5>');
    html.push(' </div>');
    html.push(' <textarea  class="form-control" rows="2" style="width:100%" id="ctgList1" readonly="readonly"></textarea> ');
    html.push('</div>');
    html.push('</div>');
    html.push('<div class="col-md-12"> ');
    html.push('<div class="input-group">');
    html.push(' <div class="input-group-btn">');
    html.push('<h5 class="h5-margin">备注:</h5> ');
    html.push(' </div>');
    html.push(' <textarea  class="form-control" rows="3" style="width:100%" id="beizhu1" readonly="readonly"></textarea> ');
    html.push('</div>');
    html.push('</div>');
    html.push(' </div>');
    html.push('<footer class="panel-footer text-right bg-light lter">');
    html.push('<button class="btn btn-warning btn-s-xs" type="submit" data-dismiss="modal">关闭</button> ');
    html.push('</footer>');
    html.push('</div>');
    html.push(' </div>');
    html.push('</div>');
    html.push('</div>');
    html.push('</div>');
    html.push('</div>');
    html.push('</div>');

    $("#contractDetail").html("");

    $("#contractDetail").append(html.join(''));

    this.initData.call(this);
}

contractDetail.prototype.initData = function(){
    $.ajax({
        url: this._url,// 跳转到 action
        type: 'POST',
        cache: false,
        data: {
            id: this._ctrId,
            contractType : this._contractType
        },
        dataType: 'json',
        success: function (data) {

            if(data != "" && data != null){


                var mtCtr = data.mtCtr;
                var ctrCtgList = data.ctrCtgList;

                var type = data.type == 1 ? "采购":"销售";
                $("#contractTypeInfo").text("协议类型："+type);
                $("#buyer").text("采购方:"+data.buyer.mmbFname);
                $("#seller").text("供货方:"+data.seller.mmbFname);
                $("#workerName1").val(mtCtr.operateUserName);


                $("#workTime1").val($.changeTime(mtCtr.operateTime));
                $("#contractTitle1").val(mtCtr.contractTitle);
                $("#userTime1").val(data.userTime);

                var payTypeOption,flowTypeOption,sendgoodsTypeOption;
                if(mtCtr.payType == 0){
                    payTypeOption = ' <option value="0">每月</option>' ;
                }else if(mtCtr.payType == 1){
                    payTypeOption = '  <option value="1">每季</option> ' ;
                }else if(mtCtr.payType == 2){
                    payTypeOption = ' <option value="2" >6个月</option>' ;
                }else if(mtCtr.payType == 3){
                    payTypeOption = '<option value="3">每年</option>' ;
                }else if(mtCtr.payType == 4){
                    payTypeOption = '<option value="4">其他</option>' ;
                }

                $("#payType1").append(payTypeOption);

                if(mtCtr.flowType == 0){
                    flowTypeOption = ' <option value="0">自取</option>' ;
                }else if(mtCtr.flowType == 1){
                    flowTypeOption = '  <option value="1">免费配送</option> ' ;
                }else if(mtCtr.flowType == 2){
                    flowTypeOption = ' <option value="2" >有偿配送（1%）</option>' ;
                }

                $("#flowType1").append(flowTypeOption);

                if(mtCtr.sendgoodsType == 0){
                    sendgoodsTypeOption = '<option value="0">行运</option>' ;
                }else if(mtCtr.sendgoodsType == 1){
                    sendgoodsTypeOption = '<option value="1">空运</option> ' ;
                }

                $("#sendgoodsType1").append(sendgoodsTypeOption);

                $("#payerCode1").append('<option value="0">'+mtCtr.payerName+'</option>');
                $("#getgoodsAddress1").append('<option value="0">'+mtCtr.getgoodsAddress+'</option>');

                $("#getmoneyCode1").append('<option value="0">'+mtCtr.getmoneyName+'</option>');
                $("#sendgoodsAddress1").append('<option value="0">'+mtCtr.sendgoodsAddress+'</option>');

                if(ctrCtgList.length > 0){
                    var ctrCtg = "";
                    for(var i =0;i <ctrCtgList.length;i++ ){
                        ctrCtg += ctrCtgList[i].categoryName+",";
                    }
                    ctrCtg = ctrCtg.substr(0,ctrCtg.length-1);

                    $("#ctgList1").val(ctrCtg);
                }


                $("#beizhu1").val(mtCtr.beizhu);

                $("#contractDetail").modal("show");
            }

        },
        error: function () {
            alert("异常！");
        }
    });
}
