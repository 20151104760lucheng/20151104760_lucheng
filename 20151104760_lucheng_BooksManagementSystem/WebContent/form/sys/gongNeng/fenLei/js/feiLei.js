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
 * 功能分类查询
 * @param page
 */
function queryGongNengFeiLei(page){
	loadingDiv();
	var queryFenLeiMing = $("#queryFenLeiMing").val();
	var json = {
		"gnfl_mc" : queryFenLeiMing,
		"gnfl_id" : ""
	};
	var jsonObj = getPageParameter(json, page, "y","gongNengFeiLei");
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#queryForm").submit();
}

/**
 * 打开添加页面
 */
function openAddPage(){
	var jsonAjax = {
		"url" : "gongNengFenLei_addPage.do",
		"jsonData" : null,
		"title" : "功能分类添加",
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
 * @param trIsOpen 当前tr是否打开
 * @param imgIsOpne 当前+是否加载
 * @param imgSrc 当前+显示地址
 * @return JSON
 */
function getUpdateJiaoSheJson(id,trIsOpen,imgIsOpne, imgSrc) {
	var updateFenLeiMing = $("#updateFenLeiMing").val();
	var updateFenLeiIcon = $("#updateFenLeiIcon").val();
	var jsonObj = {
		"gnfl_mc" : updateFenLeiMing,
		"gnfl_id" : id,
		"fl_icon" : updateFenLeiIcon,
		"imgIsOpne":imgIsOpne,
		"imgSrc":imgSrc,
		"trIsOpen":trIsOpen
	};
	var jsonTemp = publicFieldsnJSON(jsonObj); 
	return {"mapStr":jsonToStr(jsonTemp)};
}
/**
 * 添加验证回调
 * @param jsonObj
 */
function addData(jsonObj){
	var imgSrc = getRoot() + "/form_util/util/images/nolines_plus.gif";
	var jsonObj = getUpdateJiaoSheJson("", "n", "n", imgSrc);
	var jsonAjax = {
		"url" : "gongNengFenLei_updateData.do",
		"jsonData" : jsonObj,
		"methodName" : "update_add",
		"toID" : "tbodyGongNengFeiLei"
	};
	getAjaxData(jsonAjax);
}

/**
 * 打开添加页面
 * @param id 需要操作的ID
 */
function openEditPage(id){
	var jsonObj = {"gnfl_id" : id};
	var jsonAjax = {
		"url" : "gongNengFenLei_editPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"title" : "功能分类编辑",
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
	var img = $("#img" + jsonObj.id);
	var imgIsOpne = img.attr("isOpen");
	var imgSrc = img.attr("src");
	var trIsOpen = $("#tr"+jsonObj.id).attr("isOpen");
	var jsonAjax = {
		"url" : "gongNengFenLei_updateData.do",
		"jsonData" : getUpdateJiaoSheJson(jsonObj.id, trIsOpen, imgIsOpne, imgSrc),
		"methodName" : "update_edit",
		"toID" : "tbodyGongNengFeiLei"
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
		"title":"删除功能分类",
		"icon" : "exclamation-triangle",
		"color":"red",
		"info":'确认删除 <span class="red">'+name+'</span> ?',
		"methodName":"deleteJiaoShe"
	};
	openConfirmPage(jsonObj);
}
/**
 * 删除功能分类
 * @param jsonObj
 */
function deleteJiaoShe(jsonObj){
	var jsonObj = {
		"name" : jsonObj.name,
		"id" : jsonObj.id
	};
	var jsonAjax = {
		"url" : "gongNengFenLei_deleteData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "update_delete",
		"toID" : "tbodyGongNengFeiLei"
	};
	getAjaxData(jsonAjax);
}