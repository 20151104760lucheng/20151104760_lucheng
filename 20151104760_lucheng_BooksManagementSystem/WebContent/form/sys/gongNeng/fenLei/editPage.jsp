<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/form_util/page/root.jsp"%>

<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>功能分类编辑</strong> </font>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				功能分类名
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="功能分类名" id="updateFenLeiMing" class="col-xs-12"
						data-validate="required:功能分类名必填" value="${map.gnfl_mc}" onchange="$('#isUpdate').attr('value','y')">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				显示图标
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="显示图标" id="updateFenLeiIcon" class="col-xs-12" value="${map.gnfl_icon}" onchange="$('#isUpdate').attr('value','y')">
					<i class="${map.gnfl_icon}"></i>
				</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				备注
			</label>
			<div class="col-sm-5">
				<textarea class="col-xs-12" id="beiZhu" placeholder="备注" onchange="$('#isUpdate').attr('value','y')">${map.bz}</textarea>
			</div>
		</div>

		<div class="form-actions center">
			<button type="button" class="btn btn-sm btn-lg" onclick="closeShowDiv()">
				返回
				<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
			</button>
			<button type="submit" class="btn btn-sm btn-success" onclick="editDataValidate('${map.gnfl_id}')">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>
<script>
	initValidate();
</script>



