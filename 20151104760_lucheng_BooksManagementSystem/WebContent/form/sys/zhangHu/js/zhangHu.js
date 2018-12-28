/******账户JS 李飞  2016年4月13日 23:22:58  *********/
$(function(){
	//父窗口关闭加载层
	$("#dialodIndex", window.parent.document).remove();
	//本窗口关闭加载层
	closeLoadingDiv();
	//进行页面处理 如：排序 或其他之内
	initPage();
	
})
/**
 * 账户查询
 * @param page
 */
function queryZhangHu(page){
	loadingDiv();
	var queryZhangHuMing = $("#queryZhangHuMing").val();
	var json = {
		"zh_zhm" : queryZhangHuMing,
		"zh_id" : ""
	};
	var jsonObj = getPageParameter(json, page, "y","zhangHu");
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#queryForm").submit();
}

/**
 * 打开添加页面
 */
function openAddPage(){
	var jsonAjax = {
		"url" : "zhangHu_addPage.do",
		"jsonData" : null,
		"title" : "账户添加",
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
function getUpdateZhangHuJson(id) {
	var updateZhangHuMing = $("#updateZhangHuMing").val();
	var updateZhangHuMiMa = $("#updateZhangHuMiMa").val();
	var jsonObj = {
		"zh_zhm" : updateZhangHuMing,
		"zh_zhmm" : updateZhangHuMiMa,
		"zh_id" : id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj); 
	return {"mapStr":jsonToStr(jsonTemp)};
}
/**
 * 添加验证回调
 * @param jsonObj
 */
function addData(jsonObj){
	var jsonObj = getUpdateZhangHuJson("");
	var jsonAjax = {
		"url" : "zhangHu_updateData.do",
		"jsonData" : jsonObj,
		"methodName" : "update_add",
		"toID" : "tbodyZhangHu"
	};
	getAjaxData(jsonAjax);
}

/**
 * 打开添加页面
 * @param id 需要操作的ID
 */
function openEditPage(id){
	var jsonObj = {"zh_id" : id};
	var jsonAjax = {
		"url" : "zhangHu_editPage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"title" : "账户编辑",
		"icon" : "edit",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}

/**
 * 编辑验证
 * @param id 当前操作数据ID
 * @param zt 当前操作数据状态
 */
function editDataValidate(id,zt){
	var jsonObj={
		"methodName":"editData",
		"id":id,
		"zt":zt
	};
	jsonValidate = jsonObj;
}
/**
 * 修改数据
 * @param id
 */
function editData(jsonObj){
	var updateJson = getUpdateZhangHuJson(jsonObj.id);
	var jsonAjax = {
		"url" : "zhangHu_updateData.do",
		"jsonData" : editJson(updateJson,"zt",jsonObj.zt),
		"methodName" : "update_edit",
		"toID" : "tbodyZhangHu"
	};
	getAjaxData(jsonAjax);
}
/**
 * 重置账户密码
 * @param zh_id 账户ID
 * @param zh_zhm 账户名
 */
function confirmResetPaw(zh_id,zh_zhm){
	var jsonObj = {
		"id":zh_id,
		"name":zh_zhm,
		"title":"重置密码",
		"icon" : "exclamation-triangle",
		"color":"red",
		"info":'确认重置 <span class="red">'+zh_zhm+'</span> 的密码 ?',
		"methodName":"updateResetPaw"
	};
	openConfirmPage(jsonObj);
}
/**
 * 重置密码
 * @param jsonObj
 */
function updateResetPaw(jsonObj){
	var jsonAjax = {
		"url" : "zhangHu_updateResetPaw.do",
		"jsonData" : {"zh_id" : jsonObj.id},
		"methodName" : "updateResetPaw_back",
		"name":jsonObj.name
	};
	getAjaxData(jsonAjax);
}
/**
 * 重置密码回调
 * @param jsonObj
 */
function updateResetPaw_back(jsonObj){
	var fal = ajaxReceipt(jsonObj.data);
	if(!fal){
		alert(jsonObj.name+" 重置失败");
	}
}


/**
 * 确认 解锁 禁用当前帐号
 * @param zh_id 账户ID
 * @param zh_zhm 账户名
 * @param this_ 当前操作的按钮对象
 * @param cz 操作 2 解锁 0 禁用
 */
function confirmUpdateLock(zh_id,zh_zhm,cz){
	var info = null ;
	if(cz==0){
		info="禁用";
	}else if(cz==2){
		info="解锁";
	}
	var jsonObj = {
		"id":zh_id,
		"name":zh_zhm,
		"zt":cz,
		"title":info+"账户",
		"icon" : "exclamation-triangle",
		"color":"red",
		"info":'确认 <span class="red">'+info+'('+zh_zhm+')</span> 此帐号?',
		"methodName":"updateLock"
	};
	openConfirmPage(jsonObj);
}
/**
 * 解锁 禁用当前帐号
 * @param jsonObj
 */
function updateLock(jsonObj){
	var jsonTemp = {
		"zh_id" : jsonObj.id,
		"zt" : jsonObj.zt
	};
	var jsonAjax = {
		"url" : "zhangHu_updateLock.do",
		"jsonData" : {"mapStr":jsonToStr(jsonTemp)},
		"methodName" : "updateLock_back",
		"name":jsonObj.name,
		"zt":jsonObj.zt,
		"id":jsonObj.id
	};
	getAjaxData(jsonAjax);
}
/**
 * 重置密码回调
 * @param jsonObj
 */
function updateLock_back(jsonObj){
	var fal = ajaxReceipt(jsonObj.data);
	if(fal){
		var zt = jsonObj.zt;
		var id = jsonObj.id;
		var buttonThis = $("#lockBotton"+id);
		var name = jsonObj.name;
		var ztTd = $("#zt"+id);
		if(zt==2){
			buttonThis.before('<button id="lockBotton'+id+'" class="btn btn-xs btn-danger" onclick="confirmUpdateLock(\''+id+'\',\''+name+'\',0)" title="禁用"><i class="ace-icon fa  fa-lock bigger-120"></i></button>');
			ztTd.html('<span class="badge badge-success">正常</span>');
		}else if(zt==0){
			buttonThis.before('<button id="lockBotton'+id+'" class="btn btn-xs btn-success" onclick="confirmUpdateLock(\''+id+'\',\''+name+'\',2)" title="解锁"><i class="ace-icon fa fa-unlock bigger-120"></i></button>');
			ztTd.html('<span class="badge badge-danger">禁用</span>');
		}
		buttonThis.remove();
	}else{
		alert(jsonObj.name+" 操作失败");
	}
}


/**
 * 跳转到账户绑定角色页
 * @param zh_id
 * @param zh_zhm
 */
function zhangHuTiedjiaoShePage(zh_id,zh_zhm){
	var jsonData = {
		"zh_id" : zh_id,
		"zh_zhm":zh_zhm
	};
	var jsonAjax = {
		"url" : "zhangHu_zhangHuTiedjiaoShePage.do",
		"jsonData" : {"mapStr":jsonToStr(jsonData)},
		"title" : "账户绑定角色",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
	//将isUpdate状态码复原
	$('#isUpdate').attr('value','n');
}

/**
 * 点击选择之后将数据保存到数据库中
 * @param zh_id
 * @param zh_zhm
 */
function selectOKJiaoSheGoengNeng(zh_id,zh_zhm){
	var jsonObj = {
		"zh_id" : zh_id,
		"jsids" : getCheckboxVal("jiaoSheCheckBox"),
		"zh_zhm":zh_zhm
	};
	var jsonAjax = {
		"url" : "zhangHu_selectJiaoShe.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "updateOver_back"
	};
	getAjaxData(jsonAjax);
}
