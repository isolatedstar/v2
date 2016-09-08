<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

   <link rel="stylesheet" href="${pageContext.request.contextPath}/mall/css/bootstrap.min.css">
   <link href="${pageContext.request.contextPath}/mall/css/theme.css" rel="stylesheet">
   <script src="${pageContext.request.contextPath}/mall/js/jquery.js"></script>
   <script src="${pageContext.request.contextPath}/mall/js/jquery.min.js"></script>
   <script src="${pageContext.request.contextPath}/mall/js/bootstrap.min.js"></script>
   <!--文件树插件-->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/mall/css/demo.css" type="text/css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/mall/css/zTreeStyle/zTreeStyle.css" type="text/css">
   <script type="text/javascript" src="${pageContext.request.contextPath}/mall/js/jquery.ztree.core-3.5.js"></script>  
   <script type="text/javascript" src="${pageContext.request.contextPath}/mall/js/jquery.ztree.excheck-3.5.js"></script>  
   <script type="text/javascript" src="${pageContext.request.contextPath}/mall/js/jquery.ztree.exedit-3.5.js"></script> 
   <script src="${pageContext.request.contextPath}/mall/js/bootstrap-treeview.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/mall/js/extendPagination.js"></script>
   <script type="text/JavaScript" src="${pageContext.request.contextPath}/mall/js/jQuery-1.7.2.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/mall/js/uploadify/jquery.uploadify.min.js"></script>
   <link href="${pageContext.request.contextPath}/mall/css/default.css" rel="stylesheet" type="text/css" />  
	<link href="${pageContext.request.contextPath}/mall/css/uploadify.css" rel="stylesheet" type="text/css" />  
	  
	<script type="text/javascript" src="uploadify/scripts/swfobject.js"></script>  
	
   <script language="JavaScript">  
   $(document).ready(function() {  
	    $("#uploadify").uploadify({  
	        'uploader'       : '${pageContext.request.contextPath}/mall/fonts/uploadify.swf',  
	        'script'         : 'UploadPhotoServlet',  
	        'cancelImg'      : 'uploadify/cancel.png',  
	        'buttonImg'      : 'uploadify/buttonImg.png',  
	        'folder'         : '/jxdBlog/photos',  
	        'queueID'        : 'fileQueue',  
	        'auto'           : false,  
	        'multi'          : true,  
	        'wmode'          : 'transparent',  
	        'simUploadLimit' : 999,  
	        'fileExt'        : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',  
	        'fileDesc'       : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',  
	        'onAllComplete'  :function(event,data)   
	        {  
	            $('#result').html(data.filesUploaded +'个图片上传成功');  
	        }  
	    });  
	});  
	</script>  
	<style type="text/css">  
	.inputcss  
	{  
	    color:#333333;  
	    font-family: "Tahoma";   
	    font-size: 12px;   
	    border:solid 1px #CCCCCC;  
	}  
	.buttoncss  
	{  
	    color:#333333;  
	    font-family: "Tahoma";   
	    font-size: 12px;   
	    background-color:#FFFFFF;  
	    border:solid 1px #CCCCCC;  
	}  
	</style>  
<title>上传测试</title>
</head>
<body>
	<table style="width: 90%;">  
        <tr>  
            <td style="width: 100px;">  
                <input type="file" name="uploadify" id="uploadify" />  
            </td>  
            <td align="left">  
                <a href="javascript:$('#uploadify').uploadifyUpload()">开始上传</a>|  
            <a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传</a>  
            <span id="result" style="font-size: 13px;color: red"></span>  
            </td>  
        </tr>  
    </table>  
        <div id="fileQueue" style="width: 400px;height: 300px; border: 2px solid green;">
</body>
</html>