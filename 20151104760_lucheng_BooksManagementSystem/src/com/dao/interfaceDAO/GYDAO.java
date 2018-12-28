package com.dao.interfaceDAO;

import java.util.List;
import java.util.Map;

/**
 * oracle 数据库操作DAO接口 里面包括 查询 分页 修改
 * 
 */
public interface GYDAO {

	/**
	 *是否查询到记录 返回值 boolean 返回值 true 表示查询到 false 没有查询到数据
	 * 
	 * 
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * 
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean findSqlBool(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception;

	/**
	 * @author  根据条件得到所有指定记录 返回值 List<Map<String, Object>>
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> findSqlList(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception;

	/**
	 * 根据条件得到一条记录 返回值 Map<String, Object>
	 * 
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * 
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> findSqlMap(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception;

	/**
	 * 分页查询 返回值List<Map<String, Object>>
	 * 
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @param mapUtilStr
	 *            公用操作map 参数
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> findSqlPageList(StringBuffer sql,
			Map<String, Object> mapSQLParameter, Map<String, String> mapUtilStr)
			throws Exception;

	/**
	 * 数据修改操作 当前操作成功数据 返回true 否则false 返回值 boolean
	 * 
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @param pss
	 *            传入一个PreparedStatement类参数
	 * @return boolean
	 * @throws Exception
	 */
	public boolean exeSqlBool(StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception;
	
	/**
	 * 数据修改操作 当前操作成功数据 返回true 否则false 返回值 boolean
	 * 
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @param pss
	 *            传入一个PreparedStatement类参数
	 * @return boolean
	 * @throws Exception
	 */
	public boolean exeSqlBool_log(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception;
	/**
	 * 根据条统计查到的总数 返回值 long
	 * 
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> findSqlCount(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception;
}
