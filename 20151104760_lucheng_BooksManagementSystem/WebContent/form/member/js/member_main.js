/******  操作JS  *********/
$(function(){
	//父窗口关闭加载层
	$("#dialodIndex", window.parent.document).remove();
	//本窗口关闭加载层
	closeLoadingDiv();
	//进行页面处理 如：排序 或其他之内
	initPage();
})
/************************系统生成方法开始******************************/
function query_Member(page){
	loadingDiv();
	var query_username = $("#query_username").val();
	var query_phone = $("#query_phone").val();
	var json = {
		"username" : query_username,
		"phone" : query_phone,
 		 "id" : ""
	 }	
	var jsonObj = getPageParameter(json, page, "y","selectPageMain");
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#queryForm").submit();
}

function openAddPage(){
	var jsonAjax = {
		"url" : "member_addPage.do",
		"jsonData" : null,
		"title" : "添加",
		"icon" : "plus",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}

function addDataValidate(){
	var jsonObj={
		"methodName":"addData"
	};
	jsonValidate = jsonObj;
}

function getUpdateMainJson(id) {
	var update_username = $("#update_username").val();
	var update_password = $("#update_password").val();
	var update_phone = $("#update_phone").val();
	var update_nick_name = $("#update_nick_name").val();
	var update_sex = $("#update_sex").val();
	var update_age = $("#update_age").val();
	var jsonObj = {
		"username" : update_username,
		"password" : update_password,
		"phone" : update_phone,
		"nick_name" : update_nick_name,
		"sex" : update_sex,
		"age" : update_age,
		"id" : id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj);
	return {"mapStr":jsonToStr(jsonTemp)};
}

function addData(jsonObj){
	var jsonObj = getUpdateMainJson("");
	var jsonAjax = {
		"url" : "member_updateData.do",
		"jsonData" : jsonObj,
		"methodName" : "update_add",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}

function openEditPage(id){
	var jsonObj = {"id" : id};
	var jsonAjax = {
		"url" : "member_editPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"title" : "编辑",
		"icon" : "edit",
		"methodName" : "page_back"
	};
		getAjaxData(jsonAjax);
}

function editDataValidate(id){
	var jsonObj={
		"methodName":"editData",
		"id":id
		};
	jsonValidate = jsonObj;
}

function editData(jsonObj){
	var jsonAjax = {
		"url" : "member_updateData.do",
		"jsonData" : getUpdateMainJson(jsonObj.id),
		"methodName" : "update_edit",
		"toID" :"tbodyData"
	};
	getAjaxData(jsonAjax);
}

function confirmDeleteDate(id,name){
	var jsonObj = {
		"id":id,
		"name":name,
		"title":"删除",
		"icon" : "exclamation-triangle",
		"color":"red",
		"info":'确认删除 <span class="red">'+name+'</span> ?',
		"methodName":"deleteMain"
	};
	openConfirmPage(jsonObj);
}

function deleteMain(jsonObj){
	var jsonObj = {
		"name" : jsonObj.name,
		"id" : jsonObj.id
	};
	var jsonAjax = {
		"url" : "member_deleteData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "update_delete",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}
/************************系统生成方法完毕******************************/