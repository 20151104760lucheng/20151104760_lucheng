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
	if (tj / 10 != 0) {
		j = tj / 10;
		if (tj % 10 != 0) {
			j += 1;
		}
	}
	request.setAttribute("j", j);
%>
<center>
	<c:if test="${!empty tj}">
		<c:if test="${pageNum+1<j}">
			<a style="text-decoration:none;" href="javascript:${cx}('${data_id}',${pageNum+1})">
				加载更多数据(共<strong style="color: red">&nbsp;${tj}&nbsp;</strong>条数据)
			</a>
		</c:if>
		<c:if test="${pageNum+1>j}">
			共<strong style="color: red">&nbsp;${tj}&nbsp;</strong>条数据
		</c:if>
	</c:if>
</center>
