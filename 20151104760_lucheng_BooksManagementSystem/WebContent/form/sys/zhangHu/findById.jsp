<%@ page language="java" pageEncoding="utf-8"%>
<div class="widget-main no-padding" style="width: 666px">
	<form class="form-horizontal">
	<div class="form-group text-center">
			<font size="4"> <strong>账户详情</strong> </font>
		</div>
		<div class="profile-user-info profile-user-info-striped">
			<div class="profile-info-row">
				<div class="profile-info-name">
					账户名
				</div>
				<div class="profile-info-value">
					<span class="">${map.zh_zhm}</span>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">
					最新操作时间
				</div>
				<div class="profile-info-value">
					<span class="" id="country">${map.gxsj}</span>
				</div>
				<div class="profile-info-name">
					录入时间
				</div>
				<div class="profile-info-value">
					<span class="" id="country">${map.lrsj}</span>
				</div>
			</div>
		</div>
		<div style="padding-top: 2px"></div>
		<div class="profile-user-info profile-user-info-striped">
			<div class="profile-info-row">
				<div class="profile-info-name">
					备注
				</div>
				<div class="profile-info-value">
					<span class="" id="country">${map.bz}</span>
				</div>
			</div>
		</div>
		<div style="padding-top: 10px"></div>
		<div class="form-actions center">
			<button type="button" onclick="closeShowDiv()" class="btn btn-sm btn-lg">
				返回
				<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
			</button>

		</div>
	</form>
</div>