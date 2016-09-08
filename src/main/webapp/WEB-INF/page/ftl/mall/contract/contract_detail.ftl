<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="save" content="history">
    <title>合作协议详情</title>
    <link href="${ctx}/mall/css/pulic.css" rel="stylesheet">
    <link href="${ctx}/mall/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/mall/css/theme.css" rel="stylesheet">
    <script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css"/>
    <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
    <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
    <script type="text/javascript" src="${ctx}/factoring/js/DatePicker/WdatePicker.js"></script>


    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/common/jqueryValidation/js/jquery.validationEngine.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/jqueryValidation/css/validationEngine.jquery.css"/>
    <script>
        $(function () {

            $("#updateContractForm").validationEngine('attach', {
                scroll: false,
                autoHidePrompt: true,
                autoHideDelay: 2500,
                promptPosition: "bottomLeft"
            });
        });

    </script>


</head>

<body>
<div class="panel panel-default"
     style="margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
    <!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <p>合作协议详情 </p>
        <button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
    </div>
    <!---------------------------------------con top  over--------------------------------------------------------------->
    <div class="A_b_3_in_title" style=" height:30px; background-color:#418bca;  padding-left:50px;">
        <p>业务类型：<#if (type!'')=='1'>采购<#elseif (type!'')=='2'>销售</#if> </p>

        <p>采购方：${mmb.mmbFname!}</p>

        <p>供货方：${mmb1.mmbFname!}</p>

    </div>
    <div class="clear"></div>
    <div style="padding-left:20px; width:60%; float:left; ">
        <form class="form-horizontal" role="form" id="updateContractForm">

            <input type="hidden" id="mtCtrId" value="${mtCtr.id!}" name="id"/>

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">操作人：</label>

                <div class="form-group col-lg-3  input-lg">
                    <input type="text" class="form-control" value="${workerName!}" readonly="readonly" id="firstname"/>
                </div>
                <label for="firstname" class="col-sm-2 control-label">操作时间：</label>

                <div class="form-group col-lg-3  input-lg">
                    <input type="text" class="form-control" id="workerTime" value="javascript:new Date();"
                           readonly="readonly"/>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label ">标题：</label>

                <div class="form-group col-lg-8 input-lg">
                    <input type="text" class="form-control validate[required,maxSize[100]]" id="contractTitle"
                           value="${mtCtr.contractTitle!}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label ">有效时间：</label>

                <div class="form-group col-lg-4  input-lg">
                    <input type="text" class="form-control validate[required]" readonly="readonly" value="${userTime!}"
                           id="userTime" style="behavior:url(#default#savehistory)"/>
                </div>
                <label for="firstname" class="col-sm-2 control-label">结款规则：</label>

                <div class="form-group col-lg-3 input-lg">
                    <select class="form-control validate[required]" id="payType" name="payType">
                        <option value="0" <#if (mtCtr.payType!'')==0>selected="selected"</#if>>每月</option>
                        <option value="1" <#if (mtCtr.payType!'')==1>selected="selected"</#if>>每季</option>
                        <option value="2" <#if (mtCtr.payType!'')==2>selected="selected"</#if>>6个月</option>
                        <option value="3" <#if (mtCtr.payType!'')==3>selected="selected"</#if>>每年</option>
                        <option value="4" <#if (mtCtr.payType!'')==4>selected="selected"</#if>>其他</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">发货规则：</label>

                <div class="form-group col-lg-4  input-lg">
                    <select class="form-control validate[required]" id="flowType" name="flowType">
                        <option value="0" <#if (mtCtr.flowType!'')==0>selected="selected"</#if>>自取</option>
                        <option value="1" <#if (mtCtr.flowType!'')==1>selected="selected"</#if>>免费配送</option>
                        <option value="2" <#if (mtCtr.flowType!'')==2>selected="selected"</#if>>有偿配送（1%）</option>
                    </select>
                </div>
                <label for="firstname" class="col-sm-2 control-label">运输方式：</label>

                <div class="form-group col-lg-3  input-lg">
                    <select class="form-control validate[required]" id="sendgoodsType" name="sendgoodsType">
                        <option value="0" <#if (mtCtr.sendgoodsType!'')==0>selected="selected"</#if>>行运</option>
                        <option value="1" <#if (mtCtr.sendgoodsType!'')==1>selected="selected"</#if>>空运</option>
                    </select>
                </div>
            </div>

        <#if (type!'')== "1">
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">付款账号：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="payerCode">
                        <#if payMoneyBankList ??>
                            <#list payMoneyBankList as bk>
                                <option value="${bk.accountno}"
                                        <#if (mtCtr.payerCode!'')==bk.accountno>selected="selected"</#if>>${bk.bankname}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">收货地址：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="getgoodsAddress">
                        <#if getGoodsAddressList ??>
                            <#list getGoodsAddressList as hc>
                                <option value="${hc.id}"
                                        <#if (mtCtr.getmoneyName!'')==hc.address>selected="selected"</#if>>${hc.address}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">收款账号：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="getmoneyCode" disabled="disabled">
                        <#if getMoneyBankList ??>
                            <#list getMoneyBankList as bk>
                                <option value="${bk.accountno}"
                                        <#if mtCtr.getmoneyCode==bk.accountno>selected="selected"</#if>>${bk.bankname}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">发货地址：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="sendgoodsAddress" disabled="disabled">
                        <#if sendGoodsAddressList ??>
                            <#list sendGoodsAddressList as hc>
                                <option value="${hc.id}" <#if mtCtr.payerName==hc.address>
                                        selected="selected" </#if>>${hc.address}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>


        <#elseif (type!"")== "2">

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">付款账号：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="payerCode" disabled="disabled">
                        <#if payMoneyBankList ??>
                            <#list payMoneyBankList as bk>
                                <option value="${bk.accountno}"
                                        <#if (mtCtr.payerCode!'')==bk.accountno>selected="selected"</#if>>${bk.bankname}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">收货地址：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="getgoodsAddress" disabled="disabled">
                        <#if getGoodsAddressList ??>
                            <#list getGoodsAddressList as hc>
                                <option value="${hc.id}"
                                        <#if (mtCtr.getmoneyName!'')==hc.address>selected="selected"</#if>>${hc.address}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">收款账号：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="getmoneyCode">
                        <#if getMoneyBankList ??>
                            <#list getMoneyBankList as bk>
                                <option value="${bk.accountno}"
                                        <#if mtCtr.getmoneyCode==bk.accountno>selected="selected"</#if>>${bk.bankname}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">发货地址：</label>

                <div class="form-group col-lg-8  input-lg">
                    <select class="form-control validate[required]" id="sendgoodsAddress">
                        <#if sendGoodsAddressList ??>
                            <#list sendGoodsAddressList as hc>
                                <option value="${hc.id}" <#if mtCtr.payerName==hc.address>
                                        selected="selected" </#if>>${hc.address}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
        </#if>

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">已选商品列表：</label>

                <div class="form-group col-lg-8  input-lg">
                    <textarea class="form-control" rows="2" id="categoryList">
                    <#if ctrCtgList ??>
                        <#list ctrCtgList as ctg>
                        ${ctg.categoryName!} &nbsp;
                        </#list>
                    </#if>
                    </textarea>
                </div>
            </div>

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">备注：</label>

                <div class="form-group col-lg-8  input-lg">
                    <textarea class="form-control" rows="3" id="beizhu">${mtCtr.beizhu!}</textarea>
                </div>
            </div>
        </form>
        <div class="clear"></div>
    </div>

</div>
</body>


</html>
