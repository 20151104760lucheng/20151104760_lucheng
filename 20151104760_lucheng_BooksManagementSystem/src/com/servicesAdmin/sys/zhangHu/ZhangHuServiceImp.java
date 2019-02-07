package com.servicesAdmin.sys.zhangHu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.custom.interceptor.AccessRequired;
import com.dao.interfaceDAO.GYDAO;
import com.util.SysFinal;
import com.util.UtilSql;
import com.util.UtilStr;
import com.util.UtilValiDate;

/**
 * 账户service 实现类，并实现了ZhangHuService接口
 * 
 * 
 */
@Service("zhangHuServiceImp")
public class ZhangHuServiceImp implements ZhangHuService {
	@Resource
	private GYDAO gyDAOimp;// dao操作类

	/**
	 * SQL
	 * @param mapStr
	 * @param sql
	 * @param mapSQLParameter
	 * @throws Exception
	 */
	private void getSQL(Map<String, String> mapStr, StringBuffer sql,
			Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select");
		sql.append(" zhb.zh_id,zhb.zh_zhm,zhb.zh_zhmm");
		sql.append(",zhb.lrsj,zhb.lrzh,zhb.zt,zhb.bz,zhb.gxsj");
		sql.append(" from qx_zhb zhb ");
		sql.append(" where zhb.zh_zhm<>'admin'");
		if (UtilValiDate.isEmpty(mapStr.get("zh_id"))) {
			sql.append(" and zhb.zh_id=:zh_id ");
			mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		} else {
			if (UtilValiDate.isEmpty(mapStr.get("zh_zhm"))) {
				sql.append(" and zhb.zh_zhm = :zh_zhm ");
				mapSQLParameter.put("zh_zhm",  mapStr.get("zh_zhm"));
			}
		}
	}

	/**
	 * 得到tBody数据
	 * 
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String, String> mapStr)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		if (!UtilValiDate.isEmpty(mapStr.get(SysFinal.ISSORT_KEY))){ //由于从菜单进入没有带入参数，这里给入判断进行开启排序
			mapStr.put(SysFinal.ISSORT_KEY,"y");
		}
		List<Map<String, Object>> list_Map = this.gyDAOimp.findSqlPageList(sql,mapSQLParameter, mapStr);
		list_Map.add(this.gyDAOimp.findSqlCount(sql, mapSQLParameter));
		return list_Map;
	}

	/**
	 * 根据ID条件查询数据
	 * 
	 * @param mapStr
	 *            账户Bean
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String, String> mapStr)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		return this.gyDAOimp.findSqlMap(sql, mapSQLParameter);
	}


	/**
	 * 得到当前帐户是否存在  存在true  不存在false
	 * @param mapStr
	 * @return boolean            
	 * @throws Exception
	 */
	public boolean isExistName(Map<String, String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("select t.zh_id from qx_zhb t where t.zh_zhm=:zh_zhm");
		mapSQLParameter.put("zh_zhm", mapStr.get("zh_zhm"));
		return this.gyDAOimp.findSqlBool(sql, mapSQLParameter);	
	}

	/**
	 * 往<帐户验证表>添加一条数据
	 * @param bean_ZH
	 * @throws Exception
	 */
	public void add_Data(Map<String, String> mapStr) throws Exception {
		mapStr.put("zh_id", UtilSql.getTimeId());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("insert into qx_zhb");
		sql.append("(zh_id,zh_zhm,zh_zhmm");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values");
		sql.append("(:zh_id,:zh_zhm,:zh_zhmm");
		sql.append(UtilSql.getFieldInsertVal());
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		mapSQLParameter.put("zh_zhm", mapStr.get("zh_zhm"));
		mapSQLParameter.put("zh_zhmm",(mapStr.get("zh_zhmm")));
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
		StringBuffer sql = new StringBuffer();
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update  qx_zhb set bz=:bz,gxsj=:gxsj");
		sql.append(" where zh_id=:zh_id ");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}
	/**
	 * 账户禁用和解禁操作
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void updateLock(Map<String,String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer();
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update  qx_zhb set zt=:zt,gxsj=:gxsj");
		mapSQLParameter.put("gxsj", UtilSql.getGXSJ());
		sql.append(" where zh_id=:zh_id ");
		mapSQLParameter.put("zt", mapStr.get("zt"));
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}
	
	/**
	 * 重置账户密码   密码默认为6个000000
	 * @param mapStr
	 * @throws Exception
	 */
	public void updateResetPaw(Map<String,String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer();
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update  qx_zhb set zh_zhmm=:zh_zhmm");
		sql.append(" where zh_id=:zh_id ");
		mapSQLParameter.put("zh_zhmm",("000000"));
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}
	
	
	/**
	 * 查询出当前的所有可用角色和当前账户已经绑定的角色
	 * 
	 * @param mapStr
	 *            账户Bean
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> getJiaoShePage(Map<String, String> mapStr)
			throws Exception {
		Map<String, Object> mapList = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		//查询所有角色
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("select t.js_id,t.js_jsm from qx_jsb t where zt<>0 order by t.js_jsm asc");
		mapList.put("JiaoShe", this.gyDAOimp.findSqlList(sql, mapSQLParameter));
		//查询当前账户角色
		sql = sql.delete(0, sql.length());
		mapSQLParameter.clear();
		sql.append(" select t.js_id from qx_js_zhb t where t.zh_id=:zh_id");
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		mapList.put("ZhangHuJiaoShe", this.gyDAOimp.findSqlList(sql,mapSQLParameter));
		return mapList;
	}

	/**
	 * 将角色账户保存到 qx_js_zhb 中
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_JiaoSheZhangHu(Map<String, String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		// 第一步将qx_js_gnsb表中对应JS_ID的数据全部删除
		sql.append("delete from qx_js_zhb where zh_id=:zh_id and js_id<>'0'");
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		// 第二步将页面操作数据存入qx_js_gnsb中
		List<String> listStr = UtilStr.getStringToList(mapStr.get("jsids"), ",");
		for (int i = 0; i < listStr.size(); i++) {
			String jsid = listStr.get(i);
			sql = sql.delete(0, sql.length());
			mapSQLParameter.clear();
			sql.append("insert into qx_js_zhb");
			sql.append("(js_zhid,zh_id,js_id)");
			sql.append("values");
			sql.append("(:js_zhid,:zh_id,:js_id)");
			mapSQLParameter.put("js_zhid", UtilSql.getTimeId());
			mapSQLParameter.put("js_id", jsid);
			mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
			this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		}
	}
	
	
	/**
	 * 修改密码之前验证旧密码是否正确 查询到数据返回true 查不到数据返回false
	 * @param mapStr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isPassword(Map<String, String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("select t.zh_id from qx_zhb t where t.zh_id=:zh_id and t.zh_zhmm=:zh_zhmm");
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		mapSQLParameter.put("zh_zhmm",(mapStr.get("zh_zhmm")));
		return gyDAOimp.findSqlBool(sql, mapSQLParameter);
	}
	
	public void delete(Map<String, String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer(); 
		Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		// 第一步将qx_js_gnsb表中对应JS_ID的数据全部删除
		sql.append("delete from qx_js_zhb where zh_id=:zh_id and js_id<>'0'");
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		sql.setLength(0);
		sql.append("delete from qx_zhb where zh_id=:zh_id");
		mapSQLParameter.put("id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		
	}

	/**
	 * 修改密码 修改成功返回true 修改失败返回 false
	 * @param mapStr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean update_Password(Map<String, String> mapStr) throws Exception {
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update qx_zhb");
		sql.append(" set zh_zhmm=:zh_zhmm,gxsj=:gxsj");
		sql.append(" where zh_id=:zh_id ");
		mapSQLParameter.put("gxsj", UtilSql.getGXSJ());
		mapSQLParameter.put("zh_id", mapStr.get("zh_id"));
		mapSQLParameter.put("zh_zhmm",(mapStr.get("zh_zhmm")));
		return gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}
}
