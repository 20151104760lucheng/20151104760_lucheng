/******角色JS 李飞  2016年4月13日 23:22:58  *********/
$(function(){
	//父窗口关闭加载层
	$("#dialodIndex", window.parent.document).remove();
	//本窗口关闭加载层
	closeLoadingDiv();
	//进行页面处理 如：排序 或其他之内
	initPage();
	
})
/**
 * 角色查询
 * @param page
 */
function queryJiaoShe(page){
	loadingDiv();
	var queryJiaoSheMing = $("#queryJiaoSheMing").val();
	var json = {
		"js_jsm" : queryJiaoSheMing,
		"js_id" : ""
	};
	var jsonObj = getPageParameter(json, page, "y","jiaoShe");
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#queryForm").submit();
}

/**
 * 打开添加页面
 */
function openAddPage(){
	var jsonAjax = {
		"url" : "jiaoShe_addPage.do",
		"jsonData" : null,
		"title" : "角色添加",
		"icon" : "plus",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}

/**
 * 添加验证
 */
function addDataValidate(){
	var jsonObj={
		"methodName":"addData"
	};
	jsonValidate = jsonObj;
}


/**
 * 编辑或添加得到页面中共有参数
 * @param id 当前数据ID
 * @return JSON
 */
function getUpdateJiaoSheJson(id) {
	var updateJiaoSheMing = $("#updateJiaoSheMing").val();
	var jsonObj = {
		"js_jsm" : updateJiaoSheMing,
		"js_id" : id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj); 
	return {"mapStr":jsonToStr(jsonTemp)};
}
/**
 * 添加验证回调
 * @param jsonObj
 */
function addData(jsonObj){
	var jsonObj = getUpdateJiaoSheJson("");
	var jsonAjax = {
		"url" : "jiaoShe_updateData.do",
		"jsonData" : jsonObj,
		"methodName" : "update_add",
		"toID" : "tbodyJiaoShe"
	};
	getAjaxData(jsonAjax);
}

/**
 * 打开添加页面
 * @param id 需要操作的ID
 */
function openEditPage(id){
	var jsonObj = {"js_id" : id};
	var jsonAjax = {
		"url" : "jiaoShe_editPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"title" : "角色编辑",
		"icon" : "edit",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}

/**
 * 编辑验证
 */
function editDataValidate(id){
	var jsonObj={
		"methodName":"editData",
		"id":id
	};
	jsonValidate = jsonObj;
}
/**
 * 修改数据
 * @param id
 */
function editData(jsonObj){
	var jsonAjax = {
		"url" : "jiaoShe_updateData.do",
		"jsonData" : getUpdateJiaoSheJson(jsonObj.id),
		"methodName" : "update_edit",
		"toID" : "tbodyJiaoShe"
	};
	getAjaxData(jsonAjax);
}
/**
 * 确认删除
 * @param id
 * @param name
 */
function confirmDeleteDate(id,name){
	var jsonObj = {
		"id":id,
		"name":name,
		"title":"删除角色",
		"icon" : "exclamation-triangle",
		"color":"red",
		"info":'确认删除 <span class="red">'+name+'</span> ?',
		"methodName":"deleteJiaoShe"
	};
	openConfirmPage(jsonObj);
}
/**
 * 删除角色
 * @param jsonObj
 */
function deleteJiaoShe(jsonObj){
	var jsonObj = {
		"name" : jsonObj.name,
		"id" : jsonObj.id
	};
	var jsonAjax = {
		"url" : "jiaoShe_deleteData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "update_delete",
		"toID" : "tbodyJiaoShe"
	};
	getAjaxData(jsonAjax);
}

/**
 * 跳转到角色绑定功能页
 * @param js_id
 * @param js_jsm 
 */
function jiaoSheTiedGongNengPage(js_id,js_jsm){
	var jsonData = {
		"js_id" : js_id,
		"js_jsm" : js_jsm
	};
	var jsonAjax = {
		"url" : "jiaoShe_jiaoSheTiedGongNengPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonData)},
		"title" : "角色绑定功能",
		"methodName" : "page_back"
	};
	// 调用显示函数
	getAjaxData(jsonAjax);
}

/**
 * 点击选择之后将数据保存到数据库中
 * @param js_id
 * @param js_jsm 
 */
function selectOKJiaoSheGoengNeng(id,js_jsm){
	var jsonObj = {
		"js_id" : id,
		"gnids" : getCheckboxVal("gongNengCheckBox"),
		"js_jsm":js_jsm
	};
	var jsonAjax = {
		"url" : "jiaoShe_selectGongNeng.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)}, //加入isUpdate  判断是否修改  没有修改就不进services方法
		"methodName" : "updateOver_back"
	};
	getAjaxData(jsonAjax);
}

