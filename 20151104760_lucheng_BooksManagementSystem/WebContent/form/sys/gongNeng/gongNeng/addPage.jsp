<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/form_util/page/root.jsp"%>

<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>功能录入</strong> </font>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				功能名
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="功能名" id="updateGongNengMing" class="col-xs-12"
						data-validate="required:功能名必填">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				功能分类
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="功能分类" disabled="disabled" value="${mapFL.fl_mc}" class="col-xs-12">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				功能地址
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="功能地址" id="updateGongNengDiZhi" class="col-xs-12"
						data-validate="required:功能地址">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				显示图标
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="显示图标" id="updateGongNengIcon" class="col-xs-12">
				</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				备注
			</label>
			<div class="col-sm-5">
				<textarea class="col-xs-12" id="beiZhu" placeholder="备注"></textarea>
			</div>
		</div>

		<div class="form-actions center">
			<button type="button" class="btn btn-sm btn-lg" onclick="closeShowDiv()">
				返回
				<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
			</button>
			<button type="submit" class="btn btn-sm btn-success" onclick="addDataValidateGongNeng('${mapFL.fl_id}')">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>
<script>
	initValidate();
</script>



