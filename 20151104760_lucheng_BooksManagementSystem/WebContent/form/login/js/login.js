/** *****登录中心控制JS  2016年4月3日 15:43:56 ******* */

$(function() {
	//加载验证
	//initValidate();
});

/**
 * 登录验证
 */
function loginValidate(jsid){
	if(validate ()){
		var jsonObj={
				'jsid':jsid,
			"methodName":"login"
		};
		jsonValidate = jsonObj;
	}
}
/**
 * 登录
 * @param jsonObj
 */
function login(jsid){
	$("#infoDiv").html("登陆中...");
	var jsonObj = {
		'jsid':jsid,
		"zh_zhm":$("#zh_zhm").val(),
		"zh_zhmm":$("#zh_zhmm").val()
	};
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#myLoginForm").submit();
}

function addDataValidate(){
	var jsonObj={
		"methodName":"addData"
	};
	jsonValidate = jsonObj;
}

function openAddPage(){
	var jsonAjax = {
		"url" : "member_addPage.do",
		"jsonData" : null,
		"title" : "register",
		"icon" : "plus",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
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
		"methodName" : "back",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}
function back(){
		alert("注册成功");
		location.reload();
	
}