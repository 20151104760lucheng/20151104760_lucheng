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
							<form action="book_index.do" id="queryForm" method="post">
								<!-- 参数隐藏 用于页面传值-->
								<input style="display: none;" id="mapStr" name="mapStr">
								<fieldset>
									
									<label>
										书名
									</label>
									<input type="text" id="query_title" placeholder="标题" value="${map.title}">
									
									<label>
										编号
									</label>
									<input type="text" id="query_number" placeholder="编号" value="${map.number}">
									
									<label>
										作者
									</label>
									<input type="text" id="query_aunthor" placeholder="作者" value="${map.aunthor}">
									<button type="submit" onclick="query_Book(0)" class="btn btn-sm btn-success">
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
<th field="title"  onclick="querySort(this,'query_Book')">
						书名
					</th>
					<th field="number"  onclick="querySort(this,'query_Book')">
						编号
					</th>
					<th field="aunthor"  onclick="querySort(this,'query_Book')">
						作者
					</th>
					<th field="publishing"  onclick="querySort(this,'query_Book')">
						出版社
					</th>
					<th field="vision"  onclick="querySort(this,'query_Book')">
						版本
					</th>
					<th field="status"  onclick="querySort(this,'query_Book')">
						状态
					</th>
					<th field="gxsj" width="170" onclick="querySort(this,'query_Book')">
						更新时间
					</th><th field="bz"  onclick="querySort(this,'query_Book')">
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
							${map.title}
						</td>
						<td>
							${map.number}
						</td>
						<td>
							${map.aunthor}
						</td>
						<td>
							${map.publishing}
						</td>
						<td>
							${map.vision}
						</td>
						<td>
							${map.status==0?'空闲':'外借'}
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
<c:set var="cx" value="query_Book" />
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
<script src="${root}/form/book/js/book_main.js"></script>
</body>
</html>