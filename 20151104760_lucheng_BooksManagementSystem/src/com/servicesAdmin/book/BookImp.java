package com.servicesAdmin.book;
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
@Service("bookImp")
public class BookImp implements Book {
	@Resource
	private GYDAO gyDAOimp;// dao操作类

	/************************系统生成方法开始******************************/

	private void getSQL(Map<String, String> mapStr,StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select t.id,t.title,t.number,t.aunthor,t.publishing,t.vision,t.status,t.gxsj,t.bz,t.lrsj,t.zt,t.lrzh");
		sql.append(" from book t where 1=1");
		if (UtilValiDate.isEmpty(mapStr.get("id"))) {
			sql.append(" and t.id=:id ");
			mapSQLParameter.put("id", mapStr.get("id"));
		}else {
			if (UtilValiDate.isEmpty(mapStr.get("title"))) {
				sql.append(" and t.title like :title");
				mapSQLParameter.put("title", "%" + mapStr.get("title") + "%");
			}
			if (UtilValiDate.isEmpty(mapStr.get("number"))) {
				sql.append(" and t.number like :number");
				mapSQLParameter.put("number", "%" + mapStr.get("number") + "%");
			}
			if (UtilValiDate.isEmpty(mapStr.get("aunthor"))) {
				sql.append(" and t.aunthor like :aunthor");
				mapSQLParameter.put("aunthor", "%" + mapStr.get("aunthor") + "%");
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
		sql.append("insert into book");
		sql.append("( id, title,number,aunthor,publishing,vision,status");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values( :id,:title,:number,:aunthor,:publishing,:vision,:status");
		sql.append(UtilSql.getFieldInsertVal());
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("title", mapStr.get("title"));
		mapSQLParameter.put("number", mapStr.get("number"));
		mapSQLParameter.put("aunthor", mapStr.get("aunthor"));
		mapSQLParameter.put("publishing", mapStr.get("publishing"));
		mapSQLParameter.put("vision", mapStr.get("vision"));
		mapSQLParameter.put("status", mapStr.get("status"));
		UtilSql.setMapVal(mapSQLParameter, mapStr);
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	public Map<String, Object> update_Data(Map<String, String> mapStr) throws Exception{
		mapStr.put("gxsj", UtilSql.getGXSJ());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update book set gxsj=:gxsj");
		sql.append(" ,title=:title,number=:number,aunthor=:aunthor,publishing=:publishing,vision=:vision,status=:status, bz=:bz ");
		sql.append(" where id=:id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("title", mapStr.get("title"));
		mapSQLParameter.put("number", mapStr.get("number"));
		mapSQLParameter.put("aunthor", mapStr.get("aunthor"));
		mapSQLParameter.put("publishing", mapStr.get("publishing"));
		mapSQLParameter.put("vision", mapStr.get("vision"));
		mapSQLParameter.put("status", mapStr.get("status"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	public void delete_Data(Map<String, String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("delete from book where id=:id");
		mapSQLParameter.put("id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/************************系统生成方法完毕******************************/
}