<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input id="root" style="display: none;" value="${root}">
<input id="lrzhm" style="display: none;"  value="${zhangHu.zh_zhm}">
<input id="zh_id" style="display: none;"  value="${zhangHu.zh_id}">
<input id="isUpdate" style="display: none;" value="n">
<!-- 存储修改数据返回的数据 -->
<div id="infoupdateData" style="display: none;" ></div>
<!-- 数据排序  字段处理  默认为 更新时间   倒序-->
<input id="sortField" style="display: none;" value="gxsj">
<input id="sortType" style="display: none;" value="desc">
<input id="sortDataType" style="display: none;">
<!-- 页面参数map -->
<input style="display: none;" id="jsonConfirm" >