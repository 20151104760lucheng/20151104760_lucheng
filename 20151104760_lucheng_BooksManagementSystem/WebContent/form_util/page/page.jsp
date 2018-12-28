<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.util.*"%>
<%
	Logger loggerPage = Logger.getLogger(Object.class);
	int tj = 0;
	try {
		tj = UtilStr.getInt(request.getAttribute("tj"));
	} catch (Exception e) {
		loggerPage.error(e);
	}
	int j = 0;
	if (tj / SysFinal.PAGENUM_VAL != 0) {
		j = tj / SysFinal.PAGENUM_VAL;
		if (tj % SysFinal.PAGENUM_VAL != 0) {
			j += 1;
		}
	}
	request.setAttribute("j", j);
	request.setAttribute("sysPageNum", SysFinal.PAGENUM_VAL);
%>
<center>
	<div style="position: fixed; bottom: -20px; width: 100%; height: 50px">
	<div class="btn-group btn-corner">
		<!-- 没有总条数 -->
		<c:if test="${empty tj}">
			<button type="button" class="btn btn-info btn-xs"
				disabled="disabled">
				首页
			</button>
			<button type="button" class="btn btn-info btn-xs"
				disabled="disabled">
				上一页
			</button>
			<button type="button" class="btn btn-info btn-xs"
				disabled="disabled">
				下一页
			</button>
			<button type="button" class="btn btn-info btn-xs"
				disabled="disabled">
				末页
			</button>
		</c:if>

		<c:if test="${!empty tj}">
			<c:if test="${pageNum-1>=0}">
				<button type="button" class="btn btn-info btn-xs"
					onclick="${cx}(0)">
					首页
				</button>
			</c:if>
			<c:if test="${pageNum-1<0}">
				<button type="button" class="btn btn-info btn-xs"
					disabled="disabled">
					首页
				</button>
			</c:if>


			<c:if test="${pageNum-1>=0}">
				<button type="button" class="btn btn-info btn-xs"
					onclick="${cx}(${pageNum-1})">
					上一页
				</button>
			</c:if>
			<c:if test="${pageNum-1<0}">
				<button type="button" class="btn btn-info btn-xs"
					disabled="disabled">
					上一页
				</button>
			</c:if>

			<c:if test="${pageNum+1<j}">
				<button type="button" class="btn btn-info btn-xs"
					onclick="${cx}(${pageNum+1})">
					下一页
				</button>
			</c:if>
			<c:if test="${pageNum+1>=j}">
				<button type="button" class="btn btn-info btn-xs"
					disabled="disabled">
					下一页
				</button>
			</c:if>
			<c:if test="${pageNum+1<j}">
				<button type="button" class="btn btn-info btn-xs"
					onclick="${cx}(${j-1})">
					末页
				</button>
			</c:if>
			<c:if test="${pageNum+1>=j}">
				<button type="button" class="btn btn-info btn-xs"
					disabled="disabled">
					末页
				</button>
			</c:if>
		</c:if>
		</div>
		<span class="badge badge-danger"> ${tj} </span> 条数据 当前为第
		<span class="badge badge-danger"> ${pageNum+1} </span> 页 每页显示
		<select class="ui-pg-selbox" id="${pageSelectNum}SelectPageNum"
			onchange="${cx}(0);">
			<option value="10"
				<c:if test="${sysPageNum==10}">selected="selected"</c:if>>
				10
			</option>
			<option value="15"
				<c:if test="${sysPageNum==15}">selected="selected"</c:if>>
				15
			</option>
			<option value="20"
				<c:if test="${sysPageNum==20}">selected="selected"</c:if>>
				20
			</option>
			<option value="25"
				<c:if test="${sysPageNum==25}">selected="selected"</c:if>>
				25
			</option>
		</select>
		条
	</div>
</center>