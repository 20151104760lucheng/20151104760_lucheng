/** ***************固定json格式模板 李飞 2015年7月12日20:18:30******************** */
/**
 * 返回请求page参数
 * 
 * @param json
 *            当前条件参数
 * @param page
 *            当前页
 * @param isSort
 *            是否开启排序
 * @param pageSelectID
 *            分页条数选择 select元素ID
 * @return JSON
 */
function getPageParameter(json, page, isSort, pageSelectID) {
	var jsonObj = {
		"json" : json,
		"pageNum" : page,
		"isSort" : isSort,
		"sortField" : $("#sortField").val(),
		"sortType" : $("#sortType").val(),
		"sortDataType" : $("#sortDataType").val(),
		"pageSelectID" : pageSelectID
	}
	return publicFieldsnJSON(queryJson(jsonObj));
}

/**
 * 私有方法 共有处理分页参数 其中包含：页面数 并加入了每页条数 开启排序 排序字段 排序方式
 * 
 * @param jsonObj
 *            包含： jsonObj.json 当前需要操作的json对象 jsonObj.page 当前页码 如果不需要分页参数
 *            只需随便给入一个值 建议给 "" jsonObj.isSort 是否开启排序 jsonObj.sortField
 *            排序字段 jsonObj.sortType 排序方式 jsonObj.pageSelectID 分页条数选择
 *            select元素ID
 * @return JSON
 */
function queryJson(jsonObj) {
	var pageNumSelect = $("#"+jsonObj.pageSelectID+"SelectPageNum").val();
	if(isUndefinedAndEmpty(jsonObj.pageSelectID) || isUndefinedAndEmpty(pageNumSelect)){
		pageNumSelect = 10 ;
	}
	// 如果为空就给默认值y
	if(isUndefinedAndEmpty(jsonObj.isSort)){
		jsonObj.isSort = "y";
	}
	// 如果为空就给默认值t.lrsj
	if(isUndefinedAndEmpty(jsonObj.sortField)){
		jsonObj.sortField = "t.gxsj";
	}
	// 如果为空就给默认值desc
	if(isUndefinedAndEmpty(jsonObj.sortType)){
		jsonObj.sortType = "desc";
	}
	var jsonTarget = '[{"pageNum":"' + jsonObj.pageNum
			+ '","pageNumSelect":"' + pageNumSelect
			+ '","isSort":"' + jsonObj.isSort
			+ '","sortField":"' + jsonObj.sortField
			+ '","sortType":"' + jsonObj.sortType
			+ '","sortDataType":"' + jsonObj.sortDataType
			+ '"}]';
	return copyJson(strToJson(jsonTarget), jsonObj.json);
}
/**
 * 加入公用的参数 录入时间 录入人 注册id 返回json对象
 * 
 * @param jsonObj
 *            json 请求到后台参数
 * @return JSON
 */
function publicFieldsnJSON(jsonObj){
	var lrzhm = $("#lrzhm").val(); // 帐户名称
	var lrzh = $("#zh_id").val(); // 帐户ID
	var lrsj = $("#editLrsj").val(); // 录入时间
	if(isUndefinedAndEmpty(lrsj)){
		lrsj = "";
	}
	var zt = 2; // 状态
	var bz = $("#beiZhu").val();// 备注
	var jsonTarget = '[{"lrzhm":"' + lrzhm
	+ '","lrzh":"' + lrzh
	+ '","lrsj":"' + lrsj
	+ '","zt":"' + zt
	+ '","bz":"' + toUTF_8(bz)
	+ '","isUpdate":"' + $("#isUpdate").attr("value")
	+ '"}]';
	return copyJson(strToJson(jsonTarget), jsonObj);
}