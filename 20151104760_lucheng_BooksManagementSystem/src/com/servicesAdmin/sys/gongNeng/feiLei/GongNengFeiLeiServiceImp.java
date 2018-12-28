package com.servicesAdmin.sys.gongNeng.feiLei;

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
 * 功能分类service 实现类，继承ServiceUtil类并实现了GongNengFeiLeiService 接口
 * 
 * 
 */
@Service("gongNengFeiLeiServiceImp")
public class GongNengFeiLeiServiceImp implements GongNengFeiLeiService {
	@Resource
	protected GYDAO gyDAOimp;// dao操作类
	
	/**
	 * 功能分类查询 SQL
	 * @param bean_GNFL
	 * @param sql
	 * @param mapSQLParameter
	 * @throws Exception
	 */
	private void getSQL(Map<String,String> mapStr,StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select t.gnfl_id,t.gnfl_mc,t.gnfl_icon,t.sj_id,zhb.zh_zhm,zhb.zh_id");
		sql.append(UtilSql.getFieldQuery());
		sql.append(" from qx_gnflb t,qx_zhb zhb");
		sql.append(" where zhb.zh_id=t.lrzh");
		if (UtilValiDate.isEmpty(mapStr.get("gnfl_id"))) {
			sql.append(" and t.gnfl_id=:gnfl_id ");
			mapSQLParameter.put("gnfl_id", mapStr.get("gnfl_id"));
		} else {
			if (UtilValiDate.isEmpty(mapStr.get("gnfl_mc"))) {
				sql.append(" and t.gnfl_mc like :gnfl_mc ");
				mapSQLParameter.put("gnfl_mc", "%" + mapStr.get("gnfl_mc") + "%");
			}
		}
	}
	/**
	 * 得到tBody数据
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String,String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		if (!UtilValiDate.isEmpty(mapStr.get(SysFinal.ISSORT_KEY))){ //由于从菜单进入没有带入参数，这里给入判断进行开启排序
			mapStr.put(SysFinal.ISSORT_KEY,"y");
		}
		List<Map<String, Object>> list_Map = this.gyDAOimp.findSqlPageList(sql, mapSQLParameter, mapStr);
		list_Map.add(this.gyDAOimp.findSqlCount(sql, mapSQLParameter));
		return list_Map;
	}

	/**
	 * 根据条件得到所有的功能分类
	 * 
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> listMap(Map<String,String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		List<Map<String, Object>> list_Map = this.gyDAOimp.findSqlPageList(sql, mapSQLParameter,mapStr);
		list_Map.add(this.gyDAOimp.findSqlCount(sql, mapSQLParameter));
		return list_Map;
	}
			
	/**
	 * 根据ID条件查询数据
	 * 
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String,String> mapStr)
			throws Exception {
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
		mapStr.put("gnfl_id", UtilSql.getTimeId());
		sql.append("insert into qx_gnflb");
		sql.append("(gnfl_id,gnfl_mc,sj_id,gnfl_icon");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values");
		sql.append("(:gnfl_id,:gnfl_mc,:sj_id,:gnfl_icon");
		sql.append(UtilSql.getFieldInsertVal());
		mapSQLParameter.put("gnfl_id", mapStr.get("gnfl_id"));
		mapSQLParameter.put("gnfl_mc", mapStr.get("gnfl_mc"));
		mapSQLParameter.put("sj_id", "0");
		mapSQLParameter.put("gnfl_icon", mapStr.get("fl_icon"));
		UtilSql.setMapVal(mapSQLParameter, mapStr);
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/**
	 * 根据条件更新数据
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public Map<String, Object> update_Data(Map<String,String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		mapStr.put("gxsj", UtilSql.getGXSJ());
		sql.append("update qx_gnflb ");
		sql.append(" set gnfl_mc=:gnfl_mc ,bz=:bz,gxsj=:gxsj,gnfl_icon=:gnfl_icon");
		sql.append(" where gnfl_id=:gnfl_id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		mapSQLParameter.put("gnfl_id", mapStr.get("gnfl_id"));
		mapSQLParameter.put("gnfl_mc", mapStr.get("gnfl_mc"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("gnfl_icon", mapStr.get("fl_icon"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void delete_Data(Map<String,String> mapStr) throws Exception {
		// 拼凑SQL 删除分类表
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("delete from qx_gnflb where gnfl_id=:gnfl_id");
		mapSQLParameter.put("gnfl_id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		// 拼凑SQL 删除功能表
		sql =sql.delete(0, sql.length());
		sql.append("delete from qx_js_gnsb where gn_id in(select gn_id from qx_gnsb where gnfl_id=:gnfl_id) ");
		mapSQLParameter.put("gnfl_id",mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		
		// 拼凑SQL 删除功能表
		sql =sql.delete(0, sql.length());
		sql.append("delete from qx_gnsb where gnfl_id=:gnfl_id ");
		mapSQLParameter.put("gnfl_id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}
}
