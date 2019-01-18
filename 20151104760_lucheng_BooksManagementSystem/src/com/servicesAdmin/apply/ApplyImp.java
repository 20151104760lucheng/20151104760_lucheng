package com.servicesAdmin.apply;
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
@Service("applyImp")
public class ApplyImp implements Apply {
	@Resource
	private GYDAO gyDAOimp;// dao操作类

	/************************方法开始******************************/

	private void getSQL(Map<String, String> mapStr,StringBuffer sql,Map<String, Object> mapSQLParameter) throws Exception {
		sql.append("select (datediff(estimatedTime,now())<0)is_overdue,is_renew,nick_name,b.title,t.id,t.book_id,t.user_id,t.createTime,estimatedTime,t.updateTime,t.gxsj,t.bz,t.lrsj,t.zt,t.lrzh");
		sql.append(" from apply t left join book b on b.id=t.book_id left join member m on m.id=t.user_id where 1=1");
		if (UtilValiDate.isEmpty(mapStr.get("id"))) {
			sql.append(" and t.id=:id ");
			mapSQLParameter.put("id", mapStr.get("id"));
		}else {
			if (UtilValiDate.isEmpty(mapStr.get("book_id"))) {
				sql.append(" and b.title like :book_id");
				mapSQLParameter.put("book_id", "%" + mapStr.get("book_id") + "%");
			}
			if (UtilValiDate.isEmpty(mapStr.get("user"))) {
				sql.append(" and t.user_id = :user");
				mapSQLParameter.put("user",  mapStr.get("user") );
			}
			if (UtilValiDate.isEmpty(mapStr.get("user_id"))) {
				sql.append(" and m.nick_name like :user_id");
				mapSQLParameter.put("user_id", "%" + mapStr.get("user_id") + "%");
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
		sql.append("insert into apply");
		sql.append("( id, book_id,user_id,createTime,estimatedTime");
		sql.append(UtilSql.getFieldInsertKey());
		sql.append("values( :id,:book_id,:user_id,now(),DATE_ADD(now(),INTERVAL +1 MONTH)");
		sql.append(UtilSql.getFieldInsertVal());
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("book_id", mapStr.get("book_id"));
		mapSQLParameter.put("user_id", mapStr.get("user_id"));
		UtilSql.setMapVal(mapSQLParameter, mapStr);
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}
	
	/**
	 * 续借
	 */
	public Map<String, Object> update_xuqi(Map<String, String> mapStr) throws Exception{
		mapStr.put("gxsj", UtilSql.getGXSJ());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update apply set gxsj=:gxsj");
		sql.append(" ,estimatedTime=DATE_ADD(estimatedTime,INTERVAL +1 MONTH), is_renew='1' ");
		sql.append(" where id=:id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("book_id", mapStr.get("book_id"));
		mapSQLParameter.put("user_id", mapStr.get("user_id"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	public Map<String, Object> update_Data(Map<String, String> mapStr) throws Exception{
		mapStr.put("gxsj", UtilSql.getGXSJ());
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("update apply set gxsj=:gxsj");
		sql.append(" ,book_id=:book_id,user_id=:user_id,updateTime=now(), bz=:bz ");
		sql.append(" where id=:id");
		mapStr.put(SysFinal.BZ_KEY, UtilStr.codeUTF_8(mapStr.get(SysFinal.BZ_KEY)));
		mapSQLParameter.put("book_id", mapStr.get("book_id"));
		mapSQLParameter.put("user_id", mapStr.get("user_id"));
		mapSQLParameter.put("bz", mapStr.get(SysFinal.BZ_KEY));
		mapSQLParameter.put("id", mapStr.get("id"));
		mapSQLParameter.put("gxsj", mapStr.get("gxsj"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
		return this.find_Map(mapStr);
	}

	public void delete_Data(Map<String, String> mapStr) throws Exception{
		StringBuffer sql = new StringBuffer(); Map<String, Object> mapSQLParameter = new HashMap<String, Object>();
		sql.append("delete from apply where id=:id");
		mapSQLParameter.put("id", mapStr.get("id"));
		this.gyDAOimp.exeSqlBool(sql, mapSQLParameter);
	}

	/************************方法完毕******************************/
}