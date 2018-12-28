<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input id="updateID" style="display: none;" value="${map.zh_id}">
<%@include file="/form_util/page/root.jsp"%>
<table>
	<tr id="tr${map.zh_id}">
		<td index="tdIndex">

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
				<button class="btn btn-xs btn-info" title="编辑"
					onclick="openEditPage('${map.zh_id}')" title="编辑">
					<i class="ace-icon fa fa-pencil bigger-120"></i>
				</button>
				<button class="btn btn-xs btn-success"
					onclick="zhangHuTiedjiaoShePage('${map.zh_id}','${map.zh_zhm}')"
					title="绑定角色">
					<i class="ace-icon fa fa-cogs bigger-120"></i>
				</button>
				<button class="btn btn-xs btn-primary"
					onclick="confirmResetPaw('${map.zh_id}','${map.zh_zhm}')"
					title="重置密码">
					<i class="ace-icon fa fa-refresh bigger-120"></i>
				</button>
				<c:if test="${map.zt==2}">
					<button class="btn btn-xs btn-danger" id="lockBotton${map.zh_id}"
						onclick="confirmUpdateLock('${map.zh_id}','${map.zh_zhm}',0)"
						title="禁用">
						<i class="ace-icon fa  fa-lock bigger-120"></i>
					</button>
				</c:if>
				<c:if test="${map.zt==0}">
					<button class="btn btn-xs btn-success" id="lockBotton${map.zh_id}"
						onclick="confirmUpdateLock('${map.zh_id}','${map.zh_zhm}',2)"
						title="解禁">
						<i class="ace-icon fa fa-unlock bigger-120"></i>
					</button>
				</c:if>
			</div>
		</td>
	</tr>
</table>
