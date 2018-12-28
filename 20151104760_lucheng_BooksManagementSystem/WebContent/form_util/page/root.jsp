<%@ page language="java" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath_HTE_Web = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/HTE_Web";
	//取得上下文路径
	pageContext.setAttribute("root", request.getContextPath());
	pageContext.setAttribute("hte_web", basePath_HTE_Web);
%>