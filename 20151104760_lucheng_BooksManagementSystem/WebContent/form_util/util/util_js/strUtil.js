/************字符串操作  李飞  2015-7-23 21:08:52******************/

/**
 * 传入一个对象 返回本对象的字符串格式 如果是null/undefined 会放回""
 * @param obj
 * @return String
 */
function getStr(obj){
	obj = $.trim(obj);
	if(undefined==obj || "undefined"==obj || "null" ==obj || null == obj){
		return "" ;
	}
	return obj+"";
}

/**
 * 将一个字符转换成utf-8 模式请求到后台
 * @param str
 */
function  toUTF_8(str){
	return encodeURIComponent($.trim(str));
}

/**
 * 将一个字符转换成utf-8 模式请求到后台  使用encodeURI两次转码
 * @param str
 */
function  toEncodeURI(str){
	return encodeURI(encodeURI($.trim(str)));
}


/**
 * 传入一个字符串进行截取
 * @param str 需要截取的字符串
 * @param sign 截取标志位
 * @param leng 截取长度
 * @return 根据截取要求  截取字符串
 */
function subStrUtil(str,sign,leng){
	str = getStr(str);
	if(str.indexOf(sign)==-1){
		return str;
	}
	return str.substring(0,str.indexOf(sign)+leng);
}
/**
 * 将一个字符串转化成数字
 * @param str 需要转换的字符串
 */
function strToInt(str){
	if(isUndefinedAndEmpty(str)){
		return 0;
	}
	return parseInt(str);
}

