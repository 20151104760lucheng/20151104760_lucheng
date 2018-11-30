<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>   
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
</head>
<body style="background-color: #000;">
<form isValidate="y" onsubmit="return false;">
	<dl class="admin_login">
	 <dt>
	  <strong>图书借阅管理系统</strong>
	  <em>Book lending management system</em>
	 </dt>
	 <dd class="user_icon">
	  <input id="zh_zhm" value="" name="username" type="name" placeholder="账号" class="login_txtbx"/>
	 </dd>
	 <dd class="pwd_icon">
	  <input id="zh_zhmm" value="" name="userpwd" type="password" placeholder="密码" class="login_txtbx"/>
	 </dd>
	 <dd>
 		<input onclick="login('')" type="submit" value="立即登录" class="submit_btn" name="button"/>
	 </dd>
	  <dd>
 		没有账号？<a href="JavaScript:openAddPage();" style="color: red;">立即注册</a>
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