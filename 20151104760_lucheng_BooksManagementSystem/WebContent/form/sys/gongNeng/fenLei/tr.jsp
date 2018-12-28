<%@ page language="java" pageEncoding="utf-8"%>
<input id="updateID" style="display: none;" value="${map.gnfl_id}">

<%@include file="/form_util/page/root.jsp"%>
<table>
	<tr id="tr${map.gnfl_id}" isOpen="${map.trIsOpen}">
		<td index="tdIndex">
			
		</td>
		<td>
			<img style="cursor: pointer;" id="img${map.gnfl_id}"
				onclick="gongNengTbody('${map.gnfl_id}')" isOpen="${map.imgIsOpne}"
				src="${map.imgSrc}">
		</td>
		<td>
			${map.gnfl_mc}
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
				<button class="btn btn-xs btn-info" title="编辑" onclick="openEditPage('${map.gnfl_id}')">
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
</table>
