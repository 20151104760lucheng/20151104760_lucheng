<%@ page language="java" pageEncoding="utf-8"%>
<input id="updateID" style="display: none;" value="${map.gn_id}">
<table>
	<tr id="tr${map.gn_id}" pid="tr${map.fl_id}">
		<td colspan="2" align="right">
			--|
		</td>
		<td>
			${map.gn_mc}
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
				<button class="btn btn-xs btn-info"
					onclick="openEditPageGongNeng('${map.gn_id}')" title="编辑">
					<i class="ace-icon fa fa-pencil bigger-120"></i>
				</button>

				<button class="btn btn-xs btn-danger"
					onclick="confirmDeleteDateGongNeng('${map.gn_id}','${map.gn_mc}')"
					title="删除">
					<i class="ace-icon fa fa-trash-o bigger-120"></i>
				</button>
			</div>
		</td>
	</tr>
</table>
