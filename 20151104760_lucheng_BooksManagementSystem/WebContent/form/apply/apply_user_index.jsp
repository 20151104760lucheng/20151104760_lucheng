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
							<form action="apply_index.do?isUser=1" id="queryForm" method="post">
								<!-- 参数隐藏 用于页面传值-->
								<input style="display: none;" id="mapStr" name="mapStr">
								<fieldset>
									
									<label>
										书籍
									</label>
									<input type="text" id="query_book_id" placeholder="书籍" value="${map.book_id}">
									
									<button type="submit" onclick="query_Apply(0)" class="btn btn-sm btn-success">
										查询
									<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
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
					<th field="book_id"  onclick="querySort(this,'query_Apply')">
						书籍
					</th>
					<th field="user_id"  onclick="querySort(this,'query_Apply')">
						读者
					</th>
					<th field="createTime"  onclick="querySort(this,'query_Apply')">
						借书时间
					</th>
					<th field="estimatedTime"  onclick="querySort(this,'query_Apply')">
						约定还书时间
					</th>
					<th field="updateTime"  onclick="querySort(this,'query_Apply')">
						实际还书时间
					</th>
					<th field="is_renew" width="70" onclick="querySort(this,'query_Apply')">
						续借
					</th>
					<th field="is_renew" width="70" onclick="querySort(this,'query_Apply')">
						逾期
					</th>
					<th field="updateTime" width="170" onclick="querySort(this,'query_Apply')">
						状态
					</th>
					<th width="110">
						操作
					</th>
				</tr>
			</thead>
			<tbody id="tbodyData">
				<c:if test="${empty listMap}">
					<tr align="center">
						<td colspan="44">
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
							${map.nick_name}
						</td>
						<td>
							${map.createTime}
						</td>
						<td>
							${map.estimatedTime}
						</td>
						<td>
							${map.updateTime}
						</td>
						<td>${map.is_renew=='0'?'无':'有' }</td>
						<td>${(!empty map.updateTime)?'':(map.is_overdue=='0'?'无':'有') }</td>
						<td>
							${empty map.updateTime?'未归还':'已归还'}
						</td>
						<td>
							<div class="hidden-sm hidden-xs btn-group">
								<c:if test="${empty map.updateTime}">
									<c:if test="${map.is_overdue==0}">
										<button class="btn btn-xs btn-info" onclick="updateApplyData('${zhangHu.student_id }','${map.book_id}','${map.id}')" title="归还">归还</button>
										<c:if test="${'0'== map.is_renew}">
											<button class="btn btn-xs btn-success" onclick="xujie('${map.id}')" title="续借">续借</button>
										</c:if>
									</c:if>
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<c:set var="cx" value="query_Apply" />
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
<script src="${root}/form/apply/js/apply_main.js"></script>
</body>
</html>