<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/form_util/page/root.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>账户管理</title>
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
								账户管理
							</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main no-padding">
								<form action="zhangHu_index.do" id="queryForm" method="post">
									<!-- 参数隐藏 用于页面传值-->
									<input style="display: none;" id="mapStr" name="mapStr">
									<fieldset>
										<label>
											账户名
										</label>
										<input type="text" id="queryZhangHuMing" placeholder="账户名" value="${map.zh_zhm}">
										<button type="submit" onclick="queryZhangHu(0)" class="btn btn-sm btn-success">
											查询
											<i
												class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
										</button>
										<button type="button" onclick="openAddPage()" class="btn btn-sm btn-info">
											添加账户
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
							<th field="zh_zhm" width="20%" onclick="querySort(this,'queryZhangHu')">
								账户名
							</th>
							<th field="bz"  onclick="querySort(this,'queryZhangHu')">
								描述
							</th>
							<th field="zt" width="100px" onclick="querySort(this,'queryZhangHu')">
								账户状态
							</th>
							<th field="gxsj" width="150px" onclick="querySort(this,'queryZhangHu')">
								更新时间
							</th>
							<th field="lrsj" width="150px" onclick="querySort(this,'queryZhangHu')">
								录入时间
							</th>
							<th width="135">
								操作
							</th>
						</tr>
					</thead>

					<tbody id="tbodyZhangHu">
						<c:if test="${empty listMap}">
							<tr align="center">
								<td colspan="7">
									没有查询到数据
								</td>
							</tr>
						</c:if>

						<c:if test="${!empty listMap}">
							<c:forEach items="${listMap}" varStatus="i" var="map">
								<tr id="tr${map.ZH_ID}">
									<td index="tdIndex">
										${i.index+1}
									</td>
									<td>
										${map.zh_zhm}
									</td>
									<td>
										${map.bz}
									</td>
									<td id="zt${map.zh_id}">
										<c:if test="${map.zt==2}">
											<span class="badge badge-success">正常</span>
										</c:if>
										<c:if test="${map.zt==0}">
											<span class="badge badge-danger">禁用</span>
										</c:if>
									</td>
									<td>
										${map.gxsj}
									</td>
									<td>
										${map.lrsj}
									</td>
									<td>
										<div class="hidden-sm hidden-xs btn-group">
											<button class="btn btn-xs btn-info" onclick="openEditPage('${map.zh_id}')" title="编辑">
												<i class="ace-icon fa fa-pencil bigger-120"></i>
											</button>
											<button class="btn btn-xs btn-success" onclick="zhangHuTiedjiaoShePage('${map.zh_id}','${map.zh_zhm}')" title="绑定角色">
												<i class="ace-icon fa fa-cogs bigger-120"></i>
											</button>
											<button class="btn btn-xs btn-primary" onclick="confirmResetPaw('${map.zh_id}','${map.zh_zhm}')" title="重置密码">
												<i class="ace-icon fa fa-refresh bigger-120"></i>
											</button>
											<c:if test="${map.zt==2}">
												<button class="btn btn-xs btn-danger" id="lockBotton${map.zh_id}" onclick="confirmUpdateLock('${map.zh_id}','${map.zh_zhm}',0)" title="禁用">
													<i class="ace-icon fa  fa-lock bigger-120"></i>
												</button>
											</c:if>
											<c:if test="${map.zt==0}">
												<button class="btn btn-xs btn-success" id="lockBotton${map.zh_id}" onclick="confirmUpdateLock('${map.zh_id}','${map.zh_zhm}',2)" title="解禁">
													<i class="ace-icon fa fa-unlock bigger-120"></i>
												</button>
											</c:if>
											
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
		<c:set var="cx" value="queryZhangHu" />
		<c:set var="pageSelectNum" value="zhangHu" />
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
		<script src="${root}/form/sys/zhangHu/js/zhangHu.js"></script>
	</body>
</html>

