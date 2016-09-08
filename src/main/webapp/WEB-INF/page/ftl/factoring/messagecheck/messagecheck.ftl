<#assign sec=JspTaglibs["/WEB-INF/security.tld"] /> <#-- 引入security.tld标签-->
<#assign ctx = request.contextPath/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>还款管理</title>
 <link rel="stylesheet" href="${ctx}/factoring/css/bootstrap.min111.css"/>
  <link href="${ctx}/factoring/css/theme.css" rel="stylesheet">

  <script src="${ctx}/factoring/js/jquery.js"></script>
  <script src="${ctx}/factoring/js/bootstrap.min111.js"></script>
  <script src="${ctx}/factoring/js/page/page.js"></script>

  <link href="${ctx}/factoring/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="${ctx}/factoring/css/daterangepicker-bs3.css" />
  <script type="text/javascript" src="${ctx}/factoring/js/moment.js"></script>
  <script type="text/javascript" src="${ctx}/common/js/common.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/daterangepicker.js"></script>
  <script type="text/javascript" src="${ctx}/factoring/js/jquery.page.js"></script>
  <script type="text/javascript">
   $(document).ready(function() {
      $('#createDate').daterangepicker({
      }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
      });
   });
   $(document).ready(function() {
      $('#createDate').daterangepicker({
        timePicker: true,
      }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
      });
   });

 $(function(){
   		 //总页数
   		 var totalPage = parseInt("${totalPage!}");
   		 //当前页 
   		 var page = parseInt("${nowPage!}");
		 $('#register').click(function(){
		  	$('#selectForm').submit();
	  	 });
		 
		 $(".selectPage").createPage({
	        pageCount:totalPage,
	        current:page,
	        backFn:function(p){
	        	$("#nowPage").val(p);
				$('#selectForm').submit();
	        }
	     });
   });
	 
   function showDetail(messId){
	 	
	  	var len = parseInt(show_Detail.rows.length);
	    for(var j=1;j<len;len--){
			document.getElementById("show_Detail").deleteRow(j);
		}
		
	 	$.ajax({
			url:'${ctx}/messageCheckController/findDetailMessage.do',
			dataType:"JSON",
			type:"POST",
			data:{"messId":messId},
			success:function(data){
			    var messDetail = data.split(",");
			    for(var i=0;i<parseInt(messDetail.length);i++){
			    	var row1 =document.getElementById("show_Detail").insertRow(i+1);
			    	row1.insertCell(0).innerHTML='<td id="messDetail">'+messDetail[i]+'</td>';
			    }
			}
		});
  }
  
  function executeMessage(){

	var messIds = "";
	$("#messBtn").attr({"disabled":"disabled"});
	$("#repay_tbody input:checked").each(function(i,element){
		if(i>0){messIds += ","}
		messIds += $(element).val();
	}); 
	 
	$.ajax({
			url:'${ctx}/messageCheckController/executeMessage.do',
			dataType:"JSON",
			type:"POST",
			data:{"messIds":messIds},
			success:function(data){
			    $("#messBtn").removeAttr("disabled");
			    alert(data);
			    $('#selectForm').submit();
			}
	});	  
  }
  
  </script>

</head>
<body>
  <div class="container-fluid">
    <div class="row-fluid">
      <!-- col-sm-12 -->
      <div class="panel panel-default article-bj">
        <div class="panel-heading box-shodm">报文审核</div>
        <div class="row wrapper form-margin">
          <form name="selectForm" id="selectForm" action="${ctx}/messageCheckController/findMessageCheckList.do" method="post">
            <input type="hidden" name="nowPage" id="nowPage" value="1"/>
            <input type="hidden" name="hidFinancingId" id="hidFinancingId"/>
            <div class="row wrapper form-margin">
              <div class="col-md-3">
              <div class="input-group">
                <div class="input-group-btn">
                  <h5 class="h5-margin">生成日期:</h5>
                </div>
                <input type="text" placeholder="" class="form-control " name="createDate" id="createDate"></div>
              </div>
              <div class="col-md-3">
                <div class="input-group">
                  <div class="input-group-btn">
                    <h5 class="h5-margin">报文来源:</h5>
                  </div>
                  <select name="messageSource" tabindex="-1"class="form-control">
                   	<option value="-1">--请选择--</option>
                    <option value="0">认可</option>
                    <option value="1">还款</option>
                  </select>
                </div>
              </div>
            </div>
            <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" href="javascript:void(0);" name="register" id="register">查询</a>
          </form>
        </div>

        <div class="table-responsive panel-table-body ">
          <table class="table table-striped table-hover ">
            <thead>
              <tr>
                <th>
                  <input type="checkbox" onclick="checkAllBox(this)" name="checkAll">
                </th>
                <th>生成日期</th>
                <th>还款描述</th>
                <th>操作员</th>
                <th>来源</th>
              </tr>
            </thead>
            <tbody id="repay_tbody">
              <#if messList??>
					<#list messList as mess>
	              <tr>
	                <td>
	                  <input type="checkbox" value="${mess.id!}" name="forLowerId" onclick="forLowerIdCheck(this)">
	                </td>
	                <td>${(mess.createtime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
	                <td>
	                	<#if mess.refunddescription??>
	                		<#if mess.refunddescription?length lt 80>
	                			<a href="javascript:void(0);"  data-toggle="modal" data-target="#changeChar" onclick="showDetail('${mess.id!}');">
	                				${mess.refunddescription}
	                			</a>
	                		<#else>
	                			<a href="javascript:void(0);"  data-toggle="modal" data-target="#changeChar" onclick="showDetail('${mess.id!}');">${mess.refunddescription?substring(0,80)}...</a>
	                		</#if>
	                	</#if>
	                </td>
	                <td>${mess.operator!}<td>
	                <td>
	                	<#if mess.source??>
	                		<#if mess.source==0>
	                		  	 认可
							<#elseif mess.source==1>
	                			还款
		           			</#if>
	            		</#if>
	                </td>
	              </tr>
              </#list>
              </#if>
            </tbody>
          </table>
        </div>
        <footer class="panel-footer text-right bg-light lter">
          <div class="right-footer">
          	<button class="btn btn-success btn-s-xs" type="button" id="messBtn" onclick="return executeMessage();">审核</button>
          </div>
          <div class="yema-page">
    			<ul class="pagination-page selectPage">
		        </ul>
     		</div>
        </footer>
      </div>
      <!-- 转账报文明细  -->
      <div class="modal fade" id="changeChar" role="dialog" aria-labelledby="gridSystemModalLabel" style="margin-left:225px;">
        <div class="modal-dialog tanchu-box" role="document">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="col-sm-12 ">
                <div class="panel panel-default">
                  <div class="panel-heading box-shodm">
                    	转账报文明细
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  </div>
                  <div class="table-responsive panel-table-body ">
                    <table class="table table-striped table-hover fixed" id="show_Detail">
                        <tr>
                          	<th>
							</th>
                        </tr>
                    </table>
                    <footer class="panel-footer text-right bg-light lter">
			    	<div class="yema-page">
			    		<ul class="pagination-page detailPage" id="append_ul_li">
						 
						</ul>
					</div>
			        </footer>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--  查看详情表 -->
    <!-- col-sm-12 --> 
  </div>
</body>
</html>