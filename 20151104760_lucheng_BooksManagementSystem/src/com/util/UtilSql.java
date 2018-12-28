package com.util;

import java.util.Map;

/**
 * @sm 处理sql类
 * 
 */
public class UtilSql {

	/**
	 * 得到以时间为ID的一字符串  并加当前账户ID
	 * @return String
	 * @throws Exception
	 */
	public static String getTimeId() throws Exception {
		//避免程序运行过快，每得到一个ID之前主线程停止1毫秒
		Thread.sleep(1);
		return UtilStr.getStr(UtilTime.getTime(SysFinal.YYYYMMDDHHMMSSSSS));
	}
	

	/**
	 * 返回公用的排序SQL 语句
	 * @param mapUtilStr //map工具参数
	 * @return String
	 */
	public static String getPaiXuSQL(Map<String,String> mapUtilStr) {
		if (!"y".equals(mapUtilStr.get(SysFinal.ISSORT_KEY))){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if("num".equals(mapUtilStr.get("sortDataType"))){//如果是数字类型就不做zhu
			sb.append(" order by ");
			sb.append("t."+mapUtilStr.get(SysFinal.SORTFIELD_KEY));
			sb.append(" ");
			if(!UtilValiDate.isEmpty(mapUtilStr.get(SysFinal.SORT_TYPE))){
				mapUtilStr.put(SysFinal.SORT_TYPE,"desc");
			}
			sb.append(mapUtilStr.get(SysFinal.SORT_TYPE));
			return sb.toString();
		}
		
		sb.append(" order by convert(");
		if(!UtilValiDate.isEmpty(mapUtilStr.get(SysFinal.SORTFIELD_KEY))){
			mapUtilStr.put(SysFinal.SORTFIELD_KEY,"gxsj");
		}
		sb.append("t."+mapUtilStr.get(SysFinal.SORTFIELD_KEY));
		sb.append(" using gbk)");
		if(!UtilValiDate.isEmpty(mapUtilStr.get(SysFinal.SORT_TYPE))){
			mapUtilStr.put(SysFinal.SORT_TYPE,"desc");
		}
		sb.append(mapUtilStr.get(SysFinal.SORT_TYPE));
		return UtilStr.getStr(sb);
	}

	/**
	 * 得到当前时间
	 * @return String
	 * @throws Exception
	 */
	public static String getGXSJ()throws Exception{
		return UtilTime.getTime(SysFinal.YYYY_MM_DDHH_MM_SS);
	}
	
	/**
	 * 得到公用insert name
	 * @return String
	 */
	public static String getFieldInsertKey(){
		return ",lrsj,lrzh,zt,bz,gxsj)";
	}
	/**
	 * 得到公用insert key
	 * @return String
	 */
	public static String getFieldInsertVal(){
		return ",:lrsj,:lrzh,:zt,:bz,:gxsj)";
	}
	
	/**
	 * 得到公用query field
	 * @return String
	 */
	public static String getFieldQuery(){
		return ",t.lrsj,t.lrzh,t.zt,t.bz,t.gxsj";
	}

	/**
	 * 将公用字段的map值统一存放
	 * @param mapSQLParameter
	 * @param mapUtilStr
	 * @throws Exception 
	 */
	public static void setMapVal(Map<String, Object> mapSQLParameter,Map<String,String> mapUtilStr) throws Exception{
		mapUtilStr.put("lrsj", UtilSql.getGXSJ());
		mapUtilStr.put("gxsj", UtilSql.getGXSJ());
		mapUtilStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapUtilStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("lrsj", mapUtilStr.get("lrsj"));
		mapSQLParameter.put("lrzh", mapUtilStr.get(SysFinal.LRZH_KEY));
		mapSQLParameter.put("zt", mapUtilStr.get(SysFinal.ZT_KEY));
		mapSQLParameter.put("bz", mapUtilStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("gxsj", mapUtilStr.get("gxsj"));
	}
		
	/**
	 * 得到一个开始时间  00:00:00
	 * @param lrsj
	 * @return String
	 */
	public static String getLrsjBegin(String lrsj){
		return lrsj+" 00:00:00";
	}
	
	/**
	 * 得到一个结束时间  00:00:00
	 * @param lrsj
	 * @return String
	 */
	public static String getLrsjEnd(String lrsj){
		return lrsj+" 23:59:59";
	}
}
