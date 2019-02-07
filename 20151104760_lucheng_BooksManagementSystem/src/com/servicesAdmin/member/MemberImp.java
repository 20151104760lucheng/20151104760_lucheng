package com.servicesAdmin.member;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import com.dao.interfaceDAO.GYDAO;
import com.util.UtilValiDate;
import com.util.SysFinal;
import org.springframework.stereotype.Service;
import com.util.UtilSql;
import com.util.UtilStr;
/**
 *  service 实现类
 */ 
@Service("memberImp")
public class MemberImp implements Member {
	@Resource
	private GYDAO gyDAOimp;// dao操作类

	/************************方法开始******************************/

	private void getSQL(Map<String, String> mapStr,StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select t.id,t.username,t.password,t.phone,t.nick_name,t.sex,t.age,t.gxsj,t.bz,t.lrsj,t.zt,t.lrzh");
		sql.append(" from member t where 1=1");
		if (UtilValiDate.isEmpty(mapStr.get("id"))) {
			sql.append(" and t.id=:id ");
			mapSQLParameter.put("id", mapStr.get("id"));
		}else {
			if (UtilValiDate.isEmpty(mapStr.get("username"))) {
				sql.append(" and t.username like :username");
				mapSQLParameter.put("username", "%" + mapStr.get("username") + "%");
			}
			if (UtilValiDate.isEmpty(mapStr.get("phone"))) {
				sql.append(" and t.phone like :phone");
				mapSQLParameter.put("phone", "%" + mapStr.get("phone") + "%");
			}
		}
	}

	public List<Map<String, Object>> tBody(Map<String, String> mapStr)throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		if (!UtilValiDate.isEmpty(mapStr.get(SysFinal.ISSORT_KEY))){ //由于从菜单进入没有带入参数，这里给入判断进行开启排序
			mapStr.put(SysFinal.ISSORT_KEY,"y");
		}
		List<Map<String, Object>> list_Map = this.gyDAOimp.findSqlPageList(sql, mapSQLParameter,mapStr);
		list_Map.add(this.gyDAOimp.findSqlCount(sql, mapSQLParameter));
		return list_Map;
	}

	public Map<String, Object> find_Map(Map<String, String> mapStr)throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		this.getSQL(mapStr, sql, mapSQLParameter);
		return this.gyDAOimp.findSqlMap(sql, mapSQLParameter);
	}

	public Map<String, Object> add_Data(Map<String, String> mapStr) throws Exception{
		mapStr.put("id", UtilSql.getTimeId());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("insert into member");
		sql.append("( id, username,password,phone,nick_name,sex,age");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values( :id,:username,:password,:phone,:nick_name,:sex,:age");
		sql.append(UtilSql.getFieldInsertVal());
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("username", mapStr.get("username"));
		mapSQLParameter.put("password", mapStr.get("password"));
		mapSQLParameter.put("phone", mapStr.get("phone"));
		mapSQLParameter.put("nick_name", mapStr.get("nick_name"));
		mapSQLParameter.put("sex", mapStr.get("sex"));
		mapSQLParameter.put("age", mapStr.get("age"));
		UtilSql.setMapVal(mapSQLParameter, mapStr);
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	public Map<String, Object> update_Data(Map<String, String> mapStr) throws Exception{
		mapStr.put("gxsj", UtilSql.getGXSJ());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update member set gxsj=:gxsj");
		sql.append(" ,username=:username,password=:password,phone=:phone,nick_name=:nick_name,sex=:sex,age=:age, bz=:bz ");
		sql.append(" where id=:id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("username", mapStr.get("username"));
		mapSQLParameter.put("password", mapStr.get("password"));
		mapSQLParameter.put("phone", mapStr.get("phone"));
		mapSQLParameter.put("nick_name", mapStr.get("nick_name"));
		mapSQLParameter.put("sex", mapStr.get("sex"));
		mapSQLParameter.put("age", mapStr.get("age"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	public void delete_Data(Map<String, String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("delete from member where id=:id");
		mapSQLParameter.put("id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/************************方法完毕******************************/
}