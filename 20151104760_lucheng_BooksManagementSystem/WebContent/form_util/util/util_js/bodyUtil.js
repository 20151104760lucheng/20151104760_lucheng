/***********body操作JS 李飞  2016年4月3日 23:59:22 *************/

/***********************获得浏览器高度和宽度*****************************/
/**
 * 得到当前浏览器的可视化高度 不包含滚动条一下的高度
 * @return 高度
 */
function getBodyHeight(){  
    if(window.innerHeight!= undefined){  
        return window.innerHeight;  
    }  
    else{  
        var B= document.body, D= document.documentElement;  
        return Math.min(D.clientHeight, B.clientHeight);  
    }  
}
/**
 * 得到页面高度宽度
 * @return 宽度
 */
function getBodyWidth(){  
	return $(window).width();
}
/***********************其他JS操作*****************************/
/**
 * 得到当前目录
 * @return root
 */
function getRoot() {
	return $('#root').val();
}


/**
 * 为table中的tr加载Click事件 操作tr颜色
 */
function loadTrClick(){
//	$("tr").unbind("click").click(function(e){
//		var index = 0;
//		$("table tbody tr").each(function(){
//			//余数为0 或 有isOpen 或 有 pid属性的tr不改变其他颜色
//			if(index%2==0 && isUndefinedAndEmpty($(this).attr("isOpen")) && isUndefinedAndEmpty($(this).attr("pid"))){
//				$(this).css("background-color","#f9f9f9");
//			}else{
//				$(this).css("background-color","#fff");
//			}
//			index +=1;
//		});
//		$(this).css("background-color","#F0F0F0");
//	});
}
/**
 * 为table中的tr加载Click事件 操作checkbox
 */
function loadTrTdClickCheckbox(){
	$("tr td").unbind("click").click(function(e){
		if($(this).find("input[type=checkbox]").length==0){
			var checkboxThis = $(this).parent().find("input[type=checkbox]");
			if(checkboxThis.length!=0){
				if(checkboxThis.is(":checked")){
					checkboxThis.prop("checked",false);
				}else{
					checkboxThis.prop("checked",true);
				}
			}
		}
	});
}

/***********************页面JS初始化排序操作*****************************/



/**
 * 处理页面排序th
 */
function initPage(){
	loadTrClick();
	initSort();
}

/*********************处理页面排序******************************************/
/**
 * 处理页面排序th
 */
function initSort(){
	var jsonMap = strToJson($("#pageMap").val());
	$(".dataTable").find("thead tr th").each(function(){
		var field = $(this).attr("field");
		if(!isUndefinedAndEmpty(field)){ //拥有排序字段权限
			if(jsonMap.SORTFIELD==field){
				$("#sortField").val(field);
				if("desc" == jsonMap.SORTTYPE){
					$(this).addClass("sorting_desc");
					$("#sortType").val("desc");
				}else if("asc" == jsonMap.SORTTYPE){
					$(this).addClass("sorting_asc");
					$("#sortType").val("asc");
				}
			}else{
				$(this).addClass("sorting");
			}
		}
	});
}

/**
 * 排序查询
 * @param this_ 当前排序th
 */
function querySort(this_,sortMethosName){
	$("#sortField").val($(this_).attr("field"));
	$("#sortDataType").val($(this_).attr("type"));
	var sort = $(this_).attr("class");
	if("sorting" == sort){
		$("#sortType").val("desc");
	}else if("sorting_asc" == sort){
		$("#sortType").val("desc");
	}else if("sorting_desc" == sort){
		$("#sortType").val("asc");
	}
	doCallback(eval(sortMethosName),[0]);
}

/***********************弹出确认框公用操作*****************************/
/**
 * 打开确认页面
 * 
 * @param jsonObj  title 标题 icon图标 
 * 
 */
function openConfirmPage(jsonObj) {
	$("#jsonConfirm").val(jsonToStr(jsonObj));
	var jsonAjax = {
		"url" : "globalInfo_confirmBox.do",
		"title" : jsonObj.title,
		"icon" : jsonObj.icon,
		"color" : jsonObj.color,
		"jsonData":{"info":jsonObj.info},
		"methodName" : "page_back"
	};
	getAjaxData(jsonAjax);
}
/**
 * 点击确认按钮 回调自己方法
 */
function confirmOk() {
	var jsonStr = $("#jsonConfirm").val();
	var jsonObj = strToJson(jsonStr);
	if(!isUndefinedAndEmpty(jsonObj.methodName)){
		doCallback(eval(jsonObj.methodName), [ jsonObj ]);
	}
	closeShowDiv();
}

/***********************checkbox公用操作*****************************/
/**
 * 根据IDS将对应的checkbox选中
 * @param ids 已选中的checkbox的ID集合
 */
function checkboxChecked(ids){
	if(isUndefinedAndEmpty(ids)){
		return ;
	}
	var idArray = ids.split(",");
	for (var i in idArray) {
		$("#checkbox"+idArray[i]).attr("checked",true);
	}
}


/**
 * 选择与全部选 
 * @param obj
 * @param checkboxName
 */
function selectAll(obj,checkboxName){
	var fal = obj.checked ;
	$('input[name=' + checkboxName + ']').each(function(){
		if(isUndefinedAndEmpty($(this).attr('disabled'))){
			//如果使用attr("checked",fal) 第一次正常  第二次就不顶用了
			$(this).prop("checked",fal);
		}
	});
}

/**
 * 得到所有选中的checkbox值 返回一个由id组成的字符串
 * 
 * @param checkboxName
 *            checkbox
 * @return str
 */
function getCheckboxVal(checkboxName) {
	var chk_values = null;
	$('input[name=' + checkboxName + ']:checked').each(function() {
		var val = $(this).val();
		if (val != "" && val != null) {
			if(null==chk_values){
				chk_values = val+",";
			}else{
				chk_values += val+",";
			}
		}
	});
	return chk_values;
}

