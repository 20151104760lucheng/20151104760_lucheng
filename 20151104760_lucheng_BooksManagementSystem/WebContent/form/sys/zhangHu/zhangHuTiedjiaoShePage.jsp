<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/myTag.tld " prefix="myTag"%>

<div class="widget-main no-padding" style="min-width: 550px">
	<form class="form-horizontal" isValidate="y" onsubmit="return false;">
		<div class="form-group text-center">
			<font size="4"> <strong>账户角色绑定</strong> </font>
			<div style="margin-bottom: -5px">
				正在为
				<b><font color="red">${map.zh_zhm}</font> </b> 绑定角色
			</div>
		</div>

		<div class="bs-example"
			style="min-height: 300px; padding: 5px; overflow: auto;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							<input type="checkbox" onclick="selectAll(this,'jiaoSheCheckBox');">
						</th>
						<th>
							序号
						</th>
						<th>
							名称
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jiaoShe}" varStatus="i" var="map">
						<tr>
							<td style="padding: 8px">
								<input type="checkbox" id="checkbox${map.js_id}"
									name="jiaoSheCheckBox" value="${map.js_id}">
							</td>
							<td style="padding: 10px">
								${i.index+1}
							</td>
							<td>
								${map.js_jsm}
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
				onclick="selectOKJiaoSheGoengNeng('${map.zh_id}','${map.zh_zhm}');">
				提交
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</button>
		</div>
	</form>
</div>


<script type="text/javascript">
	checkboxChecked('<myTag:listToStr listMap="${zhangHuJiaoShe}"/>');
	loadTrTdClickCheckbox();//加载选中td 是选中td中的checkbox
</script>