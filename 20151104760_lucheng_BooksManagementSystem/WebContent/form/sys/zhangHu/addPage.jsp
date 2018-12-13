<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/form_util/page/root.jsp"%>
<head>
	<style type="text/css">
	</style>
</head>
<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>账户录入</strong> </font>
		</div>
		<div class="form-group field">
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="text" placeholder="邮箱" id="updateZhangHuMing" class="col-xs-12"
						onchange="senEmail();" data-validate="required:帐户名必填,ajax#zhangHu_isExistName.do?zh_zhm=:用户名已注册"/>
				</span>
			</div>
		</div>
		<div class="form-group field">
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="password" placeholder="账户密码" id="password" name="password" class="col-xs-12"
						data-validate="required:账户密码">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="password" placeholder="确认密码" id="updateZhangHuMiMa" class="col-xs-12"
						 data-validate="required:请填写密码,repeat#password:两次输入的密码不一致">
				</span>
			</div>
		</div>
		<div class="form-group field">
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right"> 
					<input type="password" placeholder="验证码" id="updateZhangHuMiMa" class="col-xs-12"
						 data-validate="required:请填写验证码,ajax#yanzheng.do?yzm=:验证码错误">
				</span>
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
	function senEmail(){
		var email = $("#updateZhangHuMing").val();
		if(email==null){
			return;
		}
		$.post("seneEmail.do?zh_zhm="+email,function(){
			console.log("验证码已发送");
		})
	}
	
</script>



