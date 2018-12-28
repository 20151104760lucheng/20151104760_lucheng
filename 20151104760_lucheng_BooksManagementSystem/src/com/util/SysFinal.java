package com.util;

/**
 * 系统常量 类
 * 
 * 
 */
public class SysFinal {
	/******分页参数名（页面条数）******/
	public static final String PAGENUM_KEY = "pageNum";
	
	/****默认页面显示条数*****/
	public static  int PAGENUM_VAL = 10;
	
	/******页面显示条数******/
	public static final String PAGENUMSELECT_KEY = "pageNumSelect";
	
	/**  是否开启排序   ***/
	public static final String ISSORT_KEY = "isSort";
	
	/*** 排序字段 默认根据 录入时间(gxsj)排序 ***/
	public static final String SORTFIELD_KEY = "sortField";
	
	/*** 排序方式 默认为 (降序)desc ***/
	public static final String SORT_TYPE = "sortType";

	/*** 数据库表中公用字段 ***/
	public static final String LRSJ_KEY="lrsj", LRZH_KEY="lrzh", ZT_KEY="zt", BZ_KEY="bz" ,GXSJ_KEY="gxsj";
	
	/*** 录入账户名---公用显示字段 ***/
	public static final String LRZHM_KEY="lrzhm";
	
	/*** 是否修改了页面中的内容 ***/
	public static final String ISUPDATE_KEY="isUpdate";
	
	/** 当前查询SQL中一共出现的条数*/
	public static final String TJ = "TJ";
	
	/** 时间格式 年_月_日期 小时_分钟_秒速 */
	public final static String YYYY_MM_DDHH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/** 时间格式 年_月_日期   */
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	
	/** 时间格式 年月日期 小时分钟秒毫秒 */
	public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";
	
	/**角色表中 (系统管理员) **/
	public static final String XTGLY_ID="0";
	
	/** mysql区别符 ***/
	public static final String MYSQL="mysql";
	
	/** 获得当前运行目录 **/
	public static String file = null;
	/** temp文件操作路径 (用于上传下载的一个临时目录) **/
	
	public static final String tempFile = "temp/";
	
	/** img文件操作路径 (用于存储图片文件夹) **/
	public static final String imgFile = "img/";
	
	/** img文件操作路径 (用于存储文件的文件夹) **/
	//public static final String fileFile = "file/";
	public static final String file_File = "file/file/";
	public static final String file_Video = "file/video/";
	public static final String file_Img = "images/goods/";
	
	
	/** 微信分割符号 **/
	public static final String weiXinParameter = "commas";
	
	/**
	 * 管理员
	 */
	public static final String ADMIN = "20170111095121029";
	
	/**
	 * 学院负责人
	 */
	public static final String XYFZR = "20171213073840778";
	
	/**
	 * 课程负责人
	 */
	public static final String KCFZR = "20171213102932319";
}
