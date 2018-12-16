/*************************操作DIV js 如弹出DIV  弹出遮盖层******************************/

/**
 * 根据屏幕大小得到不同弹出层宽度
 * @return 当前宽度百分比
 */
function getWidth(){
	var width = null;
	if($(window).width()>1500){
		width = "60%";
	}else if($(window).width()>1200){
		width = "65%";
	}else if($(window).width()>1000){
		width = "70%";
	}else if($(window).width()>800){
		width = "80%";
	}else if($(window).width()>700){
		width = "90%";
	}else{
		width = "98%";
	}
	return width;
}

/**
 * 弹出一个层 并显示指定数据
 * @param jsonObjDIV 弹出层参数 里面包含 
 * data_width 弹出层的宽度 一般为%
 * data_title 弹出层需要显示的标题
 * data_content 指定数据内容
 * @return String 当前打开的层
 */
function openwDiv(jsonObjDIV){
	//取得名称为showDiv的DIV长度
	var div_length =divLength("showDiv"); 
	//为当前显示div创建一个新ID
	var showId = "showDiv"+div_length;
	//取得当前DIV中的各种参数属性
	var getid="#"+showId;
	//得到宽度
	var width=getWidth();
	//设置div其他样式 
	var z_index=parseInt(div_length)+9;
	var title=jsonObjDIV.data_title;
	var icon=isUndefinedAndEmpty(jsonObjDIV.icon)?"comment":jsonObjDIV.icon;
	var color=isUndefinedAndEmpty(jsonObjDIV.color)?"blue":jsonObjDIV.color;
	var dialogID = 'dialog'+div_length;
	//弹出层
	
	var divHtml = '<div id="'+showId+'" class="widget-box" style="position:fixed;" name="showDiv"><div class="widget-header"><h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-'+icon+' '+color+'"></i> '+title+'</h4>';
	divHtml+='<span class="widget-toolbar"><a href="javascript:closeShowDiv();" data-action="close" title="关闭"><i class="ace-icon fa fa-times"></i></a></span></div>';
	divHtml+='<div class="widget-body"><div class="widget-main no-padding" style="max-height:'+(getBodyHeight()-150)+'px;overflow-y:auto;overflow-x:hidden;">'+jsonObjDIV.data_content+'</div></div></div>';
	$("body").append($(divHtml));
	
	//操作遮盖曾
	var dialog_maskCss='position:fixed;left:0;top:0;right:0;bottom:0;background:#e3e3e3;filter:alpha(opacity=50);opacity:.5;display:block;overflow-x:hidden;overflow-y:auto;';
	var masklayout=$('<div style="'+dialog_maskCss+'" id='+dialogID+'></div>');
	$("body").append(masklayout);

	var win=$("#"+showId);
	//取得body对应的width  和  heigth  
	var x=parseInt($(window).width()-win.outerWidth())/2;
	var y=parseInt(getBodyHeight()-win.outerHeight())/3;
	//得到所有div下class='dialog-body'的元素
	//如果当前getBodyHeight()小于要显示层的height 这是我们做一个if处理
	if(getBodyHeight()<win.outerHeight()){
		win.css("height",getBodyHeight()-100);
		y=parseInt(getBodyHeight()-(getBodyHeight()-60))/3;
	}
	//设置显示DIV  x  y 样式
	if (y<=10){y="10"}
	
	//win.css("left",x+"px");
		win.css("left","10%");
		win.css("top","10%");
		win.css("max-width","80%");
	//win.css("top",y+"px");
	
	//设置z-index属性
	win.css("z-index",z_index);
	masklayout.css("z-index",(z_index-1));
	return showId ;
}



/**
 * 删除当前打开的的DIV
 * @param dialogID
 */
function closeShowDiv(){
	var divId = getDivTopID();
	$("#showDiv"+divId).remove();
	$("#dialog"+divId).remove();
	
}
/**
 * 点击取消按钮操作
 */
function cancelDiv(){
	$('#isUpdate').attr('value','n');
	closeShowDiv();
}



/**
 * 开启加载层
 * @return
 */
function loadingDiv(){
	//判断加载层是否存在
	if($("#dialodIndex").length>0 ){
		$("#dialodIndex").show()
		return ;
	}
	var div_length = divLength("loadingDiv");
	var divId = 'loadingDiv'+div_length;
	var styleCss = 'position:absolute;padding-top:15px;z-index:999;';
	//操作遮盖曾
	var dialog_maskCss='position:fixed;left:0;top:0;right:0;bottom:0;background:#fefefe;filter:alpha(opacity=0);opacity:.5;display:block;overflow-x:hidden;overflow-y:auto;z-index:1000;';
	//在页面中创造一个新的DIV并用户显示
	var divHtml = '<div id="dialodIndex" onclick="closeLoadingDiv()" title="点击即消失" style="'+dialog_maskCss+'"><div id="'+divId+'" name="loadingDiv" style="'+styleCss+'">&nbsp;&nbsp;<img src="'+getRoot()+'/form_util/util/images/loading.gif" style="width: 30px;height: 30px;"/></div></div>';
	//拼加在body中
	$("body").append(divHtml);
	var win = $("#"+divId);
	var x=parseInt($(window).width()-125)/2;
	var y=parseInt(getBodyHeight()-50)/2;
	win.css({"left":x,"top":y});
}

/**
 * 关闭加载层
 * @return
 */
function closeLoadingDiv(){
	$("#dialodIndex").remove();
}

/**
 * 根据DIV  name属性  得到相同名称的个数
 * @param name
 * @return int
 */
function divLength(name){
	//取得名称为showDiv的DIV长度
	return  $('div[name='+name+']').length;
}
/**
 * 取得最顶层的弹出层ID
 * @return str  层ID
 */
function getDivTopID(){
	//取得最上
	var div_length =divLength("showDiv")-1;
	return div_length;
}