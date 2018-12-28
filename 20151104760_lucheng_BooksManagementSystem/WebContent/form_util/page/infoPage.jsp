<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/form_util/page/root.jsp"%>

<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<p class="bigger-110 bolder center grey" style="height: 66px;padding-top: 30px">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>
			${confirmInfo}
		</p>
		<div class="form-actions center">
			<button type="button" class="btn btn-sm btn-lg" onclick="closeShowDiv()">
				返回
				<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
			</button>
			<button type="submit" class="btn btn-sm btn-success" onclick="confirmOk()">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>

	</form>
</div>



