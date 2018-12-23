/********************************根据ID查询一条数据 2015年7月6日22:31:10******************************/

/**
 * 功能分类findById
 * @param id 当前操作的id
 */
function findById_GongNengFenLei(id){
	var jsonData  =  {"gnfl_id" : id};
	var url = "gongNengFenLei_findById.do";
	findById_Body(url, jsonData,"功能分类");
}

/**
 * 功能findById
 * @param id 当前操作的id
 */
function findById_GongNeng(id){
	var jsonData  =  {"gn_id" : id}; 
	var url = "gongNeng_findById.do";
	findById_Body(url, jsonData,"功能");
}


/**
 * 查询一个角色
 * @param id 当前操作的ID
 */
function findById_JiaoShe(id){
	var jsonData  =  {"js_id" : id}; 
	var url = "jiaoShe_findById.do";
	findById_Body(url, jsonData,"角色");
}
/**
 * 查询一个帐户
 * @param id 当前操作的ID
 */
function findById_ZhangHu(id){
	var jsonData  =  {"zh_id" : id}; 
	var url = "zhangHu_findById.do";
	findById_Body(url, jsonData,"账户");
}

/**
 * 公用显示一条数据函数
 * @param url
 * @param jsonData
 */
function findById_Body(url,jsonData,title){
	var jsonAjax={
		"url":url,
		"jsonData":{"mapStr":jsonToStr(jsonData)},
		"title":title,
		"icon" : "comment",
		"methodName":"page_back"
	};
	// 调用显示函数
	getAjaxData(jsonAjax);
}