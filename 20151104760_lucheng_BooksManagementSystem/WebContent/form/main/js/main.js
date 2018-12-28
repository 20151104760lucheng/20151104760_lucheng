/**********主页面JS操作 李飞  2016年4月12日 22:24:42*************/
var topPx = 120;
var leftPx = 200;
$(function () {
	$(window).resize(function() {
		$(".tab-pane").find("iframe").css("height",getBodyHeight()-topPx);
		$(".tab-pane").find("iframe").css("width",getBodyWidth()-leftPx);
		$("#indexPage").css("height",getBodyHeight()-topPx);
	});
});

/*******************修改密码*************************/
/**
 * 打开修改密码页面
 */
function openUpdatePasswordPage(){
	var jsonAjax = {
		"url" : "zhangHu_openUpdatePasswordPage.do",
		"jsonData" : null,
		"title" : "角色添加",
		"icon" : "plus",
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}

/**
 * 修改密码验证
 * @param zh_id
 */
function updatePasswordValidate(zh_id){
	var jsonObj={
		"methodName":"updatePassword",
		"zh_id":zh_id
	};
	jsonValidate = jsonObj;
}
/**
 * 修改密码
 * @param zh_id
 */
function updatePassword(jsonObj){
	var updateZhangHuMiMa = $("#updateZhangHuMiMa").val();
	var jsonObj = {
		"zh_id":jsonObj.zh_id,
		"zh_zhmm":updateZhangHuMiMa
	};
	var jsonAjax = {
		"url" : "zhangHu_updatePassword.do",
		"jsonData" : {"mapStr":jsonToStr(jsonObj)},
		"methodName" : "updatePassword_back"
	};
	getAjaxData(jsonAjax);
}
/**
 * 修改密码操作回调函数
 * @param jsonObj
 */
function updatePassword_back(jsonObj){
	if(ajaxReceipt(jsonObj.data)){
		alert("密码修改成功");
	}else{
		errorInfo();
	}
	closeShowDiv();
}


