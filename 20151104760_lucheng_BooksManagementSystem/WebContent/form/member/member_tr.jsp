<%@ page language="java" pageEncoding="utf-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input id="updateID" style="display: none;" value="${map.id}">
<%@include file="/form_util/page/root.jsp"%>
<table>
	<tr id="tr${map.id}">
		<td index="tdIndex">
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
				<button class="btn btn-xs btn-info" title="编辑" onclick="openEditPage('${map.id}')">
					编辑
				</button>
				<button class="btn btn-xs btn-danger"  onclick="confirmDeleteDate('${map.id}','${map.username}')" title="删除">
					删除
				</button>
			</div>
		</td>
	</tr>
</table>
