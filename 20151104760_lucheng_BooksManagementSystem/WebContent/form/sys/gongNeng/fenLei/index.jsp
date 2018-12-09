<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/form_util/page/root.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>功能分类管理</title>
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
								功能分类管理
							</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main no-padding">
								<form action="gongNengFenLei_index.do" id="queryForm" method="post">
									<!-- 参数隐藏 用于页面传值-->
									<input style="display: none;" id="mapStr" name="mapStr">
									<fieldset>
										<label>
											分类名
										</label>
										<input type="text" id="queryFenLeiMing" placeholder="分类名" value="${map.fl_mc}">
										<button type="submit" onclick="queryGongNengFeiLei(0)" class="btn btn-sm btn-success">
											查询
											<i
												class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
										</button>
										<button type="button" onclick="openAddPage()" class="btn btn-sm btn-info">
											添加分类
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
							<th width="50px">
							</th>
							<th field="gnfl_mc" width="20%" onclick="querySort(this,'queryGongNengFeiLei')">
								分类名
							</th>
							<th field="bz"  onclick="querySort(this,'queryGongNengFeiLei')">
								描述
							</th>
							<th field="zh_zhm" width="100px" onclick="querySort(this,'queryGongNengFeiLei')">
								操作账户
							</th>
							<th field="gxsj" width="150px" onclick="querySort(this,'queryGongNengFeiLei')">
								更新时间
							</th>
							<th field="lrsj" width="150px" onclick="querySort(this,'queryGongNengFeiLei')">
								录入时间
							</th>
							<th width="100">
								操作
							</th>
						</tr>
					</thead>

					<tbody id="tbodyGongNengFeiLei">
						<c:if test="${empty listMap}">
							<tr align="center">
								<td colspan="8">
									没有查询到数据
								</td>
							</tr>
						</c:if>

						<c:if test="${!empty listMap}">
							<c:forEach items="${listMap}" varStatus="i" var="map">
								<tr id="tr${map.gnfl_id}" isOpen="n">
									<td index="tdIndex">
										${i.index+1}
									</td>
									<td>
										<img style="cursor: pointer;" id="img${map.gnfl_id}"
											onclick="gongNengTbody('${map.gnfl_id}')" isOpen="n"
											src="${root}/form_util/util/images/nolines_plus.gif">
									</td>
									<td>
										${map.gnfl_mc}
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
											<button class="btn btn-xs btn-info" onclick="openEditPage('${map.gnfl_id}')" title="编辑">
												<i class="ace-icon fa fa-pencil bigger-120"></i>
											</button>
											
											<button class="btn btn-xs btn-danger" onclick="confirmDeleteDate('${map.gnfl_id}','${map.gnfl_mc}')" title="删除">
												<i class="ace-icon fa fa-trash-o bigger-120"></i>
											</button>
											
											<button class="btn btn-xs btn-success" onclick="openAddPageGongNeng('${map.gnfl_id}','${map.gnfl_mc}')" title="功能添加">
												<i class="ace-icon fa fa-plus bigger-120"></i>
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
		<c:set var="cx" value="queryGongNengFeiLei" />
		<c:set var="pageSelectNum" value="gongNengFeiLei" />
		<%@include file="/form_util/page/page.jsp"%>
		
		<script src="${root}/form_util/util/sys_js/jquery.js"></script>
		<script src="${root}/form_util/util/util_js/nodeUtil.js"></script>
		<script src="${root}/form_util/util/util_js/ajaxUtil.js"></script>
		<script src="${root}/form_util/util/util_js/alertDivUtil.js"></script>
		<script src="${root}/form_util/util/util_js/bodyUtil.js"></script>
		<script src="${root}/form_util/util/util_js/validateUtil.js"></script>
		<script src="${root}/form_util/util/util_js/jsonUtil.js"></script>
		<script src="${root}/form_util/util/util_js/publicJsonUtil.js"></script>
		<script src="${root}/form_util/util/util_js/findByIdUtil.js"></script>
		<script src="${root}/form_util/util/util_js/strUtil.js"></script>
		<script src="${root}/form/sys/gongNeng/fenLei/js/feiLei.js"></script>
		<script src="${root}/form/sys/gongNeng/gongNeng/js/gongNeng.js"></script>
	</body>
</html>

