<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>结款单签章</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/factoring/css/bootstrap.min111.css"/>
  <link href="${pageContext.request.contextPath}/factoring/css/theme.css" rel="stylesheet">

  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/json2.js"></script>
  <script src="${pageContext.request.contextPath}/factoring/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/factoring/js/bootstrap.min111.js"></script>
  <script src="${pageContext.request.contextPath}/factoring/js/page/page.js"></script>
  <link href="${pageContext.request.contextPath}/factoring/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/factoring/css/daterangepicker-bs3.css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/factoring/js/moment.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/factoring/js/daterangepicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/factoring/js/jquery.page.js"></script>
  <script type="text/javascript">
	var CryptoAgency = "";
	function LoadObject() {
		try {
			var eDiv = document.createElement("div");
			if (navigator.appName.indexOf("Internet") >= 0) {
				if (window.navigator.cpuClass == "x86") {
					eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\"${pageContext.request.contextPath}/cfcak_Auth/CryptoKit.NXDJFWW.x86.cab\" classid=\"clsid:4AB61763-2A77-4044-966B-4589F676E8A9\"></object>";
				} else {
					eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\"${pageContext.request.contextPath}/cfcak_Auth/CryptoKit.NXDJFWW.x64.cab\" classid=\"clsid:054AFC85-2FEA-488F-8BE4-824EBF55907A\"></object>";
				}
			} else {
				eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\"${pageContext.request.contextPath}/cfcak_Auth/CryptoKit.NXDJFWW.x86.cab\" classid=\"clsid:4AB61763-2A77-4044-966B-4589F676E8A9\"></object>";
			}
			document.body.appendChild(eDiv);
		} catch (e) {
			alert(e);
		}
		CryptoAgency = document.getElementById("CryptoAgent");
	}

	function SelectCertificate() {

		try {
			
			var CertSubjectDN = CryptoAgency.SelectSignCertificate("", "");
			if (!CertSubjectDN) {
				var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
				alert(LastErrorDesc);
				return;
			}
			document.getElementById("TextSelectedCertificateSubjectDN").value = CertSubjectDN;

			var certSN = CryptoAgency.GetSelectedCertSN();
			document.getElementById("TextSelectedCertificateSubjectSN").value = certSN;

			var certTime = CryptoAgency.GetSelectedCertVaildTime();
			document.getElementById("TextSelectedCertificateValidTime").value = certTime;

			var csp = CryptoAgency.GetCSPNameofSignCert();
			document.getElementById("TextSelectedCertificateCSP").value = csp;
			$("#tis").html("");
		} catch (e) {
			$("#tis").html("证书未安装请刷新页面！");
			$("#tiss").html("(使用IE浏览器)");
			var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
			alert(LastErrorDesc);
		}
	}

	function GetSelectedItemValue(itemName) {

		var ele = document.getElementsByName(itemName);
		for (i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				return ele[i].value;
			}
		}
	}

	function GetSelectedItemIndex(itemName) {

		var ele = document.getElementsByName(itemName);
		for (i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				return i;
			}
		}
	}

	function GetSelectedCertContent() {
		try {
			var certificate = CryptoAgency.GetSelectedCertContent();

			if (!certificate) {
				var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
				alert(LastErrorDesc);
				return;
			}
			document.getElementById("TextGetCertificate").value = certificate;
		} catch (e) {
			var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
			alert(LastErrorDesc);
		}
	}

	function GetVersion() {
		try {
			var version = CryptoAgency.GetVersion();
			document.getElementById("TextGetversion").value = version;
		} catch (e) {
			var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
			alert(LastErrorDesc);
		}
	}

	function SignMessage() {

		try {
			var Signature = "";
			var SignedContent = $("#signSettle").val();
			var opertion = $("#opertion").val();
			//var selectedAlg = GetSelectedItemValue("algorithm");
			var signStyle = GetSelectedItemIndex("signStyle");
			
			var textGetCertificate = $("#TextGetCertificate").val();
			if(textGetCertificate==""){
				alert("请选择证书！");
				return false;
			}
			
			//Signature = CryptoAgency.SignMessage(SignedContent, selectedAlg);
			Signature = CryptoAgency.SignMessage(SignedContent, "SHA-1");

			if (!Signature) {
				var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
				alert(LastErrorDesc);
				return;
			}

			document.getElementById("textareaSignature").value = Signature;

			$.ajax({
				url:'${pageContext.request.contextPath}/cfcAkController/cfcakHandle_Auth.do',
				dataType:"JSON",
				type:"POST",
				data:{"signedContent":SignedContent, "signature":Signature, "opertion":opertion},
				success:function(data){
					alert(data);
					window.location = "${pageContext.request.contextPath}/settle/signature.do?settleId="+$("#settleId").val();
				}
			});
			
		} catch (e) {
			var LastErrorDesc = CryptoAgency.GetLastErrorDesc();
			alert(LastErrorDesc);
		}
	}
	
	$(function(){
		var settle = $("#settle").text();
		var signEnu = $("#signEnu").text();
		var jsonObj = eval("("+settle+")");
		var signEnuJson = eval("("+signEnu+")");
		var statusSign = "";
		for(var key in signEnuJson){
			if(jsonObj.content.statusSign==key){
				statusSign = signEnuJson[key];
			}
		}
		
		$("#signSettle").val(JSON.stringify(jsonObj.content));
		$("#opertion").val(JSON.stringify(jsonObj.memberInfo));
		$("#settleId").val(jsonObj.memberInfo.settleId);
		
		$("#settleCode").append(jsonObj.content.settleCode);
		$("#settleMoney").append(jsonObj.content.settleMoney);
		$("#ctrTime").append(jsonObj.content.ctrTime);
		$("#ctrMoney").append(jsonObj.content.ctrMoney);
		$("#gotMoney").append(jsonObj.content.gotMoney);
		$("#mmbpayName").append(jsonObj.content.mmbpayName);
		$("#mmbgetName").append(jsonObj.content.mmbgetName);
		$("#statusSign").append(statusSign);
	})
	
</script>
<body onload="javascript:LoadObject();GetVersion();">
	<div class="container-fluid">
    <div class="row-fluid">
      <!-- col-sm-12 -->
      <div class="panel panel-default article-bj">
        <div class="panel-heading box-shodm">结款单签章</div>
        <div class="row wrapper form-margin">
          <form name="selectForm" id="selectForm" action="${ctx}/refundMgController/selectFinancingRepaymen.do" method="post">
            <!-- 后台原文 -->
            <span style="display:none;" id="settle">${settle}</span>
		   	<!-- 签名原文 -->
		   	<input type="hidden" id="signSettle" />
		   	<!-- 操作人信息-->
		   	<input type="hidden" id="opertion" />
		   	<!-- 结款单Id-->
		   	<input type="hidden" id="settleId" />
		   	<!-- 版本号 -->
		   	<input type="hidden" id="TextGetversion" />
		   	<!-- 选定证书所在CSP -->
		   	<input type="hidden" id="TextSelectedCertificateCSP"/>
		   	<!-- 选定证书的主题DN -->
		   	<input type="hidden" id="TextSelectedCertificateSubjectDN"/>
		   	<!-- 选定证书的内容(Base64编码) -->
		   	<input type="hidden" id="TextGetCertificate"/>
		   	<!-- 签名结果 -->
		   	<input type="hidden" id="textareaSignature"/>
		   	<!-- 签章状态枚举 -->
		   	<span style="display:none;" id="signEnu">${signEnu}</span>
            <div>
              <div class="col-md-4">
	              <div class="input-group">
	                <div class="input-group-btn">
	                  <h5 class="h5-margin">证书SN:</h5>
	                </div>
	              <input type="text" placeholder="" class="form-control" readonly="readonly" name="TextSelectedCertificateSubjectSN" id="TextSelectedCertificateSubjectSN"></div>
              </div>
              <div class="col-md-4">
	              <div class="input-group">
	                <div class="input-group-btn">
	                  <h5 class="h5-margin">证书有效时间:</h5>
	                </div>
	              <input type="text" placeholder="" class="form-control" readonly="readonly" name="TextSelectedCertificateValidTime" id="TextSelectedCertificateValidTime"></div>
              </div>
              <div class="col-md-4">
              	<!-- 提示信息 -->
              	<span id="tiss" style="color: red;line-height:36px;vertical-align:middle;"></span>
				<span id="tis" style="color: red;line-height:36px;vertical-align:middle;"></span>
	            <a class="btn btn-info btn-s-md btn-default float-right cx-btn-margin" onclick="SelectCertificate();GetSelectedCertContent();" href="javascript:void(0);" id="register">选择证书</a>
              </div>
            </div>
          </form>
        </div>

        <div class="table-responsive panel-table-body ">
          <table class="table table-striped table-hover ">
            <thead>
              <tr>
                <th>结款单号</th>
                <th>结款金额</th>
                <th>约定结款时间</th>
                <th>约定实付金额</th>
                <th>收到的金额</th>
                <th>付款方全称</th>
                <th>收款方全称</th>
                <th>签章状态</th>
              </tr>
            </thead>
            <tbody id="sign_tbody">
            	<tr>
            		<td id="settleCode"></td>
            		<td id="settleMoney"></td>
            		<td id="ctrTime"></td>
            		<td id="ctrMoney"></td>
            		<td id="gotMoney"></td>
            		<td id="mmbpayName"></td>
            		<td id="mmbgetName"></td>
            		<td id="statusSign"></td>
            	</tr>
            </tbody>
          </table>
        </div>
        <footer class="panel-footer text-right bg-light lter">
          <div>
          	<h5 style="float:left;margin-left:30%; color: red">签章方:<span>${user.muser.member.mmbFname}</span></h5>
          	<button class="btn btn-success btn-s-xs" type="button" id="refundBtn" onclick="return SignMessage();">确认签章</button>
          </div>
        </footer>
      </div>
	 </div>
    </div>
</body>
</html>
