/******  操作JS  *********/
$(function(){
	//父窗口关闭加载层
	$("#dialodIndex", window.parent.document).remove();
	//本窗口关闭加载层
	closeLoadingDiv();
	//进行页面处理 如：排序 或其他之内
	initPage();
})
/************************方法开始******************************/
function query_Apply(page){
	loadingDiv();
	var query_book_id = $("#query_book_id").val();
	var query_user_id = $("#query_user_id").val();
	var json = {
		"book_id" : query_book_id,
		"user_id" : query_user_id,
 		 "id" : ""
	 }	
	var jsonObj = getPageParameter(json, page, "y","selectPageMain");
	$("#mapStr").val(jsonToStr(jsonObj));
	$("#queryForm").submit();
}


	
function xujie(id){
	var jsonObj = {
			'id':id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj);
	var jsonAjax = {
		"url" : "apply_xujie.do",
		"jsonData" : {"mapStr":jsonToStr(jsonTemp)},
		"methodName" : "query_Apply(0)",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}
	

function updateApplyData(student_id,book_id,id){
	var jsonObj = {
			'user_id':student_id,
			'book_id':book_id,
			'id':id
	};
	var jsonTemp = publicFieldsnJSON(jsonObj);
	var jsonAjax = {
		"url" : "apply_updateData.do",
		"jsonData" : {"mapStr":jsonToStr(jsonTemp)},
		"methodName" : "query_Apply(0)",
		"toID" : "tbodyData"
	};
	getAjaxData(jsonAjax);
}
/************************方法完毕******************************/