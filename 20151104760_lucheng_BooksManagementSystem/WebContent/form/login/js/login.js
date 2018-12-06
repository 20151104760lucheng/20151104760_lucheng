/** *****登录中心控制JS 李飞 2016年4月3日 15:43:56 ******* */

$(function() {
	//加载验证
	//initValidate();
});

/**
 * 登录验证
 */
function loginValidate(){
	if(validate ()){
		var jsonObj={
			"methodName":"login"
		};
		jsonValidate = jsonObj;
	}
}
/**
 * 登录
 * @param jsonObj
 */
function login(jsonObj){
	$("#infoDiv").html("登陆中...");
	var jsonObj = {
		"zh_zhm":$("#zh_zhm").val(),
		"zh_zhmm":$("#zh_zhmm").val()
	};
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#myLoginForm").submit();
}

function registerValidate(){
	var jsonObj={
			"methodName":"addData"
		};
		jsonValidate = jsonObj;
	
}
/**
 * 注册
 * @param jsonObj
 */
function addData(jsonObj){
	$("#infoDiv").html("登陆中...");
	var jsonObj = {
		"zh_zhm":$("#zh_zhm").val(),
		"zh_zhmm":$("#zh_zhmm").val()
	};
	$("#mapStr2").val(jsonToStr(jsonObj));
	$("#myRegisterForm").submit();
}
function back(){
		alert("注册成功");
		location.reload();
	
}