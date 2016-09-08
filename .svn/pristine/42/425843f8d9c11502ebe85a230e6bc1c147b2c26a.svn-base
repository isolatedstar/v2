<#assign sec=JspTaglibs["/WEB-INF/security.tld"] />   <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>无标题文档</title>
    <link href="${ctx}/mall/css/pulic.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/mall/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${ctx}/mall/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/mall/js/extendPagination.js"></script>
    <style>
        .table-responsive {
            width: 60%;
            float: left;
            margin-left: 5%;
            padding: 0 0 0 20px;
        }


    </style>
    <script language="javascript">
        $(document).ready(function (e) {
            $('.A_b_2_nav_left>li').click(function () {
                alert("111");
                /*$('.A_b_2_subnav').slideUp()*/
                $(this).next('ul').stop().slideToggle();
            });
            $('.A_b_2_subnav li a').click(function () {
                $('.A_b_2_subnav li a').css('color', '#333')
                $(this).css('color', '#4aa3df');
                $($(this).attr('href')).show();
            });

            //获取当前会员id
            var mmbId = $("#mmbId").val();
            if (mmbId != "") {
                $.ajax({
                    url: '${ctx}/contract/queryAllRelaMmbsByMmbId.do',// 跳转到 action
                    data: {
                        mmbId: mmbId
                    },
                    type: 'POST',
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        //先清空ul中信息
                        $("#mmbsUl").empty();
                        //返回数据处理
                        if (data != "") {
                            var content = ""
                            for (var i = 0; i < data.length; i++) {
                                content += "<li onclick='changeDisplay(this)'>" + data[i].fname + "</li>";
                                content += "<ul class='A_b_2_subnav' style='display: none;'>";
                                content += "<li><a href='#' onclick=searchContract('" + data[i].memberId + "',2)>销售协议</a></li>";
                                content += "<li><a href='#' onclick=searchContract('" + data[i].memberId + "',1)>采购协议</a></li>";
                                content += "</ul>";
                            }
                            $("#mmbsUl").append(content);
                            //alert($(".A_b_2_subnav :first").length);
                        }
                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            }
        });

        //切换显示
        function changeDisplay(obj) {
            $(obj).next('ul').stop().slideToggle()
        }



        //点击查询
        function searchContract(mmbId, type) {
            var mmbId = mmbId.toString();
            //mmbId = mmbId.substring(1,mmbId.length-1);
            //将选中的会员赋值为隐藏域中
            $("#relammbId").val(mmbId);
            $("#type").val(type);

            var currmmbId = $("#mmbId").val();
            if (mmbId != "" && type != "") {
                $.ajax({
                    url: '${ctx}/contract/queryAllContractsByTypeAndStatus.do',// 跳转到 action
                    data: {
                        mmbId: currmmbId,
                        relaMmbId: mmbId,
                        type: type,
                        pendingStatus: 0,
                        pageNo: 1,
                        pageSize: 10
                    },
                    type: 'POST',
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        //先清空table中的数据
                        $("#contractsTable  tr:not(:first)").remove();
                        if (data != "" && data.contracts.length > 0) {
                            for (var i = 0; i < data.contracts.length; i++) {
                                /**var content = '';
                                 content += "<tr style='border-right:1px #ddd solid; border-left:1px #ddd solid;'>";
                                 content += "<td style='padding-left:20px;'>"+(i+1)+"</td><td width='4%' style='padding-left:25px;'><input type='checkbox' value='"+data.contracts[i].id+"'></td>";
                                 content += "<td>"+data.contracts[i].contractTitle+"</td>";
                                 content += "<td>"+data.contracts[i].startTime+"</td>";
                                 content += "<td>"+data.contracts[i].endTime+"</td>";
                                 content += "<td>"+data.contracts[i].pay_type_+"</td>";
                                 content += "<td>"+data.contracts[i].flow_type_+"</td>";
                                 content += '</tr>';
                                 addTr('relaMmbPendingTable', -1, content);*/

                                var row2 = document.getElementById("contractsTable").insertRow(contractsTable.rows.length);
                                row2.insertCell(0).innerHTML = '<td width="4%" style="padding-left:25px;"><input type="checkbox" value="' + data.contracts[i].id + '" name ="balanceCheckBox"id="checkbox"/></td>';
                                row2.insertCell(1).innerHTML = '<td id="guaranteetId">' + data.contracts[i].contractTitle + '</td>';
                                row2.insertCell(2).innerHTML = '<td id="guaranteetId">' + new Date(data.contracts[i].startTime).toLocaleString() + '</td>';
                                row2.insertCell(3).innerHTML = '<td id="guaranteetId">' + new Date(data.contracts[i].endTime).toLocaleString() + '</td>';
                                row2.insertCell(4).innerHTML = '<td id="guaranteetId">' + data.contracts[i].pay_type_ + '</td>';
                                row2.insertCell(5).innerHTML = '<td id="guaranteetId">' + data.contracts[i].flow_type_ + '</td>';
                            }
                        }
                        setPagination(1, 10, data.countContract, type, mmbId);
                        var addressList = eval(data.addressList);
                        //alert(addressList.length);
                        document.getElementById("postGoodsAddress").options.length = 0;
                        for (var a = 0; a < parseInt(addressList.length); a++) {
                            document.getElementById("postGoodsAddress").options.add(new Option(addressList[a].addresstype, a));
                        }
                        var bankList = eval(data.bankList);
                        //alert(bankList.length);
                        document.getElementById("postGoodsBank").options.length = 0;
                        for (var j = 0; j < parseInt(bankList.length); j++) {
                            document.getElementById("postGoodsBank").options.add(new Option(bankList[j].bankname, j));
                        }

                    },
                    error: function () {
                        alert("异常！");
                    }
                });
            }
        }
        //会员申请审批列表分页信息
        function setPagination(curr, limit, totalCount, type, relaMmbId) {
            $('#callBackPager').extendPagination({
                totalCount: totalCount,
                showCount: limit,
                limit: limit,
                callback: function (curr, limit, totalCount) {
                    if ($("#keyword").val() == "" && $("#keyProvince").text() == "所在省份" && $("#keyTitle").text() == "") {
                        Notify('请至少选择一个条件！', 'bottom-right', '5000', 'info', 'fa-tag', true);
                        return;
                    }
                    $.ajax({
                        url: '${ctx}/contract/queryAllContractsByTypeAndStatus.do',// 跳转到 action
                        data: {
                            mmbId: $("#mmbId").val(),
                            relaMmbId: relaMmbId,
                            type: type,
                            pendingStatus: 0,
                            pageNo: curr,
                            pageSize: limit
                        },
                        type: 'POST',
                        cache: false,
                        dataType: 'json',
                        success: function (data) {
                            //先清空table中的数据
                            $("#contractsTable  tr:not(:first)").remove();
                            if (data != "" && data.contracts.length > 0) {
                                for (var i = 0; i < data.contracts.length; i++) {
                                    /**var content = '';
                                     content += "<tr style='border-right:1px #ddd solid; border-left:1px #ddd solid;'>";
                                     content += "<td style='padding-left:20px;'>"+(i+1)+"</td><td width='4%' style='padding-left:25px;'><input type='checkbox' value='"+data.contracts[i].id+"'></td>";
                                     content += "<td>"+data.contracts[i].contractTitle+"</td>";
                                     content += "<td>"+ function(data.contracts[i].startTime, row, index) {
				var unixTimestamp = new Date(value);
				return unixTimestamp.toLocaleString();
			}+"</td>";
                                     content += "<td>"+data.contracts[i].endTime+"</td>";
                                     content += "<td>"+data.contracts[i].pay_type_+"</td>";
                                     content += "<td>"+data.contracts[i].flow_type_+"</td>";
                                     content += '</tr>';
                                     addTr('relaMmbPendingTable', -1, content);*/

                                    var row2 = document.getElementById("contractsTable").insertRow(contractsTable.rows.length);
                                    row2.insertCell(0).innerHTML = '<td width="4%" style="padding-left:25px;"><input type="checkbox" value="' + data.contracts[i].id + '" name ="balanceCheckBox"id="checkbox"/></td>';
                                    row2.insertCell(1).innerHTML = '<td id="guaranteetId">' + data.contracts[i].contractTitle + '</td>';
                                    row2.insertCell(2).innerHTML = '<td id="guaranteetId">' + new Date(data.contracts[i].startTime).toLocaleString() + '</td>';
                                    row2.insertCell(3).innerHTML = '<td id="guaranteetId">' + new Date(data.contracts[i].endTime).toLocaleString() + '</td>';
                                    row2.insertCell(4).innerHTML = '<td id="guaranteetId">' + data.contracts[i].pay_type_ + '</td>';
                                    row2.insertCell(5).innerHTML = '<td id="guaranteetId">' + data.contracts[i].flow_type_ + '</td>';
                                }
                            }
                        },
                        error: function () {
                            alert("异常！");
                        }
                    });
                }
            });
        }

        Date.prototype.format = function (format) {
            var o = {
                "M+": this.getMonth() + 1, //month
                "d+": this.getDate(), //day
                "h+": this.getHours(), //hour
                "m+": this.getMinutes(), //minute
                "s+": this.getSeconds(), //second
                "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
                "S": this.getMilliseconds() //millisecond
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
         * 为table指定行添加一行
         * tab 表id
         * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
         * trHtml 添加行的html代码
         */
        function addTr(tab, row, trHtml) {
            //获取table最后一行 $("#tab tr:last")
            //获取table第一行 $("#tab tr").eq(0)
            //获取table倒数第二行 $("#tab tr").eq(-2)
            var $tr = $("#" + tab + " tr").eq(row);
            if ($tr.size() == 0) {
                alert("指定的table id或行数不存在！");
                return;
            }
            $tr.after(trHtml);
        }

        //进入添加协议页面
        function toAddContract() {
            var relammbId = $("#relammbId").val();
            var type = $("#type").val();
            if (relammbId == null || relammbId == "") {
                alert("请选择关系会员！");
                return false;
            }
            else if (type == null || type == "") {
                alert("请选择协议类型！");
                return false;
            }
            else {
                //form表单提交
                $("#form1").submit();
            }
        }

        function agree(status) {
            $.ajax({
                url: '${ctx}/contract/lockCTR.do',// 跳转到 action
                data: $("#contractsForm").serialize() + "&payerCode=" + $("#postGoodsBank").find("option:selected").text() + "&payerName=" + $("#postGoodsAddress").find("option:selected").text()
                + "&contractStatus=" + status,
                type: 'POST',
                cache: false,
                dataType: 'json',
                success: function (data) {
                    if (data != 0) {
                        alert("确认成功！确认条数为" + data);
                    }
                    window.location = "${ctx}/contract/toPendingCtr.do";
                },
            })
        }

        //选中所有协议
        function checkAllCtr(){
            //var flag = $(obj).prop("checked");
           // console.log(this);
            alert(1);
           /* console.log(flag);
            if(flag){
                $("#contractsTable input[name ='balanceCheckBox']").prop("checked",true);
            }else{
                $("#contractsTable input[name ='balanceCheckBox']").prop("checked",false);
            }*/
        }


        /*$("#checkAllCtr").click(function(){
            alert(1);
            var flag = $(this).prop("checked");
            alert(flag);

        });
*/



    </script>
</head>

<body>
<div class="panel panel-default"
     style="  margin-top:10px; box-shadow:3px 3px 8px rgba(0,0,0,0.1); margin-right:1%; height:auto;">
    <!---------------------------------------con top  start-------------------------------------------------------------->
    <div class="con_top">
        <form id="form1" action="${ctx}/contract/toAddContractPage.do" type="post">
            <p>待审批合作协议
                <input type="hidden" id="mmbId" value="${mmbId!}" name="mmbId"/>
                <input type="hidden" id="relammbId" name="relammbId"/>
                <input type="hidden" id="type" name="type"/></p>
        </form>
    </div>
    <!---------------------------------------con top  over--------------------------------------------------------------->
    <!---------------------------------------nav start--------------------------------------------------------------->
    <div>
        <ul class="A_b_2_nav_left" style=" float:left; margin-bottom:2%;" id="mmbsUl">

        </ul>
        <!---------------------------------------nav  over--------------------------------------------------------------->
        <!---------------------------- table start--------------------------------------------------->
        <div class="table-responsive">
            <div class="A_b_3_in_title" style=" height:30px; background-color:#418bca;  padding-left:50px;">
            </div>

            <form id="contractsForm">
                <table class="table table-hover" id="contractsTable">
                    <thead>
                    <tr class="active" style=" border:1px solid #ddd;">
                        <th width="2%" style="padding-left:25px;"><input type="checkbox" id="checkAllCtr222" onclick="checkAllCtr()"></th>
                        <th width="10%">标题</th>
                        <th width="10%">开始时间</th>
                        <th width="10%">结束时间</th>
                        <th width="10%">付款期</th>
                        <th width="10%">运输方式</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </form>
            <footer class="panel-footer text-right bg-light lter">
                <div id="callBackPager" float="right;"></div>
            </footer>
        </div>
        <!---------------------------- table over--------------------------------------------------->
        <div class="clear"></div>
        <div style="float:right; margin:10px 22% 22px 0;">
            <a href="#" onclick="toAddContract()">
                <button type="button" class="btn btn-primary" style=" margin-right:20px; padding:6px 20px;">添加</button>
            </a>
            <button type="button" class="btn btn-primary" style=" margin-right:20px; padding:6px 20px;"  onclick="agree(2)">拒绝   </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"     style=" margin-right:20px; padding:6px 20px;">同意  </button>
            <!--同意 start------>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" style="">
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <form class="form-horizontal" role="form" style="padding:5% 0 0 4%;">
                            <div class="form-group">
                                <label for="firstname" class="col-sm-3 control-label">缺省付款账号</label>

                                <div class="col-sm-8 input-lg">
                                    <select class="form-control " id="postGoodsBank">
                                        <option value="">中信银行：88888888888888888888888</option>
                                        <option value="">建设银行：88888888888888888888888</option>
                                    <#if bankList ??>
                                        <#list bankList as hc>
                                            <option value="${hc.id}">${hc.name}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstname" class="col-sm-3 control-label">缺省收货地址</label>

                                <div class="col-sm-8 input-lg">
                                    <select class="form-control " id="postGoodsAddress">
                                        <option value="">丰台北大地库</option>
                                        <option value="">朝阳新发地库</option>
                                    <#if addressList ??>
                                        <#list addressList as hc>
                                            <option value="${hc.id}">${hc.addresstype}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                            <!--<div class="form-group" id="getGoodsBank">
                               <label for="firstname" class="col-sm-3 control-label">缺省付款账号</label>
                               <div class="col-sm-8 input-lg">
                                   <select class="form-control ">
                                      <option value="">中信银行：88888888888888888888888</option>
                                      <option value="">建设银行：88888888888888888888888</option>
                                   </select>
                               </div>
                            </div>
                            <div class="form-group" id="getGoodsAddress">
                               <label for="firstname" class="col-sm-3 control-label">缺省收货地址</label>
                               <div class="col-sm-8 input-lg">
                                   <select class="form-control ">
                                      <option value="">丰台北大地库</option>
                                      <option value="">朝阳新发地库</option>
                                   </select>
                               </div>
                            </div>-->

                            <div class="form-group">
                                <input style="margin:5% 0 5% 30%; padding:6px 24px;" class="btn btn-primary"
                                       data-dismiss="modal" type="button" value="确认" onclick="agree(1)">
                                <input style="margin:5% 0 5% 10%; padding:6px 24px;" class="btn btn-primary"
                                       data-dismiss="modal" type="button" value="取消">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!------ 同意 over------>
        </div>
        <div class="clear"></div>
    </div>

</body>
</html>
