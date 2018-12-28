<%@ page language="java" pageEncoding="utf-8"%>
<input id="updateID" style="display: none;" value="${map.js_id}">

<%@include file="/form_util/page/root.jsp"%>
<table>
	<tr id="tr${map.js_id}">
		<td index="tdIndex">
			
		</td>
		<td>
			${map.js_jsm}
		</td>
		<td>
			${map.bz}
		</td>
		<td>
			${map.lrzhm}
		</td>
		<td>
			${map.gxsj}
		</td>
		<td>
			${map.lrsj}
		</td>

		<td>
			<div class="hidden-sm hidden-xs btn-group">
				<button class="btn btn-xs btn-info" title="编辑" onclick="openEditPage('${map.js_id}')">
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
</table>
