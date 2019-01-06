<%@ page language="java" pageEncoding="utf-8"%> 
<%@include file="/form_util/page/root.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>录入</strong> </font>
		</div>
		
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				账号
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="账号" id="update_username" class="col-xs-12"
					data-validate="required:账号必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				密码
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="密码" id="update_password" class="col-xs-12"
					data-validate="required:密码必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				手机号
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="手机号" id="update_phone" class="col-xs-12"
					data-validate="required:手机号必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				昵称
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="昵称" id="update_nick_name" class="col-xs-12"
					data-validate="required:昵称必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				性别
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="性别" id="update_sex" class="col-xs-12"
					data-validate="required:性别必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				年龄
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="年龄" id="update_age" class="col-xs-12"
					data-validate="required:年龄必填">
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
			<button type="submit" class="btn btn-sm btn-success" onclick="addDataValidate()">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>
<script>
initValidate();
</script>
