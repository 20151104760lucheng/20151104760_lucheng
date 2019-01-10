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
				书名
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="书名" id="update_title" class="col-xs-12"
					data-validate="required:书名必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				编号
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="编号" id="update_number" class="col-xs-12"
					data-validate="required:编号必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				作者
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="作者" id="update_aunthor" class="col-xs-12"
					data-validate="required:作者必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				出版社
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="出版社" id="update_publishing" class="col-xs-12"
					data-validate="required:出版社必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				版本
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
				<input type="text" placeholder="版本" id="update_vision" class="col-xs-12"
					data-validate="required:版本必填">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				状态
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
					<select id="update_status" class="col-xs-12">
						<option value="0">空闲</option>
						<option value="1">外借</option>
					</select>
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
