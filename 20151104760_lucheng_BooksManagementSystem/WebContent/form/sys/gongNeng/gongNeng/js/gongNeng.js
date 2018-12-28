/******角色JS 李飞  2016年4月13日 23:22:58  *********/
/**
 * 点击+号触发
 *  @param fl_id
 *            当前操作分类ID
 */
function gongNengTbody(fl_id) {
	var url = "gongNeng_index.do";
	//请求加载下一集数据
	showHiddenNode(getGongNengNodeJsonData(fl_id,getNodeJson(fl_id,url)));
}

/**
 * 组织查询next node节点参数
 * @param fl_id 功能分类
 * @param jsonParameter当前已组织好的公用条件
 * @return JSON
 */
function getGongNengNodeJsonData(fl_id,jsonParameter){
	//ajax请求后台参数
	var json = {
		"fl_id" : fl_id,
		"gn_id" : ""
	};
	return editJson(jsonParameter, "jsonData",{"mapStr":jsonToStr(json)});
}


/**
 * 打开添加页面
 * @param fl_id 分类ID
 * @param fl_mc 分类名称
 */
function openAddPageGongNeng(fl_id,fl_mc){
	var jsonTemp ={
		"fl_id":fl_id,
		"fl_mc":fl_mc
	};
	var jsonAjax = {
		"url" : "gongNeng_addPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonTemp)},
		"title" : "功能添加",
		"icon" : "plus",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}
/**
 * 编辑或添加得到页面中共有参数
 * @param id 当前数据ID
 * @return JSON
 */
function getUpdateGongNengJson(id,fl_id) {
	var updateGongNengMing = $("#updateGongNengMing").val();
	var updateGongNengDiZhi = $("#updateGongNengDiZhi").val();
	var updateGongNengIcon = $("#updateGongNengIcon").val();
	var jsonObj = {
		"fl_id" : fl_id,
		"gn_mc" : updateGongNengMing,
		"gn_dz" : updateGongNengDiZhi,
		"gn_icon" : updateGongNengIcon,
		"gn_id" : id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj); 
	return {"mapStr":jsonToStr(jsonTemp)};
}

/**
 * 添加验证
 * @param fl_id 分类ID
 */
function addDataValidateGongNeng(fl_id){
	var jsonObj={
		"methodName":"addDataGongNeng",
		"fl_id":fl_id
	};
	jsonValidate = jsonObj;
}


/**
 * 添加验证回调
 * @param jsonObj
 */
function addDataGongNeng(jsonObj){
	var jsonAjax = {
		"url" : "gongNeng_updateData.do",
		"jsonData" : getUpdateGongNengJson("",jsonObj.fl_id),
		"methodName" : "update_addNode",
		"fl_id" : jsonObj.fl_id,
		"tbodyMethodName" : "gongNengTbody"
	};
	getAjaxData(jsonAjax);
}



/**
 * 打开添加页面
 * @param id 需要操作的ID
 */
function openEditPageGongNeng(id){
	var jsonObj = {"gn_id" : id};
	var jsonAjax = {
		"url" : "gongNeng_editPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"title" : "功能编辑",
		"icon" : "edit",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}

/**
 * 编辑验证
 * @param id
 * @param fl_id
 */
function editDataValidateGongNeng(id,fl_id){
	var jsonObj={
		"methodName":"editDataGongNeng",
		"id":id,
		"fl_id":fl_id
	};
	jsonValidate = jsonObj;
}
/**
 * 修改数据
 * @param id
 */
function editDataGongNeng(jsonObj){
	var jsonAjax = {
		"url" : "gongNeng_updateData.do",
		"jsonData" : getUpdateGongNengJson(jsonObj.id,jsonObj.fl_id),
		"methodName" : "update_editNode",
		"toID" : "tbodyGongNengFeiLei"
	};
	getAjaxData(jsonAjax);
}
/**
 * 确认删除
 * @param id
 * @param name
 */
function confirmDeleteDateGongNeng(id,name){
	var jsonObj = {
		"id":id,
		"name":name,
		"title":"删除功能",
		"icon" : "exclamation-triangle",
		"color":"red",
		"info":'确认删除 <span class="red">'+name+'</span> ?',
		"methodName":"deleteGongNeng"
	};
	openConfirmPage(jsonObj);
}
/**
 * 删除角色
 * @param jsonObj
 */
function deleteGongNeng(jsonObj){
	var jsonObj = {
		"name" : jsonObj.name,
		"id" : jsonObj.id
	};
	var jsonAjax = {
		"url" : "gongNeng_deleteData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "update_delete",
		"toID" : "tbodyGongNengFeiLei"
	};
	getAjaxData(jsonAjax);
}