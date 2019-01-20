<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/form_util/page/root.jsp"%>
<!DOCTYPE html>
<html> 
<head>
<title>图书借阅管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="${root}/form/login/css/style.css" />
<link rel="stylesheet"
	href="${root}/form/main/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${root}/form/main/assets/css/ace.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="${root}/form/login/js/jquery.js"></script>
<script src="${root}/form/login/js/jquery-1.8.3.js"></script>
<script src="${root}/form/login/js/verificationNumbers.js"></script>
<script src="${root}/form/login/js/Particleground.js"></script>
<script src="${root}/form_util/util/util_js/validateUtil.js"></script>
<script src="${root}/form_util/util/util_js/bodyUtil.js"></script>
<script src="${root}/form_util/util/util_js/jsonUtil.js"></script>
<script src="${root}/form_util/util/util_js/ajaxUtil.js"></script>
<script src="${root}/form/login/js/login.js"></script>
<script src="${root}/form_util/util/sys_js/jquery.js"></script>
<script src="${root}/form_util/util/util_js/ajaxUtil.js"></script>
<script src="${root}/form_util/util/util_js/alertDivUtil.js"></script>
<script src="${root}/form_util/util/util_js/bodyUtil.js"></script>
<script src="${root}/form_util/util/util_js/validateUtil.js"></script>
<script src="${root}/form_util/util/util_js/jsonUtil.js"></script>
<script src="${root}/form_util/util/util_js/publicJsonUtil.js"></script>
<script src="${root}/form_util/util/util_js/findByIdUtil.js"></script>
<script src="${root}/form_util/util/util_js/strUtil.js"></script>
<script>

</script>
</head>
<body style="background-color: #000;">
<form isValidate="y" onsubmit="return false;">
	<dl class="admin_login">
	 <dt>
	  <strong>管理员端-图书借阅管理系统</strong>
	  <em>Admin - Book lending management system</em>
	 </dt>
	 <dd class="user_icon">
	  <input id="zh_zhm" value="" name="username" type="name" placeholder="账号" class="login_txtbx"/>
	 </dd>
	 <dd class="pwd_icon">
	  <input id="zh_zhmm" value="" name="userpwd" type="password" placeholder="密码" class="login_txtbx"/>
	 </dd>
	 <dd>
 		<input onclick="login('20180307104743203')" type="submit" value="立即登录" class="submit_btn" name="button"/>
	 </dd>
	 <dd>
	 </dd>
	 <dd style="color: red" id="infoDiv">${infoError}</dd>
	</dl>
</form>
<!-- 提交数据 -->		
<form action="login_valiDate.do" id="myLoginForm" method="post">
	<!-- 参数隐藏 用于页面传值-->
	<input style="display: none;" id="mapStr" name="mapStr">
</form>
</body>
</html>
