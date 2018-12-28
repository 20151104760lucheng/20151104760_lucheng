<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/form_util/page/root.jsp"%>

<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>修改密码</strong> </font>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				旧密码
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="password" placeholder="旧密码"  class="col-xs-12"
						 data-validate="required:旧密码,ajax#zhangHu_isPassword.do?zh_id=${zhangHu.ZH_ID}&zh_zhmm=:旧密码不正确" />
				</span>
			</div>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				新密码
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="password" placeholder="账户密码" id="password" name="password" class="col-xs-12"
						data-validate="required:账户密码">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				确认密码
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="password" placeholder="确认密码" id="updateZhangHuMiMa" class="col-xs-12"
						 data-validate="required:请填写密码,repeat#password:两次输入的密码不一致">
				</span>
			</div>
		</div>

		<div class="form-actions center">
			<button type="button" class="btn btn-sm btn-lg" onclick="closeShowDiv()">
				返回
				<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
			</button>
			<button type="submit" class="btn btn-sm btn-success" onclick="updatePasswordValidate('${zhangHu.zh_id}')">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>
<script>
	initValidate();
</script>



