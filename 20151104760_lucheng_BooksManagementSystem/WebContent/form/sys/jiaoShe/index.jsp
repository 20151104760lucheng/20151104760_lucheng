<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/form_util/page/root.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>角色管理</title>
		<link rel="stylesheet"
			href="${root}/form/main/assets/css/bootstrap.css" />
		<link rel="stylesheet"
			href="${root}/form/main/assets/css/font-awesome.css" />
		<link rel="stylesheet"
			href="${root}/form/main/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${root}/form/main/assets/css/ace.css" />
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
								角色管理
							</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main no-padding">
								<form action="jiaoShe_index.do" id="queryForm" method="post">
									<!-- 参数隐藏 用于页面传值-->
									<input style="display: none;" id="mapStr" name="mapStr">
									<fieldset>
										<label>
											角色名
										</label>
										<input type="text" id="queryJiaoSheMing" placeholder="角色名" value="${map.js_jsm}">
										<button type="submit" onclick="queryJiaoShe(0)" class="btn btn-sm btn-success">
											查询
											<i
												class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
										</button>
										<button type="button" onclick="openAddPage()" class="btn btn-sm btn-info">
											添加角色
											<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
										</button>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="sample-table-2_wrapper"
				class="dataTables_wrapper form-inline no-footer">
				<table id="sample-table-1"
					class="table  table-bordered dataTable">
					<thead>
						<tr>
							<th width="50px">
								序号
							</th>
							<th field="js_jsm" width="20%" onclick="querySort(this,'queryJiaoShe')">
								角色名
							</th>
							<th field="bz"  onclick="querySort(this,'queryJiaoShe')">
								描述
							</th>
							<th field="zh_zhm" width="100px" onclick="querySort(this,'queryJiaoShe')">
								操作账户
							</th>
							<th field="gxsj" width="150px" onclick="querySort(this,'queryJiaoShe')">
								更新时间
							</th>
							<th field="lrsj" width="150px" onclick="querySort(this,'queryJiaoShe')">
								录入时间
							</th>
							<th width="110">
								操作
							</th>
						</tr>
					</thead>

					<tbody id="tbodyJiaoShe">
						<c:if test="${empty listMap}">
							<tr align="center">
								<td colspan="7">
									没有查询到数据
								</td>
							</tr>
						</c:if>

						<c:if test="${!empty listMap}">
							<c:forEach items="${listMap}" varStatus="i" var="map">
								<tr id="tr${map.js_id}">
									<td index="tdIndex">
										${i.index+1}
									</td>
									<td>
										${map.js_jsm}
									</td>
									<td>
										${map.bz}
									</td>
									<td>
										${map.zh_zhm}
									</td>
									<td>
										${map.gxsj}
									</td>
									<td>
										${map.lrsj}
									</td>
									<td>
										<div class="hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info" onclick="openEditPage('${map.js_id}')" title="编辑">
												<i class="ace-icon fa fa-pencil bigger-120"></i>
											</button>
											
											<button class="btn btn-xs btn-success" onclick="jiaoSheTiedGongNengPage('${map.js_id}','${map.js_jsm}')" title="绑定功能">
												<i class="ace-icon fa fa-cogs bigger-120"></i>
											</button>
											
											<button class="btn btn-xs btn-danger" onclick="confirmDeleteDate('${map.js_id}','${map.js_jsm}')" title="删除">
												<i class="ace-icon fa fa-trash-o bigger-120"></i>
											</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页参数配置 -->
		<c:set var="cx" value="queryJiaoShe" />
		<c:set var="pageSelectNum" value="jiaoShe" />
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
		<script src="${root}/form/sys/jiaoShe/js/jiaoShe.js"></script>
	</body>
</html>

