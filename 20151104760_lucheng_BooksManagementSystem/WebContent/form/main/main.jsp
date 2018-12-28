<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/form_util/page/root.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>图书借阅管理系统</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet"
			href="${root}/form/main/assets/css/bootstrap.css" />
		<link rel="stylesheet"
			href="${root}/form/main/assets/css/font-awesome.css" />
		<link rel="stylesheet"
			href="${root}/form/main/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${root}/form/main/assets/css/ace.css" />
	</head>

	<body class="no-skin">
		<!-- 存放菜单数据临时元素 -->
		<input style="display: none;" id="menuData" value="${menuData}">
		
		<input id="root" style="display: none;" value="${root}">
		<div id="navbar" class="navbar navbar-default">
			<!-- 头部 -->
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand"> <small>图书借阅管理系统</small> </a>
				</div>

				<div class="navbar-buttons navbar-header pull-right"
					role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img
									class="nav-user-photo"
									src="${root}/form/main/assets/avatars/user.jpg"
									alt="Jason's Photo" /> <span class="user-info"> <small>欢迎你,</small>
									${zhangHu.zh_zhm} </span> <i class="ace-icon fa fa-caret-down"></i> </a>

							<ul
								class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="javascript:openUpdatePasswordPage()"> <i class="ace-icon fa fa-cog"></i> 修改密码 </a>
								</li>


								<li class="divider"></li>

								<li>
									<a href="login_result.do"> <i class="ace-icon fa fa-power-off"></i>
									 安全退出</a>
								</li>
							</ul>
						</li>

					</ul>
				</div>
			</div>
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<!-- 菜单 -->
			<div id="sidebar" class="sidebar responsive">
				<ul class="nav nav-list" id="menu"></ul>
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left"
						data-icon1="ace-icon fa fa-angle-double-left"
						data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
			<!-- 显示内容区 -->
			<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12 widget-box"
							style="width: 99%; margin-left: 5px;border: 0px solid;" id="showMenuDiv">
							<ul class="nav nav-tabs" role="tablist" style="margin: -1px -1px 0px -1px" id="tabsMenu">
								<li class="active" style="border-left: 0px solid;">
									<a href="#indexPage" role="tab" data-toggle="tab">系统首页</a>
								</li>
							</ul>
							<div class="tab-content" style="border: 0px;">
								<div role="tabpanel" class="tab-pane active" id="indexPage">

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="${root}/form_util/util/sys_js/jquery.js"></script>
		<script src="${root}/form_util/util/util_js/bodyUtil.js"></script>
		<script src="${root}/form/main/assets/js/bootstrap.js"></script>
		<script src="${root}/form/main/assets/js/ace/ace.js"></script>
		<script src="${root}/form/main/assets/js/ace/ace.sidebar.js"></script>
		<script src="${root}/form_util/util/util_js/jsonUtil.js"></script>
		<script src="${root}/form/main/js/sidebar-menu.js"></script>
		<script src="${root}/form/main/assets/js/bootstrap-tab.js"></script>
		<script src="${root}/form_util/util/util_js/ajaxUtil.js"></script>
		<script src="${root}/form_util/util/util_js/validateUtil.js"></script>
		<script src="${root}/form_util/util/util_js/strUtil.js"></script>
		<script src="${root}/form_util/util/util_js/alertDivUtil.js"></script>
		<script src="${root}/form/main/js/main.js"></script>
	</body>
</html>