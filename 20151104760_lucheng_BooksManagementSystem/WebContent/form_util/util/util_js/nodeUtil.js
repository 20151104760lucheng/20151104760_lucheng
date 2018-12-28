/************节点操作JS**************/
/*********************操作节点下面子类 2015年7月9日21:48:41************************/


/**
 * 得到点击+号  得到其所有子集
 * @param fl_id 当前的分类ID
 * @param url 当前需要请求的URL
 * @return JSON 返回组织好的json对象 
 * 				"img":"img"+fl_id,
 *				"trId":"tr"+fl_id,
 *				"url":url
 */
function getNodeJson(fl_id,url,methodName){
	//json参数其中包含  用于js中参数
	var jsonParameter={
		"img":"img"+fl_id,
		"trId":"tr"+fl_id,
		"url":url,
		"methodName":methodName
	};
	return jsonParameter;
}



/**
 * 显示或隐藏树形Table的子节点数据
 * 
 * @param imgObj
 *            传入img标题的this
 * @param jsonParameter json参数其中包含
 *        trId 当前操作trID
 *        url     请求参数
 *        tbodyID 要加入到tbody的ID
 *        jsonData ajax到action的参数     
 */
function showHiddenNode(jsonParameter) {
	var imgSrc =$("#"+jsonParameter.img);
	var trs = jQuery("tr[pid=" + jsonParameter.trId + "]");
	if ("y"==imgSrc.attr("isOpen")) { // 如果是展开的图标则隐藏其子菜单
		imgSrc.attr("src", getRoot() + "/form_util/util/images/nolines_plus.gif");
		trs.css("display", "none");
		imgSrc.attr("isOpen","n");
	} else { // 显示
		imgSrc.attr("src", getRoot() + "/form_util/util/images/nolines_minus.gif");
		// 显示第一层的子节点 
		var tr = $("#"+jsonParameter.trId) ;
		trs.css("display", "");
		if(tr.attr("isOpen")=="n"){
			 var jsonAjax={
				"url":jsonParameter.url,
				"jsonData":jsonParameter.jsonData,
				"methodName":"node_back",
				"jsonParameter":jsonParameter
			};
			getAjaxData(jsonAjax);
		}
		imgSrc.attr("isOpen","y");
	}
}
/**
 * 默认点击+号  加载其下级数据 
 * @param jsonObj
 *  	  包含：data 数据
 *  	  jsonObj.jsonParameter 参数其中包含
 *        trId 当前操作trID
 *        url     请求参数
 *        tbodyID 要加入到tbody的ID
 */
function node_back(jsonObj){
	nodeReceipt(jsonObj.data,jsonObj.jsonParameter);
}
/**
 * 点击+号回执处理
 * @param data 需要处理的信息
 * @param jsonParameter json参数其中包含
 *        trId 当前操作trID
 *        url     请求参数
 *        tbodyID 要加入到tbody的ID
 */
function nodeReceipt(data,jsonParameter){
	// 显示第一层的子节点 
	var trs = jQuery("tr[id=" + jsonParameter.trId + "]");
	//将data元素追加到指定元素后面
	trs.after(data);
	//重新得到pid元素进行显示
	trs = jQuery("tr[pid=" + jsonParameter.trId + "]");
	trs.css("display", "");
	//将tr中的isOpen状态修改了y 下面就不用在请求一次后台
	var tr = $("#"+jsonParameter.trId);
	//将isOpen状态改为y
	tr.attr("isOpen","y");
	//判断是否有回调
	if(!isUndefinedAndEmpty(jsonParameter.methodName)){
		var jsonObj = editJson(jsonParameter, "data", data);
		doCallback(eval(jsonParameter.methodName),[jsonObj]);     
	}
}