<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/myTag.tld " prefix="myTag"%>  
<input id="root" style="display: none;" value="${root}">
<input id="lrzhm" style="display: none;"  value="${zhangHu.zh_zhm}">
<input id="zh_id" style="display: none;"  value="${zhangHu.zh_id}">
<!-- 默认值为n   数据是否被修改  (n没有修改)  李飞 2015年7月7日0:22:08 -->
<input id="isUpdate" style="display: none;" value="n">
<!-- 存储修改数据返回的数据 -->
<div id="infoupdateData" style="display: none;" ></div>
<!-- 数据排序  字段处理  默认为 更新时间   倒序-->
<input id="sortField" style="display: none;" value="gxsj">
<input id="sortType" style="display: none;" value="desc">
<input id="sortDataType" style="display: none;">
<!-- 页面参数map -->
<input id="pageMap" style="display: none;" value="<myTag:mapToJson map="${map}"/>">
<!-- 打开确认框存放的json参数 用于点击确定时使用  李飞  2015年7月21日 16:09:45 -->
<input style="display: none;" id="jsonConfirm" >