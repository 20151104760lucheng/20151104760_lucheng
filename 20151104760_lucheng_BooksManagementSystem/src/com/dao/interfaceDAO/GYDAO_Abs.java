package com.dao.interfaceDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilSql;
import com.util.UtilStr;

/**
 * 数据库操作DAO抽象类 将一些共性操作抽出实现
 * 
 */
public abstract class GYDAO_Abs extends NamedParameterJdbcDaoSupport implements GYDAO {
	@Resource
	public DriverManagerDataSource dataSource;

	/**
	 *是否查询到记录 返回值 boolean 返回值 true 表示查询到 false 没有查询到数据
	 * 
	 * 
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @return boolean
	 * @throws Exception
	 */
	public boolean findSqlBool(StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		return super.getNamedParameterJdbcTemplate().queryForList(UtilStr.getStr(sql), mapSQLParameter).size() > 0 ? true
				: false;
	}

	/**
	 * @author 据条件得到所有指定记录 返回值 List<Map<String, Object>>
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> findSqlList(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception {
		return super.getNamedParameterJdbcTemplate().queryForList(
				UtilStr.getStr(sql), mapSQLParameter);
	}



	/**
	 * 根据条件得到一条记录 返回值 Map<String, Object>
	 * 
	 * @param sql
	 *            sql语句
	 * @param mapSQLParameter
	 *            传入查询时所有参数
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> findSqlMap(StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception {
		List<Map<String, Object>> list_map = this.getNamedParameterJdbcTemplate()
				.queryForList(UtilStr.getStr(sql), mapSQLParameter);
		if (list_map.size() < 1) {
			return new HashMap<String, Object>();
		}
		return list_map.get(0);
	}

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
	public boolean exeSqlBool(StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		Map<String, Object> mapSQLParameterTemp = new HashMap<String, Object>();
		StringBuffer sqlTemp = new StringBuffer();
		sqlTemp.append("insert into util_log(log_id,log_par_content,log_sql_content,gxsj)");
		sqlTemp.append("values(:log_id,:log_par_content,:log_sql_content,:gxsj)");
		mapSQLParameterTemp.put("log_id",UtilSql.getTimeId());
		mapSQLParameterTemp.put("log_par_content", UtilJson.mapToJson(mapSQLParameter));
		mapSQLParameterTemp.put("log_sql_content", sql);
		mapSQLParameterTemp.put("gxsj", UtilSql.getGXSJ());
		this.exeSqlBool_log(sqlTemp, mapSQLParameterTemp);
		
		return this.getNamedParameterJdbcTemplate().update(UtilStr.getStr(sql),mapSQLParameter) > 0 ? true : false;
	}
	
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
			Map<String, Object> mapSQLParameter) throws Exception {
		return this.getNamedParameterJdbcTemplate().update(UtilStr.getStr(sql),
				mapSQLParameter) > 0 ? true : false;
	}
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
			Map<String, Object> mapSQLParameter) throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select ");
		sqlBuf.append(" count(*)  ");
		sqlBuf.append(SysFinal.TJ);
		sqlBuf.append(" from (");
		sqlBuf.append(sql);
		sqlBuf.append(") tj");
		return this.getNamedParameterJdbcTemplate().queryForList(UtilStr.getStr(sqlBuf), mapSQLParameter).get(0);
	}
}
