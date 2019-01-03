<%@ page language="java" pageEncoding="utf-8"%> 
<%@include file="/form_util/page/root.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>编辑</strong> </font>
		</div>
		
		<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				标题
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
					<input type="text" placeholder="标题" id="update_title" class="col-xs-12"
						data-validate="required:标题必填" value="${map.title}" onchange="$('#isUpdate').attr('value','y')">
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
						data-validate="required:编号必填" value="${map.number}" onchange="$('#isUpdate').attr('value','y')">
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
						data-validate="required:作者必填" value="${map.aunthor}" onchange="$('#isUpdate').attr('value','y')">
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
						data-validate="required:出版社必填" value="${map.publishing}" onchange="$('#isUpdate').attr('value','y')">
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
						data-validate="required:版本必填" value="${map.vision}" onchange="$('#isUpdate').attr('value','y')">
				</span>
			</div>
		</div>
			<div class="form-group field">
			<label class="col-sm-3 control-label no-padding-right">
				状态
			</label>
			<div class="col-sm-5">
				<span class="block input-icon input-icon-right">
					<select id="update_status" class="col-xs-12" onchange="$('#isUpdate').attr('value','y')">
						<option value="0" <c:if test="${map.status==0 }">selected="selected"</c:if>>空闲</option>
						<option value="1" <c:if test="${map.status==1 }">selected="selected"</c:if>>外借</option>
					</select>
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
			<button type="submit" class="btn btn-sm btn-success" onclick="editDataValidate('${map.id}')">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>
<script>
initValidate();
</script>
