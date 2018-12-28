package com.servicesAdmin.sys.gongNeng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.interfaceDAO.GYDAO;
import com.util.SysFinal;
import com.util.UtilSql;
import com.util.UtilStr;
import com.util.UtilValiDate;

/**
 * 功能service 实现类，继承ServiceUtil类并并实现了GongNengService接口 
 * 
 */
@Service("gongNengServiceImp")
public class GongNengServiceImp implements GongNengService {
	@Resource
	private GYDAO gyDAOimp;// dao操作类
	/**
	 * SQL
	 * @param mapStr
	 * @param sql
	 * @param mapSQLParameter
	 * @throws Exception
	 */
	private void getSQL(Map<String,String> mapStr,StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select t.gn_id,t.gnfl_id,t.gn_mc,t.gn_dz,t.gn_icon,gnfl.gnfl_mc,zhb.zh_zhm,zhb.zh_id");
		sql.append(UtilSql.getFieldQuery());
		sql.append(" from qx_gnsb t,qx_gnflb gnfl,qx_zhb zhb");
		sql.append(" where zhb.zh_id=t.lrzh and t.gnfl_id =gnfl.gnfl_id");
		if (UtilValiDate.isEmpty(mapStr.get("gn_id"))) {
			sql.append(" and t.gn_id=:gn_id ");
			mapSQLParameter.put("gn_id", mapStr.get("gn_id"));
		}else{
			if(UtilValiDate.isEmpty(mapStr.get("fl_id"))){
				sql.append(" and t.gnfl_id = :fl_id ");
				mapSQLParameter.put("fl_id", mapStr.get("fl_id"));
			}
		}
		sql.append(" order by t.gxsj desc ");
	}
	
	/**
	 * 得到tBody数据
	 * 
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String,String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		return this.gyDAOimp.findSqlList(sql, mapSQLParameter);
	}

	/**
	 * 根据ID条件查询数据
	 * 
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String,String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		return this.gyDAOimp.findSqlMap(sql, mapSQLParameter);
	}
	/**
	 * 添加数据
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_Data(Map<String,String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("insert into qx_gnsb");
		sql.append("(gn_id, gnfl_id, gn_mc,gn_dz,gn_icon");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values");
		sql.append("(:gn_id,:gnfl_id,:gn_mc,:gn_dz,:gn_icon");
		sql.append(UtilSql.getFieldInsertVal());
		mapStr.put("gn_id", UtilSql.getTimeId());
		mapSQLParameter.put("gn_id", mapStr.get("gn_id"));
		mapSQLParameter.put("gnfl_id", mapStr.get("fl_id"));
		mapSQLParameter.put("gn_mc", mapStr.get("gn_mc"));
		mapSQLParameter.put("gn_dz", mapStr.get("gn_dz"));
		mapSQLParameter.put("gn_icon", mapStr.get("gn_icon"));
		UtilSql.setMapVal(mapSQLParameter, mapStr);
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/**
	 * 根据条件更新数据
	 * @param mapStr
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> update_Data(Map<String,String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		mapStr.put("gxsj", UtilSql.getGXSJ());
		sql.append("update qx_gnsb ");
		sql.append(" set gn_mc=:gn_mc,gnfl_id=:gnfl_id,gn_dz=:gn_dz, bz=:bz,gxsj=:gxsj,gn_icon=:gn_icon");
		sql.append(" where gn_id=:gn_id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("gn_id",mapStr.get("gn_id"));
		mapSQLParameter.put("gnfl_id", mapStr.get("fl_id"));
		mapSQLParameter.put("gn_mc", mapStr.get("gn_mc"));
		mapSQLParameter.put("gn_dz",mapStr.get("gn_dz"));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj")); 
		mapSQLParameter.put("gn_icon", mapStr.get("gn_icon")); 
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	/**
	 * 根据条件删除数据
	 * @param mapStr
	 * @throws Exception
	 */
	public void delete_Data(Map<String,String> mapStr) throws Exception {
		//删除功能表
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("delete from qx_gnsb where gn_id=:gn_id");
		mapSQLParameter.put("gn_id",mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		
		//删除角色功能树表
		sql =sql.delete(0, sql.length());
		sql.append("delete from qx_js_gnsb where gn_id=:gn_id");
		mapSQLParameter.put("gn_id",mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}
}
