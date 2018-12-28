package com.servicesAdmin.sys.jiaoShe;

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
 * 角色service 实现类，并实现了JiaoSheService接口
 * 
 * 
 */
@Service("jiaoSheServiceImp")
public class JiaoSheServiceImp implements JiaoSheService {
	@Resource
	private GYDAO gyDAOimp;// dao操作类
	/**
	 * SQL
	 * @param mapStr
	 * @param sql
	 * @param mapSQLParameter
	 * @throws Exception
	 */
	private void getSQL(Map<String, String> mapStr,StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select t.js_id,t.js_jsm,zhb.zh_zhm,zhb.zh_id");
		sql.append(UtilSql.getFieldQuery());
		sql.append(" from qx_jsb t,qx_zhb zhb");
		sql.append(" where zhb.zh_id=t.lrzh and t.zt<>0");
		if (UtilValiDate.isEmpty(mapStr.get("js_id"))) {
			sql.append(" and t.js_id=:js_id ");
			mapSQLParameter.put("js_id", mapStr.get("js_id"));
		} else {
			if (UtilValiDate.isEmpty(mapStr.get("js_jsm"))) {
				sql.append(" and t.js_jsm like :js_jsm");
				mapSQLParameter.put("js_jsm", "%" + mapStr.get("js_jsm") + "%");
			}
		}
	}

	/**
	 * 得到tBody数据
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String, String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		if (!UtilValiDate.isEmpty(mapStr.get(SysFinal.ISSORT_KEY))){ //由于从菜单进入没有带入参数，这里给入判断进行开启排序
			mapStr.put(SysFinal.ISSORT_KEY,"y");
		}
		List<Map<String, Object>> list_Map = this.gyDAOimp.findSqlPageList(sql, mapSQLParameter,mapStr);
		list_Map.add(this.gyDAOimp.findSqlCount(sql, mapSQLParameter));
		return list_Map;
	}

	/**
	 * 根据ID条件查询数据
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String, String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		return this.gyDAOimp.findSqlMap(sql, mapSQLParameter);
	}

	/**
	 * 添加数据
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_Data(Map<String, String> mapStr) throws Exception {
		mapStr.put("js_id", UtilSql.getTimeId());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("insert into qx_jsb");
		sql.append("(js_id, js_jsm");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values");
		sql.append("(:js_id,:js_jsm");
		sql.append(UtilSql.getFieldInsertVal());
		mapSQLParameter.put("js_id", mapStr.get("js_id"));
		mapSQLParameter.put("js_jsm", mapStr.get("js_jsm"));
		UtilSql.setMapVal(mapSQLParameter, mapStr);
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/**
	 * 根据条件更新数据
	 * @param mapStr
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> update_Data(Map<String, String> mapStr) throws Exception {
		mapStr.put("gxsj", UtilSql.getGXSJ());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update qx_jsb set");
		sql.append(" js_jsm=:js_jsm,bz=:bz,gxsj=:gxsj");
		sql.append(" where js_id=:js_id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("js_jsm", mapStr.get("js_jsm"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("js_id", mapStr.get("js_id"));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	/**
	 * 根据条件删除数据
	 * @param mapStr
	 * @throws Exception
	 */
	public void delete_Data(Map<String, String> mapStr) throws Exception {
		// 删除角色表
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("delete from qx_jsb where js_id=:js_id");
		mapSQLParameter.put("js_id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		
		// 删除角色账户表
		sql =sql.delete(0, sql.length());
		sql.append("delete from qx_js_zhb where js_id=:js_id");
		mapSQLParameter.put("js_id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		
		// 删除角色功能树表
		sql =sql.delete(0, sql.length());
		sql.append("delete from qx_js_gnsb where js_id=:js_id");
		mapSQLParameter.put("js_id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/**
	 * 查询出当前的所有可用功能和当前角色已经绑定的功能
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> getGongNengPage(Map<String, String> mapStr)
			throws Exception {
		Map<String, Object> mapList = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("select t.gn_id,t.gn_mc,flb.gnfl_mc");
		sql.append(" from qx_gnsb t,qx_gnflb flb ");
		sql.append(" where flb.gnfl_id = t.gnfl_id ");
		sql.append(" and (t.zt=2 or t.zt=0)");
		sql.append(" order by flb.gnfl_id,t.gn_mc asc");
		mapList.put("GongNeng", this.gyDAOimp.findSqlList(sql, mapSQLParameter));

		sql =sql.delete(0, sql.length());
		mapSQLParameter.clear()	;
		sql.append(" select t.gn_id from qx_js_gnsb t");
		sql.append(" where t.js_id=:js_id ");
		mapSQLParameter.put("js_id",  mapStr.get("js_id"));
		mapList.put("JiaoSheGoengNeng", this.gyDAOimp.findSqlList(sql, mapSQLParameter));
		return mapList;
	}

	/**
	 * 将功能角色保存到 qx_js_gnsb 中
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_GongNengJiaoShe(Map<String, String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		// 第一步将qx_js_gnsb表中对应JS_ID的数据全部删除
		sql.append("delete from qx_js_gnsb where js_id=:js_id");
		mapSQLParameter.put("js_id", mapStr.get("js_id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		// 第二部将页面操作数据存入qx_js_gnsb中
		List<String> listStr = UtilStr.getStringToList(mapStr.get("gnids"), ",");
		for (int i = 0; i < listStr.size(); i++) {
			String gnid = listStr.get(i);
			sql = sql.delete(0, sql.length());
			mapSQLParameter.clear();
			sql.append("insert into qx_js_gnsb");
			sql.append("(js_gnid,gn_id,js_id)");
			sql.append("values");
			sql.append("(:js_gnid,:gn_id,:js_id)");
			mapSQLParameter.put("js_gnid", UtilSql.getTimeId());
			mapSQLParameter.put("gn_id", gnid);
			mapSQLParameter.put("js_id", mapStr.get("js_id"));
			this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		}
	}
}
