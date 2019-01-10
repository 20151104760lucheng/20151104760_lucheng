/******  操作JS  *********/
$(function(){
	//父窗口关闭加载层
	$("#dialodIndex", window.parent.document).remove();
	//本窗口关闭加载层
	closeLoadingDiv();
	//进行页面处理 如：排序 或其他之内
	initPage();
})
/************************方法开始******************************/
function query_Book(page){
	loadingDiv();
	var query_title = $("#query_title").val();
	var query_number = $("#query_number").val();
	var query_aunthor = $("#query_aunthor").val();
	var json = {
		"title" : query_title,
		"number" : query_number,
		"aunthor" : query_aunthor,
 		 "id" : ""
	 }	
	var jsonObj = getPageParameter(json, page, "y","selectPageMain");
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#queryForm").submit();
}

function openAddPage(){
	var jsonAjax = {
		"url" : "book_addPage.do",
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
	var update_title = $("#update_title").val();
	var update_number = $("#update_number").val();
	var update_aunthor = $("#update_aunthor").val();
	var update_publishing = $("#update_publishing").val();
	var update_vision = $("#update_vision").val();
	var update_status = $("#update_status").val();
	var jsonObj = {
		"title" : update_title,
		"number" : update_number,
		"aunthor" : update_aunthor,
		"publishing" : update_publishing,
		"vision" : update_vision,
		"status" : update_status,
		"id" : id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj);
	return {"mapStr":jsonToStr(jsonTemp)};
}

function addData(jsonObj){
	var jsonObj = getUpdateMainJson("");
	var jsonAjax = {
		"url" : "book_updateData.do",
		"jsonData" : jsonObj,
		"methodName" : "update_add",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}

function openEditPage(id){
	var jsonObj = {"id" : id};
	var jsonAjax = {
		"url" : "book_editPage.do",
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
		"url" : "book_updateData.do",
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
		"url" : "book_deleteData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "update_delete",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}


function addApplyData(student_id,book_id){
	var jsonObj = {
			'user_id':student_id,
			'book_id':book_id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj);
	var jsonAjax = {
		"url" : "apply_updateData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonTemp)},
		"methodName" : "query_Book(0)",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}
/************************方法完毕******************************/