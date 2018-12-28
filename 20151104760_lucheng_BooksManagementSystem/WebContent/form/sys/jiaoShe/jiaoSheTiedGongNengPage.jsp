<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/myTag.tld " prefix="myTag"%>
<div class="widget-main no-padding" style="min-width: 850px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>角色功能绑定</strong> </font>
			<div style="margin-bottom: -5px">
				正在为
				<b><font color="red">${map.js_jsm}</font> </b> 绑定功能
			</div>
		</div>

		<div class="bs-example"
			style="height: 430px; padding: 5px; overflow: auto;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							<input type="checkbox"
								onclick="selectAll(this,'gongNengCheckBox');">
						</th>
						<th>
							序号
						</th>
						<th>
							名称
						</th>
						<th>
							大类名称
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gongNeng}" varStatus="i" var="map">
						<tr>
							<th scope="row">
								<input type="checkbox" id="checkbox${map.gn_id}"
									name="gongNengCheckBox" value="${map.gn_id}">
							</th>
							<td>
								${i.index+1}
							</td>
							<td>
								${map.GN_MC}
							</td>
							<td>
								${map.gnfl_mc}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
		<div class="form-actions center">
			<button type="button" class="btn btn-sm btn-lg"
				onclick="closeShowDiv()">
				返回
				<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
			</button>
			<button type="submit" class="btn btn-sm btn-success"
				onclick="selectOKJiaoSheGoengNeng('${map.js_id}','${map.js_jsm}');">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>


<script type="text/javascript">
	checkboxChecked('<myTag:listToStr listMap="${jiaoSheGoengNeng}"/>');
	loadTrTdClickCheckbox();//加载选中td 是选中td中的checkbox
</script>