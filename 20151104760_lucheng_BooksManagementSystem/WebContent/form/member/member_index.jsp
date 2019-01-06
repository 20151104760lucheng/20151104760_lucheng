<%@ page language="java" pageEncoding="utf-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@include file="/form_util/page/root.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="${root}/form/main/assets/css/bootstrap.css" />
<link rel="stylesheet" href="${root}/form/main/assets/css/font-awesome.css" />
<link rel="stylesheet" href="${root}/form/main/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${root}/form/main/assets/css/ace.css" />
<link rel="stylesheet" href="${root}/form_util/util/util_css/myPage.css" />
</head>
<body class="no-skin">
<!-- 返回回来参数 -->
<%@include file="/form_util/page/hidden.jsp"%>
<div class="main-container box" id="main-container">
	<div class="page-content">
		<div class="row">
			<div class="widget-box">
				<div class="widget-header">
					<h4 class="widget-title">
						管理
					</h4>
				</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<form action="member_index.do" id="queryForm" method="post">
								<!-- 参数隐藏 用于页面传值-->
								<input style="display: none;" id="mapStr" name="mapStr">
								<fieldset>
									
									<label>
										账号
									</label>
									<input type="text" id="query_username" placeholder="账号" value="${map.username}">
									
									<label>
										手机号
									</label>
									<input type="text" id="query_phone" placeholder="手机号" value="${map.phone}">
									<button type="submit" onclick="query_Member(0)" class="btn btn-sm btn-success">
										查询
									<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
									</button>
									<button type="button" onclick="openAddPage()" class="btn btn-sm btn-info">
										添加
										<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
									</button>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	<div id="sample-table-2_wrapper" class="dataTables_wrapper form-inline no-footer"> 
		<table id="sample-table-1" class="table  table-bordered dataTable"> 
			<thead>
				<tr>
					<th width="50px">
						序号
					</th>
<th field="username"  onclick="querySort(this,'query_Member')">
						账号
					</th>
					<th field="password"  onclick="querySort(this,'query_Member')">
						密码
					</th>
					<th field="phone"  onclick="querySort(this,'query_Member')">
						手机号
					</th>
					<th field="nick_name"  onclick="querySort(this,'query_Member')">
						昵称
					</th>
					<th field="sex"  onclick="querySort(this,'query_Member')">
						性别
					</th>
					<th field="age"  onclick="querySort(this,'query_Member')">
						年龄
					</th>
					<th field="gxsj" width="170" onclick="querySort(this,'query_Member')">
						更新时间
					</th><th field="bz"  onclick="querySort(this,'query_Member')">
						备注
					</th>
					<th width="110">
						操作
					</th>
				</tr>
			</thead>
			<tbody id="tbodyData">
				<c:if test="${empty listMap}">
					<tr align="center">
						<td colspan="64">
							没有查询到数据
						</td>
					</tr>
				</c:if>
				<c:forEach items="${listMap}" varStatus="i" var="map">
					<tr id="tr${map.id}"  isOpen="n">
						<td index="tdIndex">
							${i.index+1}
						</td>
					<td>
							${map.username}
						</td>
						<td>
							${map.password}
						</td>
						<td>
							${map.phone}
						</td>
						<td>
							${map.nick_name}
						</td>
						<td>
							${map.sex}
						</td>
						<td>
							${map.age}
						</td>
						<td>
							${map.gxsj}
						</td>
						<td>
							${map.bz}
						</td>
						<td>
							<div class="hidden-sm hidden-xs btn-group">
								<button class="btn btn-xs btn-info" onclick="openEditPage('${map.id}')" title="编辑">编辑</button><button class="btn btn-xs btn-danger" onclick="confirmDeleteDate('${map.id}','${map.username}')" title="删除">删除</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<c:set var="cx" value="query_Member" />
<c:set var="pageSelectNum" value="selectPageMain" />
<%@include file="/form_util/page/page.jsp"%>

<script src="${root}/form_util/util/sys_js/jquery.js"></script>
<script src="${root}/form_util/util/util_js/ajaxUtil.js"></script>
<script src="${root}/form_util/util/util_js/alertDivUtil.js"></script>
<script src="${root}/form_util/util/util_js/bodyUtil.js"></script>
<script src="${root}/form_util/util/util_js/validateUtil.js"></script>
<script src="${root}/form_util/util/util_js/jsonUtil.js"></script>
<script src="${root}/form_util/util/util_js/publicJsonUtil.js"></script>
<script src="${root}/form_util/util/util_js/findByIdUtil.js"></script>
<script src="${root}/form_util/util/util_js/strUtil.js"></script>
<script src="${root}/form_util/date/WdatePicker.js"></script>
<script src="${root}/form/main/assets/js/bootstrap-tab.js"></script>
<script src="${root}/form_util/util/sys_js/ajaxfileupload.js"></script>
<script src="${root}/form_util/util/util_js/nodeUtil.js"></script>
<script src="${root}/form/member/js/member_main.js"></script>
</body>
</html>